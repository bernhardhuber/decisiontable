/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.huberb.decisiontable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import org.huberb.decisiontable.DecisionTable.Conditions.ConditionResult;
import org.huberb.decisiontable.tuple.Couple;

/**
 * A simple decision table.
 *
 * <ol>
 * <li>Using enum to name conditions. Conditions are evaluated using
 * {@link Predicate}.
 * </li>
 * <li>
 * Result of applying predicates are mapped to Rules again denoted via some
 * enum.
 * </li>
 * <li>
 * Finally rules are evaluated using {@link  Function}.
 * </li>
 * </ul>
 * <p>
 * This implementation is quite simple, and uses Builders for setting up the
 * datastructure.
 *
 * @author berni
 */
public class DecisionTable {

    /**
     * Conditions: named via {@link Enum} and evaluated by {@link Predicate}.
     *
     * @param <C> the Context-type of the predicate evaluation.
     */
    static class Conditions<C> {

        private final List<Couple<Enum, Predicate<C>>> l;

        public Conditions(List<Couple<Enum, Predicate<C>>> l) {
            this.l = l;
        }

        public static class ConditionResult {

            private final List<Couple<Enum, Boolean>> result;

            public ConditionResult(List<Couple<Enum, Boolean>> result) {
                this.result = result;
            }

            public List<Couple<Enum, Boolean>> result() {
                return result;
            }
        }

        /**
         * Evaluate conditions.
         *
         * @param ctx context for evaluating the predicates.
         * @return
         */
        public ConditionResult evaluateUsing(C ctx) {
            final List<Couple<Enum, Boolean>> result = new ArrayList<>();
            l.stream().forEach((t) -> {
                final Boolean f = t.getU().test(ctx);
                final Enum e = t.getT();
                result.add(new Couple(e, f));
            });
            return new ConditionResult(result);
        }

        /**
         * Get Boolean result of the Condition evaluation more easily, than
         * searching the result list.
         */
        public Optional<Boolean> findByCondtionEnum(ConditionResult conditionResult, Enum conditionEnum) {
            final Optional<Boolean> optBooleanResult = conditionResult.result().
                    stream().
                    filter((Couple<Enum, Boolean> elem) -> elem.getT() == conditionEnum).
                    findFirst().
                    map((Couple<Enum, Boolean> elem) -> elem.getU());
            return optBooleanResult;
        }
    }

    static class ConditionsListBuilder<C> {

        private final List<Couple<Enum, Predicate<C>>> l = new ArrayList<>();

        ConditionsListBuilder<C> enumPredicate(Enum e, Predicate<C> p) {
            l.add(new Couple(e, p));
            return this;
        }

        List<Couple<Enum, Predicate<C>>> build() {
            return l;
        }
    }

    /**
     * Map predicate evaluation results to rules.
     *
     */
    static class RuleMapping {

        final Map<ConditionResult, List<Enum>> m;

        public RuleMapping(Map<ConditionResult, List<Enum>> m) {
            this.m = m;
        }

        /**
         *
         * @param l
         * @return
         */
        List<Enum> mapToRules(ConditionResult conditionResult) {
            List<Couple<Enum, Boolean>> l = conditionResult.result;
            List<Enum> result = m.get(l);
            if (result == null) {
                for (Map.Entry<ConditionResult, List<Enum>> entry : m.entrySet()) {
                    ConditionResult entryKey = entry.getKey();

                    boolean matched = false;
                    if (l.size() == entryKey.result().size()) {
                        for (int i = 0; i < l.size(); i++) {
                            Couple<Enum, Boolean> lcouple = l.get(i);
                            Couple<Enum, Boolean> entryKeycouple = entryKey.result().get(i);

                            if (lcouple.getT() == entryKeycouple.getT()
                                    && lcouple.getU().equals(entryKeycouple.getU())) {
                                matched = true;
                            } else {
                                matched = false;
                                break;
                            }
                        }
                    }
                    if (matched) {
                        result = entry.getValue();
                    }
                }
            }
            return result;
        }
    }

    /**
     * Builder for the rule mapping map.
     */
    static class RuleMappingMapBuilder {

        ConditionResult conditionResultKey = new ConditionResult(new ArrayList<>());
        final Map<ConditionResult, List<Enum>> m = new HashMap<>();

        RuleMappingMapBuilder assocConditionWithPredicateResult(Enum e, Boolean b) {
            conditionResultKey.result().add(new Couple(e, b));
            return this;
        }

        RuleMappingMapBuilder mapToRules(List<Enum> v) {
            m.put(conditionResultKey, v);
            conditionResultKey = new ConditionResult(new ArrayList<>());
            return this;
        }

        Map<ConditionResult, List<Enum>> build() {
            return m;
        }
    }

    /**
     *
     * @param <C> Context-Type
     * @param <R> Action-Result
     */
    static class ActionMapping<C, R> {

        final Map<Enum, List<Function<C, R>>> m;

        public ActionMapping(Map<Enum, List<Function<C, R>>> m) {
            this.m = m;
        }

        /**
         * Apply rule actions.
         *
         * @param ctx context for applying some rule action.
         * @param l list of rules denoted via enum.
         * @return
         */
        List<Couple<Enum, R>> applyActions(C ctx, List<Enum> l) {
            List<Couple<Enum, R>> result = new ArrayList<>();
            Set<Enum> s = new LinkedHashSet<>(l);
            for (Enum e : s) {
                List<Function<C, R>> fl = m.get(e);
                for (Function<C, R> f : fl) {
                    R r = f.apply(ctx);
                    result.add(new Couple(e, r));
                }
            }
            return result;
        }
    }

    static class ActionMappingMapBuilder<C, R> {

        List<Function<C, R>> l = new ArrayList<>();
        final Map<Enum, List<Function<C, R>>> m = new HashMap<>();

        ActionMappingMapBuilder<C, R> action(Function<C, R> f) {
            l.add(f);
            return this;
        }

        ActionMappingMapBuilder<C, R> rule(Enum e) {
            m.put(e, l);
            l = new ArrayList<>();
            return this;
        }

        Map<Enum, List<Function<C, R>>> build() {
            return m;
        }
    }

}

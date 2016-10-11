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
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
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

        final List<Couple<Enum, Predicate<C>>> l;

        public Conditions(List<Couple<Enum, Predicate<C>>> l) {
            this.l = l;
        }

        /**
         * Evaluate conditions.
         *
         * @param ctx context for evaluating the predicates.
         * @return
         */
        public List<Couple<Enum, Boolean>> evaluateUsing(C ctx) {
            List<Couple<Enum, Boolean>> result = new ArrayList<>();
            l.stream().forEach((t) -> {
                Boolean f = t.getU().test(ctx);
                Enum e = t.getT();
                result.add(new Couple(e, f));
            });
            return result;
        }

        /**
         * Get Boolean result of the Condition evaluation more easily, than
         * searching the result list.
         */
        Optional<Boolean> findByCondtionEnum(List<Couple<Enum, Boolean>> result, Enum conditionEnum) {
            final BiFunction<List<Couple<Enum, Boolean>>, Enum, Optional<Boolean>> funcResultConditions
                    = (List<Couple<Enum, Boolean>> conditionResult, Enum cond) -> {
                        Optional<Boolean> res = conditionResult.
                        stream().
                        filter((Couple<Enum, Boolean> elem) -> elem.getT() == cond).
                        findFirst().
                        map((Couple<Enum, Boolean> elem) -> elem.getU());
                        return res;
                    };
            Optional<Boolean> bresult = funcResultConditions.apply(result, conditionEnum);
            return bresult;
        }
    }

    static class ConditionsListBuilder<C> {

        final List<Couple<Enum, Predicate<C>>> l = new ArrayList<>();

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

        final Map<List<Couple<Enum, Boolean>>, List<Enum>> m;

        public RuleMapping(Map<List<Couple<Enum, Boolean>>, List<Enum>> m) {
            this.m = m;
        }

        /**
         *
         * @param l
         * @return
         */
        List<Enum> mapToRules(List<Couple<Enum, Boolean>> l) {
            List<Enum> result = m.get(l);
            if (result == null) {
                for (Map.Entry<List<Couple<Enum, Boolean>>, List<Enum>> entry : m.entrySet()) {
                    List<Couple<Enum, Boolean>> entryKey = entry.getKey();

                    boolean matched = false;
                    if (l.size() == entryKey.size()) {
                        for (int i = 0; i < l.size(); i++) {
                            Couple<Enum, Boolean> lcouple = l.get(i);
                            Couple<Enum, Boolean> entryKeycouple = entryKey.get(i);

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

        List<Couple<Enum, Boolean>> keyl = new ArrayList<>();
        final Map<List<Couple<Enum, Boolean>>, List<Enum>> m = new HashMap<>();

        RuleMappingMapBuilder assocConditionWithPredicateResult(Enum e, Boolean b) {
            keyl.add(new Couple(e, b));
            return this;
        }

        RuleMappingMapBuilder mapToRules(List<Enum> v) {
            m.put(keyl, v);
            keyl = new ArrayList<>();
            return this;
        }

        Map<List<Couple<Enum, Boolean>>, List<Enum>> build() {
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

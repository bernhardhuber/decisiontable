/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.huberb.decisiontable;

import java.util.function.Function;
import java.util.function.Predicate;

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
}

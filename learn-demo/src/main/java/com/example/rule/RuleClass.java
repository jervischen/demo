package com.example.rule;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;
import org.jeasy.rules.support.ActivationRuleGroup;
import org.jeasy.rules.support.UnitRuleGroup;

/**
 * @author Chen Xiao
 * @since 2021-05-12 13:00
 */
public class RuleClass {
    @Rule(priority = 1)
    public static class FizzRule {
        @Condition
        public boolean isFizz(@Fact("number") Integer number) {
            return number % 5 == 0;
        }

        @Action
        public void printFizz(@Fact("number") Integer number) {
            System.out.print("----buzz "+number+"可以整除5----");
        }

//        @Action
//        public void printFizz1(@Fact("number") Integer number) {
//            System.out.print("-多个ACTION-");
//        }
    }


    @Rule(priority = 2)
    public static class BuzzRule {
        @Condition
        public boolean isBuzz(@Fact("number") Integer number) {
            return number % 7 == 0;
        }

        @Action
        public void printBuzz(@Fact("number") Integer number) {
            System.out.print("----buzz "+number+"可以整除7----");
        }
    }

    public static class FizzBuzzRule extends UnitRuleGroup {

        public FizzBuzzRule(Object... rules) {
            for (Object rule : rules) {
                addRule(rule);
            }
        }

        @Override
        public int getPriority() {
            return 4;
        }
    }

    @Rule(priority = 3)
    public static class NonFizzBuzzRule {

        @Condition
        public boolean isNotFizzNorBuzz(@Fact("number") Integer number) {
            // can return true, because this is the latest rule to trigger according to
            // assigned priorities
            // and in which case, the number is not fizz nor buzz
            return number % 5 != 0 || number % 7 != 0;
        }

        @Action
        public void printInput(@Fact("number") Integer number) {
            System.out.print("----"+number+" 不能可以整除7或5----");
        }
    }


    public static class FizzBuzzRule1 extends ActivationRuleGroup {

        public FizzBuzzRule1(Object... rules) {
            for (Object rule : rules) {
                addRule(rule);
            }
        }

        @Override
        public int getPriority() {
            return 4;
        }
    }
}

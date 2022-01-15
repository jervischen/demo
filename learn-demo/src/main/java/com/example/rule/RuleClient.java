package com.example.rule;

import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rule;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.core.DefaultRulesEngine;
import org.jeasy.rules.core.RulesEngineParameters;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Chen Xiao
 * @since 2021-05-12 13:04
 */
public class RuleClient {
    public static void main(String[] args) {
        // create a rules engine
        RulesEngineParameters parameters = new RulesEngineParameters();
        RulesEngine fizzBuzzEngine = new DefaultRulesEngine(parameters);

        // create rules
        Rules rules = new Rules();
//        rules.register(new RuleClass.FizzRule());
//        rules.register(new RuleClass.BuzzRule());
//        rules.register(new RuleClass.NonFizzBuzzRule());

        //and 条件
//        rules.register(new RuleClass.FizzBuzzRule(new RuleClass.FizzRule(), new RuleClass.BuzzRule()));
        //OR 条件
        rules.register(new RuleClass.FizzBuzzRule1(new RuleClass.FizzRule(), new RuleClass.BuzzRule()));
        // fire rules
        Facts facts = new Facts();
        facts.put("number", 7);
        fizzBuzzEngine.fire(rules, facts);

        Map<String,Integer> map = new HashMap<>();
//        map.put()



        Map<Rule, Boolean> check = fizzBuzzEngine.check(rules, facts);
        for (Boolean value : check.values()) {
                System.out.println(value);
        }

    }
}

package com.example.rule;

import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.core.DefaultRulesEngine;
import org.jeasy.rules.core.RulesEngineParameters;
import org.jeasy.rules.mvel.MVELRuleFactory;

import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * @author Chen Xiao
 * @since 2021-05-12 15:36
 */
public class RuleClient2 {
    public static void main(String[] args) throws FileNotFoundException {
        // create a rules engine
        RulesEngineParameters parameters = new RulesEngineParameters().skipOnFirstAppliedRule(true);
        RulesEngine fizzBuzzEngine = new DefaultRulesEngine(parameters);

        // create rules
        String path = new RuleClient2().getClass().getResource("").getPath();
        System.out.println(path);
        Rules rules = MVELRuleFactory.createRulesFrom(new FileReader(path+ "/fizzbuzz1.yml"));

        // fire rules
        Facts facts = new Facts();
        for (int i = 1; i <= 100; i++) {
            facts.put("number", i);
            fizzBuzzEngine.fire(rules, facts);
            System.out.println();
        }
    }
}

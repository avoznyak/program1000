package org.selenide.programs.programs1000;
 
import cucumber.api.junit.Cucumber;
import cucumber.api.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class) 
@CucumberOptions (
        //plugin = {"html:target/cucumber-report/smoketest", "json:target/cucumber.json"},
		plugin = {"json:target/cucumber.json"},
        features = "src/test/java/cucumberJava",
		
        //glue = "ru/riskmarket/steps",
        //tags = "@inDevelopment2")
tags = "@testFeature")
public class runTest { }
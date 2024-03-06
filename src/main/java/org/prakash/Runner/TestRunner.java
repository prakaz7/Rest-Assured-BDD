package org.prakash.Runner;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.plugin.Plugin;

@CucumberOptions(
        features = "src/main/resources/Features/",
        glue = {"org.prakash.Steps"},
        tags = "@test",
        monochrome = true,
        plugin = {"pretty",
                "html:target/cucumber-reports/cucumber-pretty/cucumber-Report.html",
                "json:target/cucumber-reports/CucumberTestReport.json",
                "rerun:target/cucumber-reports/rerun.txt",
                //"com.cucumber.listener.ExtentCucumberFormatter",
        		"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
        		}

)
public class TestRunner extends AbstractTestNGCucumberTests{
	
	

}
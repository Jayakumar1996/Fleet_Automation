package org.runner;

import org.junit.runner.RunWith;
import org.report.ReportingClass;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.CucumberOptions.SnippetType;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/resources/Features/F03_practice.feature",
    dryRun = false,
    glue = {"org.steps", "hooks"},
    monochrome = true,
    snippets = SnippetType.CAMELCASE,
    plugin = {
        "pretty",
        "html:report/cucumber-reports/report.html",
        "json:report/cucumber-reports/report.json",
        "junit:report/cucumber-reports/report.xml"
    }
)
public class TestRunnerClass {
//
//    @AfterClass
//    public static void report() {
//        try {
//            // ✅ 1. Use correct JSON path as per your Cucumber plugin
//            ReportingClass.generateReport("report/cucumber-reports/report.json");
//
//            System.out.println("Email started to trigger in TestRunnerClass");
//
//            // ✅ 2. Read report path from config
//            ConfigReader config = new ConfigReader();
//            File reportFile = new File(config.getReportPath());
//
//            if (!reportFile.exists()) {
//                System.err.println("❌ Test report not found at: " + reportFile.getAbsolutePath());
//            } else {
//                EmailUtil.sendEmail(reportFile.getAbsolutePath());
//                System.out.println("✅ Email sent successfully with report");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.err.println("❌ Failed to generate or email report");
//        }
//    }
}

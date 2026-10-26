package listeners;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestResult;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SuiteMetricsListener implements ISuiteListener {

    @Override
    public void onStart(ISuite suite) {
        // No setup needed at initialization
    }

    @Override
    public void onFinish(ISuite suite) {
        int totalPassed = 0;
        int totalFailed = 0;
        int totalSkipped = 0;
        List<ITestResult> failedTestsList = new ArrayList<>();

        // 1. Gather results and track failures from all execution threads
        Map<String, ISuiteResult> suiteResults = suite.getResults();
        for (ISuiteResult sr : suiteResults.values()) {
            ITestContext tc = sr.getTestContext();
            totalPassed += tc.getPassedTests().size();
            totalFailed += tc.getFailedTests().size();
            totalSkipped += tc.getSkippedTests().size();
            failedTestsList.addAll(tc.getFailedTests().getAllResults());
        }

        int totalTests = totalPassed + totalFailed + totalSkipped;

        // 2. Capture and format execution date and time
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter fileFormatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
        DateTimeFormatter displayFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        
        String timestampForFile = now.format(fileFormatter);
        String timestampForDisplay = now.format(displayFormatter);

        // 3. Build the Failures Table rows dynamically
        StringBuilder failureRows = new StringBuilder();
        if (failedTestsList.isEmpty()) {
            failureRows.append("<tr><td colspan='2' style='color:#2ed573; text-align:center; font-weight:bold;'>🎉 All tests passed successfully!</td></tr>");
        } else {
            for (ITestResult result : failedTestsList) {
                String className = result.getTestClass().getName();
                String methodName = result.getName();
                
                String errorMessage = "No exception message provided.";
                if (result.getThrowable() != null) {
                    errorMessage = result.getThrowable().getMessage() != null ? 
                                   result.getThrowable().getMessage() : 
                                   result.getThrowable().toString();
                }
                
                failureRows.append("<tr>")
                           .append("<td><strong>").append(className).append("</strong><br><small style='color:#64748b;'>Method: ").append(methodName).append("</small></td>")
                           .append("<td class='error-log'>").append(escapeHtml(errorMessage)).append("</td>")
                           .append("</tr>");
            }
        }

        // 4. Generate HTML reporting layout with explicit timestamp visibility
        String htmlContent = "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "    <title>Test Execution Dashboard</title>\n" +
                "    <style>\n" +
                "        body { font-family: Arial, sans-serif; background-color: #f4f6f9; margin: 40px; color: #333; }\n" +
                "        .container { max-width: 800px; background: white; padding: 30px; border-radius: 8px; box-shadow: 0 4px 15px rgba(0,0,0,0.1); margin: 0 auto; }\n" +
                "        h2, h3 { text-align: center; color: #2c3e50; padding-bottom: 10px; }\n" +
                "        h2 { border-bottom: 2px solid #ecf0f1; padding-bottom: 15px; }\n" +
                "        h3 { text-align: left; margin-top: 35px; border-bottom: 1px solid #cbd5e1; padding-bottom: 5px; color: #475569; }\n" +
                "        .meta-info { background-color: #f8fafc; padding: 12px; border-radius: 6px; margin-bottom: 20px; font-size: 14px; line-height: 1.6; border: 1px solid #e2e8f0; }\n" +
                "        .metric-table, .failure-table { width: 100%; border-collapse: collapse; margin-top: 15px; }\n" +
                "        .metric-table th, .metric-table td, .failure-table th, .failure-table td { padding: 12px; text-align: left; border-bottom: 1px solid #e2e8f0; }\n" +
                "        .metric-table th, .failure-table th { background-color: #f8fafc; color: #64748b; font-weight: 600; }\n" +
                "        .pass { color: #2ed573; font-weight: bold; }\n" +
                "        .fail { color: #ff4757; font-weight: bold; }\n" +
                "        .skip { color: #ffa502; font-weight: bold; }\n" +
                "        .total { font-weight: bold; color: #2c3e50; }\n" +
                "        .error-log { color: #dc2626; font-family: 'Courier New', Courier, monospace; font-size: 13px; background-color: #fef2f2; border-radius: 4px; white-space: pre-wrap; }\n" +
                "        .footer { text-align: center; margin-top: 30px; font-size: 12px; color: #94a3b8; }\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <div class='container'>\n" +
                "        <h2>Test Suite Execution Report</h2>\n" +
                "        <div class='meta-info'>\n" +
                "            <strong>Suite Name:</strong> " + suite.getName() + "<br>\n" +
                "            <strong>Execution Date & Time:</strong> " + timestampForDisplay + "\n" +
                "        </div>\n" +
                "        \n" +
                "        <table class='metric-table'>\n" +
                "            <tr><th>Metric Status</th><th>Count</th></tr>\n" +
                "            <tr class='pass'><td>Passed</td><td>" + totalPassed + "</td></tr>\n" +
                "            <tr class='fail'><td>Failed</td><td>" + totalFailed + "</td></tr>\n" +
                "            <tr class='skip'><td>Skipped</td><td>" + totalSkipped + "</td></tr>\n" +
                "            <tr class='total'><td>Total Executed</td><td>" + totalTests + "</td></tr>\n" +
                "        </table>\n" +
                "        \n" +
                "        <h3>Failure Breakdown Details</h3>\n" +
                "        <table class='failure-table'>\n" +
                "            <tr><th style='width: 35%;'>Failed Test Component</th><th style='width: 65%;'>Failure Reason / Exception</th></tr>\n" +
                "            " + failureRows.toString() + "\n" +
                "        </table>\n" +
                "        \n" +
                "        <div class='footer'>Report generated automatically by TestNG Framework Suite Listener</div>\n" +
                "    </div>\n" +
                "</body>\n" +
                "</html>";

        // 5. Save the timestamped report locally
        writeHtmlReport(htmlContent, timestampForFile);
    }

    private void writeHtmlReport(String content, String timestamp) {
        String outputDir = System.getProperty("user.dir") + File.separator + "target" + File.separator + "custom-reports";
        File directory = new File(outputDir);
        
        if (!directory.exists()) {
            directory.mkdirs();
        }

        // Appends the execution timestamp directly into the file name string
        String fileName = "suite-summary_" + timestamp + ".html";
        File reportFile = new File(directory, fileName);

        try (FileWriter writer = new FileWriter(reportFile)) {
            writer.write(content);
            System.out.println("Local HTML execution dashboard generated successfully at: " + reportFile.getAbsolutePath());
        } catch (IOException e) {
            System.err.println("Failed to write local HTML dashboard output: " + e.getMessage());
        }
    }

    private String escapeHtml(String input) {
        if (input == null) return "";
        return input.replace("&", "&amp;")
                    .replace("<", "&lt;")
                    .replace(">", "&gt;")
                    .replace("\"", "&quot;")
                    .replace("'", "&#39;");
    }
}

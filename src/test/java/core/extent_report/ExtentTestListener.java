package core.extent_report;

import com.aventstack.extentreports.Status;

import com.microsoft.playwright.Page;
import core.commons.BaseTest;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.nio.file.Paths;

import static core.extent_report.ExtentManager.getTest;


public class ExtentTestListener  implements ITestListener {
    private static String getTestMethodName(ITestResult iTestResult) {
        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        ExtentManager.extentReports.flush();
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        getTest().log(Status.PASS, "Test passed");
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        Object testClass = iTestResult.getInstance();
//        WebDriver driver = ((BaseTest) testClass).getDriver();
        Page page = ((BaseTest) testClass).getPage();
//        String base64Screenshot = "data:image/png;base64," + ((TakesScreenshot) page).getScreenshotAs(OutputType.BASE64);

        page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("target/example.png"))) ;
        getTest().log(Status.FAIL, "Test Failed", getTest().addScreenCaptureFromPath("C:\\Users\\Admin\\Downloads\\ExcelDriven\\PLaywright_Project\\target\\example.png").getModel().getMedia().get(0));
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        getTest().log(Status.SKIP, "Test Skipped");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        getTest().log(Status.FAIL, "Test Failed with percentage" + getTestMethodName(iTestResult));
    }

    @Override
    public void onStart(ITestContext iTestContext) {

    }
}

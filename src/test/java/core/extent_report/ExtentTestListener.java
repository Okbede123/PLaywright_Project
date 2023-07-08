package core.extent_report;

import com.aventstack.extentreports.Status;

import com.microsoft.playwright.Page;
import core.GlobalConstant;
import core.commons.BaseTest;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.lang.reflect.Method;
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
        ExtentManager.startTest(iTestResult.getMethod().getMethodName()+ BaseTest.thread.get(),BaseTest.thread.get() + " luong");

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

        page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("target/example.png")).setFullPage(true)) ;
        getTest().log(Status.FAIL, "Test Failed", getTest().addScreenCaptureFromPath(GlobalConstant.LINK_PROJECT + "example.png").getModel().getMedia().get(0));
//        System.out.println(GlobalConstant.LINK_PROJECT + "example.png");
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

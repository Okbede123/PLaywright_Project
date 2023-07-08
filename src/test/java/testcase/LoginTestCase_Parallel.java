package testcase;

import action.pageobject.HomePageObject;
import action.pageobject.LoginPageObject;
import action.pageobject.PageGeneralManager;
import com.aventstack.extentreports.Status;
import com.microsoft.playwright.Page;
import core.commons.BaseTest;
import core.extent_report.ExtentManager;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

public class LoginTestCase_Parallel extends BaseTest {

    Page page;

    HomePageObject homePageObject;

    @Parameters({"browser","url","thread"})
    @BeforeClass
    public void beforeClass(String browser, String url, String thread){
        page = openBrowser_OneTabs(false,browser,url,thread);
    }

    @Test
    public void Login(){
        ExtentManager.getTest().log(Status.INFO,"bat dau login");
        LoginPageObject LoginPageObject = PageGeneralManager.openLoginPage(page);
       homePageObject = LoginPageObject.doLoginUserNameAndPassWord("mngr513624","vumUgys");

    }

    @Test
    public void verifyLogin(){
        ExtentManager.getTest().log(Status.INFO,"verify login thanh cong");
        String login_Ok = homePageObject.getTextLogin();
        System.out.println(login_Ok);
        Assert.assertFalse(homePageObject.verifyElementLoginVisibility());
    }

    @AfterClass
    public void tearDownOffBrowser(){
        closeBrowser();
    }
}

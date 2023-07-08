package action.pageobject;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import core.commons.BasePage;
import interfaceUI.LoginPageUI;


public class LoginPageObject extends BasePage {


    public LoginPageObject(Page page) {
        super(page);

    }

    public HomePageObject doLoginUserNameAndPassWord(String userName,String password){
        sendKeys(LoginPageUI.FILL_USERNAME_OR_PASSWORD("uid"),userName);
        sendKeys(LoginPageUI.FILL_USERNAME_OR_PASSWORD("password"),password);
        click(LoginPageUI.CLICK("login"));
        return PageGeneralManager.openHomePage(page);
    }
}

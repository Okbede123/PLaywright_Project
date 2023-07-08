package action.pageobject;

import com.microsoft.playwright.Page;

public class PageGeneralManager {

    public static LoginPageObject openLoginPage(Page page){
        return new LoginPageObject(page);
    }

    public static HomePageObject openHomePage(Page page){
        return new HomePageObject(page);
    }
}

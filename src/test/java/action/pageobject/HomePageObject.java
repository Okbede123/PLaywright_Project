package action.pageobject;

import com.microsoft.playwright.Page;
import core.commons.BasePage;
import interfaceUI.HomePageUI;

public class HomePageObject extends BasePage {

    public HomePageObject(Page page) {
        super(page);
    }

    public String getTextLogin(){
       return getText(HomePageUI.TEXT_LOGIN);
    }

    public boolean verifyElementLoginVisibility(){
        return isElementVisibility(HomePageUI.TEXT_LOGIN);
    }
}

package core.commons;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class BasePage {
    protected Page page;
    double time  = 15000;

    public BasePage(Page page){
        this.page = page;
    }

    public void openUrl(String url){
        page.navigate(url);
    }

    public Locator getElement(String locator){
       return page.locator(locator);
    }

    public void sendKeys(String locator,String value){
        getElement(locator).waitFor(waitElement(time));
        getElement(locator).fill(value);
    }

    public void click(String locator){
        getElement(locator).waitFor(waitElement(time));
        getElement(locator).click();
    }

    public String getText(String locator){
        getElement(locator).waitFor(waitElement(time));
        return page.locator(locator).textContent();
    }

    public Locator.WaitForOptions waitElement(double time){
        Locator.WaitForOptions waitForOptions = new Locator.WaitForOptions();
        waitForOptions.setTimeout(time);
        return waitForOptions;
    }

    public boolean isElementVisibility(String locator){
        getElement(locator).waitFor(waitElement(time));
       return getElement(locator).isVisible();
    }

}

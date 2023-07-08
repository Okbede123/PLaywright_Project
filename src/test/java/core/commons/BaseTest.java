package core.commons;

import com.microsoft.playwright.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class BaseTest {

    Playwright playwright;
    BrowserType.LaunchOptions launchOptions;
    Browser browser;
    BrowserContext browserContext;
    Page page;
    Properties properties;
    public static ThreadLocal<String> thread = new ThreadLocal<>();

    public Page openBrowser_OneTabs(boolean setHeadless,String browser,String url,String thread){
        playwright = Playwright.create();
        launchOptions = new BrowserType.LaunchOptions().setHeadless(setHeadless);
        switch (browser){
            case "chromium":{
                this.browser = playwright.chromium().launch(launchOptions);
                break;
            }
            case "firefox":{
                this.browser = playwright.firefox().launch(launchOptions);
                break;
            }
            case "webkit":{
                this.browser = playwright.webkit().launch(launchOptions);
                break;
            }
            case "chrome":{
                this.browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(setHeadless));
                break;
            }
            case "msedge":{
                this.browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setChannel("msedge").setHeadless(setHeadless));
                break;
            }
            default:{
                System.out.println("vao default");
                throw new RuntimeException();
            }
        }
        String pro = readProperties("config.properties").getProperty("url");
        System.out.println("property test doc url la "+ pro);
        browserContext = this.browser.newContext();
        page = browserContext.newPage();
        page.navigate(url);
        if(!thread.isEmpty()){
            this.thread.set(thread);
        }
        return page;
    }

    public void closeBrowser(){
        page.context().close();
        browser.close();
        playwright.close();
    }

    public Page getPage(){
        return page;
    }

    public Properties readProperties(String nameFile){
        try {
            FileInputStream fileInputStream = new FileInputStream("src/test/java/resource/"+nameFile);
            properties = new Properties();
            properties.load(fileInputStream);
        } catch (IOException e ) {
            System.out.println("readProperties failed");
            throw new RuntimeException(e);

        }
        return properties;
    }
}

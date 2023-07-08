package interfaceUI;

public class LoginPageUI {

    public static final String FILL_USERNAME_OR_PASSWORD(String value){
        return String.format("//input[@name='%s']",value);
    }

    public static final String CLICK(String value){
        return String.format("//input[@value='%s']",value.toUpperCase());
    }
}

package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.support.FindBy;

public class LoginRegistrationScreen extends BaseScreen {
    @FindBy(xpath = "//*[@resource-id= 'com.sheygam.contactapp:id/regBtn']")
    MobileElement registrationBtnView;

    @FindBy(xpath = "//*[@resource-id= 'com.sheygam.contactapp:id/loginBtn']")
    MobileElement loginBtnView;

    public LoginRegistrationScreen(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    public String getRegistration(){
        return registrationBtnView.getText();

    }

    public String getLogin(){
        return loginBtnView.getText();

    }
}
//com.sheygam.contactapp:id/regBtn
//com.sheygam.contactapp:id/loginBtn
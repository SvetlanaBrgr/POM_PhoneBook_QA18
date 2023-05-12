package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import models.Auth;
import org.openqa.selenium.Alert;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class AuthenticationScreen extends BaseScreen {
    public AuthenticationScreen(AppiumDriver<MobileElement> driver) {
        super(driver);
    }
    @FindBy(xpath = "//*[@resource-id= 'com.sheygam.contactapp:id/inputEmail']")
    MobileElement editTextEmail;//перечисление полей с аннотацией @FindBy


    @FindBy(xpath = "//*[@resource-id= 'com.sheygam.contactapp:id/inputPassword']")
    MobileElement editTextPassword;

    @FindBy(xpath = "//*[@resource-id= 'com.sheygam.contactapp:id/regBtn']")
    MobileElement registrationButton;

    @FindBy(xpath = "//*[@resource-id= 'com.sheygam.contactapp:id/loginBtn']")
    MobileElement loginButton;

//    @FindBy(xpath = "//*[@resource-id = 'android:id/button1']") //resource-id	android:id/button1
//    MobileElement OKButton;
    @FindBy(id = "android:id/button1") //cw-23
    MobileElement okButton;

//    @FindBy(xpath = "//*[@resource-id = 'android:id/message']")  //resource-id	android:id/message (text) hw-22
//       MobileElement errorTextView;
    @FindBy(id = "android:id/message") //cw-23 android:id/message- method -isErrorMessageContainsText
    MobileElement errorTextView;

    public AuthenticationScreen fillEmail(String email){
        waitElement(editTextEmail,5);
        type(editTextEmail,email);
        return this;
    }
    public AuthenticationScreen fillPassword(String password){
               type(editTextPassword,password);
        return this;
    }

    public ContactListScreen submitLogin(){
        loginButton.click();
        return new ContactListScreen(driver);
    }
    public ContactListScreen login(Auth auth){
        waitElement(editTextEmail, 5);
        type(editTextEmail, auth.getEmail());
        type(editTextPassword, auth.getPassword());
        loginButton.click();
        return new ContactListScreen(driver);
    }

    public ContactListScreen submitRegistration(){
        registrationButton.click();
        return new ContactListScreen(driver);
    }
    public ContactListScreen registrationModel(Auth auth){ //cw_23 public ContactListScreen registration(Auth auth)
        waitElement(editTextEmail, 5);
        type(editTextEmail, auth.getEmail());
        type(editTextPassword, auth.getPassword());
        registrationButton.click();
        return new ContactListScreen(driver);
    }
    public AuthenticationScreen submitRegistrationNegative(){//cw-24 hw23
        registrationButton.click();
        return this;
    }
    public AuthenticationScreen registrationNegative(Auth auth){//cw-24 hw23
        waitElement(editTextEmail, 5);
        type(editTextEmail, auth.getEmail());
        type(editTextPassword, auth.getPassword());
        submitRegistrationNegative();
        return this;
    }
    public AuthenticationScreen isErrorMessageContainsText(String text){//cw-24 hw23
        Assert.assertTrue(errorTextView.getText().contains(text));
        return this;
    }

    public AuthenticationScreen isErrorMessageContainsTextInAlert(String text){//cw-23 hw-22
        Alert alert = new WebDriverWait(driver,1)
                .until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert();
        Assert.assertTrue(alert.getText().contains(text));
        alert.accept();
        return this;
    }

}
//    @FindBy(id= "com.sheygam.contactapp:id/loginBtn")
//    MobileElement loginButton; // вариант xpath --> id

//    @FindBy(xpath = "//xpath = /hierarchy/android.widget.FrameLayout") //hierarchy/android.widget.FrameLayout
//    MobileElement WindowErrorView;
//
//    @FindBy(xpath = "//*[@resource-id = 'android:id/alertTitle']") //resource-id android:id/alertTitle (Error)
//    MobileElement ErrorView;
//
//@FindBy(xpath = "//*[@resource-id = 'android:id/message']")  //resource-id	android:id/message (text)
//        MobileElement errorTextView;


//com.sheygam.contactapp:id/inputEmail
//com.sheygam.contactapp:id/inputPassword

//com.sheygam.contactapp:id/regBtn
//com.sheygam.contactapp:id/loginBtn
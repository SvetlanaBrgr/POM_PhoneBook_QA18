package tests;

import config.AppiumConfig;
import models.Auth;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import screens.AuthenticationScreen;
import screens.ContactListScreen;
import screens.SplashScreen;

import java.util.Random;

public class RegistrationTest extends AppiumConfig {

    @Test
    public void registrationSuccess(){
        int i = new Random().nextInt(1000) + 1000;
        new AuthenticationScreen(driver)
                .fillEmail("emulator" + "_" + i + "@mail.ru")
                .fillPassword("$Asdf1234")
                .submitRegistration()
                .assertContactListActivityPresent();
    }
    @Test
    public void RegistrationSuccessModel(){
        int i = new Random().nextInt(1000) + 1000;
        new AuthenticationScreen(driver)
                .registrationModel(Auth.builder()//class- AuthenticationScreen
                        .email("model" + "_" + i + "@mail.ru")
                        .password("Qwer1234$")
                        .build())
                .assertContactListActivityPresent();
    }
    @Test
    public void registrationWrongEmail(){
        new AuthenticationScreen(driver)
                .fillEmail("negative" + "mail.ru")
                .fillPassword("Qwer1234$")
                .submitRegistrationNegative()//method from class AuthenticationScreen cw-23
                .isErrorMessageContainsText("must be a well-formed email address");// text from Appium
    }
    @Test
    public void registrationWrongPassword(){
  new AuthenticationScreen(driver)
                .registrationNegative(Auth.builder()//method from class- AuthenticationScreen cw-23
                        .email("model@mail.ru")
                        .password("Qwer1234")
                        .build())
//                .isErrorMessageContainsText("password=");
                .isErrorMessageContainsTextInAlert("password=");//method from class- AuthenticationScreen cw-23
    }


    @AfterMethod
    public void postCondition(){ // + method - isDisplayedWithException(MobileElement element) in BaseScreen
        // + if in logout() ContactListScreen

        new ContactListScreen(driver).logout();
        new SplashScreen(driver);
    }

}

package tests;

import config.AppiumConfig;
import models.Auth;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import screens.ContactListScreen;
import screens.SplashScreen;

public class LoginTest extends AppiumConfig {
    @Test
    public void loginSuccess(){
        boolean res = new SplashScreen(driver)
                .gotoAuthenticationScreen()
                .fillEmail ("test+1@mail.ru")
                .fillPassword("Qwer1234$")
                .submitLogin()
                        .isContactListActivityPresent();

        Assert.assertTrue(res);
    }
    @Test
    public void loginSuccessModel(){
        boolean res = new SplashScreen(driver)
                .gotoAuthenticationScreen()
                .login(Auth.builder()
                        .email("test+1@mail.ru")
                        .password("Qwer1234$")
                        .build())
                .isContactListActivityPresent();
        Assert.assertTrue(res);
    }

    @AfterMethod
    public void postCondition(){
        new ContactListScreen(driver).logout();
        new SplashScreen(driver);
    }

}

package tests;

import config.AppiumConfig;
import models.Auth;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import screens.AuthenticationScreen;
import screens.ContactListScreen;

public class RemoveContactsTests extends AppiumConfig {
    @BeforeClass
    public void precondition(){
        new AuthenticationScreen(driver)
                .login(Auth.builder()
                        .email("test+1@mail.ru")
                        .password("Qwer1234$")
                        .build());
    }

    @Test
    public void removeOneContactPositive(){
        new ContactListScreen(driver).removeOneContact();// class ContactListScreen
    }

    @AfterClass
    public void postCondition(){
//        new ContactListScreen(driver).logout();
    }
}

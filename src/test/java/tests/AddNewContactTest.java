package tests;

import config.AppiumConfig;
import models.Auth;
import models.Contact;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import screens.AuthenticationScreen;
import screens.ContactListScreen;

import java.util.Random;

public class AddNewContactTest extends AppiumConfig {

    @BeforeClass//cw-25
    public void preCondition(){
        new AuthenticationScreen(driver)
                .login(Auth.builder()
                        .email("test+1@mail.ru")
                        .password("Qwer1234$")
                        .build());
    }

    @Test
    public void addNewContactPositive(){
        int i = new Random().nextInt(1000) + 1000;//random
        Contact contact = Contact.builder()
                .name("Add_" + i)
                .lastName("Positive")
                .email("add_" + i + "@mail.com")
                .phone("123456" + i)
                .address("Haifa")
                .description("New Contact_" + i)
                .build();
        new ContactListScreen(driver)
                .openContactForm()//method from class ContactListScreen
                .fillContactForm(contact)
                .submitContactForm();
//                .isContactAdded(contact);//cw-25 ???? red
    }


    @Test
    public void addNewContactNegativeEmptyName() {//method in AddNewContactScreen
        int i = new Random().nextInt(1000) + 1000;
        Contact contact = Contact.builder()
//                .name(""Add_" + i")// field name empty --> delete .name(""Add_" + i")
                .lastName("Negative")
                .email("emptyName" + i + "@mail.com")
                .phone("123456" + i)
                .address("Haifa")
                .description("New Contact_" + i)
                .build();
        new ContactListScreen(driver)
                .openContactForm()//method in ContactListScree
                .fillContactForm(contact)
                .submitContactFormNegative()//method in AddNewContactScreen
                .isErrorContainsText("name=must not be blank")//method in AddNewContactScreen
        ;
    }
    @Test
    public void addNewContactNegativeEmptyPhone() {//cw-25
        int i = new Random().nextInt(1000) + 1000;
        Contact contact = Contact.builder()
                .name("Add_" + i)
                .lastName("Negative")
                .email("emptyPhone" + i + "@mail.com")
//                .phone("")// field phone empty --> delete .phone("123456" + i)
                .address("Haifa")
                .description("New Contact_" + i)
                .build();
        new ContactListScreen(driver)
                .openContactForm()//method in ContactListScree
                .fillContactForm(contact)
                .submitContactFormNegative()//method in AddNewContactScreen
                .isErrorContainsText("phone=Phone number must")//cw-25 method in class ContactListScreen
        ;
    }

    @AfterClass//cw-25
    public void postCondition(){
        new ContactListScreen(driver).logout();
    }

}

package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import models.Contact;
import org.openqa.selenium.Alert;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class AddNewContactScreen extends BaseScreen {

    public AddNewContactScreen(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    @FindBy(id = "com.sheygam.contactapp:id/inputName")
    MobileElement nameEditText;
    @FindBy(id = "com.sheygam.contactapp:id/inputLastName")
    MobileElement lastNameEditText;
    @FindBy(id = "com.sheygam.contactapp:id/inputEmail")
    MobileElement emailEditText;
    @FindBy(id = "com.sheygam.contactapp:id/inputPhone")
    MobileElement phoneEditText;
    @FindBy(id = "com.sheygam.contactapp:id/inputAddress")
    MobileElement addressEditText;
    @FindBy(id = "com.sheygam.contactapp:id/inputDesc")
    MobileElement descriptionEditText;
    @FindBy(id = "com.sheygam.contactapp:id/createBtn")
    MobileElement createButton;

    public AddNewContactScreen fillContactForm(Contact contact) {
        waitElement(nameEditText, 3);//найти элемент
        type(nameEditText, contact.getName()); //заполнить поле
        type(lastNameEditText, contact.getLastName());
        type(emailEditText, contact.getEmail());
        type(phoneEditText, contact.getPhone());
        type(addressEditText, contact.getAddress());
        type(descriptionEditText, contact.getDescription());
        return this;
    }

    public ContactListScreen submitContactForm() {
        createButton.click();
        return new ContactListScreen(driver);
    }

    public AddNewContactScreen submitContactFormNegative() {//cw-25 if field -Name empty
        createButton.click();
        return this;
    }

    public AddNewContactScreen isErrorContainsText(String text) {//cw-25 window Error name=must
        Alert alert = new WebDriverWait(driver, 5)
                .until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert();
        Assert.assertTrue(alert.getText().contains(text));
        alert.accept();
        return this;
    }
}

//    @FindBy(xpath = "//*[@resource-id= 'com.sheygam.contactapp:id/add_contact_btn']")
//    MobileElement addContactplus;
//
//    @FindBy(xpath = "//*[@resource-id= 'com.sheygam.contactapp:id/createBtn']")
//    MobileElement createContact;

//    public String getAddContactBtn(){
//        return addContactplus.getText();
//    }
//    public String getCreateContactBtn(){
//        return createContact.getText();
//    }


//com.sheygam.contactapp:id/add_contact_btn
//com.sheygam.contactapp:id/createBtn

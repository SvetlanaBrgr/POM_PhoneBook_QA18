package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.support.FindBy;

public class AddNewContactScreen extends BaseScreen {
    @FindBy(xpath = "//*[@resource-id= 'com.sheygam.contactapp:id/add_contact_btn']")
    MobileElement addContactplusView;

    @FindBy(xpath = "//*[@resource-id= 'com.sheygam.contactapp:id/createBtn']")
    MobileElement createContactView;
    public AddNewContactScreen(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    public String getAddContactBtn(){
        return addContactplusView.getText();
    }
    public String getCreateContactBtn(){
        return createContactView.getText();
    }
}

//com.sheygam.contactapp:id/add_contact_btn
//com.sheygam.contactapp:id/createBtn

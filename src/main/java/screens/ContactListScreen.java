package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import models.Contact;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.List;


public class ContactListScreen extends BaseScreen{
    public ContactListScreen(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[@resource-id = 'com.sheygam.contactapp:id/action_bar']/android.widget.TextView")
    //com.sheygam.contactapp:id/action_bar// android.widget.TextView
    MobileElement activityViewText;


    @FindBy(xpath = "//*[@content-desc = 'More options']")
    MobileElement moreOptions;

    @FindBy(xpath = "//*[@resource-id = 'com.sheygam.contactapp:id/add_contact_btn']")
    //com.sheygam.contactapp:id/add_contact_btn
    MobileElement plusButton;

    @FindBy(xpath = "//*[@resource-id = 'com.sheygam.contactapp:id/title']") //com.sheygam.contactapp:id/title
    MobileElement logoutButton;

    @FindBy(id = "com.sheygam.contactapp:id/rowContainer")
    List<MobileElement> contacts;
    @FindBy(id = "com.sheygam.contactapp:id/rowName")
    List<MobileElement> nameList;
    @FindBy(id = "com.sheygam.contactapp:id/rowPhone")
    List<MobileElement> phoneList;

    @FindBy(id = "android:id/button1")//locator - button yes
    MobileElement yesButton;

    @FindBy(id = "android:id/button2")
    MobileElement cancelButton;

    public boolean isContactListActivityPresent(){
        return shouldHave(activityViewText, "Contact list", 10);
    }

    public AuthenticationScreen logout(){
        if(isDisplayedWithException(moreOptions)){//cw-23 + if and method isDisplayedWithException(moreOptions)
        moreOptions.click();
        logoutButton.click();
        }
        return new AuthenticationScreen(driver);
    }

    public ContactListScreen assertContactListActivityPresent(){
        Assert.assertTrue(isContactListActivityPresent());
        return this;
    }
    public AddNewContactScreen openContactForm() { //class AddNewContactTest
//        waitElement(plusButton, 5);
        if(isDisplayedWithException(plusButton))
            plusButton.click();
        return new AddNewContactScreen(driver);
    }

    public ContactListScreen removeOneContact(){//method delete 1 contact by coordinates
        waitElement(plusButton, 3);
        MobileElement contact = contacts.get(0);
        Rectangle rect = contact.getRect();

        int xStart = rect.getX() + rect.getWidth() / 8;//coordinates
        int xEnd = xStart + (rect.getWidth() * 6) / 8;
        int y = rect.getY() + rect.getHeight() / 2;

        TouchAction<?> touchAction = new TouchAction<>(driver);
        touchAction.longPress(PointOption.point(xStart, y))
                .moveTo(PointOption.point(xEnd, y))
                .release()
                .perform();

        waitElement(yesButton, 5);
        yesButton.click();
        return this;
    }
    public EditContactScreen updateOneContact(){
        waitElement(plusButton, 3);
        MobileElement contact = contacts.get(0);
        Rectangle rect = contact.getRect();

        int xEnd = rect.getX() + rect.getWidth() / 8;
        int xStart = xEnd + (rect.getWidth() * 6) / 8;
        int y = rect.getY() + rect.getHeight() / 2;

        TouchAction<?> touchAction = new TouchAction<>(driver);
        touchAction.longPress(PointOption.point(xStart, y))
                .moveTo(PointOption.point(xEnd, y))
                .release()
                .perform();

        return new EditContactScreen(driver);
    }

    public ContactListScreen isContactAdded(Contact contact){
        boolean checkName = checkContainsText(nameList, contact.getName() + " "
                + contact.getLastName());
        boolean checkPhone = checkContainsText(phoneList, contact.getPhone());
        Assert.assertTrue(checkName && checkPhone);
        return this;
    }

    public boolean checkContainsText(List<MobileElement> list, String text){
        for (MobileElement e : list){
            if (e.getText().contains(text)) {
                return true;
            }
        }
        return false;
    }

}

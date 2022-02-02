package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {

    private SelenideElement loginField = $("[data-test-id=login] input");
    private SelenideElement passwordField = $("[data-test-id=password] input");
    private SelenideElement loginButton = $("[data-test-id=action-login]");
    private SelenideElement errorNotification = $("[data-test-id='error-notification']");

    public VerificationPage authValid(DataHelper.AuthInfo info) {
        completedEntry(info);
        return new VerificationPage();
    }

    public void authInvalid() {
        errorNotification.shouldBe(Condition.visible);
    }

    public void completedEntry(DataHelper.AuthInfo info) {
        loginField.setValue(info.getLogin());
        passwordField.setValue(info.getPassword());
        loginButton.click();
    }

    public void authInvalidIfInvalidPassword(String password) {
        passwordField.setValue(password);
        loginButton.click();
    }

    public void clearPasswordField() {
        passwordField.doubleClick().sendKeys(Keys.BACK_SPACE);
    }

    public void loginButtonShouldBeInactive() {
        loginButton.shouldBe(Condition.disabled);
    }
}
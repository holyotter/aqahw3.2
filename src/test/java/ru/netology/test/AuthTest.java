package ru.netology.test;

import lombok.val;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static ru.netology.data.DataHelper.*;
import static ru.netology.data.SQLData.dropDataBase;
import static ru.netology.data.SQLData.getVerificationCode;

public class AuthTest {

    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
    }

    @AfterAll
    public static void clearDataBase() {
        dropDataBase();
    }

    @Test
    void shouldSuccessAuth() {
        val loginPage = new LoginPage();
        val authInfo = getAuthInfo();
        val verificationPage = loginPage.authValid(authInfo);
        val verificationCode = getVerificationCode(authInfo);
        val dashboardPage = verificationPage.verificationValid(verificationCode);
        dashboardPage.dashboardPageIsVisible();
    }

    @Test
    void shouldNotEnterIfInvalidLogin() {
        val loginPage = new LoginPage();
        val authInfo = getInvalidAuthIfInvalidLogin();
        loginPage.completedEntry(authInfo);
        loginPage.authInvalid();
    }

    @Test
    void shouldNotEnterIfInvalidPassword() {
        val loginPage = new LoginPage();
        val authInfo = getInvalidAuthIfInvalidPassword();
        loginPage.completedEntry(authInfo);
        loginPage.authInvalid();
    }

    @Test
    void shouldNotLoginIfInvalidCode() {
        val loginPage = new LoginPage();
        val authInfo = getAuthInfo();
        val verificationPage = loginPage.authValid(authInfo);
        val verificationCode = getInvalidVerificationCode();
        verificationPage.verificationValid(verificationCode);
        verificationPage.verificationInvalid();
    }

    @Test
    void shouldBlockedAfterThirdAttemptWithInvalidPassword() {
        val loginPage = new LoginPage();
        val authInfo = getInvalidAuthIfInvalidPassword();
        loginPage.completedEntry(authInfo);
        loginPage.authInvalid();
        loginPage.clearPasswordField();
        loginPage.authInvalidIfInvalidPassword(authInfo.getPassword());
        loginPage.clearPasswordField();
        loginPage.authInvalidIfInvalidPassword(authInfo.getPassword());
        loginPage.loginButtonShouldBeInactive();
    }
}

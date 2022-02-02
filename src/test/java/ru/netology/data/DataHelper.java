package ru.netology.data;

import com.github.javafaker.Faker;
import lombok.Value;

public class DataHelper {
    private DataHelper() {
    }

    @Value
    public static class AuthInfo {
        String login;
        String password;
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    public static AuthInfo getInvalidAuthIfInvalidLogin() {
        Faker faker = new Faker();
        return new AuthInfo(faker.name().firstName(), "qwerty123");
    }

    public static AuthInfo getInvalidAuthIfInvalidPassword() {
        Faker faker = new Faker();
        return new AuthInfo("vasya", faker.internet().password());
    }

    @Value
    public static class VerificationCode {
        String code;
    }

    public static String getInvalidVerificationCode() {
        return ("1543679865432");
    }
}

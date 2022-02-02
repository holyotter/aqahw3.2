package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class DashboardPage {

    private SelenideElement head = $("[data-test-id=dashboard]");

    public void dashboardPageIsVisible() {
        head.shouldBe(Condition.visible);
    }
}

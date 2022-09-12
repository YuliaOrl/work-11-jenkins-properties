package tests.demoqa;

import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class RegistrationFormTests extends TestBase_2 {

    @Test
    void fillFormTest () {
        step("Open registration form", () -> {
            open("/automation-practice-form");
            $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));
            executeJavaScript("$('footer').remove()");
            executeJavaScript("$('#fixedban').remove()");
        });

        step("Fill form", () -> {
            $("#firstName").setValue("Olga");
            $("#lastName").setValue("Alexandrova");
            $("#userEmail").setValue("Olga@ya.ru");
            $("#genterWrapper").$(byText("Female")).click();
            $("#userNumber").setValue("8916111111");
            $("#dateOfBirthInput").click();
            $(".react-datepicker__month-select").selectOption("June");
            $(".react-datepicker__year-select").selectOption("2008");
            $(".react-datepicker__day--030:not(.react-datepicker__day-outside-month)").click();
            $("#subjectsInput").setValue("History").pressEnter();
            $("#hobbiesWrapper").$(byText("Sports")).click();
            $(byText("Reading")).click();
            $(byText("Music")).click();
            $("#uploadPicture").uploadFile(new File("src/test/resources/file.txt"));
            $("#currentAddress").setValue("Moscow");
            $("#react-select-3-input").setValue("NCR").pressEnter();
            $("#react-select-4-input").setValue("Delhi").pressEnter();
            $("#submit").scrollTo().pressEnter();
        });

        step("Check form results", () -> {
            $(".modal-dialog").should(appear);
            $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
            $("[class=table-responsive]").shouldHave(text("Olga Alexandrova"),
                    text("Olga@ya.ru"),
                    text("Female"),
                    text("8916111111"),
                    text("30 June,2008"),
                    text("History"),
                    text("Sports, Reading, Music"),
                    text("file.txt"), text("Moscow"),
                    text("NCR Delhi"));
        });
    }
}

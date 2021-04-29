package ee.fizzdev.application;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeOptions;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.assertj.core.api.Assertions.assertThat;

public class ITUITest {

    public static final String TEST_NAME = "Konstantin Konstantinopolsky";

    @BeforeAll
    static void setup(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments(
                "--verbose",
                "--no-sandbox",
                "--headless",
                "--disable-dev-shm-usage");
        Configuration.browserCapabilities.setCapability(ChromeOptions.CAPABILITY,
                options);
    }

    @Test
    void testHello() throws InterruptedException {


        open("http://localhost:8080/");
        $("#name_input").sendKeys(TEST_NAME);
        $("#addname_btn").click();
        SelenideElement note = $("#names");
        assertThat(note.getText()).contains(TEST_NAME);


    }
}

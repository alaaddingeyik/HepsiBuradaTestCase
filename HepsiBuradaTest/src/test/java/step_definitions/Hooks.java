package step_definitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import utilities.Driver;

public class Hooks {


    @Before
    public void setup() {
        System.out.println("Test hazırlığı: Tarayıcı penceresi maksimum boyuta getiriliyor.");
        Driver.getDriver().manage().window().maximize();
    }

    /**
     * Bu metot, her test senaryosundan sonra çalışır.
     * Tarayıcıyı kapatarak temizleme işlemi yapar. Eğer bir senaryo başarısız olursa, ekran görüntüsü alır.
     *
     * @param scenario biten senaryoyu temsil eder
     */
    @After
    public void tearDown(Scenario scenario) {
        System.out.println("Senaryo tamamlandı.");

        if (scenario.isFailed()) {
            // Başarısız olan senaryo için ekran görüntüsü al
            final byte[] screenshot = ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getName());
            System.out.println("Başarısız olan senaryo için ekran görüntüsü alındı: " + scenario.getName());
        }

        Driver.closeDriver();
        System.out.println("Tarayıcı kapatıldı.");
    }
}

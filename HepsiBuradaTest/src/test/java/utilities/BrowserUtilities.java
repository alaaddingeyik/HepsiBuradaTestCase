package utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BrowserUtilities {

    /**
     * Belirtilen süre kadar bekler.
     *
     * @param seconds Bekleme süresi (saniye)
     */
    public static void wait(int seconds) {
        try {
            Thread.sleep(1000 * seconds);
        } catch (InterruptedException e) {

            e.printStackTrace();
        }
    }

    /**
     * Sayfanın tamamen yüklenmesini bekler.
     *
     * @param timeOutInSeconds Maksimum bekleme süresi (saniye)
     */
    public static void waitForPageToLoad(long timeOutInSeconds) {
        WebDriver driver = Driver.getDriver();
        ExpectedCondition<Boolean> expectation =
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete");

        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOutInSeconds));
            wait.until(expectation);
        } catch (Throwable error) {

            error.printStackTrace();
        }
    }
    /**
     * Çerez bildirimini kapatır.
     *
     * @param driver WebDriver nesnesi
     */
    public static void acceptCookies(WebDriver driver) {
        try {

            WebElement acceptCookiesButton = driver.findElement(By.xpath("//button[@id='onetrust-accept-btn-handler']"));
            if (acceptCookiesButton.isDisplayed()) {
                acceptCookiesButton.click();
            }
        } catch (Exception e) {
            System.out.println("Cookies notification not found or already accepted.");
        }
    }

}

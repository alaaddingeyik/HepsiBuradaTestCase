package utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.logging.Logger;

public class Driver {
    private static ThreadLocal<WebDriver> driverPool = new ThreadLocal<>();
    final static Logger logger = Logger.getLogger(Driver.class.getName());

    // Özel yapıcı metod, dışarıdan örneklenmesini engeller
    private Driver() {
    }

    /**
     * WebDriver örneğini döner. Varsayılan tarayıcı konfigürasyonunu kullanır.
     *
     * @return WebDriver örneği
     */
    public synchronized static WebDriver getDriver() {
        if (driverPool.get() == null) {
            String browser = ConfigurationReader.getProperty("browser").toLowerCase();
            switch (browser) {
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.addArguments("--start-maximized");
                    driverPool.set(new ChromeDriver(chromeOptions));
                    break;
                case "chromeheadless":
                    WebDriverManager.chromedriver().setup();
                    ChromeOptions options = new ChromeOptions();
                    options.setHeadless(true);
                    driverPool.set(new ChromeDriver(options));
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driverPool.set(new FirefoxDriver());
                    break;
                default:
                    throw new RuntimeException("Geçersiz tarayıcı adı!");
            }
        }
        return driverPool.get();
    }

    /**
     * Belirtilen tarayıcı türünde bir WebDriver örneği döner.
     *
     * @param browser Tarayıcı adı ("chrome", "chromeheadless", "firefox")
     * @return WebDriver örneği
     */
    public synchronized static WebDriver getDriver(String browser) {
        if (driverPool.get() == null) {
            switch (browser) {
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.addArguments("--start-maximized");
                    driverPool.set(new ChromeDriver(chromeOptions));
                    break;
                case "chromeheadless":
                    WebDriverManager.chromedriver().setup();
                    ChromeOptions options = new ChromeOptions();
                    options.setHeadless(true);
                    driverPool.set(new ChromeDriver(options));
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driverPool.set(new FirefoxDriver());
                    break;
                default:
                    throw new RuntimeException("Geçersiz tarayıcı adı!");
            }
        }
        return driverPool.get();
    }

    /**
     * WebDriver örneğini kapatır ve havuzdan çıkarır.
     */
    public static void closeDriver() {
        if (driverPool.get() != null) {
            driverPool.get().quit();
            driverPool.remove();
        }
    }
}

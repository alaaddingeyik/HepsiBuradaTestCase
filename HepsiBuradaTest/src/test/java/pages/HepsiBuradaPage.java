package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.BrowserUtilities;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HepsiBuradaPage extends AbstractBasePage {

    @FindBy(xpath = "//span[contains(text(),'Elektronik')]")
    protected WebElement electronic;

    @FindBy(xpath = "//a[normalize-space()='Bilgisayar/Tablet']")
    protected WebElement computerTablet;

    @FindBy(xpath = "//a[@class='sf-ChildMenuItems-KdzkrxSVhcwDy3od0Yre item-1855']")
    protected WebElement tablet;

    @FindBy(css = "a[title='Apple Tablet']")
    protected WebElement filterApple;

    @FindBy(xpath = "//span[normalize-space()='13,2 inç']")
    protected WebElement size;

    @FindBy(xpath = "//div[contains(@class,'ie_Lkl_Fke_47s20d78x')]")
    protected List<WebElement> priceElements;

    @FindBy(xpath = "//li[contains(@class,'productListContent')]//a")
    protected List<WebElement> productElements;

    @FindBy(xpath = "//button[contains(text(),'Sepete Ekle')]")
    protected WebElement addToCartButton;

    private WebElement highestPricedProduct;

    public void closePopup() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        try {
            boolean popUpVisible = true;
            while (popUpVisible) {
                popUpVisible = false;

                // **Kabul Et (Çerez) Pop-up'ı**
                if (driver.findElements(By.xpath("//button[contains(text(),'Kabul et')]")).size() > 0) {
                    WebElement acceptButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Kabul et')]")));
                    acceptButton.click();
                    BrowserUtilities.wait(2);
                    popUpVisible = true;
                    System.out.println("✅ Çerez pop-up'ı kapatıldı.");
                }

                // **Consent Pop-up'ı (Yeni Locator)**
                if (driver.findElements(By.xpath("//button[contains(@class,'fc-button fc-cta-consent fc-primary-button')]")).size() > 0) {
                    WebElement consentPopup = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(@class,'fc-button fc-cta-consent fc-primary-button')]")));
                    consentPopup.click();
                    BrowserUtilities.wait(2);
                    popUpVisible = true;
                    System.out.println("✅ Yeni Consent pop-up'ı kapatıldı.");
                }
            }

        } catch (Exception e) {
            System.out.println("🚀 Pop-up görünmüyor veya zaten kapatılmış.");
        }
    }


    public void hoverOverElectronicCategory() {
        Actions actions = new Actions(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        // **Elektronik menüsüne gelmeden önce pop-up kontrolü yap**
        closePopup();

        wait.until(ExpectedConditions.visibilityOf(electronic));
        actions.moveToElement(electronic).perform();
        BrowserUtilities.wait(2);

        // **Elektronik menüsüne geldikten sonra tekrar "Consent" çıkarsa kapat**
        closePopup();
    }


    public void hoverOverComputerTabletCategory() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Actions actions = new Actions(driver);

        int retryCount = 0;
        boolean success = false;

        while (retryCount < 3 && !success) {
            try {
                WebElement computerTabletElement = wait.until(ExpectedConditions.presenceOfElementLocated(
                        By.xpath("//a[normalize-space()='Bilgisayar/Tablet']")
                ));

                actions.moveToElement(computerTabletElement).perform();
                BrowserUtilities.wait(2);
                success = true;

            } catch (StaleElementReferenceException e) {
                retryCount++;
                BrowserUtilities.wait(2);
            }
        }

        if (!success) {
            throw new RuntimeException("❌ 3 denemeden sonra 'Bilgisayar/Tablet' elementi bulunamadı!");
        }
    }

    public void clickOnTabletCategory() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(tablet)).click();
    }

    public void selectAppleTabletFilter() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(filterApple)).click();
    }

    public void selectScreenSizeFilter() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(size)).click();
    }

    public void selectHighestPricedTablet() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        BrowserUtilities.wait(3);

        List<Double> prices = new ArrayList<>();

        for (WebElement priceElement : priceElements) {
            String priceText = priceElement.getText()
                    .replaceAll("[^0-9,]", "")  // Sadece rakam ve virgülü koru
                    .replace(",", ".");        // Virgülü noktaya çevir

            // **Eğer birden fazla nokta varsa, ilk nokta hariç tümünü kaldır**
            int firstDotIndex = priceText.indexOf(".");
            if (firstDotIndex != -1) {
                priceText = priceText.substring(0, firstDotIndex + 1) + priceText.substring(firstDotIndex + 1).replace(".", "");
            }

            if (!priceText.isEmpty()) {
                try {
                    double price = Double.parseDouble(priceText);
                    prices.add(price);
                } catch (NumberFormatException e) {
                    System.out.println("❌ Geçersiz fiyat formatı atlandı: " + priceText);
                }
            }
        }

        if (prices.isEmpty()) {
            System.out.println("❌ Hiçbir fiyat bulunamadı!");
            return;
        }

        double maxPrice = Collections.max(prices);
        int index = prices.indexOf(maxPrice);

        highestPricedProduct = productElements.get(index);
        System.out.println("✅ En yüksek fiyatlı tablet belirlendi: " + maxPrice + " TL");
    }


    public void clickHighestPricedTablet() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        if (highestPricedProduct != null) {
            wait.until(ExpectedConditions.elementToBeClickable(highestPricedProduct)).click();
            wait.until(ExpectedConditions.elementToBeClickable(addToCartButton)).click();
        }
    }

    public void addToCart() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(addToCartButton)).click();
    }
    public void verifyCartPrice() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement cartPriceElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[contains(@class,'basket_total_price')]") // **Sepetteki fiyat alanı**
        ));

        String cartPriceText = cartPriceElement.getText().replaceAll("[^0-9,]", "").replace(",", ".");
        double cartPrice = Double.parseDouble(cartPriceText);

        System.out.println("✅ Sepetteki fiyat doğrulandı: " + cartPrice + " TL");
    }

}

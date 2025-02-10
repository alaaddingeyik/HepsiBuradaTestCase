package step_definitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.HepsiBuradaPage;
import utilities.BrowserUtilities;
import utilities.Driver;

public class HepsiBuradaSteps {

    HepsiBuradaPage hepsiBuradaPage = new HepsiBuradaPage();

    @Given("Kullanici HepsiBurada sitesine gider")
    public void kullanici_hepsi_burada_sitesine_gider() {
        BrowserUtilities.waitForPageToLoad(10);
        Driver.getDriver().get("https://www.hepsiburada.com/");
        BrowserUtilities.waitForPageToLoad(5);
        hepsiBuradaPage.closePopup();
    }

    @When("Kullanici elektronik alanindan Tablet secenegine tiklar")
    public void kullanici_elektronik_alanindan_tablet_secenegine_tiklar() {
        hepsiBuradaPage.hoverOverElectronicCategory();
        hepsiBuradaPage.hoverOverComputerTabletCategory();
        hepsiBuradaPage.clickOnTabletCategory();
    }

    @When("Kullanici Filtreleme alanindan Marka olarak Apple secer")
    public void kullanici_filtreleme_alanindan_marka_olarak_apple_secer() {
        hepsiBuradaPage.selectAppleTabletFilter();
    }

    @When("Kullanici Ekran boyutunu {string} inches secer")
    public void kullanici_ekran_boyutunu_inches_secer(String screenSize) {
        hepsiBuradaPage.selectScreenSizeFilter();
    }

    @Then("Kullanici Filtreleme yapmadan cikan sonuclardan en yuksek fiyatli olan tableti secer")
    public void kullanici_filtreleme_yapmadan_cikan_sonuclardan_en_yuksek_fiyatli_olan_tableti_secer() {
        hepsiBuradaPage.selectHighestPricedTablet();
    }

    @And("Kullanici urun detayi sayfasindan sepete ekle butonuna tiklar")
    public void kullanici_urun_detayi_sayfasindan_sepete_ekle_butonuna_tiklar() {
        hepsiBuradaPage.clickHighestPricedTablet();
        hepsiBuradaPage.addToCart();
    }

    @Then("Kullanici urunun sepete eklendigini ve fiyatin urun detay sayfasiyla ayni fiyat oldugunu dogrular")
    public void kullanici_urunun_sepete_eklendigini_ve_fiyatin_urun_detay_sayfasiyla_ayni_fiyat_oldugunu_dogrular() {
        hepsiBuradaPage.verifyCartPrice(); // **✅ Sepet doğrulama işlemi burada yapılıyor**
    }
}

Feature: HepsiBurada E-Ticaret sitesinden Tablet Siparisi Olusturma Islemi
@Hepsi
  Scenario:E-Ticaret sitesinden istenilen tablet urununun sepete eklendigi kontrol edilir
    Given Kullanici HepsiBurada sitesine gider
    When  Kullanici elektronik alanindan Tablet secenegine tiklar
    And  Kullanici Filtreleme alanindan Marka olarak Apple secer
    And Kullanici Ekran boyutunu "13,2" inches secer
    Then Kullanici Filtreleme yapmadan cikan sonuclardan en yuksek fiyatli olan tableti secer
    And Kullanici urun detayi sayfasindan sepete ekle butonuna tiklar
    Then Kullanici urunun sepete eklendigini ve fiyatin urun detay sayfasiyla ayni fiyat oldugunu dogrular

# 🛒 HepsiBuradaTest - E-Ticaret Otomasyon Projesi  

Bu proje, **HepsiBurada** e-ticaret sitesinde belirli bir **tablet ürününün sipariş sürecini test eden** bir **Selenium Cucumber otomasyon framework'üdür**. Proje, **JUnit, WebDriverManager, Cucumber BDD ve Maven** kullanarak tasarlanmıştır.  

---

## 📌 **Proje Amacı**  
Bu proje ile:  
✔ Kullanıcıların **filtreleme yaparak ürün seçme** sürecini test etmek  
✔ **En yüksek fiyatlı ürünü** tespit edip sepete ekleme işlemini doğrulamak  
✔ **Sepetteki fiyat doğrulamasını** yapmak  

Tüm testler **Cucumber BDD** formatında yazılmıştır.  

## 🛠 **Kullanılan Teknolojiler & Araçlar**  
| Teknoloji / Kütüphane | Açıklama |
|----------------------|-------------|
| **Java**            | Test otomasyon dili |
| **Selenium**        | Web otomasyon aracı |
| **Cucumber BDD**    | Davranış Odaklı Test Çerçevesi |
| **JUnit**           | Test çalıştırıcı framework |
| **WebDriverManager**| Tarayıcı yönetimi |
| **Maven**           | Bağımlılık yönetimi |
| **Extent Reports**  | Test raporlama aracı |


## 📂 **Dizin Yapısı**  

HepsiBuradaTest
│── src
│   ├── main
│   │   ├── java
│   │   │   ├── utilities/      # Yardımcı metotlar
│   ├── test
│   │   ├── java
│   │   │   ├── pages/          # Page Object Model (POM) sınıfları
│   │   │   ├── step_definitions/  # Cucumber Step Definitions
│   │   │   ├── runners/        # Test çalıştırıcılar
│   │   │   ├── feature/        # Cucumber .feature dosyaları
│   │   ├── resources
│   │   │   ├── configuration.properties  # Tarayıcı ayarları
│── pom.xml   # Maven bağımlılıkları
│── README.md  # Proje dökümantasyonu



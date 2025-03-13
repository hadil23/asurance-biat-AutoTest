package com.example.AssuranceBiat;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.time.Duration;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ContacterConseillersEntrepriseChampsValide {
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
  private WebDriver driver;
  private WebDriverWait wait;
  private String baseUrl = "https://www.assurancesbiat.com.tn/";
  JavascriptExecutor js;

  @Before
  public void setUp() {
    System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\Java\\chromedriver.exe");
    ChromeOptions options = new ChromeOptions();
    options.addArguments("start-maximized", "--remote-allow-origins=*");
    driver = new ChromeDriver(options);
    wait = new WebDriverWait(driver, Duration.ofSeconds(20)); // Attente explicite
    js = (JavascriptExecutor) driver;
  }

  @Test
  public void testContacterConseillersEntrepriseChampsValide() throws Exception {
    driver.get("https://www.assurancesbiat.com.tn/contact");
    try {
      
      // Assurez-vous que l'élément "Contactez nos conseillers" est visible et cliquable
      WebElement contactLink = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Contactez nos conseillers")));
      contactLink.click();

      // Vérification de la légende
      assertTrue("La légende ne correspond pas", driver.findElement(By.xpath("//legend[@id='edit-vous-etes---wrapper-legend']/span")).getText().matches("^Vous êtes[\\s\\S]*$"));

      // Sélectionnez "Entreprise"
      WebElement entrepriseOption = wait.until(ExpectedConditions.elementToBeClickable(By.id("edit-vous-etes-entreprise")));
      entrepriseOption.click();
      assertEquals("Raison sociale", driver.findElement(By.xpath("//form[@id='webform-submission-contacter-nos-conseillers-paragraph-8-add-form']/div/label")).getText());

      // Remplir les champs du formulaire
      remplirChampsFormulaire();
      driver.findElement(By.xpath("//*[@id=\"popup-buttons\"]/button[1]")).click();

      // Vérification du champ "Je souhaite être contacté par"
      WebElement contactEmailOption = wait.until(ExpectedConditions.elementToBeClickable(By.id("edit-je-souhaite-etre-contacte-par-email")));
      WebElement contactTelOption = wait.until(ExpectedConditions.elementToBeClickable(By.id("edit-je-souhaite-etre-contacte-par-tlphone")));
      contactEmailOption.click();
      contactTelOption.click();

      // Manipulation de la date et de l'heure
      remplirDatesEtHeures();

      // Accepter les conditions d'utilisation
      WebElement termsOfServiceCheckbox = wait.until(ExpectedConditions.elementToBeClickable(By.id("edit-terms-of-service-01")));
     
      WebElement termsDescription = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='edit-terms-of-service-01--description']/p")));
      assertEquals("J’ai lu et j’accepte les conditions d’utilisation", termsDescription.getText());

      // Validation du formulaire
      WebElement validantCheckbox = wait.until(ExpectedConditions.elementToBeClickable(By.id("edit-validant")));
      validantCheckbox.click();
      WebElement validationDescription = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='edit-validant--description']/p")));
      assertEquals("En validant votre demande, vous reconnaissez avoir pris connaissance de notre politique de gestion des données personnelles (PPDP) et vous l’acceptez", validationDescription.getText());

      try {
		// Soumettre le formulaire
		  driver.findElement(By.xpath("//*[@id=\"popup-buttons\"]/button[1]")).click();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

      WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("edit-actions-submit")));
      submitButton.click();

    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
  }

  private void remplirChampsFormulaire() {
    // Remplissage des champs du formulaire
    WebElement raisonSociale = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("edit-raison-sociale")));
    raisonSociale.clear();
    raisonSociale.sendKeys("Entrepreneur");

    WebElement nom = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("edit-nom")));
    nom.clear();
    nom.sendKeys("Mahmoud");

    WebElement prenom = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("edit-name")));
    prenom.clear();
    prenom.sendKeys("Jaballah");

    WebElement profession = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("edit-profession")));
    profession.clear();
    profession.sendKeys("Etudiant");

    WebElement email = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("edit-email")));
    email.clear();
    email.sendKeys("mahmoud12@gmail.com");

    WebElement tel = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("edit-tel")));
    tel.clear();
    tel.sendKeys("51478963");

    WebElement fax = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("edit-fax")));
    fax.clear();
    fax.sendKeys("123");

    WebElement adresse = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("edit-adresse")));
    adresse.clear();
    adresse.sendKeys("Tunis");

    WebElement ville = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("edit-ville")));
    ville.clear();
    ville.sendKeys("Ariana");

    WebElement codePostal = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("edit-code-postal")));
    codePostal.clear();
    codePostal.sendKeys("123");

    WebElement subject = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("edit-subject")));
    subject.clear();
    subject.sendKeys("payement");

    WebElement message = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("edit-message")));
    message.clear();
    message.sendKeys("hello");
  }

  private void remplirDatesEtHeures() {
    WebElement deDate = wait.until(ExpectedConditions.elementToBeClickable(By.id("edit-de-date")));
    deDate.clear();
    deDate.sendKeys("2025-03-29");

    WebElement deTime = wait.until(ExpectedConditions.elementToBeClickable(By.id("edit-de-time")));
    deTime.clear();
    deTime.sendKeys("17:30:16");

    WebElement auDate = wait.until(ExpectedConditions.elementToBeClickable(By.id("edit-au-date")));
    auDate.clear();
    auDate.sendKeys("2025-04-20");

    WebElement auTime = wait.until(ExpectedConditions.elementToBeClickable(By.id("edit-au-time")));
    auTime.clear();
    auTime.sendKeys("11:30:35");
    
    //driver.findElement(By.xpath("//*[@id=\"popup-buttons\"]/button[1]")).click();

  }

 

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}

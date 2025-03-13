package com.example.AssuranceBiat;

import java.time.Duration;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.containsString;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ContacterConseillersEntrepriseChampsDonnEErronE {
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
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        js = (JavascriptExecutor) driver;
    }

    @Test
    public void testContacterConseillersEntrepriseChampsDonnEErronE() {
        driver.get(baseUrl + "contact/");

        try {
            // Fermer le pop-up de cookies si présent
            //fermerPopupSiPresent();

            // Accéder au formulaire de contact
            WebElement contactLink = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Contactez nos conseillers")));
            contactLink.click();

            WebElement legend = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("edit-vous-etes---wrapper-legend")));
            assertThat(legend.getText(), containsString("Vous êtes"));

            driver.findElement(By.id("edit-vous-etes-entreprise")).click();

            remplirChamp("edit-raison-sociale", "Paye");
            remplirChamp("edit-nom", "12Had");
            remplirChamp("edit-name", "A14");
            remplirChamp("edit-profession", "Salarié");
            remplirChamp("edit-email", "Had123");
            remplirChamp("edit-tel", "14444");
            remplirChamp("edit-fax", "12");
            remplirChamp("edit-adresse", "Ary@");
            remplirChamp("edit-ville", "@12");
            remplirChamp("edit-code-postal", "12");
            remplirChamp("edit-subject", "14");
            remplirChamp("edit-message", "Helopa");
            //driver.findElement(By.xpath("//*[@id=\"popup-buttons\"]/button[1]")).click();

            WebElement contactLabel = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//legend[@id='edit-je-souhaite-etre-contacte-par---wrapper-legend']/span")));
            assertThat(contactLabel.getText(), containsString("Je souhaite être contacté par"));

            driver.findElement(By.id("edit-je-souhaite-etre-contacte-par-tlphone")).click();

            remplirChamp("edit-de-date", "2025-03-08");
            remplirChamp("edit-de-time", "11:49:50");
            remplirChamp("edit-au-date", "2025-03-08");

            WebElement termsCheckbox = wait.until(ExpectedConditions.elementToBeClickable(By.id("edit-terms-of-service-01")));
            scrollToElement(termsCheckbox);
            // Si vous souhaitez cliquer sur le checkbox des termes, décommentez la ligne suivante :
            termsCheckbox.click();
            
            try {
				driver.findElement(By.xpath("//*[@id=\"popup-buttons\"]/button[1]")).click();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


            WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("edit-actions-submit")));
            scrollToElement(submitButton);
            submitButton.click();

        } catch (Exception e) {
            fail("Erreur rencontrée : " + e.getMessage());
        }
    }

   /* private void fermerPopupSiPresent() {
        try {
            WebElement popup = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("popup-text")));
            if (popup.isDisplayed()) {
                // Si le pop-up est visible, nous le fermons (vous pouvez remplacer ce code si vous voulez le faire autrement)
                WebElement closeButton = popup.findElement(By.cssSelector("button.close"));
                closeButton.click();
            }
        } catch (NoSuchElementException | TimeoutException e) {
            // Pas de pop-up visible, continuer l'exécution
        }
*/
    private void remplirChamp(String id, String valeur) {
        WebElement champ = wait.until(ExpectedConditions.elementToBeClickable(By.id(id)));
        scrollToElement(champ);
        champ.clear();
        champ.sendKeys(valeur);
    }

    private void scrollToElement(WebElement element) {
        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", element);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

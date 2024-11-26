package electronics;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import utilities.Utility;

public class ElectronicTest extends Utility {

    String baseUrl = "https://demowebshop.tricentis.com/";

    @Before
    public void setUp() {
        openBrowser(baseUrl);  // Open the browser and navigate to the URL
    }

    @Test
    public void verifyUserShouldNavigateToCellPhonesPageSuccessfully() {
        // Hover over 'Electronics' and click on 'Cell Phones'
      //  WebElement electronicsTab = driver.findElement(By.xpath("//ul[@class='top-menu notmobile']//a[contains(text(),'Electronics')]"));
      //  hoverOverElement(electronicsTab);
        clickOnElement(By.xpath("//ul[@class='top-menu notmobile']//a[contains(text(),'Cell phones')]"));

        // Verify the text 'Cell phones'
        verifyText(By.xpath("//h1[contains(text(),'Cell phones')]"), "Cell phones");
    }

    @Test
    public void verifyThatTheProductAddedSuccessfullyAndPlaceOrderSuccessfully() {
        // Navigate to Cell Phones page

        clickOnElement(By.xpath("//ul[@class='top-menu notmobile']//a[contains(text(),'Cell phones')]"));

        // Verify the 'Cell phones' text
        verifyText(By.xpath("//h1[contains(text(),'Cell phones')]"), "Cell phones");

        // Select view as 'List'
        selectFromDropdown(By.xpath("//select[@id='products-viewmode']"), "List");

        // Click on 'Smartphone' product link
        clickOnElement(By.linkText("Smartphone"));

        // Verify product title and price
        verifyText(By.xpath("//h1[contains(text(),'Smartphone')]"), "Smartphone");
        verifyPrice(By.xpath("//span[@id='price-value-20']"), "$100.00");

        // Change quantity to 2 and add to cart
        enterText(By.xpath("//input[@class='qty-input']"), "2");
        clickAddToCart();

        // Verify success message after adding to cart
        verifyConfirmationMessage("The product has been added to your shopping cart");

        // Proceed to shopping cart and verify details
        hoverOverElement(driver.findElement(By.xpath("//span[@class='cart-label']")));
        clickOnElement(By.xpath("//span[@class='cart-label']"));

        verifyText(By.xpath("//h1[contains(text(),'Shopping cart')]"), "Shopping cart");
        verifyPrice(By.xpath("//span[@class='product-subtotal']"), "$200.00");

        // Agree to terms and proceed to checkout
        WebElement agreeCheckbox = driver.findElement(By.id("termsofservice"));
        agreeCheckbox.click();
        proceedToCheckout();

        // Verify the sign-in text
        verifyText(By.xpath("//h1[contains(text(),'Welcome, Please Sign In!')]"), "Welcome, Please Sign In!");


    }

    @After
    public void tearDown() {
        closeBrowser();  // Close the browser after the test
    }
}





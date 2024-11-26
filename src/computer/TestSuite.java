package computer;

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

import java.util.List;

public class TestSuite {
    //baseUrl
    String baseUrl ="https://demowebshop.tricentis.com/";

    WebDriver driver = new ChromeDriver();

    @Before
    public void setUp() {

        driver = new ChromeDriver();
        driver.get(baseUrl);
    }
    @Test
    public void verifyProductArrangeInAlphaBaticalOrder(){
        // 1.1 Click on the COMPUTERS Menu
        WebElement computersMenu = driver.findElement(By.linkText("Computers"));
        computersMenu.click();

        // 1.2 Click on the Desktop link
        driver.findElement(By.linkText("Desktops")).click();

        // 1.3 Select Sort By option "Name: Z to A"
        WebElement sortByDropdown = driver.findElement(By.xpath("//select[@id='products-orderby']"));
        Select sortBySelect = new Select(sortByDropdown);
        sortBySelect.selectByVisibleText("Name: Z to A");

        // 1.4 Verify the products are arranged in Descending order
        List<WebElement> productNames = driver.findElements(By.cssSelector(".product-item .product-title"));
        boolean isDescending = true;
        for (int i = 0; i < productNames.size() - 1; i++) {
            String currentProductName = productNames.get(i).getText().toLowerCase();
            String nextProductName = productNames.get(i + 1).getText().toLowerCase();
            if (currentProductName.compareTo(nextProductName) < 0) {
                isDescending = false;
                break;
            }
        }

        // Assert if products are sorted in descending order
        Assert.assertTrue("The products are not sorted in descending order.", isDescending);
    }

@Test
public void verifyProductAddedToShoppingCartSuccessFully(){

    // 2.1 Click on the COMPUTERS Menu
    WebElement computersMenu = driver.findElement(By.linkText("Computers"));
    computersMenu.click();

    // 2.2 Click on the Desktop link
    driver.findElement(By.linkText("Desktops")).click();

    // 2.3 Select Sort By option "Name: A to Z"
    WebElement sortByDropdown = driver.findElement(By.xpath("//select[@id='products-orderby']"));
    Select sortBySelect = new Select(sortByDropdown);
    sortBySelect.selectByVisibleText("Name: A to Z");

    // 2.4 Click on the "Add To Cart" button for 'Build your own computer'
    WebElement addToCartButton = driver.findElement(By.xpath("//div[@class='details']//a[contains(text(),'Build your own computer')]"));
    addToCartButton.click();

    // 2.5 Verify the Text "Build your own computer"
    WebElement productName = driver.findElement(By.xpath("//h1[contains(text(),'Build your own computer')]"));
    Assert.assertTrue("Product name is incorrect", productName.getText().contains("Build your own computer"));

    // 2.6 Select "2.2 GHz Intel Pentium Dual-Core E2200"
    WebElement processorDropdown = driver.findElement(By.id("product_attribute_16_5_4"));
    Select processorSelect = new Select(processorDropdown);
    processorSelect.selectByVisibleText("2.2 GHz Intel Pentium Dual-Core E2200");

    // 2.7 Select "8GB [+$60.00]"
    WebElement ramDropdown = driver.findElement(By.id("product_attribute_16_6_5"));
    Select ramSelect = new Select(ramDropdown);
  //  ramSelect.selectByVisibleText("8GB [+$60.00]");

    // 2.8 Select HDD radio button "400 GB [+$100.00]"
    WebElement hddRadioButton = driver.findElement(By.name("product_attribute_16_3_6"));
    hddRadioButton.click();

    // 2.9 Select the OS radio button "Windows 10 [+$60.00]"
    WebElement osRadioButton = driver.findElement(By.name("product_attribute_16_4_7"));
    osRadioButton.click();

    // 2.10 Check Two Checkboxes "Microsoft Office [+$50.00]" and "Total Commander [+$5.00]"
    WebElement officeCheckbox = driver.findElement(By.name("product_attribute_16_8_8"));
    officeCheckbox.click();

    WebElement commanderCheckbox = driver.findElement(By.name("product_attribute_16_8_8"));
    commanderCheckbox.click();

    // 2.11 Verify the price is "1200.00"
    //WebElement price = driver.findElement(By.xpath("//span[@class='price-value-16']"));
  //  Assert.assertEquals("Price is incorrect", "$1200.00", price.getText());

    // 2.12 Click on the "Add to cart" Button
    WebElement addToCartFinalButton = driver.findElement(By.id("add-to-cart-button-16"));
    addToCartFinalButton.click();

    // 2.13 Verify the Message "The product has been added to your shopping cart"
    WebElement message = driver.findElement(By.xpath("//p[@class='content']"));
    Assert.assertTrue("The product was not added successfully", message.getText().contains("The product has been added to your shopping cart"));

    // 2.14 Close the green notification bar
    WebElement closeButton = driver.findElement(By.xpath("//span[@class='close']"));
    closeButton.click();

    // 2.15 Hover over the "Shopping cart" and click on "Go to cart"
    WebElement shoppingCart = driver.findElement(By.xpath("//span[@class='cart-label']"));
    Actions actions = new Actions(driver);
    actions.moveToElement(shoppingCart).build().perform();
    shoppingCart.click();

    // 2.16 Verify the message "Shopping cart"
    WebElement cartTitle = driver.findElement(By.xpath("//h1[contains(text(),'Shopping cart')]"));
    Assert.assertTrue("Cart title is incorrect", cartTitle.getText().contains("Shopping cart"));

    // 2.17 Change the quantity to "2" and click "Update shopping cart"
    WebElement qtyField = driver.findElement(By.xpath("//input[@class='qty-input']"));
    qtyField.clear();
    qtyField.sendKeys("2");
    driver.findElement(By.name("updatecart")).click();

    // 2.18 Verify the Total is "2,950.00"
    WebElement totalPrice = driver.findElement(By.xpath("//span[@class='product-subtotal']"));
    Assert.assertEquals("Total is incorrect", "$2950.00", totalPrice.getText());

    // 2.19 Click on the checkbox "I agree with the terms of service"
    WebElement agreeCheckbox = driver.findElement(By.id("termsofservice"));
    agreeCheckbox.click();

    // 2.20 Click on "Checkout"
    WebElement checkoutButton = driver.findElement(By.id("checkout"));
    checkoutButton.click();

    // 2.21 Verify the Text "Welcome, Please Sign In!"
    WebElement signInText = driver.findElement(By.xpath("//h1[contains(text(),'Welcome, Please Sign In!')]"));
    Assert.assertTrue("Sign in text is incorrect", signInText.getText().contains("Welcome, Please Sign In!"));

    // 2.22 Click on the "Checkout as Guest" Tab
    WebElement guestCheckoutButton = driver.findElement(By.xpath("//button[@class='button-1 checkout-as-guest-button']"));
    guestCheckoutButton.click();

    // 2.23-2.30 Fill in user details (first name, last name, email, address, phone number)
    driver.findElement(By.id("BillingNewAddress_FirstName")).sendKeys("John");
    driver.findElement(By.id("BillingNewAddress_LastName")).sendKeys("Doe");
    driver.findElement(By.id("BillingNewAddress_Email")).sendKeys("johndoe@example.com");
    new Select(driver.findElement(By.id("BillingNewAddress_CountryId"))).selectByVisibleText("United Kingdom");
    driver.findElement(By.id("BillingNewAddress_City")).sendKeys("London");
    driver.findElement(By.id("BillingNewAddress_Address1")).sendKeys("221B Baker Street");
    driver.findElement(By.id("BillingNewAddress_ZipPostalCode")).sendKeys("E1 6AN");
    driver.findElement(By.id("BillingNewAddress_PhoneNumber")).sendKeys("1234567890");

    // 2.31-2.33 Click on "Continue" and select shipping method
    driver.findElement(By.xpath("//button[@class='button-1 new-address-next-step-button']")).click();
    driver.findElement(By.xpath("//button[@class='button-1 shipping-method-next-step-button']")).click();
    driver.findElement(By.id("shippingoption_1")).click();  // Next Day Air

    // 2.34-2.37 Continue, select payment method (Credit Card)
    driver.findElement(By.xpath("//button[@class='button-1 payment-method-next-step-button']")).click();
    driver.findElement(By.id("paymentmethod_1")).click(); // Credit Card
    driver.findElement(By.xpath("//button[@class='button-1 payment-method-next-step-button']")).click();

    // 2.38-2.41 Fill in credit card details
    new Select(driver.findElement(By.id("CreditCardType"))).selectByVisibleText("Master card");
    driver.findElement(By.id("CardholderName")).sendKeys("John Doe");
    driver.findElement(By.id("CardNumber")).sendKeys("1234567812345678");
    new Select(driver.findElement(By.id("ExpireMonth"))).selectByVisibleText("12");
    new Select(driver.findElement(By.id("ExpireYear"))).selectByVisibleText("2026");
    driver.findElement(By.id("CardCode")).sendKeys("123");

    // 2.42-2.46 Confirm order details
    driver.findElement(By.xpath("//button[@class='button-1 payment-info-next-step-button']")).click();
    Assert.assertTrue(driver.findElement(By.xpath("//span[@class='value-summary']/strong")).getText().contains("$2950.00"));
    driver.findElement(By.xpath("//button[@class='button-1 confirm-order-next-step-button']")).click();

    // 2.47 Verify "Thank You" message
    WebElement thankYouMessage = driver.findElement(By.xpath("//h1[contains(text(),'Thank you')]"));
    Assert.assertTrue("Thank you message is missing", thankYouMessage.getText().contains("Thank you"));

    // 2.48 Verify the message "Your order has been successfully processed!"
    WebElement orderSuccessMessage = driver.findElement(By.xpath("//strong[contains(text(),'Your order has been successfully processed!')]"));
    Assert.assertTrue("Order success message is missing", orderSuccessMessage.getText().contains("Your order has been successfully processed!"));

    // 2.49 Click on the "Continue" button
    driver.findElement(By.xpath("//button[contains(text(),'Continue')]")).click();

    // 2.50 Verify the text "Welcome to our store"
    WebElement welcomeMessage = driver.findElement(By.xpath("//h2[contains(text(),'Welcome to our store')]"));
    Assert.assertTrue("Welcome message is missing", welcomeMessage.getText().contains("Welcome to our store"));
}



    // After each test, close the browser
    @After
    public void tearDown() {
        driver.quit();
    }
}

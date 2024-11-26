package utilities;


import browserfactory.BaseTest;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class Utility extends BaseTest {

    // Method for mouse hover on menu
    public void hoverOverElement(WebElement element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
    }

    // Method to click on an element using By locator
    public void clickOnElement(By by) {
        WebElement element = driver.findElement(by);
        element.click();
    }

    // Method to select an option from a dropdown
    public void selectFromDropdown(By by, String visibleText) {
        WebElement dropdown = driver.findElement(by);
        Select select = new Select(dropdown);
        select.selectByVisibleText(visibleText);
    }

    // Method to verify the text on an element
    public void verifyText(By by, String expectedText) {
        WebElement element = driver.findElement(by);
        Assert.assertTrue("Text does not match", element.getText().contains(expectedText));
    }

    // Method to enter text into an input field
    public void enterText(By by, String text) {
        WebElement inputField = driver.findElement(by);
        inputField.clear();
        inputField.sendKeys(text);
    }

    // Method to verify the price text of a product
    public void verifyPrice(By by, String expectedPrice) {
        WebElement price = driver.findElement(by);
        Assert.assertEquals("Price does not match", expectedPrice, price.getText());
    }

    // Method to interact with the shopping cart (e.g., go to the cart, update quantity)
    public void updateShoppingCart(String qty) {
        WebElement qtyField = driver.findElement(By.xpath("//input[@class='qty-input']"));
        qtyField.clear();
        qtyField.sendKeys(qty);

        WebElement updateCartButton = driver.findElement(By.xpath("//button[@title='Update shopping cart']"));
        updateCartButton.click();
    }

    // Method to verify the cart total
    public void verifyCartTotal(String expectedTotal) {
        WebElement totalPrice = driver.findElement(By.xpath("//span[@class='product-subtotal']"));
        Assert.assertEquals("Total is incorrect", expectedTotal, totalPrice.getText());
    }

    // Method to click on 'Add to Cart' button
    public void clickAddToCart() {
        WebElement addToCartButton = driver.findElement(By.id("add-to-cart-button-20"));
        addToCartButton.click();
    }

    // Method to verify a confirmation message
    public void verifyConfirmationMessage(String expectedMessage) {
        WebElement confirmationMessage = driver.findElement(By.xpath("//div[@class='bar-notification success']"));
        Assert.assertTrue("Confirmation message is incorrect", confirmationMessage.getText().contains(expectedMessage));
    }

    // Method to fill out address form
    public void fillOutAddressForm(String country, String city, String address, String zip, String phone) {
        new Select(driver.findElement(By.id("BillingNewAddress_CountryId"))).selectByVisibleText(country);
        driver.findElement(By.id("BillingNewAddress_City")).sendKeys(city);
        driver.findElement(By.id("BillingNewAddress_Address1")).sendKeys(address);
        driver.findElement(By.id("BillingNewAddress_ZipPostalCode")).sendKeys(zip);
        driver.findElement(By.id("BillingNewAddress_PhoneNumber")).sendKeys(phone);
    }

    // Method to verify that the page has loaded and title is correct
    public void verifyPageTitle(String expectedTitle) {
        WebElement pageTitle = driver.findElement(By.xpath("//h1[contains(text(),'" + expectedTitle + "')]"));
        Assert.assertTrue("Page title is incorrect", pageTitle.getText().contains(expectedTitle));
    }

    // Method to click 'Proceed to Checkout'
    public void proceedToCheckout() {
        WebElement checkoutButton = driver.findElement(By.id("checkout"));
        checkoutButton.click();
    }

    // Method to select a shipping method
    public void selectShippingMethod(String method) {
        driver.findElement(By.xpath("//button[@class='button-1 shipping-method-next-step-button']")).click();
        WebElement shippingOption = driver.findElement(By.id(method));
        shippingOption.click();
    }

    // Method to select payment method
    public void selectPaymentMethod(String method) {
        new Select(driver.findElement(By.id("paymentmethod_1"))).selectByVisibleText(method);
        driver.findElement(By.xpath("//button[@class='button-1 payment-method-next-step-button']")).click();
    }

    // Method to verify payment, shipping, and total details
    public void verifyPaymentDetails(String paymentMethod, String shippingMethod, String totalPrice) {
        Assert.assertTrue("Payment method is incorrect", driver.getPageSource().contains(paymentMethod));
        Assert.assertTrue("Shipping method is incorrect", driver.getPageSource().contains(shippingMethod));
        Assert.assertTrue("Total price is incorrect", driver.getPageSource().contains(totalPrice));
    }
}
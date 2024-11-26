package homepage;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import utilities.Utility;

public class TopMenuTest {
    //baseUrl
    String baseUrl ="https://demowebshop.tricentis.com/";

    WebDriver driver = new ChromeDriver();


    //method to select menu
    public void selectMenu(String menu){
        WebElement menuElement = driver.findElement(By.xpath("/html/body/div[4]/div[1]/div[2]/ul[1]/li[1]/a"));
        menuElement.click();
    }
    @Test
    public void verifyPageNavigation(){
        driver.get(baseUrl);

        //select menu
        selectMenu("Books");

        }

    }


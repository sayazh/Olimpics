package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import utilities.TestBase;

public class porsche2 extends TestBase {
    public void open() {
        driver.get("https://www.porsche.com/usa/modelstart/");

    }


    @Test
    public void prosheTest() {
        open();

        //3.Select model 718
        driver.findElement(By.linkText("718")).click();

        //4. Remember the price of 718 Cayman Model S
        WebElement priceOfModelS = driver.findElement(By.xpath("//*[@id=\"m982130\"]/div[1]/div[2]/div[2]"));
        String infoWithPrice = priceOfModelS.getText();
        String priceOfCar = infoWithPrice.substring(6, 13).trim();

        //5.Click on 718 Cayman S
        driver.findElement(By.xpath("//*[@id=\"m982130\"]/div[1]/div[2]/div[1]")).click();
        String winHandleBefore = driver.getWindowHandle();

        // Switch to new window opened
        for (String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }
        //6.Verify that Base price displayed on the page is same the price from step 4

    }
}

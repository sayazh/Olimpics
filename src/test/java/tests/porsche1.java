package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.TestBase;

public class porsche1 extends TestBase {



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

        WebElement basePrice = driver.findElement(By.xpath("//*[@id=\"s_price\"]/div[1]/div[1]/div[2]"));
        String basePriceTxt = basePrice.getText();
        String basePriceOfCar = basePriceTxt.substring(1).trim();
        System.out.println("Price on home page: " + priceOfCar + " | Base price: " + basePriceOfCar);
        Assert.assertEquals(priceOfCar, basePriceOfCar);

        //7.Verify that Price for Equipment 0
        String expectedEquipment = "0";
        String equipmentTxt = driver.findElement(By.xpath("//*[@id=\"s_price\"]/div[1]/div[2]/div[2]")).getText();
        String actualEquipment = equipmentTxt.substring(1).trim();
        System.out.println("Expected equipment price: " + expectedEquipment + " | Actual Equipment price: " + actualEquipment);
        Assert.assertEquals(expectedEquipment, actualEquipment);

        //8.Verify that total price is the sum of base price +Delivery, Processing and Handling Fee
        String deliveryFeeTxt = driver.findElement(By.xpath("//*[@id=\"s_price\"]/div[1]/div[3]/div[2]")).getText();
        String delivery = deliveryFeeTxt.substring(1).replaceAll(",", "").trim();
        String totalPriceTxt = driver.findElement(By.xpath("//*[@id=\"s_price\"]/div[1]/div[4]/div[2]")).getText();
        String totalPrice = totalPriceTxt.substring(1).replaceAll(",", "").trim();
        String baseCarPrice = basePriceOfCar.replaceAll(",", "").trim();

        System.out.println("Delivery and extra service fee: " + delivery + "| Base price of the car: " + baseCarPrice + " | total price: " + totalPrice);

        Integer totalInt = Integer.parseInt(totalPrice);
        Integer sum = Integer.parseInt(baseCarPrice) + Integer.parseInt(delivery);

        Assert.assertEquals(totalInt, sum);

        //9.Select color "Miami Blue
        driver.findElement(By.xpath("//*[@id=\"s_exterieur_x_FJ5\"]/span")).click();
        WebElement maiamiBlue = driver.findElement(By.xpath("//*[@id=\"s_exterieur_x_IAF\"]/div[2]/div[1]/div"));
        String priceOfMaiamiBlue = maiamiBlue.getText();
        String priceOfMaiami = priceOfMaiamiBlue.substring(10).trim();
        String equipmentNewPrice = driver.findElement(By.xpath("//*[@id=\"s_price\"]/div[1]/div[2]/div[2]")).getText();

        //10.Verify that Price for Equipment is Equal to Miami Blue price
        System.out.println("Price of Maiami blue: " + priceOfMaiami + " Equipment new price: " + equipmentNewPrice);
        Assert.assertEquals(priceOfMaiami, equipmentNewPrice);

        //11. Verify that total price is the sum of base price +Price for Equipment+Delivery.
        String totalPriceAfterSelectMiami =driver.findElement(By.xpath("//*[@id=\"s_price\"]/div[1]/div[4]/div[2]")).getText().replaceAll(",","");
        Integer totalAfterSelect=Integer.parseInt(totalPriceAfterSelectMiami.substring(1).trim());
        Integer priceForEquipment = Integer.parseInt(equipmentNewPrice.substring(1).replace(",", "").trim());
        Integer summary = sum + priceForEquipment;
        System.out.println("Expected total price: " + totalAfterSelect + " Actual total price: " + summary);
        Assert.assertEquals(totalAfterSelect, summary);




    }

    }








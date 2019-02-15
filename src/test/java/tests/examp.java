package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import utilities.TestBase;

import java.util.ArrayList;
import java.util.List;

public class examp extends TestBase {

    public void login() {
        driver.get("https://en.wikipedia.org/wiki/2016_Summer_Olympics#Medal_table");

    }

    @Test
    public void getMaxSilverCountry() {
        login();
        int counter = 0;
        List<WebElement> silverColumn = driver.findElements(By.xpath("//*[@id=\"mw-content-text\"]/div/table[8]/tbody/tr/td[3]"));
        List<WebElement> countryName = driver.findElements(By.xpath("//*[@id=\"mw-content-text\"]/div/table[8]/tbody/tr/th"));

        for (int i = 0; i < silverColumn.size(); i++) {
            if (silverMaxNumber() == Integer.parseInt(silverColumn.get(i).getText())) {
                counter = i;
            }
        }

       System.out.println(countryName.get(counter).getText());
    }

    public int silverMaxNumber() {
        login();
        List<WebElement> silverColumn = driver.findElements(By.xpath("//*[@id=\"mw-content-text\"]/div/table[8]/tbody/tr/td[3]"));
        ArrayList<String> silverNumbers = new ArrayList<String>();
        for (WebElement el : silverColumn) {
            silverNumbers.add(el.getText());
        }
        System.out.println("The greatest number of silver medals: " + findMax(silverNumbers));
        return Integer.parseInt(findMax(silverNumbers));
    }

    public String findMax(ArrayList<String> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            if (Integer.valueOf(list.get(i)) > Integer.valueOf(list.get(i + 1))) {
                return list.get(i);
            }
        }
        return null;
    }
}
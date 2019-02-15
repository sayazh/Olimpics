package tests;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.TestBase;

import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.lang3.ArrayUtils.isSorted;

public class OlimpicsWebTable extends TestBase {


    public void login() {
        driver.get("https://en.wikipedia.org/wiki/2016_Summer_Olympics#Medal_table");

    }

    @Test
    public void sortTest() {
        login();
        List<WebElement> rank = driver.findElements(By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr/td[1]"));
        int[] rankNumbers = new int[rank.size() - 2];
        int index = 0;
        for (int i = 0; i < rank.size() - 2; i++) {
            rankNumbers[index] = Integer.valueOf(rank.get(i).getText());
            index++;
        }
        //2.Verify that by default the Medal table is sorted by rank in ascending order
        Assert.assertTrue(isSorted(rankNumbers));

        //3.Click on NOC
        driver.findElement(By.xpath("//*[@id=\"mw-content-text\"]/div/table[8]/thead/tr/th[2]")).click();

        //4.Verify that the table is sorted in alphabetical order
        List<WebElement> secondColumn = driver.findElements(By.xpath("//*[@id=\"mw-content-text\"]/div/table[8]/tbody/tr/th"));
        String[] countryNames = new String[secondColumn.size()];
        int idx = 0;
        for (WebElement element : secondColumn) {
            countryNames[idx] = element.getText();
            idx++;
        }
        Assert.assertTrue(isSorted(countryNames));
        System.out.println("Country names length: " + countryNames.length);

        //5.Verify that Rank column is not in ascending order anymore
        List<WebElement> rankColumn2 = driver.findElements(By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr/td[1]"));
        int[] rankNumbers2 = new int[rankColumn2.size() - 1];
        int index1 = 0;
        for (int i = 0; i < rankColumn2.size(); i++) {
            if (i != 7) {
                rankNumbers2[index1] = Integer.valueOf(rankColumn2.get(i).getText());
                index1++;
            } else {
                continue;
            }
        }
        Assert.assertFalse(isSorted(rankNumbers2));
    }


    @Test
    public void most() {
        login();
        List<WebElement> countries = driver.findElements(By.xpath("//*[@id=\"mw-content-text\"]/div/table[8]/tbody/tr/td[2]"));
        ArrayList<String> goldCountry = new ArrayList<String>();
        int j = 0;
        for (int i = 0; i < countries.size() - 2; i++) {
            goldCountry.add(countries.get(i).getText());
        }
        System.out.println("The greatest number of gold medals: " + findMax(goldCountry));
        int index =getRowIndex("46");
        System.out.println("right here: "+getRowIndex("19"));

    }
     @Test
     public void silverMaxNumber(){
        login();
        List<WebElement> silverColumn =driver.findElements(By.xpath("//*[@id=\"mw-content-text\"]/div/table[8]/tbody/tr/td[3]"));
        ArrayList<String> silverNumbers = new ArrayList<String>();
        for (WebElement el : silverColumn){
            silverNumbers.add(el.getText());
        }
         System.out.println("The greatest number of silver medals: "+findMax(silverNumbers));

     }

     @Test
     public void bronzeMaxNumber(){
         login();
         List<WebElement> bronzeColumn =driver.findElements(By.xpath("//*[@id=\"mw-content-text\"]/div/table[8]/tbody/tr/td[4]"));
         ArrayList<String> bronzeNumbers = new ArrayList<String>();
         for (WebElement el : bronzeColumn){
            bronzeNumbers.add(el.getText());
         }
         System.out.println("The greatest number of bronze medals: "+findMax(bronzeNumbers));
     }



    public String findMax(ArrayList<String> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            if (Integer.valueOf(list.get(i)) > Integer.valueOf(list.get(i + 1))) {
                return list.get(i);
            }
        }
        return null;
    }
      public int getRowIndex(String maxNumber) {
          List<WebElement> allRows = driver.findElements(By.xpath("//*[@id=\"mw-content-text\"]/div/table[8]/tbody/tr/td[1]"));
          List<WebElement> allColumns =driver.findElements(By.xpath("//*[@id=\"mw-content-text\"]/div/table[8]/tbody/tr[1]/td"));
          for (int i = 0; i < allRows.size() - 2; i++) {
              if (allRows.get(i).getText().equals(maxNumber)) {
                  return i + 1;
              }
          }
          return 0;

      }

          public String country(int num) {
              List<WebElement> countryName = driver.findElements(By.xpath("//*[@id=\"mw-content-text\"]/div/table[8]/tbody/tr/th"));
              List<WebElement> allRows1 = driver.findElements(By.xpath("//*[@id=\"mw-content-text\"]/div/table[8]/tbody/tr"));
              String str = "";
              for (int i = 0; i < allRows1.size() - 2; i++) {
                  if (i==num) {
                      str = countryName.get(num).getText();
                  }
              }
              return str;
          }

}






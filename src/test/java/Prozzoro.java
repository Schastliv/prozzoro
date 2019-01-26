import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

public class Prozzoro {

    @Test
    public void checkNoResult() throws Exception {
        String searchQuery = "asassd";
        WebDriver webDriver = new ChromeDriver();
        try {
            webDriver.manage().window().maximize();
            webDriver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
            webDriver.get("http://prozorro.gov.ua");
            WebElement webElement = webDriver
                    .findElement(By.cssSelector("#query"));
            webElement.sendKeys("asassd");

            new WebDriverWait(webDriver, 3).until(new ExpectedCondition<Boolean>() {
                public Boolean apply(WebDriver webDriver) {
                    return webDriver.findElement(By.xpath("//a[@class='selected']//span")).getText().contains("asassd");
                }
            });

            webElement.sendKeys(Keys.ENTER);

            WebElement noResult = webDriver.findElement(By.xpath("//div[contains(text(),\"Жодних результатiв\")]"));

            WebElement elementCkross = webDriver.findElement (By.xpath("//*[@class ='delete']"));
            elementCkross.click();
            Thread.sleep(2000);
            Assert.assertTrue(webDriver.findElements(By.xpath("//div[contains(text(),\"Жодних результатiв\")]")).isEmpty(),"No result is not displayed");

        } finally {
            webDriver.quit();
        }
    }


//@Test
//public void actionsTest(){
//        WebDriver driver=new ChromeDriver();
//        try{
//        driver.get("dou.ua");
//        driver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS);
//        WebElement salariesElement=driver
//                  .findElement(By.xpath("//a[contains(@href,'salaries')]"));
//
//        Actions actions=new Actions(driver);
//        actions.moveToElement(salariesElement).build().perform();
//
//        Assert.assertEquals(salariesElement.getCssValue("color"),
//                "rgba(255,0,0,1)",
//                    "Elementis red after hovering");
//
//
//        })finally{
//        if(driver!=null){
//        driver.quit();
//
//                  }
//                }
//            }

}

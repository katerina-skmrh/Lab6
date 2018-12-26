import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Google1 {
    static WebDriver driver;

    @Before
    public void setUp() {
        String exePath = "C:\\Users\\Acer\\Documents\\Testing\\chromedriver_win32\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", exePath);
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }
    @Test
    public void aTest() {
        driver.get("https://www.google.com/");
        //WebElement searchField = driver.findElement(By.cssSelector("input#lst-ib.gsfi"));
        WebElement searchField = driver.findElement(By.cssSelector("body.hp.vasq:nth-child(2) div.ctr-p:nth-child(4) div.jhp.big:nth-child(4) form.tsf.nj:nth-child(5) div.A8SBwf div.RNNXgb:nth-child(2) div.SDkEP div.a4bIc > input.gLFyf.gsfi:nth-child(3)"));
        searchField.clear();
        String wordForSearch = "HM";
        searchField.sendKeys(wordForSearch);

        driver.findElement(By.name("btnK")).click();

        int pageNumber=2;
        String keyWord = "одежда";

        while (true)
        {
            try{
                driver.findElement(By.xpath("//a[@id='pnnext']")).click();
                driver.findElement(By.partialLinkText(keyWord));
                break;
            }
            catch(NoSuchElementException e){
                try {
                    driver.findElement(By.xpath("//a[@id='pnnext']")).click();
                    pageNumber++;
                    }
                    catch (NoSuchElementException e1){
                    System.out.println("The last page");
                    break;
                }
            }
        }

        System.out.println("Page number is "+pageNumber);



    }
    @After
    public void tearDown()  {

        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(scrFile, new File("Google1.png"));
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
        driver.quit();
    }
}
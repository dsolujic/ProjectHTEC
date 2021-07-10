import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class CreateReport {

    private static final WebDriver driver = new ChromeDriver();

    @Test(testName = "CreateReport")
    public static void Report() {

        //Login
        driver.get(Utils.LoginForm);
        driver.findElement(By.name("email")).sendKeys("dsolujic@gmail.com");
        driver.findElement(By.name("password")).sendKeys("Noki@n958gb");
        driver.findElement(By.className("full-width-btn")).click(); //Click Login
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.findElement(By.linkText("Reports")).click();
        driver.findElement(By.cssSelector("#root > div > div.grid-menu-container > div.main-grid > div > div:nth-child(1) > div > div > div > span > a")).click();
        driver.findElement(By.name("summary")).sendKeys("Create report automatically");
        driver.findElement(By.cssSelector("#root > div > div.grid-menu-container > div.main-grid > div > div > div.width-container.default-container.default-padding > div:nth-child(2) > select > option:nth-child(4)")).click();
        driver.findElement(By.cssSelector("#root > div > div.grid-menu-container > div.main-grid > div > div > div.width-container.default-container.default-padding > div:nth-child(3) > select > option:nth-child(5)")).click();
        driver.findElement(By.cssSelector("#root > div > div.grid-menu-container > div.main-grid > div > div > div.width-container.default-container.default-padding > div:nth-child(4) > select > option:nth-child(6)")).click();
        driver.findElement(By.cssSelector("#root > div > div.grid-menu-container > div.main-grid > div > div > div.width-container.default-container.default-padding > div:nth-child(5) > div.form-element-item > textarea")).sendKeys("Report description");
        driver.findElement(By.id("step-0")).sendKeys("Step 1");
        driver.findElement(By.cssSelector("#root > div > div.grid-menu-container > div.main-grid > div > div > div.width-container.default-container.default-padding > div:nth-child(6) > div.form-element.undefined > div:nth-child(2) > div > div")).click();
        driver.findElement(By.id("step-1")).sendKeys("Step 2");
        driver.findElement(By.name("expected_result")).sendKeys("Expected result");
        //driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div[2]/div/div/div[2]/div[8]/div/div[2]/div[2]/label")).sendKeys("C:\\Users\\HP EliteBook 840 G3\\Desktop\\picture9.png");
        driver.findElement(By.cssSelector("#root > div > div.grid-menu-container > div.main-grid > div > div > div.width-container.default-container.default-padding > div.submit-button > button")).click();
    }
}
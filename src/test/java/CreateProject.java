import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.File;
import java.util.concurrent.TimeUnit;

public class CreateProject {

    private static final WebDriver driver = new ChromeDriver();

    public static void Screenshot() throws Exception {
        TakesScreenshot ts = (TakesScreenshot)driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(source, new File("D:\\AutomationProjectHTEC\\ScreenShots\\Project.png"));
    }

    @Test(testName = "Project")
    public static void Project() throws Exception {
        WebDriverWait wait = new WebDriverWait(driver, 20);

        //Login
        driver.get(Utils.LoginForm);
        driver.findElement(By.name("email")).sendKeys("dsolujic@gmail.com");
        driver.findElement(By.name("password")).sendKeys("Noki@n958gb");
        driver.findElement(By.className("full-width-btn")).click(); //Click Login
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        //Create new project
        driver.findElement(By.linkText("Playground")).click();
        driver.findElement(By.linkText("New Project")).click();
        driver.findElement(By.name("title")).sendKeys("HTEC Project");
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div[2]/div/div[2]/div[2]/button")).click(); //Click Submit button for creating project
        WebElement playground = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#root > div > div.grid-menu-container > div.main-grid > div > div:nth-child(1) > div > a")));
        Screenshot();

        //Return to playground page and create new project with same name
        playground.click();
        driver.findElement(By.linkText("New Project")).click();
        driver.findElement(By.name("title")).sendKeys("HTEC Project");
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div[2]/div/div[2]/div[2]/button")).click(); //Click Submit button for creating project

        //Assert project title uniqueness
        String cpValidation = driver.findElement(By.xpath("//*[@id=\"validation-msg\"]")).getText();
        Assert.assertEquals(cpValidation, "Project title already exist");

        //Create second project
        driver.findElement(By.linkText("Playground")).click();
        driver.findElement(By.linkText("New Project")).click();
        driver.findElement(By.name("title")).sendKeys("HTEC Project2");
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div[2]/div/div[2]/div[2]/button")).click(); //Click Submit button for creating project
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        WebElement projectLoad = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#root > div > div.grid-menu-container > div.main-grid > div > div.project > div.project-bottom > div:nth-child(1) > div.no-content")));

        //Assert that sections are empty
        String techSection = projectLoad.getText();
        Assert.assertEquals(techSection, "No technologies created");
        String senioritiesSection = driver.findElement(By.cssSelector("#root > div > div.grid-menu-container > div.main-grid > div > div.project > div.project-bottom > div:nth-child(2) > div.no-content")).getText();
        Assert.assertEquals(senioritiesSection, "No seniorities created");
        String teamsSection = driver.findElement(By.cssSelector("#root > div > div.grid-menu-container > div.main-grid > div > div.project > div.project-bottom > div:nth-child(3) > div.no-content")).getText();
        Assert.assertEquals(teamsSection, "No teams created");
        String peopleSection = driver.findElement(By.cssSelector("#root > div > div.grid-menu-container > div.main-grid > div > div.project > div.project-bottom > div:nth-child(4) > div.no-content")).getText();
        Assert.assertEquals(peopleSection, "No people created");

        //Open created project
        driver.findElement(By.linkText("Playground")).click();
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div[2]/div/div[2]/div/span[1]/a/div/div[1]/div")).click(); //Open HTEC Project2

        //Insert values in section
        driver.findElement(By.name("technology")).sendKeys("SQL");
        driver.findElement(By.cssSelector("#root > div > div.grid-menu-container > div.main-grid > div > div.project > div.project-bottom > div:nth-child(1) > div.submit-button > button")).click(); //Click tech create button
        driver.findElement(By.name("seniority")).sendKeys("Medior");
        driver.findElement(By.cssSelector("#root > div > div.grid-menu-container > div.main-grid > div > div.project > div.project-bottom > div:nth-child(2) > div.submit-button > button")).click(); //Click seniority create button
        driver.findElement(By.name("team")).sendKeys("DEV");
        driver.findElement(By.cssSelector("#root > div > div.grid-menu-container > div.main-grid > div > div.project > div.project-bottom > div:nth-child(3) > div.submit-button > button")).click(); //Click team create button

        //Assert inserted values in sections
        String tech = driver.findElement(By.cssSelector("#root > div > div.grid-menu-container > div.main-grid > div > div.project > div.project-bottom > div:nth-child(1) > div.settings > div")).getText(); //Get tech value
        Assert.assertEquals(tech, "SQL");
        String seniority = driver.findElement(By.cssSelector("#root > div > div.grid-menu-container > div.main-grid > div > div.project > div.project-bottom > div:nth-child(2) > div.settings > div")).getText(); //Get seniority value
        Assert.assertEquals(seniority, "Medior");
        String team = driver.findElement(By.cssSelector("#root > div > div.grid-menu-container > div.main-grid > div > div.project > div.project-bottom > div:nth-child(3) > div.settings > div")).getText(); //Get team value
        Assert.assertEquals(team, "DEV");

        //Edit section values
        driver.findElement(By.cssSelector("#root > div > div.grid-menu-container > div.main-grid > div > div.project > div.project-bottom > div:nth-child(1) > div.settings > div > div > div.settings-list-buttons-edit")).click(); //Click tech edit button
        driver.findElement(By.name("technology_name")).sendKeys(Keys.SHIFT, Keys.HOME); //select old value
        driver.findElement(By.name("technology_name")).sendKeys("Selenium"); //insert new value
        driver.findElement(By.cssSelector("#root > div > div.grid-menu-container > div.main-grid > div > div.project > div.project-bottom > div:nth-child(1) > div.settings > div > div > div.settings-list-buttons > div.settings-list-buttons-confirm")).click(); //Click confirm button
        driver.findElement(By.cssSelector("#root > div > div.grid-menu-container > div.main-grid > div > div.project > div.project-bottom > div:nth-child(2) > div.settings > div > div > div.settings-list-buttons-edit")).click();  //Click seniority edit button
        driver.findElement(By.name("seniority_name")).sendKeys(Keys.SHIFT, Keys.HOME); //select old value
        driver.findElement(By.name("seniority_name")).sendKeys("Senior"); //insert new value
        driver.findElement(By.cssSelector("#root > div > div.grid-menu-container > div.main-grid > div > div.project > div.project-bottom > div:nth-child(2) > div.settings > div > div > div.settings-list-buttons > div.settings-list-buttons-confirm")).click(); //Click confirm button
        driver.findElement(By.cssSelector("#root > div > div.grid-menu-container > div.main-grid > div > div.project > div.project-bottom > div:nth-child(3) > div.settings > div > div > div.settings-list-buttons-edit")).click(); //Click team edit button
        driver.findElement(By.name("team_name")).sendKeys(Keys.SHIFT, Keys.HOME); //select old value
        driver.findElement(By.name("team_name")).sendKeys("QA"); //insert new value
        driver.findElement(By.cssSelector("#root > div > div.grid-menu-container > div.main-grid > div > div.project > div.project-bottom > div:nth-child(3) > div.settings > div > div > div.settings-list-buttons > div.settings-list-buttons-confirm")).click(); //Click confirm button

        //wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("#root > div > div:nth-child(2) > div > div")));
        //NOTE: This want work waiting for notification to close down, so test is failing

        //Assert edited section values
        tech = driver.findElement(By.cssSelector("#root > div > div.grid-menu-container > div.main-grid > div > div.project > div.project-bottom > div:nth-child(1) > div.settings > div")).getText();
        Assert.assertEquals(tech, "Selenium");
        seniority = driver.findElement(By.cssSelector("#root > div > div.grid-menu-container > div.main-grid > div > div.project > div.project-bottom > div:nth-child(2) > div.settings > div")).getText();
        Assert.assertEquals(seniority, "Senior");
        team = driver.findElement(By.cssSelector("#root > div > div.grid-menu-container > div.main-grid > div > div.project > div.project-bottom > div:nth-child(3) > div.settings > div")).getText();
        Assert.assertEquals(team, "QA");

        //Remove section values
        driver.findElement(By.cssSelector("#root > div > div.grid-menu-container > div.main-grid > div > div.project > div.project-bottom > div:nth-child(1) > div.settings > div > div > div.settings-list-buttons-remove")).click(); //Click remove button
        driver.findElement(By.cssSelector("#react-confirm-alert > div > div > div > div.confirmation-dialog--buttons > div.confirmation-dialog--buttons--confirm > div")).click(); //Click remove confirmation button
        driver.findElement(By.cssSelector("#root > div > div.grid-menu-container > div.main-grid > div > div.project > div.project-bottom > div:nth-child(2) > div.settings > div > div > div.settings-list-buttons-remove")).click(); //Click remove button
        driver.findElement(By.cssSelector("#react-confirm-alert > div > div > div > div.confirmation-dialog--buttons > div.confirmation-dialog--buttons--confirm > div")).click(); //Click remove confirmation button
        driver.findElement(By.cssSelector("#root > div > div.grid-menu-container > div.main-grid > div > div.project > div.project-bottom > div:nth-child(3) > div.settings > div > div > div.settings-list-buttons-remove")).click(); //Click remove button
        driver.findElement(By.cssSelector("#react-confirm-alert > div > div > div > div.confirmation-dialog--buttons > div.confirmation-dialog--buttons--confirm > div")).click(); //Click remove confirmation button

        //Assert that sections are empty
        techSection = projectLoad.getText();
        Assert.assertEquals(techSection, "No technologies created");
        senioritiesSection = driver.findElement(By.cssSelector("#root > div > div.grid-menu-container > div.main-grid > div > div.project > div.project-bottom > div:nth-child(2) > div.no-content")).getText();
        Assert.assertEquals(senioritiesSection, "No seniorities created");
        teamsSection = driver.findElement(By.cssSelector("#root > div > div.grid-menu-container > div.main-grid > div > div.project > div.project-bottom > div:nth-child(3) > div.no-content")).getText();
        Assert.assertEquals(teamsSection, "No teams created");

        //Validate Submit button for creating people
        driver.findElement(By.cssSelector("#root > div > div.grid-menu-container > div.main-grid > div > div.project > div.project-bottom > div:nth-child(4) > div.submit-button > button")).click(); //Click submit button for creating people
        String createPeopleValidation = driver.findElement(By.id("validation-msg")).getText();
        Assert.assertEquals(createPeopleValidation, "Name is required");

        //Create people
        driver.findElement(By.name("person")).sendKeys("Lee");
        driver.findElement(By.cssSelector("#root > div > div.grid-menu-container > div.main-grid > div > div.project > div.project-bottom > div:nth-child(4) > div.submit-button > button")).click(); //Click submit button for creating people
        String personName = driver.findElement(By.cssSelector("#root > div > div.grid-menu-container > div.main-grid > div > div.project > div.project-bottom > div:nth-child(4) > div.settings > div.settings-list > div > div > div.person-container-bottom--teams-people--person-name")).getText();

        //Assert created people
        Assert.assertEquals(personName, "Lee");

        //Remove people
        driver.findElement(By.id("57")).click(); //Click remove button
        driver.findElement(By.cssSelector("#react-confirm-alert > div > div > div > div.confirmation-dialog--buttons > div.confirmation-dialog--buttons--confirm > div")).click(); //Click confirm remove button

        //Negative testing
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div[2]/div/div[1]/div/div/div/span/button")).click(); // Click remove button on project
        driver.findElement(By.xpath("//*[@id=\"react-confirm-alert\"]/div/div/div/div[3]/div[1]/div")).click(); //Click cancel on delete popup dialog

        //Delete project
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div[2]/div/div[1]/div/div/div/span/button")).click(); // Click remove button on project
        driver.findElement(By.xpath("//*[@id=\"react-confirm-alert\"]/div/div/div/div[3]/div[2]/div")).click(); //Click remove on delete popup dialog

        //Delete second project
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div[2]/div/div[2]/div/span[1]/a/div/div[1]/div")).click(); //Open HTEC Project
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div[2]/div/div[1]/div/div/div/span/button")).click(); // Click remove button on project
        driver.findElement(By.xpath("//*[@id=\"react-confirm-alert\"]/div/div/div/div[3]/div[2]/div")).click(); //Click remove on delete popup dialog

        driver.close();
    }
}
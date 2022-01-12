package SmokeTest;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

public class SmokeTestSteps {
    WebDriver driver = null;
    JavascriptExecutor js = null;
    Variables var = new Variables();

    private class Variables {
        String MyStor_Home = "https://www.gumtree.co.za";
        String chrome_path = "C:\\Users\\malusi.msomi\\Documents\\GitHub\\GT-smoke\\src\\main\\resources\\drivers\\chromedriver.exe";
        String firefox_path = "C:\\Users\\malusi.msomi\\Documents\\GitHub\\GT-smoke\\src\\main\\resources\\drivers\\geckodriver.exe";
        String cache_proceed_btn = "/html/body//div[2]/button[contains(.,\"Proceed\")]";
        String gumtree_label = "/html/body/div[1]/div[3]/header/div[3]/nav/div[2]/span/span[contains(@class,\"label\")]";
        String username_field = "//*[contains(concat( \" \", @class, \" \" ), concat( \" \", \"input-main\", \" \" ))]";
        String password_field = "/html/body/div[1]/section/div[2]/div/div/div[2]/div[4]/form/div[2]/input";
        String login_button = "//*[contains(concat( \" \", @class, \" \" ), concat( \" \", \"login-submit\", \" \" ))]";
        String username = "/html/body/div[1]/div[3]/header/div[3]/nav/div[2]/span/span[2]";
        String Error_test = "/html/body/div[1]/section/div[2]/div/div/div[1]";
    }
    public void SetChromeBrowserDriver(){
        System.setProperty("webdriver.chrome.driver",var.chrome_path);
        driver = new ChromeDriver();

    }
    public void SetFirefoxBrowserDriver(){
        System.setProperty("webdriver.gecko.driver",var.firefox_path);
        driver = new FirefoxDriver();
    }
    public void verifyHomePageLoad(){

        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        String url = driver.getCurrentUrl();
        System.out.println("Getting URL");
        UserCurrentPage(url);
    }

    public void UserCurrentPage(String url){
        if(url.equals("https://www.gumtree.co.za")){
            System.out.println("Correct page is being presented to the user");
        } else {
            System.out.println("User is presented with another page");
        }
    }

    @Before
    public void Load_Browser_and_navigate_to_registration(){

        SetChromeBrowserDriver();
        js = (JavascriptExecutor) driver;
        driver.get(var.MyStor_Home);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.findElement(By.xpath(var.gumtree_label)).isDisplayed();
        verifyHomePageLoad();

    }

    @Given("The user enters an already existing email")
    public void the_user_enters_an_already_existing_email() {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("email already exists");

        //throw new io.cucumber.java.PendingException();
    }

    @Given("the user enters their {string} and clicks create")
    public void the_user_enters_their_and_clicks_create(String string) {
        // Write code here that turns the phrase above into concrete actions
        driver.findElement(By.xpath(var.cache_proceed_btn)).isDisplayed();
        driver.findElement(By.xpath(var.cache_proceed_btn)).click();
        driver.findElement(By.xpath(var.gumtree_label)).isDisplayed();
        driver.findElement(By.xpath(var.gumtree_label)).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        System.out.println(string);
        enter_login_details(string,var.username_field);
        //throw new io.cucumber.java.PendingException();
    }

    @When("the user tries logging in with a {string}")
    public void the_user_tries_logging_in_with_a(String string) {
        // Write code here that turns the phrase above into concrete actions
        System.out.println(string);
        enter_login_details(string, var.password_field);
        //throw new io.cucumber.java.PendingException();
    }

    @Then("the user is presented with the correct user")
    public void the_user_is_presented_with_the_correct_user() {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("presented with correct user");
        driver.findElement(By.xpath(var.login_button)).isDisplayed();
        driver.findElement(By.xpath(var.login_button)).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.findElement(By.xpath(var.username)).isDisplayed();
        //throw new io.cucumber.java.PendingException();
    }

    public void Incorrect_login(){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.findElement(By.xpath(var.Error_test)).isDisplayed();
    }

    public void enter_login_details(String string,String field){
        driver.findElement(By.xpath(field)).isDisplayed();
        driver.findElement(By.xpath(field)).click();
        driver.findElement(By.xpath(field)).sendKeys(string);
    }
}
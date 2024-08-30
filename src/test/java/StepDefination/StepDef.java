package StepDefination;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import PageObject.AddNewCustomerPage;
import PageObject.LoginPage;
import PageObject.SearchCustomerPage;
import Utilities.ReadConfig;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

/*Child class of BaseClass*/

public class StepDef extends BaseClass {
	@Before
	public void setup() {
		readConfig = new ReadConfig();// we initialized properties class.

		System.out.println("Setup method executed.");
		
		String browser = readConfig.getBrowser();
		//Launch browser
		switch(browser.toLowerCase())
		{
		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;

		case "msedge":
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			break;

		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			break;
		default:
			driver = null;
			break;

		}
		
	}

//////////////////common log in steps for all other step definitions files/////////
	@Given("User Launch Chrome browser")
	public void user_launch_chrome_browser() {

		loginpg = new LoginPage(driver); // called a constructor which we have created in page object class and
											// initialize, Allocated a memoryAllocated a memory
		addNewCustPg = new AddNewCustomerPage(driver); // called a constructor which we have created in page object
														// class and initialize, Allocated a memory
		SearchCustPg = new SearchCustomerPage(driver); // called a constructor which we have created in page object
														// class and initialize, Allocated a memory

	}

	@When("User opens URL {string}")
	public void user_opens_url(String url) {
		driver.get(url);
	}

	@And("User enters Email as {string} and Password as {string}")
	public void user_enters_email_as_and_password_as(String emailadd, String password) {
		loginpg.enterEmail(emailadd);
		loginpg.enterPassword(password);
	}

	@And("Click on Login")
	public void click_on_login() {
		loginpg.clickOnLoginButton();
	}

////////////Login feature step definition steps//////////
	@Then("Page Title should be {string}")
	public void page_title_should_be(String expectedTitle) {
		String actualTitle = driver.getTitle();

		if (actualTitle.equals(expectedTitle)) {
			Assert.assertTrue(true);
		} else {
			Assert.assertFalse(false);
		}
	}

	@When("User click on Log out link")
	public void user_click_on_log_out_link() {
		loginpg.clickOnLogOutButton();
	}

	@And("close browser")
	public void close_browser() {
		driver.close();
		// driver.quit();
	}
/////////////Customers.feature step definition steps below///////////

	@Then("User can view Dashboard")
	public void user_can_view_dashboard() {
		String actualTitle = addNewCustPg.getPageTitle();
		String expectedTitle = "Dashboard / nopCommerce administration";

		if (actualTitle.equals(expectedTitle)) {
			Assert.assertTrue(true);
		} else {
			Assert.assertTrue(false);
		}
	}

	@When("User click on customer Menu")
	public void user_click_on_customer_menu() {
		addNewCustPg.clickOnCustomersMenu();
	}

	@When("click on customer Menu Item")
	public void click_on_customer_menu_item() {
		addNewCustPg.clickOnCustomersMenu();
	}

	@When("click on add new button")
	public void click_on_add_new_button() {
		addNewCustPg.clickOnAddnew();
	}

	@Then("user can view add new customer page")
	public void user_can_view_add_new_customer_page() {
		String actualTitle = addNewCustPg.getPageTitle();
		String expectedTitle = "Add a new customer / nopCommerce administration";
		if (actualTitle.equals(expectedTitle)) {
			Assert.assertTrue(false);
		} else {
			Assert.assertTrue(true);
		}
	}

	@When("user enter customer info")
	public void user_enter_customer_info() {
		// addNewCustPg.enterEmail("Test1@gmail.com");
		addNewCustPg.enterEmail(generateEmailId() + "@gmail.com"); // written this method to generate unique email ID,
																	// called generateEmailID method from BaseClass
		addNewCustPg.enterPassword("test1");
		addNewCustPg.enterFirstName("Omkar");
		addNewCustPg.enterLastName("Tungatkar");
		addNewCustPg.enterGender("Male");
		addNewCustPg.enterDob("13/12/1993");
		addNewCustPg.enterCompanyName("ValueLabs");
		addNewCustPg.enterAdminContent("Admin content");
		addNewCustPg.enterManagerOfVendor("Vendor1");

	}

	@When("click pn Save button")
	public void click_pn_save_button() {
	}

	@Then("user can view confirmation message {string}")
	public void user_can_view_confirmation_message(String expectedConfirmationMessage) {
		String bodyTag = driver.findElement(By.tagName("Body")).getText();
		if (bodyTag.contains(expectedConfirmationMessage)) {
			Assert.assertTrue(true);
		} else {
			Assert.assertTrue(false);
		}
	}

/////////Search customer////////
	@When("Enter customer EMail")
	public void enter_customer_e_mail() {
		SearchCustPg.enterEmailAdd("victoria_victoria@nopCommerce.com");
	}

	@When("Click on search button")
	public void click_on_search_button() {
		SearchCustPg.clickOnSearchButton();
	}

	@Then("User should found Email in the Search table")
	public void user_should_found_email_in_the_search_table() {
		String expectedEmail = "victoria_victoria@nopCommerce.com";

		if (SearchCustPg.searchCustomerByEmail(expectedEmail) == true) {
			Assert.assertTrue(true);
		} else {
			Assert.assertTrue(false);
		}
	}
	/////// Search customer by name////////

	@When("Enter customer FirstName")
	public void enter_customer_first_name() {
		SearchCustPg.enterFirstName("Victoria");
	}

	@When("Enter customer LastName")
	public void enter_customer_last_name() {
		SearchCustPg.enterLastName("Terces");
	}

	@Then("User should found Name in the Search table")
	public void user_should_found_name_in_the_search_table() {
		String expectedName = "Victoria";

		if (SearchCustPg.searchCustomerByName(expectedName) == true) {
			Assert.assertTrue(true);
		} else {
			Assert.assertTrue(false);
		}

	}

	@After
	public void teardown(Scenario sc) {
		System.out.println("Tear Down method executed");
		if (sc.isFailed() == true) {
			// Convert we driver object to TakeScreenshot
			String filewithPath = "C:\\Users\\user\\eclipse-workspace\\CucumberBDD\\Screenshot\\test1.png";
			TakesScreenshot scrShot = ((TakesScreenshot) driver);

			// Call getScreenshotAs method to create image file
			File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);

			// Move image file to new destination
			File DestFIle = new File(filewithPath);

			// Copy file at destination
			try {
				FileUtils.copyFile(SrcFile, DestFIle);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		driver.quit();
	}

	@BeforeStep
	public void beforStepMethodDemo() {
		System.out.println("Before step");
	}

	@AfterStep
	public void afterStepMethodDemo() {
		System.out.println("after step");
	}

}

package StepDefination;

import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;

import PageObject.AddNewCustomerPage;
import PageObject.LoginPage;
import PageObject.SearchCustomerPage;
import Utilities.ReadConfig;

/*Parent class, we created this class for common functions */

public class BaseClass {
	public WebDriver driver; // created object of WebDriver
	public LoginPage loginpg; // created object of LoginPage feature.
	public SearchCustomerPage SearchCustPg; // created object of SearchCustomerPage feature.
	public AddNewCustomerPage addNewCustPg; // created object of AddNewCustomerPage feature
	public ReadConfig readConfig; // instance with property class.
	
	public String generateEmailId()
	{
		return(RandomStringUtils.randomAlphabetic(5)); //written this method to generate unique email ID.
	}
}

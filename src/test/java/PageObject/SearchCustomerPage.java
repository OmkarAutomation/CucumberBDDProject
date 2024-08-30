package PageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchCustomerPage {
	WebDriver ldriver; // object created

	// created constructor
	public SearchCustomerPage(WebDriver rDriver) {
		ldriver = rDriver;
		PageFactory.initElements(rDriver, this);
	}

	@FindBy(id = "SearchEmail")
	WebElement emailAdd;

	@FindBy(id = "search-customers")
	WebElement searchBtn;

	@FindBy(xpath = "//table[@class=\"table table-bordered table-hover table-striped dataTable no-footer\"]//tbody/tr")
	List<WebElement> tableRows;

	@FindBy(xpath = "//table[@class=\"table table-bordered table-hover table-striped dataTable no-footer\"]//tbody/tr[1]/td")
	List<WebElement> tableColumns;

	@FindBy(id = "SearchFirstName")
	WebElement fistName;

	@FindBy(id = "SearchLastName")
	WebElement lastName;

	public void enterEmailAdd(String email) {
		emailAdd.sendKeys(email);
	}

	public void clickOnSearchButton() {
		searchBtn.click();
	}

	public boolean searchCustomerByEmail(String email) {
		boolean found = false;

		// We will get total numbers of rows in grid/table
		int ttlRows = tableRows.size();

		// We will get total numbers of columns in grid/table
		// int ttlColumns = tableColumns.size();

		for (int i = 1; i <= ttlRows; i++) // to iterate all rows of the grid/table
		{
			WebElement webElementEmail = ldriver.findElement(By.xpath(
					"//table[@class=\\\"table table-bordered table-hover table-striped dataTable no-footer\\\"]//tbody/tr["
							+ i + "]/td[]"));
			String actualEmailAdd = webElementEmail.getText();

			if (actualEmailAdd.equals(email)) {
				found = true;

			}

		}
		return found;
	}

	///////// Search customer by name/////////////
	/////////// Action method to enter first name/////
	public void enterFirstName(String firstNameText) {
		fistName.sendKeys(firstNameText);
	}

	public void enterLastName(String lastNameText) {
		lastName.sendKeys(lastNameText);
	}

	public boolean searchCustomerByName(String name) {
		boolean found = false;

		// We will get total numbers of rows in grid/table
		int ttlRows = tableRows.size();

		for (int i = 1; i <= ttlRows; i++) // to iterate all rows of the grid/table
		{
			WebElement webElementName = ldriver.findElement(By.xpath(
					"//table[@class=\\\"table table-bordered table-hover table-striped dataTable no-footer\\\"]//tbody/tr["
							+ i + "]/td[3]"));
			String actualName = webElementName.getText();

			if (actualName.equals(name)) {
				found = true;
				break;
			}

		}
		return found;
	}
}

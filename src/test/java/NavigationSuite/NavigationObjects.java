package NavigationSuite;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NavigationObjects {
	
	WebDriver driver;
	
	public NavigationObjects(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//a[contains(text(),'Sign in')]")
	WebElement signInLink;
	
	public WebElement signInLink(){
		return signInLink;
	}
	
}

package NavigationSuite;

import GlobalSetup.SuiteSetup;

public class NavigationTest extends SuiteSetup{

	public void GoToMyAccountPage() {
		NavigationObjects navObj = new NavigationObjects(driver);
		navObj.signInLink().click();
	}
	
}

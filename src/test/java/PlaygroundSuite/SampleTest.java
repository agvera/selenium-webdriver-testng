package PlaygroundSuite;

import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import GlobalSetup.SuiteSetup;
import NavigationSuite.NavigationTest;

public class SampleTest extends SuiteSetup{
	
	@Test
	public void sampleTest() throws Exception {
		
		// Start Test
		test = report.startTest("Name of Test");
		
		NavigationTest navigate = new NavigationTest();
		navigate.GoToMyAccountPage();
		
		// Log info, status, error using text only
		test.log(LogStatus.INFO, "text info");
        
		// Log info, status, error using text and screenshot attachment
        this.takeScreenshot();
        test.log(LogStatus.INFO, imagePath() + test.addScreenCapture(imagePath()));
 
	}
	
}

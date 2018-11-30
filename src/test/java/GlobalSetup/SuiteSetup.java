package GlobalSetup;

import org.testng.annotations.Test;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;


public class SuiteSetup {
	public static WebDriver driver;
	Properties testProperty = new Properties();
	FileInputStream globalProperties;
	public ExtentReports report;
	public ExtentTest test;
	
	@BeforeTest
	public void Setup() throws IOException {

		// Load configuration file
		globalProperties = new FileInputStream( System.getProperty("user.dir") + "/src/resources/globalConfig.properties");
		testProperty.load(globalProperties);
		
		// If browser is set to Google Chrome		
		if(testProperty.getProperty("browser").equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/resources/chromedriver243");
			driver = new ChromeDriver();
		}
		
		// If browser is set to Mozilla Firefox		
		else if(testProperty.getProperty("browser").equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/src/resources/geckodriver023");
			driver = new FirefoxDriver();
		}
		
		// Adjust screen size to Full Screen	
		//driver.manage().window().fullscreen(); 
				
		// Adjust screen to specific resolution
		// Dimension dimension = new Dimension(1280, 760); 
		// driver.manage().window().setSize(dimension);
		
		// Open base URL
		driver.get(testProperty.getProperty("baseURL"));
				
		// Set implicit wait
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		// Set Report directory
		report = new ExtentReports(System.getProperty("user.dir") + "/src/test/resources/Reports/"+ this.getClass().getSimpleName() + "Report.html", true);
		
	}
	
	@AfterMethod 
	public void getResult(ITestResult result) throws Exception{
		
		// capture screenshot
		this.takeScreenshot();
		
		if(result.getStatus() == ITestResult.FAILURE){
			test.log(LogStatus.FAIL, "Failing Test Case: "+result.getName() + test.addScreenCapture(imagePath()));
			test.log(LogStatus.FAIL, "ERROR: "+result.getThrowable() + test.addScreenCapture(imagePath()));
		} else if(result.getStatus() == ITestResult.SKIP){
			test.log(LogStatus.SKIP, "Test Case Skipped is "+result.getName());
		}
		
		report.endTest(test);
		
		// Set implicit wait
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	@AfterTest
	public void SuiteTeardown() {
		
		// calling flush writes everything to the log file
		report.flush();
		
		//close browser
		driver.quit();
		
	}
	
	public void takeScreenshot() throws IOException {
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File(imagePath()));
	}
	
	public String imagePath() {
		
		SimpleDateFormat sdfDate = new SimpleDateFormat("yyyyMMddHHmmsss");
	    Date now = new Date();
	    String timeStamp = sdfDate.format(now);
		String imgDir = System.getProperty("user.dir") + "/src/test/resources/Reports/"+ this.getClass().getSimpleName() + timeStamp + ".png";
		return imgDir;
	}
	
}

package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {
	
	public static WebDriver driver;
	public Logger logger; // Log4j Logger
	public Properties p;
	
	@BeforeClass(groups = {"Sanity", "Regression", "Master"})
	@Parameters({"os","browser"})
	public void setup(String os, String br) throws IOException {
		
		//Loading config.properties file
		FileReader file = new FileReader("./src//test//resources//config.properties");
		p = new Properties();
		p.load(file);
		
		logger = LogManager.getLogger(this.getClass());
	
		
		if(p.getProperty("execution_env").equalsIgnoreCase("remote")) {
			DesiredCapabilities capabilities = new DesiredCapabilities();
			
			//os
			if(os.equalsIgnoreCase("MacOS")) {
				capabilities.setPlatform(Platform.MAC);
			} else if(os.equalsIgnoreCase("Windows")) {
				capabilities.setPlatform(Platform.WIN11);
			} else if(os.equalsIgnoreCase("Linux")) {
				capabilities.setPlatform(Platform.LINUX);
			} else {
				System.out.println("No Matching OS");
				return;
			}
			
			//browser
			switch(br.toLowerCase()) {
			case "chrome": capabilities.setBrowserName("chrome"); break;
			case "firefox": capabilities.setBrowserName("firefox"); break;
			case "edge": capabilities.setBrowserName("edge"); break;
			case "safari": capabilities.setBrowserName("safari"); break;
			default: System.out.println("No matching browser"); break;
			}
			
			driver = new RemoteWebDriver(URI.create("http://10.0.0.49:4444/wd/hub").toURL(), capabilities);
			
		}
		
		if(p.getProperty("execution_env").equalsIgnoreCase("local")) {
			switch(br.toLowerCase()) {
			case "chrome" : ChromeOptions options = new ChromeOptions();
							options.addArguments("--headless=new");
							driver = new ChromeDriver(options);
							break;
			case "safari" : driver = new SafariDriver(); break;
	 		case "edge" : driver = new EdgeDriver(); break;
			case "firefox" : driver = new FirefoxDriver(); break;
			default : System.out.println("Invalid browser name...."); return;
			
			}
			
		}
		
	
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.get(p.getProperty("appURL"));
		driver.manage().window().maximize();
	}
	
	@AfterClass(groups = {"Sanity", "Regression", "Master"})
	public void teardown() {
		driver.close();
	}
	
	public String randomString() {
	    return generateRandomAlphabeticString(5);
	}

	public String randomLongString() {
	    return generateRandomAlphabeticString(10);
	}

	private String generateRandomAlphabeticString(int length) {
	    String characters = "abcdefghijklmnopqrstuvwxyz";
	    StringBuilder sb = new StringBuilder(length);
	    java.util.Random random = new java.util.Random();
	    for (int i = 0; i < length; i++) {
	        sb.append(characters.charAt(random.nextInt(characters.length())));
	    }
	    return sb.toString();
	}
	
	public String captureScreen(String tname) throws IOException {

		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
				
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath=System.getProperty("user.dir")+"/screenshots/" + tname + "_" + timeStamp + ".png";
		File targetFile=new File(targetFilePath);
		
		sourceFile.renameTo(targetFile);
			
		return targetFilePath;

	}
	
}

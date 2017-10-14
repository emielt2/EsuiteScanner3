/*
	This is the Geb configuration file.
	
	See: http://www.gebish.org/manual/current/#configuration
*/


import geb.Browser
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.firefox.FirefoxDriver
//import org.openqa.selenium.phantomjs.PhantomJSDriver

waiting {
	timeout = 2
}
//------------------------------------------------------------------------------------------



//import com.gargoylesoftware.htmlunit.BrowserVersion
//import com.gargoylesoftware.htmlunit.WebClient
import org.openqa.selenium.Platform
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.firefox.FirefoxProfile
//import org.openqa.selenium.htmlunit.HtmlUnitDriver
import org.openqa.selenium.ie.InternetExplorerDriver
import org.openqa.selenium.remote.CapabilityType
import org.openqa.selenium.remote.DesiredCapabilities

import java.util.logging.Level
import java.util.logging.Logger

/*
1) For testing on different browsers, On Edit Configurations provide VM Options = -ea -Dgeb.env=<BROWSER_NAME>
2) For testing from command prompt by using maven command, run the command :
   mvn -Dgeb.env=<BROWSER_NAME> -Dtest=<TEST_CASE_NAME_WITHOUT_EXTENSION > test

Note: <BROWSER_NAME> = "firefox" or "chrome" or "ie" or "htmlunit"
      <TEST_CASE_NAME_WITHOUT_EXTENSION> example: eSuite_MP_CON_09_Zoek_in_kennisbank_PDC                        */

//FUNCTIONAL SETTINGS
def final DEFAULT_BROWSER = "chrome"                            //"chrome" or "firefox" or "ie" or "htmlunit"
def final BASE_URL = "https://dmidoffice5.sdmc.ao-srv.com/mp"
def final BASE_URL_BEHEER = "https://dmidoffice7.sdmc.ao-srv.com/beheer"
def final DEFAULT_LANGUAGE = "nl"                                       //"en" or "nl"
def final REPORT_DIR = 'target/reports'
def final DEFAULT_DOWNLOAD_PATH = "C://Users/IEUser/Downloads/"

//TECHNICAL SETTINGS
def final WAITFOR_TIMEOUT = 4
def final WAITFOR_RETRY = 0.5
def final WAIT_AT_KEYWORD_AT = true
def final WAIT_TIME = 2000


def browser = System.getProperty("geb.env")
//Default browser
if (!correctBrowser(browser)) {
	browser = DEFAULT_BROWSER
}

def envLang = System.getProperty("geb.env.lang")
//Default language
if (!correctLanguage(envLang)) {
	envLang = DEFAULT_LANGUAGE
}

System.setProperty("geb.env.lang", envLang)
System.setProperty("geb.build.baseUrl", BASE_URL);
System.setProperty("geb.build.beheer.baseUrl", BASE_URL_BEHEER);
System.setProperty("geb.wait.time", WAIT_TIME.toString());
System.setProperty("geb.download.path", DEFAULT_DOWNLOAD_PATH);
if (Platform.LINUX.equals(Platform.getCurrent())) {
	System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver-2.15-linux64");
} else {
	//println "this is setproperty in gebconfig"
	System.setProperty("webdriver.chrome.driver", "F:\\SeleniumDownloadFolder\\chromedriver_win32\\chromedriver.exe");
}
System.setProperty("webdriver.ie.driver", "src/test/resources/drivers/IEDriverServer-2.45.0_Win32_.exe");

environments {

	driver = { getDriver(browser, envLang) }

}

/**
 * Returns the browser specific WebDriver
 * @param browser Browser name
 * @return WebDriver
 */
public WebDriver getDriver(def browser, def String language) {

	if ("chrome".equalsIgnoreCase(browser)) {
		println "this is webdriver stuff in MAIN/gebconfig"//ETADD
		DesiredCapabilities chromeCapabilities = DesiredCapabilities.chrome();
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("user-data-dir=Z:\\Browser_profile");//ETADD
		Map<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("intl.accept_languages", language);
		chromeOptions.setExperimentalOption("prefs", prefs);
		chromeCapabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions)
		def chromeDriver = new ChromeDriver(chromeCapabilities)
		chromeDriver

	} else if ("firefox".equalsIgnoreCase(browser)) {

		FirefoxProfile profile = new FirefoxProfile()
		profile.setPreference("intl.accept_languages", language)
		def firefoxDriver = new FirefoxDriver(profile)
		firefoxDriver.manage().window().maximize()
		firefoxDriver

	} else if ("ie".equalsIgnoreCase(browser)) {

		/**
		 * For InternetExplorer: In order to set the browser language to 'EN' or 'NL',
		 *  Go to, Settings -> Internet Options -> General ->
		 *  - Click on the button 'Languages'
		 *  - In the Language text box,
		 *    Add the desired language "Dutch (Netherlands) [nl-NL]" or "English (United States) [en-US]"
		 *    and move it up by clicking 'Move up' button
		 *
		 */
		DesiredCapabilities ieCapabilities = DesiredCapabilities.internetExplorer()
		ieCapabilities.setCapability(InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING, false)
		ieCapabilities.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, false)
		ieCapabilities.setCapability(InternetExplorerDriver.UNEXPECTED_ALERT_BEHAVIOR, true)
		ieCapabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true)
		ieCapabilities.setCapability(CapabilityType.HAS_NATIVE_EVENTS, true)
		ieCapabilities.setCapability(InternetExplorerDriver.ENABLE_ELEMENT_CACHE_CLEANUP, true);
		ieCapabilities.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
		ieCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true)
		ieCapabilities.setJavascriptEnabled(true);
		def ieDriver = new InternetExplorerDriver(ieCapabilities)
		ieDriver.manage().window().maximize()
		ieDriver

	}

}

/**
 * Checks if the browser name provided is the correct one
 * @param browser Browser name
 * @return boolean
 */
private boolean correctBrowser(def browser) {
	boolean result = true;
	if (null == browser || (!"chrome".equalsIgnoreCase(browser) && !"firefox".equalsIgnoreCase(browser) && !"ie".equalsIgnoreCase(browser) && !"htmlunit".equalsIgnoreCase(browser))) {
		result = false;
	}
	return result;
}

/**
 * Check if the language provided is the correct one
 * @param lang Language
 * @return boolean
 */
private boolean correctLanguage(def lang) {
	boolean result = true;
	if (null == lang || (!"en".equalsIgnoreCase(lang) && !"nl".equalsIgnoreCase(lang))) {
		result = false;
	}
	return result;
}



waiting {
	timeout = WAITFOR_TIMEOUT
	retryInterval = WAITFOR_RETRY
}

atCheckWaiting = WAIT_AT_KEYWORD_AT
reportsDir = REPORT_DIR
//--------------------------------------------------------------------------------------------
environments {
	
	// run via “./gradlew chromeTest”
	// See: http://code.google.com/p/selenium/wiki/ChromeDriver
	chrome {
		ChromeOptions chromeoptions = new ChromeOptions();
		chromeoptions.addArguments("user-data-dir=Z:\\Browser_profile");
		chromeoptions.addArguments("start-maximized");
		//def browser1 = new Browser(driver: new ChromeDriver(chromeoptions), baseUrl: 'http://nu.nl')
		println "environment Chrome"
		driver = { new ChromeDriver(chromeoptionsx) }
	}
	
	// run via “./gradlew firefoxTest”
	// See: http://code.google.com/p/selenium/wiki/FirefoxDriver
	firefox {
		driver = { new FirefoxDriver() }
		println "environment FireFox"
	}

//    phantomJs {
//        driver = { new PhantomJSDriver() }
//		println "environment PhantomJSDriver"
//    }

}

// To run the tests with all browsers just run “./gradlew test”

//baseUrl = "http://xgebish.org"//deze wordt gebruikt door de ProcessBuilder!
baseUrl = "about:blank"//deze wordt gebruikt door de ProcessBuilder!
WebDriver gebconfDriver;

//set the report output directory
reportsDir = "reports/"
/*
import org.openqa.selenium.chrome.ChromeDriver
driver = {
	System.setProperty('webdriver.chrome.driver', 'F:\\SeleniumDownloadFolder\\chromedriver_win32\\chromedriver.exe')
	new ChromeDriver()

	//ChromeOptions chromeoptions = new ChromeOptions();
    //    chromeoptions.addArguments("user-data-dir=Y:\\Browser_profile");
    //    def browser = new Browser(driver: new ChromeDriver(chromeoptions), baseUrl: 'http://gebish.org')
}
*/
//static def browser2 = new Browser(driver: new ChromeDriver(), baseUrl: 'http://nu.nl')//(new ChromeDriver());

/**INFO ENVVIRONMENT UITZOEKEN misschien daardoor crossed browser = nee komt door nieuwe brower.drive
 */
package ES3;

import geb.Browser
import geb.Configuration
import javafx.beans.property.Property
import org.openqa.selenium.By
import org.openqa.selenium.NoSuchElementException
import org.openqa.selenium.Platform;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.interactions.Actions;

public class GroovyBrowserDaoES3 {
    static String baseUrl
    static Browser browser2
    private static StringBuffer verificationErrors = new StringBuffer();
    public static String shellReturnString01

    public GroovyBrowserDaoES3(String input) {
        baseUrl = input
    }

    public static void startSeleniumConnection(String inputUrl) throws Exception {
        System.out.println("Check1 " + baseUrl);

        ChromeOptions chromeoptions = new ChromeOptions();
        if (Platform.LINUX.equals(Platform.getCurrent())) {

            System.setProperty("webdriver.chrome.driver", "resources/chromedriver")
            chromeoptions.addArguments("user-data-dir=resources/Browser_profileManual");
            chromeoptions.addArguments("disable-infobars");
        } else {
            chromeoptions.addArguments("user-data-dir=resources/Browser_profileManual");
            if (new File("resources/chromedriver.exe").exists()) {
                System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe")
            } else System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe")
        }


        //public Browser(Map props, Configuration config) {
        Map newmap = new TreeMap();



        newmap.put("driver",new ChromeDriver(chromeoptions));
        Configuration configuration = new Configuration();
        configuration.baseUrl = inputUrl;
        browser2 = new Browser(newmap,configuration);

        //browser2 = new Browser(driver: new ChromeDriver(chromeoptions), baseUrl: inputUrl)
        browser2.go()
    }

    String[] doGebSpockActionOnShell(String elementString, String bystring, String selectorString, String actionstring, String contentNameString, String choiceBrowser) {
        String[] returnvalue = ["Error!", "Error!", "Error!", "Error!"]
        try {
            //println "StartManual"
            String scriptBegin;
            String scriptMid;
            String scriptEnd;
            if (elementString == "") {
                scriptBegin = new String("import ES3.GroovyBrowserDaoES3;import org.openqa.selenium.By;public class test{public static void main(String...args){GroovyBrowserDaoES3 gb = new GroovyBrowserDaoES3();gb.shellReturnString01 = gb.browser2.getDriver().");
                scriptMid = new String("findElement(By." + bystring + "(\"" + selectorString + "\"))." + actionstring + ".toString();");
                scriptEnd = new String("}}");
            }
            String scriptTotal = scriptBegin + scriptMid + scriptEnd;
            returnvalue[0] = "@FindBy(" + bystring + " = \"" + selectorString + "\")\n" +
                    "public WebElement " + contentNameString + ";"
            returnvalue[1] = scriptMid //india style
            returnvalue[1] = "xPage." + contentNameString + "." + actionstring
            returnvalue[2] = shellReturnString01 //for scenetitle2 like information or exceptions
            returnvalue[3] = "hallo" //voor scenetitle2
            Binding binding = new Binding()
            GroovyShell shell = new GroovyShell(binding)
            shell.evaluate(scriptTotal)
        }
        catch (Exception e) {
            returnvalue[2] = e.message.toString();

            if (returnvalue[2].contains("no such element: Unable to locate element")) {
                returnvalue[3] = "Failed to locate"
            } else returnvalue[3] = "Exception occurred"

            if (returnvalue[2].contains("Cannot invoke method getDriver() on null object")) {
                returnvalue[2] = returnvalue[2] + " (Browser session is not started!)."
            }
            return returnvalue
        }


        returnvalue[2] = GroovyBrowserDaoES3.shellReturnString01;
        returnvalue[3] = "Action successful"
        return returnvalue
    }

    static void stopSeleniumConnection() throws Exception {
        browser2.close()
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            //fail(verificationErrorString)
        }
    }

    public String getSelectorText(String stringcss1, String stringcss2) {
        try {
            String returnvalue = "startvaluex"
            browser2.drive
            browser2.drive {
                try {
                    returnvalue = browser2.getDriver().findElement(new By.ByCssSelector(stringcss1)).getAttribute(stringcss2);
                    //print browser2.getDriver().findElement(new By.ByCssSelector(stringcss1)).getAttribute(stringcss2);
                }
                catch (Exception e) {
                    returnvalue = e.message.toString()
                }
            }
            return returnvalue
        }
        catch (NoSuchElementException e) {
            println "NoSuchElementException"
            return e.message.toString()
        }
    }

    public void mouseOver(String cssstring1, String choiceBrowser) {
        Actions builder = new Actions(driver)
        try {
            builder.moveToElement(driver.findElement(new By.ByCssSelector(cssstring1))).perform();
        } catch (Exception e) {
            System.out.println(e.getMessage())
        }
    }

    public static String getThis(String stringcss1, String stringcss2) {
        String returnvalue
        browser2.drive {
            try {
                returnvalue = browser2.$(stringcss1).getProperties()
            }
            catch (Exception e) {
                return e.message.toString()
            }
        }
        return returnvalue
    }

    public void mouseClick(String cssstring1, String choiceBrowser) {
        try {
            browser2.getDriver().findElement(By.cssSelector("" + cssstring1 + "")).click();
        } catch (Exception e) {
            System.out.println(e.getMessage())
        }
    }
}

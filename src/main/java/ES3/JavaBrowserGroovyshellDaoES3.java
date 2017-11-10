package ES3;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.Platform;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import geb.Browser;
import geb.Configuration;
import groovy.lang.Binding;
import groovy.lang.GroovyShell;

public class JavaBrowserGroovyshellDaoES3 {
    static String baseUrl;
    static Browser browser2;
    private static StringBuffer verificationErrors = new StringBuffer();
    public static String shellReturnString01;

    public JavaBrowserGroovyshellDaoES3(String input) {
        baseUrl = input;
    }

    public static void startSeleniumConnection(String inputUrl) throws Exception {
        System.out.println("Opening URL: " + baseUrl);
        ChromeOptions chromeoptions = new ChromeOptions();
        if (Platform.LINUX.equals(Platform.getCurrent())) {
            System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
            //chromeoptions.addArguments("user-data-dir=resources/Browser_profileManual");
            chromeoptions.addArguments("disable-infobars");

        } else {
            //chromeoptions.addArguments("user-data-dir=resources/Browser_profileManual");
            System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        }
        final Map<String, Object> prefs = new HashMap();
        prefs.put("intl.accept_languages", "nl");
        chromeoptions.setExperimentalOption("prefs", prefs);
        Map<String, Object> newmap = new TreeMap<>();
        newmap.put("driver", new ChromeDriver(chromeoptions));
        Configuration configuration = new Configuration();
        configuration.setBaseUrl(inputUrl);
        browser2 = new Browser(newmap, configuration);
        browser2.go();
    }

    String[] doGebSpockActionOnShell(String bystring, String selectorString, String actionstring, String contentNameString, String shellChooseMid) {
        String[] returnvalue = {"Error!", "Error!", "Error!", "Error!"};
        try {
            shellReturnString01="";
            String scriptBegin = new String();
            String scriptMid = new String();
            String scriptEnd = new String();

            scriptBegin = new String("package ES3;import java.util.concurrent.TimeUnit;import org.openqa.selenium.interactions.Actions;import org.openqa.selenium.WebElement;import org.openqa.selenium.WebDriver;import ES3.JavaBrowserGroovyshellDaoES3;import org.openqa.selenium.By;public class Test{public static void main(String...args){");
            scriptMid = new String("JavaBrowserGroovyshellDaoES3 gb = new JavaBrowserGroovyshellDaoES3(); gb.shellReturnString01 = gb.browser2.getDriver().findElement(By." + bystring + "(\"" + selectorString + "\"))." + actionstring + "; ");//toString() gebeurt hier eigenlijk
//            actionstring = "size()";
            if (actionstring.equals("size()")) {
                scriptMid = new String("JavaBrowserGroovyshellDaoES3 gb = new JavaBrowserGroovyshellDaoES3(); gb.shellReturnString01 = gb.browser2.getDriver().findElements(By." + bystring + "(\"" + selectorString + "\"))." + actionstring + "; ");
            }
            if (shellChooseMid.equals("switchToZero")) {
                scriptMid = new String("JavaBrowserGroovyshellDaoES3 gb = new JavaBrowserGroovyshellDaoES3(); gb.shellReturnString01 = gb.browser2.getDriver().switchTo().frame(0); ");
            }
            if (shellChooseMid.equals("switchToNewForm")) {
                scriptMid = new String("JavaBrowserGroovyshellDaoES3 gb = new JavaBrowserGroovyshellDaoES3(); gb.shellReturnString01 = gb.browser2.getDriver().switchTo().defaultContent();" +
                        "final Set stWndHndls=gb.browser2.getDriver().getWindowHandles();" +
                        "final Iterator it=stWndHndls.iterator();" +
                        "it.next();" +
                        "final String secondWindowHndl=(String) it.next();" +
                        "gb.browser2.getDriver().switchTo().window(secondWindowHndl);\n");
            }
            if (shellChooseMid.equals("JAVACODE")) {
                scriptMid = new String("JavaBrowserGroovyshellDaoES3 gb = new JavaBrowserGroovyshellDaoES3(\"x\");WebDriver webdriver =gb.browser2.getDriver();  String output = new String(); " + selectorString + " \ngb.shellReturnString01 = output;");
            }
            scriptEnd = new String("}}");

            if(ES3_GUI_JAVA.loggingConsoleOutput)System.out.println("Complete script\n-------------\n" + scriptBegin + scriptMid + scriptEnd + "\n----------------");
            String scriptTotal = scriptBegin + scriptMid + scriptEnd;
            Binding binding = new Binding();
            GroovyShell shell = new GroovyShell(binding);
            shell.evaluate(scriptTotal);

            returnvalue[0] = "@FindBy(" + (bystring.equals("cssSelector") ? "css" : bystring) + " = \"" + selectorString + "\")\n" +
                    "public WebElement " + contentNameString + ";";
            returnvalue[0] = returnvalue[0].replace("\\$", "$");
            returnvalue[1] = "xPage." + contentNameString + "." + actionstring;
            if(shellChooseMid.equals("sizeonly"))returnvalue[2] = shellReturnString01;
            else returnvalue[2] = prettyDateReportLines()+shellReturnString01;
            returnvalue[3] = "return3"; //voor scenetitle2
        } catch (Exception e) {
            returnvalue[2] = prettyDateReportLines()+shellReturnString01+"\nException occurred:\n"+e.getMessage();
            if (returnvalue[2].contains("no such element: Unable to locate element")) {
                returnvalue[3] = "Failed to locate";
            } else returnvalue[3] = "Exception occurred";
            if (returnvalue[2].contains("Cannot invoke method getDriver() on null object")) {
                returnvalue[2] = returnvalue[2] + " (Browser session is not started!).";
            }
            return returnvalue;
        }
        returnvalue[3] = "Action successful";
        return returnvalue;
    }

    String[][] generatorXpathToCSS(String bystring, String selectorString, String actionstring, String contentNameString) {
        String[] returnvalue = {"Error!", "Error!", "Error!", "Error!"};
        String outerHTML = shellReturnString01;
        ArrayList<String> parts = new ArrayList<>();
        outerHTML = outerHTML.substring(1, outerHTML.indexOf(">"));
        //String tagElement = outerHTML.substring(0, outerHTML.indexOf(" "));
        String tagElement = outerHTML.substring(0, outerHTML.indexOf(" "));
        outerHTML = outerHTML.substring(0 + tagElement.length() + 1, outerHTML.length());
        Matcher m = Pattern.compile("[A-z-]*=\"[:_?/A-z0-9.,s -()-]*\"").matcher(outerHTML);

        while (m.find()) {
            if (!(m.group().contains("class=") && (m.group().contains("ng-untouched") || m.group().contains("ng-touched") || m.group().contains("ng-pristine") || m.group().contains("ng-dirty") || m.group().contains("ng-invalid") || m.group().contains("ng-invalid")))) {
                String newPart = m.group();
                if ((newPart.length() - newPart.replace("'", "").length()) + (newPart.length() - newPart.replace("\"", "").length()) >= 4) {
                    newPart = newPart.replaceAll("([^\\\\])([\"])", "$1\\\\\"");
                } else {
                    newPart = newPart.replaceAll("\"", "'");
                }
                parts.add(newPart);
            }
        }
        List<String> binList = new ArrayList<>();
        int amountElements = parts.size();
        int totalCombinations = (int) Math.pow(2, amountElements);
        int MAXRESULTS = 0;
        try {
            MAXRESULTS = Integer.parseInt(ES3_GUI_JAVA.loadProperties().getProperty("maxresults"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        for (int i = 1; i < totalCombinations && i < MAXRESULTS + 2; i++) {
            binList.add(String.format("%0" + amountElements + "d", Integer.parseInt(Integer.toBinaryString(i))));
        }
        String allCssCombinations[][] = new String[binList.size()][3];
        try {
            for (int x = 0; x < allCssCombinations.length; x++) {
                StringBuilder newString = new StringBuilder(tagElement);
                for (int i = 0; i < amountElements; i++) {
                    if (binList.get(x).charAt(i) == '1') {
                        newString.append("[").append(parts.get(i)).append("]");
                    }
                }
                allCssCombinations[x][0] = newString.toString();
            }
            for (int i = 0; i < allCssCombinations.length - 1; i++) {
                String cssTry = allCssCombinations[i][0];
                cssTry = cssTry.replace("$", "\\$");
                Integer teller = Integer.parseInt(doGebSpockActionOnShell("cssSelector", cssTry, "size()", "", "sizeonly")[2]);
                if(ES3_GUI_JAVA.loggingConsoleOutput)System.out.printf("%20s %s",(teller == 0 ? "Found 0 items" : teller == 1 ? "Found 1 item" : "Found more than 1")    ,allCssCombinations[i][0]);
                String result[] = doGebSpockActionOnShell("cssSelector", cssTry, "getAttribute(\"outerHTML\")", "x", "normalShell");
                allCssCombinations[i][1] = result[3];
                allCssCombinations[i][2] = "" + teller;
            }

            for (int i = 0; i < allCssCombinations.length - 1; i++) {
                if(ES3_GUI_JAVA.loggingConsoleOutput)System.out.printf("%s   %s\n", allCssCombinations[i][0], allCssCombinations[i][1]);
            }
        } catch (Exception e) {
            e.printStackTrace();
            returnvalue[2] = e.getMessage();
            if (returnvalue[2].contains("no such element: Unable to locate element")) {
                returnvalue[3] = "Failed to locate";
            } else returnvalue[3] = "Exception occurred";
            if (returnvalue[2].contains("Cannot invoke method getDriver() on null object")) {
                returnvalue[2] = returnvalue[2] + " (Browser session is not started!).";
            }
        }
        returnvalue[3] = "Action successful";
        return allCssCombinations;
    }

    String prettyDateReportLines(){
        return "Results/output (time is "+new SimpleDateFormat("HH:mm:ss.SSS").format(new Date())+"):\n-----------------------------------------------------\n";
    }

    static void stopSeleniumConnection() throws Exception {
        browser2.close();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
        }
    }
}

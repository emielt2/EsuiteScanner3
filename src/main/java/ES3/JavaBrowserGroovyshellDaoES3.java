package ES3;

import geb.Browser;
import geb.Configuration;
import groovy.lang.Binding;
//import groovy.lang.Closure;
import groovy.lang.GroovyShell;
//import javafx.beans.property.Property;
//import org.openqa.selenium.By;
//import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Platform;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
//import org.openqa.selenium.interactions.Actions;

import java.io.File;
//import java.io.FileOutputStream;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
            System.setProperty("webdriver.chrome.driver", "resources/chromedriver");
            chromeoptions.addArguments("user-data-dir=resources/Browser_profileManual");
            chromeoptions.addArguments("disable-infobars");
        } else {
            chromeoptions.addArguments("user-data-dir=resources/Browser_profileManual");
            if (new File("resources/chromedriver.exe").exists()) {
                System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");
            } else System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        }
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
            String scriptBegin = new String();
            String scriptMid = new String();
            String scriptEnd = new String();

            scriptBegin = new String("package ES3;import ES3.JavaBrowserGroovyshellDaoES3;import org.openqa.selenium.By;public class Test{public static void main(String...args){JavaBrowserGroovyshellDaoES3 gb = new JavaBrowserGroovyshellDaoES3(); gb.shellReturnString01 = gb.browser2.getDriver().");
            scriptMid = new String("findElement(By." + bystring + "(\"" + selectorString + "\"))." + actionstring + "; ");//toString() gebeurt hier eigenlijk
//            actionstring = "size()";
            if (actionstring.equals("size()")) {
                scriptMid = new String("findElements(By." + bystring + "(\"" + selectorString + "\"))." + actionstring + "; ");
            }
            if (shellChooseMid.equals("switchToZero")) {
                scriptMid = new String("switchTo().frame(0); ");
            }
            if (shellChooseMid.equals("switchToNewForm")) {
                scriptMid = new String("switchTo().defaultContent();" +
                        "final Set stWndHndls=gb.browser2.getDriver().getWindowHandles();" +
                        "final Iterator it=stWndHndls.iterator();" +
                        "it.next();" +
                        "final String secondWindowHndl=(String) it.next();" +
                        "gb.browser2.getDriver().switchTo().window(secondWindowHndl);\n");
            }

            scriptEnd = new String("}}");

            System.out.println("Complete script\n-------------\n" + scriptBegin + scriptMid + scriptEnd + "\n----------------");
            String scriptTotal = scriptBegin + scriptMid + scriptEnd;
            Binding binding = new Binding();
            GroovyShell shell = new GroovyShell(binding);
            shell.evaluate(scriptTotal);
            returnvalue[2] = shellReturnString01;
            returnvalue[0] = "@FindBy(" + bystring + " = \"" + selectorString + "\")\n" +
                    "public WebElement " + contentNameString + ";";
            returnvalue[1] = scriptMid; //india style
            returnvalue[1] = "xPage." + contentNameString + "." + actionstring;
            returnvalue[3] = "hallo"; //voor scenetitle2
        } catch (Exception e) {
            returnvalue[2] = e.getMessage();
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

    /**
     *
     *
     */


    String[][] generatorXpathToCSS(String bystring, String selectorString, String actionstring, String contentNameString) {
        String[] returnvalue = {"Error!", "Error!", "Error!", "Error!"};


        System.out.println("shellReturnString01 OUTPUT GEEFT:" + shellReturnString01);
        String outerHTML = shellReturnString01;
        ArrayList<String> parts = new ArrayList<>();

        outerHTML = outerHTML.substring(1, outerHTML.length() - 1);
        System.out.println("outerHTML1:     ___" + outerHTML + "___");
        String tagElement = outerHTML.substring(0, outerHTML.indexOf(" "));
        System.out.println("tagElement:     ___" + tagElement + "___");
        outerHTML = outerHTML.substring(0 + tagElement.length() + 1, outerHTML.length());
        System.out.println(outerHTML);
        outerHTML = outerHTML.replaceAll("\"", "'");
        //List<String> allMatches = new ArrayList<String>();
        Matcher m = Pattern.compile("[A-z-]*='[:_?/A-z0-9.,s -()-]*'").matcher(outerHTML);
        while (m.find()) {
            if (!(m.group().contains("class=") && (m.group().contains("ng-untouched") || m.group().contains("ng-touched") || m.group().contains("ng-pristine") || m.group().contains("ng-dirty") || m.group().contains("ng-invalid") || m.group().contains("ng-invalid")))) {
                parts.add(m.group());
            }
        }
        System.out.println(parts.toString());
        System.out.println("Total size parts = " + parts.size());
        List<String> binList = new ArrayList<>();
        int amountElements = parts.size();
        int totalCombinations = (int) Math.pow(2, amountElements);
        System.out.println("totalCombinations = " + totalCombinations);
        for (int i = totalCombinations - 1; i >= 0; i--) {
            System.out.println(" i = " + i);
            binList.add(String.format("%0" + amountElements + "d", Integer.parseInt(Integer.toBinaryString(i))));
        }

        String allCssCombinations[][] = new String[binList.size()][3];//[0] is the complete css  [1]is result fail/succes/excep 3=size
        try {
            System.out.println("totalCombinations size " + totalCombinations);
            for (int x = 0; x < totalCombinations - 1; x++) {//last value is 00000 so that's why totalcombinations-1
                StringBuilder newString = new StringBuilder(tagElement);
                System.out.println("amountElements = " + amountElements);
                for (int i = 0; i < amountElements; i++) {
                    if (binList.get(x).charAt(i) == '1') {
                        newString.append("[").append(parts.get(i)).append("]");
                    }
                }
                allCssCombinations[x][0] = newString.toString();
                System.out.println("allCssCombinations size=" + allCssCombinations.length);
                System.out.println("NEWSTRING = " + newString);
            }


            for (int i = 0; i < allCssCombinations.length - 1; i++) {

                System.out.println("\n-NEW ATTEMPT i = " + i + "----------------------------------------------------------------------------------------\n");
                String cssTry = allCssCombinations[i][0];
                cssTry = cssTry.replace("$", "\\$");
                System.out.println(cssTry);
                Integer teller = Integer.parseInt(doGebSpockActionOnShell("cssSelector", cssTry, "size()", "", "findElements")[2]);
                System.out.println(teller == 0 ? "Found 0 items" : teller == 1 ? "Found 1 item" : teller > 1 ? "Found more than 1" : "Something strange happened not 0,1,>1");

                String result[] = doGebSpockActionOnShell("cssSelector", cssTry, "getAttribute(\"outerHTML\")", "x","normalShell");
                allCssCombinations[i][1] = result[3];
                allCssCombinations[i][2] = ""+teller;
                System.out.println(result[0]);
                System.out.println(result[1]);
                System.out.println(result[2]);
                System.out.println(result[3]);
            }
//            while(outerHTML.length()>0){
//                int firstQuotation = outerHTML.indexOf("\"");
//                int secondQuotation = outerHTML.indexOf("\"",firstQuotation);
//                String foundPart = outerHTML.substring(0,outerHTML.indexOf("\"",firstQuotation));
//            }

            for (int i = 0; i < allCssCombinations.length - 1; i++) {
                System.out.printf("%s   %s\n", allCssCombinations[i][0], allCssCombinations[i][1]);
            }
//            returnvalue[2] = shellReturnString01;
//            returnvalue[0] = "@FindBy(" + bystring + " = \"" + selectorString + "\")\n" +
//                    "public WebElement " + contentNameString + ";";
//            returnvalue[1] = "xPage." + contentNameString + "." + actionstring;
//
//            returnvalue[3] = "hallo"; //voor scenetitle2

            System.out.println("Reached END");
        } catch (IndexOutOfBoundsException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception e) {
            System.out.println("EXC:" + e.getMessage());
            e.printStackTrace();
            returnvalue[2] = e.getMessage();
            if (returnvalue[2].contains("no such element: Unable to locate element")) {
                returnvalue[3] = "Failed to locate";
            } else returnvalue[3] = "Exception occurred";
            if (returnvalue[2].contains("Cannot invoke method getDriver() on null object")) {
                returnvalue[2] = returnvalue[2] + " (Browser session is not started!).";
            }
            //return returnvalue;
        }
        returnvalue[3] = "Action successful";
        //return returnvalue;
        return allCssCombinations;
    }

    static void stopSeleniumConnection() throws Exception {
        browser2.close();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            //fail(verificationErrorString)
        }
    }


}

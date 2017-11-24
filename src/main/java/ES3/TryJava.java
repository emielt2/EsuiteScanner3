package ES3;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import ES3.JavaBrowserGroovyshellDaoES3;
import org.openqa.selenium.By;

public class TryJava {
    public static void main(String... args) {
        JavaBrowserGroovyshellDaoES3 gb = new JavaBrowserGroovyshellDaoES3("x");
        WebDriver webdriver = gb.browser2.getDriver();
        String output = new String(); //Enter java or groovy code here. Use variable 'output' for returning text. Example:
        output = "This is example text \n --------------- \n";
        output = output + "Checking if element is displayed:\n";
        output = output + webdriver.findElement(By.id("huisnummer")).isDisplayed();
        gb.shellReturnString01 = output;
    }
}
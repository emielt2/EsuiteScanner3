import ES3.JavaBrowserGroovyshellDaoES3;
import org.openqa.selenium.WebDriver;
import static ES3.JavaBrowserGroovyshellDaoES3.shellReturnString01;
import org.openqa.selenium.By;

import java.util.HashMap;
import java.util.Map;

public class TempTest {
    public static void main(String... args) {
        WebDriver webdriver = new JavaBrowserGroovyshellDaoES3("x").browser2.getDriver();
        shellReturnString01 = ""+webdriver.findElement(By.id("lst-ib")).isDisplayed() +"\n";
        Map<Integer, String> testmap = new HashMap<>();
        testmap.put(5, "five");
        shellReturnString01=shellReturnString01+testmap.size();

    }
}
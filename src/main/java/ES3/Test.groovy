package ES3;

import ES3.JavaBrowserGroovyshellDaoES3;
import org.openqa.selenium.By;

public class Test {
    public static void main(String... args) {
        JavaBrowserGroovyshellDaoES3 gb = new JavaBrowserGroovyshellDaoES3("x"); String output = new String();
        output = "abc";
        for (int i = 0; i < 10; i++) {
            output = output + i;
        }
        gb.shellReturnString01 = output;
    }
}
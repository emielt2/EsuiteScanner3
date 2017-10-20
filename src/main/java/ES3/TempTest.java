package ES3;

import ES3.JavaBrowserGroovyshellDaoES3;
import org.openqa.selenium.By;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class TempTest {
    public static void main(String... args) throws ScriptException{
//        String mystring = "input[ng-change=\"cac.opNaamVanChanged('afdeling')\"]";
//        System.out.println(mystring);
//        //String newstring = mystring.replaceAll("[^\\\\][\\\"]","\"");
//        //String newstring = mystring.replaceAll("[\"]","\\\\\"");
//        String newstring = mystring.replaceAll("([^\\\\])([\"])","$1\\\\\"");
//        System.out.println(newstring );

        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("JavaScript");

        // evaluate JavaScript code
        //engine.eval("print('Hello, World')");
        engine.eval("package ES3;import ES3.JavaBrowserGroovyshellDaoES3;import org.openqa.selenium.By;public class Test{public static void main(String...args){JavaBrowserGroovyshellDaoES3 gb = new JavaBrowserGroovyshellDaoES3(); gb.shellReturnString01 = gb.browser2.getDriver().findElement(By.id(\"loginBtn\")).click(); }}\n");
        //engine.eval("print(\"HELLO\")");

    }
}
package ES3;

import ES3.JavaBrowserGroovyshellDaoES3;
import org.openqa.selenium.By

import java.util.regex.Matcher
import java.util.regex.Pattern;

public class Test {
    public static void main(String... args) {
        ArrayList<String> parts = new ArrayList<>();
        String outerHTML = "id=\"huisnummer\" name=\"huisnummer\" type=\"text\" placeholder=\"Huisnummer\" maxlength=\"20\" ng-pattern=\"\\d+[^\\s\\\\]*\" ng-blur=\"kcc.onBlur()\" ng-keypress=\"kcc.onKeypress(\$event)\" ng-model=\"kcc.kccZoekParameter.huisnummer\" class=\"ng-pristine ng-untouched ng-valid ng-empty ng-valid-pattern ng-valid-maxlength\""
        String outerHTML2 = "ng-pattern=\"\\d+[^\\s\\\\]*\" class=\"ng-pristine ng-untouched ng-valid ng-empty ng-valid-pattern ng-valid-maxlength\""
        outerHTML = outerHTML.replaceAll("\"","'");
//        Matcher m = Pattern.compile("[A-z-]*=\"[A-z0-9.s -()-]*\"")
        Matcher m = Pattern.compile("[A-z-]*='[A-z0-9.s -()-]*'")
                .matcher(outerHTML);
        while (m.find()) {
            parts.add(m.group());
        }
        System.out.println(parts.toString());
        System.out.println(parts.size());

    }
}
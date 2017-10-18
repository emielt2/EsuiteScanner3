package ES3;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestJava {
    public static void main(String[] args) {
        ArrayList<String> parts = new ArrayList<>();
       // String outerHTML = "id=\"huisnummer\" name=\"huisnummer\" type=\"text\" placeholder=\"Huisnummer\" maxlength=\"20\" ng-pattern=\"\\d+[^\\s\\\\]*\" ng-blur=\"kcc.onBlur()\" ng-keypress=\"kcc.onKeypress($event)\" ng-model=\"kcc.kccZoekParameter.huisnummer\" class=\"ng-pristine ng-untouched ng-valid ng-empty ng-valid-pattern ng-valid-maxlength\"";
        String outerHTML = "href=\"/mp/webapplication/img/favicon.ico\" maxlength=\"20\" ng-pattern=\"\\d+[^\\s\\\\]*\" ";

        //String outerHTML2 = "ng-pattern=\"\\d+[^\\s\\\\]*\" class=\"ng-pristine ng-untouched ng-valid ng-empty ng-valid-pattern ng-valid-maxlength\"";
        outerHTML = outerHTML.replaceAll("\"","'");
        //        Matcher m = Pattern.compile("[A-z-]*=\"[A-z0-9.s -()-]*\"")
        //Matcher m = Pattern.compile("[A-z-]*='[A-z0-9.s -()-]*'").matcher(outerHTML);

        Matcher m = Pattern.compile("[A-z-]*='[/A-z0-9.s -()-]*'").matcher(outerHTML);

        while (m.find()) {
            if( !(m.group().contains("class=")&&(m.group().contains("ng-untouched")||m.group().contains("ng-touched")||m.group().contains("ng-pristine")||m.group().contains("ng-dirty")||m.group().contains("ng-invalid")||m.group().contains("ng-invalid")))){
                parts.add(m.group());
            }
            System.out.println("Testing Numberfield\n" + "mandatory");

        }
        System.out.println(parts.toString());
        System.out.println(parts.size());

        outerHTML = outerHTML.replace("$", "\\"+"$");
        System.out.println(outerHTML);

        List<String> binList = new ArrayList<>();
        int amountElements = parts.size();
        int totalCombinations = (int)Math.pow(2,amountElements);
        for (int i=totalCombinations-1; i>=0; i--) {
            binList.add(String.format("%0"+amountElements+"d",Integer.parseInt(Integer.toBinaryString(i))));
        }
        String tagName = "input";

        for(int x=0;x<totalCombinations-1;x++){//last value is 00000 so that's why totalcombinations-1
            StringBuilder newString = new StringBuilder(tagName);
            for(int i=0;i<amountElements;i++){
//                System.out.println(binList.get(x));
  //              System.out.println(binList.get(x).charAt(i));//equals('1'
                if(binList.get(x).charAt(i)=='1'){
                    newString.append("[").append(parts.get(i)).append("]");
                }

            }
            System.out.println(newString);
        }

    }

}

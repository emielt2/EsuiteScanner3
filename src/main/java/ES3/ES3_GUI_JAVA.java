package ES3;

import groovyjarjarantlr.StringUtils;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Properties;



/**
 * This is EsuiteScanner 3.2
 */

public class ES3_GUI_JAVA extends Application {
    static String outputs[] = new String[5];
    Stage primStage;
    Scene sceneShellBrowser;
    String choiceShellOrManual = "Manual";//or"Shell"

    @Override
    public void start(Stage primaryStage) {
        primStage = primaryStage;
        System.out.println("STARTED EsuiteScanner3.2");
        primStage.setTitle("EsuiteScanner3.2");
        GridPane grid1 = new GridPane();
        grid1.setAlignment(Pos.TOP_LEFT);
        grid1.setHgap(5);
        grid1.setVgap(5);
        grid1.setMaxHeight(800);
        grid1.setMinHeight(800);
        grid1.setPadding(new Insets(1, 25, 25, 25));
//--experiment new scenes
        final Scene sceneManualDrive = new Scene(grid1, 1000, 950);
        GridPane grid2 = new GridPane();
        /*Scene*/
        sceneShellBrowser = new Scene(grid2, 1000, 800);
//---------------------------------
        int valueWidth = 450;

//---------------------------------
        Button button01OpenSession = new Button("Open session URL");//button01OpenSession
        Button button02CloseSession = new Button("Close session");
        final Button button03GenerateXpathToCss = new Button("TRY!");
        //Button button04MouseOver = new Button("MouseOver");
        //Button button05InfoAttribute = new Button("Attribute info");
        //Button button06MouseClick = new Button("MouseClick");
        Button button09Report = new Button("Report");
        final Button button11TrySelector = new Button("TRYx!");
        final Button button12SwitchToZero = new Button("SWITCH TO IFRAME 0 ZTC2");
        final Button button13SwitchToNewForms = new Button("SWITCH TO NEW ANGULAR FORM");
//-------------------------------
        final CheckBox checkbox1 = new CheckBox("PNG");//.setselected?
        checkbox1.setSelected(true);
        final CheckBox checkbox2 = new CheckBox("HTML");//.setselected?
        checkbox2.setSelected(true);

//-------------------------------
        final HBox hBox1url = new HBox(40);//for Connectionactions
        hBox1url.setMaxHeight(25);
        hBox1url.setMinHeight(25);
        HBox hBox2genXpathToCss = new HBox(1);//voor input1
        hBox2genXpathToCss.setMaxHeight(25);
        hBox2genXpathToCss.setMinHeight(25);
        VBox hBox3becameVBox = new VBox(2);//voor gebspock
        hBox3becameVBox.setMinHeight(150);
        hBox3becameVBox.setMaxHeight(150);
        final VBox vBox5 = new VBox(40);//voor groovyshell
        final HBox hBox5Try = new HBox(40);
        hBox5Try.setMaxHeight(25);
        hBox5Try.setMinHeight(25);
        hBox1url.setSpacing(3);
        vBox5.setSpacing(3);
        hBox1url.setMinWidth(valueWidth);
        VBox vBoxCSSGenResults = new VBox(40);//voor knoppen Driver
        vBoxCSSGenResults.setMinHeight(350);
        vBoxCSSGenResults.setMaxHeight(350);
        vBoxCSSGenResults.setMaxWidth(valueWidth);
        vBoxCSSGenResults.setMinWidth(valueWidth);
        vBoxCSSGenResults.setSpacing(5);
        VBox vBox2OutText2 = new VBox(1);//voor outText2
        VBox vBox3VarNamesCreator = new VBox(40);//voor knoppen GebSpock
        vBox3VarNamesCreator.setSpacing(5);
        vBox3VarNamesCreator.setMinHeight(160);
        vBox3VarNamesCreator.setMaxHeight(160);
        vBox3VarNamesCreator.setMaxWidth(valueWidth);
        vBox3VarNamesCreator.setMinWidth(valueWidth);
        final HBox hBox0switchboxes = new HBox(40);

//--------------------------------
        Separator sepHor1 = new Separator();
        Separator sepHor2 = new Separator();
        Separator sepHor3 = new Separator();
        Separator sepHor4 = new Separator();
        Separator sepHor5 = new Separator();
        Separator sepHor6 = new Separator();
        Separator sepVer = new Separator();//Vertical separator
        sepHor1.setMinWidth(valueWidth);
        sepHor2.setMinWidth(valueWidth);
        sepHor3.setMinWidth(valueWidth);
        sepHor4.setMinWidth(valueWidth);
        sepHor5.setMinWidth(valueWidth);
        sepHor6.setMinWidth(valueWidth);
        sepHor1.setMaxWidth(valueWidth);
        sepHor2.setMaxWidth(valueWidth);
        sepHor3.setMaxWidth(valueWidth);
        sepHor4.setMaxWidth(valueWidth);
        sepHor5.setMaxWidth(valueWidth);
        sepHor6.setMaxWidth(valueWidth);
        sepVer.setOrientation(Orientation.VERTICAL);//Vertical separator
        sepHor1.setMinHeight(10);
        sepHor1.setMaxHeight(10);
        sepHor2.setMinHeight(10);
        sepHor2.setMaxHeight(10);
        sepHor3.setMinHeight(10);
        sepHor3.setMaxHeight(10);
        sepHor4.setMinHeight(10);
        sepHor4.setMaxHeight(10);
        sepHor5.setMinHeight(10);
        sepHor5.setMaxHeight(10);
        sepHor6.setMinHeight(10);
        sepHor6.setMaxHeight(10);
        sepHor1.setStyle("-fx-background-color: grey");
        sepHor2.setStyle("-fx-background-color: grey");
        sepHor3.setStyle("-fx-background-color: grey");
        sepHor4.setStyle("-fx-background-color: grey");
        sepHor5.setStyle("-fx-background-color: black");
        sepHor6.setStyle("-fx-background-color: black");
//--------------------------------
        final TextArea outText2 = new TextArea("Last results:");
        final TextFlow outText3 = new TextFlow();
        outText3.getChildren().add(outText2);
        final String exampleCodeJava = "//Enter java or groovy code here. Use variable 'output' for returning text. Example:\n" +
                "output = \"This is example text \\n --------------- \\n\";\n" +
                "output = output + \"Checking if element is displayed:\\n\" \n" +
                "output = output + webdriver.findElement(By.id(\"huisnummer\")).isDisplayed();";
        final TextArea inputTextFieldJava = new TextArea(exampleCodeJava);
        inputTextFieldJava.setMaxWidth(450);
        inputTextFieldJava.setMinWidth(450);
        inputTextFieldJava.setMaxHeight(250);
        inputTextFieldJava.setMinHeight(250);

        final Text textDrivFindElAtt = new Text("Give XPATH to generate CSS:");
        textDrivFindElAtt.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        final TextField inputField01URL = new TextField("https://dmidoffice8.sdmc.ao-srv.com/mp");//switch

        //final TextField inputField02css2 = new TextField("outerHTML");//innerHTML was mooi.  getProperties niet goed. //displayed werkt als $(x).getproperty

        final TextField inputField02UserInputString = new TextField("huisnummer");//li.crossbrowser
        inputField02UserInputString.setMaxWidth(450);
        inputField02UserInputString.setMinWidth(450);


        final TextField inputField03ExtraUserString = new TextField("");
        inputField03ExtraUserString.setDisable(true);
        inputField03ExtraUserString.setMaxWidth(450);
        inputField03ExtraUserString.setMinWidth(450);


        final TextField inputField05css1 = new TextField("//*[@id=\"uitloggen\"]"); //Give Xpath HUISNUMMER
        inputField05css1.setMaxWidth(400);
        inputField05css1.setMinWidth(400);

        final TextField inputField04WishedVarName = new TextField("giveNiceVarNameHere");
        final TextArea outputField1ContentItem = new TextArea("click button to get new result");
        final TextField outputField1ActionText = new TextField("click button to get new result");
        final TextField outputFieldCssTops[] = new TextField[5];
        for (int i = 0; i < outputFieldCssTops.length; i++) {
            outputFieldCssTops[i] = new TextField("?");
        }
        outputField1ContentItem.setMaxHeight(55);
        outputField1ContentItem.setMinHeight(55);
        inputField01URL.setAlignment(Pos.TOP_LEFT);
        inputField01URL.setMaxWidth(240);
        inputField01URL.setMinWidth(240);
        outText2.setMinHeight(750);
        outText2.setMaxHeight(750);
        outText2.setWrapText(true);
        final TextField outputFieldResultTry = new TextField("------");
        outputFieldResultTry.setStyle("-fx-background-color: grey;");
        outputFieldResultTry.setMaxWidth(250);
        outputFieldResultTry.setMinWidth(250);

//--------------------------------

        ObservableList<String> optionsSelectors =
                FXCollections.observableArrayList(
                        "className",
                        "cssSelector",
                        "id",
                        "linkText",
                        "name",
                        "partialLinkText",
                        "tagName",
                        "xpath",
                        "JAVACODE"
                );
        final ComboBox comboBoxSelectors = new ComboBox(optionsSelectors);
        comboBoxSelectors.setMaxWidth(150);
        comboBoxSelectors.setMinWidth(150);

        comboBoxSelectors.setValue(optionsSelectors.get(2));

        ObservableList<String> optionsActions =
                FXCollections.observableArrayList(
                        "clear()",
                        "click()",
                        "getAttribute(\"STRING\")",
                        "getCssValue(\"STRING\")",
                        "getLocation()",
                        "getProperties()",
                        "getSize()",
                        "getTagName()",
                        "getText()",
                        "isDisplayed()",
                        "isEnabled()",
                        "isSelected()",
                        "sendKeys(\"STRING\")",
                        "submit()"
                );
        final ComboBox comboBoxActions = new ComboBox(optionsActions);
        comboBoxActions.setMaxWidth(150);
        comboBoxActions.setMinWidth(150);
        comboBoxActions.setValue(optionsActions.get(9));

//--------------------------------
        hBox1url.getChildren().addAll(inputField01URL, button01OpenSession, button02CloseSession);
        hBox2genXpathToCss.getChildren().addAll(inputField05css1/*, inputField02css2*/, button03GenerateXpathToCss);
        vBoxCSSGenResults.getChildren().addAll(outputFieldCssTops[0], outputFieldCssTops[1], outputFieldCssTops[2], outputFieldCssTops[3], outputFieldCssTops[4]);
        hBox3becameVBox.getChildren().addAll(comboBoxSelectors, inputField02UserInputString,/*inputField05gebAction,*/comboBoxActions, inputField03ExtraUserString, hBox5Try);
        //vBox2OutText2.getChildren().add(outText2);
        vBox2OutText2.getChildren().add(outText3);
        hBox5Try.getChildren().addAll(button11TrySelector, outputFieldResultTry);
        vBox3VarNamesCreator.getChildren().addAll(/*hBox5Try,*//*button11TrySelector, outputFieldResultTry,*/ sepHor2, inputField04WishedVarName, outputField1ContentItem, outputField1ActionText);
        vBox2OutText2.setMaxWidth(800);
        vBox2OutText2.setMinWidth(500);
        vBox2OutText2.setMaxHeight(800);
        vBox2OutText2.setMinHeight(800);
        //vBox2OutText2.setFillWidth(true);
        hBox0switchboxes.getChildren().addAll(button12SwitchToZero, button13SwitchToNewForms);

//--------------------------------
        Arrays.fill(outputs, "---");
        for (int i = 0; i < 5; i++) {
            outText2.setText(outText2.getText().concat(outputs[i]).concat("\n"));
        }
//--------------------------------
        final JavaBrowserGroovyshellDaoES3 groovybrowser = new JavaBrowserGroovyshellDaoES3(inputField01URL.getText());
//--------------------------------


//        inputField02UserInputString.setOnKeyPressed((event) -> { if(event.getCode() == KeyCode.ENTER) { button11TrySelector.fire(); } });
//        inputField03ExtraUserString.setOnKeyPressed((event) -> { if(event.getCode() == KeyCode.ENTER) { button11TrySelector.fire(); } });
//        inputField05css1.setOnKeyPressed((event) -> { if(event.getCode() == KeyCode.ENTER) { button03GenerateXpathToCss.fire(); } });


        inputField02UserInputString.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    button11TrySelector.fire();
                }
            }
        });

        inputField03ExtraUserString.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    button11TrySelector.fire();
                }
            }
        });

        inputField05css1.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    button03GenerateXpathToCss.fire();
                }
            }
        });


        inputField05css1.setOnAction(new EventHandler<ActionEvent>() {
                                         public void handle(ActionEvent e) {
                                             try {
                                                 System.out.println("Go clicked");
                                             } catch (Exception e1) {
                                                 e1.printStackTrace();
                                             }
                                         }
                                     }
        );


        button01OpenSession.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                try {
                    button01OpenSession.setDisable(true);
                    File currentWorkPath = new File(".").getCanonicalFile();
                    File resourcePath = new File(currentWorkPath + "/resources/settings.properties");
                    if (!resourcePath.exists()) {
                        resourcePath = new File(currentWorkPath + "/src/test/resources/settings.properties");
                    }
                    Properties propertiesConnection = new Properties();
                    propertiesConnection.load(new FileInputStream(resourcePath));
                    JavaBrowserGroovyshellDaoES3.startSeleniumConnection(propertiesConnection.getProperty("url"));
                    groovybrowser.doGebSpockActionOnShell("id", "username", "sendKeys(\"" + propertiesConnection.getProperty("username") + "\")", inputField04WishedVarName.getText(), "normal");
                    groovybrowser.doGebSpockActionOnShell("id", "password", "sendKeys(\"" + propertiesConnection.getProperty("password") + "\")", inputField04WishedVarName.getText(), "normal");
                    groovybrowser.doGebSpockActionOnShell("id", "loginBtn", "click()", inputField04WishedVarName.getText(), "normal");
                    button02CloseSession.setDisable(false);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });

        button02CloseSession.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                try {
                    //System.out.println("button02CloseSession clicked");
                    button02CloseSession.setDisable(true);
                    JavaBrowserGroovyshellDaoES3.stopSeleniumConnection();
                    button01OpenSession.setDisable(false);
                    outText2.setText("Connection closed.");

                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });

        comboBoxActions.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                try {
                    if (comboBoxActions.getValue().toString().contains("STRING")) {
                        inputField03ExtraUserString.setDisable(false);
                    } else {
                        inputField03ExtraUserString.setDisable(true);
                    }
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });

        comboBoxSelectors.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                try {
                    if (comboBoxSelectors.getValue().toString().contains("JAVACODE")) {

                        hBox3becameVBox.getChildren().clear();
                        hBox3becameVBox.getChildren().addAll(comboBoxSelectors,inputTextFieldJava,hBox5Try);
                        vBox3VarNamesCreator.setVisible(false);
                        inputTextFieldJava.requestFocus();
                    } else {
                        hBox3becameVBox.getChildren().clear();
                        hBox3becameVBox.getChildren().addAll(comboBoxSelectors, inputField02UserInputString,/*inputField05gebAction,*/comboBoxActions, inputField03ExtraUserString, hBox5Try);
                        vBox3VarNamesCreator.setVisible(true);
                        inputField02UserInputString.requestFocus();
                    }
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });

        button11TrySelector.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                try {
                    String requestedAction = comboBoxActions.getValue().toString();
                    if ((comboBoxSelectors.getValue().toString().contains("cssSelector") | comboBoxSelectors.getValue().toString().contains("xpath")) && !comboBoxSelectors.getValue().toString().contains("\\\"")) {

                        //int count = ;

                        if ((inputField02UserInputString.getText().length() - inputField02UserInputString.getText().replace("'", "").length()) +
                                (inputField02UserInputString.getText().length() - inputField02UserInputString.getText().replace("\"", "").length()) >= 4) {
                            inputField02UserInputString.setText(inputField02UserInputString.getText().replaceAll("([^\\\\])([\"])", "$1\\\\\""));
                            //int found=-1;
//                            while((found=inputField02UserInputString.getText().indexOf("\""))>0){
//                                if(inputField02UserInputString.getText().charAt(found-1)!='\\'){
//                                    inputField02UserInputString.setText(inputField02UserInputString.getText().replaceAll("([^\\\\])([\"])","$1\\\\\""));
//
//                                }
//                                //inputField02UserInputString.setText(inputField02UserInputString.getText().replaceAll("\"", "\\\\\""));
//                            }

                        } else {
                            inputField02UserInputString.setText(inputField02UserInputString.getText().replaceAll("\"", "'"));
                        }

                        /// System.out.println("AAAAANNNNPAASSEEEENNNNNN!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                        // inputField02UserInputString.setText(inputField02UserInputString.getText().replaceAll("\"", "\\\\\""));
                        // System.out.println("XX");
                    }
                    if (comboBoxActions.getValue().toString().contains("STRING")) {
                        // System.out.println("CHECK03a = " + requestedAction);
                        requestedAction = requestedAction.replace("STRING", inputField03ExtraUserString.getText());
                        //  System.out.println("CHECK03b = " + requestedAction);
                    }

                    //System.out.println("CHECK01 = " + requestedAction);
                    String result[] = new String[4];
                    if (!comboBoxSelectors.getValue().toString().equals("JAVACODE")) {
                        result = groovybrowser.doGebSpockActionOnShell(comboBoxSelectors.getValue().toString(), inputField02UserInputString.getText(), requestedAction, inputField04WishedVarName.getText(), "normal");
                    } else {
                        result = groovybrowser.doGebSpockActionOnShell("", inputTextFieldJava.getText(), "", "", "JAVACODE");
                    }


                    outputField1ActionText.setText(result[1]);
                    outputField1ContentItem.setText(result[0]);


                    /**
                     *  result[0] is complete text for creating the locator in a cucumber testcase
                     *  result[1] is XXX now part of script / is now showing the full line like xpage.var.click
                     *  result[2] is result of the execution, can contain returnvalues of the actions or exceptions
                     *  result[3] is Action Successful, Exception occurred, Failed to locate
                     */


                    switch (result[3]) {
                        case "Action successful":
                            outputFieldResultTry.setStyle("-fx-background-color: green;");
                            outputFieldResultTry.setText("Action successful");
                            System.out.println("SUCCCESS!!!" + result[3]);
                            break;
                        case "Exception occurred":
                            outputFieldResultTry.setStyle("-fx-background-color: red;");
                            if(comboBoxSelectors.getValue().equals("JAVACODE")){
                                outputFieldResultTry.setText("Exception occurred");
                            }
                            else  outputFieldResultTry.setText("Exception occurred (is Selector correct?)");
                            System.out.println("EXCEPTION!!!" + result[3]);
                            break;
                        case "Failed to locate":
                            outputFieldResultTry.setStyle("-fx-background-color: orange;");
                            outputFieldResultTry.setText("Failed to locate");
                            System.out.println("FAILED!!!" + result[3]);
                            break;
                    }

                    outText2.setText(result[2]);
                    System.out.println("HERE3");
                } catch (Exception e1) {

                    e1.printStackTrace();
                }
            }
        });


        button03GenerateXpathToCss.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                outText2.setText("");
                for (int i = 0; i < outputFieldCssTops.length; i++) {
                    outputFieldCssTops[i].setText("?");

                }
                try {
                    inputField05css1.setText(inputField05css1.getText().replaceAll("\"", "'"));
                    String[] result = groovybrowser.doGebSpockActionOnShell("xpath", inputField05css1.getText(), "getAttribute(\"outerHTML\")", inputField04WishedVarName.getText(), "normal");
                    for (String receivedResults : result) {
                        System.out.println(receivedResults);
                    }
                    if (result[3].equals("Action successful")) {
                        outText2.setText("buttonHANDLE SLEEPING 500ms");
                        Thread.sleep(300);
                        Thread.yield();
                        String[][] generateresult = groovybrowser.generatorXpathToCSS("xpath", inputField05css1.getText(), "getAttribute(\"outerHTML\")", inputField04WishedVarName.getText());
                        System.out.println("RESULTS BELOW FROM GUI:");
                        for (int i = 0; i < generateresult.length - 1; i++) {
                            System.out.printf("Count=%-5s %-30s %s\n", generateresult[i][2], generateresult[i][1], generateresult[i][0]);
                        }
                        outText2.setText("Results for generating Xpath to CSS:");
                        int topCounter = 0;
                        for (int i = 0; i < generateresult.length - 1; i++) {
                            if (generateresult[i][1].equals("Action successful") && generateresult[i][2].equals("1") && topCounter < outputFieldCssTops.length) {
                                outputFieldCssTops[topCounter++].setText(generateresult[i][0]);
                            }
                            outText2.setText(outText2.getText() + "\n\n" + generateresult[i][0]);
                            outText2.setText(outText2.getText() + "\n(RESULT=" + generateresult[i][1] + ")");
                            outText2.setText(outText2.getText() + "\n(COUNT=" + generateresult[i][2] + ")");
                        }

                    } else {
                        outText2.setText(result[3] + " :\n\n" + inputField05css1 + "\n\n" + result[2]);
                    }

                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });

        button12SwitchToZero.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                try {
                    String[] result = groovybrowser.doGebSpockActionOnShell("", "", "", "", "switchToZero");

                    switch (result[3]) {
                        case "Action successful":
                            outputFieldResultTry.setStyle("-fx-background-color: green;");
                            outputFieldResultTry.setText("Action successful");
                            System.out.println("SUCCCESS!!!" + result[3]);
                            break;
                        case "Exception occurred":
                            outputFieldResultTry.setStyle("-fx-background-color: red;");
                            outputFieldResultTry.setText("Exception occurred (is Selector correct?)");
                            System.out.println("EXCEPTION!!!" + result[3]);
                            break;
                        case "Failed to locate":
                            outputFieldResultTry.setStyle("-fx-background-color: orange;");
                            outputFieldResultTry.setText("Failed to locate");
                            System.out.println("FAILED!!!" + result[3]);
                            break;
                    }

                    outText2.setText(result[2]);
                    System.out.println("HERE3");
                } catch (Exception e1) {

                    e1.printStackTrace();
                }
            }
        });

        button13SwitchToNewForms.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                try {
                    String[] result = groovybrowser.doGebSpockActionOnShell("", "", "", "", "switchToNewForm");

                    switch (result[3]) {
                        case "Action successful":
                            outputFieldResultTry.setStyle("-fx-background-color: green;");
                            outputFieldResultTry.setText("Action successful");
                            System.out.println("SUCCCESS!!!" + result[3]);
                            break;
                        case "Exception occurred":
                            outputFieldResultTry.setStyle("-fx-background-color: red;");
                            outputFieldResultTry.setText("Exception occurred (is Selector correct?)");
                            System.out.println("EXCEPTION!!!" + result[3]);
                            break;
                        case "Failed to locate":
                            outputFieldResultTry.setStyle("-fx-background-color: orange;");
                            outputFieldResultTry.setText("Failed to locate");
                            System.out.println("FAILED!!!" + result[3]);
                            break;
                    }

                    outText2.setText(result[2]);
                    System.out.println("HERE3");
                } catch (Exception e1) {

                    e1.printStackTrace();
                }
            }
        });

        /*  https://stackoverflow.com/questions/44060204/javafx-label-will-not-continuously-update*/
//        Label timerLabel = new Label();
//        final Timer timer = new Timer();
//        final int[] count = {0};
//        timer.schedule(new TimerTask() { // timer task to update the seconds
//            @Override
//            public void run() {
//                // use Platform.runLater(Runnable runnable) If you need to update a GUI component from a non-GUI thread.
//                Platform.runLater(new Runnable() {
//                    public void run() {
//                        timerLabel.setText("Second : " + count[0]);
//                        count[0]++;
//                        if (count[0] >= 10) {
//                            timer.cancel();
//                        }
//                    }
//                });
//            }
//        }, 1000, 1000); //Every 1 second


/**
 * Setting content of grids
 *
 */
        int grid1leftcounter = 0;
        grid1.add(hBox1url, 0, grid1leftcounter++);//url conn
        grid1.add(vBox2OutText2, 1, 0, 1, 25);//was 1 0 1 25
        grid1.add(hBox0switchboxes, 0, grid1leftcounter++);//url conn
        grid1.add(sepHor1, 0, grid1leftcounter++);
        grid1.add(hBox3becameVBox, 0, grid1leftcounter++);
        grid1.add(vBox3VarNamesCreator, 0, grid1leftcounter++);
        grid1.add(sepHor5, 0, grid1leftcounter++);
        grid1.add(sepHor6, 0, grid1leftcounter++);
        grid1.add(textDrivFindElAtt, 0, grid1leftcounter++);
        grid1.add(hBox2genXpathToCss, 0, grid1leftcounter++);
        grid1.add(vBoxCSSGenResults, 0, grid1leftcounter);
        primStage.setScene(sceneManualDrive);
        primStage.getScene().fillProperty();
        primStage.show();
        button01OpenSession.fire();//auto start open url browser



    }

    public static void main(String[] args) {
        launch(args);
    }
}

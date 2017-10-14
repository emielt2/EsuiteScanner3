package ES3;

import javafx.application.Application;
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
import javafx.stage.Stage;
import net.sf.cglib.core.Local;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;


/**
 * This is EsuiteScanner 3.2
 */

public class ES3_GUI extends Application {
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
        final Button button03Information = new Button("Information");
        Button button04MouseOver = new Button("MouseOver");
        Button button05InfoAttribute = new Button("Attribute info");
        Button button06MouseClick = new Button("MouseClick");
        Button button09Report = new Button("Report");
        final Button button11SpockGet = new Button("TRY!");
//-------------------------------
        final CheckBox checkbox1 = new CheckBox("PNG");//.setselected?
        checkbox1.setSelected(true);
        final CheckBox checkbox2 = new CheckBox("HTML");//.setselected?
        checkbox2.setSelected(true);

//-------------------------------
        final HBox hBox1 = new HBox(40);//for Connectionactions
        hBox1.setMaxHeight(25);
        hBox1.setMinHeight(25);
        HBox hBox2 = new HBox(40);//voor input1
        hBox2.setMaxHeight(25);
        hBox2.setMinHeight(25);
        VBox hBox3becameVBox = new VBox(2);//voor gebspock
        hBox3becameVBox.setMinHeight(150);
        hBox3becameVBox.setMaxHeight(150);
        final VBox vBox5 = new VBox(40);//voor groovyshell
        final HBox hBox5Try = new HBox(40);
        hBox5Try.setMaxHeight(25);
        hBox5Try.setMinHeight(25);
        hBox1.setSpacing(3);
        vBox5.setSpacing(3);
        hBox1.setMinWidth(valueWidth);
        VBox vBox1 = new VBox(40);//voor knoppen Driver
        vBox1.setMinHeight(350);
        vBox1.setMaxHeight(350);
        vBox1.setMaxWidth(valueWidth);
        vBox1.setMinWidth(valueWidth);
        vBox1.setSpacing(5);
        VBox vBox2 = new VBox(40);//voor outText2
        VBox vBox3 = new VBox(40);//voor knoppen GebSpock
        vBox3.setSpacing(5);
        vBox3.setMinHeight(160);
        vBox3.setMaxHeight(160);
        vBox3.setMaxWidth(valueWidth);
        vBox3.setMinWidth(valueWidth);

//--------------------------------
        Separator sepHor1 = new Separator();
        Separator sepHor2 = new Separator();
        Separator sepHor3 = new Separator();
        Separator sepHor4 = new Separator();
        Separator sepHor5 = new Separator();
        Separator sepVer = new Separator();//Vertical separator
        sepHor1.setMinWidth(valueWidth);
        sepHor2.setMinWidth(valueWidth);
        sepHor3.setMinWidth(valueWidth);
        sepHor4.setMinWidth(valueWidth);
        sepHor5.setMinWidth(valueWidth);
        sepHor1.setMaxWidth(valueWidth);
        sepHor2.setMaxWidth(valueWidth);
        sepHor3.setMaxWidth(valueWidth);
        sepHor4.setMaxWidth(valueWidth);
        sepHor5.setMaxWidth(valueWidth);
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
        sepHor1.setStyle("-fx-background-color: grey");
        sepHor2.setStyle("-fx-background-color: grey");
        sepHor3.setStyle("-fx-background-color: grey");
        sepHor4.setStyle("-fx-background-color: grey");
        sepHor5.setStyle("-fx-background-color: grey");
//--------------------------------
        final TextArea outText2 = new TextArea("Last results:");
        final Text textDrivFindElAtt = new Text("CSS experiment below:");
        textDrivFindElAtt.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        final TextField urlInputField = new TextField("https://dmidoffice8.sdmc.ao-srv.com/mp");//switch
        final TextField inputField01css1 = new TextField("img[ class=\"logo\"]");
        final TextField inputField02css2 = new TextField("outerHTML");//innerHTML was mooi.  getProperties niet goed. //displayed werkt als $(x).getproperty
        final TextField inputField03AgebElement = new TextField("");
        final TextField inputField04gebString = new TextField("huisnummer");//li.crossbrowser
        inputField04gebString.setMaxWidth(450);
        inputField04gebString.setMinWidth(450);
        final TextField inputField06gebContentName = new TextField("giveNiceVarNameHere");
        final TextField inputFieldExtraString = new TextField("");
        inputFieldExtraString.setDisable(true);
        inputFieldExtraString.setMaxWidth(450);
        inputFieldExtraString.setMinWidth(450);
        final TextArea outputField1ContentItem = new TextArea("click button to get new result");
        final TextField outputField1ActionText = new TextField("click button to get new result");
        outputField1ContentItem.setMaxHeight(55);
        outputField1ContentItem.setMinHeight(55);
        urlInputField.setAlignment(Pos.TOP_LEFT);
        urlInputField.setMaxWidth(240);
        urlInputField.setMinWidth(240);
        outText2.setMinHeight(750);
        outText2.setMaxHeight(750);
        outText2.setWrapText(true);
        final javafx.scene.control.TextField outputFieldResultTry = new TextField("------");
        outputFieldResultTry.setStyle("-fx-background-color: grey;");
        outputFieldResultTry.setMaxWidth(150);
        outputFieldResultTry.setMinWidth(150);

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
                        "xpath"
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
        hBox1.getChildren().addAll(urlInputField, button01OpenSession, button02CloseSession);
        hBox2.getChildren().addAll(inputField01css1, inputField02css2);
        vBox1.getChildren().addAll(button03Information, button04MouseOver, button05InfoAttribute, button06MouseClick);
        hBox3becameVBox.getChildren().addAll(comboBoxSelectors, inputField04gebString,/*inputField05gebAction,*/comboBoxActions, inputFieldExtraString,hBox5Try);
        vBox2.getChildren().add(outText2);
        hBox5Try.getChildren().addAll(button11SpockGet,outputFieldResultTry);
        vBox3.getChildren().addAll(/*hBox5Try,*//*button11SpockGet, outputFieldResultTry,*/ sepHor2, inputField06gebContentName, outputField1ContentItem, outputField1ActionText);
        vBox2.setMaxWidth(800);
        vBox2.setMinWidth(500);
        vBox2.setMaxHeight(800);
        vBox2.setMinHeight(800);
        vBox2.setFillWidth(true);

//--------------------------------
        Arrays.fill(outputs, "---");
        for (int i = 0; i < 5; i++) {
            outText2.setText(outText2.getText().concat(outputs[i]).concat("\n"));
        }
//--------------------------------
        final GroovyBrowserDaoES3 groovybrowser = new GroovyBrowserDaoES3(urlInputField.getText());
//--------------------------------


//        inputField04gebString.setOnKeyPressed((event) -> { if(event.getCode() == KeyCode.ENTER) { button11SpockGet.fire(); } });
//        inputFieldExtraString.setOnKeyPressed((event) -> { if(event.getCode() == KeyCode.ENTER) { button11SpockGet.fire(); } });
//        inputField01css1.setOnKeyPressed((event) -> { if(event.getCode() == KeyCode.ENTER) { button03Information.fire(); } });


        inputField04gebString.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode() == KeyCode.ENTER) { button11SpockGet.fire(); }
            }
        });

        inputFieldExtraString.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode() == KeyCode.ENTER) { button11SpockGet.fire(); }
            }
        });
        inputField04gebString.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode() == KeyCode.ENTER) { button03Information.fire();  }
            }
        });



        inputField01css1.setOnAction(new EventHandler<ActionEvent>() {
                                         public void handle(ActionEvent e) {
                                             try {
                                                 System.out.println("Go clicked");
                                             } catch (Exception e1) {
                                                 e1.printStackTrace();
                                             }
                                         }
                                     }
        );

        inputField02css2.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                try {
                 //  System.out.println("Go clicked");
                } catch (Exception e1) {
                    e1.printStackTrace();
                }

            }
        });


        button01OpenSession.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                try {
                    button01OpenSession.setDisable(true);

                    File currentWorkPath = new File(".").getCanonicalFile();
                    File resourcePath =  new File(currentWorkPath+"/resources/settings.properties");
                    if(!resourcePath.exists()){
                        resourcePath =new File(currentWorkPath+"/src/test/resources/settings.properties");
                    }
                    Properties propertiesConnection = new Properties();
                    propertiesConnection.load(new FileInputStream(resourcePath));
                    //groovybrowser.startSeleniumConnection(urlInputField.getText());
                    groovybrowser.startSeleniumConnection(propertiesConnection.getProperty("url"));
                    groovybrowser.doGebSpockActionOnShell("", "id", "username", "sendKeys(\""+propertiesConnection.getProperty("username")+"\")", inputField06gebContentName.getText(), choiceShellOrManual);
                    groovybrowser.doGebSpockActionOnShell("", "id", "password", "sendKeys(\""+propertiesConnection.getProperty("password")+"\")", inputField06gebContentName.getText(), choiceShellOrManual);
                    groovybrowser.doGebSpockActionOnShell("", "id", "loginBtn", "click()", inputField06gebContentName.getText(), choiceShellOrManual);

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
                    groovybrowser.stopSeleniumConnection();
                    button01OpenSession.setDisable(false);
                    outText2.setText("Connection closed.");

                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });

        button03Information.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                try {
                            outText2.setText((groovybrowser.getSelectorText(inputField01css1.getText(), inputField02css2.getText())));
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });

        comboBoxActions.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                try {
                    if (comboBoxActions.getValue().toString().contains("STRING")) {
                        inputFieldExtraString.setDisable(false);
                    } else {
                        inputFieldExtraString.setDisable(true);
                    }
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });

        button04MouseOver.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                try {
                    //System.out.println("button04MouseOver clicked");
                    groovybrowser.mouseOver(inputField01css1.getText(), choiceShellOrManual);
                    //seleniumInstance1.mouseOver(inputField01css1.getText());
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });
        button05InfoAttribute.setOnAction(new EventHandler<ActionEvent>() {
                                              public void handle(ActionEvent e) {
                                                  try {
                                                      //System.out.println("button05InfoAttribute clicked");
                                                      outText2.setText(groovybrowser.getThis(inputField01css1.getText(), "getProperties()"));
                                                  } catch (Exception e1) {
                                                      e1.printStackTrace();
                                                  }
                                              }
                                          }
        );

        button06MouseClick.setOnAction(new EventHandler<ActionEvent>() {
                                           public void handle(ActionEvent e) {
                                               try {
                                                  // System.out.println("button06MouseClick clicked");
                                                   groovybrowser.mouseClick(inputField01css1.getText(), choiceShellOrManual);
                                               } catch (Exception e1) {
                                                   e1.printStackTrace();
                                               }
                                           }
                                       }
        );

        button09Report.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                try {
                    //System.out.println("button09Report clicked");
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });

        button11SpockGet.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                try {
                    String requestedAction = comboBoxActions.getValue().toString();
                    if (comboBoxSelectors.getValue().toString().contains("cssSelector")|comboBoxSelectors.getValue().toString().contains("xpath")) {
                        inputField04gebString.setText(inputField04gebString.getText().replaceAll("\"", "'"));
                       // System.out.println("XX");
                    }
                    if (comboBoxActions.getValue().toString().contains("STRING")) {
                       // System.out.println("CHECK03a = " + requestedAction);
                        requestedAction = requestedAction.replace("STRING", inputFieldExtraString.getText());
                      //  System.out.println("CHECK03b = " + requestedAction);
                    }

                    //System.out.println("CHECK01 = " + requestedAction);
                    String[] result = groovybrowser.doGebSpockActionOnShell(inputField03AgebElement.getText(), comboBoxSelectors.getValue().toString(), inputField04gebString.getText(), requestedAction, inputField06gebContentName.getText(), choiceShellOrManual);

                    outputField1ActionText.setText(result[1]);
                    outputField1ContentItem.setText(result[0]);


                    /**
                     *  result[0] is complete text for creating the locator
                     *  result[1] is XXX now part of script
                     *  result[2] is result of the execution, can contain returnvalues of the actions or exceptions
                     *  result[3] is Action Successful, Exception occurred, Failed to locate
                     */


                    switch (result[3]) {
                        case "Action successful":
                            outputFieldResultTry.setStyle("-fx-background-color: green;");
                            outputFieldResultTry.setText("Action successful");
                           // System.out.println("SUCCCESS!!!"+result[3]);
                            break;
                        case "Exception occurred":
                            outputFieldResultTry.setStyle("-fx-background-color: red;");
                            outputFieldResultTry.setText("Exception occurred");
                          //  System.out.println("EXCEPTION!!!"+result[3]);
                            break;
                        case "Failed to locate":
                            outputFieldResultTry.setStyle("-fx-background-color: orange;");
                            outputFieldResultTry.setText("Failed to locate");
                          //  System.out.println("FAILED!!!"+result[3]);
                            break;
                    }

                    outText2.setText(result[2]);
                } catch (Exception e1) {

                    e1.printStackTrace();
                }
            }
        });

/**
 * Setting content of grids
 *
 */
        int grid1leftcounter = 0;
        grid1.add(vBox5, 0, grid1leftcounter++);//groovyshell
        grid1.add(hBox1, 0, grid1leftcounter++);//url conn
        grid1.add(sepHor1, 0, grid1leftcounter++);
        grid1.add(hBox3becameVBox, 0, grid1leftcounter++);
        grid1.add(vBox3, 0, grid1leftcounter++);
        grid1.add(sepHor5, 0, grid1leftcounter++);
        grid1.add(textDrivFindElAtt, 0, grid1leftcounter++);
        grid1.add(hBox2, 0, grid1leftcounter++);
        grid1.add(vBox2, 1, 0, 1, 25);
        grid1.add(vBox1, 0, grid1leftcounter++);
        primStage.setScene(sceneManualDrive);
        primStage.getScene().fillProperty();
        primStage.show();
        button01OpenSession.fire();//auto start open url browser
    }

    public static void main(String[] args) {
        launch(args);
    }
}

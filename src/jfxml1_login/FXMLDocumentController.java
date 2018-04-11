/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jfxml1_login;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Rupak Kalita
 */
public class FXMLDocumentController implements Initializable {
    @FXML
    private AnchorPane anchorpane;
    @FXML
    private Label label;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private Hyperlink signin;
        @FXML
    private Hyperlink forgot;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
//    @FXML
//    private void forgot(ActionEvent event) throws IOException {
//       loadWindow("/forgotpassword/forgot.fxml","forgot password","/img/index.png");
//    }

//        public void loadWindow(String location,String title, String iconPath) throws IOException{
//        AnchorPane root = FXMLLoader.load(getClass().getResource(location));
//        Stage stage = new Stage(StageStyle.DECORATED);
//        stage.setTitle(title);
//        Image icon = new Image(getClass().getResourceAsStream(iconPath));
//        stage.getIcons().add(icon);
//        stage.setScene(new Scene(root));
//        stage.show();
//    }
    
    
    
    @FXML
    private void signIn(ActionEvent event) throws IOException {
        AnchorPane root;
        root = FXMLLoader.load(getClass().getResource("/Singup/signup.fxml"));
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setTitle("SIGN IN");
        stage.setScene(new Scene(root));
        stage.show();
    }
    
//    @FXML
//    public void loadWindow(String location,String title, String iconPath) throws IOException{
//        Parent root = FXMLLoader.load(getClass().getResource(location));
//        Stage stage = new Stage(StageStyle.DECORATED);
//        stage.setTitle(title);
//        Image icon = new Image(getClass().getResourceAsStream(iconPath));
//        stage.getIcons().add(icon);
//        stage.setScene(new Scene(root));
//        stage.show();
//    }
    
    @FXML
    private void forgot(ActionEvent event) throws IOException {
        Parent root;
        root = FXMLLoader.load(getClass().getResource("/forgotpassword/forgot.fxml"));
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setTitle("Forgot password");
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    private void login(ActionEvent event) throws IOException {
            Parent root = FXMLLoader.load(getClass().getResource("/library_help/Library.fxml"));
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle("Welcome");
            stage.setScene(new Scene(root));
            stage.show();
    }
}

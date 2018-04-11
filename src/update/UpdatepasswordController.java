/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package update;

import DButils.ConnectDB;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author anoop
 */
public class UpdatepasswordController implements Initializable {
     @FXML
    private AnchorPane anchorpane;
    @FXML
    private TextField idname;
    @FXML
    private PasswordField ppassword;
    @FXML
    private PasswordField cpassword;
    @FXML
    private PasswordField npassword;
    @FXML
    private JFXButton submit;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
//        ConnectDB connectDB = new ConnectDB();
//        LoadData();
    }    

 // onsubmit button 

    @FXML
    private void onsubmit(ActionEvent event) throws SQLException {
        String midname = idname.getText();
        String mpassword = ppassword.getText();
        String mcpassword = cpassword.getText();
        String mnpassword=npassword.getText();
        
        if(midname.isEmpty() || mpassword.isEmpty() || mcpassword.isEmpty() || mnpassword.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Fields must not be empty");
            alert.showAndWait();
            
        } 
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setContentText("WANT TO UPDATE?");
            alert.showAndWait();
            Optional<ButtonType> response =alert.showAndWait();
            
        if(response.get()==ButtonType.OK)
        {
            
        
        
        String sql = "UPDATE tbl_signmember  "
                + "SET password= mnpassword "
                + " WHERE rowid= midname";
        Connection conn = ConnectDB.getConnections();
        PreparedStatement pst = conn.prepareStatement(sql);
        
        pst.setString(1,midname);
        pst.setString(2,mpassword);
        pst.setString(3,mcpassword);
        pst.setString(4,mnpassword);
        
        
        pst.execute();
            Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION);
            alert2.setHeaderText(null);
            alert2.setContentText("updated");
            alert2.showAndWait();
         
        }
        else
        { 
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setHeaderText(null);
            alert1.setContentText("please try again");
            alert1.showAndWait();
            
        }
        
    }
}

    


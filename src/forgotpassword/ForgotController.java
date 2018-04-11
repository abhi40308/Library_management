/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forgotpassword;

import DButils.ConnectDB;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;


/**
 * FXML Controller class
 *
 * @author Rupak Kalita
 */
public class ForgotController implements Initializable {

    @FXML
    private TextField email;
    
    @FXML
    private TextField dob;
    
    @FXML
    private Label retpass;
        @FXML
    private ListView<String> listview;

    private ConnectDB connectDB;

    Boolean isReadyForSubmission = false;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        connectDB = new ConnectDB();
    }
    
    public boolean isLogin(String email1,String dob1) throws SQLException{
    PreparedStatement pt=null;
    ResultSet rs=null;
    String query="select * from tbl_signmember where email=? and dob=?";
    try{
       Connection conn = ConnectDB.getConnections();
        pt = conn.prepareStatement(query);
        

       pt.setString(1, email1);
       pt.setString(2, dob1);
       rs=pt.executeQuery();
       if(rs.next()){
           return true;
       }else{
           return false;
       }
    }catch(Exception e){
        return false;
    }finally{
        rs.close();
        pt.close();
    }
    
}  
    
    
       @FXML
   public void getpass(ActionEvent event) throws SQLException {
            try{
         if(isLogin(email.getText(), dob.getText())){

           
             
        String id = email.getText(); 
        String sql = "SELECT * FROM tbl_signmember WHERE id = '" + id + "'";
        Connection conn = ConnectDB.getConnections();
        PreparedStatement pst = conn.prepareStatement(sql);
        ResultSet rst = pst.executeQuery();

        while (rst.next()) {
            String pass = rst.getString("password");

            retpass.setText(pass);       
             
             
            }}
        else{
         retpass.setText("wrong input...try again");
         } 
     }
         catch(SQLException e){
         retpass.setText("wrong input ");
     }  
   }

}

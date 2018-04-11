/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library_help;

import DButils.ConnectDB;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Rupak Kalita
 */
public class LibraryController implements Initializable {
    
    @FXML
    private TextField BookInputID;
    @FXML
    private Label bookname;
    @FXML
    private Label bookauthor;
    @FXML
    private TextField memberID;
    @FXML
    private Label membercontact;
    @FXML
    private Label membername;
    @FXML
    private TextField bookID;
    @FXML
    private ListView<String> listview;
    
    ObservableList<String> issueData= FXCollections.observableArrayList();
    private ConnectDB connectDB;
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        connectDB = new ConnectDB();
    }   
        public void loadWindow(String location,String title, String iconPath) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource(location));
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setTitle(title);
        Image icon = new Image(getClass().getResourceAsStream(iconPath));
        stage.getIcons().add(icon);
        stage.setScene(new Scene(root));
        stage.show();
    }
    @FXML
    private void addBook(ActionEvent event) throws IOException {
       loadWindow("/Addbook/AddBook.fxml","Add Book","/img/index.png");
    }
    
    @FXML
    private void submitBook(ActionEvent event) throws IOException {
       loadWindow("/Addbook/AddBook.fxml","Add Book","/img/index.png");
    }
   
        
    @FXML
    private void addMember(ActionEvent event) throws IOException {
        
        loadWindow("/AddMember/AddMember.fxml","Add Member","/img/member-add-on-300x300.png");
    }
    
    @FXML
    private void listBook(ActionEvent event) throws IOException {
        loadWindow("/BookList/BookList.fxml","Book List","/img/images.png");
    }
    
    
    
    @FXML
    private void MemberList(ActionEvent event) throws IOException {
        loadWindow("/MemberList/MemberList.fxml","Member List","/img/login-members.png");
    }
    
    @FXML
    private void update(ActionEvent event) throws IOException {
        
        loadWindow("/update/updatepassword.fxml","upadate","/img/index.png");
    }

    

    @FXML
    public void bookIDenter(ActionEvent event) throws SQLException{
        String id = BookInputID.getText();
        String sql = "SELECT * FROM tbl_addbook WHERE id = '" + id +"'";
        Connection conn = ConnectDB.getConnections();
        PreparedStatement pst = conn.prepareStatement(sql);
        ResultSet rst = pst.executeQuery();
        
        boolean flag=false;
        while(rst.next()) {
            String bName = rst.getString("title");
            String bAuthor = rst.getString("author");
            
            bookname.setText(bName);
            bookauthor.setText(bAuthor);
            flag = true;
        }
        if(!flag){
            bookname.setText("No such book is available");
            bookauthor.setText("No such author is available");
        }
    }
    
    @FXML
    public void memberIDenter(ActionEvent event) throws SQLException{
        String id = memberID.getText();
        String sql = "SELECT * FROM tbl_addmember WHERE id = '" + id +"'";
        Connection conn = ConnectDB.getConnections();
        PreparedStatement pst = conn.prepareStatement(sql);
        ResultSet rst = pst.executeQuery();
        
        boolean flag=false;
        while(rst.next()) {
            String bName = rst.getString("name");
            String bEmail = rst.getString("email");
            
            membername.setText(bName);
            membercontact.setText(bEmail);
            flag = true;
        }
        if(!flag){
            membername.setText("No such name is available");
            membercontact.setText("No such contact is available");
        }
    }
    
    @FXML
    public void issuebook(ActionEvent event) throws SQLException{
        String bookID = BookInputID.getText();
        String memberInput = memberID.getText();
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("confirm issue operation");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure to issue the book?" + bookname.getText() + "\n To" + membername.getText());
        Optional<ButtonType> response = alert.showAndWait();
        
        if(response.get() == ButtonType.OK)
        {
            String sql = "INSERT INTO tbl_issue (bookID,memberInput) VALUES(?,?)";
            Connection conn = ConnectDB.getConnections();
            
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, bookID);
            pst.setString(2, memberInput);
            pst.execute();
            
            Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
            alert1.setHeaderText(null);
            alert1.setContentText("Book issued successfully to" + membername.getText());
            alert1.showAndWait();
        }
    }
    
    @FXML
    public void loadInfo(ActionEvent event) throws SQLException{
        issueData= FXCollections.observableArrayList();
        issueData.removeAll(issueData);
        String id = bookID.getText();
        String sql = "SELECT * FROM tbl_issue WHERE bookID = '" + id + "'";
        Connection conn = ConnectDB.getConnections();
        PreparedStatement pst = conn.prepareStatement(sql);
        ResultSet rst = pst.executeQuery();
        
        while(rst.next()){
            String BookID = id;
            String MemberID = rst.getString("memberInput");
            String issueTime = rst.getString("issueTime");
            int renew_count = rst.getInt("renew_count");
            
            issueData.add("Issued Information: ");
            issueData.add("Issued Time and Date: " + issueTime);
            issueData.add("Renew Count: " + renew_count);
            
            issueData.add("Book Information: ");
            String sql2 = "SELECT * FROM tbl_addbook WHERE id = '" + BookID + "'";
            Connection con2 = ConnectDB.getConnections();
            PreparedStatement pst2 = con2.prepareStatement(sql2);
            ResultSet rst2 = pst2.executeQuery();
            while(rst2.next()){
                issueData.add("Book Name: " + rst2.getString("title"));
                issueData.add("Book ID: " + rst2.getString("id"));
                issueData.add("Book Name: " + rst2.getString("author"));
                issueData.add("Book Publisher: " + rst2.getString("publisher"));
                
            }
            
            String sql3 = "SELECT * FROM tbl_addmember WHERE id = '" + MemberID + "'";
            Connection con3 = ConnectDB.getConnections();
            PreparedStatement pst3 = con3.prepareStatement(sql3);
            ResultSet rst3 = pst3.executeQuery();
            while(rst3.next()){
                issueData.add("Member Name: " + rst3.getString("name"));
                issueData.add("Member ID: " + rst3.getString("id"));
                issueData.add("Member Contact Number: " + rst3.getString("mobile"));
                issueData.add("Member Email: " + rst3.getString("email"));
            }
            
            listview.getItems().setAll(issueData);
       }
        
      
            
    
}

   
}

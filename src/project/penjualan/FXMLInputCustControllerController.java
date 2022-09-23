/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.penjualan;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class FXMLInputCustControllerController implements Initializable {

    boolean editdata=false;
    
    @FXML
    private TextField txtid;
    @FXML
    private TextField txtnama;
    @FXML
    private TextField txtalamat;
    @FXML
    private TextField txttotal;
    @FXML
    private Button btnsimpan;
    @FXML
    private Button btnbatal;
    @FXML
    private Button btnquit;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void execute(CustModel d){
        if(!d.getIdMember().isEmpty()){
          editdata=true;
          txtid.setText(d.getIdMember());
          txtnama.setText(d.getNama());          txtalamat.setText(d.getAlamat());
          txttotal.setText(String.valueOf(d.getTotal()));          
          txtid.setEditable(false);          txtnama.requestFocus();         
        }
    }


    @FXML
    private void simpanklik(ActionEvent event) {
        CustModel n=new CustModel();        
        n.setIdMember(txtid.getText());
        n.setNama(txtnama.getText());     
        n.setAlamat(txtalamat.getText());  
        n.setTotal(Double.parseDouble(txttotal.getText()));
FXMLDocumentController.dtcust.setCustModel(n);
        if(editdata){
            if(FXMLDocumentController.dtcust.update()){
               Alert a=new Alert(Alert.AlertType.INFORMATION,"Data berhasil diubah",ButtonType.OK);
               a.showAndWait();   
               txtid.setEditable(true);        
               batalklik(event); } else {               
                Alert a=new Alert(Alert.AlertType.ERROR,"Data gagal diubah",ButtonType.OK);
                a.showAndWait(); 
            }
    }
                    else if(FXMLDocumentController.dtcust.validasi(n.getIdMember())<=0){
            if(FXMLDocumentController.dtcust.insert()){
               Alert a=new Alert(Alert.AlertType.INFORMATION,"Data berhasil disimpan",ButtonType.OK);
               a.showAndWait();            
               batalklik(event);
            } else {
               Alert a=new Alert(Alert.AlertType.ERROR,"Data gagal disimpan",ButtonType.OK);
               a.showAndWait();            
            }
        }else{Alert a=new Alert(Alert.AlertType.ERROR,"Data sudah ada",ButtonType.OK);
            a.showAndWait();
            txtid.requestFocus();
        }
    }
   

    @FXML
    private void batalkilk(ActionEvent event) {
                txtid.setText("");        
        txtnama.setText("");
        txtalamat.setText("");       
        txttotal.setText("");  
        txtid.requestFocus();

    }

    @FXML
    private void quitklik(ActionEvent event) {
                btnquit.getScene().getWindow().hide();

    }

    private void batalklik(ActionEvent event) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

  
    
}

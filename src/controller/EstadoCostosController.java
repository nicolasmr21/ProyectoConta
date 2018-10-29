package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class EstadoCostosController implements Initializable{
   
	@FXML
    private BorderPane borderPane;
	
	   @FXML
	    private TextField txtInicialMD;

	    @FXML
	    private TextField txtInicialPP;

	    @FXML
	    private TextField txtInicialPT;

	    @FXML
	    private TextField txtFinalMD;

	    @FXML
	    private TextField txtFinalPP;

	    @FXML
	    private TextField txtFinalPT;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/application/s2.fxml"));
        Parent p;
		try {
			p = loader.load();
			borderPane.setCenter(p);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        //access the controller and call a method
 
	}

}

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
	 private MainController main;
	
	 public void init(MainController m) {
			main = m;
		}
	
	@FXML
    private BorderPane borderPane;
	
	@FXML
	private TablaCuentasController t;
	
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
			t = loader.getController();
			t.init(this);
			borderPane.setCenter(p);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        //access the controller and call a method
 
	}


	public BorderPane getBorderPane() {
		return borderPane;
	}


	public TextField getTxtInicialMD() {
		return txtInicialMD;
	}


	public TextField getTxtInicialPP() {
		return txtInicialPP;
	}


	public TextField getTxtInicialPT() {
		return txtInicialPT;
	}


	public TextField getTxtFinalMD() {
		return txtFinalMD;
	}


	public TextField getTxtFinalPP() {
		return txtFinalPP;
	}


	public TextField getTxtFinalPT() {
		return txtFinalPT;
	}


	public void calcularEstado(String[] n, double[] a, int[] b) throws IOException {
		main.calcularEstado(n, a, b);
	}
	
	

}

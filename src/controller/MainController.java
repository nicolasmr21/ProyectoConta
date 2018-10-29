package controller;

import java.awt.Paint;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import javafx.animation.RotateTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.NodeOrientation;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Regresion;

public class MainController implements Initializable {
	
		@FXML
		private PresupuestalController presupuestal;
		
		@FXML
		private GraphicsController graficos;

	    @FXML
	    private Button btnFormula;

	    @FXML
	    private Button btnEstado;


	    @FXML
	    private Button btnBack;

	    @FXML
	    private Circle c1;

	    @FXML
	    private Circle c3;

	    @FXML
	    private Circle c2;
	   
	    @FXML
	    private BorderPane borderPane;
	   
	    
	    @FXML
	    void estadoDeCostos(ActionEvent event) throws IOException {
	    	FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(getClass().getResource("/application/EstadoCostos.fxml"));
	        Parent p = loader.load();
	        //access the controller and call a method
	    	borderPane.setCenter(p);
	    }

	    @FXML
	    void formulaPresupuestal(ActionEvent event) throws IOException {
	    	FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(getClass().getResource("/application/s.fxml"));
	        Parent p = loader.load();
	        //access the controller and call a method
	        presupuestal = loader.getController();
	        presupuestal.init(this);
	    	borderPane.setCenter(p);
	    }
	    
	    void crearGraficos(double[] x, double[] y, double xMax, double xMin
	    		,double yMax, double yMin) throws IOException {
	    	FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(getClass().getResource("/application/GraphicsView.fxml"));
	        Parent p = loader.load();
	        //access the controller and call a method
	        graficos = loader.getController();
	        Regresion r = new Regresion();
	        double[] reg = r.calcRectaRegresionXsobreY(y, x);
	        double puntoPendiente = pendiente(xMax, xMin, yMax, yMin);
	        double puntoB = b(puntoPendiente, xMax, yMax );
	        graficos.pintarFunciones(reg[0], reg[1], puntoPendiente, puntoB, 1000);
	    	borderPane.setCenter(p);
	    }
	    
	    public double pendiente(double x1, double x2
	    		,double y1, double y2) {
	    	return ((y2-y1)/(x2-x1));
	    }
	    
	    public double b(double pendiente, double x1, double y1) {
	    	return y1-(pendiente*x1);
	    }

	    @FXML
	    void goBack(ActionEvent event) {
	    	System.exit(0);
	    }


	    @FXML
	    void pushImage(MouseEvent event) throws IOException {
	    	FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(getClass().getResource("/application/MainView.fxml"));
	        Parent p = loader.load();
	        Scene scene = new Scene(p);
	        //access the controller and call a method
	        
	        //This line gets the Stage information
	        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
	        
	        window.setScene(scene);
	        window.show();
	    }
	    
		@Override
		public void initialize(URL location, ResourceBundle resources) {
			
			setRotate(c1, true, 360, 10);
			setRotate(c2, true, 180, 8);
			setRotate(c3, true, 145, 15);
			
			
	
			
		}
		
		

		public void setRotate(Circle c, boolean reverse, int angle, int d) {
			
			RotateTransition r = new RotateTransition(Duration.seconds(d), c);
			
			r.setAutoReverse(reverse);
			r.setByAngle(angle);
			r.setDelay(Duration.seconds(0));
			r.setRate(5);
			r.setCycleCount(100);
			r.play();
			
		}

		public void removeCenter() {
			borderPane.setCenter(null);
		}
		

}

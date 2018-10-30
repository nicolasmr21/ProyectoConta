package controller;

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
		private EstadoCostosController estado;
		
		@FXML
		private CalculateController calcular;

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
	        estado = loader.getController();
	        estado.init(this);
	        //access the controller and call a method
	    	borderPane.setCenter(p);
	    }
	    
	    void calcularEstado(String[] nombres, double[] costos, int[] tipos) throws IOException {
	    	FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(getClass().getResource("/application/Calculate.fxml"));
	        Parent p = loader.load();
	        calcular = loader.getController();
	        //access the controller and call a method
	    	borderPane.setCenter(p);
	    	
	    	double inicialMD = Double.parseDouble(estado.getTxtInicialMD().getText());
	    	double compras = calcularCompras(costos, tipos);
	    	double disponible = inicialMD + compras;
	    	double finalMD = Double.parseDouble(estado.getTxtFinalMD().getText());
	    	double MD = disponible - finalMD;
	    	double MOD = calcularMOD(costos, tipos);
	    	double primos = MD+MOD;
	    	double CIF = calcularCIF(costos, tipos);
	    	double costoProduccion = MD + MOD + CIF;
	    	double inicialPP = Double.parseDouble(estado.getTxtInicialPP().getText());
	    	double finalPP = Double.parseDouble(estado.getTxtFinalPP().getText());
	    	double costoTerminado = costoProduccion + inicialPP - finalPP;
	    	double inicialPT = Double.parseDouble(estado.getTxtInicialPT().getText());
	    	double finalPT = Double.parseDouble(estado.getTxtFinalPT().getText());
	    	double costoVenta = costoTerminado + inicialPT -finalPT;

	    	
	    	calcular.getInicialMD().setText(String.valueOf(inicialMD));;
	    	calcular.getCompras().setText(String.valueOf(compras));;
	    	calcular.getDisponibleMD().setText(String.valueOf(disponible));
	    	calcular.getFinalMD().setText(String.valueOf(finalMD));
	    	calcular.getMD().setText(String.valueOf(MD));
	    	calcular.getMOD().setText(String.valueOf(MOD));
	    	calcular.getCostoPrimo().setText(String.valueOf(primos));
	    	calcular.getCIF().setText(String.valueOf(CIF));
	    	calcular.getCostoProduccion().setText(String.valueOf(costoProduccion));
	    	calcular.getInicialPP().setText(String.valueOf(inicialPP));
	    	calcular.getFinalPP().setText(String.valueOf(finalPP));
	    	calcular.getCostoPTerminado().setText(String.valueOf(costoTerminado));
	    	calcular.getInicialPT().setText(String.valueOf(inicialPT));
	    	calcular.getFinalPT().setText(String.valueOf(finalPT));
	    	calcular.getCostoVenta().setText(String.valueOf(costoVenta));



	    	
	    }

	    private double calcularCompras(double[] costos, int[] tipos) {
	    	double compras = 0;
	    	for (int i = 0; i < tipos.length; i++) {
				if(tipos[i]==1) {
					compras += costos[i];
				}
			}
	    	return compras;
		}
	    
	    private double calcularCIF(double[] costos, int[] tipos) {
	    	double CIF = 0;
	    	for (int i = 0; i < tipos.length; i++) {
				if(tipos[i]==2) {
					CIF += costos[i];
				}
			}
	    	return CIF;
		}
	    
	    private double calcularMOD(double[] costos, int[] tipos) {
	    	double MOD = 0;
	    	for (int i = 0; i < tipos.length; i++) {
				if(tipos[i]==3) {
					MOD += costos[i];
				}
			}
	    	return MOD;
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

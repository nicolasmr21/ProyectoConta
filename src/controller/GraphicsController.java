package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.Chart;
import javafx.scene.chart.LineChart;

public class GraphicsController  implements Initializable{

    @FXML
    private LineChart<String, Double> variableM;


    @FXML
    private LineChart<String, Double> fijoM;


    @FXML
    private LineChart<String, Double> mixtoM;

  
    @FXML
    private LineChart<String, Double> mixtoP;

    @FXML
    private LineChart<String, Double> fijoP;

 

    @FXML
    private LineChart<String, Double> variableP;

   
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		
	}
	
	
	public void pintarFunciones(double aMinimos, double bMinimos, double aP, double bP,  int max) {
		
		//VARIABLE POR MINIMOS
		
		// Creamos un ObservableList para guardar los puntos que pintaremos en la gráfica
        ObservableList<LineChart.Series<String, Double>> dataVariableM = FXCollections.observableArrayList();
        // Instanciamos un punto a pintar
        LineChart.Series<String, Double> seriesVariableM = new LineChart.Series<String, Double>();
        // Imprimimos la función que vamos a pintar
        seriesVariableM.setName("fx ="+aMinimos+"*X");
        // obtenemos los puntos a pintar. Daros cuenta que los puntos a pintar estan definidos
        // por el valor de 'x' y el resultado de 'f(x)', siendo f(x)=Math.pow(x, grado) = x^grado
        for (double i = 0; i<max; i=i+1){
        	seriesVariableM.getData().add(new LineChart.Data<String, Double>(""+i, aMinimos*i));
        }
        // Guardamos todos los puntos de la función que hemos obtenido
        dataVariableM.add(seriesVariableM);
        // Si No quereis que se pinten los puntos, poner a false
        variableM.setCreateSymbols(false);
        // Ponemos los puntos en la gráfica
        variableM.setData(dataVariableM);
        variableM.createSymbolsProperty();
 
		//FIJO POR MINIMOS
        
     // Creamos un ObservableList para guardar los puntos que pintaremos en la gráfica
        ObservableList<LineChart.Series<String, Double>> dataFijoM = FXCollections.observableArrayList();
        // Instanciamos un punto a pintar
        LineChart.Series<String, Double> seriesFijoM = new LineChart.Series<String, Double>();
        // Imprimimos la función que vamos a pintar
        seriesFijoM.setName("fx = "+bMinimos);
        // obtenemos los puntos a pintar. Daros cuenta que los puntos a pintar estan definidos
        // por el valor de 'x' y el resultado de 'f(x)', siendo f(x)=Math.pow(x, grado) = x^grado
        for (double i = 0; i<max; i=i+1){
        	seriesFijoM.getData().add(new LineChart.Data<String, Double>(""+i, bMinimos));
        }
        // Guardamos todos los puntos de la función que hemos obtenido
        dataFijoM.add(seriesFijoM);
        // Si No quereis que se pinten los puntos, poner a false
        fijoM.setCreateSymbols(false);
        // Ponemos los puntos en la gráfica
        fijoM.setData(dataFijoM);
        fijoM.createSymbolsProperty();

        
    	//MIXTO POR MINIMOS
        
        // Creamos un ObservableList para guardar los puntos que pintaremos en la gráfica
           ObservableList<LineChart.Series<String, Double>> dataMixtoM = FXCollections.observableArrayList();
           // Instanciamos un punto a pintar
           LineChart.Series<String, Double> seriesMixtoM = new LineChart.Series<String, Double>();
           // Imprimimos la función que vamos a pintar
           seriesMixtoM.setName("fx ="+aMinimos+"*X+"+bMinimos);
           // obtenemos los puntos a pintar. Daros cuenta que los puntos a pintar estan definidos
           // por el valor de 'x' y el resultado de 'f(x)', siendo f(x)=Math.pow(x, grado) = x^grado
           for (double i = 0; i<max; i=i+1){
           	seriesMixtoM.getData().add(new LineChart.Data<String, Double>(""+i, aMinimos*i+bMinimos));
           }
           // Guardamos todos los puntos de la función que hemos obtenido
           dataMixtoM.add(seriesMixtoM);
           // Si No quereis que se pinten los puntos, poner a false
           mixtoM.setCreateSymbols(false);
           // Ponemos los puntos en la gráfica
           mixtoM.setData(dataMixtoM);
           mixtoM.createSymbolsProperty();
           
           
           
           
       	//VARIABLE POR PUNTO
   		
   		// Creamos un ObservableList para guardar los puntos que pintaremos en la gráfica
           ObservableList<LineChart.Series<String, Double>> dataVariableP = FXCollections.observableArrayList();
           // Instanciamos un punto a pintar
           LineChart.Series<String, Double> seriesVariableP = new LineChart.Series<String, Double>();
           // Imprimimos la función que vamos a pintar
           seriesVariableP.setName("fx ="+aP+"*X");
           // obtenemos los puntos a pintar. Daros cuenta que los puntos a pintar estan definidos
           // por el valor de 'x' y el resultado de 'f(x)', siendo f(x)=Math.pow(x, grado) = x^grado
           for (double i = 0; i<max; i=i+1){
           	seriesVariableP.getData().add(new LineChart.Data<String, Double>(""+i, aP*i));
           }
           // Guardamos todos los puntos de la función que hemos obtenido
           dataVariableP.add(seriesVariableP);
           // Si No quereis que se pinten los puntos, poner a false
           variableP.setCreateSymbols(false);
           // Ponemos los puntos en la gráfica
           variableP.setData(dataVariableP);
           variableP.createSymbolsProperty();
    
   		//FIJO POR MINIMOS
           
        // Creamos un ObservableList para guardar los puntos que pintaremos en la gráfica
           ObservableList<LineChart.Series<String, Double>> dataFijoP = FXCollections.observableArrayList();
           // Instanciamos un punto a pintar
           LineChart.Series<String, Double> seriesFijoP = new LineChart.Series<String, Double>();
           // Imprimimos la función que vamos a pintar
           seriesFijoP.setName("fx = "+bP);
           // obtenemos los puntos a pintar. Daros cuenta que los puntos a pintar estan definidos
           // por el valor de 'x' y el resultado de 'f(x)', siendo f(x)=Math.pow(x, grado) = x^grado
           for (double i = 0; i<max; i=i+1){
           	seriesFijoP.getData().add(new LineChart.Data<String, Double>(""+i, bP));
           }
           // Guardamos todos los puntos de la función que hemos obtenido
           dataFijoP.add(seriesFijoP);
           // Si No quereis que se pinten los puntos, poner a false
           fijoP.setCreateSymbols(false);
           // Ponemos los puntos en la gráfica
           fijoP.setData(dataFijoP);
           fijoM.createSymbolsProperty();

           
       	//MIXTO POR MINIMOS
           
           // Creamos un ObservableList para guardar los puntos que pintaremos en la gráfica
              ObservableList<LineChart.Series<String, Double>> dataMixtoP = FXCollections.observableArrayList();
              // Instanciamos un punto a pintar
              LineChart.Series<String, Double> seriesMixtoP = new LineChart.Series<String, Double>();
              // Imprimimos la función que vamos a pintar
              seriesMixtoP.setName("fx ="+aP+"*X+"+bP);
              // obtenemos los puntos a pintar. Daros cuenta que los puntos a pintar estan definidos
              // por el valor de 'x' y el resultado de 'f(x)', siendo f(x)=Math.pow(x, grado) = x^grado
              for (double i = 0; i<max; i=i+1){
              	seriesMixtoP.getData().add(new LineChart.Data<String, Double>(""+i, aP*i+bP));
              }
              // Guardamos todos los puntos de la función que hemos obtenido
              dataMixtoP.add(seriesMixtoP);
              // Si No quereis que se pinten los puntos, poner a false
              mixtoP.setCreateSymbols(false);
              // Ponemos los puntos en la gráfica
              mixtoP.setData(dataMixtoP);
              mixtoP.createSymbolsProperty();
           
	}
	

}

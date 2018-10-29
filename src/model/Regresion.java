package model;

public class Regresion {
		// funcion y= ax+b
	    public static double[] calcRectaRegresionYsobreX(double[] lasX, double[] lasY) {
	        double[] retVal = new double[3];
	        double mediaX = calcMedia(lasX);
	        double mediaY = calcMedia(lasY);
	        double varianzaX = (calcMediaDeLosCuadrados(lasX) - Math.pow(mediaX, 2));
	        double varianzaY = (calcMediaDeLosCuadrados(lasY) - Math.pow(mediaY, 2));
	        double covarianza = calcMediaDeLosProductos(lasX, lasY) - (mediaX * mediaY);
	        double diviCovaX = covarianza / varianzaX;
	
	        retVal[0] = diviCovaX;    // aqui devuelve la pendiente de la recta
	        retVal[1] = -(diviCovaX * mediaX) + mediaY; // aqui devuelve el parametro independiente
		        if ((Math.sqrt(varianzaX) * Math.sqrt(varianzaY))==0){
		            retVal[2]=1;
		        } else {
		            retVal[2] = covarianza / (Math.sqrt(varianzaX) * 
		            Math.sqrt(varianzaY)); // esto es la correlacion r
		        }
		        return retVal;
	    }
	
	    // funcion x= ay + b
	    public static double[] calcRectaRegresionXsobreY(double[] lasX, double[] lasY) {
	        double[] retVal = new double[3];
	        double mediaX = calcMedia(lasX);
	        double mediaY = calcMedia(lasY);
	        double varianzaX = (calcMediaDeLosCuadrados(lasX) - Math.pow(mediaX, 2));
	        double varianzaY = (calcMediaDeLosCuadrados(lasY) - Math.pow(mediaY, 2));
	        double covarianza = calcMediaDeLosProductos(lasX, lasY) - (mediaX * mediaY);
	        double diviCovaY = covarianza / varianzaY;
	
	        retVal[0] = diviCovaY;                     
	        // aqui devuelve la pendiente de la recta
	        retVal[1] = -(diviCovaY * mediaY) + mediaX;   
	        // aqui devuelve el parametro independiente
	        retVal[2] = covarianza / (Math.sqrt(varianzaX) * 
	        Math.sqrt(varianzaY)); // esta es la correlacion r
	        return retVal;
	    }
	
	    public static double calcMedia(double[] valores) {
	        double retVal = 0;
	        for (int i = 0; i < valores.length; i++) {
	            retVal += valores[i];
	        }
	        return retVal / valores.length;
	    }
	
	    public static double calcMediaDeLosCuadrados(double[] valores) {
	        double retVal = 0;
	        for (int i = 0; i < valores.length; i++) {
	            retVal += Math.pow(valores[i], 2);
	        }
	        return retVal / valores.length;
	    }
	
	    public static double calcMediaDeLosProductos(double[] valores1, 
	   		double[] valores2) {
	    	double retVal = 0;
	        for (int i = 0; i < valores1.length; i++) {
	            retVal += valores1[i] * valores2[i];
	        }
	        return retVal / valores1.length;
	    }
}

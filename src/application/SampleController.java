package application;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ResourceBundle;

import org.json.JSONObject;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class SampleController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane backColor;

    @FXML
    private Button button;

    @FXML
    private Text feelsLike;

    @FXML
    private Text info;

    @FXML
    private Text max;

    @FXML
    private Text min;

    @FXML
    private Text pressure;

    @FXML
    private Text temp;

    @FXML
    private TextField typeTheCity;
    
    @FXML
    private Text wind;

    @FXML
    void initialize() {
    	button.setOnAction(event -> {
    	String getUserCity = typeTheCity.getText().trim();	
    	String output = getUrlContent("https://api.openweathermap.org/data/2.5/weather?q=" + getUserCity + "&appid=eaf61b5b83888e04669bab080451d9d3&units=metric");	
    	System.out.println(output);
    	
    	if (!output.isEmpty()) {
    		JSONObject obj = new JSONObject (output);
    		temp.setText("Temperature: " + obj.getJSONObject("main").getInt("temp"));
    		feelsLike.setText("Feels like: " + obj.getJSONObject("main").getInt("feels_like"));
    		max.setText("Maximum: " + obj.getJSONObject("main").getInt("temp_max"));
    		min.setText("Minimum: " + obj.getJSONObject("main").getInt("temp_min"));
    		pressure.setText("Pressure: " + obj.getJSONObject("main").getInt("pressure"));
    		wind.setText("Wind: " + obj.getJSONObject("wind").getInt("speed"));
    	}
    	});
    }

    private static String getUrlContent(String urlAdress) {
    	StringBuffer content = new StringBuffer();
    try {
    	URL url = new URL (urlAdress);
    	URLConnection urlConn = url.openConnection();
    	
    	BufferedReader bufferedReader = new BufferedReader (new InputStreamReader (urlConn.getInputStream()));
    	String line;
    	
    	while((line = bufferedReader.readLine())!= null) {
    		content.append(line + "\n");
    	}
    	bufferedReader.close();
    } catch(Exception e) {
    	System.out.println("This city doesn't exist");
    }
    return content.toString();
   
    }
    
    
    
    
}

package es.ieslaarboleda.clinicaveterinaria.main;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
	public static void main (String [] args) {
		launch(args); /*Esto arranca la aplicacion de javafx*/
		
		
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("/es/ieslaarboleda/clinicaveterinaria/view/Clientes.fxml"));
		Scene scn = new Scene(root);
		primaryStage.setScene(scn);
		primaryStage.setTitle("Clinica Veterinaria");
		primaryStage.show();
		
	}

}

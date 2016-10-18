package model;



import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MainApp extends Application {

	 private Stage primaryStage;
	 private Pane telaLogin;
	
	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Exemplo Netflix");
        
        mostraTelaLogin();
	}
	
	public void mostraTelaLogin(){
		
		try {
			 // Carrega a tela de login
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("/view/Login.fxml"));					
			telaLogin = (Pane) loader.load();
			
			Scene scene = new Scene(telaLogin);
            primaryStage.setScene(scene);
            primaryStage.show();
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public static void main(String[] args) {
		launch(args);
	}
}

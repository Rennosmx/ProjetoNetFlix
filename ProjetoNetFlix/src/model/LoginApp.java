package model;



import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import view.TelaCadastroController;
import view.TelaInicialController;
import view.TelaLoginController;

public class LoginApp extends Application {

	 private Stage primaryStage;
	 private Pane telaLogin, telaCadastro, telaInicial;
	
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
			loader.setLocation(LoginApp.class.getResource("/view/TelaLogin.fxml"));					
			telaLogin = (Pane) loader.load();
			
			Scene scene = new Scene(telaLogin);
            primaryStage.setScene(scene);
            primaryStage.show();
			                      
            TelaLoginController controller = loader.getController();
            controller.setMainApp(this);
            
            
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void mostraTelaCadastro(){
		
		try {
			 // Carrega a tela de cadastro
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(LoginApp.class.getResource("/view/TelaCadastro.fxml"));					
			telaCadastro = (Pane) loader.load();
			
			Scene scene = new Scene(telaCadastro);
            primaryStage.setScene(scene);
            primaryStage.show();
			                      
            TelaCadastroController controller = loader.getController();
            controller.setMainApp(this);
            
            
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void mostraTelaInicial(){
		
		try {
			 // Carrega a tela inicial
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(LoginApp.class.getResource("/view/TelaInicial.fxml"));					
			telaInicial = (Pane) loader.load();
			
			Scene scene = new Scene(telaInicial);
            primaryStage.setScene(scene);
            primaryStage.show();
            
            TelaInicialController controller = loader.getController();
            controller.setMainApp(this);
			                      
            
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

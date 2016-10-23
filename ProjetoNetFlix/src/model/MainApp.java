package model;



import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import view.TelaCadastroFilmeController;
import view.TelaCadastroUsuarioController;
import view.TelaHomeController;
import view.TelaLoginController;

public class MainApp extends Application {

	private static Usuario usuarioLogado;
	private Stage primaryStage;
	 private Pane telaLogin, telaCadastro, telaInicial;
	
	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Netfx");
        
        mostraTelaLogin();
	}
	
	public void mostraTelaLogin(){
		
		try {
			 // Carrega a tela de login
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("/view/TelaLogin.fxml"));					
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
	
	public void mostraTelaCadastroFilme() {
		
		try {
			 // Carrega a tela de cadastro
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("/view/TelaCadastroFilme.fxml"));					
			telaCadastro = (Pane) loader.load();
			
			Scene scene = new Scene(telaCadastro);
            primaryStage.setScene(scene);
            primaryStage.show();
			                      
            TelaCadastroFilmeController controller = loader.getController();
            controller.setMainApp(this);
            
            
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void mostraTelaCadastroUsuario(){
		
		try {
			 // Carrega a tela de cadastro
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("/view/TelaCadastroUsuario.fxml"));					
			telaCadastro = (Pane) loader.load();
			
			Scene scene = new Scene(telaCadastro);
            primaryStage.setScene(scene);
            primaryStage.show();
			                      
            TelaCadastroUsuarioController controller = loader.getController();
            controller.setMainApp(this);
            
            
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void mostraTelaHome(){
		
		try {
			 // Carrega a tela inicial
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("/view/TelaHome.fxml"));					
			telaInicial = (Pane) loader.load();
			
			Scene scene = new Scene(telaInicial);
            primaryStage.setScene(scene);
            primaryStage.show();
            
            TelaHomeController controller = loader.getController();
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
	
	public static Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

	public static void setUsuarioLogado(Usuario usuarioLogado) {
		MainApp.usuarioLogado = usuarioLogado;
	}
}

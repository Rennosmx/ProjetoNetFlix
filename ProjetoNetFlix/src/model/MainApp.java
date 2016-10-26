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
import view.TelaInformacoesFilmeController;
import view.TelaLoginController;
import view.TelaReproducaoFilmeController;

public class MainApp extends Application {

	private static Usuario usuarioLogado;
	private Stage primaryStage;
	 private Pane telaLogin, telaCadastro, telaInicial, telaInfoFilmes;
	
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
            primaryStage.setResizable(false);            
            primaryStage.show();
			                      
            TelaLoginController controller = loader.getController();
            controller.setMainApp(this);
            
            
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void mostraTelaCadastroFilme() {
		
		try {
			 // Carrega a tela de cadastro de filme
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("/view/TelaCadastroFilme.fxml"));					
			telaCadastro = (Pane) loader.load();
			
			Scene scene = new Scene(telaCadastro);
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);            
            primaryStage.show();
			                      
            TelaCadastroFilmeController controller = loader.getController();
            controller.setMainApp(this);
            
            
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void mostraTelaEdicaoFilme(Filme filme) {
		
		try {
			 // Carrega a tela de cadastro de filme
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("/view/TelaCadastroFilme.fxml"));					
			telaCadastro = (Pane) loader.load();
            TelaCadastroFilmeController controller = loader.getController();
            controller.setMainApp(this);
            
            controller.setModoEdicao(filme);
			Scene scene = new Scene(telaCadastro);
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);            
            primaryStage.show();
			                      

            
            
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void mostraTelaEdicaoUsuario(Usuario usuario) {
		
		try {
			// Carrega a tela de cadastro de filme
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("/view/TelaCadastroUsuario.fxml"));					
			telaCadastro = (Pane) loader.load();
			TelaCadastroUsuarioController controller = loader.getController();
			controller.setMainApp(this);
			
			controller.setModoEdicao(usuario);
			Scene scene = new Scene(telaCadastro);
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);            
			primaryStage.show();
				
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	
	
	public void mostraTelaCadastroUsuario(){
		
		try {
			 // Carrega a tela de cadastro de usuario
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("/view/TelaCadastroUsuario.fxml"));					
			telaCadastro = (Pane) loader.load();
			
			Scene scene = new Scene(telaCadastro);
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);           
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
            primaryStage.setResizable(false);
            primaryStage.show();
            
            TelaHomeController controller = loader.getController();
            controller.setMainApp(this);
            controller.mostrarTodosOsFilmes();         
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void mostraTelaInformacoesFilme(Filme filme){
		
		try {
			 // Carrega a tela com detalhes do filme selecionado
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("/view/TelaInformacoesFilme.fxml"));					
			telaInfoFilmes = (Pane) loader.load();
			TelaInformacoesFilmeController controller = loader.<TelaInformacoesFilmeController>getController();
	        controller.initData(filme);
	        
			Scene scene = new Scene(telaInfoFilmes);
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);            
            primaryStage.show();
            
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

	public void mostraTelaReproducaoFilme(Filme filme) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("/view/TelaReproducaoFilme.fxml"));					
			Pane tela = (Pane) loader.load();
            TelaReproducaoFilmeController controller = loader.getController();
            controller.setMainApp(this);
            controller.initData(filme);		
			Scene scene = new Scene(tela);
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);           
			primaryStage.show();
			controller.playMovie();              
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
}

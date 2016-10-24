package view;


import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.text.Text;
import model.MainApp;
import persistence.FilmeRepository;

public class TelaHomeController {

	private MainApp loginApp;
	
	@FXML
	private Button novoFilmeBT;
	
	@FXML
	private Text usuarioLogadoTX;
	
	@FXML
	private TextField buscaTF;
	
	@FXML
	private TilePane filmesTP;
	
	
	public TelaHomeController() {
									
	}
	
    /**
     * Inicializa a classe controller. Este método é chamado automaticamente
     *  após o arquivo fxml ter sido carregado.
     */
    @FXML
    private void initialize() {
		usuarioLogadoTX.setText(MainApp.getUsuarioLogado().getNome());
		
		novoFilmeBT.setVisible(MainApp.getUsuarioLogado().isAdmin());
	
    }	

	public void setMainApp(MainApp loginApp) {
        this.loginApp = loginApp;    
    }
	
	@FXML
	private void btnSair(){
		MainApp.setUsuarioLogado(null);
		loginApp.mostraTelaLogin();
	}
	
	@FXML
	private void handleNovoFilme(){
		loginApp.mostraTelaCadastroFilme();
	}
	
	@FXML
	private void handleBuscarFilme(){
		
	     FXMLLoader loader;
	        try {
				loader = new FXMLLoader();
		        loader.setLocation(MainApp.class.getResource("/view/FilmeLayout.fxml"));
				Pane panel = loader.load();
				FilmeLayoutController controller = loader.<FilmeLayoutController>getController();
		        controller.initData("Filme A", 1999, 90);
				filmesTP.getChildren().add(panel);
				
				loader = new FXMLLoader();
		        loader.setLocation(MainApp.class.getResource("/view/FilmeLayout.fxml"));
				filmesTP.getChildren().add(loader.load());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
		
		//FilmeRepository filmeRepository = new FilmeRepository();
		//filmesGP.add
	}	
}

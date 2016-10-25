package view;


import java.io.IOException;
import java.util.List;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.text.Text;
import model.Filme;
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
	
	private List<Filme> filmes;
	
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
		FilmeRepository filmeRepository = new FilmeRepository();
		
		filmes = filmeRepository.buscarTudo();
		updateFilmeTiles();
		
    }	

    private void updateFilmeTiles() {
    	filmesTP.getChildren().clear();
    	for (Filme filme : filmes) {
	        try {
	        	FXMLLoader loader = new FXMLLoader();
		        loader.setLocation(MainApp.class.getResource("/view/FilmeLayout.fxml"));
				Pane tile = loader.load();
				FilmeLayoutController controller = loader.<FilmeLayoutController>getController();
		        controller.initData(filme);
				filmesTP.getChildren().add(tile);
			} catch (IOException e) {
				e.printStackTrace();
			}			
		}
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
	private void handleNovoFilme() {
		loginApp.mostraTelaCadastroFilme();
	}
	
	@FXML
	private void handleBuscarFilme() {
		FilmeRepository filmeRepository = new FilmeRepository();
		filmes = filmeRepository.buscarPorTituloLikeOuAtorPrincipalLike(
				buscaTF.getText(), buscaTF.getText());
		updateFilmeTiles();	
	}	
}

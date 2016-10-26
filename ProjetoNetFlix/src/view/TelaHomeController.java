package view;


import java.io.IOException;
import java.util.List;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.text.Text;
import model.Filme;
import model.MainApp;
import persistence.FilmeRepository;
import util.DateUtil;

public class TelaHomeController {

	private MainApp mainApp;
	
	@FXML
	private Button novoFilmeBT;
	
	@FXML
	private Text usuarioLogadoTX;
	
	@FXML
	private TextField buscaTF;
	
	@FXML
	private TilePane filmesTP;
	
	@FXML
	private AnchorPane mainPane;	 
	
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
		
    }	

    private void updateFilmeTiles() {
    	filmesTP.getChildren().clear();
    	int idadeUsuarioLogado = DateUtil.calculateAge(MainApp.getUsuarioLogado().getDataNascimento());
    	for (Filme filme : filmes) {
    		if (filme.getIdade() > idadeUsuarioLogado) { // se a classificacao etaria do filme for maior que a idade do usuario, nao exibe-o
    			continue;
    		}
	        try {
	        	FXMLLoader loader = new FXMLLoader();
		        loader.setLocation(MainApp.class.getResource("/view/FilmeLayout.fxml"));
				Pane tile = loader.load();
				FilmeLayoutController controller = loader.<FilmeLayoutController>getController();
		        controller.initData(filme);
	            controller.setMainApp(mainApp);
				filmesTP.getChildren().add(tile);
			} catch (IOException e) {
				e.printStackTrace();
			}			
		}
    }
    
	public void setMainApp(MainApp loginApp) {
        this.mainApp = loginApp;    
    }
	
	@FXML
	private void btnSair(){
		MainApp.setUsuarioLogado(null);
		mainApp.mostraTelaLogin();
	}
	
	@FXML
	private void handleNovoFilme() {
		mainApp.mostraTelaCadastroFilme();
	}
	
	@FXML
	private void handleBuscarFilme() {
		FilmeRepository filmeRepository = new FilmeRepository();
		filmes = filmeRepository.buscarPorTituloLikeOuAtorPrincipalLike(
				buscaTF.getText(), buscaTF.getText());
		updateFilmeTiles();	
	}
	
	@FXML
	public void mostrarTodosOsFilmes() {
		FilmeRepository filmeRepository = new FilmeRepository();		
		filmes = filmeRepository.buscarTudo();
		updateFilmeTiles();
	}
	
	@FXML
	private void handleEditarUsuario() {
		mainApp.mostraTelaEdicaoUsuario(MainApp.getUsuarioLogado());
	}	
	
}

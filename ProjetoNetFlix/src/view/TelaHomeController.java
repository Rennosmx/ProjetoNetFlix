package view;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import model.MainApp;

public class TelaHomeController {

	private MainApp loginApp;
	
	@FXML
	private Button novoFilmeBT;
	
	@FXML
	private Text usuarioLogadoTX;
	
	@FXML
	private TextField buscaTF;
	
	@FXML
	private GridPane filmesGP;
	
	
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
		//TODO
	}	
}

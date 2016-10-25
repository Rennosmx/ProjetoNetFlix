package view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import model.MainApp;

public class TelaInformacoesFilmeController {

	private MainApp mainApp;
	
	@FXML
	private Text nomeFilme;
	
	@FXML
	private Text anoFilme, duracaoFilme, generoFilme;
	
	@FXML
	private Text diretorFilme;
	
	@FXML
	private Text atorPrincipalFilme;
	
	@FXML
	private TextArea descricaoFilme;
	
	@FXML
	private Button btnAssistirFilme;
	
	
	public TelaInformacoesFilmeController() {
	
	}
	
	@FXML
	private void assistirFilme(){
		//mainApp.mostraTelaReproducaoFilme();
	}

	@FXML
	private void cancelarFilme(){
		mainApp.mostraTelaHome();
	}

	
	public MainApp getMainApp() {
		return mainApp;
	}

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}
	
	
	
}

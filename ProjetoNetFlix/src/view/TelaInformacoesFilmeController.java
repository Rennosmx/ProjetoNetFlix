package view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import model.Filme;
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
	
	@FXML
	private ImageView capaIV;	
	
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

	public void initData(Filme filme) {
		nomeFilme.setText(filme.getTitulo());
		anoFilme.setText(filme.getAno() + "");
		generoFilme.setText(filme.getGenero() + "");
		duracaoFilme.setText(filme.getDuracao() + " minutos");
        capaIV.setImage(new Image("file:" + filme.getArquivoCapa()));	
        atorPrincipalFilme.setText(filme.getAtorPrincipal());
        
    }
	
	public MainApp getMainApp() {
		return mainApp;
	}

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}
	
	
	
}

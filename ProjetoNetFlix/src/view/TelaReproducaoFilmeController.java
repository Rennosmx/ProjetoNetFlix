package view;


import javafx.fxml.FXML;
import javafx.scene.web.WebView;
import model.Filme;
import model.MainApp;

public class TelaReproducaoFilmeController {

	private MainApp mainApp;
	private Filme filme;
	
	@FXML
	private WebView movieview;
	
	/**
     * Inicializa a classe controller. Este método é chamado automaticamente
     *  após o arquivo fxml ter sido carregado.
     */
    @FXML
    private void initialize() {
  
    }		
     
	@FXML
	private void voltar(){
		mainApp.mostraTelaHome();
	}
	
	public void initData(Filme filme) {
		this.filme = filme;       
    }

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;	
	}

	public void playMovie() {
	    movieview.getEngine().load(filme.getArquivoMidia());	
	}
	
}

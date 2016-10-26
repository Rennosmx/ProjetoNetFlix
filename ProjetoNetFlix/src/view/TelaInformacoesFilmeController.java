package view;

import javax.swing.JOptionPane;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import model.Filme;
import model.MainApp;
import persistence.FilmeRepository;

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
	private Text faixaEtariaFilme;
		
	@FXML
	private Button btnAssistirFilme;
	
	@FXML
	private Button editarFilmeBT;	
	
	@FXML
	private Button removerFilmeBT;	
	
	@FXML
	private ImageView capaIV;	
	
	private Filme filme;
	
	public TelaInformacoesFilmeController() {
	
	}
	   /**
     * Inicializa a classe controller. Este método é chamado automaticamente
     *  após o arquivo fxml ter sido carregado.
     */
    @FXML
    private void initialize() {
		editarFilmeBT.setVisible(MainApp.getUsuarioLogado().isAdmin());
		removerFilmeBT.setVisible(MainApp.getUsuarioLogado().isAdmin());
  
    }		
     
	@FXML
	private void assistirFilme(){
		//mainApp.mostraTelaReproducaoFilme();
	}

	@FXML
	private void cancelarFilme(){
		mainApp.mostraTelaHome();
	}
	
	@FXML
	private void editarFilme(){
		mainApp.mostraTelaEdicaoFilme(filme);
	}
	
	@FXML
	private void removerFilme(){
		int dialogResult = JOptionPane.showConfirmDialog (null, "Tem certeza de que deseja remover este filme?", "Aviso!", JOptionPane.YES_NO_OPTION);
		if (dialogResult == JOptionPane.YES_OPTION){
			FilmeRepository filmeRepository = new FilmeRepository();
			filmeRepository.excluir(filme.getId());
				
			try {
				mainApp.mostraTelaHome();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}		
		
	}
	
	public void initData(Filme filme) {
		this.filme = filme;
		nomeFilme.setText(filme.getTitulo());
		anoFilme.setText(filme.getAno() + "");
		generoFilme.setText(filme.getGenero() + "");
		duracaoFilme.setText(filme.getDuracao() + " minutos");
        capaIV.setImage(new Image("file:" + filme.getArquivoCapa()));	
        atorPrincipalFilme.setText(filme.getAtorPrincipal());
        diretorFilme.setText(filme.getDiretor());
        descricaoFilme.setText(filme.getDescricao());
        
    }
	
	public MainApp getMainApp() {
		return mainApp;
	}

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}
	
	
	
}

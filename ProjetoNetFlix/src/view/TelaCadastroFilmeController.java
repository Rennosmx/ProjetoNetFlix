package view;


import java.io.File;

import javax.swing.JOptionPane;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import model.Filme;
import model.MainApp;
import persistence.FilmeRepository;

public class TelaCadastroFilmeController {
			
	private MainApp mainApp;
	
	@FXML
	private TextField tituloTF;
	
	@FXML
	private TextField anoTF;
	
	@FXML
	private TextField duracaoTF;
	
	@FXML
	private TextField atorPrincipalTF;
	
	@FXML
	private TextField arquivoMidiaTF;	
	
	@FXML
	private ImageView capaIV;	

	@FXML
	private TextField idadeTF;	
	
	@FXML
	private ComboBox<Filme.Genero> generoCB;

	private String arquivoCapa;
	
    /**
     * Inicializa a classe controller. Este método é chamado automaticamente
     *  após o arquivo fxml ter sido carregado.
     */
    @FXML
    private void initialize() {
    	TextField[] numericTextFields = {
    			idadeTF, anoTF, duracaoTF	
    	};
    	
    	// force the field to be numeric only
    	for (TextField item : numericTextFields) {
        	item.textProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    if (!newValue.matches("\\d*")) {
                    	item.setText(newValue.replaceAll("[^\\d]", ""));
                    }
                }
            }); 			
		}
  	
		generoCB.getItems().setAll(Filme.Genero.values());
    }	
	
	@FXML
	private void btnFinalizarCadastro(){
		
		if (tituloTF.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Informe um título!", 
					"Erro", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		if (anoTF.getText() == null ) {
			JOptionPane.showMessageDialog(null, "Informe um ano!", 
					"Erro", JOptionPane.ERROR_MESSAGE);
			return;		
		}
		
		if (duracaoTF.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Informe uma duração!", 
					"Erro", JOptionPane.ERROR_MESSAGE);
			return;			
		}	
		
		if (generoCB.getValue() == null ) {
			JOptionPane.showMessageDialog(null, "Informe um gênero!", 
					"Erro", JOptionPane.ERROR_MESSAGE);
			return;			
		}
		
		if (arquivoMidiaTF.getText() == null) {
			JOptionPane.showMessageDialog(null, "Informe um arquivo de mídia!", 
					"Erro", JOptionPane.ERROR_MESSAGE);
			return;			
		}
		
		if (arquivoCapa == null) {
			JOptionPane.showMessageDialog(null, "Informe uma foto de capa!", 
					"Erro", JOptionPane.ERROR_MESSAGE);
			return;			
		}
		
		Filme filme = new Filme();
		filme.setTitulo(tituloTF.getText());
		filme.setAno(Integer.parseInt(anoTF.getText()));
		filme.setDuracao(Integer.parseInt(duracaoTF.getText()));
		filme.setGenero(generoCB.getValue());
		filme.setAtorPrincipal(atorPrincipalTF.getText());
		filme.setArquivoCapa(arquivoCapa);
		filme.setArquivoMidia(arquivoMidiaTF.getText());

		FilmeRepository filmeRepository = new FilmeRepository();
		filmeRepository.editarIncluir(filme);
		
		JOptionPane.showMessageDialog(null, "Filme cadastrado com sucesso!", 
				"Cadastro", JOptionPane.INFORMATION_MESSAGE);		
		
		try {
			mainApp.mostraTelaLogin();
		} catch (Exception e) {
			e.printStackTrace();
		}	
		
	}
	
	@FXML
	private void btnCancelarCadastro(){
		try {
			mainApp.mostraTelaHome();
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
    /**
     * Abre o FileChooser para permitir o usuário selecionar um arquivo
     * para carregar.
     */
    @FXML
    private void handleSelectArquivoMidia() {
        FileChooser fileChooser = new FileChooser();

        // Define um filtro de extensão
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Arquivo de vídeo", "*.mpg", "*.mpeg", "*.avi", "*.flv", "*.mp4", "*.mkv");
        fileChooser.getExtensionFilters().add(extFilter);

        // Mostra a janela de salvar arquivo
        File file = fileChooser.showOpenDialog(mainApp.getPrimaryStage());

        if (file != null) {
            arquivoMidiaTF.setText(file.getAbsolutePath());
        }
    }	
	
    /**
     * Abre o FileChooser para permitir o usuário selecionar uma capa do filme
     */
    @FXML
    private void handleSelectArquivoCapa() {
        FileChooser fileChooser = new FileChooser();

        // Define um filtro de extensão
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Arquivo de imagem", "*.png", "*.jpg", "*.gif", "*.jpeg");
        fileChooser.getExtensionFilters().add(extFilter);

        // Mostra a janela de salvar arquivo
        File file = fileChooser.showOpenDialog(mainApp.getPrimaryStage());

        if (file != null) {
            arquivoCapa = file.getAbsolutePath();
			Image img = new Image("file:" + arquivoCapa);

            capaIV.setImage(img);
        }
    }
    
    
	public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;    
    }
}

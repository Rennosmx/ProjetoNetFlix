package view;


import java.io.File;

import javax.swing.JOptionPane;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
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
	
	@FXML
	private TextField diretorTF;	
	
	@FXML
	private TextArea descricaoTA;
	
	@FXML
	private Button cadastrarBT;	
	
	private String arquivoCapa;
	
	private Filme filmeParaEdicao;
	
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
		
		if (tituloTF.getText() == null || tituloTF.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Informe um título!", 
					"Erro", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		if (anoTF.getText() == null || anoTF.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Informe um ano!", 
					"Erro", JOptionPane.ERROR_MESSAGE);
			return;		
		}
		
		if (duracaoTF.getText() == null || duracaoTF.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Informe uma duração!", 
					"Erro", JOptionPane.ERROR_MESSAGE);
			return;			
		}	
		
		if (diretorTF.getText() == null || diretorTF.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Informe um diretor!", 
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
		
		Filme filme = filmeParaEdicao == null ? new Filme() : filmeParaEdicao;
		filme.setTitulo(tituloTF.getText());
		filme.setAno(Integer.parseInt(anoTF.getText()));
		filme.setDuracao(Integer.parseInt(duracaoTF.getText()));
		filme.setGenero(generoCB.getValue());
		filme.setAtorPrincipal(atorPrincipalTF.getText());
		int idade = (idadeTF == null || idadeTF.getText().isEmpty()) ? 0 : Integer.parseInt(idadeTF.getText());
		filme.setIdade(idade);
		filme.setArquivoCapa(arquivoCapa);
		filme.setArquivoMidia(arquivoMidiaTF.getText());
		filme.setDiretor(diretorTF.getText());
		filme.setDescricao(descricaoTA.getText());
		
		FilmeRepository filmeRepository = new FilmeRepository();
		filmeRepository.editarIncluir(filme);
		
		if (filmeParaEdicao == null) {
			try {
				mainApp.mostraTelaHome();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			try {
				mainApp.mostraTelaInformacoesFilme(filme);
			} catch (Exception e) {
				e.printStackTrace();
			}			
		}
		
	}
	
	@FXML
	private void btnCancelarCadastro(){
		try {
			if (filmeParaEdicao != null) {
				mainApp.mostraTelaInformacoesFilme(filmeParaEdicao);
			} else {
				mainApp.mostraTelaHome();
			}
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

	public void setModoEdicao(Filme filme) {
		this.filmeParaEdicao = filme;
		cadastrarBT.setText("Alterar");
		tituloTF.setText(filme.getTitulo());
		anoTF.setText(filme.getAno() + "");
		generoCB.setValue(filme.getGenero());
		duracaoTF.setText(filme.getDuracao() + "");
		arquivoCapa = filme.getArquivoCapa();
        capaIV.setImage(new Image("file:" + arquivoCapa));	
        atorPrincipalTF.setText(filme.getAtorPrincipal());
        diretorTF.setText(filme.getDiretor());
        descricaoTA.setText(filme.getDescricao());	
        arquivoMidiaTF.setText(filme.getArquivoMidia());
        idadeTF.setText(filme.getIdade() + "");
	}
}

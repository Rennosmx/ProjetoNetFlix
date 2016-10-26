package view;

import java.time.Instant;
import java.time.ZoneId;
import java.util.Date;

import javax.swing.JOptionPane;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.MainApp;
import model.Usuario;
import persistence.UsuarioRepository;

public class TelaCadastroUsuarioController {

			
	private MainApp mainApp;
	
	@FXML
	private TextField cadastroNome;
	
	@FXML
	private DatePicker cadastroIdade;
	
	@FXML
	private TextField cadastroEmail;
	
	@FXML
	private PasswordField cadastroSenha;
	
	@FXML
	private Button cadastrarBT;
		
	private Usuario usuarioParaEdicao;
		
	@FXML
	private void btnFinalizarCadastro(){
		
		if (cadastroNome.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Informe um Nome", 
					"Erro", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		if (cadastroIdade.getValue() == null ) {
			JOptionPane.showMessageDialog(null, "Informe uma data de nascimento", 
					"Erro", JOptionPane.ERROR_MESSAGE);
			return;		
		}
		
		if (cadastroEmail.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Informe um Email", 
					"Erro", JOptionPane.ERROR_MESSAGE);
			return;			
		}
		
		if (cadastroSenha.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Informe uma Senha", 
					"Erro", JOptionPane.ERROR_MESSAGE);
			return;			
		}		
		
		Usuario usuario = usuarioParaEdicao == null ? new Usuario() : usuarioParaEdicao;		
		usuario.setNome(cadastroNome.getText());
		Date date = Date.from(cadastroIdade.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
		usuario.setDataNascimento(date);
		usuario.setNome(cadastroNome.getText());
		usuario.setEmail(cadastroEmail.getText());
		usuario.setSenha(cadastroSenha.getText());	
		
		UsuarioRepository usuarioRepository = new UsuarioRepository();
		
		Usuario usuarioExistente = usuarioRepository.buscar(usuario.getEmail());
		if (usuarioExistente != null && usuarioExistente.getId() != usuario.getId()) {
			JOptionPane.showMessageDialog(null, "Já existe uma conta com o email especificado.", 
					"Erro", JOptionPane.ERROR_MESSAGE);
			return;				
		}
		
		usuarioRepository.editarIncluir(usuario);
		
		if (usuarioParaEdicao == null) {
			JOptionPane.showMessageDialog(null, "Conta cadastrada com sucesso!", 
					"Conta criada", JOptionPane.INFORMATION_MESSAGE);	
			try {
				mainApp.mostraTelaLogin();
			} catch (Exception e) {
				e.printStackTrace();
			}	
		} else {
			try {
				mainApp.mostraTelaHome();
			} catch (Exception e) {
				e.printStackTrace();
			}			
		}

		
	}
	
	@FXML
	private void btnCancelarCadastro(){
		try {
			if (usuarioParaEdicao == null) {
				mainApp.mostraTelaLogin();
			} else {
				mainApp.mostraTelaHome();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	public void setMainApp(MainApp loginApp) {
        this.mainApp = loginApp;    
    }
		
	public void setModoEdicao(Usuario usuario) {
		this.usuarioParaEdicao = usuario;
		cadastrarBT.setText("Alterar");
		cadastroNome.setText(usuario.getNome());
		Date data = usuario.getDataNascimento();

		cadastroIdade.setValue(Instant.ofEpochMilli(data.getTime()).atZone(ZoneId.systemDefault()).toLocalDate());
		cadastroEmail.setText(usuario.getEmail());
		cadastroSenha.setText(usuario.getSenha());
	}	
}

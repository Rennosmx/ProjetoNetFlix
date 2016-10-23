package view;

import java.time.ZoneId;
import java.util.Date;

import javax.swing.JOptionPane;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import model.MainApp;
import model.Usuario;
import persistence.UsuarioRepository;

public class TelaCadastroUsuarioController {

			
	private MainApp loginApp;
	
	@FXML
	private TextField cadastroNome;
	
	@FXML
	private DatePicker cadastroIdade;
	
	@FXML
	private TextField cadastroEmail;
	
	@FXML
	private TextField cadastroSenha;
	
	
	
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
		
		Usuario usuario = new Usuario();
		usuario.setNome(cadastroNome.getText());
		Date date = Date.from(cadastroIdade.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
		usuario.setDataNascimento(date);
		usuario.setNome(cadastroNome.getText());
		usuario.setEmail(cadastroEmail.getText());
		usuario.setSenha(cadastroSenha.getText());	
		
		UsuarioRepository usuarioRepository = new UsuarioRepository();
		
		if (usuarioRepository.buscar(usuario.getEmail()) != null ) {
			JOptionPane.showMessageDialog(null, "Já existe uma conta com o email especificado.", 
					"Erro", JOptionPane.ERROR_MESSAGE);
			return;				
		}
		
		usuarioRepository.incluir(usuario);
		
		JOptionPane.showMessageDialog(null, "Conta cadastrada com sucesso!", 
				"Conta criada", JOptionPane.INFORMATION_MESSAGE);		
		
		try {
			loginApp.mostraTelaLogin();
		} catch (Exception e) {
			e.printStackTrace();
		}	
		
	}
	
	@FXML
	private void btnCancelarCadastro(){
		try {
			loginApp.mostraTelaLogin();
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	public void setMainApp(MainApp loginApp) {
        this.loginApp = loginApp;    
    }
		
	
}

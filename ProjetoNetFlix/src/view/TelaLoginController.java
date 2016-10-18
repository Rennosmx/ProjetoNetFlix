package view;

import javax.swing.JOptionPane;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.MainApp;
import model.Usuario;
import persistence.UsuarioRepository;

public class TelaLoginController {
		
	private MainApp loginApp;
	
	@FXML
	private TextField txEmail;
	
	@FXML
	private PasswordField txSenha;
	
	
	@FXML
	private void linkCadastrar(){
		loginApp.mostraTelaCadastro();
	}
	
	@FXML
	private void btnEntrar(){
				
		UsuarioRepository usuarioRepository = new UsuarioRepository();
		Usuario usuario = usuarioRepository.buscar(txEmail.getText());
						
		if (usuario != null && txSenha.getText().equals(usuario.getSenha())) {
			Main.setUsuarioLogado(usuario);
			try {
				loginApp.mostraTelaInicial();				
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			JOptionPane.showMessageDialog(null, "Login ou Senha inválidos", 
					"Erro", JOptionPane.ERROR_MESSAGE);
		}				
		
	}

	public void setMainApp(MainApp loginApp) {
        this.loginApp = loginApp;    
    }
	
}

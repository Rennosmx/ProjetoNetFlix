package view;

import java.text.SimpleDateFormat;

import javafx.fxml.FXML;
import javafx.scene.text.Text;
import model.LoginApp;

public class TelaInicialController {

	private LoginApp loginApp;
	
	@FXML
	private Text nomeUsuario;
	
	@FXML
	private Text idadeUsuario;
	
	@FXML
	private Text emailUsuario;
	
	public TelaInicialController() {
				
		nomeUsuario = new Text(Main.getUsuarioLogado().getNome());
		
		SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
		idadeUsuario = new Text(f.format(Main.getUsuarioLogado().getDataNascimento()));
		
		emailUsuario = new Text(Main.getUsuarioLogado().getEmail());						
	}

	public void setMainApp(LoginApp loginApp) {
        this.loginApp = loginApp;    
    }
	
	@FXML
	private void btnSair(){
		loginApp.mostraTelaInicial();
	}
	
	
}

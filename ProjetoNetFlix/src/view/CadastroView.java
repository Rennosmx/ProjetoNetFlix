package view;
	
import java.time.ZoneId;
import java.util.Date;

import javax.swing.JOptionPane;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import model.Usuario;
import persistence.UsuarioRepository;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;


public class CadastroView extends Application {
	private AnchorPane pane;
	private Label lbNome;
	private Label lbNascimento;
	private Label lbEmail;
	private Label lbSenha;
	private TextField txNome;
	private DatePicker txNascimento;
	private TextField txEmail;
	private PasswordField txSenha;	
	private Button btCriarConta;
	private Button btCancelar;
	private static Stage stage;
	
	@Override
	public void start(Stage stage) {
		try {
			initComponents();
			initListeners();
			Scene scene = new Scene(pane);
			stage.setScene(scene);
			stage.setResizable(false);
			stage.setTitle("Criar conta");
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			stage.show();
			initLayout();
			CadastroView.stage = stage;
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
		
	private void initComponents() {
		pane = new AnchorPane();
		pane.setPrefSize(400, 300);
		lbNome = new Label("Nome:");
		txNome = new TextField();
		lbNascimento = new Label("Nascimento:");
		txNascimento = new DatePicker();
		lbEmail = new Label("Email:");
		txEmail = new TextField();		
		lbSenha = new Label("Senha:");
		txSenha = new PasswordField();
		
		btCriarConta = new Button("Criar conta");
		btCancelar = new Button("Cancelar");
		
		pane.getChildren().addAll(
				txNome, txSenha, txEmail, txNascimento, btCriarConta, btCancelar,
				lbNome, lbNascimento, lbEmail, lbSenha);
	}
	
	private void initLayout() {
		lbNome.setLayoutX(50);
		lbNome.setLayoutY(50);	
		txNome.setLayoutX(150);
		txNome.setLayoutY(50);
		
		lbNascimento.setLayoutX(50);
		lbNascimento.setLayoutY(100);	
		txNascimento.setLayoutX(150);
		txNascimento.setLayoutY(100);	
		
		lbEmail.setLayoutX(50);
		lbEmail.setLayoutY(150);	
		txEmail.setLayoutX(150);
		txEmail.setLayoutY(150);
		
		lbSenha.setLayoutX(50);
		lbSenha.setLayoutY(200);	
		txSenha.setLayoutX(150);
		txSenha.setLayoutY(200);
		
		btCriarConta.setLayoutX(50);
		btCriarConta.setLayoutY(250);
		
		btCancelar.setLayoutX(150);
		btCancelar.setLayoutY(250);			
	}
	
	private void initListeners() {
		btCriarConta.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				criarConta();				
			}
		});
		
		btCancelar.setOnAction(new EventHandler<ActionEvent>() {			
			@Override
			public void handle(ActionEvent event) {
				try {
					new LoginView().start(new Stage());
					getStage().close();
				} catch (Exception e) {
					e.printStackTrace();
				}			
			}
		});
	}

	protected void criarConta() {
		if (txNome.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Nome é obrigatório", 
					"Erro", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		if (txNascimento.getValue() == null ) {
			JOptionPane.showMessageDialog(null, "Data de nascimento é obrigatório", 
					"Erro", JOptionPane.ERROR_MESSAGE);
			return;		
		}
		
		if (txEmail.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Email é obrigatório", 
					"Erro", JOptionPane.ERROR_MESSAGE);
			return;			
		}
		
		if (txSenha.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Senha é obrigatório", 
					"Erro", JOptionPane.ERROR_MESSAGE);
			return;			
		}		
		
		Usuario usuario = new Usuario();
		usuario.setNome(txNome.getText());
		Date date = Date.from(txNascimento.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
		usuario.setDataNascimento(date);
		usuario.setNome(txNome.getText());
		usuario.setEmail(txEmail.getText());
		usuario.setSenha(txSenha.getText());	
		
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
			new LoginView().start(new Stage());
			getStage().close();
		} catch (Exception e) {
			e.printStackTrace();
		}	
		
	}

	public static Stage getStage() {
		return stage;
	}	
}

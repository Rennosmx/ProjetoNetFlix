package view;
	
import javax.swing.JOptionPane;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import model.Usuario;
import persistence.UsuarioRepository;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;


public class LoginView extends Application {
	private AnchorPane pane;
	private TextField txLogin;
	private PasswordField txSenha;
	private Button btEntrar;
	private Hyperlink linkCriarConta;
	private static Stage stage;
	
	@Override
	public void start(Stage stage) {
		try {
			initComponents();
			initListeners();
			Scene scene = new Scene(pane);
			stage.setScene(scene);
			stage.setResizable(false);
			stage.setTitle("Login - Netfx");
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			stage.show();
			initLayout();
			LoginView.stage = stage;
			
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
		txLogin = new TextField();
		txLogin.setPromptText("Digite seu email...");
		txSenha = new PasswordField();
		txSenha.setPromptText("Digite sua senha...");
		
		btEntrar = new Button();
		btEntrar.setText("Entrar");
		linkCriarConta = new Hyperlink();
		linkCriarConta.setText("Criar uma conta");
		
		pane.getChildren().addAll(txLogin, txSenha, btEntrar, linkCriarConta);
	}
	
	private void initLayout() {
		txLogin.setLayoutX( (pane.getWidth() - txLogin.getWidth()) / 2.0 );
		txLogin.setLayoutY(50);
		
		txSenha.setLayoutX( (pane.getWidth() - txSenha.getWidth()) / 2.0 );
		txSenha.setLayoutY(100);
		
		btEntrar.setLayoutX( (pane.getWidth() - btEntrar.getWidth()) / 2.0 );
		btEntrar.setLayoutY(150);
		
		linkCriarConta.setLayoutX( (pane.getWidth() - linkCriarConta.getWidth()) / 2.0 );
		linkCriarConta.setLayoutY(200);

		btEntrar.getStyleClass().addAll("btEntrar");
		pane.getStyleClass().add("pane");				
	}
	
	private void initListeners() {
		btEntrar.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				logar();				
			}
		});
		
		linkCriarConta.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					new CadastroView().start(new Stage());
					getStage().close();
				} catch (Exception e) {
					e.printStackTrace();
				}							
			}
		});	
	}

	protected void logar() {
		UsuarioRepository usuarioRepository = new UsuarioRepository();
		Usuario usuario = usuarioRepository.buscar(txLogin.getText());
		
		if (usuario != null && txSenha.getText().equals(usuario.getSenha())) {
			Main.setUsuarioLogado(usuario);
			try {
				new HomeView().start(new Stage());
				LoginView.getStage().close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			JOptionPane.showMessageDialog(null, "Login ou Senha inválidos", 
					"Erro", JOptionPane.ERROR_MESSAGE);
		}
	}

	protected void fecharAplicacao() {
		System.out.println("flw");
		System.exit(0);
	}

	public static Stage getStage() {
		return stage;
	}	
}

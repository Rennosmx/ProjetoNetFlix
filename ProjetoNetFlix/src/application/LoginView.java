package application;
	
import javax.swing.JOptionPane;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;


public class LoginView extends Application {
	private AnchorPane pane;
	private TextField txLogin;
	private PasswordField txSenha;
	private Button btEntrar;
	private Button btSair;
	private static Stage stage;
	
	@Override
	public void start(Stage stage) {
		try {
			initComponents();
			initListeners();
			Scene scene = new Scene(pane);
			stage.setScene(scene);
			scene.getStylesheets().add("file:resources/css/login.css");
			stage.setResizable(false);
			stage.setTitle("Login - Netfx");
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
		txLogin.setPromptText("Digite seu login...");
		txSenha = new PasswordField();
		txSenha.setPromptText("Digite sua senha...");
		
		btEntrar = new Button();
		btEntrar.setText("Entrar");
		btSair = new Button();
		btSair.setText("Sair");
		
		pane.getChildren().addAll(txLogin, txSenha, btEntrar, btSair);
	}
	
	private void initLayout() {
		txLogin.setLayoutX( (pane.getWidth() - txLogin.getWidth()) / 2.0 );
		txLogin.setLayoutY(50);
		
		txSenha.setLayoutX( (pane.getWidth() - txSenha.getWidth()) / 2.0 );
		txSenha.setLayoutY(100);
		
		btEntrar.setLayoutX( (pane.getWidth() - btEntrar.getWidth()) / 2.0 );
		btEntrar.setLayoutY(150);
		
		btSair.setLayoutX( (pane.getWidth() - btSair.getWidth()) / 2.0 );
		btSair.setLayoutY(200);	
		
		btEntrar.getStyleClass().addAll("btEntrar");
		btSair.getStyleClass().addAll("btSair");
		pane.getStyleClass().add("pane");				
	}
	
	private void initListeners() {
		btEntrar.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				logar();				
			}
		});
		
		btSair.setOnAction(new EventHandler<ActionEvent>() {			
			@Override
			public void handle(ActionEvent event) {
				fecharAplicacao();				
			}
		});
	}

	protected void logar() {
		if (txLogin.getText().equals("admin") && txSenha.getText().equals("123")) {			
			try {
				// TODO new HomeView().start(new Stage());
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

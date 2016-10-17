package view;
	
import java.text.SimpleDateFormat;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class HomeView extends Application {
	private AnchorPane pane;
	private Label lbNome;
	private Label lbNascimento;
	private Label lbEmail;	
	private Button btSair;
	
	private static Stage stage;
	
	@Override
	public void start(Stage stage) {
		try {
			initComponents();
			initListeners();
			Scene scene = new Scene(pane);
			stage.setScene(scene);
			stage.setResizable(false);
			stage.setTitle("Home - Netfx");
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			stage.show();
			initLayout();
			HomeView.stage = stage;
			
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
		lbNome = new Label("Nome: " + Main.getUsuarioLogado().getNome());
		
		SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
		lbNascimento = new Label("Nascimento: " + f.format(Main.getUsuarioLogado().getDataNascimento()));
		lbEmail = new Label("Email: " + Main.getUsuarioLogado().getEmail());
		btSair = new Button("Sair");
		
		pane.getChildren().addAll(
				btSair,
				lbNome, lbNascimento, lbEmail);		
	}
	
	private void initLayout() {
		lbNome.setLayoutX(50);
		lbNome.setLayoutY(50);	
		
		lbNascimento.setLayoutX(50);
		lbNascimento.setLayoutY(100);	
		
		lbEmail.setLayoutX(50);
		lbEmail.setLayoutY(150);
		
		btSair.setLayoutX(50);
		btSair.setLayoutY(200);			
	}
	
	private void initListeners() {
		btSair.setOnAction(new EventHandler<ActionEvent>() {			
			@Override
			public void handle(ActionEvent event) {
				Main.setUsuarioLogado(null);
				try {
					new LoginView().start(new Stage());
					getStage().close();
				} catch (Exception e) {
					e.printStackTrace();
				}			
			}
		});		
	}

	public static Stage getStage() {
		return stage;
	}	
}

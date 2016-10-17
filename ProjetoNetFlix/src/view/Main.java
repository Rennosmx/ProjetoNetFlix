package view;
	
import javafx.application.Application;
import javafx.stage.Stage;
import model.Usuario;


public class Main extends Application {
	private static Usuario usuarioLogado;

	@Override
	public void start(Stage primaryStage) {
		try {
			new LoginView().start(new Stage());
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}

	public static Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

	public static void setUsuarioLogado(Usuario usuarioLogado) {
		Main.usuarioLogado = usuarioLogado;
	}
}

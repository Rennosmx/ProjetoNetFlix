package view;


import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import model.MainApp;

public class FilmeLayoutController {
			
	private MainApp mainApp;
	
	@FXML
	private Label tituloLB;
	
	@FXML
	private Label anoLB;
	
	@FXML
	private Label duracaoLB;
	
	@FXML
	private ImageView capaIV;	
	
    /**
     * Inicializa a classe controller. Este método é chamado automaticamente
     *  após o arquivo fxml ter sido carregado.
     */
    @FXML
    private void initialize() {
 
    }	
    
	public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;    
    }
	
	public void initData(String a, int b, int c) {
		tituloLB.setText(a);
		anoLB.setText(b + "");
		duracaoLB.setText(c + "");
	}
}
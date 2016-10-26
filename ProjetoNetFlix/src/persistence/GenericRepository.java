package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GenericRepository {

	private String driver = "org.postgresql.Driver";
	private String url = "jdbc:postgresql://localhost/postgres";
	private String user = "postgres";
	private String password = "cativeiro";
	protected Connection conn;

	public GenericRepository() {
		// método construtor quando chamado já irá se conectar ao banco de dados
		try {
			Class.forName(this.driver);
			this.conn = DriverManager.getConnection(url, user, password);
			//this.conn.setAutoCommit(false);
		} catch (ClassNotFoundException e) {
			System.out.println("Não foi possivel encontrar " 
					+ "o driver de banco: " + e.getMessage());
		} catch(SQLException e){
			System.out.println("Erro ao conectar com o banco: " + e.getMessage());
		}
	}

	// desconecta do banco de dados
	public void desconectar(){
		try {
			if (this.conn != null) {
				this.conn.close();
				this.conn = null;
			}
		} catch(SQLException e){
			System.out.println("Erro tentanto fechar a conexão com o banco: " + e.getMessage());
		}
	}
}

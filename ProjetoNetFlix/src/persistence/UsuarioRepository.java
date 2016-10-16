package persistence;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Usuario;

public class UsuarioRepository {

	private String driver = "org.postgresql.Driver";
	private String url = "jdbc:postgresql://localhost/postgres";
	private String user = "postgres";
	private String password = "postgres";
	private Connection conn;

	public UsuarioRepository() {
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

	public Usuario buscar(String email) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Usuario usuario = null;
        
        // query que será executada
        String sql = "SELECT id, nome, nascimento, email, senha FROM usuario"
        		+ " WHERE email=?";

        try {
            // executa a query e guarda o resultado no RecordSet rs
        	
            stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, email);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                usuario = new Usuario();           	
            	usuario.setId(rs.getInt("id"));
            	usuario.setNome(rs.getString("nome"));
            	usuario.setDataNascimento(rs.getDate("nascimento"));
            	usuario.setEmail(rs.getString("email")); 
            	usuario.setSenha(rs.getString("senha"));
            }
            
        } catch(SQLException e){
            System.out.println("Erro ao buscar usuário: " + e.getMessage());
        } finally {
            try {
                // fecha o stmt e o rs
                if (stmt != null) stmt.close();
                if (rs != null) rs.close();
            } catch (SQLException e){
                System.out.println("Erro ao tentar fechar o stmt e o rs: " + e.getMessage());
            }
        }
        
        return usuario;
	}
	

	/**
	 * Inclui um usuario no banco de dados.
	 *
	 */
	public void incluir(Usuario usuario) {
		PreparedStatement stmt = null;
		// query que será executada
		String sql = "INSERT INTO usuario (nome, nascimento, email, senha)"
				+ " VALUES (?, ?, ?, ?)";
		try {
			stmt = this.conn.prepareStatement(sql);
			stmt.setString(1, usuario.getNome());
			stmt.setDate(2, new Date(usuario.getDataNascimento().getTime()));
			stmt.setString(3, usuario.getEmail());
			stmt.setString(4, usuario.getSenha());
			stmt.executeUpdate();
			System.out.println("Usuário incluído com sucesso!");
		} catch(SQLException e){
			System.out.println("Erro ao incluir usuário: " + e.getMessage());
		} finally {
			try {
				if (stmt != null) stmt.close();
			} catch (SQLException e){
				System.out.println("Erro ao tentar fechar o stmt: " + e.getMessage());
			}
		}
	}
	
	/**
	 * Exclui um usuario do banco de dados.
	 * 
	 * @param email
	 */
	public void excluir(String email) {		
		PreparedStatement stmt = null;
		
		// query que será executada
		String sql = "DELETE FROM usuario WHERE email=?";

		try {
			stmt = this.conn.prepareStatement(sql);
			stmt.setString(1, email);
			stmt.executeUpdate();
			System.out.println("Usuário excluído com sucesso!");
		} catch(SQLException e){
			System.out.println("Erro ao excluir usuário: " + e.getMessage());
		} finally {
			try {
				if (stmt != null) stmt.close();
			} catch (SQLException e){
				System.out.println("Erro ao tentar fechar o stmt: " + e.getMessage());
			}
		}		
	}
	
	/**
	 * Edita um usuario do banco de dados.
	 * 
	 * @param usuario
	 */
	public void editar(Usuario usuario) {		
		PreparedStatement stmt = null;
		// query que será executada
		String sql = "UPDATE usuario"
				+ " SET nome=?,nascimento=?,email=?,senha=?"
				+ " WHERE id=?";
		try {
			stmt = this.conn.prepareStatement(sql);
			stmt.setString(1, usuario.getNome());
			stmt.setDate(2, new Date(usuario.getDataNascimento().getTime()));
			stmt.setString(3, usuario.getEmail());
			stmt.setString(4, usuario.getSenha());
			stmt.executeUpdate();
			System.out.println("Usuário editado com sucesso!");
		} catch(SQLException e) {
			System.out.println("Erro ao editar usuario: " + e.getMessage());
		} finally {
			try {
				if (stmt != null) stmt.close();
			} catch (SQLException e) {
				System.out.println("erro ao tentar fechar o stmt: " + e.getMessage());
			}
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

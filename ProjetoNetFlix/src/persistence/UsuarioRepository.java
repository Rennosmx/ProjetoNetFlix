package persistence;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Usuario;

public class UsuarioRepository extends GenericRepository {

	public Usuario buscar(String email) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Usuario usuario = null;
        
        // query que será executada
        String sql = "SELECT * FROM usuario"
        		+ " WHERE email=?";

        try {
            // executa a query e guarda o resultado no ResultSet rs
        	
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
            	usuario.setAdmin(rs.getBoolean("is_admin"));
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
	 * Edita/Inclui um usuario no banco de dados.
	 *
	 */
	public void editarIncluir(Usuario usuario) {
		PreparedStatement stmt = null;
		// query que será executada
		String sqlIncluir = "INSERT INTO usuario "
				+ "(nome, nascimento, email, senha, is_admin)"
				+ " VALUES (?, ?, ?, ?, FALSE)";
		
		String sqlEditar = "UPDATE usuario"
				+ " SET nome=?, nascimento=?, email=?, senha=?"
				+ " WHERE id=?";		
		
		String sql = usuario.getId() == 0 ? sqlIncluir : sqlEditar;
		
		try {
			stmt = this.conn.prepareStatement(sql);
			stmt.setString(1, usuario.getNome());
			stmt.setDate(2, new Date(usuario.getDataNascimento().getTime()));
			stmt.setString(3, usuario.getEmail());
			stmt.setString(4, usuario.getSenha());
			
			if (usuario.getId() != 0) {
				stmt.setInt(stmt.getParameterMetaData().getParameterCount(), usuario.getId());
			}			
			
			stmt.executeUpdate();
			System.out.println("Usuário editado/incluído com sucesso!");
		} catch(SQLException e){
			System.out.println("Erro ao editar/incluir usuário: " + e.getMessage());
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

}

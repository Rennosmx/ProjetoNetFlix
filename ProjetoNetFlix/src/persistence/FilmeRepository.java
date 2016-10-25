package persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Filme;
import model.Filme.Genero;

public class FilmeRepository extends GenericRepository {
	
	
	private List<Filme> fillFromResultSet(ResultSet rs) throws SQLException {
		List<Filme> result = new ArrayList<>();
		
        while (rs.next()) {
            Filme filme = new Filme();           	
            filme.setId(rs.getInt("id"));
            filme.setTitulo(rs.getString("titulo"));
            filme.setAno(rs.getInt("ano"));
        	filme.setDuracao(rs.getInt("duracao")); 
        	filme.setGenero(Genero.valueOf(rs.getString("genero")));
        	filme.setAtorPrincipal(rs.getString("ator_principal"));
        	filme.setIdade(rs.getInt("idade"));
        	filme.setArquivoCapa(rs.getString("arquivo_capa"));
        	filme.setArquivoMidia(rs.getString("arquivo_midia"));
        	result.add(filme);
        }
        
        return result;
	}
	
	public Filme buscar(int id) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Filme filme = null;
        
        // query que será executada
        String sql = "SELECT * FROM filme WHERE id=?";

        try {
            // executa a query e guarda o resultado no RecordSet rs
        	
            stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            
        	List<Filme> filmes = fillFromResultSet(rs);
        	if (!filmes.isEmpty()) {
        		filme = filmes.get(0);
        	}
        } catch(SQLException e){
            System.out.println("Erro ao buscar filme: " + e.getMessage());
        } finally {
            try {
                // fecha o stmt e o rs
                if (stmt != null) stmt.close();
                if (rs != null) rs.close();
            } catch (SQLException e){
                System.out.println("Erro ao tentar fechar o stmt e o rs: " + e.getMessage());
            }
        }
        
        return filme;
	}
	
	public List<Filme> buscarTudo() {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Filme> filmes = null;
        
        // query que será executada
        String sql = "SELECT * FROM filme";

        try {
            // executa a query e guarda o resultado no RecordSet rs
        	
            stmt = this.conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            
        	filmes = fillFromResultSet(rs);

        } catch(SQLException e){
            System.out.println("Erro ao buscar filme: " + e.getMessage());
        } finally {
            try {
                // fecha o stmt e o rs
                if (stmt != null) stmt.close();
                if (rs != null) rs.close();
            } catch (SQLException e){
                System.out.println("Erro ao tentar fechar o stmt e o rs: " + e.getMessage());
            }
        }
        
        return filmes;
	}	

	public List<Filme> buscarPorTituloLikeOuAtorPrincipalLike(String titulo, String atorPrincipal) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Filme> filmes = null;
        
        // query que será executada
        String sql = "SELECT * FROM filme"
        		+ " WHERE LOWER(titulo) LIKE ?"
        		+ " OR LOWER(ator_principal) LIKE ?";

        try {
            // executa a query e guarda o resultado no RecordSet rs
        	
            stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, "%" + titulo.toLowerCase() + "%");
            stmt.setString(2, "%" + atorPrincipal.toLowerCase() + "%");
            rs = stmt.executeQuery();
            
        	filmes = fillFromResultSet(rs);

        } catch(SQLException e){
            System.out.println("Erro ao buscar filme: " + e.getMessage());
        } finally {
            try {
                // fecha o stmt e o rs
                if (stmt != null) stmt.close();
                if (rs != null) rs.close();
            } catch (SQLException e){
                System.out.println("Erro ao tentar fechar o stmt e o rs: " + e.getMessage());
            }
        }
        
        return filmes;
	}	
	
	/**
	 * Edita/Inclui um filme no banco de dados.
	 * 
	 * @param filme
	 */
	public void editarIncluir(Filme filme) {		
		PreparedStatement stmt = null;
		
		// query que será executada
		String sqlIncluir = "INSERT INTO filme "
				+ "(titulo, genero, ano, ator_principal, idade, duracao, arquivo_capa, arquivo_midia)"
				+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		
		String sqlEditar = "UPDATE usuario"
				+ " SET titulo=?, genero=?, ano=?, ator_principal=?, idade=?, duracao=?, arquivo_capa=?, arquivo_midia=?"
				+ " WHERE id=?";
		
		String sql = filme.getId() == 0 ? sqlIncluir : sqlEditar;
		
		try {
			stmt = this.conn.prepareStatement(sql);
			stmt.setString(1, filme.getTitulo());
			stmt.setString(2, filme.getGenero().name());
			stmt.setInt(3, filme.getAno());
			stmt.setString(4, filme.getAtorPrincipal());			
			stmt.setInt(5, filme.getIdade());
			stmt.setInt(6, filme.getDuracao());
			stmt.setString(7, filme.getArquivoCapa());			
			stmt.setString(8, filme.getArquivoMidia());			
			
			if (filme.getId() != 0) {
				stmt.setInt(stmt.getParameterMetaData().getParameterCount(), filme.getId());
			}
			
			stmt.executeUpdate();
			System.out.println("Usuário editado/incluído com sucesso!");
		} catch(SQLException e) {
			System.out.println("Erro ao editar/incluir usuario: " + e.getMessage());
		} finally {
			try {
				if (stmt != null) stmt.close();
			} catch (SQLException e) {
				System.out.println("erro ao tentar fechar o stmt: " + e.getMessage());
			}
		}	
	}

}

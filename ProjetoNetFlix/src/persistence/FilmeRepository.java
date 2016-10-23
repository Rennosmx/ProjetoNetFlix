package persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Filme;
import model.Filme.Genero;

public class FilmeRepository extends GenericRepository {

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
            
            while (rs.next()) {
                filme = new Filme();           	
                filme.setId(rs.getInt("id"));
                filme.setTitulo(rs.getString("titulo"));
                filme.setAno(rs.getInt("ano"));
            	filme.setDuracao(rs.getInt("duracao")); 
            	filme.setGenero(Genero.valueOf(rs.getString("genero")));
            	filme.setAtorPrincipal(rs.getString("ator_principal"));
            	filme.setIdade(rs.getInt("idade"));
            	filme.setArquivoCapa(rs.getString("arquivo_capa"));
            	filme.setArquivoMidia(rs.getString("arquivo_midia"));
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

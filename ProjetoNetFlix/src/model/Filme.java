package model;

public class Filme {
	public enum Genero {
		TERROR("Terror"),
		SUSPENSE("Suspense"),
		COMEDIA("Comédia"),
		ROMANCE("Romance"),
		AVENTURA("Aventura"),
		ACAO("Ação"),
		FICCAO_CIENTIFICA("Ficção científica"),
		SERIADO("Seriado"),
		DRAMA("Drama"),
		OUTROS("Outros");
		
		private String descricao;
		
		public String toString() {
			return descricao;
		}
		
		Genero(String descricao) {
			this.descricao = descricao;
		}
	}	
	
	private int id;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	private String titulo;
	private int ano;
	private Genero genero;
	private String atorPrincipal;
	private int duracao; // duracao do filme, em minutos
	private int idade;
	private String arquivoMidia;
	private String arquivoCapa;
	private String diretor;
	private String descricao;

	
	public String getDiretor() {
		return diretor;
	}

	public void setDiretor(String diretor) {
		this.diretor = diretor;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getArquivoMidia() {
		return arquivoMidia;
	}

	public void setArquivoMidia(String arquivoMidia) {
		this.arquivoMidia = arquivoMidia;
	}

	public String getArquivoCapa() {
		return arquivoCapa;
	}

	public void setArquivoCapa(String arquivoCapa) {
		this.arquivoCapa = arquivoCapa;
	}

	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public int getAno() {
		return ano;
	}
	public void setAno(int ano) {
		this.ano = ano;
	}
	public Genero getGenero() {
		return genero;
	}
	public void setGenero(Genero genero) {
		this.genero = genero;
	}
	public String getAtorPrincipal() {
		return atorPrincipal;
	}
	public void setAtorPrincipal(String atorPrincipal) {
		this.atorPrincipal = atorPrincipal;
	}
	public int getDuracao() {
		return duracao;
	}
	public void setDuracao(int duracao) {
		this.duracao = duracao;
	}
	public int getIdade() {
		return idade;
	}
	public void setIdade(int idade) {
		this.idade = idade;
	}
	
	
}

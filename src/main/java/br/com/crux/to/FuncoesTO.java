
package br.com.crux.to;

public class FuncoesTO {

	private Long id;
	private String nome;
	private CargoTO cargo;
	private Double valorSalario;
	private String descricao;
	private String atribuicoes;
	private String conhecimentosEspecificos;
	private String comportamentosEsperados;
	

	public FuncoesTO() {}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public CargoTO getCargo() {
		return cargo;
	}


	public void setCargo(CargoTO cargo) {
		this.cargo = cargo;
	}


	public Double getValorSalario() {
		return valorSalario;
	}


	public void setValorSalario(Double valorSalario) {
		this.valorSalario = valorSalario;
	}


	public String getDescricao() {
		return descricao;
	}


	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


	public String getAtribuicoes() {
		return atribuicoes;
	}


	public void setAtribuicoes(String atribuicoes) {
		this.atribuicoes = atribuicoes;
	}


	public String getConhecimentosEspecificos() {
		return conhecimentosEspecificos;
	}


	public void setConhecimentosEspecificos(String conhecimentosEspecificos) {
		this.conhecimentosEspecificos = conhecimentosEspecificos;
	}


	public String getComportamentosEsperados() {
		return comportamentosEsperados;
	}


	public void setComportamentosEsperados(String comportamentosEsperados) {
		this.comportamentosEsperados = comportamentosEsperados;
	}

	

}
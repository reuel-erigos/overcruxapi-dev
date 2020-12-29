package br.com.crux.to;

public class ColaboradoresGestaoPessoalTO {
	
	private Long idPessoaFisica;
	private Long idColaborador;
	private String nomeCobaborador;
	private String email;
	private String unidade;
	private String departamento;
	private String cargo;
	private String funcao;
	
	public ColaboradoresGestaoPessoalTO() {
	}

	public Long getIdPessoaFisica() {
		return idPessoaFisica;
	}

	public void setIdPessoaFisica(Long idPessoaFisica) {
		this.idPessoaFisica = idPessoaFisica;
	}

	public Long getIdColaborador() {
		return idColaborador;
	}

	public void setIdColaborador(Long idColaborador) {
		this.idColaborador = idColaborador;
	}

	public String getNomeCobaborador() {
		return nomeCobaborador;
	}

	public void setNomeCobaborador(String nomeCobaborador) {
		this.nomeCobaborador = nomeCobaborador;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUnidade() {
		return unidade;
	}

	public void setUnidade(String unidade) {
		this.unidade = unidade;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String getFuncao() {
		return funcao;
	}

	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}

	
}
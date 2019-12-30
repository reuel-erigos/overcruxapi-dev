package br.com.crux.to;

public class DepartamentoTO  {

	private Long idDepartamento;
	private String cdUnidadeDepartamento;
	private String dsEnderecoDepartamento;
	private String nmDepartamento;
	private String nrTelefoneDepartamento;
	private DepartamentoTO departamentoSuperior;
	private UnidadeTO unidade;
	private Long usuarioAlteracao;

	
	public DepartamentoTO() {
	}

	public Long getIdDepartamento() {
		return this.idDepartamento;
	}

	public void setIdDepartamento(Long idDepartamento) {
		this.idDepartamento = idDepartamento;
	}

	public String getCdUnidadeDepartamento() {
		return this.cdUnidadeDepartamento;
	}

	public void setCdUnidadeDepartamento(String cdUnidadeDepartamento) {
		this.cdUnidadeDepartamento = cdUnidadeDepartamento;
	}

	public String getDsEnderecoDepartamento() {
		return this.dsEnderecoDepartamento;
	}

	public void setDsEnderecoDepartamento(String dsEnderecoDepartamento) {
		this.dsEnderecoDepartamento = dsEnderecoDepartamento;
	}

	public String getNmDepartamento() {
		return this.nmDepartamento;
	}

	public void setNmDepartamento(String nmDepartamento) {
		this.nmDepartamento = nmDepartamento;
	}

	public String getNrTelefoneDepartamento() {
		return this.nrTelefoneDepartamento;
	}

	public void setNrTelefoneDepartamento(String nrTelefoneDepartamento) {
		this.nrTelefoneDepartamento = nrTelefoneDepartamento;
	}

	public DepartamentoTO getDepartamentoSuperior() {
		return this.departamentoSuperior;
	}

	public void setDepartamentoSuperior(DepartamentoTO departamentosUnidade) {
		this.departamentoSuperior = departamentosUnidade;
	}

	public UnidadeTO getUnidade() {
		return this.unidade;
	}

	public void setUnidade(UnidadeTO unidade) {
		this.unidade = unidade;
	}

	public Long getUsuarioAlteracao() {
		return usuarioAlteracao;
	}

	public void setUsuarioAlteracao(Long usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}


}
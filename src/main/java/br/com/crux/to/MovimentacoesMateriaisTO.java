package br.com.crux.to;

public class MovimentacoesMateriaisTO {

	private Long id;
	private String descricaoMovimentacaoMaterial;
	private DepartamentoTO departamento;
	private EmpresaTO empresa;
	private ProgramaTO programa;
	private ProjetoTO projeto;
	private UnidadeTO unidade;
	private String numeroDocumento;
	private String tipoMovimentacao;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescricaoMovimentacaoMaterial() {
		return descricaoMovimentacaoMaterial;
	}
	public void setDescricaoMovimentacaoMaterial(String descricaoMovimentacaoMaterial) {
		this.descricaoMovimentacaoMaterial = descricaoMovimentacaoMaterial;
	}
	public DepartamentoTO getDepartamento() {
		return departamento;
	}
	public void setDepartamento(DepartamentoTO departamento) {
		this.departamento = departamento;
	}
	public EmpresaTO getEmpresa() {
		return empresa;
	}
	public void setEmpresa(EmpresaTO empresa) {
		this.empresa = empresa;
	}
	public ProgramaTO getPrograma() {
		return programa;
	}
	public void setPrograma(ProgramaTO programa) {
		this.programa = programa;
	}
	public ProjetoTO getProjeto() {
		return projeto;
	}
	public void setProjeto(ProjetoTO projeto) {
		this.projeto = projeto;
	}
	public UnidadeTO getUnidade() {
		return unidade;
	}
	public void setUnidade(UnidadeTO unidade) {
		this.unidade = unidade;
	}
	public String getNumeroDocumento() {
		return numeroDocumento;
	}
	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}
	public String getTipoMovimentacao() {
		return tipoMovimentacao;
	}
	public void setTipoMovimentacao(String tipoMovimentacao) {
		this.tipoMovimentacao = tipoMovimentacao;
	}
	
}

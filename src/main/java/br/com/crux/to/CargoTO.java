package br.com.crux.to;

public class CargoTO {

	private Long id;
	private String codigo;
	private String nome;
	private String tipoCargo;
	private Long usuarioAlteracao;
	private CboTO cbo;
	private String descricaoPerfilProfissional;
	private GrausInstrucaoTO grausInstrucao;
	private String descricaoResumoAtividades;	
	private Long qtdHoras;		
	private Double valorRemuneracao;
	private InstituicaoTO instituicao;
	

	public CargoTO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTipoCargo() {
		return tipoCargo;
	}

	public void setTipoCargo(String tipoCargo) {
		this.tipoCargo = tipoCargo;
	}

	public Long getUsuarioAlteracao() {
		return usuarioAlteracao;
	}

	public void setUsuarioAlteracao(Long usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}

	public CboTO getCbo() {
		return cbo;
	}

	public void setCbo(CboTO cbo) {
		this.cbo = cbo;
	}

	public String getDescricaoPerfilProfissional() {
		return descricaoPerfilProfissional;
	}

	public void setDescricaoPerfilProfissional(String descricaoPerfilProfissional) {
		this.descricaoPerfilProfissional = descricaoPerfilProfissional;
	}

	public String getDescricaoResumoAtividades() {
		return descricaoResumoAtividades;
	}

	public void setDescricaoResumoAtividades(String descricaoResumoAtividades) {
		this.descricaoResumoAtividades = descricaoResumoAtividades;
	}

	public Long getQtdHoras() {
		return qtdHoras;
	}

	public void setQtdHoras(Long qtdHoras) {
		this.qtdHoras = qtdHoras;
	}

	public Double getValorRemuneracao() {
		return valorRemuneracao;
	}

	public void setValorRemuneracao(Double valorRemuneracao) {
		this.valorRemuneracao = valorRemuneracao;
	}

	public InstituicaoTO getInstituicao() {
		return instituicao;
	}

	public void setInstituicao(InstituicaoTO instituicao) {
		this.instituicao = instituicao;
	}

	public GrausInstrucaoTO getGrausInstrucao() {
		return grausInstrucao;
	}

	public void setGrausInstrucao(GrausInstrucaoTO grausInstrucaoTO) {
		this.grausInstrucao = grausInstrucaoTO;
	}

	
}

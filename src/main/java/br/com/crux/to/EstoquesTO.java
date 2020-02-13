package br.com.crux.to;

import java.time.LocalDateTime;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import br.com.crux.infra.adapter.LocalDateTimeAdapter;

public class EstoquesTO {

	private Long id;
	
	private MaterialTO material;
	private UnidadeTO unidade;
	private FuncionarioTO funcionario;
	private DepartamentoTO departamento;
	private ProgramaTO programa;
	private ProjetoTO projeto;

	@XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
	private LocalDateTime dataEstoque;
	@XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
	private LocalDateTime dtAtzEstoque;

	private Double quantidadeEstoque;
	private Double valorMedioMaterial;
	private Double valorUltimoMaterial;
	private Double quantidadeMinimoEstoque;
	private Double quantidadeMaximoEstoque;
	private Double valorEntradaMaterial;
	
	private String descricaoMovimentacaoEstoque;
	private String descricaoFormaValorEntrada;

	// Classificador do tipo de entrada do material no estoque (C = COMPRA; D = DOAÇÃO; P = PAGAMENTO DE PENA; O = OUTRO)
	private String tipoEntradaMaterial;

	// Classificador do tipo de movimentação do estoque (E = ENTRADA; S = SAÍDA; A = ACERTO; O = OUTRO)
	private String tipoMovimentacaoEstoque;
	
	private Long usuarioAlteracao;

	public EstoquesTO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public MaterialTO getMaterial() {
		return material;
	}

	public void setMaterial(MaterialTO material) {
		this.material = material;
	}

	public UnidadeTO getUnidade() {
		return unidade;
	}

	public void setUnidade(UnidadeTO unidade) {
		this.unidade = unidade;
	}

	public FuncionarioTO getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(FuncionarioTO funcionario) {
		this.funcionario = funcionario;
	}

	public String getTipoMovimentacaoEstoque() {
		return tipoMovimentacaoEstoque;
	}

	public void setTipoMovimentacaoEstoque(String tipoMovimentacaoEstoque) {
		this.tipoMovimentacaoEstoque = tipoMovimentacaoEstoque;
	}

	public Double getQuantidadeEstoque() {
		return quantidadeEstoque;
	}

	public void setQuantidadeEstoque(Double quantidadeEstoque) {
		this.quantidadeEstoque = quantidadeEstoque;
	}

	public LocalDateTime getDataEstoque() {
		return dataEstoque;
	}

	public void setDataEstoque(LocalDateTime dataEstoque) {
		this.dataEstoque = dataEstoque;
	}

	public LocalDateTime getDtAtzEstoque() {
		return dtAtzEstoque;
	}

	public void setDtAtzEstoque(LocalDateTime dtAtzEstoque) {
		this.dtAtzEstoque = dtAtzEstoque;
	}

	public Double getValorMedioMaterial() {
		return valorMedioMaterial;
	}

	public void setValorMedioMaterial(Double valorMedioMaterial) {
		this.valorMedioMaterial = valorMedioMaterial;
	}

	public Double getValorUltimoMaterial() {
		return valorUltimoMaterial;
	}

	public void setValorUltimoMaterial(Double valorUltimoMaterial) {
		this.valorUltimoMaterial = valorUltimoMaterial;
	}

	public Double getQuantidadeMinimoEstoque() {
		return quantidadeMinimoEstoque;
	}

	public void setQuantidadeMinimoEstoque(Double quantidadeMinimoEstoque) {
		this.quantidadeMinimoEstoque = quantidadeMinimoEstoque;
	}

	public String getDescricaoMovimentacaoEstoque() {
		return descricaoMovimentacaoEstoque;
	}

	public void setDescricaoMovimentacaoEstoque(String descricaoMovimentacaoEstoque) {
		this.descricaoMovimentacaoEstoque = descricaoMovimentacaoEstoque;
	}

	public DepartamentoTO getDepartamento() {
		return departamento;
	}

	public void setDepartamento(DepartamentoTO departamento) {
		this.departamento = departamento;
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

	public Double getQuantidadeMaximoEstoque() {
		return quantidadeMaximoEstoque;
	}

	public void setQuantidadeMaximoEstoque(Double quantidadeMaximoEstoque) {
		this.quantidadeMaximoEstoque = quantidadeMaximoEstoque;
	}

	public Double getValorEntradaMaterial() {
		return valorEntradaMaterial;
	}

	public void setValorEntradaMaterial(Double valorEntradaMaterial) {
		this.valorEntradaMaterial = valorEntradaMaterial;
	}

	public String getDescricaoFormaValorEntrada() {
		return descricaoFormaValorEntrada;
	}

	public void setDescricaoFormaValorEntrada(String descricaoFormaValorEntrada) {
		this.descricaoFormaValorEntrada = descricaoFormaValorEntrada;
	}

	public String getTipoEntradaMaterial() {
		return tipoEntradaMaterial;
	}

	public void setTipoEntradaMaterial(String tipoEntradaMaterial) {
		this.tipoEntradaMaterial = tipoEntradaMaterial;
	}

	public Long getUsuarioAlteracao() {
		return usuarioAlteracao;
	}

	public void setUsuarioAlteracao(Long usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}

}
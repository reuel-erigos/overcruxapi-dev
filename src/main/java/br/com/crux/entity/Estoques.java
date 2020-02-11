package br.com.crux.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.crux.infra.constantes.Constantes;


/**
 * The persistent class for the estoques database table.
 * 
 */
@Entity
@Table(name="estoques")
public class Estoques {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_id_estoque")
	@SequenceGenerator(name = "sq_id_estoque", sequenceName = "sq_id_estoque", schema = Constantes.SCHEMA_PUBLIC, initialValue = 1, allocationSize = 1)
	@Column(name="id_estoque")
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_material")
	private Material material;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_unidade")
	private Unidade unidade;	
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_funcionario")
	private Funcionario funcionario;
	
	//Classificador do tipo de movimentação do estoque (E = ENTRADA; S = SAÍDA; A = ACERTO; O = OUTRO)
	@Column(name="cs_tipo_mov_estoque")
	private String csTipoMovEstoque;

	@Column(name="qt_estoque")
	private Double quantidadeEstoque;

	@Column(name="dt_estoque")
	private LocalDateTime dataEstoque;
	
	@Column(name="dt_atz_estoque")
	private LocalDateTime dtAtzEstoque;
	
	@Column(name="vl_medio_material")
	private Double valorMedioMaterial;

	@Column(name="vl_ultimo_material")
	private Double valorUltimoMaterial;
	
	@Column(name="qt_minimo_estoque")
	private Double quantidadeMinimoEstoque;
	
	@Column(name="ds_mov_estoque")
	private String descricaoMovimentacaoEstoque;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_departamento")
	private Departamentos departamento;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_pedido")
	private Pedido pedito;
	
	@Column(name="qt_maximo_estoque")
	private Double quantidadeMaximoEstoque;

	@Column(name="vl_entrada_material")
	private Double valorEntradaMaterial;

	@Column(name="ds_vl_entrada_material")
	private String descricaoFormaValorEntrada;

	//Classificador do tipo de entrada do material no estoque (C = COMPRA; D = DOAÇÃO; P = PAGAMENTO DE PENA; O = OUTRO)
	@Column(name="cs_tipo_entrada_estoque")
	private String tipoEntradaMaterial;

	@Column(name = "id_usuario_apl")
	private Long usuarioAlteracao;

	public Estoques() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

	public Unidade getUnidade() {
		return unidade;
	}

	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public String getCsTipoMovEstoque() {
		return csTipoMovEstoque;
	}

	public void setCsTipoMovEstoque(String csTipoMovEstoque) {
		this.csTipoMovEstoque = csTipoMovEstoque;
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

	public Departamentos getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamentos departamento) {
		this.departamento = departamento;
	}

	public Pedido getPedito() {
		return pedito;
	}

	public void setPedito(Pedido pedito) {
		this.pedito = pedito;
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
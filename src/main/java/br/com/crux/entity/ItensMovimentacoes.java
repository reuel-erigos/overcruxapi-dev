package br.com.crux.entity;

import java.io.Serializable;

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
 * The persistent class for the itens_movimentacoes database table.
 * 
 */
@Entity
@Table(name = "itens_movimentacoes")
public class ItensMovimentacoes implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_id_item_movimentacao")
	@SequenceGenerator(name = "sq_id_item_movimentacao", sequenceName = "sq_id_item_movimentacao", schema = Constantes.SCHEMA_PUBLIC, initialValue = 1, allocationSize = 1)
	@Column(name = "id_item_movimentacao")
	private Long id;

	@Column(name = "ds_item_movimentacao")
	private String descricaoItemMovimentacao;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_categoria")
	private CategoriasContabeis categoria;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_categoria_adicional")
	private CategoriasContabeis categoriaAdicional;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_departamento")
	private Departamentos departamento;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_material")
	private Material material;

	@Column(name = "id_movimentacao")
	private Long idMovimentacao;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_unidade")
	private Unidade unidade;

	@Column(name = "id_usuario_apl")
	private Long usuarioAlteracao;

	@Column(name = "qtd_material")
	private Long quantidadeMaterial;

	@Column(name = "vl_total_item")
	private Double valorTotalItem;

	@Column(name = "vl_unitario_item")
	private Double valorUnitarioItem;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_pedido_material")
	private PedidosMateriais pedidosMateriais;


	public ItensMovimentacoes() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricaoItemMovimentacao() {
		return descricaoItemMovimentacao;
	}

	public void setDescricaoItemMovimentacao(String descricaoItemMovimentacao) {
		this.descricaoItemMovimentacao = descricaoItemMovimentacao;
	}

	public CategoriasContabeis getCategoria() {
		return categoria;
	}

	public void setCategoria(CategoriasContabeis categoria) {
		this.categoria = categoria;
	}

	public Departamentos getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamentos departamento) {
		this.departamento = departamento;
	}

	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

	public Long getIdMovimentacao() {
		return idMovimentacao;
	}

	public void setIdMovimentacao(Long idMovimentacao) {
		this.idMovimentacao = idMovimentacao;
	}

	public void setPedidosMateriais(PedidosMateriais pedidosMateriais) {
		this.pedidosMateriais = pedidosMateriais;
	}

	public Unidade getUnidade() {
		return unidade;
	}

	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
	}

	public Long getUsuarioAlteracao() {
		return usuarioAlteracao;
	}

	public void setUsuarioAlteracao(Long usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}

	public Long getQuantidadeMaterial() {
		return quantidadeMaterial;
	}

	public void setQuantidadeMaterial(Long quantidadeMaterial) {
		this.quantidadeMaterial = quantidadeMaterial;
	}

	public Double getValorTotalItem() {
		return valorTotalItem;
	}

	public void setValorTotalItem(Double valorTotalItem) {
		this.valorTotalItem = valorTotalItem;
	}

	public Double getValorUnitarioItem() {
		return valorUnitarioItem;
	}

	public void setValorUnitarioItem(Double valorUnitarioItem) {
		this.valorUnitarioItem = valorUnitarioItem;
	}

	public PedidosMateriais getPedidosMateriais() {
		return pedidosMateriais;
	}

	public CategoriasContabeis getCategoriaAdicional() {
		return categoriaAdicional;
	}

	public void setCategoriaAdicional(CategoriasContabeis categoriaAdicional) {
		this.categoriaAdicional = categoriaAdicional;
	}

}
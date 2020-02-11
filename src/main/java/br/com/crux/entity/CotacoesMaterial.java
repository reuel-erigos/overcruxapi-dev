package br.com.crux.entity;

import java.io.Serializable;
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

import br.com.crux.entity.Empresa;
import br.com.crux.entity.Material;
import br.com.crux.entity.Pedido;
import br.com.crux.infra.constantes.Constantes;


/**
 * The persistent class for the cotacoes_materiais database table.
 * 
 */
@Entity
@Table(name="cotacoes_materiais")
public class CotacoesMaterial implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_id_cotacao_material")
	@SequenceGenerator(name = "sq_id_cotacao_material", sequenceName = "sq_id_cotacao_material", schema = Constantes.SCHEMA_PUBLIC, initialValue = 1, allocationSize = 1)
	@Column(name="id_cotacao_material")
	private Long id;

	@Column(name="dt_cotacao")
	private LocalDateTime dataCotacao;

	@Column(name="dt_validade_cotacao")
	private LocalDateTime dataValidadeCotacao;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_empresa")
	private Empresa empresa;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_material")
	private Material material;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_pedido")
	private Pedido pedido;

	@Column(name = "id_usuario_apl")
	private Long usuarioAlteracao;

	@Column(name="qtd_material")
	private Double quantidadeMaterial;

	@Column(name="vl_total_cotacao")
	private Double valorTotalCotacao;

	@Column(name="vl_unitario_cotacao")
	private Double valorUnitarioCotacao;

	public CotacoesMaterial() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getDataCotacao() {
		return dataCotacao;
	}

	public void setDataCotacao(LocalDateTime dataCotacao) {
		this.dataCotacao = dataCotacao;
	}

	public LocalDateTime getDataValidadeCotacao() {
		return dataValidadeCotacao;
	}

	public void setDataValidadeCotacao(LocalDateTime dataValidadeCotacao) {
		this.dataValidadeCotacao = dataValidadeCotacao;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Long getUsuarioAlteracao() {
		return usuarioAlteracao;
	}

	public void setUsuarioAlteracao(Long usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}

	public Double getQuantidadeMaterial() {
		return quantidadeMaterial;
	}

	public void setQuantidadeMaterial(Double quantidadeMaterial) {
		this.quantidadeMaterial = quantidadeMaterial;
	}

	public Double getValorTotalCotacao() {
		return valorTotalCotacao;
	}

	public void setValorTotalCotacao(Double valorTotalCotacao) {
		this.valorTotalCotacao = valorTotalCotacao;
	}

	public Double getValorUnitarioCotacao() {
		return valorUnitarioCotacao;
	}

	public void setValorUnitarioCotacao(Double valorUnitarioCotacao) {
		this.valorUnitarioCotacao = valorUnitarioCotacao;
	}

	

}
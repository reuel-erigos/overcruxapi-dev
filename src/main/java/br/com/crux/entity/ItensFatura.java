package br.com.crux.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the itens_faturas database table.
 * 
 */
@Entity
@Table(name="itens_faturas")
@NamedQuery(name="ItensFatura.findAll", query="SELECT i FROM ItensFatura i")
public class ItensFatura implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_item_fatura")
	private Long idItemFatura;

	@Column(name="ds_item_fatura")
	private String dsItemFatura;

	@Column(name="qt_item_fatura")
	private BigDecimal qtItemFatura;

	@Column(name="vl_item_fatura")
	private BigDecimal vlItemFatura;

	//bi-directional many-to-one association to CentrosCusto
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_centro_custo")
	private CentrosCusto centrosCusto;

	//bi-directional many-to-one association to Fatura
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_fatura")
	private Fatura fatura;

	//bi-directional many-to-one association to Pedido
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_pedido")
	private Pedido pedido;

	//bi-directional many-to-one association to Produto
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_produto")
	private Material material;

	//bi-directional many-to-one association to Servico
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_servico")
	private Servico servico;

	//bi-directional many-to-one association to UsuariosSistema
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_usuario_apl")
	private UsuariosSistema usuariosSistema;

	public ItensFatura() {
	}

	public Long getIdItemFatura() {
		return this.idItemFatura;
	}

	public void setIdItemFatura(Long idItemFatura) {
		this.idItemFatura = idItemFatura;
	}

	public String getDsItemFatura() {
		return this.dsItemFatura;
	}

	public void setDsItemFatura(String dsItemFatura) {
		this.dsItemFatura = dsItemFatura;
	}

	public BigDecimal getQtItemFatura() {
		return this.qtItemFatura;
	}

	public void setQtItemFatura(BigDecimal qtItemFatura) {
		this.qtItemFatura = qtItemFatura;
	}

	public BigDecimal getVlItemFatura() {
		return this.vlItemFatura;
	}

	public void setVlItemFatura(BigDecimal vlItemFatura) {
		this.vlItemFatura = vlItemFatura;
	}

	public CentrosCusto getCentrosCusto() {
		return this.centrosCusto;
	}

	public void setCentrosCusto(CentrosCusto centrosCusto) {
		this.centrosCusto = centrosCusto;
	}

	public Fatura getFatura() {
		return this.fatura;
	}

	public void setFatura(Fatura fatura) {
		this.fatura = fatura;
	}

	public Pedido getPedido() {
		return this.pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Material getMaterial() {
		return this.material;
	}

	public void setProduto(Material material) {
		this.material = material;
	}

	public Servico getServico() {
		return this.servico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
	}

	public UsuariosSistema getUsuariosSistema() {
		return this.usuariosSistema;
	}

	public void setUsuariosSistema(UsuariosSistema usuariosSistema) {
		this.usuariosSistema = usuariosSistema;
	}

}
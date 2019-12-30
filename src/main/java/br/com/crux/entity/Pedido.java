package br.com.crux.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the pedidos database table.
 * 
 */
@Entity
@Table(name="pedidos")
@NamedQuery(name="Pedido.findAll", query="SELECT p FROM Pedido p")
public class Pedido implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_pedido")
	private Long idPedido;

	@Column(name="ds_pedido")
	private String dsPedido;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="dt_entrega")
	private Date dtEntrega;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="dt_envio")
	private Date dtEnvio;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="dt_pedido")
	private Date dtPedido;

	@Column(name="qtd_pedido")
	private BigDecimal qtdPedido;

	@Column(name="vl_pedido")
	private BigDecimal vlPedido;

	//bi-directional many-to-one association to ItensFatura
	@OneToMany(mappedBy="pedido")
	private List<ItensFatura> itensFaturas;

	//bi-directional many-to-one association to CentrosCusto
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_centro_custo")
	private CentrosCusto centrosCusto;

	//bi-directional many-to-one association to Funcionario
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_funcionario_rec_ped")
	private Funcionario funcionario1;

	//bi-directional many-to-one association to Funcionario
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_funcionario_pedido")
	private Funcionario funcionario2;

	//bi-directional many-to-one association to Produto
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_produto")
	private Material material;

	//bi-directional many-to-one association to Servico
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_servico")
	private Servico servico;

	//bi-directional many-to-one association to Unidade
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_unidade")
	private Unidade unidade;

	//bi-directional many-to-one association to UsuariosSistema
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_usuario_apl")
	private UsuariosSistema usuariosSistema;

	public Pedido() {
	}

	public Long getIdPedido() {
		return this.idPedido;
	}

	public void setIdPedido(Long idPedido) {
		this.idPedido = idPedido;
	}

	public String getDsPedido() {
		return this.dsPedido;
	}

	public void setDsPedido(String dsPedido) {
		this.dsPedido = dsPedido;
	}

	public Date getDtEntrega() {
		return this.dtEntrega;
	}

	public void setDtEntrega(Date dtEntrega) {
		this.dtEntrega = dtEntrega;
	}

	public Date getDtEnvio() {
		return this.dtEnvio;
	}

	public void setDtEnvio(Date dtEnvio) {
		this.dtEnvio = dtEnvio;
	}

	public Date getDtPedido() {
		return this.dtPedido;
	}

	public void setDtPedido(Date dtPedido) {
		this.dtPedido = dtPedido;
	}

	public BigDecimal getQtdPedido() {
		return this.qtdPedido;
	}

	public void setQtdPedido(BigDecimal qtdPedido) {
		this.qtdPedido = qtdPedido;
	}

	public BigDecimal getVlPedido() {
		return this.vlPedido;
	}

	public void setVlPedido(BigDecimal vlPedido) {
		this.vlPedido = vlPedido;
	}

	public List<ItensFatura> getItensFaturas() {
		return this.itensFaturas;
	}

	public void setItensFaturas(List<ItensFatura> itensFaturas) {
		this.itensFaturas = itensFaturas;
	}

	public ItensFatura addItensFatura(ItensFatura itensFatura) {
		getItensFaturas().add(itensFatura);
		itensFatura.setPedido(this);

		return itensFatura;
	}

	public ItensFatura removeItensFatura(ItensFatura itensFatura) {
		getItensFaturas().remove(itensFatura);
		itensFatura.setPedido(null);

		return itensFatura;
	}

	public CentrosCusto getCentrosCusto() {
		return this.centrosCusto;
	}

	public void setCentrosCusto(CentrosCusto centrosCusto) {
		this.centrosCusto = centrosCusto;
	}

	public Funcionario getFuncionario1() {
		return this.funcionario1;
	}

	public void setFuncionario1(Funcionario funcionario1) {
		this.funcionario1 = funcionario1;
	}

	public Funcionario getFuncionario2() {
		return this.funcionario2;
	}

	public void setFuncionario2(Funcionario funcionario2) {
		this.funcionario2 = funcionario2;
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

	public Unidade getUnidade() {
		return this.unidade;
	}

	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
	}

	public UsuariosSistema getUsuariosSistema() {
		return this.usuariosSistema;
	}

	public void setUsuariosSistema(UsuariosSistema usuariosSistema) {
		this.usuariosSistema = usuariosSistema;
	}

}
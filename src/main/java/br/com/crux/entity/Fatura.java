package br.com.crux.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the faturas database table.
 * 
 */
@Entity
@Table(name="faturas")
@NamedQuery(name="Fatura.findAll", query="SELECT f FROM Fatura f")
public class Fatura implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_fatura")
	private Long idFatura;

	@Column(name="cs_tipo_fatura")
	private String csTipoFatura;

	@Column(name="ds_fatura")
	private String dsFatura;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="dt_doc_fatura")
	private Date dtDocFatura;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="dt_fatura")
	private Date dtFatura;

	@Column(name="nr_doc_fatura")
	private String nrDocFatura;

	@Column(name="qt_parcelas")
	private BigDecimal qtParcelas;

	@Column(name="vl_fatura")
	private BigDecimal vlFatura;

	//bi-directional many-to-one association to Empresa
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="empresas_id_empresa")
	private Empresa empresa;

	//bi-directional many-to-one association to Unidade
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="unidades_id_unidade")
	private Unidade unidade;

	//bi-directional many-to-one association to UsuariosSistema
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_usuario_apl")
	private UsuariosSistema usuariosSistema;

	//bi-directional many-to-one association to ItensFatura
	@OneToMany(mappedBy="fatura")
	private List<ItensFatura> itensFaturas;

	//bi-directional many-to-one association to PagamentosFatura
	@OneToMany(mappedBy="fatura")
	private List<PagamentosFatura> pagamentosFaturas;

	public Fatura() {
	}

	public Long getIdFatura() {
		return this.idFatura;
	}

	public void setIdFatura(Long idFatura) {
		this.idFatura = idFatura;
	}

	public String getCsTipoFatura() {
		return this.csTipoFatura;
	}

	public void setCsTipoFatura(String csTipoFatura) {
		this.csTipoFatura = csTipoFatura;
	}

	public String getDsFatura() {
		return this.dsFatura;
	}

	public void setDsFatura(String dsFatura) {
		this.dsFatura = dsFatura;
	}

	public Date getDtDocFatura() {
		return this.dtDocFatura;
	}

	public void setDtDocFatura(Date dtDocFatura) {
		this.dtDocFatura = dtDocFatura;
	}

	public Date getDtFatura() {
		return this.dtFatura;
	}

	public void setDtFatura(Date dtFatura) {
		this.dtFatura = dtFatura;
	}

	public String getNrDocFatura() {
		return this.nrDocFatura;
	}

	public void setNrDocFatura(String nrDocFatura) {
		this.nrDocFatura = nrDocFatura;
	}

	public BigDecimal getQtParcelas() {
		return this.qtParcelas;
	}

	public void setQtParcelas(BigDecimal qtParcelas) {
		this.qtParcelas = qtParcelas;
	}

	public BigDecimal getVlFatura() {
		return this.vlFatura;
	}

	public void setVlFatura(BigDecimal vlFatura) {
		this.vlFatura = vlFatura;
	}

	public Empresa getEmpresa() {
		return this.empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
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

	public List<ItensFatura> getItensFaturas() {
		return this.itensFaturas;
	}

	public void setItensFaturas(List<ItensFatura> itensFaturas) {
		this.itensFaturas = itensFaturas;
	}

	public ItensFatura addItensFatura(ItensFatura itensFatura) {
		getItensFaturas().add(itensFatura);
		itensFatura.setFatura(this);

		return itensFatura;
	}

	public ItensFatura removeItensFatura(ItensFatura itensFatura) {
		getItensFaturas().remove(itensFatura);
		itensFatura.setFatura(null);

		return itensFatura;
	}

	public List<PagamentosFatura> getPagamentosFaturas() {
		return this.pagamentosFaturas;
	}

	public void setPagamentosFaturas(List<PagamentosFatura> pagamentosFaturas) {
		this.pagamentosFaturas = pagamentosFaturas;
	}

	public PagamentosFatura addPagamentosFatura(PagamentosFatura pagamentosFatura) {
		getPagamentosFaturas().add(pagamentosFatura);
		pagamentosFatura.setFatura(this);

		return pagamentosFatura;
	}

	public PagamentosFatura removePagamentosFatura(PagamentosFatura pagamentosFatura) {
		getPagamentosFaturas().remove(pagamentosFatura);
		pagamentosFatura.setFatura(null);

		return pagamentosFatura;
	}

}
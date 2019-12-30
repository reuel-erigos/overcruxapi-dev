package br.com.crux.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the pagamentos_faturas database table.
 * 
 */
@Entity
@Table(name="pagamentos_faturas")
@NamedQuery(name="PagamentosFatura.findAll", query="SELECT p FROM PagamentosFatura p")
public class PagamentosFatura implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_pagamento_fatura")
	private Long idPagamentoFatura;

	@Column(name="cs_tipo_pagamento")
	private String csTipoPagamento;

	@Column(name="ds_item_fatura")
	private String dsItemFatura;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="dt_cheque_fatura")
	private Date dtChequeFatura;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="dt_pagamento_fatura")
	private Date dtPagamentoFatura;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="dt_vencimento_fatura")
	private Date dtVencimentoFatura;

	@Column(name="nr_doc_pagamento_fatura")
	private String nrDocPagamentoFatura;

	@Column(name="nr_parcela_fatura")
	private BigDecimal nrParcelaFatura;

	@Column(name="vl_fatura")
	private BigDecimal vlFatura;

	@Column(name="vl_pagamento_fatura")
	private BigDecimal vlPagamentoFatura;

	//bi-directional many-to-one association to MovimentacoesConta
	@OneToMany(mappedBy="pagamentosFatura")
	private List<MovimentacoesConta> movimentacoesContas;

	//bi-directional many-to-one association to ContasBancaria
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_conta_bancaria")
	private ContasBancaria contasBancaria;

	//bi-directional many-to-one association to Fatura
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_fatura")
	private Fatura fatura;

	//bi-directional many-to-one association to UsuariosSistema
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_usuario_apl")
	private UsuariosSistema usuariosSistema;

	public PagamentosFatura() {
	}

	public Long getIdPagamentoFatura() {
		return this.idPagamentoFatura;
	}

	public void setIdPagamentoFatura(Long idPagamentoFatura) {
		this.idPagamentoFatura = idPagamentoFatura;
	}

	public String getCsTipoPagamento() {
		return this.csTipoPagamento;
	}

	public void setCsTipoPagamento(String csTipoPagamento) {
		this.csTipoPagamento = csTipoPagamento;
	}

	public String getDsItemFatura() {
		return this.dsItemFatura;
	}

	public void setDsItemFatura(String dsItemFatura) {
		this.dsItemFatura = dsItemFatura;
	}

	public Date getDtChequeFatura() {
		return this.dtChequeFatura;
	}

	public void setDtChequeFatura(Date dtChequeFatura) {
		this.dtChequeFatura = dtChequeFatura;
	}

	public Date getDtPagamentoFatura() {
		return this.dtPagamentoFatura;
	}

	public void setDtPagamentoFatura(Date dtPagamentoFatura) {
		this.dtPagamentoFatura = dtPagamentoFatura;
	}

	public Date getDtVencimentoFatura() {
		return this.dtVencimentoFatura;
	}

	public void setDtVencimentoFatura(Date dtVencimentoFatura) {
		this.dtVencimentoFatura = dtVencimentoFatura;
	}

	public String getNrDocPagamentoFatura() {
		return this.nrDocPagamentoFatura;
	}

	public void setNrDocPagamentoFatura(String nrDocPagamentoFatura) {
		this.nrDocPagamentoFatura = nrDocPagamentoFatura;
	}

	public BigDecimal getNrParcelaFatura() {
		return this.nrParcelaFatura;
	}

	public void setNrParcelaFatura(BigDecimal nrParcelaFatura) {
		this.nrParcelaFatura = nrParcelaFatura;
	}

	public BigDecimal getVlFatura() {
		return this.vlFatura;
	}

	public void setVlFatura(BigDecimal vlFatura) {
		this.vlFatura = vlFatura;
	}

	public BigDecimal getVlPagamentoFatura() {
		return this.vlPagamentoFatura;
	}

	public void setVlPagamentoFatura(BigDecimal vlPagamentoFatura) {
		this.vlPagamentoFatura = vlPagamentoFatura;
	}

	public List<MovimentacoesConta> getMovimentacoesContas() {
		return this.movimentacoesContas;
	}

	public void setMovimentacoesContas(List<MovimentacoesConta> movimentacoesContas) {
		this.movimentacoesContas = movimentacoesContas;
	}

	public MovimentacoesConta addMovimentacoesConta(MovimentacoesConta movimentacoesConta) {
		getMovimentacoesContas().add(movimentacoesConta);
		movimentacoesConta.setPagamentosFatura(this);

		return movimentacoesConta;
	}

	public MovimentacoesConta removeMovimentacoesConta(MovimentacoesConta movimentacoesConta) {
		getMovimentacoesContas().remove(movimentacoesConta);
		movimentacoesConta.setPagamentosFatura(null);

		return movimentacoesConta;
	}

	public ContasBancaria getContasBancaria() {
		return this.contasBancaria;
	}

	public void setContasBancaria(ContasBancaria contasBancaria) {
		this.contasBancaria = contasBancaria;
	}

	public Fatura getFatura() {
		return this.fatura;
	}

	public void setFatura(Fatura fatura) {
		this.fatura = fatura;
	}

	public UsuariosSistema getUsuariosSistema() {
		return this.usuariosSistema;
	}

	public void setUsuariosSistema(UsuariosSistema usuariosSistema) {
		this.usuariosSistema = usuariosSistema;
	}

}
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

import br.com.crux.entity.Departamentos;
import br.com.crux.entity.Empresa;
import br.com.crux.entity.Programa;
import br.com.crux.entity.Projeto;
import br.com.crux.entity.Unidade;
import br.com.crux.infra.constantes.Constantes;

/**
 * The persistent class for the movimentacoes database table.
 * 
 */
@Entity
@Table(name = "movimentacoes")
public class Movimentacoes implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_id_movimentacao")
	@SequenceGenerator(name = "sq_id_movimentacao", sequenceName = "sq_id_movimentacao", schema = Constantes.SCHEMA_PUBLIC, initialValue = 1, allocationSize = 1)
	@Column(name = "id_movimentacao")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_empresa")
	private Empresa empresa;

	// Classificador do Tipo de Movimentação de $ (E = ENTRADA ou S = SAÍDA)
	@Column(name = "st_tipo_movimentacao")
	private String stTipoMovimentacao;

	@Column(name = "dt_movimentacao")
	private LocalDateTime dataMovimentacao;

	@Column(name = "ds_movimentacao")
	private String descricaoMovimentacao;

	@Column(name = "nr_documento")
	private String nrDocumento;

	@Column(name = "dt_documento")
	private LocalDateTime dataDocumento;

	@Column(name = "vl_movimentacao")
	private Double valorMovimentacao;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_programa")
	private Programa programa;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_projeto")
	private Projeto projeto;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_unidade")
	private Unidade unidade;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_departamento")
	private Departamentos departamento;

	@Column(name = "qtd_parcelas")
	private Long qtdParcelas;

	@Column(name = "id_usuario_apl")
	private Long usuarioAlteracao;

	
	@Column(name = "vl_iss")
	private Double valorISS;
	
	@Column(name = "vl_icms")
	private Double valorICMS;
	
	@Column(name = "vl_ipi")
	private Double valorIPI;
	
	@Column(name = "vl_pis_cofins_csll")
	private Double valorPisConfinsCsll;
	
	@Column(name = "vl_inss")
	private Double valorInss;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_saldo_conta_bancaria")
	private SaldosContasBancaria saldoContaBancaria;
	
	
	public Movimentacoes() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public String getStTipoMovimentacao() {
		return stTipoMovimentacao;
	}

	public void setStTipoMovimentacao(String stTipoMovimentacao) {
		this.stTipoMovimentacao = stTipoMovimentacao;
	}

	public LocalDateTime getDataMovimentacao() {
		return dataMovimentacao;
	}

	public void setDataMovimentacao(LocalDateTime dataMovimentacao) {
		this.dataMovimentacao = dataMovimentacao;
	}

	public String getDescricaoMovimentacao() {
		return descricaoMovimentacao;
	}

	public void setDescricaoMovimentacao(String descricaoMovimentacao) {
		this.descricaoMovimentacao = descricaoMovimentacao;
	}

	public String getNrDocumento() {
		return nrDocumento;
	}

	public void setNrDocumento(String nrDocumento) {
		this.nrDocumento = nrDocumento;
	}

	public LocalDateTime getDataDocumento() {
		return dataDocumento;
	}

	public void setDataDocumento(LocalDateTime dataDocumento) {
		this.dataDocumento = dataDocumento;
	}

	public Double getValorMovimentacao() {
		return valorMovimentacao;
	}

	public void setValorMovimentacao(Double valorMovimentacao) {
		this.valorMovimentacao = valorMovimentacao;
	}

	public Programa getPrograma() {
		return programa;
	}

	public void setPrograma(Programa programa) {
		this.programa = programa;
	}

	public Projeto getProjeto() {
		return projeto;
	}

	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}

	public Unidade getUnidade() {
		return unidade;
	}

	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
	}

	public Departamentos getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamentos departamento) {
		this.departamento = departamento;
	}

	public Long getQtdParcelas() {
		return qtdParcelas;
	}

	public void setQtdParcelas(Long qtdParcelas) {
		this.qtdParcelas = qtdParcelas;
	}

	public Long getUsuarioAlteracao() {
		return usuarioAlteracao;
	}

	public void setUsuarioAlteracao(Long usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}

	public Double getValorISS() {
		return valorISS;
	}

	public void setValorISS(Double valorISS) {
		this.valorISS = valorISS;
	}

	public Double getValorICMS() {
		return valorICMS;
	}

	public void setValorICMS(Double valorICMS) {
		this.valorICMS = valorICMS;
	}

	public Double getValorIPI() {
		return valorIPI;
	}

	public void setValorIPI(Double valorIPI) {
		this.valorIPI = valorIPI;
	}

	public Double getValorPisConfinsCsll() {
		return valorPisConfinsCsll;
	}

	public void setValorPisConfinsCsll(Double valorPisConfinsCsll) {
		this.valorPisConfinsCsll = valorPisConfinsCsll;
	}

	public Double getValorInss() {
		return valorInss;
	}

	public void setValorInss(Double valorInss) {
		this.valorInss = valorInss;
	}

	public SaldosContasBancaria getSaldoContaBancaria() {
		return saldoContaBancaria;
	}

	public void setSaldoContaBancaria(SaldosContasBancaria saldoContaBancaria) {
		this.saldoContaBancaria = saldoContaBancaria;
	}

	
}
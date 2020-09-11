package br.com.crux.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Convert;
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
import br.com.crux.infra.dao.SimNaoConverter;

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
	@JoinColumn(name = "id_unidade")
	private Unidade unidade;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_departamento")
	private Departamentos departamento;

	@Column(name = "qtd_parcelas")
	private Long qtdParcelas;

	@Column(name = "id_usuario_apl")
	private Long usuarioAlteracao;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_conta_bancaria")
	private ContasBancaria contaBancaria;
	
	@Convert(converter = SimNaoConverter.class)
	@Column(name = "st_registro_saldo")
	private Boolean statusRegistroSaldo;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_conta_bancaria_destino")
	private ContasBancaria contaBancariaDestino;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_doador")
	private Doadores doador;
	
	
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

	public ContasBancaria getContaBancaria() {
		return contaBancaria;
	}

	public void setContaBancaria(ContasBancaria contaBancaria) {
		this.contaBancaria = contaBancaria;
	}

	public Boolean getStatusRegistroSaldo() {
		return statusRegistroSaldo;
	}

	public void setStatusRegistroSaldo(Boolean statusRegistroSaldo) {
		this.statusRegistroSaldo = statusRegistroSaldo;
	}

	public ContasBancaria getContaBancariaDestino() {
		return contaBancariaDestino;
	}

	public void setContaBancariaDestino(ContasBancaria contaBancariaDestino) {
		this.contaBancariaDestino = contaBancariaDestino;
	}

	public Doadores getDoador() {
		return doador;
	}

	public void setDoador(Doadores doador) {
		this.doador = doador;
	}

	
}
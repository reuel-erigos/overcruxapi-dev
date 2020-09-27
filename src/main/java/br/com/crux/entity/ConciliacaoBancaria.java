package br.com.crux.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.crux.infra.constantes.Constantes;
import br.com.crux.infra.dao.SimNaoConverter;

@Entity
@Table(name="conciliacoes_bancarias")
public class ConciliacaoBancaria implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_id_conciliacao_bancaria")
	@SequenceGenerator(name = "sq_id_conciliacao_bancaria", sequenceName = "sq_id_conciliacao_bancaria", schema = Constantes.SCHEMA_PUBLIC, initialValue = 1, allocationSize = 1)
	@Column(name="id_conciliacao_bancaria", unique=true, nullable=false, precision=10)
	private Long id;

	@Column(name="uk_conciliacao_bancaria")
	private String uk;	
	
	@Column(name="id_movimentacao")
	private Long idMovimentacao;
	
	@Column(name="id_instituicao")
	private Long idInstituicao;	
	
	@Column(name="id_conta_bancaria")
	private Long idContaBancaria;	

	@Column(name="st_tipo")
	private String tipo;
	
	@Column(name="st_status")
	private String status;

	@Column(name="nr_documento")
	private String numeroDocumento;

	@Column(name="dt_conciliacao")
	private LocalDateTime dataConciliacao;
	
	@Column(name="ds_banco")
	private String banco;

	@Column(name="ds_categoria")
	private String categoria;

	@Column(name="ds_fornecedor")
	private String descFornecedor;
	
	@Column(name="nm_fornecedor")
	private String nomeFornecedor;

	@Convert(converter = SimNaoConverter.class)
	@Column(name = "st_sem_doc")
	private Boolean statusSemDocumento;	
	
	@Column(name="ds_complemento")
	private String complemento;	
	
	@Column(name="ds_centro_custo")
	private String centroCusto;	
	
	@Column(name="ds_grupo_contas")
	private String grupoConta;	

	@Column(name="vl_conciliacao")
	private Double valor;	
	
	@Column(name="dt_geracao")
	private LocalDateTime dataGeracao;

	@Column(name="dt_exportacao")
	private LocalDateTime dataExportacao;
	
	@Column(name="id_usuario_apl")
	private Long usuarioAlteracao;

	public ConciliacaoBancaria() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUk() {
		return uk;
	}

	public void setUk(String uk) {
		this.uk = uk;
	}

	public Long getIdMovimentacao() {
		return idMovimentacao;
	}

	public void setIdMovimentacao(Long idMovimentacao) {
		this.idMovimentacao = idMovimentacao;
	}

	public Long getIdInstituicao() {
		return idInstituicao;
	}

	public void setIdInstituicao(Long idInstituicao) {
		this.idInstituicao = idInstituicao;
	}

	public Long getIdContaBancaria() {
		return idContaBancaria;
	}

	public void setIdContaBancaria(Long idContaBancaria) {
		this.idContaBancaria = idContaBancaria;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public LocalDateTime getDataConciliacao() {
		return dataConciliacao;
	}

	public void setDataConciliacao(LocalDateTime dataConciliacao) {
		this.dataConciliacao = dataConciliacao;
	}

	public String getBanco() {
		return banco;
	}

	public void setBanco(String banco) {
		this.banco = banco;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getDescFornecedor() {
		return descFornecedor;
	}

	public void setDescFornecedor(String descFornecedor) {
		this.descFornecedor = descFornecedor;
	}

	public String getNomeFornecedor() {
		return nomeFornecedor;
	}

	public void setNomeFornecedor(String nomeFornecedor) {
		this.nomeFornecedor = nomeFornecedor;
	}

	public Boolean getStatusSemDocumento() {
		return statusSemDocumento;
	}

	public void setStatusSemDocumento(Boolean statusSemDocumento) {
		this.statusSemDocumento = statusSemDocumento;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getCentroCusto() {
		return centroCusto;
	}

	public void setCentroCusto(String centroCusto) {
		this.centroCusto = centroCusto;
	}

	public String getGrupoConta() {
		return grupoConta;
	}

	public void setGrupoConta(String grupoConta) {
		this.grupoConta = grupoConta;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public LocalDateTime getDataGeracao() {
		return dataGeracao;
	}

	public void setDataGeracao(LocalDateTime dataGeracao) {
		this.dataGeracao = dataGeracao;
	}

	public LocalDateTime getDataExportacao() {
		return dataExportacao;
	}

	public void setDataExportacao(LocalDateTime dataExportacao) {
		this.dataExportacao = dataExportacao;
	}

	public Long getUsuarioAlteracao() {
		return usuarioAlteracao;
	}

	public void setUsuarioAlteracao(Long usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}


	
}
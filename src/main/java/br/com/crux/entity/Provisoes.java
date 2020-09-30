package br.com.crux.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.crux.infra.constantes.Constantes;

@Entity
@Table(name="provisoes")
public class Provisoes implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_id_provisao")
	@SequenceGenerator(name = "sq_id_provisao", sequenceName = "sq_id_provisao", schema = Constantes.SCHEMA_PUBLIC, initialValue = 1, allocationSize = 1)
	@Column(name="id_provisao", unique=true, nullable=false, precision=10)
	private Long id;

	@Column(name="uk_provisao")
	private String uk;	
	
	@Column(name="id_movimentacao")
	private Long idMovimentacao;
	
	@Column(name="id_instituicao")
	private Long idInstituicao;	
	
	@Column(name="st_status")
	private String status;

	@Column(name="nr_documento")
	private String numeroDocumento;

	@Column(name="dt_provisao")
	private LocalDateTime dataProvisao;

	@Column(name="vl_provisao")
	private Double valor;	
	
	@Column(name="ds_complemento")
	private String complemento;	

	@Column(name="ds_categoria")
	private String categoria;

	@Column(name="ds_centro_custo")
	private String centroCusto;	
	
	@Column(name="ds_grupo_contas")
	private String grupoConta;	
	
	@Column(name="ds_fornecedor")
	private String descFornecedor;

	@Column(name="nm_fornecedor")
	private String nomeFornecedor;

	@Column(name="dt_geracao")
	private LocalDateTime dataGeracao;
	
	@Column(name="dt_exportacao")
	private LocalDateTime dataExportacao;
	
	@Column(name="id_usuario_apl")
	private Long usuarioAlteracao;

	public Provisoes() {
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

	public LocalDateTime getDataProvisao() {
		return dataProvisao;
	}

	public void setDataProvisao(LocalDateTime dataProvisao) {
		this.dataProvisao = dataProvisao;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
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
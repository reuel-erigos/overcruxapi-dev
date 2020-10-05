package br.com.crux.entity.view;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

@Entity
@Immutable
@Table(name = "vw_programas_projetos_movimentacoes")
public class RateiosProgramaProjeto {

	@Id
	@Column(name = "id_rateio_movimentacao")
	private Long idRateioMovimentacao;
	
	@Column(name = "id_movimentacao")
	private Long idMovimentacao;
	
	@Column(name = "nm_programa_projeto")
	private String nomeProgramaProjeto;
	
	@Column(name = "fornecedor")
	private String fornecedor;
	
	@Column(name = "nr_documento")
	private String numeroDocumento;
	
	@Column(name = "cnpj_cpf")
	private String cnpf_cpf;
	
	@Column(name = "dt_documento")
	private LocalDateTime dataDocumento;
	
	@Column(name = "vl_movimentacao")
	private Double valor;
	
	@Column(name = "nr_transacao")
	private String numeroTransacao;
	
	@Column(name = "dt_ultimo_pgto")
	private LocalDateTime dataUltimoPagamento;
	
	@Column(name = "dt_vencimento")
	private LocalDateTime dataVencimento;
	
	@Column(name = "categorias")
	private String categorias;
	
	public RateiosProgramaProjeto() {
	}

	public Long getIdMovimentacao() {
		return idMovimentacao;
	}

	public void setIdMovimentacao(Long idMovimentacao) {
		this.idMovimentacao = idMovimentacao;
	}

	public Long getIdRateioMovimentacao() {
		return idRateioMovimentacao;
	}

	public void setIdRateioMovimentacao(Long idRateioMovimentacao) {
		this.idRateioMovimentacao = idRateioMovimentacao;
	}

	public String getNomeProgramaProjeto() {
		return nomeProgramaProjeto;
	}

	public void setNomeProgramaProjeto(String nomeProgramaProjeto) {
		this.nomeProgramaProjeto = nomeProgramaProjeto;
	}

	public String getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(String fornecedor) {
		this.fornecedor = fornecedor;
	}

	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public String getCnpf_cpf() {
		return cnpf_cpf;
	}

	public void setCnpf_cpf(String cnpf_cpf) {
		this.cnpf_cpf = cnpf_cpf;
	}

	public LocalDateTime getDataDocumento() {
		return dataDocumento;
	}

	public void setDataDocumento(LocalDateTime dataDocumento) {
		this.dataDocumento = dataDocumento;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public String getNumeroTransacao() {
		return numeroTransacao;
	}

	public void setNumeroTransacao(String numeroTransacao) {
		this.numeroTransacao = numeroTransacao;
	}

	public LocalDateTime getDataUltimoPagamento() {
		return dataUltimoPagamento;
	}

	public void setDataUltimoPagamento(LocalDateTime dataUltimoPagamento) {
		this.dataUltimoPagamento = dataUltimoPagamento;
	}

	public LocalDateTime getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(LocalDateTime dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public String getCategorias() {
		return categorias;
	}

	public void setCategorias(String categorias) {
		this.categorias = categorias;
	}
	
	
}

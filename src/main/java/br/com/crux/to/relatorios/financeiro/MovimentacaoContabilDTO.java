package br.com.crux.to.relatorios.financeiro;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

import br.com.crux.infra.util.DataUtil;

public class MovimentacaoContabilDTO {
	
	private String nomeProgramaProjeto  ;
	private String numeroDocumento      ;
	private String dataDocumento        ;
	private String descricaoCategoria   ;
	private String dataMovimentacao     ;
	private Double valorCategoria       ;
	private String contaDestino         ;
	private String contaOrigem          ;
	private Long   idMovimentacao       ;
	private Long   idCategoriaOrigem    ;
	private Long   idCategoriaDestino   ;
	private Long   idPrograma           ;
	private Long   idProjeto            ;
	
	public MovimentacaoContabilDTO() {
	}
	
	public MovimentacaoContabilDTO(Object[] colunas) {
		this.nomeProgramaProjeto       = (colunas[0] != null) ? (String) colunas[0] : "";
		this.numeroDocumento           = (colunas[1] != null) ? (String) colunas[1] : "";
		this.dataDocumento             = (colunas[2] != null) ? DataUtil.formataData(new Date(((Timestamp) colunas[2]).getTime()), "dd/MM/yyyy") : "";
		this.descricaoCategoria        = (colunas[3] != null) ? (String) colunas[3] : ""; 
		this.dataMovimentacao          = (colunas[4] != null) ? DataUtil.formataData(new Date(((Timestamp) colunas[4]).getTime()), "dd/MM/yyyy") : ""; 
		this.valorCategoria            = (colunas[5] != null)? ((BigDecimal)colunas[5]).doubleValue() : null; 
		this.contaDestino              = (colunas[6] != null) ? (String) colunas[6] : ""; 
		this.contaOrigem               = (colunas[7] != null) ? (String) colunas[7] : ""; 
		this.idMovimentacao            = (colunas[8] != null)? ((BigDecimal)colunas[8]).longValue() : null;  
		this.idCategoriaOrigem         = (colunas[9] != null)? ((BigDecimal)colunas[9]).longValue() : null;  
		this.idCategoriaDestino        = (colunas[10] != null)? ((BigDecimal)colunas[10]).longValue() : null;  
		this.idPrograma                = (colunas[11] != null)? ((BigDecimal)colunas[11]).longValue() : null;  
		this.idProjeto                 = (colunas[12] != null)? ((BigDecimal)colunas[12]).longValue() : null;  
	}

	public String getNomeProgramaProjeto() {
		return nomeProgramaProjeto;
	}

	public void setNomeProgramaProjeto(String nomeProgramaProjeto) {
		this.nomeProgramaProjeto = nomeProgramaProjeto;
	}

	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public String getDataDocumento() {
		return dataDocumento;
	}

	public void setDataDocumento(String dataDocumento) {
		this.dataDocumento = dataDocumento;
	}

	public String getDescricaoCategoria() {
		return descricaoCategoria;
	}

	public void setDescricaoCategoria(String descricaoCategoria) {
		this.descricaoCategoria = descricaoCategoria;
	}

	public String getDataMovimentacao() {
		return dataMovimentacao;
	}

	public void setDataMovimentacao(String dataMovimentacao) {
		this.dataMovimentacao = dataMovimentacao;
	}

	public Double getValorCategoria() {
		return valorCategoria;
	}

	public void setValorCategoria(Double valorCategoria) {
		this.valorCategoria = valorCategoria;
	}

	public String getContaDestino() {
		return contaDestino;
	}

	public void setContaDestino(String contaDestino) {
		this.contaDestino = contaDestino;
	}

	public String getContaOrigem() {
		return contaOrigem;
	}

	public void setContaOrigem(String contaOrigem) {
		this.contaOrigem = contaOrigem;
	}

	public Long getIdMovimentacao() {
		return idMovimentacao;
	}

	public void setIdMovimentacao(Long idMovimentacao) {
		this.idMovimentacao = idMovimentacao;
	}

	public Long getIdCategoriaOrigem() {
		return idCategoriaOrigem;
	}

	public void setIdCategoriaOrigem(Long idCategoriaOrigem) {
		this.idCategoriaOrigem = idCategoriaOrigem;
	}

	public Long getIdCategoriaDestino() {
		return idCategoriaDestino;
	}

	public void setIdCategoriaDestino(Long idCategoriaDestino) {
		this.idCategoriaDestino = idCategoriaDestino;
	}

	public Long getIdPrograma() {
		return idPrograma;
	}

	public void setIdPrograma(Long idPrograma) {
		this.idPrograma = idPrograma;
	}

	public Long getIdProjeto() {
		return idProjeto;
	}

	public void setIdProjeto(Long idProjeto) {
		this.idProjeto = idProjeto;
	}

	
}

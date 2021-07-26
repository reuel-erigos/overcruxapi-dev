package br.com.crux.dao.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class MovimentacoesContabeisDTO {
	
	private Long id;
	private LocalDateTime data;
	private Double valor;
	private Long idPrograma01;
	private String nomePrograma01;
	private Long idProjeto01;
	private String nomeProjeto01;	
	private Long idCategoriaOrigem01;
	private String planoContaOrigem01;
	private String codigoPlanoContaOrigem01;	
	private Long idCategoriaDestino01;
	private String planoContaDestino01;
	private String codigoPlanoContaDestino01;	

	
	public MovimentacoesContabeisDTO() {
	}
	
	public MovimentacoesContabeisDTO(Object[] colunas) {
		this.id                           = (colunas[0] != null)? ((BigDecimal)colunas[0]).longValue() : null;
		this.data                         = (colunas[1] != null)? ((Timestamp)colunas[1]).toLocalDateTime() : null;
		this.valor                        = (colunas[2] != null)? ((BigDecimal)colunas[2]).doubleValue() : null;
		this.idPrograma01                 = (colunas[3] != null)? ((BigDecimal)colunas[3]).longValue() : null;
		this.nomePrograma01               = (String) colunas[4];
		this.idProjeto01                  = (colunas[5] != null)? ((BigDecimal)colunas[5]).longValue() : null;
		this.nomeProjeto01                = (String) colunas[6];
		this.idCategoriaOrigem01          = (colunas[7] != null)? ((BigDecimal)colunas[7]).longValue() : null;
		this.planoContaOrigem01           = (String) colunas[8];
		this.codigoPlanoContaOrigem01     = (String) colunas[9];
		this.idCategoriaDestino01         = (colunas[10] != null)? ((BigDecimal)colunas[10]).longValue() : null;
		this.planoContaDestino01          = (String) colunas[11];
		this.codigoPlanoContaDestino01    = (String) colunas[12];		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Long getIdPrograma01() {
		return idPrograma01;
	}

	public void setIdPrograma01(Long idPrograma01) {
		this.idPrograma01 = idPrograma01;
	}

	public String getNomePrograma01() {
		return nomePrograma01;
	}

	public void setNomePrograma01(String nomePrograma01) {
		this.nomePrograma01 = nomePrograma01;
	}

	public Long getIdProjeto01() {
		return idProjeto01;
	}

	public void setIdProjeto01(Long idProjeto01) {
		this.idProjeto01 = idProjeto01;
	}

	public String getNomeProjeto01() {
		return nomeProjeto01;
	}

	public void setNomeProjeto01(String nomeProjeto01) {
		this.nomeProjeto01 = nomeProjeto01;
	}

	public Long getIdCategoriaOrigem01() {
		return idCategoriaOrigem01;
	}

	public void setIdCategoriaOrigem01(Long idCategoriaOrigem01) {
		this.idCategoriaOrigem01 = idCategoriaOrigem01;
	}

	public String getPlanoContaOrigem01() {
		return planoContaOrigem01;
	}

	public void setPlanoContaOrigem01(String planoContaOrigem01) {
		this.planoContaOrigem01 = planoContaOrigem01;
	}

	public String getCodigoPlanoContaOrigem01() {
		return codigoPlanoContaOrigem01;
	}

	public void setCodigoPlanoContaOrigem01(String codigoPlanoContaOrigem01) {
		this.codigoPlanoContaOrigem01 = codigoPlanoContaOrigem01;
	}

	public Long getIdCategoriaDestino01() {
		return idCategoriaDestino01;
	}

	public void setIdCategoriaDestino01(Long idCategoriaDestino01) {
		this.idCategoriaDestino01 = idCategoriaDestino01;
	}

	public String getPlanoContaDestino01() {
		return planoContaDestino01;
	}

	public void setPlanoContaDestino01(String planoContaDestino01) {
		this.planoContaDestino01 = planoContaDestino01;
	}

	public String getCodigoPlanoContaDestino01() {
		return codigoPlanoContaDestino01;
	}

	public void setCodigoPlanoContaDestino01(String codigoPlanoContaDestino01) {
		this.codigoPlanoContaDestino01 = codigoPlanoContaDestino01;
	}

	
}

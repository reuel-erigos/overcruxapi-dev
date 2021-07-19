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
	private Long idPrograma02;
	private String nomePrograma02;
	private Long idProjeto02;
	private String nomeProjeto02;
	private Long idCategoriaOrigem02;
	private String planoContaOrigem02;
	private String codigoPlanoContaOrigem02;
	private Long idCategoriaDestino02;
	private String planoContaDestino02;
	private String codigoPlanoContaDestino02;

	
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
		this.idPrograma02                 = (colunas[13] != null)? ((BigDecimal)colunas[13]).longValue() : null;
		this.nomePrograma02               = (String) colunas[14];
		this.idProjeto02                  = (colunas[15] != null)? ((BigDecimal)colunas[15]).longValue() : null;
		this.nomeProjeto02                = (String) colunas[16];
		this.idCategoriaOrigem02          = (colunas[17] != null)? ((BigDecimal)colunas[17]).longValue() : null;
		this.planoContaOrigem02           = (String) colunas[18];
		this.codigoPlanoContaOrigem02     = (String) colunas[19];
		this.idCategoriaDestino02         = (colunas[20] != null)? ((BigDecimal)colunas[20]).longValue() : null;
		this.planoContaDestino02          = (String) colunas[21];
		this.codigoPlanoContaDestino02    = (String) colunas[22];		
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

	public Long getIdPrograma02() {
		return idPrograma02;
	}

	public void setIdPrograma02(Long idPrograma02) {
		this.idPrograma02 = idPrograma02;
	}

	public String getNomePrograma02() {
		return nomePrograma02;
	}

	public void setNomePrograma02(String nomePrograma02) {
		this.nomePrograma02 = nomePrograma02;
	}

	public Long getIdProjeto02() {
		return idProjeto02;
	}

	public void setIdProjeto02(Long idProjeto02) {
		this.idProjeto02 = idProjeto02;
	}

	public String getNomeProjeto02() {
		return nomeProjeto02;
	}

	public void setNomeProjeto02(String nomeProjeto02) {
		this.nomeProjeto02 = nomeProjeto02;
	}

	public Long getIdCategoriaOrigem02() {
		return idCategoriaOrigem02;
	}

	public void setIdCategoriaOrigem02(Long idCategoriaOrigem02) {
		this.idCategoriaOrigem02 = idCategoriaOrigem02;
	}

	public String getPlanoContaOrigem02() {
		return planoContaOrigem02;
	}

	public void setPlanoContaOrigem02(String planoContaOrigem02) {
		this.planoContaOrigem02 = planoContaOrigem02;
	}

	public String getCodigoPlanoContaOrigem02() {
		return codigoPlanoContaOrigem02;
	}

	public void setCodigoPlanoContaOrigem02(String codigoPlanoContaOrigem02) {
		this.codigoPlanoContaOrigem02 = codigoPlanoContaOrigem02;
	}

	public Long getIdCategoriaDestino02() {
		return idCategoriaDestino02;
	}

	public void setIdCategoriaDestino02(Long idCategoriaDestino02) {
		this.idCategoriaDestino02 = idCategoriaDestino02;
	}

	public String getPlanoContaDestino02() {
		return planoContaDestino02;
	}

	public void setPlanoContaDestino02(String planoContaDestino02) {
		this.planoContaDestino02 = planoContaDestino02;
	}

	public String getCodigoPlanoContaDestino02() {
		return codigoPlanoContaDestino02;
	}

	public void setCodigoPlanoContaDestino02(String codigoPlanoContaDestino02) {
		this.codigoPlanoContaDestino02 = codigoPlanoContaDestino02;
	}

	
	
}

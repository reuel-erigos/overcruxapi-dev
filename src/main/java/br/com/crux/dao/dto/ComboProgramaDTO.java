package br.com.crux.dao.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class ComboProgramaDTO {
	
	private Long id;
	private String nome;
	private LocalDateTime dataInicio;
	private LocalDateTime dataFim;
	
	public ComboProgramaDTO() {
	}
	
	public ComboProgramaDTO(Object[] colunas) {
		this.id      		= (colunas[0] != null)? ((BigDecimal)colunas[0]).longValue() : null;
		this.nome    		= (String) colunas[1];
		this.dataInicio 	= (colunas[2] != null)? ((Timestamp)colunas[2]).toLocalDateTime() : null;
		this.dataFim      	= (colunas[3] != null)? ((Timestamp)colunas[3]).toLocalDateTime() : null;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public LocalDateTime getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(LocalDateTime dataInicio) {
		this.dataInicio = dataInicio;
	}

	public LocalDateTime getDataFim() {
		return dataFim;
	}

	public void setDataFim(LocalDateTime dataFim) {
		this.dataFim = dataFim;
	}

	
}

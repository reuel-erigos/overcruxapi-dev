package br.com.crux.dao.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;

public class ComboAlunoDTO {
	
	private Long id;
	private String nome;
	private String matricula;
	private LocalDate dataEntrada;
	private LocalDate dataDesligamento;
	
	public ComboAlunoDTO() {
	}
	
	public ComboAlunoDTO(Object[] colunas) {
		this.id                = (colunas[0] != null)? ((BigDecimal)colunas[0]).longValue() : null;
		this.nome              = (String) colunas[1];
		this.matricula         = (String) colunas[2];
		this.dataEntrada       = (colunas[3] != null)? ((Timestamp)colunas[3]).toLocalDateTime().toLocalDate() : null;
		this.dataDesligamento  = (colunas[4] != null)? ((Timestamp)colunas[4]).toLocalDateTime().toLocalDate() : null;
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

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public LocalDate getDataEntrada() {
		return dataEntrada;
	}

	public void setDataEntrada(LocalDate dataEntrada) {
		this.dataEntrada = dataEntrada;
	}

	public LocalDate getDataDesligamento() {
		return dataDesligamento;
	}

	public void setDataDesligamento(LocalDate dataDesligamento) {
		this.dataDesligamento = dataDesligamento;
	}

	
}

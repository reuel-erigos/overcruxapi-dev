package br.com.crux.dao.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;

public class ExportacaoDadosAlunoDTO {
	
	private Long id;
	private String matricula;
	private String nomeBeneficiario;
	private LocalDate dataNascimento;
	private String nomeMae;
	private String nomePai;
	private String cpf;
	private String nis;
	private String email;
	private String celular;
	private String foneResidencial;
	private String foneComercial;
	private String unidade;
	private LocalDate dataEntrada;
	private LocalDate dataDesligamento;
	
	
	public ExportacaoDadosAlunoDTO() {
	}
	
	public ExportacaoDadosAlunoDTO(Object[] colunas) {
		this.id                  = (colunas[0] != null)? ((BigDecimal)colunas[0]).longValue() : null;
		this.matricula           = (String) colunas[1];
		this.nomeBeneficiario    = (String) colunas[2];
		this.dataNascimento      = (colunas[3] != null)? ((Timestamp)colunas[3]).toLocalDateTime().toLocalDate() : null;
		this.nomeMae             = (String) colunas[4];
		this.nomePai             = (String) colunas[5];
		this.cpf                 = (String) colunas[6];
		this.nis                 = (String) colunas[7];
		this.email               = (String) colunas[8];
		this.celular             = (String) colunas[9];
		this.foneResidencial     = (String) colunas[10];
		this.foneComercial       = (String) colunas[11];
		this.unidade             = (String) colunas[12];
		this.dataEntrada         = (colunas[13] != null)? ((Timestamp)colunas[13]).toLocalDateTime().toLocalDate() : null;
		this.dataDesligamento    = (colunas[14] != null)? ((Timestamp)colunas[14]).toLocalDateTime().toLocalDate() : null;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getNomeBeneficiario() {
		return nomeBeneficiario;
	}

	public void setNomeBeneficiario(String nomeBeneficiario) {
		this.nomeBeneficiario = nomeBeneficiario;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getNomeMae() {
		return nomeMae;
	}

	public void setNomeMae(String nomeMae) {
		this.nomeMae = nomeMae;
	}

	public String getNomePai() {
		return nomePai;
	}

	public void setNomePai(String nomePai) {
		this.nomePai = nomePai;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNis() {
		return nis;
	}

	public void setNis(String nis) {
		this.nis = nis;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getFoneResidencial() {
		return foneResidencial;
	}

	public void setFoneResidencial(String foneResidencial) {
		this.foneResidencial = foneResidencial;
	}

	public String getFoneComercial() {
		return foneComercial;
	}

	public void setFoneComercial(String foneComercial) {
		this.foneComercial = foneComercial;
	}

	public String getUnidade() {
		return unidade;
	}

	public void setUnidade(String unidade) {
		this.unidade = unidade;
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

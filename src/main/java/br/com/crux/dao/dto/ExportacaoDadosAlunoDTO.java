package br.com.crux.dao.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;

public class ExportacaoDadosAlunoDTO {
	
	private Long idPessoaFisica;
	private Long idAluno;
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
		this.idPessoaFisica      = (colunas[0] != null)? ((BigDecimal)colunas[0]).longValue() : null;
		this.idAluno             = (colunas[1] != null)? ((BigDecimal)colunas[1]).longValue() : null;
		this.matricula           = (String) colunas[2];
		this.nomeBeneficiario    = (String) colunas[2];
		this.dataNascimento      = (colunas[4] != null)? ((Timestamp)colunas[4]).toLocalDateTime().toLocalDate() : null;
		this.nomeMae             = (String) colunas[5];
		this.nomePai             = (String) colunas[6];
		this.cpf                 = (String) colunas[7];
		this.nis                 = (String) colunas[8];
		this.email               = (String) colunas[9];
		this.celular             = (String) colunas[10];
		this.foneResidencial     = (String) colunas[11];
		this.foneComercial       = (String) colunas[12];
		this.unidade             = (String) colunas[13];
		this.dataEntrada         = (colunas[14] != null)? ((Timestamp)colunas[14]).toLocalDateTime().toLocalDate() : null;
		this.dataDesligamento    = (colunas[15] != null)? ((Timestamp)colunas[15]).toLocalDateTime().toLocalDate() : null;
	}

	public Long getIdPessoaFisica() {
		return idPessoaFisica;
	}

	public void setIdPessoaFisica(Long id) {
		this.idPessoaFisica = id;
	}

	public Long getIdAluno() {
		return idAluno;
	}

	public void setIdAluno(Long idAluno) {
		this.idAluno = idAluno;
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

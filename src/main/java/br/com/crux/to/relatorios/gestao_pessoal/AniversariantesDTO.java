package br.com.crux.to.relatorios.gestao_pessoal;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

import br.com.crux.infra.util.DataUtil;

public class AniversariantesDTO {

	private Long   idUnidade        ;
	private Long   idPessoaFisica   ;
	private String codigoUnidade    ;
	private String nomeUnidade      ;
	private String nomePessoaFisica ;
	private String dataNascimento   ;
	private String mes              ;
	
	public AniversariantesDTO() {
	}
	
	public AniversariantesDTO(Object[] colunas) {
		this.idUnidade             = (colunas[0] != null)? ((BigDecimal)colunas[0]).longValue() : null;              
		this.idPessoaFisica        = (colunas[1] != null)? ((BigDecimal)colunas[1]).longValue() : null;
		this.codigoUnidade         = (colunas[2] != null) ? (String) colunas[2] : ""; 
		this.nomeUnidade           = (colunas[3] != null) ? (String) colunas[3] : ""; 
		this.nomePessoaFisica      = (colunas[4] != null) ? (String) colunas[4] : ""; 
		this.dataNascimento        = (colunas[5] != null) ? DataUtil.formataData(new Date(((Timestamp) colunas[5]).getTime()), "dd/MM/yyyy") : ""; 
		this.mes                   = (colunas[6] != null) ? (String) colunas[6] : ""; 
	}

	public Long getIdUnidade() {
		return idUnidade;
	}

	public void setIdUnidade(Long idUnidade) {
		this.idUnidade = idUnidade;
	}

	public Long getIdPessoaFisica() {
		return idPessoaFisica;
	}

	public void setIdPessoaFisica(Long idPessoaFisica) {
		this.idPessoaFisica = idPessoaFisica;
	}

	public String getCodigoUnidade() {
		return codigoUnidade;
	}

	public void setCodigoUnidade(String codigoUnidade) {
		this.codigoUnidade = codigoUnidade;
	}

	public String getNomeUnidade() {
		return nomeUnidade;
	}

	public void setNomeUnidade(String nomeUnidade) {
		this.nomeUnidade = nomeUnidade;
	}

	public String getNomePessoaFisica() {
		return nomePessoaFisica;
	}

	public void setNomePessoaFisica(String nomePessoaFisica) {
		this.nomePessoaFisica = nomePessoaFisica;
	}

	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getMes() {
		return mes;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}

		
	
}

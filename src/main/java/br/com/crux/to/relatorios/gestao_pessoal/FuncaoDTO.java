package br.com.crux.to.relatorios.gestao_pessoal;

import java.math.BigDecimal;

public class FuncaoDTO {
	
	private Long   idUnidade;
    private Long   idPessoaFisica;
    private Long   idCargo;
    private Long   idFuncao;
    private String codigoUnidade;
    private String nomeUnidade;
    private String nomePessoaFisica;
    private String nomeFuncao;
    private String codigoCargo;
    private String nomeCargo;
 
	public FuncaoDTO() {
	}
	
	public FuncaoDTO(Object[] colunas) {
		this.idUnidade        = (colunas[0] != null)? ((BigDecimal)colunas[0]).longValue() : null;         
		this.idPessoaFisica   = (colunas[1] != null)? ((BigDecimal)colunas[1]).longValue() : null;
		this.idCargo          = (colunas[2] != null)? ((BigDecimal)colunas[2]).longValue() : null;           
		this.idFuncao         = (colunas[3] != null)? ((BigDecimal)colunas[3]).longValue() : null;          
		this.codigoUnidade    = (colunas[4] != null) ? (String) colunas[4] : "";     
		this.nomeUnidade      = (colunas[5] != null) ? (String) colunas[5] : "";       
		this.nomePessoaFisica = (colunas[6] != null) ? (String) colunas[6] : "";  
		this.nomeFuncao       = (colunas[7] != null) ? (String) colunas[7] : "";        
		this.codigoCargo      = (colunas[8] != null) ? (String) colunas[8] : "";       
		this.nomeCargo        = (colunas[9] != null) ? (String) colunas[9] : "";         
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

	public Long getIdCargo() {
		return idCargo;
	}

	public void setIdCargo(Long idCargo) {
		this.idCargo = idCargo;
	}

	public Long getIdFuncao() {
		return idFuncao;
	}

	public void setIdFuncao(Long idFuncao) {
		this.idFuncao = idFuncao;
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

	public String getNomeFuncao() {
		return nomeFuncao;
	}

	public void setNomeFuncao(String nomeFuncao) {
		this.nomeFuncao = nomeFuncao;
	}

	public String getCodigoCargo() {
		return codigoCargo;
	}

	public void setCodigoCargo(String codigoCargo) {
		this.codigoCargo = codigoCargo;
	}

	public String getNomeCargo() {
		return nomeCargo;
	}

	public void setNomeCargo(String nomeCargo) {
		this.nomeCargo = nomeCargo;
	}

	
}

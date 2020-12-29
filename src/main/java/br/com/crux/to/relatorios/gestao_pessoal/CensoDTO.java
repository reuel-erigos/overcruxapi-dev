package br.com.crux.to.relatorios.gestao_pessoal;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

import br.com.crux.infra.util.DataUtil;

public class CensoDTO {
	
	private Long   idUnidade;
    private Long   idPessoaFisica;
    private Long   idFuncao;
    private Long   idCargo;
    private String codigoUnidade;
    private String nomeUnidade;
    private String nomePessoaFisica;
    private String dataNascimento;
    private String sexo;
    private String cpf;
    private String rg;
    private String orgaoRg;
    private String ufRg;
    private String email;
    private String descEscolaridade;
    private String descProfissao; 
    private String codigoTipoContratacao;
    private String nomeFuncao;
    private String codigoCargo;
    private String nomeCargo; 
    private String qtdCargaHorariaMinima;
    private String qtdCargaHorariaMaxima;
    private String dataAdmissao;

    public CensoDTO() {
	}

	public CensoDTO(Object[] colunas) {
		this.idUnidade             = (colunas[0] != null)? ((BigDecimal)colunas[0]).longValue() : null;              
		this.idPessoaFisica        = (colunas[1] != null)? ((BigDecimal)colunas[1]).longValue() : null;
		this.idFuncao              = (colunas[2] != null)? ((BigDecimal)colunas[2]).longValue() : null;
		this.idCargo               = (colunas[3] != null)? ((BigDecimal)colunas[3]).longValue() : null;
		this.codigoUnidade         = (colunas[4] != null) ? (String) colunas[4] : ""; 
		this.nomeUnidade           = (colunas[5] != null) ? (String) colunas[5] : "";
		this.nomePessoaFisica      = (colunas[6] != null) ? (String) colunas[6] : ""; 
		this.dataNascimento        = (colunas[7] != null) ? DataUtil.formataData(new Date(((Timestamp) colunas[7]).getTime()), "dd/MM/yyyy") : "";         
		this.sexo                  = (colunas[8] != null) ? (String) colunas[8] : "";
		this.cpf                   = (colunas[9] != null) ? (String) colunas[9] : "";                    
		this.rg                    = (colunas[10] != null) ? (String) colunas[10] : "";  
		this.orgaoRg               = (colunas[11] != null) ? (String) colunas[11] : "";                
		this.ufRg                  = (colunas[12] != null) ? (String) colunas[12] : ""; 
		this.email                 = (colunas[13] != null) ? (String) colunas[13] : "";
		this.descEscolaridade      = (colunas[14] != null) ? (String) colunas[14] : "";
		this.descProfissao         = (colunas[15] != null) ? (String) colunas[15] : "";
		this.codigoTipoContratacao = (colunas[16] != null) ? (String) colunas[16] : "";
		this.nomeFuncao            = (colunas[17] != null) ? (String) colunas[17] : "";
		this.codigoCargo           = (colunas[18] != null) ? (String) colunas[18] : "";
		this.nomeCargo             = (colunas[19] != null) ? (String) colunas[19] : "";
		this.qtdCargaHorariaMinima = (colunas[20] != null) ? (String) colunas[20] : "";
		this.qtdCargaHorariaMaxima = (colunas[21] != null) ? (String) colunas[21] : "";
		this.dataAdmissao          = (colunas[22] != null) ? DataUtil.formataData(new Date(((Timestamp) colunas[22]).getTime()), "dd/MM/yyyy") : "";
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

	public Long getIdFuncao() {
		return idFuncao;
	}

	public void setIdFuncao(Long idFuncao) {
		this.idFuncao = idFuncao;
	}

	public Long getIdCargo() {
		return idCargo;
	}

	public void setIdCargo(Long idCargo) {
		this.idCargo = idCargo;
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

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getOrgaoRg() {
		return orgaoRg;
	}

	public void setOrgaoRg(String orgaoRg) {
		this.orgaoRg = orgaoRg;
	}

	public String getUfRg() {
		return ufRg;
	}

	public void setUfRg(String ufRg) {
		this.ufRg = ufRg;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDescEscolaridade() {
		return descEscolaridade;
	}

	public void setDescEscolaridade(String descEscolaridade) {
		this.descEscolaridade = descEscolaridade;
	}

	public String getDescProfissao() {
		return descProfissao;
	}

	public void setDescProfissao(String descProfissao) {
		this.descProfissao = descProfissao;
	}

	public String getCodigoTipoContratacao() {
		return codigoTipoContratacao;
	}

	public void setCodigoTipoContratacao(String codigoTipoContratacao) {
		this.codigoTipoContratacao = codigoTipoContratacao;
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

	public String getQtdCargaHorariaMinima() {
		return qtdCargaHorariaMinima;
	}

	public void setQtdCargaHorariaMinima(String qtdCargaHorariaMinima) {
		this.qtdCargaHorariaMinima = qtdCargaHorariaMinima;
	}

	public String getQtdCargaHorariaMaxima() {
		return qtdCargaHorariaMaxima;
	}

	public void setQtdCargaHorariaMaxima(String qtdCargaHorariaMaxima) {
		this.qtdCargaHorariaMaxima = qtdCargaHorariaMaxima;
	}

	public String getDataAdmissao() {
		return dataAdmissao;
	}

	public void setDataAdmissao(String dataAdmissao) {
		this.dataAdmissao = dataAdmissao;
	}

    
}

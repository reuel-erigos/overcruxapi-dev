package br.com.crux.to.relatorios.beneficiarios;

import java.math.BigDecimal;

import br.com.crux.infra.util.NumeroUtil;

public class FichaMatriculaDTO {
	
	private Long       idPessoaFisica;
	private String     nome;
	private String     endereco;
	private String     bairro;
	private String     ufEndereco;
	private String     cep;
	private String     foneCelular;
	private String     telefoneResidencial;
	private String     telefoneComercial;
	private String     sexo;
	private String     dataNascimento;
	private String     naturalidade;
	private String     nomeMae;
	private String     nomePai;
	private String     encaminhado;
	private String     descPublicoPrioritario;
	private String     dataMatricula;
	private String     turno;
	private String     atividade;
	private String     escola;
	private String     serie;
	private String     descProblemaSaude;
	private String     descMedicamentosControlados;
	private String     moraComPais;
	private String     paisCasados;
	private String     rendaFamiliar;
	private String     fonteRendaFamiliar;
	private String     observacoes;
	private String     descBuscaEscola;
	private String     apresentacaoExterna;
	private String     nomeResponsavel;
	private String     dataNascimentoResponsavel;
	private String     rgResponsavel;
	private String     cpfResponsavel;
	private String     foneCelularResponsavel;
	private String     foneResidencialResponsavel;
	private String     foneComercialResponsavel;
	private String     nomeGrauParentesco;
	private String     nisAluno;
	private String     nisResponsavel;
	private String     temProblemaSaude;
	private String     tomaMedicamentosControlados;
	
	

	public FichaMatriculaDTO() {
	}
	
	

	public FichaMatriculaDTO(Object[] colunas) {
		this.idPessoaFisica                  = (colunas[0] != null)? ((BigDecimal)colunas[0]).longValue() : null;    
		this.nome                            = (String) colunas[1];
		this.endereco                        = (String) colunas[2];
		this.bairro                          = (String) colunas[3];
		this.ufEndereco                      = (String) colunas[4];
		this.cep                             = (colunas[5] != null) ? ((String) colunas[5]) : "";
		this.foneCelular                     = (String) colunas[6];
		this.telefoneResidencial             = (String) colunas[7];
		this.telefoneComercial               = (String) colunas[8];
		this.sexo                            = (String) colunas[9];
		this.dataNascimento                  = (colunas[10] != null) ? ((String) colunas[10]) : "";
		this.naturalidade                    = (String) colunas[11];
		this.nomeMae                         = (String) colunas[12];
		this.nomePai                         = (String) colunas[13];
		this.encaminhado                     = (String) colunas[14];
		this.descPublicoPrioritario          = (String) colunas[15];
		this.dataMatricula                   = (colunas[16] != null) ? ((String) colunas[16]) : "";
		this.turno                           = (String) colunas[17];
		this.atividade                       = (String) colunas[18];
		this.escola                          = (String) colunas[19];
		this.serie                           = (String) colunas[20];
		this.descProblemaSaude               = (String) colunas[21];
		this.descMedicamentosControlados     = (String) colunas[22];
		this.moraComPais                     = (String) colunas[23];
		this.paisCasados                     = (String) colunas[24];
		this.rendaFamiliar                   = (colunas[25] != null)? NumeroUtil.formataMoeda(((BigDecimal)colunas[25]).doubleValue()) : null;
		this.fonteRendaFamiliar              = (String) colunas[26];
		this.observacoes                     = (String) colunas[27];
		this.descBuscaEscola                 = (String) colunas[28];
		this.apresentacaoExterna             = (String) colunas[29];
		this.nomeResponsavel                 = (String) colunas[30];
		this.dataNascimentoResponsavel       = (colunas[31] != null) ? ((String) colunas[31]) : "";
		this.rgResponsavel                   = (String) colunas[32];
		this.cpfResponsavel                  = (String) colunas[33];
		this.foneCelularResponsavel          = (colunas[34] != null) ? ((String) colunas[34]) : "";
		this.foneResidencialResponsavel      = (colunas[35] != null) ? ((String) colunas[35]) : "";
		this.foneComercialResponsavel        = (colunas[36] != null) ? ((String) colunas[36]) : "";
		this.nomeGrauParentesco              = (String) colunas[37];
		this.nisAluno                        = (String) colunas[38];
		this.nisResponsavel                  = (String) colunas[39];
		this.temProblemaSaude                = (String) colunas[40];
		this.tomaMedicamentosControlados     = (String) colunas[41];

	}

	public Long getIdPessoaFisica() {
		return idPessoaFisica;
	}

	public void setIdPessoaFisica(Long idPessoaFisica) {
		this.idPessoaFisica = idPessoaFisica;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getUfEndereco() {
		return ufEndereco;
	}

	public void setUfEndereco(String ufEndereco) {
		this.ufEndereco = ufEndereco;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getFoneCelular() {
		return foneCelular;
	}

	public void setFoneCelular(String foneCelular) {
		this.foneCelular = foneCelular;
	}

	public String getTelefoneResidencial() {
		return telefoneResidencial;
	}

	public void setTelefoneResidencial(String telefoneResidencial) {
		this.telefoneResidencial = telefoneResidencial;
	}

	public String getTelefoneComercial() {
		return telefoneComercial;
	}

	public void setTelefoneComercial(String telefoneComercial) {
		this.telefoneComercial = telefoneComercial;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getNaturalidade() {
		return naturalidade;
	}

	public void setNaturalidade(String naturalidade) {
		this.naturalidade = naturalidade;
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

	public String getEncaminhado() {
		return encaminhado;
	}

	public void setEncaminhado(String encaminhado) {
		this.encaminhado = encaminhado;
	}

	public String getDescPublicoPrioritario() {
		return descPublicoPrioritario;
	}

	public void setDescPublicoPrioritario(String descPublicoPrioritario) {
		this.descPublicoPrioritario = descPublicoPrioritario;
	}

	public String getDataMatricula() {
		return dataMatricula;
	}

	public void setDataMatricula(String dataMatricula) {
		this.dataMatricula = dataMatricula;
	}

	public String getTurno() {
		return turno;
	}

	public void setTurno(String turno) {
		this.turno = turno;
	}

	public String getAtividade() {
		return atividade;
	}

	public void setAtividade(String atividade) {
		this.atividade = atividade;
	}

	public String getEscola() {
		return escola;
	}

	public void setEscola(String escola) {
		this.escola = escola;
	}

	public String getSerie() {
		return serie;
	}

	public void setSerie(String serie) {
		this.serie = serie;
	}

	public String getDescProblemaSaude() {
		return descProblemaSaude;
	}

	public void setDescProblemaSaude(String descProblemaSaude) {
		this.descProblemaSaude = descProblemaSaude;
	}

	public String getDescMedicamentosControlados() {
		return descMedicamentosControlados;
	}

	public void setDescMedicamentosControlados(String descMedicamentosControlados) {
		this.descMedicamentosControlados = descMedicamentosControlados;
	}

	public String getMoraComPais() {
		return moraComPais;
	}

	public void setMoraComPais(String moraComPais) {
		this.moraComPais = moraComPais;
	}

	public String getPaisCasados() {
		return paisCasados;
	}

	public void setPaisCasados(String paisCasados) {
		this.paisCasados = paisCasados;
	}

	public String getRendaFamiliar() {
		return rendaFamiliar;
	}

	public void setRendaFamiliar(String rendaFamiliar) {
		this.rendaFamiliar = rendaFamiliar;
	}

	public String getFonteRendaFamiliar() {
		return fonteRendaFamiliar;
	}

	public void setFonteRendaFamiliar(String fonteRendaFamiliar) {
		this.fonteRendaFamiliar = fonteRendaFamiliar;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public String getDescBuscaEscola() {
		return descBuscaEscola;
	}

	public void setDescBuscaEscola(String descBuscaEscola) {
		this.descBuscaEscola = descBuscaEscola;
	}

	public String getApresentacaoExterna() {
		return apresentacaoExterna;
	}

	public void setApresentacaoExterna(String apresentacaoExterna) {
		this.apresentacaoExterna = apresentacaoExterna;
	}

	public String getNomeResponsavel() {
		return nomeResponsavel;
	}

	public void setNomeResponsavel(String nomeResponsavel) {
		this.nomeResponsavel = nomeResponsavel;
	}

	public String getDataNascimentoResponsavel() {
		return dataNascimentoResponsavel;
	}

	public void setDataNascimentoResponsavel(String dataNascimentoResponsavel) {
		this.dataNascimentoResponsavel = dataNascimentoResponsavel;
	}

	public String getRgResponsavel() {
		return rgResponsavel;
	}

	public void setRgResponsavel(String rgResponsavel) {
		this.rgResponsavel = rgResponsavel;
	}

	public String getCpfResponsavel() {
		return cpfResponsavel;
	}

	public void setCpfResponsavel(String cpfResponsavel) {
		this.cpfResponsavel = cpfResponsavel;
	}

	public String getFoneCelularResponsavel() {
		return foneCelularResponsavel;
	}

	public void setFoneCelularResponsavel(String foneCelularResponsavel) {
		this.foneCelularResponsavel = foneCelularResponsavel;
	}

	public String getFoneResidencialResponsavel() {
		return foneResidencialResponsavel;
	}

	public void setFoneResidencialResponsavel(String foneResidencialResponsavel) {
		this.foneResidencialResponsavel = foneResidencialResponsavel;
	}

	public String getFoneComercialResponsavel() {
		return foneComercialResponsavel;
	}

	public void setFoneComercialResponsavel(String foneComercialResponsavel) {
		this.foneComercialResponsavel = foneComercialResponsavel;
	}

	public String getNomeGrauParentesco() {
		return nomeGrauParentesco;
	}

	public void setNomeGrauParentesco(String nomeGrauParentesco) {
		this.nomeGrauParentesco = nomeGrauParentesco;
	}

	public String getNisAluno() {
		return nisAluno;
	}

	public void setNisAluno(String nisAluno) {
		this.nisAluno = nisAluno;
	}

	public String getNisResponsavel() {
		return nisResponsavel;
	}

	public void setNisResponsavel(String nisResponsavel) {
		this.nisResponsavel = nisResponsavel;
	}

	public String getTemProblemaSaude() {
		return temProblemaSaude;
	}

	public void setTemProblemaSaude(String temProblemaSaude) {
		this.temProblemaSaude = temProblemaSaude;
	}

	public String getTomaMedicamentosControlados() {
		return tomaMedicamentosControlados;
	}

	public void setTomaMedicamentosControlados(String tomaMedicamentosControlados) {
		this.tomaMedicamentosControlados = tomaMedicamentosControlados;
	}
}


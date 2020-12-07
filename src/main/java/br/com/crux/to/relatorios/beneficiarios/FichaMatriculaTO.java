package br.com.crux.to.relatorios.beneficiarios;

import java.time.LocalDate;

public class FichaMatriculaTO {

	private Long       idPessoaFisica;
	private String     nome;
	private String     endereco;
	private String     bairro;
	private String     ufEndereco;
	private Long       cep;
	private String     foneCelular;
	private String     telefoneResidencial;
	private String     telefoneComercial;
	private String     sexo;
	private LocalDate  dataNascimento;
	private String     naturalidade;
	private String     nomeMae;
	private String     nomePai;
	private String     encaminhado;
	private String     descPublicoPrioritario;
	private LocalDate  dataMatricula;
	private String     turno;
	private String     atividade;
	private String     escola;
	private String     serie;
	private String     descProblemaSaude;
	private String     descMedicamentosControlados;
	private Boolean    moraComPais;
	private Boolean    paisCasados;
	private Double     rendaFamiliar;
	private String     fonteRendaFamiliar;
	private String     observacoes;
	private String     descBuscaEscola;
	private Boolean    apresentacaoExterna;
	private String     nomeResponsavel;
	private LocalDate  dataNascimentoResponsavel;
	private String     rgResponsavel;
	private String     cpfResponsavel;
	private String     foneCelularResponsavel;
	private String     foneResidencialResponsavel;
	private String     foneComercialResponsavel;
	private String     nomeGrauParentesco;
	private String     nisAluno;
	private String     nisResponsavel;

	public FichaMatriculaTO() {
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

	public Long getCep() {
		return cep;
	}

	public void setCep(Long cep) {
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

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
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

	public LocalDate getDataMatricula() {
		return dataMatricula;
	}

	public void setDataMatricula(LocalDate dataMatricula) {
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

	public Boolean getMoraComPais() {
		return moraComPais;
	}

	public void setMoraComPais(Boolean moraComPais) {
		this.moraComPais = moraComPais;
	}

	public Boolean getPaisCasados() {
		return paisCasados;
	}

	public void setPaisCasados(Boolean paisCasados) {
		this.paisCasados = paisCasados;
	}

	public Double getRendaFamiliar() {
		return rendaFamiliar;
	}

	public void setRendaFamiliar(Double rendaFamiliar) {
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

	public Boolean getApresentacaoExterna() {
		return apresentacaoExterna;
	}

	public void setApresentacaoExterna(Boolean apresentacaoExterna) {
		this.apresentacaoExterna = apresentacaoExterna;
	}

	public String getNomeResponsavel() {
		return nomeResponsavel;
	}

	public void setNomeResponsavel(String nomeResponsavel) {
		this.nomeResponsavel = nomeResponsavel;
	}

	public LocalDate getDataNascimentoResponsavel() {
		return dataNascimentoResponsavel;
	}

	public void setDataNascimentoResponsavel(LocalDate dataNascimentoResponsavel) {
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


	

}

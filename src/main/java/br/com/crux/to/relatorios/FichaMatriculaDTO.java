package br.com.crux.to.relatorios;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;

public class FichaMatriculaDTO {

	private Long idAluno;
	private Long idPessoaFisica;
	private String numeroMatriculaAluno;
	private String nome;
	private String dataNascimento;
	private String numeroCpf;
	private String endereco;
	private String bairro;
	private String ufEndereco;
	private String nomeMae;
	private String nomePai;
	private String cep;
	private String foneCelular;
	private String telefoneResidencial;
	private String telefoneComercial;
	private String nomeResponsavel;
	private String dataNascimentoResponsavel;
	private String numeroCpfResponsavel;
	private String rgResponsavel;
	private String grauParentesco;
	private String foneCelularResponsavel;
	private String foneResidencialResponsavel;
	private String foneComercialResponsavel;
	private String codigoUnidade;
	private String programa;
	private String diasSemana;
	private Long horaInicio;
	private Long horaFim;
	private String apresentacaoExterna;
	private LocalDate dataMatricula;
	private String cursoEscola;
	private String escola;
	private String escolaridade;
	private String observacaoDeclaracaoMatricula;
	private LocalDate dataDeclaracaoMatricula;

	public FichaMatriculaDTO() {
	}
	
	

	public FichaMatriculaDTO(Object[] colunas) {
		this.idAluno                       = (colunas[0] != null)? ((BigDecimal)colunas[0]).longValue() : null;
		this.idPessoaFisica                = (colunas[1] != null)? ((BigDecimal)colunas[1]).longValue() : null;
		this.numeroMatriculaAluno          = (String) colunas[2];
		this.nome                          = (String) colunas[3];
		this.dataNascimento                = (String) colunas[4];
		this.numeroCpf                     = (String) colunas[5];
		this.endereco                      = (String) colunas[6];
		this.bairro                        = (String) colunas[7];
		this.ufEndereco                    = (String) colunas[8];
		this.nomeMae                       = (String) colunas[9];
		this.nomePai                       = (String) colunas[10];
		this.cep                           = (colunas[11] != null)? ((BigDecimal)colunas[11]).toString() : null;
		this.foneCelular                   = (String) colunas[12];
		this.telefoneResidencial           = (String) colunas[13];
		this.telefoneComercial             = (String) colunas[14];
		this.nomeResponsavel               = (String) colunas[15];
		this.dataNascimentoResponsavel     = (String) colunas[16];
		this.numeroCpfResponsavel          = (String) colunas[17];
		this.rgResponsavel                 = (String) colunas[18];
		this.grauParentesco                = (String) colunas[19];
		this.foneCelularResponsavel        = (String) colunas[20];
		this.foneResidencialResponsavel    = (String) colunas[21];
		this.foneComercialResponsavel      = (String) colunas[22];
		this.codigoUnidade                 = (String) colunas[23];
		this.programa                      = (String) colunas[24];
		this.diasSemana                    = (String) colunas[25];
		this.horaInicio                    = (colunas[26] != null)? ((BigDecimal)colunas[26]).longValue() : null;;
		this.horaFim                       = (colunas[27] != null)? ((BigDecimal)colunas[27]).longValue() : null;;
		this.apresentacaoExterna           = (String) colunas[28];
		this.dataMatricula                 = (colunas[29] != null)? ((Timestamp)colunas[29]).toLocalDateTime().toLocalDate() : null;;;
		this.cursoEscola                   = (String) colunas[30];
		this.escola                        = (String) colunas[31];
		this.escolaridade                  = (String) colunas[32];
		this.observacaoDeclaracaoMatricula = (String) colunas[33];
		this.dataDeclaracaoMatricula       = (colunas[34] != null)? ((Timestamp)colunas[34]).toLocalDateTime().toLocalDate() : null;;
	}

	public Long getIdAluno() {
		return idAluno;
	}

	public void setIdAluno(Long idAluno) {
		this.idAluno = idAluno;
	}

	public Long getIdPessoaFisica() {
		return idPessoaFisica;
	}

	public void setIdPessoaFisica(Long idPessoaFisica) {
		this.idPessoaFisica = idPessoaFisica;
	}

	public String getNumeroMatriculaAluno() {
		return numeroMatriculaAluno;
	}

	public void setNumeroMatriculaAluno(String numeroMatriculaAluno) {
		this.numeroMatriculaAluno = numeroMatriculaAluno;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getNumeroCpf() {
		return numeroCpf;
	}

	public void setNumeroCpf(String numeroCpf) {
		this.numeroCpf = numeroCpf;
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

	public String getNumeroCpfResponsavel() {
		return numeroCpfResponsavel;
	}

	public void setNumeroCpfResponsavel(String numeroCpfResponsavel) {
		this.numeroCpfResponsavel = numeroCpfResponsavel;
	}

	public String getRgResponsavel() {
		return rgResponsavel;
	}

	public void setRgResponsavel(String rgResponsavel) {
		this.rgResponsavel = rgResponsavel;
	}

	public String getGrauParentesco() {
		return grauParentesco;
	}

	public void setGrauParentesco(String grauParentesco) {
		this.grauParentesco = grauParentesco;
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

	public String getCodigoUnidade() {
		return codigoUnidade;
	}

	public void setCodigoUnidade(String codigoUnidade) {
		this.codigoUnidade = codigoUnidade;
	}

	public String getPrograma() {
		return programa;
	}

	public void setPrograma(String programa) {
		this.programa = programa;
	}

	public String getDiasSemana() {
		return diasSemana;
	}

	public void setDiasSemana(String diasSemana) {
		this.diasSemana = diasSemana;
	}

	public Long getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(Long horaInicio) {
		this.horaInicio = horaInicio;
	}

	public Long getHoraFim() {
		return horaFim;
	}

	public void setHoraFim(Long horaFim) {
		this.horaFim = horaFim;
	}

	public String getApresentacaoExterna() {
		return apresentacaoExterna;
	}

	public void setApresentacaoExterna(String apresentacaoExterna) {
		this.apresentacaoExterna = apresentacaoExterna;
	}

	public LocalDate getDataMatricula() {
		return dataMatricula;
	}

	public void setDataMatricula(LocalDate dataMatricula) {
		this.dataMatricula = dataMatricula;
	}

	public String getCursoEscola() {
		return cursoEscola;
	}

	public void setCursoEscola(String cursoEscola) {
		this.cursoEscola = cursoEscola;
	}

	public String getEscola() {
		return escola;
	}

	public void setEscola(String escola) {
		this.escola = escola;
	}

	public String getEscolaridade() {
		return escolaridade;
	}

	public void setEscolaridade(String escolaridade) {
		this.escolaridade = escolaridade;
	}

	public String getObservacaoDeclaracaoMatricula() {
		return observacaoDeclaracaoMatricula;
	}

	public void setObservacaoDeclaracaoMatricula(String observacaoDeclaracaoMatricula) {
		this.observacaoDeclaracaoMatricula = observacaoDeclaracaoMatricula;
	}

	public LocalDate getDataDeclaracaoMatricula() {
		return dataDeclaracaoMatricula;
	}

	public void setDataDeclaracaoMatricula(LocalDate dataDeclaracaoMatricula) {
		this.dataDeclaracaoMatricula = dataDeclaracaoMatricula;
	}



}

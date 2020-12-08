package br.com.crux.to.relatorios.beneficiarios;

import java.math.BigDecimal;

public class DeclaracaoBeneficiarioDTO {
	
	private Long   idAluno                      ;
	private Long   idPessoaFisica               ;
	private String matriculaAluno               ;
	private String nomeAluno                    ;
	private String dataNascimentoAluno          ;
	private String cpfAluno                     ;
	private String enderecoAluno                ;
	private String bairroAluno                  ;
	private String ufEnderecoAluno              ;
	private String nomeMae                      ;
	private String nomePai                      ;
	private Long   cep                          ;
	private String foneCelular                  ;
	private String foneResidencial              ;
	private String foneComercial                ;
	private String nomeResponsavel              ;
	private String dataNascimentoResponsavel    ;
	private String cpfResponsavel               ;
	private String rgResponsavel                ;
	private String grauParentesco               ;
	private String foneCelularResponsavel       ;
	private String foneResidencialResponsavel   ;
	private String foneComercialResponsavel     ;
	private String codigoUnidade                ;
	private String programa                     ;
	private String diasSemana                   ;
	private Long   horaInicio                   ;
	private Long   horaFim                      ;
	private String participaApresentacoesExterna;
	private String dataMatricula                ;
	private String cursoEscola                  ;
	private String escola                       ;
	private String escolaridade                 ;
	private String observacaoDeclaracaoMatricula;
	private String dataDeclaracaoMatricula      ;
	private String turma                        ;
	private String foneCelularMae               ;
	private String foneResidencialMae           ;
	private String foneComercialMae             ;
	private String foneCelularPai               ;
	private String foneResidencialPai           ;
	private String foneComercialPai             ;

	
	public DeclaracaoBeneficiarioDTO() {
	}

	public DeclaracaoBeneficiarioDTO(Object[] colunas) {
		this.idAluno                         = (colunas[0] != null)? ((BigDecimal)colunas[0]).longValue() : null;
		this.idPessoaFisica                  = (colunas[1] != null)? ((BigDecimal)colunas[1]).longValue() : null;
		this.matriculaAluno                  = (colunas[2] != null) ? (String) colunas[2] : "";
		this.nomeAluno                       = (colunas[3] != null) ? (String) colunas[3] : "";
		this.dataNascimentoAluno             = (colunas[4] != null) ? (String) colunas[4] : "";
		this.cpfAluno                        = (colunas[5] != null) ? (String) colunas[5] : "";
		this.enderecoAluno                   = (colunas[6] != null) ? (String) colunas[6] : "";
		this.bairroAluno                     = (colunas[7] != null) ? (String) colunas[7] : "";
		this.ufEnderecoAluno                 = (colunas[8] != null) ? (String) colunas[8] : "";
		this.nomeMae                         = (colunas[9] != null) ? (String) colunas[9] : "";
		this.nomePai                         = (colunas[10] != null) ? (String) colunas[10]: "";
		this.cep                             = (colunas[11] != null)? ((BigDecimal)colunas[11]).longValue() : null;
		this.foneCelular                     = (colunas[12] != null) ? (String) colunas[12] : "";
		this.foneResidencial                 = (colunas[13] != null) ? (String) colunas[13] : "";
		this.foneComercial                   = (colunas[14] != null) ? (String) colunas[14] : "";
		this.nomeResponsavel                 = (colunas[15] != null) ? (String) colunas[15] : "";
		this.dataNascimentoResponsavel       = (colunas[16] != null) ? (String) colunas[16] : "";
		this.cpfResponsavel                  = (colunas[17] != null) ? (String) colunas[17] : "";
		this.rgResponsavel                   = (colunas[18] != null) ? (String) colunas[18] : "";
		this.grauParentesco                  = (colunas[19] != null) ? (String) colunas[19] : "";
		this.foneCelularResponsavel          = (colunas[20] != null) ? (String) colunas[20] : "";
		this.foneResidencialResponsavel      = (colunas[21] != null) ? (String) colunas[21] : "";
		this.foneComercialResponsavel        = (colunas[22] != null) ? (String) colunas[22] : "";
		this.codigoUnidade                   = (colunas[23] != null) ? (String) colunas[23] : "";
		this.programa                        = (colunas[24] != null) ? (String) colunas[24] : "";
		this.diasSemana                      = (colunas[26] != null) ? (String) colunas[25] : "";
		this.horaInicio                      = (colunas[26] != null)? ((BigDecimal)colunas[26]).longValue() : null;
		this.horaFim                         = (colunas[27] != null)? ((BigDecimal)colunas[27]).longValue() : null;
		this.participaApresentacoesExterna   = (colunas[28] != null) ? (String) colunas[28] : "";
		this.dataMatricula                   = (colunas[29] != null) ? (String) colunas[29] : "";
		this.cursoEscola                     = (colunas[30] != null) ? (String) colunas[30] : "";
		this.escola                          = (colunas[31] != null) ? (String) colunas[31] : "";
		this.escolaridade                    = (colunas[32] != null) ? (String) colunas[32] : "";
		this.observacaoDeclaracaoMatricula   = (colunas[33] != null) ? (String) colunas[33] : "";
		this.dataDeclaracaoMatricula         = (colunas[34] != null) ? (String) colunas[34] : "";
		this.turma                           = (colunas[35] != null) ? (String) colunas[35] : "";
		this.foneCelularMae                  = (colunas[36] != null) ? (String) colunas[36] : "";
		this.foneResidencialMae              = (colunas[37] != null) ? (String) colunas[37] : "";
		this.foneComercialMae                = (colunas[38] != null) ? (String) colunas[38] : "";
		this.foneCelularPai                  = (colunas[39] != null) ? (String) colunas[39] : "";
		this.foneResidencialPai              = (colunas[40] != null) ? (String) colunas[40] : "";
		this.foneComercialPai                = (colunas[41] != null) ? (String) colunas[41] : "";

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


	public String getMatriculaAluno() {
		return matriculaAluno;
	}


	public void setMatriculaAluno(String matriculaAluno) {
		this.matriculaAluno = matriculaAluno;
	}


	public String getNomeAluno() {
		return nomeAluno;
	}


	public void setNomeAluno(String nomeAluno) {
		this.nomeAluno = nomeAluno;
	}


	public String getDataNascimentoAluno() {
		return dataNascimentoAluno;
	}


	public void setDataNascimentoAluno(String dataNascimentoAluno) {
		this.dataNascimentoAluno = dataNascimentoAluno;
	}


	public String getCpfAluno() {
		return cpfAluno;
	}


	public void setCpfAluno(String cpfAluno) {
		this.cpfAluno = cpfAluno;
	}


	public String getEnderecoAluno() {
		return enderecoAluno;
	}


	public void setEnderecoAluno(String enderecoAluno) {
		this.enderecoAluno = enderecoAluno;
	}


	public String getBairroAluno() {
		return bairroAluno;
	}


	public void setBairroAluno(String bairroAluno) {
		this.bairroAluno = bairroAluno;
	}


	public String getUfEnderecoAluno() {
		return ufEnderecoAluno;
	}


	public void setUfEnderecoAluno(String ufEnderecoAluno) {
		this.ufEnderecoAluno = ufEnderecoAluno;
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


	public String getCpfResponsavel() {
		return cpfResponsavel;
	}


	public void setCpfResponsavel(String cpfResponsavel) {
		this.cpfResponsavel = cpfResponsavel;
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


	public String getParticipaApresentacoesExterna() {
		return participaApresentacoesExterna;
	}


	public void setParticipaApresentacoesExterna(String participaApresentacoesExterna) {
		this.participaApresentacoesExterna = participaApresentacoesExterna;
	}


	public String getDataMatricula() {
		return dataMatricula;
	}


	public void setDataMatricula(String dataMatricula) {
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


	public String getDataDeclaracaoMatricula() {
		return dataDeclaracaoMatricula;
	}


	public void setDataDeclaracaoMatricula(String dataDeclaracaoMatricula) {
		this.dataDeclaracaoMatricula = dataDeclaracaoMatricula;
	}

	public String getTurma() {
		return turma;
	}

	public void setTurma(String turma) {
		this.turma = turma;
	}

	public String getFoneCelularMae() {
		return foneCelularMae;
	}

	public void setFoneCelularMae(String foneCelularMae) {
		this.foneCelularMae = foneCelularMae;
	}

	public String getFoneResidencialMae() {
		return foneResidencialMae;
	}

	public void setFoneResidencialMae(String foneResidencialMae) {
		this.foneResidencialMae = foneResidencialMae;
	}

	public String getFoneComercialMae() {
		return foneComercialMae;
	}

	public void setFoneComercialMae(String foneComercialMae) {
		this.foneComercialMae = foneComercialMae;
	}

	public String getFoneCelularPai() {
		return foneCelularPai;
	}

	public void setFoneCelularPai(String foneCelularPai) {
		this.foneCelularPai = foneCelularPai;
	}

	public String getFoneResidencialPai() {
		return foneResidencialPai;
	}

	public void setFoneResidencialPai(String foneResidencialPai) {
		this.foneResidencialPai = foneResidencialPai;
	}

	public String getFoneComercialPai() {
		return foneComercialPai;
	}

	public void setFoneComercialPai(String foneComercialPai) {
		this.foneComercialPai = foneComercialPai;
	}
	
}

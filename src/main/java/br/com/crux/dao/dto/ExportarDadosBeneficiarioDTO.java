package br.com.crux.dao.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;

public class ExportarDadosBeneficiarioDTO {
	
	private String    nomeAluno                                        ; // varchar(255)
    private String    dataNascimentoAluno                              ; // text
    private String    naturalidadeAluno                                ; // varchar(200)
    private String    ufNascimentoAluno                                ; // varchar(2)
    private String    sexoAluno                                        ; // varchar(1)
    private String    racaAluno                                        ; // varchar(200)
    private String    nomeMaeAluno                                     ; // varchar(200)
    private String    nomePaiAluno                                     ; // varchar(200)
    private String    estadoCivilAluno                                 ; // varchar(45)
    private String    tipoSangueAluno                                  ; // varchar(200)
    private String    cepAluno                                         ; // text
    private String    enderecoAluno                                    ; // varchar(200)
    private String    cidadeAluno                                      ; // varchar(200)
    private String    bairroAluno                                      ; // varchar(200)
    private String    ufEnderecoAluno                                  ; // varchar(2)
    private String    pontoReferenciaAluno                             ; // varchar(200)
    private String    foneResidencialAluno                             ; // varchar
    private String    foneCelularAluno                                 ; // varchar
    private String    emailAluno                                       ; // varchar(100)
    private String    stAutorizaEmailAluno                             ; // text
    private String    foneRecadoAluno                                  ; // varchar
    private String    matriculaAluno                                   ; // varchar(15)
    private String    unidadeAluno                                     ; // varchar(50)
    private String    programaAluno                                    ; // varchar(200)
    private String    projetoAluno                                     ; // varchar(200)
    private LocalDate dataEntradaAluno                                 ; // timestamp
    private LocalDate dataDesligamentoAluno                            ; // timestamp
    private String    moraPaisAluno                                    ; // text
    private String    paisCasadosAluno                                 ; // text
    private String    publicoPrioritarioAluno                          ; // varchar(500)
    private String    buscaEscolaAluno                                 ; // varchar(200)
    private String    desligamentoAluno                                ; // varchar(200)
    private String    observacoesAluno                                 ; // varchar(2500)
    
    private String    descricaoAluno                                   ; // varchar(200)
    private String    matriculadoEscolaPublicaAluno                    ; // text
    private String    nivelEscolaridadeAluno                           ; // varchar(200)
    private String    grauInscrucaoAluno                               ; // varchar(200)
    private String    tipoEscolaAluno                                  ; // text
    private String    turnoAluno                                       ; // text
    private String    escolaAluno                                      ; // varchar(200)
    private String    serieEscolaAluno                                 ; // varchar(200)
    private String    regiaoAdministrativaEscolaAluno                  ; // varchar(200)
    
    private String    cpfAluno                                         ; // varchar
    private String    nisAluno                                         ; // varchar(20)
    private String    pispasepAluno                                    ; // varchar(30)
    private String    identidadeAluno                                  ; // varchar(30)
    private String    orgaoExpedidoCiAluno                             ; // varchar(45)
    private String    ufCiAluno                                        ; // varchar(2)
    private LocalDate dataEmissaoCiAluno                               ; // timestamp
    private String    tituloEleitorAluno                               ; // varchar(15)
    private String    zonaTituloAluno                                  ; // varchar(15)
    private String    sessaoTituloAluno                                ; // varchar(15)
    private String    certificadoReservistaAluno                       ; // varchar(12)
    private String    regiaoMilitarReservistaAluno                     ; // varchar(12)
    private String    ufReservistaAluno                                ; // varchar(2)
    private String    cnhAluno                                         ; // varchar(20)
    private String    categoriaCnhAluno                                ; // varchar(2)
    private LocalDate dataVencimentoCnhAluno                           ; // timestamp
    private String    carteiraTrabalhoAluno                            ; // varchar(20)
    private String    serieCarteiraTrabalhoAluno                       ; // varchar(15)
    private String    ufCarteiraTrabalhoAluno                          ; // varchar(2)
    
    private String    possuiDeficienteAluno                            ; // text
    private String    descricaodeficienciaAluno                        ; // varchar(200)
    private String    descricaoProblemaSaudeAluno                      ; // varchar(200)
    private String    descricaoMedicamentosControladosAluno            ; // varchar(200)
    private String    condicaoMoradiaAluno                             ; // varchar(200)
    private String    descricaoCondicaoMoradiaAluno                    ; // varchar(200)
    private String    atendidoOrgaoRedeAluno                           ; // text
    private String    descricaoAtendidoOrgaoRedeAluno                  ; // varchar(200)
    private String    beneficiosAluno                                  ; // varchar
    private String    descricaoRedeApSocRelevAluno                     ; // varchar(200)
    private String    descricaoRedeApoioSocialAluno                    ; // varchar(200)
    private Double    valorBeneficiosAluno                             ; // numeric
    private String    origemRendaAluno                                 ; // varchar
    private String    entidadeSocial                                   ; // varchar(100)
    private String    dataEncaminhamentoAluno                          ; // varchar(45)
    private String    descricaoEncaminhamentoAluno                     ; // varchar(200)
    
    private String    nomeFamiliar                                     ; // varchar(255)
    private String    dataNascimentoFamiliar                           ; // text
    private String    naturalidadeFamiliar                             ; // varchar(200)
    private String    ufNascimentoFamiliar                             ; // varchar(2)
    private String    sexoFamiliar                                     ; // varchar(1)
    private String    racaFamiliar                                     ; // varchar(200)
    private String    nomeMaeFamiliar                                  ; // varchar(200)
    private String    nomaPaiFamiliar                                  ; // varchar(200)
    private String    estadoCivilFamiliar                              ; // varchar(45)
    private String    tipoSangueFamiliar                               ; // varchar(200)
    private String    cepFamiliar                                      ; // text
    private String    enderecoFamiliar                                 ; // varchar(200)
    private String    cidadeFamiliar                                   ; // varchar(200)
    private String    bairroFamiliar                                   ; // varchar(200)
    private String    ufEnderecoFamiliar                               ; // varchar(2)
    private String    pontoReferenciaFamiliar                          ; // varchar(200)
    private String    foneResidencialFamiliar                          ; // varchar
    private String    foneCelularFamiliar                              ; // varchar
    private String    emailFamiliar                                    ; // varchar(100)
    private String    autorizaEmailFamiliar                            ; // text
    
    private LocalDate dataCadastroFamiliar                             ; // timestamp
    private LocalDate datDesligamentoFamiliar                          ; // timestamp
    private String    situacaoParentescoFamiliar                       ; // varchar
    private String    descricaoGrauParentescoFamiliar                  ; // varchar(45)
    private String    descricaoDesligamentoFamiliar                    ; // varchar(200)
    private String    descricaoOutrasInformacoesFamiliar               ; // varchar(200)
    
    
    private LocalDate dataVinculacaoFamiliar                           ; // timestamp
    private LocalDate dataDesvinculacaoFamiliar                        ; // timestamp
    private String    mesmoEnderecoRespFamiliar                        ; // varchar(1)
    private String    transportaAlunoFamiliar                          ; // text
    private String    tutelaAlunoFamiliar                              ; // text
    private String    respFinanceiroAlunoFamiliar                      ; // text
    private String    descricaograuParentescoResponsavelFamiliar       ; // text
    private String    descricaoDesligamentoResponsavelFamiliar         ; // text
    
    
    private String    descricaoFamiliar                                ; // varchar(200)
    private String    nivelEscolaridadeFamiliar                        ; // varchar(200)
    private String    grauInscrucaoFamiliar                            ; // varchar(200)
    
    private String    cpfFamiliar                                      ; // varchar
    private String    nisFamiliar                                      ; // varchar(20)
    private String    pispasepFamiliar                                 ; // varchar(30)
    private String    identidadeFamiliar                               ; // varchar(30)
    private String    orgaoExpedidorCiFamiliar                         ; // varchar(45)
    private String    ufCiFamiliar                                     ; // varchar(2)
    private LocalDate dataEmissaoCiFamiliar                            ; // timestamp
    private String    tituloEleitorFamiliar                            ; // varchar(15)
    private String    zonaTituloFamiliar                               ; // varchar(15)
    private String    sessaoTituloFamiliar                             ; // varchar(15)
    private String    certificadoReservistaFamiliar                    ; // varchar(12)
    private String    regiaoMilitarReservistaFamiliar                  ; // varchar(12)
    private String    ufReservistaFamiliar                             ; // varchar(2)
    private String    cnhFamiliar                                      ; // varchar(20)
    private String    categoriaCnhFamiliar                             ; // varchar(2)
    private LocalDate dataVencimentoCnhFamiliar                        ; // timestamp
    private String    carteiraTrabalhoFamiliar                         ; // varchar(20)
    private String    serieCarteiraTrabalhoFamiliar                    ; // varchar(15)
    private String    ufCarteiraTrabalhoFamiliar                       ; // varchar(2)
    
    
    private String    descricaoProfissaoFamiliar                       ; // varchar(100)
    private String    nomeEmpresaTrabalhoFamiliar                      ; // varchar(200)
    private String    beneficioBolsaFamiliaFamiliar                    ; // text
    private Double    valorBolsaFamiliaFamiliar                        ; // numeric(10,2)
    private Double    valorRendaFamiliar                               ; // numeric
    private String    foneComercialFamiliar                            ; // varchar
    private String    descricaoSituacaoTrabalhoFamiliar                ; // varchar(200)
    private String    descricaoMotivoNaoTrabFamiliar                   ; // varchar(200)
    
    
    private String    tipoEscolaFamiliar                               ; // text
    private String    turnoFamiliar                                    ; // text
    private String    escolaFamiliar                                   ; // varchar(200)
    private String    cursoEscolaFamiliar                              ; // varchar(200)
    private String    periodoEscolaFamiliar                            ; // varchar(200)
    private String    serieEscolaFamiliar                              ; // varchar(200)
    private String    regiaoAdministrativaEscolaFamiliar               ; // varchar(200)
    private String    possuiDeficienteFamiliar                         ; // text
    private String    descricaoDeficienciaFamiliar                     ; // varchar(200)
    private String    descricaoProblemaSaudeFamiliar                   ; // varchar(200)
    private String    descricaoMedicamentosControladosFamiliar         ; // varchar(200)    
    private String    condicaoMoradiaFamiliar                          ; // varchar(200)
    private String    descricaoCondicaoMoradiaFamiliar                 ; // varchar(200)
    private String    descricaoAtendidoOrgaoRedeFamiliar               ; // varchar(200)
    private String    beneficiosFamiliar                               ; // varchar    
    private String    descricaoRedeApSocRelevFamiliar                  ; // varchar(200)
    private String    descricaoRedeApoioSocialFamiliar                 ; // varchar(200)
    private Double    valorBeneficiosFamiliar                          ; // numeric
    private String    origemRendaFamiliar                              ; // varchar
    
    private Long      idPessoaFisica                                   ; // numeric(10)
    private Long      idAluno                                          ; // numeric(10)
    private String    aprExternaAluno                                  ; // text

	
	
	public ExportarDadosBeneficiarioDTO() {
	}
	
	public ExportarDadosBeneficiarioDTO(Object[] colunas) {
		this.nomeAluno                                           = (String)  colunas [0 ];
		this.dataNascimentoAluno                                 = (String)  colunas [1 ];
		this.naturalidadeAluno                                   = (String)  colunas [2 ];
		this.ufNascimentoAluno                                   = (String)  colunas [3 ];
		this.sexoAluno                                           = (String)  colunas [4 ];
		this.racaAluno                                           = (String)  colunas [5 ];
		this.nomeMaeAluno                                        = (String)  colunas [6 ];
		this.nomePaiAluno                                        = (String)  colunas [7 ];
		this.estadoCivilAluno                                    = (String)  colunas [8 ];
		this.tipoSangueAluno                                     = (String)  colunas [9 ];
		this.cepAluno                                            = (String)  colunas [10 ];
		this.enderecoAluno                                       = (String)  colunas [11 ];
		this.cidadeAluno                                         = (String)  colunas [12 ];
		this.bairroAluno                                         = (String)  colunas [13 ];
		this.ufEnderecoAluno                                     = (String)  colunas [14 ];
		this.pontoReferenciaAluno                                = (String)  colunas [15 ];
		this.foneResidencialAluno                                = (String)  colunas [16 ];
		this.foneCelularAluno                                    = (String)  colunas [17 ];
		this.emailAluno                                          = (String)  colunas [18 ];
		this.stAutorizaEmailAluno                                = (String)  colunas [19 ];
		this.foneRecadoAluno                                     = (String)  colunas [20 ];
		this.matriculaAluno                                      = (String)  colunas [21 ];
		this.unidadeAluno                                        = (String)  colunas [22 ];
		this.programaAluno                                       = (String)  colunas [23 ];
		this.projetoAluno                                        = (String)  colunas [24 ];
		this.dataEntradaAluno                                    = (colunas[25] != null)? ((Timestamp)colunas[25]).toLocalDateTime().toLocalDate() : null;
		this.dataDesligamentoAluno                               = (colunas[26] != null)? ((Timestamp)colunas[26]).toLocalDateTime().toLocalDate() : null;
		this.moraPaisAluno                                       = (String)  colunas [27 ];
		this.paisCasadosAluno                                    = (String)  colunas [28 ];
		this.publicoPrioritarioAluno                             = (String)  colunas [29 ];
		this.buscaEscolaAluno                                    = (String)  colunas [30 ];
		this.desligamentoAluno                                   = (String)  colunas [31 ];
		this.observacoesAluno                                    = (String)  colunas [32 ];
		this.descricaoAluno                                      = (String)  colunas [33 ];
		this.matriculadoEscolaPublicaAluno                       = (String)  colunas [34 ];
		this.nivelEscolaridadeAluno                              = (String)  colunas [35 ];
		this.grauInscrucaoAluno                                  = (String)  colunas [36 ];
		this.tipoEscolaAluno                                     = (String)  colunas [37 ];
		this.turnoAluno                                          = (String)  colunas [38 ];
		this.escolaAluno                                         = (String)  colunas [39 ];
		this.serieEscolaAluno                                    = (String)  colunas [40 ];
		this.regiaoAdministrativaEscolaAluno                     = (String)  colunas [41 ];
		this.cpfAluno                                            = (String)  colunas [42 ];
		this.nisAluno                                            = (String)  colunas [43 ];
		this.pispasepAluno                                       = (String)  colunas [44 ];
		this.identidadeAluno                                     = (String)  colunas [45 ];
		this.orgaoExpedidoCiAluno                                = (String)  colunas [46 ];
		this.ufCiAluno                                           = (String)  colunas [47 ];
		this.dataEmissaoCiAluno                                  = (colunas[48] != null)? ((Timestamp)colunas[48]).toLocalDateTime().toLocalDate() : null;
		this.tituloEleitorAluno                                  = (String)  colunas [49 ];
		this.zonaTituloAluno                                     = (String)  colunas [50 ];
		this.sessaoTituloAluno                                   = (String)  colunas [51 ];
		this.certificadoReservistaAluno                          = (String)  colunas [52 ];
		this.regiaoMilitarReservistaAluno                        = (String)  colunas [53 ];
		this.ufReservistaAluno                                   = (String)  colunas [54 ];
		this.cnhAluno                                            = (String)  colunas [55 ];
		this.categoriaCnhAluno                                   = (String)  colunas [56 ];
		this.dataVencimentoCnhAluno                              = (colunas[57] != null)? ((Timestamp)colunas[57]).toLocalDateTime().toLocalDate() : null;
		this.carteiraTrabalhoAluno                               = (String)  colunas [58 ];
		this.serieCarteiraTrabalhoAluno                          = (String)  colunas [59 ];
		this.ufCarteiraTrabalhoAluno                             = (String)  colunas [60 ];
		this.possuiDeficienteAluno                               = (String)  colunas [61 ];
		this.descricaodeficienciaAluno                           = (String)  colunas [62 ];
		this.descricaoProblemaSaudeAluno                         = (String)  colunas [63 ];
		this.descricaoMedicamentosControladosAluno               = (String)  colunas [64 ];
		this.condicaoMoradiaAluno                                = (String)  colunas [65 ];
		this.descricaoCondicaoMoradiaAluno                       = (String)  colunas [66 ];
		this.atendidoOrgaoRedeAluno                              = (String)  colunas [67 ];
		this.descricaoAtendidoOrgaoRedeAluno                     = (String)  colunas [68 ];
		this.beneficiosAluno                                     = (String)  colunas [69 ];
		this.descricaoRedeApSocRelevAluno                        = (String)  colunas [70 ];
		this.descricaoRedeApoioSocialAluno                       = (String)  colunas [71 ];
		this.valorBeneficiosAluno                                = (colunas[72] != null)? ((BigDecimal)colunas[72]).doubleValue() : null;
		this.origemRendaAluno                                    = (String)  colunas [73 ];
		this.entidadeSocial                                      = (String)  colunas [74 ];
		this.dataEncaminhamentoAluno                             = (String)  colunas [75 ];
		this.descricaoEncaminhamentoAluno                        = (String)  colunas [76 ];
		this.nomeFamiliar                                        = (String)  colunas [77 ];
		this.dataNascimentoFamiliar                              = (String)  colunas [78 ];
		this.naturalidadeFamiliar                                = (String)  colunas [79 ];
		this.ufNascimentoFamiliar                                = (String)  colunas [80 ];
		this.sexoFamiliar                                        = (String)  colunas [81 ];
		this.racaFamiliar                                        = (String)  colunas [82 ];
		this.nomeMaeFamiliar                                     = (String)  colunas [83 ];
		this.nomaPaiFamiliar                                     = (String)  colunas [84 ];
		this.estadoCivilFamiliar                                 = (String)  colunas [85 ];
		this.tipoSangueFamiliar                                  = (String)  colunas [86 ];
		this.cepFamiliar                                         = (String)  colunas [87 ];
		this.enderecoFamiliar                                    = (String)  colunas [88 ];
		this.cidadeFamiliar                                      = (String)  colunas [89 ];
		this.bairroFamiliar                                      = (String)  colunas [90 ];
		this.ufEnderecoFamiliar                                  = (String)  colunas [91 ];
		this.pontoReferenciaFamiliar                             = (String)  colunas [92 ];
		this.foneResidencialFamiliar                             = (String)  colunas [93 ];
		this.foneCelularFamiliar                                 = (String)  colunas [94 ];
		this.emailFamiliar                                       = (String)  colunas [95 ];
		this.autorizaEmailFamiliar                               = (String)  colunas [96 ];
		this.dataCadastroFamiliar                                = (colunas[97] != null)? ((Timestamp)colunas[97]).toLocalDateTime().toLocalDate() : null;
		this.datDesligamentoFamiliar                             = (colunas[98] != null)? ((Timestamp)colunas[98]).toLocalDateTime().toLocalDate() : null;
		this.situacaoParentescoFamiliar                          = (String)  colunas [99 ];
		this.descricaoGrauParentescoFamiliar                     = (String)  colunas [100 ];
		this.descricaoDesligamentoFamiliar                       = (String)  colunas [101 ];
		this.descricaoOutrasInformacoesFamiliar                  = (String)  colunas [102 ];
		this.dataVinculacaoFamiliar                              = (colunas[103] != null)? ((Timestamp)colunas[103]).toLocalDateTime().toLocalDate() : null;
		this.dataDesvinculacaoFamiliar                           = (colunas[104] != null)? ((Timestamp)colunas[104]).toLocalDateTime().toLocalDate() : null;
		this.mesmoEnderecoRespFamiliar                           = (String)  colunas [105 ];
		this.transportaAlunoFamiliar                             = (String)  colunas [106 ];
		this.tutelaAlunoFamiliar                                 = (String)  colunas [107 ];
		this.respFinanceiroAlunoFamiliar                         = (String)  colunas [108 ];
		this.descricaograuParentescoResponsavelFamiliar          = (String)  colunas [109 ];
		this.descricaoDesligamentoResponsavelFamiliar            = (String)  colunas [110 ];
		this.descricaoFamiliar                                   = (String)  colunas [111 ];
		this.nivelEscolaridadeFamiliar                           = (String)  colunas [112 ];
		this.grauInscrucaoFamiliar                               = (String)  colunas [113 ];
		this.cpfFamiliar                                         = (String)  colunas [114 ];
		this.nisFamiliar                                         = (String)  colunas [115 ];
		this.pispasepFamiliar                                    = (String)  colunas [116 ];
		this.identidadeFamiliar                                  = (String)  colunas [117 ];
		this.orgaoExpedidorCiFamiliar                            = (String)  colunas [118 ];
		this.ufCiFamiliar                                        = (String)  colunas [119 ];
		this.dataEmissaoCiFamiliar                               = (colunas[120] != null)? ((Timestamp)colunas[120]).toLocalDateTime().toLocalDate() : null;
		this.tituloEleitorFamiliar                               = (String)  colunas [121 ];
		this.zonaTituloFamiliar                                  = (String)  colunas [122 ];
		this.sessaoTituloFamiliar                                = (String)  colunas [123 ];
		this.certificadoReservistaFamiliar                       = (String)  colunas [124 ];
		this.regiaoMilitarReservistaFamiliar                     = (String)  colunas [125 ];
		this.ufReservistaFamiliar                                = (String)  colunas [126 ];
		this.cnhFamiliar                                         = (String)  colunas [127 ];
		this.categoriaCnhFamiliar                                = (String)  colunas [128 ];
		this.dataVencimentoCnhFamiliar                           = (colunas[129] != null)? ((Timestamp)colunas[129]).toLocalDateTime().toLocalDate() : null;
		this.carteiraTrabalhoFamiliar                            = (String)  colunas [130 ];
		this.serieCarteiraTrabalhoFamiliar                       = (String)  colunas [131 ];
		this.ufCarteiraTrabalhoFamiliar                          = (String)  colunas [132 ];
		this.descricaoProfissaoFamiliar                          = (String)  colunas [133 ];
		this.nomeEmpresaTrabalhoFamiliar                         = (String)  colunas [134 ];
		this.beneficioBolsaFamiliaFamiliar                       = (String)  colunas [135 ];
		this.valorBolsaFamiliaFamiliar                           = (colunas[136] != null)? ((BigDecimal)colunas[136]).doubleValue() : null;
		this.valorRendaFamiliar                                  = (colunas[137] != null)? ((BigDecimal)colunas[137]).doubleValue() : null;
		this.foneComercialFamiliar                               = (String)  colunas [138 ];
		this.descricaoSituacaoTrabalhoFamiliar                   = (String)  colunas [139 ];
		this.descricaoMotivoNaoTrabFamiliar                      = (String)  colunas [140 ];
		this.tipoEscolaFamiliar                                  = (String)  colunas [141 ];
		this.turnoFamiliar                                       = (String)  colunas [142 ];
		this.escolaFamiliar                                      = (String)  colunas [143 ];
		this.cursoEscolaFamiliar                                 = (String)  colunas [144 ];
		this.periodoEscolaFamiliar                               = (String)  colunas [145 ];
		this.serieEscolaFamiliar                                 = (String)  colunas [146 ];
		this.regiaoAdministrativaEscolaFamiliar                  = (String)  colunas [147 ];
		this.possuiDeficienteFamiliar                            = (String)  colunas [148 ];
		this.descricaoDeficienciaFamiliar                        = (String)  colunas [149 ];
		this.descricaoProblemaSaudeFamiliar                      = (String)  colunas [150 ];
		this.descricaoMedicamentosControladosFamiliar            = (String)  colunas [151 ];
		this.condicaoMoradiaFamiliar                             = (String)  colunas [152 ];
		this.descricaoCondicaoMoradiaFamiliar                    = (String)  colunas [153 ];
		this.descricaoAtendidoOrgaoRedeFamiliar                  = (String)  colunas [154 ];
		this.beneficiosFamiliar                                  = (String)  colunas [155 ];
		this.descricaoRedeApSocRelevFamiliar                     = (String)  colunas [156 ];
		this.descricaoRedeApoioSocialFamiliar                    = (String)  colunas [157 ];
		this.valorBeneficiosFamiliar                             = (colunas[158] != null)? ((BigDecimal)colunas[158]).doubleValue() : null;
		this.origemRendaFamiliar                                 = (String)  colunas [159 ];
		this.idPessoaFisica                                      = (colunas[160] != null)? ((BigDecimal)colunas[160]).longValue() : null;
		this.idAluno                                             = (colunas[161] != null)? ((BigDecimal)colunas[161]).longValue() : null;
		this.aprExternaAluno                                     = (String)  colunas [162 ];
		                                                                      
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

	public String getNaturalidadeAluno() {
		return naturalidadeAluno;
	}

	public void setNaturalidadeAluno(String naturalidadeAluno) {
		this.naturalidadeAluno = naturalidadeAluno;
	}

	public String getUfNascimentoAluno() {
		return ufNascimentoAluno;
	}

	public void setUfNascimentoAluno(String ufNascimentoAluno) {
		this.ufNascimentoAluno = ufNascimentoAluno;
	}

	public String getSexoAluno() {
		return sexoAluno;
	}

	public void setSexoAluno(String sexoAluno) {
		this.sexoAluno = sexoAluno;
	}

	public String getRacaAluno() {
		return racaAluno;
	}

	public void setRacaAluno(String racaAluno) {
		this.racaAluno = racaAluno;
	}

	public String getNomeMaeAluno() {
		return nomeMaeAluno;
	}

	public void setNomeMaeAluno(String nomeMaeAluno) {
		this.nomeMaeAluno = nomeMaeAluno;
	}

	public String getNomePaiAluno() {
		return nomePaiAluno;
	}

	public void setNomePaiAluno(String nomePaiAluno) {
		this.nomePaiAluno = nomePaiAluno;
	}

	public String getEstadoCivilAluno() {
		return estadoCivilAluno;
	}

	public void setEstadoCivilAluno(String estadoCivilAluno) {
		this.estadoCivilAluno = estadoCivilAluno;
	}

	public String getTipoSangueAluno() {
		return tipoSangueAluno;
	}

	public void setTipoSangueAluno(String tipoSangueAluno) {
		this.tipoSangueAluno = tipoSangueAluno;
	}

	public String getCepAluno() {
		return cepAluno;
	}

	public void setCepAluno(String cepAluno) {
		this.cepAluno = cepAluno;
	}

	public String getEnderecoAluno() {
		return enderecoAluno;
	}

	public void setEnderecoAluno(String enderecoAluno) {
		this.enderecoAluno = enderecoAluno;
	}

	public String getCidadeAluno() {
		return cidadeAluno;
	}

	public void setCidadeAluno(String cidadeAluno) {
		this.cidadeAluno = cidadeAluno;
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

	public String getPontoReferenciaAluno() {
		return pontoReferenciaAluno;
	}

	public void setPontoReferenciaAluno(String pontoReferenciaAluno) {
		this.pontoReferenciaAluno = pontoReferenciaAluno;
	}

	public String getFoneResidencialAluno() {
		return foneResidencialAluno;
	}

	public void setFoneResidencialAluno(String foneResidencialAluno) {
		this.foneResidencialAluno = foneResidencialAluno;
	}

	public String getFoneCelularAluno() {
		return foneCelularAluno;
	}

	public void setFoneCelularAluno(String foneCelularAluno) {
		this.foneCelularAluno = foneCelularAluno;
	}

	public String getEmailAluno() {
		return emailAluno;
	}

	public void setEmailAluno(String emailAluno) {
		this.emailAluno = emailAluno;
	}

	public String getStAutorizaEmailAluno() {
		return stAutorizaEmailAluno;
	}

	public void setStAutorizaEmailAluno(String stAutorizaEmailAluno) {
		this.stAutorizaEmailAluno = stAutorizaEmailAluno;
	}

	public String getFoneRecadoAluno() {
		return foneRecadoAluno;
	}

	public void setFoneRecadoAluno(String foneRecadoAluno) {
		this.foneRecadoAluno = foneRecadoAluno;
	}

	public String getMatriculaAluno() {
		return matriculaAluno;
	}

	public void setMatriculaAluno(String matriculaAluno) {
		this.matriculaAluno = matriculaAluno;
	}

	public String getUnidadeAluno() {
		return unidadeAluno;
	}

	public void setUnidadeAluno(String unidadeAluno) {
		this.unidadeAluno = unidadeAluno;
	}

	public String getProgramaAluno() {
		return programaAluno;
	}

	public void setProgramaAluno(String programaAluno) {
		this.programaAluno = programaAluno;
	}

	public String getProjetoAluno() {
		return projetoAluno;
	}

	public void setProjetoAluno(String projetoAluno) {
		this.projetoAluno = projetoAluno;
	}

	public LocalDate getDataEntradaAluno() {
		return dataEntradaAluno;
	}

	public void setDataEntradaAluno(LocalDate dataEntradaAluno) {
		this.dataEntradaAluno = dataEntradaAluno;
	}

	public LocalDate getDataDesligamentoAluno() {
		return dataDesligamentoAluno;
	}

	public void setDataDesligamentoAluno(LocalDate dataDesligamentoAluno) {
		this.dataDesligamentoAluno = dataDesligamentoAluno;
	}

	public String getMoraPaisAluno() {
		return moraPaisAluno;
	}

	public void setMoraPaisAluno(String moraPaisAluno) {
		this.moraPaisAluno = moraPaisAluno;
	}

	public String getPaisCasadosAluno() {
		return paisCasadosAluno;
	}

	public void setPaisCasadosAluno(String paisCasadosAluno) {
		this.paisCasadosAluno = paisCasadosAluno;
	}

	public String getPublicoPrioritarioAluno() {
		return publicoPrioritarioAluno;
	}

	public void setPublicoPrioritarioAluno(String publicoPrioritarioAluno) {
		this.publicoPrioritarioAluno = publicoPrioritarioAluno;
	}

	public String getBuscaEscolaAluno() {
		return buscaEscolaAluno;
	}

	public void setBuscaEscolaAluno(String buscaEscolaAluno) {
		this.buscaEscolaAluno = buscaEscolaAluno;
	}

	public String getDesligamentoAluno() {
		return desligamentoAluno;
	}

	public void setDesligamentoAluno(String desligamentoAluno) {
		this.desligamentoAluno = desligamentoAluno;
	}

	public String getObservacoesAluno() {
		return observacoesAluno;
	}

	public void setObservacoesAluno(String observacoesAluno) {
		this.observacoesAluno = observacoesAluno;
	}

	public String getDescricaoAluno() {
		return descricaoAluno;
	}

	public void setDescricaoAluno(String descricaoAluno) {
		this.descricaoAluno = descricaoAluno;
	}

	public String getMatriculadoEscolaPublicaAluno() {
		return matriculadoEscolaPublicaAluno;
	}

	public void setMatriculadoEscolaPublicaAluno(String matriculadoEscolaPublicaAluno) {
		this.matriculadoEscolaPublicaAluno = matriculadoEscolaPublicaAluno;
	}

	public String getNivelEscolaridadeAluno() {
		return nivelEscolaridadeAluno;
	}

	public void setNivelEscolaridadeAluno(String nivelEscolaridadeAluno) {
		this.nivelEscolaridadeAluno = nivelEscolaridadeAluno;
	}

	public String getGrauInscrucaoAluno() {
		return grauInscrucaoAluno;
	}

	public void setGrauInscrucaoAluno(String grauInscrucaoAluno) {
		this.grauInscrucaoAluno = grauInscrucaoAluno;
	}

	public String getTipoEscolaAluno() {
		return tipoEscolaAluno;
	}

	public void setTipoEscolaAluno(String tipoEscolaAluno) {
		this.tipoEscolaAluno = tipoEscolaAluno;
	}

	public String getTurnoAluno() {
		return turnoAluno;
	}

	public void setTurnoAluno(String turnoAluno) {
		this.turnoAluno = turnoAluno;
	}

	public String getEscolaAluno() {
		return escolaAluno;
	}

	public void setEscolaAluno(String escolaAluno) {
		this.escolaAluno = escolaAluno;
	}

	public String getSerieEscolaAluno() {
		return serieEscolaAluno;
	}

	public void setSerieEscolaAluno(String serieEscolaAluno) {
		this.serieEscolaAluno = serieEscolaAluno;
	}

	public String getRegiaoAdministrativaEscolaAluno() {
		return regiaoAdministrativaEscolaAluno;
	}

	public void setRegiaoAdministrativaEscolaAluno(String regiaoAdministrativaEscolaAluno) {
		this.regiaoAdministrativaEscolaAluno = regiaoAdministrativaEscolaAluno;
	}

	public String getCpfAluno() {
		return cpfAluno;
	}

	public void setCpfAluno(String cpfAluno) {
		this.cpfAluno = cpfAluno;
	}

	public String getNisAluno() {
		return nisAluno;
	}

	public void setNisAluno(String nisAluno) {
		this.nisAluno = nisAluno;
	}

	public String getPispasepAluno() {
		return pispasepAluno;
	}

	public void setPispasepAluno(String pispasepAluno) {
		this.pispasepAluno = pispasepAluno;
	}

	public String getIdentidadeAluno() {
		return identidadeAluno;
	}

	public void setIdentidadeAluno(String identidadeAluno) {
		this.identidadeAluno = identidadeAluno;
	}

	public String getOrgaoExpedidoCiAluno() {
		return orgaoExpedidoCiAluno;
	}

	public void setOrgaoExpedidoCiAluno(String orgaoExpedidoCiAluno) {
		this.orgaoExpedidoCiAluno = orgaoExpedidoCiAluno;
	}

	public String getUfCiAluno() {
		return ufCiAluno;
	}

	public void setUfCiAluno(String ufCiAluno) {
		this.ufCiAluno = ufCiAluno;
	}

	public LocalDate getDataEmissaoCiAluno() {
		return dataEmissaoCiAluno;
	}

	public void setDataEmissaoCiAluno(LocalDate dataEmissaoCiAluno) {
		this.dataEmissaoCiAluno = dataEmissaoCiAluno;
	}

	public String getTituloEleitorAluno() {
		return tituloEleitorAluno;
	}

	public void setTituloEleitorAluno(String tituloEleitorAluno) {
		this.tituloEleitorAluno = tituloEleitorAluno;
	}

	public String getZonaTituloAluno() {
		return zonaTituloAluno;
	}

	public void setZonaTituloAluno(String zonaTituloAluno) {
		this.zonaTituloAluno = zonaTituloAluno;
	}

	public String getSessaoTituloAluno() {
		return sessaoTituloAluno;
	}

	public void setSessaoTituloAluno(String sessaoTituloAluno) {
		this.sessaoTituloAluno = sessaoTituloAluno;
	}

	public String getCertificadoReservistaAluno() {
		return certificadoReservistaAluno;
	}

	public void setCertificadoReservistaAluno(String certificadoReservistaAluno) {
		this.certificadoReservistaAluno = certificadoReservistaAluno;
	}

	public String getRegiaoMilitarReservistaAluno() {
		return regiaoMilitarReservistaAluno;
	}

	public void setRegiaoMilitarReservistaAluno(String regiaoMilitarReservistaAluno) {
		this.regiaoMilitarReservistaAluno = regiaoMilitarReservistaAluno;
	}

	public String getUfReservistaAluno() {
		return ufReservistaAluno;
	}

	public void setUfReservistaAluno(String ufReservistaAluno) {
		this.ufReservistaAluno = ufReservistaAluno;
	}

	public String getCnhAluno() {
		return cnhAluno;
	}

	public void setCnhAluno(String cnhAluno) {
		this.cnhAluno = cnhAluno;
	}

	public String getCategoriaCnhAluno() {
		return categoriaCnhAluno;
	}

	public void setCategoriaCnhAluno(String categoriaCnhAluno) {
		this.categoriaCnhAluno = categoriaCnhAluno;
	}

	public LocalDate getDataVencimentoCnhAluno() {
		return dataVencimentoCnhAluno;
	}

	public void setDataVencimentoCnhAluno(LocalDate dataVencimentoCnhAluno) {
		this.dataVencimentoCnhAluno = dataVencimentoCnhAluno;
	}

	public String getCarteiraTrabalhoAluno() {
		return carteiraTrabalhoAluno;
	}

	public void setCarteiraTrabalhoAluno(String carteiraTrabalhoAluno) {
		this.carteiraTrabalhoAluno = carteiraTrabalhoAluno;
	}

	public String getSerieCarteiraTrabalhoAluno() {
		return serieCarteiraTrabalhoAluno;
	}

	public void setSerieCarteiraTrabalhoAluno(String serieCarteiraTrabalhoAluno) {
		this.serieCarteiraTrabalhoAluno = serieCarteiraTrabalhoAluno;
	}

	public String getUfCarteiraTrabalhoAluno() {
		return ufCarteiraTrabalhoAluno;
	}

	public void setUfCarteiraTrabalhoAluno(String ufCarteiraTrabalhoAluno) {
		this.ufCarteiraTrabalhoAluno = ufCarteiraTrabalhoAluno;
	}

	public String getPossuiDeficienteAluno() {
		return possuiDeficienteAluno;
	}

	public void setPossuiDeficienteAluno(String possuiDeficienteAluno) {
		this.possuiDeficienteAluno = possuiDeficienteAluno;
	}

	public String getDescricaodeficienciaAluno() {
		return descricaodeficienciaAluno;
	}

	public void setDescricaodeficienciaAluno(String descricaodeficienciaAluno) {
		this.descricaodeficienciaAluno = descricaodeficienciaAluno;
	}

	public String getDescricaoProblemaSaudeAluno() {
		return descricaoProblemaSaudeAluno;
	}

	public void setDescricaoProblemaSaudeAluno(String descricaoProblemaSaudeAluno) {
		this.descricaoProblemaSaudeAluno = descricaoProblemaSaudeAluno;
	}

	public String getDescricaoMedicamentosControladosAluno() {
		return descricaoMedicamentosControladosAluno;
	}

	public void setDescricaoMedicamentosControladosAluno(String descricaoMedicamentosControladosAluno) {
		this.descricaoMedicamentosControladosAluno = descricaoMedicamentosControladosAluno;
	}

	public String getCondicaoMoradiaAluno() {
		return condicaoMoradiaAluno;
	}

	public void setCondicaoMoradiaAluno(String condicaoMoradiaAluno) {
		this.condicaoMoradiaAluno = condicaoMoradiaAluno;
	}

	public String getDescricaoCondicaoMoradiaAluno() {
		return descricaoCondicaoMoradiaAluno;
	}

	public void setDescricaoCondicaoMoradiaAluno(String descricaoCondicaoMoradiaAluno) {
		this.descricaoCondicaoMoradiaAluno = descricaoCondicaoMoradiaAluno;
	}

	public String getAtendidoOrgaoRedeAluno() {
		return atendidoOrgaoRedeAluno;
	}

	public void setAtendidoOrgaoRedeAluno(String atendidoOrgaoRedeAluno) {
		this.atendidoOrgaoRedeAluno = atendidoOrgaoRedeAluno;
	}

	public String getDescricaoAtendidoOrgaoRedeAluno() {
		return descricaoAtendidoOrgaoRedeAluno;
	}

	public void setDescricaoAtendidoOrgaoRedeAluno(String descricaoAtendidoOrgaoRedeAluno) {
		this.descricaoAtendidoOrgaoRedeAluno = descricaoAtendidoOrgaoRedeAluno;
	}

	public String getBeneficiosAluno() {
		return beneficiosAluno;
	}

	public void setBeneficiosAluno(String beneficiosAluno) {
		this.beneficiosAluno = beneficiosAluno;
	}

	public String getDescricaoRedeApSocRelevAluno() {
		return descricaoRedeApSocRelevAluno;
	}

	public void setDescricaoRedeApSocRelevAluno(String descricaoRedeApSocRelevAluno) {
		this.descricaoRedeApSocRelevAluno = descricaoRedeApSocRelevAluno;
	}

	public String getDescricaoRedeApoioSocialAluno() {
		return descricaoRedeApoioSocialAluno;
	}

	public void setDescricaoRedeApoioSocialAluno(String descricaoRedeApoioSocialAluno) {
		this.descricaoRedeApoioSocialAluno = descricaoRedeApoioSocialAluno;
	}

	public Double getValorBeneficiosAluno() {
		return valorBeneficiosAluno;
	}

	public void setValorBeneficiosAluno(Double valorBeneficiosAluno) {
		this.valorBeneficiosAluno = valorBeneficiosAluno;
	}

	public String getOrigemRendaAluno() {
		return origemRendaAluno;
	}

	public void setOrigemRendaAluno(String origemRendaAluno) {
		this.origemRendaAluno = origemRendaAluno;
	}

	public String getEntidadeSocial() {
		return entidadeSocial;
	}

	public void setEntidadeSocial(String entidadeSocial) {
		this.entidadeSocial = entidadeSocial;
	}

	public String getDataEncaminhamentoAluno() {
		return dataEncaminhamentoAluno;
	}

	public void setDataEncaminhamentoAluno(String dataEncaminhamentoAluno) {
		this.dataEncaminhamentoAluno = dataEncaminhamentoAluno;
	}

	public String getDescricaoEncaminhamentoAluno() {
		return descricaoEncaminhamentoAluno;
	}

	public void setDescricaoEncaminhamentoAluno(String descricaoEncaminhamentoAluno) {
		this.descricaoEncaminhamentoAluno = descricaoEncaminhamentoAluno;
	}

	public String getNomeFamiliar() {
		return nomeFamiliar;
	}

	public void setNomeFamiliar(String nomeFamiliar) {
		this.nomeFamiliar = nomeFamiliar;
	}

	public String getDataNascimentoFamiliar() {
		return dataNascimentoFamiliar;
	}

	public void setDataNascimentoFamiliar(String dataNascimentoFamiliar) {
		this.dataNascimentoFamiliar = dataNascimentoFamiliar;
	}

	public String getNaturalidadeFamiliar() {
		return naturalidadeFamiliar;
	}

	public void setNaturalidadeFamiliar(String naturalidadeFamiliar) {
		this.naturalidadeFamiliar = naturalidadeFamiliar;
	}

	public String getUfNascimentoFamiliar() {
		return ufNascimentoFamiliar;
	}

	public void setUfNascimentoFamiliar(String ufNascimentoFamiliar) {
		this.ufNascimentoFamiliar = ufNascimentoFamiliar;
	}

	public String getSexoFamiliar() {
		return sexoFamiliar;
	}

	public void setSexoFamiliar(String sexoFamiliar) {
		this.sexoFamiliar = sexoFamiliar;
	}

	public String getRacaFamiliar() {
		return racaFamiliar;
	}

	public void setRacaFamiliar(String racaFamiliar) {
		this.racaFamiliar = racaFamiliar;
	}

	public String getNomeMaeFamiliar() {
		return nomeMaeFamiliar;
	}

	public void setNomeMaeFamiliar(String nomeMaeFamiliar) {
		this.nomeMaeFamiliar = nomeMaeFamiliar;
	}

	public String getNomaPaiFamiliar() {
		return nomaPaiFamiliar;
	}

	public void setNomaPaiFamiliar(String nomaPaiFamiliar) {
		this.nomaPaiFamiliar = nomaPaiFamiliar;
	}

	public String getEstadoCivilFamiliar() {
		return estadoCivilFamiliar;
	}

	public void setEstadoCivilFamiliar(String estadoCivilFamiliar) {
		this.estadoCivilFamiliar = estadoCivilFamiliar;
	}

	public String getTipoSangueFamiliar() {
		return tipoSangueFamiliar;
	}

	public void setTipoSangueFamiliar(String tipoSangueFamiliar) {
		this.tipoSangueFamiliar = tipoSangueFamiliar;
	}

	public String getCepFamiliar() {
		return cepFamiliar;
	}

	public void setCepFamiliar(String cepFamiliar) {
		this.cepFamiliar = cepFamiliar;
	}

	public String getEnderecoFamiliar() {
		return enderecoFamiliar;
	}

	public void setEnderecoFamiliar(String enderecoFamiliar) {
		this.enderecoFamiliar = enderecoFamiliar;
	}

	public String getCidadeFamiliar() {
		return cidadeFamiliar;
	}

	public void setCidadeFamiliar(String cidadeFamiliar) {
		this.cidadeFamiliar = cidadeFamiliar;
	}

	public String getBairroFamiliar() {
		return bairroFamiliar;
	}

	public void setBairroFamiliar(String bairroFamiliar) {
		this.bairroFamiliar = bairroFamiliar;
	}

	public String getUfEnderecoFamiliar() {
		return ufEnderecoFamiliar;
	}

	public void setUfEnderecoFamiliar(String ufEnderecoFamiliar) {
		this.ufEnderecoFamiliar = ufEnderecoFamiliar;
	}

	public String getPontoReferenciaFamiliar() {
		return pontoReferenciaFamiliar;
	}

	public void setPontoReferenciaFamiliar(String pontoReferenciaFamiliar) {
		this.pontoReferenciaFamiliar = pontoReferenciaFamiliar;
	}

	public String getFoneResidencialFamiliar() {
		return foneResidencialFamiliar;
	}

	public void setFoneResidencialFamiliar(String foneResidencialFamiliar) {
		this.foneResidencialFamiliar = foneResidencialFamiliar;
	}

	public String getFoneCelularFamiliar() {
		return foneCelularFamiliar;
	}

	public void setFoneCelularFamiliar(String foneCelularFamiliar) {
		this.foneCelularFamiliar = foneCelularFamiliar;
	}

	public String getEmailFamiliar() {
		return emailFamiliar;
	}

	public void setEmailFamiliar(String emailFamiliar) {
		this.emailFamiliar = emailFamiliar;
	}

	public String getAutorizaEmailFamiliar() {
		return autorizaEmailFamiliar;
	}

	public void setAutorizaEmailFamiliar(String autorizaEmailFamiliar) {
		this.autorizaEmailFamiliar = autorizaEmailFamiliar;
	}

	public LocalDate getDataCadastroFamiliar() {
		return dataCadastroFamiliar;
	}

	public void setDataCadastroFamiliar(LocalDate dataCadastroFamiliar) {
		this.dataCadastroFamiliar = dataCadastroFamiliar;
	}

	public LocalDate getDatDesligamentoFamiliar() {
		return datDesligamentoFamiliar;
	}

	public void setDatDesligamentoFamiliar(LocalDate datDesligamentoFamiliar) {
		this.datDesligamentoFamiliar = datDesligamentoFamiliar;
	}

	public String getSituacaoParentescoFamiliar() {
		return situacaoParentescoFamiliar;
	}

	public void setSituacaoParentescoFamiliar(String situacaoParentescoFamiliar) {
		this.situacaoParentescoFamiliar = situacaoParentescoFamiliar;
	}

	public String getDescricaoGrauParentescoFamiliar() {
		return descricaoGrauParentescoFamiliar;
	}

	public void setDescricaoGrauParentescoFamiliar(String descricaoGrauParentescoFamiliar) {
		this.descricaoGrauParentescoFamiliar = descricaoGrauParentescoFamiliar;
	}

	public String getDescricaoDesligamentoFamiliar() {
		return descricaoDesligamentoFamiliar;
	}

	public void setDescricaoDesligamentoFamiliar(String descricaoDesligamentoFamiliar) {
		this.descricaoDesligamentoFamiliar = descricaoDesligamentoFamiliar;
	}

	public String getDescricaoOutrasInformacoesFamiliar() {
		return descricaoOutrasInformacoesFamiliar;
	}

	public void setDescricaoOutrasInformacoesFamiliar(String descricaoOutrasInformacoesFamiliar) {
		this.descricaoOutrasInformacoesFamiliar = descricaoOutrasInformacoesFamiliar;
	}

	public LocalDate getDataVinculacaoFamiliar() {
		return dataVinculacaoFamiliar;
	}

	public void setDataVinculacaoFamiliar(LocalDate dataVinculacaoFamiliar) {
		this.dataVinculacaoFamiliar = dataVinculacaoFamiliar;
	}

	public LocalDate getDataDesvinculacaoFamiliar() {
		return dataDesvinculacaoFamiliar;
	}

	public void setDataDesvinculacaoFamiliar(LocalDate dataDesvinculacaoFamiliar) {
		this.dataDesvinculacaoFamiliar = dataDesvinculacaoFamiliar;
	}

	public String getMesmoEnderecoRespFamiliar() {
		return mesmoEnderecoRespFamiliar;
	}

	public void setMesmoEnderecoRespFamiliar(String mesmoEnderecoRespFamiliar) {
		this.mesmoEnderecoRespFamiliar = mesmoEnderecoRespFamiliar;
	}

	public String getTransportaAlunoFamiliar() {
		return transportaAlunoFamiliar;
	}

	public void setTransportaAlunoFamiliar(String transportaAlunoFamiliar) {
		this.transportaAlunoFamiliar = transportaAlunoFamiliar;
	}

	public String getTutelaAlunoFamiliar() {
		return tutelaAlunoFamiliar;
	}

	public void setTutelaAlunoFamiliar(String tutelaAlunoFamiliar) {
		this.tutelaAlunoFamiliar = tutelaAlunoFamiliar;
	}

	public String getRespFinanceiroAlunoFamiliar() {
		return respFinanceiroAlunoFamiliar;
	}

	public void setRespFinanceiroAlunoFamiliar(String respFinanceiroAlunoFamiliar) {
		this.respFinanceiroAlunoFamiliar = respFinanceiroAlunoFamiliar;
	}

	public String getDescricaograuParentescoResponsavelFamiliar() {
		return descricaograuParentescoResponsavelFamiliar;
	}

	public void setDescricaograuParentescoResponsavelFamiliar(String descricaograuParentescoResponsavelFamiliar) {
		this.descricaograuParentescoResponsavelFamiliar = descricaograuParentescoResponsavelFamiliar;
	}

	public String getDescricaoDesligamentoResponsavelFamiliar() {
		return descricaoDesligamentoResponsavelFamiliar;
	}

	public void setDescricaoDesligamentoResponsavelFamiliar(String descricaoDesligamentoResponsavelFamiliar) {
		this.descricaoDesligamentoResponsavelFamiliar = descricaoDesligamentoResponsavelFamiliar;
	}

	public String getDescricaoFamiliar() {
		return descricaoFamiliar;
	}

	public void setDescricaoFamiliar(String descricaoFamiliar) {
		this.descricaoFamiliar = descricaoFamiliar;
	}

	public String getNivelEscolaridadeFamiliar() {
		return nivelEscolaridadeFamiliar;
	}

	public void setNivelEscolaridadeFamiliar(String nivelEscolaridadeFamiliar) {
		this.nivelEscolaridadeFamiliar = nivelEscolaridadeFamiliar;
	}

	public String getGrauInscrucaoFamiliar() {
		return grauInscrucaoFamiliar;
	}

	public void setGrauInscrucaoFamiliar(String grauInscrucaoFamiliar) {
		this.grauInscrucaoFamiliar = grauInscrucaoFamiliar;
	}

	public String getCpfFamiliar() {
		return cpfFamiliar;
	}

	public void setCpfFamiliar(String cpfFamiliar) {
		this.cpfFamiliar = cpfFamiliar;
	}

	public String getNisFamiliar() {
		return nisFamiliar;
	}

	public void setNisFamiliar(String nisFamiliar) {
		this.nisFamiliar = nisFamiliar;
	}

	public String getPispasepFamiliar() {
		return pispasepFamiliar;
	}

	public void setPispasepFamiliar(String pispasepFamiliar) {
		this.pispasepFamiliar = pispasepFamiliar;
	}

	public String getIdentidadeFamiliar() {
		return identidadeFamiliar;
	}

	public void setIdentidadeFamiliar(String identidadeFamiliar) {
		this.identidadeFamiliar = identidadeFamiliar;
	}

	public String getOrgaoExpedidorCiFamiliar() {
		return orgaoExpedidorCiFamiliar;
	}

	public void setOrgaoExpedidorCiFamiliar(String orgaoExpedidorCiFamiliar) {
		this.orgaoExpedidorCiFamiliar = orgaoExpedidorCiFamiliar;
	}

	public String getUfCiFamiliar() {
		return ufCiFamiliar;
	}

	public void setUfCiFamiliar(String ufCiFamiliar) {
		this.ufCiFamiliar = ufCiFamiliar;
	}

	public LocalDate getDataEmissaoCiFamiliar() {
		return dataEmissaoCiFamiliar;
	}

	public void setDataEmissaoCiFamiliar(LocalDate dataEmissaoCiFamiliar) {
		this.dataEmissaoCiFamiliar = dataEmissaoCiFamiliar;
	}

	public String getTituloEleitorFamiliar() {
		return tituloEleitorFamiliar;
	}

	public void setTituloEleitorFamiliar(String tituloEleitorFamiliar) {
		this.tituloEleitorFamiliar = tituloEleitorFamiliar;
	}

	public String getZonaTituloFamiliar() {
		return zonaTituloFamiliar;
	}

	public void setZonaTituloFamiliar(String zonaTituloFamiliar) {
		this.zonaTituloFamiliar = zonaTituloFamiliar;
	}

	public String getSessaoTituloFamiliar() {
		return sessaoTituloFamiliar;
	}

	public void setSessaoTituloFamiliar(String sessaoTituloFamiliar) {
		this.sessaoTituloFamiliar = sessaoTituloFamiliar;
	}

	public String getCertificadoReservistaFamiliar() {
		return certificadoReservistaFamiliar;
	}

	public void setCertificadoReservistaFamiliar(String certificadoReservistaFamiliar) {
		this.certificadoReservistaFamiliar = certificadoReservistaFamiliar;
	}

	public String getRegiaoMilitarReservistaFamiliar() {
		return regiaoMilitarReservistaFamiliar;
	}

	public void setRegiaoMilitarReservistaFamiliar(String regiaoMilitarReservistaFamiliar) {
		this.regiaoMilitarReservistaFamiliar = regiaoMilitarReservistaFamiliar;
	}

	public String getUfReservistaFamiliar() {
		return ufReservistaFamiliar;
	}

	public void setUfReservistaFamiliar(String ufReservistaFamiliar) {
		this.ufReservistaFamiliar = ufReservistaFamiliar;
	}

	public String getCnhFamiliar() {
		return cnhFamiliar;
	}

	public void setCnhFamiliar(String cnhFamiliar) {
		this.cnhFamiliar = cnhFamiliar;
	}

	public String getCategoriaCnhFamiliar() {
		return categoriaCnhFamiliar;
	}

	public void setCategoriaCnhFamiliar(String categoriaCnhFamiliar) {
		this.categoriaCnhFamiliar = categoriaCnhFamiliar;
	}

	public LocalDate getDataVencimentoCnhFamiliar() {
		return dataVencimentoCnhFamiliar;
	}

	public void setDataVencimentoCnhFamiliar(LocalDate dataVencimentoCnhFamiliar) {
		this.dataVencimentoCnhFamiliar = dataVencimentoCnhFamiliar;
	}

	public String getCarteiraTrabalhoFamiliar() {
		return carteiraTrabalhoFamiliar;
	}

	public void setCarteiraTrabalhoFamiliar(String carteiraTrabalhoFamiliar) {
		this.carteiraTrabalhoFamiliar = carteiraTrabalhoFamiliar;
	}

	public String getSerieCarteiraTrabalhoFamiliar() {
		return serieCarteiraTrabalhoFamiliar;
	}

	public void setSerieCarteiraTrabalhoFamiliar(String serieCarteiraTrabalhoFamiliar) {
		this.serieCarteiraTrabalhoFamiliar = serieCarteiraTrabalhoFamiliar;
	}

	public String getUfCarteiraTrabalhoFamiliar() {
		return ufCarteiraTrabalhoFamiliar;
	}

	public void setUfCarteiraTrabalhoFamiliar(String ufCarteiraTrabalhoFamiliar) {
		this.ufCarteiraTrabalhoFamiliar = ufCarteiraTrabalhoFamiliar;
	}

	public String getDescricaoProfissaoFamiliar() {
		return descricaoProfissaoFamiliar;
	}

	public void setDescricaoProfissaoFamiliar(String descricaoProfissaoFamiliar) {
		this.descricaoProfissaoFamiliar = descricaoProfissaoFamiliar;
	}

	public String getNomeEmpresaTrabalhoFamiliar() {
		return nomeEmpresaTrabalhoFamiliar;
	}

	public void setNomeEmpresaTrabalhoFamiliar(String nomeEmpresaTrabalhoFamiliar) {
		this.nomeEmpresaTrabalhoFamiliar = nomeEmpresaTrabalhoFamiliar;
	}

	public String getBeneficioBolsaFamiliaFamiliar() {
		return beneficioBolsaFamiliaFamiliar;
	}

	public void setBeneficioBolsaFamiliaFamiliar(String beneficioBolsaFamiliaFamiliar) {
		this.beneficioBolsaFamiliaFamiliar = beneficioBolsaFamiliaFamiliar;
	}

	public Double getValorBolsaFamiliaFamiliar() {
		return valorBolsaFamiliaFamiliar;
	}

	public void setValorBolsaFamiliaFamiliar(Double valorBolsaFamiliaFamiliar) {
		this.valorBolsaFamiliaFamiliar = valorBolsaFamiliaFamiliar;
	}

	public Double getValorRendaFamiliar() {
		return valorRendaFamiliar;
	}

	public void setValorRendaFamiliar(Double valorRendaFamiliar) {
		this.valorRendaFamiliar = valorRendaFamiliar;
	}

	public String getFoneComercialFamiliar() {
		return foneComercialFamiliar;
	}

	public void setFoneComercialFamiliar(String foneComercialFamiliar) {
		this.foneComercialFamiliar = foneComercialFamiliar;
	}

	public String getDescricaoSituacaoTrabalhoFamiliar() {
		return descricaoSituacaoTrabalhoFamiliar;
	}

	public void setDescricaoSituacaoTrabalhoFamiliar(String descricaoSituacaoTrabalhoFamiliar) {
		this.descricaoSituacaoTrabalhoFamiliar = descricaoSituacaoTrabalhoFamiliar;
	}

	public String getDescricaoMotivoNaoTrabFamiliar() {
		return descricaoMotivoNaoTrabFamiliar;
	}

	public void setDescricaoMotivoNaoTrabFamiliar(String descricaoMotivoNaoTrabFamiliar) {
		this.descricaoMotivoNaoTrabFamiliar = descricaoMotivoNaoTrabFamiliar;
	}

	public String getTipoEscolaFamiliar() {
		return tipoEscolaFamiliar;
	}

	public void setTipoEscolaFamiliar(String tipoEscolaFamiliar) {
		this.tipoEscolaFamiliar = tipoEscolaFamiliar;
	}

	public String getTurnoFamiliar() {
		return turnoFamiliar;
	}

	public void setTurnoFamiliar(String turnoFamiliar) {
		this.turnoFamiliar = turnoFamiliar;
	}

	public String getEscolaFamiliar() {
		return escolaFamiliar;
	}

	public void setEscolaFamiliar(String escolaFamiliar) {
		this.escolaFamiliar = escolaFamiliar;
	}

	public String getCursoEscolaFamiliar() {
		return cursoEscolaFamiliar;
	}

	public void setCursoEscolaFamiliar(String cursoEscolaFamiliar) {
		this.cursoEscolaFamiliar = cursoEscolaFamiliar;
	}

	public String getPeriodoEscolaFamiliar() {
		return periodoEscolaFamiliar;
	}

	public void setPeriodoEscolaFamiliar(String periodoEscolaFamiliar) {
		this.periodoEscolaFamiliar = periodoEscolaFamiliar;
	}

	public String getSerieEscolaFamiliar() {
		return serieEscolaFamiliar;
	}

	public void setSerieEscolaFamiliar(String serieEscolaFamiliar) {
		this.serieEscolaFamiliar = serieEscolaFamiliar;
	}

	public String getRegiaoAdministrativaEscolaFamiliar() {
		return regiaoAdministrativaEscolaFamiliar;
	}

	public void setRegiaoAdministrativaEscolaFamiliar(String regiaoAdministrativaEscolaFamiliar) {
		this.regiaoAdministrativaEscolaFamiliar = regiaoAdministrativaEscolaFamiliar;
	}

	public String getPossuiDeficienteFamiliar() {
		return possuiDeficienteFamiliar;
	}

	public void setPossuiDeficienteFamiliar(String possuiDeficienteFamiliar) {
		this.possuiDeficienteFamiliar = possuiDeficienteFamiliar;
	}

	public String getDescricaoDeficienciaFamiliar() {
		return descricaoDeficienciaFamiliar;
	}

	public void setDescricaoDeficienciaFamiliar(String descricaoDeficienciaFamiliar) {
		this.descricaoDeficienciaFamiliar = descricaoDeficienciaFamiliar;
	}

	public String getDescricaoProblemaSaudeFamiliar() {
		return descricaoProblemaSaudeFamiliar;
	}

	public void setDescricaoProblemaSaudeFamiliar(String descricaoProblemaSaudeFamiliar) {
		this.descricaoProblemaSaudeFamiliar = descricaoProblemaSaudeFamiliar;
	}

	public String getDescricaoMedicamentosControladosFamiliar() {
		return descricaoMedicamentosControladosFamiliar;
	}

	public void setDescricaoMedicamentosControladosFamiliar(String descricaoMedicamentosControladosFamiliar) {
		this.descricaoMedicamentosControladosFamiliar = descricaoMedicamentosControladosFamiliar;
	}

	public String getCondicaoMoradiaFamiliar() {
		return condicaoMoradiaFamiliar;
	}

	public void setCondicaoMoradiaFamiliar(String condicaoMoradiaFamiliar) {
		this.condicaoMoradiaFamiliar = condicaoMoradiaFamiliar;
	}

	public String getDescricaoCondicaoMoradiaFamiliar() {
		return descricaoCondicaoMoradiaFamiliar;
	}

	public void setDescricaoCondicaoMoradiaFamiliar(String descricaoCondicaoMoradiaFamiliar) {
		this.descricaoCondicaoMoradiaFamiliar = descricaoCondicaoMoradiaFamiliar;
	}

	public String getDescricaoAtendidoOrgaoRedeFamiliar() {
		return descricaoAtendidoOrgaoRedeFamiliar;
	}

	public void setDescricaoAtendidoOrgaoRedeFamiliar(String descricaoAtendidoOrgaoRedeFamiliar) {
		this.descricaoAtendidoOrgaoRedeFamiliar = descricaoAtendidoOrgaoRedeFamiliar;
	}

	public String getBeneficiosFamiliar() {
		return beneficiosFamiliar;
	}

	public void setBeneficiosFamiliar(String beneficiosFamiliar) {
		this.beneficiosFamiliar = beneficiosFamiliar;
	}

	public String getDescricaoRedeApSocRelevFamiliar() {
		return descricaoRedeApSocRelevFamiliar;
	}

	public void setDescricaoRedeApSocRelevFamiliar(String descricaoRedeApSocRelevFamiliar) {
		this.descricaoRedeApSocRelevFamiliar = descricaoRedeApSocRelevFamiliar;
	}

	public String getDescricaoRedeApoioSocialFamiliar() {
		return descricaoRedeApoioSocialFamiliar;
	}

	public void setDescricaoRedeApoioSocialFamiliar(String descricaoRedeApoioSocialFamiliar) {
		this.descricaoRedeApoioSocialFamiliar = descricaoRedeApoioSocialFamiliar;
	}

	public Double getValorBeneficiosFamiliar() {
		return valorBeneficiosFamiliar;
	}

	public void setValorBeneficiosFamiliar(Double valorBeneficiosFamiliar) {
		this.valorBeneficiosFamiliar = valorBeneficiosFamiliar;
	}

	public String getOrigemRendaFamiliar() {
		return origemRendaFamiliar;
	}

	public void setOrigemRendaFamiliar(String origemRendaFamiliar) {
		this.origemRendaFamiliar = origemRendaFamiliar;
	}

	public Long getIdPessoaFisica() {
		return idPessoaFisica;
	}

	public void setIdPessoaFisica(Long idPessoaFisica) {
		this.idPessoaFisica = idPessoaFisica;
	}

	public Long getIdAluno() {
		return idAluno;
	}

	public void setIdAluno(Long idAluno) {
		this.idAluno = idAluno;
	}

	public String getAprExternaAluno() {
		return aprExternaAluno;
	}

	public void setAprExternaAluno(String aprExternaAluno) {
		this.aprExternaAluno = aprExternaAluno;
	}

	
}

package br.com.crux.to;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import br.com.crux.infra.adapter.LocalDateTimeAdapter;

public class PessoaFisicaTO {

	private Long id;
	private String nome;
	private String orgaoCi;
	private String classificadorMotivoNaoTrab;
	private String descricaoPessoaFisicaAtendidoOrgaoRede;
	private String bairro;
	private String cidade;
	private String cidadeNaturalidade;
	private String condicaoMoradia;
	private String cor;
	private String cursoEscola;
	private String email;
	private String endereco;
	private EscolaTO escola;
	private SerieEscolarTO serieEscolar;
	private String escolaridade;
	private String estadoCivil;
	private String formaIngressoEntidade;
	private String medicamentosControlados;
	private String motivoNaoTrab;
	private String nivelEscolaridade;
	private String outrosBenSoc;
	private String periodoEscola;
	private String pontoReferencia;
	private String problemaSaude;
	private String profissao;
	private String redeApSocRelev;
	private String redeApoioSocial;
	private String sexo;
	private String situacaoTrabalho;
	private String turno;
	private LocalDateTime dataNascimento;
	private String nomeEmpresaTrabalho;
	private String nomeMae;
	private String nomePai;
	private Long cep;
	private String identidade;
	private String cpf;
	private String cts;
	private String celular;
	private String nis;
	private String serieCtps;
	private String sessaoTitulo;
	private String telefoneComercial;
	private String telefoneResidencial;
	private String tituloEleitor;
	private String zonaTitulo;
	private String ufCi;
	private String uf;
	private String ufNascimento;
	// Classificador indicativo se a pessoa física é atendida por outro órgão da
	// rede de apoio social / pessoal
	private String statusAtendidoOrgaoRede;
	private String autorizaEmail;
	private String beneficiarioBolsaFamilia;
	private String observacoes;
	private Double valorAluguel;
	private Double valorBolsaFamilia;
	private Double valorOutrosBenerficiosSoc;
	private Double valorRenda;
	private Double valorRendaCtps;
	private Double valorRendaAutonomo;
	private Double valorRendaPensaoAlimenticia;
	private Double valorRendaAposentadoria;

	private ArquivoMetadadoTO metadados;
	private CondicoesMoradiaTO condicoesMoradia;
	private GrausInstrucaoTO grausInstrucao;
	private Long usuarioAlteracao;
	
	
	private String ehDeficiente;
	private String cursandoNivelSuperior;
	
	private String descricaoDeficiencia;	
	private String tipoSangue;	
	private String raca;	
	private String numeroReservista;	
	private String regiaoMilitarReservista;	
	private String ufRegiaoMilitar;	
	private String numeroCNH;	
	private String categoriaCNH;	
	private String numeroPisPasep;	
	private String ufCTS;	
	
	@XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
	private LocalDateTime dataEmissaoCI;
	@XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
	private LocalDateTime vencimentoCNH;

	private Long idInstituicao;
	private String emailProfissional;
	private String origemRendaFamiliar;
	private String celular2;
	private String celular3;
	private String foneRecado;	
	private Boolean semCpf;

	private String nomeSocial;
	private String complementoEndereco;

	private Long qtPessoasResidemFamilia;
	
	private List<BeneficioSocialPessoaFisicaTO> beneficiosSociaisPessoaFisica;
	
	
	public PessoaFisicaTO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getOrgaoCi() {
		return orgaoCi;
	}

	public void setOrgaoCi(String orgaoCi) {
		this.orgaoCi = orgaoCi;
	}

	public String getClassificadorMotivoNaoTrab() {
		return classificadorMotivoNaoTrab;
	}

	public void setClassificadorMotivoNaoTrab(String classificadorMotivoNaoTrab) {
		this.classificadorMotivoNaoTrab = classificadorMotivoNaoTrab;
	}

	public String getDescricaoPessoaFisicaAtendidoOrgaoRede() {
		return descricaoPessoaFisicaAtendidoOrgaoRede;
	}

	public void setDescricaoPessoaFisicaAtendidoOrgaoRede(String descricaoAlunoAtendidoOrgaoRede) {
		this.descricaoPessoaFisicaAtendidoOrgaoRede = descricaoAlunoAtendidoOrgaoRede;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidadeNaturalidade) {
		this.cidade = cidadeNaturalidade;
	}

	public String getCondicaoMoradia() {
		return condicaoMoradia;
	}

	public void setCondicaoMoradia(String condicaoMoradia) {
		this.condicaoMoradia = condicaoMoradia;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public String getCursoEscola() {
		return cursoEscola;
	}

	public void setCursoEscola(String cursoEscola) {
		this.cursoEscola = cursoEscola;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public EscolaTO getEscola() {
		return escola;
	}

	public void setEscola(EscolaTO escola) {
		this.escola = escola;
	}

	public String getNomeSocial() {
		return nomeSocial;
	}

	public void setNomeSocial(String nomeSocial) {
		this.nomeSocial = nomeSocial;
	}

	public String getEscolaridade() {
		return escolaridade;
	}

	public void setEscolaridade(String escolaridade) {
		this.escolaridade = escolaridade;
	}

	public String getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public String getFormaIngressoEntidade() {
		return formaIngressoEntidade;
	}

	public void setFormaIngressoEntidade(String formaIngressoEntidade) {
		this.formaIngressoEntidade = formaIngressoEntidade;
	}

	public String getMedicamentosControlados() {
		return medicamentosControlados;
	}

	public void setMedicamentosControlados(String medicamentosControlados) {
		this.medicamentosControlados = medicamentosControlados;
	}

	public String getMotivoNaoTrab() {
		return motivoNaoTrab;
	}

	public void setMotivoNaoTrab(String motivoNaoTrab) {
		this.motivoNaoTrab = motivoNaoTrab;
	}

	public String getNivelEscolaridade() {
		return nivelEscolaridade;
	}

	public void setNivelEscolaridade(String nivelEscolaridade) {
		this.nivelEscolaridade = nivelEscolaridade;
	}

	public String getOutrosBenSoc() {
		return outrosBenSoc;
	}

	public void setOutrosBenSoc(String outrosBenSoc) {
		this.outrosBenSoc = outrosBenSoc;
	}

	public String getPeriodoEscola() {
		return periodoEscola;
	}

	public void setPeriodoEscola(String periodoEscola) {
		this.periodoEscola = periodoEscola;
	}

	public String getPontoReferencia() {
		return pontoReferencia;
	}

	public void setPontoReferencia(String pontoReferencia) {
		this.pontoReferencia = pontoReferencia;
	}

	public String getProblemaSaude() {
		return problemaSaude;
	}

	public void setProblemaSaude(String problemaSaude) {
		this.problemaSaude = problemaSaude;
	}

	public String getProfissao() {
		return profissao;
	}

	public void setProfissao(String profissao) {
		this.profissao = profissao;
	}

	public String getRedeApSocRelev() {
		return redeApSocRelev;
	}

	public void setRedeApSocRelev(String redeApSocRelev) {
		this.redeApSocRelev = redeApSocRelev;
	}

	public String getRedeApoioSocial() {
		return redeApoioSocial;
	}

	public void setRedeApoioSocial(String redeApoioSocial) {
		this.redeApoioSocial = redeApoioSocial;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getSituacaoTrabalho() {
		return situacaoTrabalho;
	}

	public void setSituacaoTrabalho(String situacaoTrabalho) {
		this.situacaoTrabalho = situacaoTrabalho;
	}

	public String getTurno() {
		return turno;
	}

	public void setTurno(String turno) {
		this.turno = turno;
	}

	public LocalDateTime getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDateTime dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getNomeEmpresaTrabalho() {
		return nomeEmpresaTrabalho;
	}

	public void setNomeEmpresaTrabalho(String nomeEmpresaTrabalho) {
		this.nomeEmpresaTrabalho = nomeEmpresaTrabalho;
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

	public String getIdentidade() {
		return identidade;
	}

	public void setIdentidade(String identidade) {
		this.identidade = identidade;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getCts() {
		return cts;
	}

	public void setCts(String cts) {
		this.cts = cts;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getNis() {
		return nis;
	}

	public void setNis(String nis) {
		this.nis = nis;
	}

	public String getSerieCtps() {
		return serieCtps;
	}

	public void setSerieCtps(String serieCtps) {
		this.serieCtps = serieCtps;
	}

	public String getSessaoTitulo() {
		return sessaoTitulo;
	}

	public void setSessaoTitulo(String sessaoTitulo) {
		this.sessaoTitulo = sessaoTitulo;
	}

	public String getTelefoneComercial() {
		return telefoneComercial;
	}

	public void setTelefoneComercial(String telefoneComercial) {
		this.telefoneComercial = telefoneComercial;
	}

	public String getTelefoneResidencial() {
		return telefoneResidencial;
	}

	public void setTelefoneResidencial(String telefoneResidencial) {
		this.telefoneResidencial = telefoneResidencial;
	}

	public String getTituloEleitor() {
		return tituloEleitor;
	}

	public void setTituloEleitor(String tituloEleitor) {
		this.tituloEleitor = tituloEleitor;
	}

	public String getZonaTitulo() {
		return zonaTitulo;
	}

	public void setZonaTitulo(String zonaTitulo) {
		this.zonaTitulo = zonaTitulo;
	}

	public String getUfCi() {
		return ufCi;
	}

	public void setUfCi(String ufCi) {
		this.ufCi = ufCi;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String ufEndereco) {
		this.uf = ufEndereco;
	}

	public String getUfNascimento() {
		return ufNascimento;
	}

	public void setUfNascimento(String ufNascimento) {
		this.ufNascimento = ufNascimento;
	}

	public String getStatusAtendidoOrgaoRede() {
		return statusAtendidoOrgaoRede;
	}

	public void setStatusAtendidoOrgaoRede(String statusAtendidoOrgaoRede) {
		this.statusAtendidoOrgaoRede = statusAtendidoOrgaoRede;
	}

	public String getAutorizaEmail() {
		return autorizaEmail;
	}

	public void setAutorizaEmail(String autorizaEmail) {
		this.autorizaEmail = autorizaEmail;
	}

	public String getBeneficiarioBolsaFamilia() {
		return beneficiarioBolsaFamilia;
	}

	public void setBeneficiarioBolsaFamilia(String beneficiarioBolsaFamilia) {
		this.beneficiarioBolsaFamilia = beneficiarioBolsaFamilia;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public Double getValorAluguel() {
		return valorAluguel;
	}

	public void setValorAluguel(Double valorAluguel) {
		this.valorAluguel = valorAluguel;
	}

	public Double getValorBolsaFamilia() {
		return valorBolsaFamilia;
	}

	public void setValorBolsaFamilia(Double valorBolsaFamilia) {
		this.valorBolsaFamilia = valorBolsaFamilia;
	}

	public Double getValorOutrosBenerficiosSoc() {
		return valorOutrosBenerficiosSoc;
	}

	public void setValorOutrosBenerficiosSoc(Double valorOutrosBenerficiosSoc) {
		this.valorOutrosBenerficiosSoc = valorOutrosBenerficiosSoc;
	}

	public Double getValorRenda() {
		return valorRenda;
	}

	public void setValorRenda(Double valorRenda) {
		this.valorRenda = valorRenda;
	}


	public ArquivoMetadadoTO getMetadados() {
		return metadados;
	}

	public void setMetadados(ArquivoMetadadoTO metadados) {
		this.metadados = metadados;
	}

	public CondicoesMoradiaTO getCondicoesMoradia() {
		return condicoesMoradia;
	}

	public void setCondicoesMoradia(CondicoesMoradiaTO condicoesMoradia) {
		this.condicoesMoradia = condicoesMoradia;
	}

	public GrausInstrucaoTO getGrausInstrucao() {
		return grausInstrucao;
	}

	public void setGrausInstrucao(GrausInstrucaoTO grausInstrucao) {
		this.grausInstrucao = grausInstrucao;
	}

	public Long getUsuarioAlteracao() {
		return usuarioAlteracao;
	}

	public void setUsuarioAlteracao(Long usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}

	public String getEhDeficiente() {
		return ehDeficiente;
	}

	public void setEhDeficiente(String ehDeficiente) {
		this.ehDeficiente = ehDeficiente;
	}

	public String getCursandoNivelSuperior() {
		return cursandoNivelSuperior;
	}

	public void setCursandoNivelSuperior(String cursandoNivelSuperior) {
		this.cursandoNivelSuperior = cursandoNivelSuperior;
	}

	public String getDescricaoDeficiencia() {
		return descricaoDeficiencia;
	}

	public void setDescricaoDeficiencia(String descricaoDeficiencia) {
		this.descricaoDeficiencia = descricaoDeficiencia;
	}

	public String getTipoSangue() {
		return tipoSangue;
	}

	public void setTipoSangue(String tipoSangue) {
		this.tipoSangue = tipoSangue;
	}

	public String getRaca() {
		return raca;
	}

	public void setRaca(String raca) {
		this.raca = raca;
	}

	public String getNumeroReservista() {
		return numeroReservista;
	}

	public void setNumeroReservista(String numeroReservista) {
		this.numeroReservista = numeroReservista;
	}

	public String getRegiaoMilitarReservista() {
		return regiaoMilitarReservista;
	}

	public void setRegiaoMilitarReservista(String regiaoMilitarReservista) {
		this.regiaoMilitarReservista = regiaoMilitarReservista;
	}

	public String getUfRegiaoMilitar() {
		return ufRegiaoMilitar;
	}

	public void setUfRegiaoMilitar(String ufRegiaoMilitar) {
		this.ufRegiaoMilitar = ufRegiaoMilitar;
	}

	public String getNumeroCNH() {
		return numeroCNH;
	}

	public void setNumeroCNH(String numeroCNH) {
		this.numeroCNH = numeroCNH;
	}

	public String getCategoriaCNH() {
		return categoriaCNH;
	}

	public void setCategoriaCNH(String categoriaCNH) {
		this.categoriaCNH = categoriaCNH;
	}

	public String getNumeroPisPasep() {
		return numeroPisPasep;
	}

	public void setNumeroPisPasep(String numeroPisPasep) {
		this.numeroPisPasep = numeroPisPasep;
	}

	public String getUfCTS() {
		return ufCTS;
	}

	public void setUfCTS(String ufCTS) {
		this.ufCTS = ufCTS;
	}

	public LocalDateTime getDataEmissaoCI() {
		return dataEmissaoCI;
	}

	public void setDataEmissaoCI(LocalDateTime dataEmissaoCI) {
		this.dataEmissaoCI = dataEmissaoCI;
	}

	public LocalDateTime getVencimentoCNH() {
		return vencimentoCNH;
	}

	public void setVencimentoCNH(LocalDateTime vencimentoCNH) {
		this.vencimentoCNH = vencimentoCNH;
	}

	public Long getIdInstituicao() {
		return idInstituicao;
	}

	public void setIdInstituicao(Long idInstituicao) {
		this.idInstituicao = idInstituicao;
	}

	public String getEmailProfissional() {
		return emailProfissional;
	}

	public void setEmailProfissional(String emailProfissional) {
		this.emailProfissional = emailProfissional;
	}

	public String getCidadeNaturalidade() {
		return cidadeNaturalidade;
	}

	public void setCidadeNaturalidade(String cidadeNaturalidade) {
		this.cidadeNaturalidade = cidadeNaturalidade;
	}
	public String getOrigemRendaFamiliar() {
		return origemRendaFamiliar;
	}

	public void setOrigemRendaFamiliar(String origemRendaFamiliar) {
		this.origemRendaFamiliar = origemRendaFamiliar;
	}

	public String getCelular2() {
		return celular2;
	}

	public void setCelular2(String celular2) {
		this.celular2 = celular2;
	}

	public String getFoneRecado() {
		return foneRecado;
	}

	public void setFoneRecado(String foneRecado) {
		this.foneRecado = foneRecado;
	}

	public Boolean getSemCpf() {
		return semCpf;
	}

	public void setSemCpf(Boolean semCpf) {
		this.semCpf = semCpf;
	}

	public Long getQtPessoasResidemFamilia() {
		return qtPessoasResidemFamilia;
	}

	public void setQtPessoasResidemFamilia(Long qtPessoasResidemFamilia) {
		this.qtPessoasResidemFamilia = qtPessoasResidemFamilia;
	}	
	
	public List<BeneficioSocialPessoaFisicaTO> getBeneficiosSociaisPessoaFisica() {
		if(beneficiosSociaisPessoaFisica== null) {
			beneficiosSociaisPessoaFisica = new ArrayList<BeneficioSocialPessoaFisicaTO>();
		}
		return beneficiosSociaisPessoaFisica;
	}

	public void setBeneficiosSociaisPessoaFisica(List<BeneficioSocialPessoaFisicaTO> beneficiosSociaisPessoaFisica) {
		this.beneficiosSociaisPessoaFisica = beneficiosSociaisPessoaFisica;
	}

	public Double getValorRendaCtps() {
		return valorRendaCtps;
	}

	public void setValorRendaCtps(Double valorRendaCtps) {
		this.valorRendaCtps = valorRendaCtps;
	}

	public Double getValorRendaAutonomo() {
		return valorRendaAutonomo;
	}

	public void setValorRendaAutonomo(Double valorRendaAutonomo) {
		this.valorRendaAutonomo = valorRendaAutonomo;
	}

	public Double getValorRendaPensaoAlimenticia() {
		return valorRendaPensaoAlimenticia;
	}

	public void setValorRendaPensaoAlimenticia(Double valorRendaPensaoAlimenticia) {
		this.valorRendaPensaoAlimenticia = valorRendaPensaoAlimenticia;
	}

	public Double getValorRendaAposentadoria() {
		return valorRendaAposentadoria;
	}

	public void setValorRendaAposentadoria(Double valorRendaAposentadoria) {
		this.valorRendaAposentadoria = valorRendaAposentadoria;
	}

	public String getCelular3() {
		return celular3;
	}

	public void setCelular3(String celular3) {
		this.celular3 = celular3;
	}

	public String getComplementoEndereco() {
		return complementoEndereco;
	}

	public void setComplementoEndereco(String complementoEndereco) {
		this.complementoEndereco = complementoEndereco;
	}

	public SerieEscolarTO getSerieEscolar() {
		return serieEscolar;
	}

	public void setSerieEscolar(SerieEscolarTO serieEscolar) {
		this.serieEscolar = serieEscolar;
	}
	
	
}

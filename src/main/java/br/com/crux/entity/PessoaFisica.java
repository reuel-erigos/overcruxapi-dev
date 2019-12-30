package br.com.crux.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.crux.infra.constantes.Constantes;
import br.com.crux.infra.dao.SimNaoConverter;

/**
 * The persistent class for the pessoas_fisicas database table.
 * 
 */
@Entity
@Table(name = "pessoas_fisicas")
public class PessoaFisica  {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_id_pessoa_fisica")
	@SequenceGenerator(name = "sq_id_pessoa_fisica", sequenceName = "sq_id_pessoa_fisica", schema = Constantes.SCHEMA_PUBLIC, initialValue = 1, allocationSize = 1)
	@Column(name = "id_pessoa_fisica")
	private Long id;

	@Column(name = "nm_pessoa_fisica")
	private String nome;

	@Column(name = "cd_orgao_ci")
	private String orgaoCi;

	// Classificador do motivo da Pessoa física não trabalhar ( SI = Não tem
	// interesse em trabalhar; NE = Procurou, mas não encontrou emprego ES = Somente
	// estuda SM = Presta serviço militar PS = Por problemas de saúde LA= Do lar OU
	// = Outros
	@Column(name = "cs_motivo_nao_trab")
	private String classificadorMotivoNaoTrab;

	@Column(name = "ds_atendido_orgao_rede")
	private String descricaoPessoaFisicaAtendidoOrgaoRede;

	@Column(name = "ds_bairro")
	private String bairro;

	@Column(name = "ds_cidade_naturalidade")
	private String cidadeNaturalidade;

	@Column(name = "ds_condicao_moradia")
	private String condicaoMoradia;

	@Column(name = "ds_cor")
	private String cor;

	@Column(name = "ds_curso_escola")
	private String cursoEscola;

	@Column(name = "ds_email")
	private String email;

	@Column(name = "ds_endereco")
	private String endereco;

	@Column(name = "ds_escola")
	private String escola;

	@Column(name = "ds_escolaridade")
	private String escolaridade;

	@Column(name = "ds_estado_civil")
	private String estadoCivil;

	@Column(name = "ds_forma_ingresso_entidade")
	private String formaIngressoEntidade;

	@Column(name = "ds_medicamentos_controlados")
	private String medicamentosControlados;

	@Column(name = "ds_motivo_nao_trab")
	private String motivoNaoTrab;

	@Column(name = "ds_nivel_escolaridade")
	private String nivelEscolaridade;

	@Column(name = "ds_outros_ben_soc")
	private String outrosBenSoc;

	@Column(name = "ds_periodo_escola")
	private String periodoEscola;

	@Column(name = "ds_ponto_referencia")
	private String pontoReferencia;

	@Column(name = "ds_problema_saude")
	private String problemaSaude;

	@Column(name = "ds_profissao")
	private String profissao;

	@Column(name = "ds_rede_ap_soc_relev")
	private String redeApSocRelev;

	@Column(name = "ds_rede_apoio_social")
	private String redeApoioSocial;

	@Column(name = "ds_regiao_escola")
	private String regiaoEscola;

	@Column(name = "ds_serie_escola")
	private String serieEscola;

	@Column(name = "ds_sexo")
	private String sexo;

	@Column(name = "ds_situacao_trabalho")
	private String situacaoTrabalho;

	@Column(name = "ds_tipo_escola")
	private String tipoEscola;

	@Column(name = "ds_turno")
	private String turno;

	@Column(name = "dt_nascimento")
	private LocalDateTime dataNascimento;

	@Column(name = "nm_empresa_trabalho")
	private String nomeEmpresaTrabalho;

	@Column(name = "nm_mae")
	private String nomeMae;

	@Column(name = "nm_pai")
	private String nomePai;

	@Column(name = "nr_cep")
	private Long cep;

	@Column(name = "nr_ci")
	private String identidade;

	@Column(name = "nr_cpf")
	private Long cpf;

	@Column(name = "nr_cts")
	private String cts;

	@Column(name = "nr_fone_celular")
	private String celular;

	@Column(name = "nr_nis")
	private String nis;

	@Column(name = "nr_serie_ctps")
	private String serieCtps;

	@Column(name = "nr_sessao_titulo")
	private String sessaoTitulo;

	@Column(name = "nr_telefone_comercial")
	private String telefoneComercial;

	@Column(name = "nr_telefone_residencial")
	private String telefoneResidencial;

	@Column(name = "nr_titulo_eleitor")
	private String tituloEleitor;

	@Column(name = "nr_zona_titulo")
	private String zonaTitulo;

	@Column(name = "sg_uf_ci")
	private String ufCi;

	@Column(name = "sg_uf_endereco")
	private String ufEndereco;

	@Column(name = "sg_uf_nascimento")
	private String ufNascimento;

	// Classificador indicativo se a pessoa física é atendida por outro órgão da
	// rede de apoio social / pessoal
	@Column(name = "st_atendido_orgao_rede")
	private String statusAtendidoOrgaoRede;

	@Column(name = "st_autoriza_email")
	private String autorizaEmail;

	@Column(name = "st_ben_bolsa_familia")
	private String beneficiarioBolsaFamilia;

	@Column(name = "tx_observacoes")
	private String observacoes;

	@Column(name = "vl_aluguel")
	private Double valorAluguel;

	@Column(name = "vl_bolsa_familia")
	private Double valorBolsaFamilia;

	@Column(name = "vl_outros_benerficios_soc")
	private Double valorOutrosBenerficiosSoc;

	@Column(name = "vl_renda")
	private Double valorRenda;

	@Column(name = "id_arquivo_foto")
	private Long idArquivo;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_condicao_moradia")
	private CondicoesMoradia condicoesMoradia;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "graus_inscrucao_pf")
	private GrausInstrucao grausInstrucao;

	@Column(name = "id_usuario_apl")
	private Long usuarioAlteracao;

	@Convert(converter = SimNaoConverter.class)
	@Column(name = "st_deficiente")
	private Boolean ehDeficiente;
	
	@Column(name = "ds_deficiente")
	private String descricaoDeficiencia;	
	
	@Column(name = "ds_tipo_sangue")
	private String tipoSangue;	
	
	@Column(name = "ds_raca")
	private String raca;	
	
	@Column(name="dt_emissao_ci")
	private LocalDateTime dataEmissaoCI;
	
	@Column(name = "nr_certificado_reservista")
	private String numeroReservista;	
	
	@Column(name = "nr_regiao_militar_reservista")
	private String regiaoMilitarReservista;	
	
	@Column(name = "uf_regiao_militar")
	private String ufRegiaoMilitar;	
	
	@Column(name = "nr_cnh")
	private String numeroCNH;	
	
	@Column(name = "categoria_cnh")
	private String categoriaCNH;	
	
	@Column(name="dt_vencimento_cnh")
	private LocalDateTime vencimentoCNH;

	@Column(name = "nr_pis_pasep")
	private String numeroPisPasep;	
	
	@Column(name = "uf_cts")
	private String ufCTS;	
	
	@Convert(converter = SimNaoConverter.class)
	@Column(name = "st_cursando_nivel_superior")
	private Boolean cursandoNivelSuperior;

	
	
	public PessoaFisica() {
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

	public String getCidadeNaturalidade() {
		return cidadeNaturalidade;
	}

	public void setCidadeNaturalidade(String cidadeNaturalidade) {
		this.cidadeNaturalidade = cidadeNaturalidade;
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

	public String getRegiaoEscola() {
		return regiaoEscola;
	}

	public void setRegiaoEscola(String regiaoEscola) {
		this.regiaoEscola = regiaoEscola;
	}

	public String getSerieEscola() {
		return serieEscola;
	}

	public void setSerieEscola(String serieEscola) {
		this.serieEscola = serieEscola;
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

	public String getTipoEscola() {
		return tipoEscola;
	}

	public void setTipoEscola(String tipoEscola) {
		this.tipoEscola = tipoEscola;
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

	public Long getCpf() {
		return cpf;
	}

	public void setCpf(Long cpf) {
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

	public String getUfEndereco() {
		return ufEndereco;
	}

	public void setUfEndereco(String ufEndereco) {
		this.ufEndereco = ufEndereco;
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

	public Long getIdArquivo() {
		return idArquivo;
	}

	public void setIdArquivo(Long idArquivo) {
		this.idArquivo = idArquivo;
	}

	public CondicoesMoradia getCondicoesMoradia() {
		return condicoesMoradia;
	}

	public void setCondicoesMoradia(CondicoesMoradia condicoesMoradia) {
		this.condicoesMoradia = condicoesMoradia;
	}

	public GrausInstrucao getGrausInstrucao() {
		return grausInstrucao;
	}

	public void setGrausInstrucao(GrausInstrucao grausInstrucao) {
		this.grausInstrucao = grausInstrucao;
	}

	public Long getUsuarioAlteracao() {
		return usuarioAlteracao;
	}

	public void setUsuarioAlteracao(Long usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}

	public Boolean getEhDeficiente() {
		return ehDeficiente;
	}

	public void setEhDeficiente(Boolean ehDeficiente) {
		this.ehDeficiente = ehDeficiente;
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

	public LocalDateTime getDataEmissaoCI() {
		return dataEmissaoCI;
	}

	public void setDataEmissaoCI(LocalDateTime dataEmissaoCI) {
		this.dataEmissaoCI = dataEmissaoCI;
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

	public LocalDateTime getVencimentoCNH() {
		return vencimentoCNH;
	}

	public void setVencimentoCNH(LocalDateTime vencimentoCNH) {
		this.vencimentoCNH = vencimentoCNH;
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

	public Boolean getCursandoNivelSuperior() {
		return cursandoNivelSuperior;
	}

	public void setCursandoNivelSuperior(Boolean cursandoNivelSuperior) {
		this.cursandoNivelSuperior = cursandoNivelSuperior;
	}
	
	

}
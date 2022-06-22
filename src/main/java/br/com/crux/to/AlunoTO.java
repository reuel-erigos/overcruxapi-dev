package br.com.crux.to;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import br.com.crux.infra.adapter.LocalDateTimeAdapter;


public class AlunoTO {
	
	private Long id;
	private String descProblemaSaude;
	private String descMedicamentosControlados;
	private String descOutrasInformacoes;
	private String descFormaIngressoEntidade;
	private String atendidoOrgaoRede;
	private String observacoes;
	
	@XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
	private LocalDateTime dataEntrada;
	@XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
	private LocalDateTime dataDesligamento;
	@XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
	private LocalDateTime dataCadastro;
	@XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
	private LocalDateTime dataAlteracaoCadastro;

	private String descDesligamento;
	private PessoaFisicaTO pessoaFisica;
	private UnidadeTO unidade;
	
	private Boolean moraPais;
	private Boolean paisCasados;
	private String matriculadoEscPub;
	private String descBuscaEscola;
	private String publicoPrioritario;
	private String matriculaAluno;
	private NiveisTurmasTO nivelTurma;
	private TiposPublicoPrioritarioTO tiposPublicoPrioritario;
	private MotivoDesligamentoTO motivoDesligamento;
	private Long usuarioAlteracao;

	private List<VulnerabilidadesAlunoTO> vulnerabilidades;
	private List<EncaminhaAlunosTO> encaminhamentos;
	private List<BeneficioSocialPessoaFisicaTO> benefeciosSociaisPessoaFisica;
	private LocalDateTime dataSugestaoDesligamento;
	private String descricaoSugestaoDesligamento;

	private ProgramaTO programa;
	private ProjetoTO projeto;
	
	private String observacaoDeclaracaoPasse;
	
	@XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
	private LocalDateTime dataDeclaracaoPasse;
	
	private String observacaoDeclaracaoMatricula;
	
	@XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
	private LocalDateTime dataDeclaracaoMatricula;
	
	private Boolean  participaApresentacaoExterna;
	
	private Boolean stAtivo;
//	private String stAtivo;
	
	public AlunoTO() {
	}
	
	public AlunoTO(Object[] colunas) {
		this.pessoaFisica = new PessoaFisicaTO();
		this.pessoaFisica.setId(((BigDecimal)colunas[0]).longValue());
		this.id = ((BigDecimal) colunas[1]).longValue();
		this.matriculaAluno = (String) colunas[2];
		this.pessoaFisica.setNome((String) colunas[3]);
		this.dataEntrada = (colunas[4] != null)? ((Timestamp)colunas[4]).toLocalDateTime(): null;
		this.dataDesligamento = (colunas[5] != null)? ((Timestamp)colunas[5]).toLocalDateTime() : null;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getDescOutrasInformacoes() {
		return descOutrasInformacoes;
	}

	public void setDescOutrasInformacoes(String descOutrasInformacoes) {
		this.descOutrasInformacoes = descOutrasInformacoes;
	}

	public String getDescFormaIngressoEntidade() {
		return descFormaIngressoEntidade;
	}

	public void setDescFormaIngressoEntidade(String descFormaIngressoEntidade) {
		this.descFormaIngressoEntidade = descFormaIngressoEntidade;
	}

	public String getAtendidoOrgaoRede() {
		return atendidoOrgaoRede;
	}

	public void setAtendidoOrgaoRede(String atendidoOrgaoRede) {
		this.atendidoOrgaoRede = atendidoOrgaoRede;
	}

	public LocalDateTime getDataEntrada() {
		return dataEntrada;
	}

	public void setDataEntrada(LocalDateTime dataEntrada) {
		this.dataEntrada = dataEntrada;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public LocalDateTime getDataDesligamento() {
		return dataDesligamento;
	}

	public void setDataDesligamento(LocalDateTime dataDesligamento) {
		this.dataDesligamento = dataDesligamento;
	}

	public String getDescDesligamento() {
		return descDesligamento;
	}

	public void setDescDesligamento(String descDesligamento) {
		this.descDesligamento = descDesligamento;
	}

	public PessoaFisicaTO getPessoaFisica() {
		return pessoaFisica;
	}

	public void setPessoaFisica(PessoaFisicaTO pessoasFisica) {
		this.pessoaFisica = pessoasFisica;
	}

	public UnidadeTO getUnidade() {
		return unidade;
	}

	public void setUnidade(UnidadeTO unidade) {
		this.unidade = unidade;
	}

	public LocalDateTime getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDateTime dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public LocalDateTime getDataAlteracaoCadastro() {
		return dataAlteracaoCadastro;
	}

	public void setDataAlteracaoCadastro(LocalDateTime dataAlteracaoCadastro) {
		this.dataAlteracaoCadastro = dataAlteracaoCadastro;
	}

	public Boolean getMoraPais() {
		return moraPais;
	}

	public void setMoraPais(Boolean moraPais) {
		this.moraPais = moraPais;
	}

	public Boolean getPaisCasados() {
		return paisCasados;
	}

	public void setPaisCasados(Boolean paisCasados) {
		this.paisCasados = paisCasados;
	}

	public String getMatriculadoEscPub() {
		return matriculadoEscPub;
	}

	public void setMatriculadoEscPub(String matriculadoEscPub) {
		this.matriculadoEscPub = matriculadoEscPub;
	}

	public String getDescBuscaEscola() {
		return descBuscaEscola;
	}

	public void setDescBuscaEscola(String descBuscaEscola) {
		this.descBuscaEscola = descBuscaEscola;
	}

	public String getPublicoPrioritario() {
		return publicoPrioritario;
	}

	public void setPublicoPrioritario(String publicoPrioritario) {
		this.publicoPrioritario = publicoPrioritario;
	}

	public String getMatriculaAluno() {
		return matriculaAluno;
	}

	public void setMatriculaAluno(String matriculaAluno) {
		this.matriculaAluno = matriculaAluno;
	}

	public Long getUsuarioAlteracao() {
		return usuarioAlteracao;
	}

	public void setUsuarioAlteracao(Long usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}

	public List<VulnerabilidadesAlunoTO> getVulnerabilidades() {
		return vulnerabilidades;
	}

	public void setVulnerabilidades(List<VulnerabilidadesAlunoTO> vulnerabilidades) {
		this.vulnerabilidades = vulnerabilidades;
	}
	
	public NiveisTurmasTO getNivelTurma() {
		return nivelTurma;
	}

	public void setNivelTurma(NiveisTurmasTO nivelTurma) {
		this.nivelTurma = nivelTurma;
	}

	public List<EncaminhaAlunosTO> getEncaminhamentos() {
		return encaminhamentos;
	}

	public void setEncaminhamentos(List<EncaminhaAlunosTO> encaminhamentos) {
		this.encaminhamentos = encaminhamentos;
	}


	public ProgramaTO getPrograma() {
		return programa;
	}

	public void setPrograma(ProgramaTO programa) {
		this.programa = programa;
	}

	public ProjetoTO getProjeto() {
		return projeto;
	}

	public void setProjeto(ProjetoTO projeto) {
		this.projeto = projeto;
	}

	public String getObservacaoDeclaracaoPasse() {
		return observacaoDeclaracaoPasse;
	}

	public void setObservacaoDeclaracaoPasse(String observacaoDeclaracaoPasse) {
		this.observacaoDeclaracaoPasse = observacaoDeclaracaoPasse;
	}

	public LocalDateTime getDataDeclaracaoPasse() {
		return dataDeclaracaoPasse;
	}

	public void setDataDeclaracaoPasse(LocalDateTime dataDeclaracaoPasse) {
		this.dataDeclaracaoPasse = dataDeclaracaoPasse;
	}

	public String getObservacaoDeclaracaoMatricula() {
		return observacaoDeclaracaoMatricula;
	}

	public void setObservacaoDeclaracaoMatricula(String observacaoDeclaracaoMatricula) {
		this.observacaoDeclaracaoMatricula = observacaoDeclaracaoMatricula;
	}

	public LocalDateTime getDataDeclaracaoMatricula() {
		return dataDeclaracaoMatricula;
	}

	public void setDataDeclaracaoMatricula(LocalDateTime dataDeclaracaoMatricula) {
		this.dataDeclaracaoMatricula = dataDeclaracaoMatricula;
	}

	public Boolean getParticipaApresentacaoExterna() {
		return participaApresentacaoExterna;
	}

	public void setParticipaApresentacaoExterna(Boolean participaApresentacaoExterna) {
		this.participaApresentacaoExterna = participaApresentacaoExterna;
	}
	
	public TiposPublicoPrioritarioTO getTiposPublicoPrioritario() {
		return tiposPublicoPrioritario;
	}

	public void setTiposPublicoPrioritario(
			TiposPublicoPrioritarioTO tiposPublicoPrioritarioTO) {
		this.tiposPublicoPrioritario = tiposPublicoPrioritarioTO;
	}

	public MotivoDesligamentoTO getMotivoDesligamento() {
		return motivoDesligamento;
	}

	public void setMotivoDesligamento(MotivoDesligamentoTO motivoDesligamentoTO) {
		this.motivoDesligamento = motivoDesligamentoTO;
	}

	public LocalDateTime getDataSugestaoDesligamento() {
		return dataSugestaoDesligamento;
	}

	public void setDataSugestaoDesligamento(LocalDateTime dataSugestaoDesligamento) {
		this.dataSugestaoDesligamento = dataSugestaoDesligamento;
	}

	public String getDescricaoSugestaoDesligamento() {
		return descricaoSugestaoDesligamento;
	}

	public void setDescricaoSugestaoDesligamento(
			String descricaoSugestaoDesligamento) {
		this.descricaoSugestaoDesligamento = descricaoSugestaoDesligamento;
	}

	public List<BeneficioSocialPessoaFisicaTO> getBenefeciosSociaisPessoaFisica() {
		if(benefeciosSociaisPessoaFisica== null) {
			benefeciosSociaisPessoaFisica = new ArrayList<BeneficioSocialPessoaFisicaTO>();
		}
		return benefeciosSociaisPessoaFisica;
	}

	public void setBenefeciosSociaisPessoaFisica(List<BeneficioSocialPessoaFisicaTO> benefeciosSociaisPessoaFisica) {
		this.benefeciosSociaisPessoaFisica = benefeciosSociaisPessoaFisica;

	}

	public Boolean getStAtivo() {
//	public String getStAtivo() {
		return stAtivo;
	}

	public void setStAtivo(Boolean stAtivo) {
//	public void setStAtivo(String stAtivo) {
		this.stAtivo = stAtivo;
	}	

	
	
}
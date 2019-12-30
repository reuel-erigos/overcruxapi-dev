package br.com.crux.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
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


/**
 * The persistent class for the alunos database table.
 * 
 */
@Entity
@Table(name="alunos")
public class Aluno {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_id_aluno")
	@SequenceGenerator(name = "sq_id_aluno", sequenceName = "sq_id_aluno", schema = Constantes.SCHEMA_PUBLIC, initialValue = 1, allocationSize = 1)
	@Column(name="id_aluno", unique=true, nullable=false, precision=10)
	private Long id;

	@Column(name="ds_problema_saude")
	private String descProblemaSaude;

	@Column(name="ds_medicamentos_controlados")
	private String descMedicamentosControlados;

	@Column(name="ds_outras_informacoes")
	private String descOutrasInformacoes;

	@Column(name="ds_forma_ingresso_entidade")
	private String descFormaIngressoEntidade;
	
	//Classificador para indicar se a família já foi atendita por outro órgão da rede
	@Column(name="st_atendido_orgao_rede")
	private String atendidoOrgaoRede;

	@Column(name="dt_entrada")
	private LocalDateTime dataEntrada;
	
	@Column(name="tx_observacoes")
	private String observacoes;
	
	@Column(name="dt_desligamento")
	private LocalDateTime dataDesligamento;
	
	@Column(name="ds_desligamento")
	private String descDesligamento;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_pessoa_fisica")
	private PessoaFisica pessoasFisica;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_unidade")
	private Unidade unidade;
	
	@Column(name="dt_cadastro")
	private LocalDateTime dataCadastro;
	
	@Column(name="dt_alteracao_cadastro")
	private LocalDateTime dataAlteracaoCadastro;
	
	@Column(name="st_mora_pais")
	private String moraPais;
	
	@Column(name="st_pais_casados")
	private String paisCasados;
	
	@Column(name="st_matriculado_esc_pub")
	private String matriculadoEscPub;
		
	@Column(name="ds_busca_escola")
	private String descBuscaEscola;

	@Column(name="st_publico_prioritario")
	private String publicoPrioritario;

	@Column(name="nr_matricula_aluno")
	private String matriculaAluno;
	

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_nivel_turma")
	private NiveisTurmas nivelTurma;	

	@Column(name="id_usuario_apl")
	private Long usuarioAlteracao;


	public Aluno() {
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

	public PessoaFisica getPessoasFisica() {
		return pessoasFisica;
	}

	public void setPessoasFisica(PessoaFisica pessoasFisica) {
		this.pessoasFisica = pessoasFisica;
	}

	public Unidade getUnidade() {
		return unidade;
	}

	public void setUnidade(Unidade unidade) {
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

	public String getMoraPais() {
		return moraPais;
	}

	public void setMoraPais(String moraPais) {
		this.moraPais = moraPais;
	}

	public String getPaisCasados() {
		return paisCasados;
	}

	public void setPaisCasados(String paisCasados) {
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

	public NiveisTurmas getNivelTurma() {
		return nivelTurma;
	}

	public void setNivelTurma(NiveisTurmas nivelTurma) {
		this.nivelTurma = nivelTurma;
	}
	
	

}
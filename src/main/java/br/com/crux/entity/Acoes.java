package br.com.crux.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.crux.infra.constantes.Constantes;


/**
 * The persistent class for the acoes database table.
 * 
 */
@Entity
@Table(name="acoes")
public class Acoes implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_id_acao")
	@SequenceGenerator(name = "sq_id_acao", sequenceName = "sq_id_acao", schema = Constantes.SCHEMA_PUBLIC, initialValue = 1, allocationSize = 1)
	@Column(name="id_acao", unique=true, nullable=false, precision=10)
	private Long id;

	@Column(name="ds_acao")
	private String descricao;	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_atividade")
	private Oficinas oficina;
	
	@Column(name="ds_objetivo_acao")
	private String descricaoObjetivoAcao;
	
	@Column(name="ds_metodologia_acao")
	private String descricaoMetodologiaAcao;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_funcionario_planeja_acao")
	private Funcionario funcionarioPlanejamentoAcao;	
	
	@Column(name="dt_prev_fim_acao")
	private LocalDateTime dataPrevisaoFim;
	
	@Column(name="dt_prev_inicio_acao")
	private LocalDateTime dataPrevisaoInicio;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_funcionario_aprova_acao")
	private Funcionario funcionarioAprovaAcao;	
	
	@Column(name="dt_aprova_acao")
	private LocalDateTime dataAprovaAcao;	

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_funcionario_executa_acao")
	private Funcionario funcionarioExecutaAcao;	
	
	@Column(name="dt_fim_acao")
	private LocalDateTime dataFim;

	@Column(name="dt_inicio_acao")
	private LocalDateTime dataInicio;
	
	@Column(name="ds_avaliacao_acao")
	private String descricaoAvaliacaoAcao;

	@Column(name="ds_ocorrencia_acao")
	private String descricaoOcorrenciaAcao;
		
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_acao")
	private List<MateriaisAcoes> materiaisAcao;

	
	@Column(name="id_usuario_apl")
	private Long usuarioAlteracao;

	public Acoes() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Oficinas getOficina() {
		return oficina;
	}

	public void setOficina(Oficinas atividade) {
		this.oficina = atividade;
	}

	public String getDescricaoObjetivoAcao() {
		return descricaoObjetivoAcao;
	}

	public void setDescricaoObjetivoAcao(String descricaoObjetivoAcao) {
		this.descricaoObjetivoAcao = descricaoObjetivoAcao;
	}

	public String getDescricaoMetodologiaAcao() {
		return descricaoMetodologiaAcao;
	}

	public void setDescricaoMetodologiaAcao(String descricaoMetodologiaAcao) {
		this.descricaoMetodologiaAcao = descricaoMetodologiaAcao;
	}

	public Funcionario getFuncionarioPlanejamentoAcao() {
		return funcionarioPlanejamentoAcao;
	}

	public void setFuncionarioPlanejamentoAcao(Funcionario funcionarioPlanejamentoAcao) {
		this.funcionarioPlanejamentoAcao = funcionarioPlanejamentoAcao;
	}

	public LocalDateTime getDataPrevisaoFim() {
		return dataPrevisaoFim;
	}

	public void setDataPrevisaoFim(LocalDateTime dataPrevisaoFim) {
		this.dataPrevisaoFim = dataPrevisaoFim;
	}

	public LocalDateTime getDataPrevisaoInicio() {
		return dataPrevisaoInicio;
	}

	public void setDataPrevisaoInicio(LocalDateTime dataPrevisaoInicio) {
		this.dataPrevisaoInicio = dataPrevisaoInicio;
	}

	public Funcionario getFuncionarioAprovaAcao() {
		return funcionarioAprovaAcao;
	}

	public void setFuncionarioAprovaAcao(Funcionario funcionarioAprovaAcao) {
		this.funcionarioAprovaAcao = funcionarioAprovaAcao;
	}

	public LocalDateTime getDataAprovaAcao() {
		return dataAprovaAcao;
	}

	public void setDataAprovaAcao(LocalDateTime dataAprovaAcao) {
		this.dataAprovaAcao = dataAprovaAcao;
	}

	public Funcionario getFuncionarioExecutaAcao() {
		return funcionarioExecutaAcao;
	}

	public void setFuncionarioExecutaAcao(Funcionario funcionarioExecutaAcao) {
		this.funcionarioExecutaAcao = funcionarioExecutaAcao;
	}

	public LocalDateTime getDataFim() {
		return dataFim;
	}

	public void setDataFim(LocalDateTime dataFim) {
		this.dataFim = dataFim;
	}

	public LocalDateTime getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(LocalDateTime dataInicio) {
		this.dataInicio = dataInicio;
	}

	public String getDescricaoAvaliacaoAcao() {
		return descricaoAvaliacaoAcao;
	}

	public void setDescricaoAvaliacaoAcao(String descricaoAvaliacaoAcao) {
		this.descricaoAvaliacaoAcao = descricaoAvaliacaoAcao;
	}

	public String getDescricaoOcorrenciaAcao() {
		return descricaoOcorrenciaAcao;
	}

	public void setDescricaoOcorrenciaAcao(String descricaoOcorrenciaAcao) {
		this.descricaoOcorrenciaAcao = descricaoOcorrenciaAcao;
	}

	public Long getUsuarioAlteracao() {
		return usuarioAlteracao;
	}

	public void setUsuarioAlteracao(Long usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}

	public List<MateriaisAcoes> getMateriaisAcao() {
		return materiaisAcao;
	}

	public void setMateriaisAcao(List<MateriaisAcoes> materiaisAcao) {
		this.materiaisAcao = materiaisAcao;
	}

	
}
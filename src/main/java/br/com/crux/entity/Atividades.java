package br.com.crux.entity;

import java.io.Serializable;
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

@Entity
@Table(name = "atividades")
public class Atividades implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_id_atividade")
	@SequenceGenerator(name = "sq_id_atividade", sequenceName = "sq_id_atividade", schema = Constantes.SCHEMA_PUBLIC, initialValue = 1, allocationSize = 1)
	@Column(name = "id_atividade")
	private Long id;

	@Column(name = "ds_atividade")
	private String descricao;

	@Column(name = "ds_local_execucao")
	private String descricaoLocalExecucao;

	@Column(name = "dt_fim_atividade")
	private LocalDateTime dataFim;

	@Column(name = "dt_inicio_atividade")
	private LocalDateTime dataInicio;

	@Column(name = "dt_prev_inicio")
	private LocalDateTime dataPrevisaoInicio;

	@Column(name = "dt_prev_termino")
	private LocalDateTime dataPrevisaoTermino;

	@Column(name = "hr_fim")
	private Long horaFim;

	@Column(name = "hr_inicio")
	private Long horaInicio;

	@Column(name = "nr_aulas")
	private Long numeroAulas;

	@Column(name = "nr_carga_horaria")
	private Long cargaHoraria;

	@Column(name = "nr_maximo_participantes")
	private Long maximoParticipantes;

	@Column(name = "periodo_atividade")
	private Long periodoAtividade;

	@Column(name = "st_domingo")
	private String domingo;

	@Column(name = "st_horario_fixo")
	private String horarioFixo;

	@Column(name = "st_local_execucao")
	private String localExecucao;

	@Column(name = "st_quarta")
	private String quarta;

	@Column(name = "st_quinta")
	private String quinta;

	@Column(name = "st_sabado")
	private String sabado;

	@Column(name = "st_segunda")
	private String segunda;

	@Column(name = "st_sexta")
	private String sexta;

	@Column(name = "st_terca")
	private String terca;

	@Column(name = "tx_observacoes")
	private String observacoes;

	@Column(name = "vl_custo_atividade")
	private Double valorCustoAtividade;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "planos_acao_id_plano_acao")
	private PlanosAcao planosAcao;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "projetos_id_projeto")
	private Projeto projeto;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_programa")
	private Programa programa;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "unidades_id_unidade")
	private Unidade unidade;

	@Column(name = "id_turma")
	private Long idTurma;

	@Column(name = "id_usuario_apl")
	private Long usuarioAlteracao;

	public Atividades() {
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

	public String getDescricaoLocalExecucao() {
		return descricaoLocalExecucao;
	}

	public void setDescricaoLocalExecucao(String descricaoLocalExecucao) {
		this.descricaoLocalExecucao = descricaoLocalExecucao;
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

	public LocalDateTime getDataPrevisaoInicio() {
		return dataPrevisaoInicio;
	}

	public void setDataPrevisaoInicio(LocalDateTime dataPrevisaoInicio) {
		this.dataPrevisaoInicio = dataPrevisaoInicio;
	}

	public LocalDateTime getDataPrevisaoTermino() {
		return dataPrevisaoTermino;
	}

	public void setDataPrevisaoTermino(LocalDateTime dataPrevisaoTermino) {
		this.dataPrevisaoTermino = dataPrevisaoTermino;
	}

	public Long getHoraFim() {
		return horaFim;
	}

	public void setHoraFim(Long horaFim) {
		this.horaFim = horaFim;
	}

	public Long getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(Long horaInicio) {
		this.horaInicio = horaInicio;
	}

	public Long getNumeroAulas() {
		return numeroAulas;
	}

	public void setNumeroAulas(Long numeroAulas) {
		this.numeroAulas = numeroAulas;
	}

	public Long getCargaHoraria() {
		return cargaHoraria;
	}

	public void setCargaHoraria(Long cargaHoraria) {
		this.cargaHoraria = cargaHoraria;
	}

	public Long getMaximoParticipantes() {
		return maximoParticipantes;
	}

	public void setMaximoParticipantes(Long maximoParticipantes) {
		this.maximoParticipantes = maximoParticipantes;
	}

	public Long getPeriodoAtividade() {
		return periodoAtividade;
	}

	public void setPeriodoAtividade(Long periodoAtividade) {
		this.periodoAtividade = periodoAtividade;
	}

	public String getDomingo() {
		return domingo;
	}

	public void setDomingo(String domingo) {
		this.domingo = domingo;
	}

	public String getHorarioFixo() {
		return horarioFixo;
	}

	public void setHorarioFixo(String horarioFixo) {
		this.horarioFixo = horarioFixo;
	}

	public String getLocalExecucao() {
		return localExecucao;
	}

	public void setLocalExecucao(String localExecucao) {
		this.localExecucao = localExecucao;
	}

	public String getQuarta() {
		return quarta;
	}

	public void setQuarta(String quarta) {
		this.quarta = quarta;
	}

	public String getQuinta() {
		return quinta;
	}

	public void setQuinta(String quinta) {
		this.quinta = quinta;
	}

	public String getSabado() {
		return sabado;
	}

	public void setSabado(String sabado) {
		this.sabado = sabado;
	}

	public String getSegunda() {
		return segunda;
	}

	public void setSegunda(String segunda) {
		this.segunda = segunda;
	}

	public String getSexta() {
		return sexta;
	}

	public void setSexta(String sexta) {
		this.sexta = sexta;
	}

	public String getTerca() {
		return terca;
	}

	public void setTerca(String terca) {
		this.terca = terca;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public Double getValorCustoAtividade() {
		return valorCustoAtividade;
	}

	public void setValorCustoAtividade(Double valorCustoAtividade) {
		this.valorCustoAtividade = valorCustoAtividade;
	}

	public PlanosAcao getPlanosAcao() {
		return planosAcao;
	}

	public void setPlanosAcao(PlanosAcao planosAcao) {
		this.planosAcao = planosAcao;
	}

	public Projeto getProjeto() {
		return projeto;
	}

	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}

	public Unidade getUnidade() {
		return unidade;
	}

	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
	}

	public Long getUsuarioAlteracao() {
		return usuarioAlteracao;
	}

	public void setUsuarioAlteracao(Long usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}

	public Programa getPrograma() {
		return programa;
	}

	public void setPrograma(Programa programa) {
		this.programa = programa;
	}

	public Long getIdTurma() {
		return idTurma;
	}

	public void setIdTurma(Long idTurma) {
		this.idTurma = idTurma;
	}

	
	
}
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

@Entity
@Table(name = "turmas")
public class Turmas  {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_id_turma")
	@SequenceGenerator(name = "sq_id_turma", sequenceName = "sq_id_turma", schema = Constantes.SCHEMA_PUBLIC, initialValue = 1, allocationSize = 1)
	@Column(name = "id_turma")
	private Long id;
	
	@Column(name = "ds_turma")
	private String descricao;
	
	@Column(name="dt_prev_inicio")
	private LocalDateTime dataPrevisaoInicio;
	
	@Column(name="dt_prev_termino")
	private LocalDateTime dataPrevisaoTermino;
	
	@Column(name="dt_inicio_turma")
	private LocalDateTime dataInicioTurma;
	
	@Column(name="dt_fim_turma")
	private LocalDateTime dataFimTurma;
	
	@Column(name = "hr_inicio")
	private Long horaInicio;	
	
	@Column(name = "hr_fim")
	private Long horaFim;	
	
	@Column(name = "nr_maximo_participantes")
	private Long numeroMaximoParticipantes;	
	
	@Column(name = "nr_carga_horaria")
	private Long numeroCargaHoraria;	
	
	@Column(name = "tx_observacoes")
	private String observacao;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_programa")
	private Programa programa;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_projeto")
	private Projeto projeto;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_unidade")
	private Unidade unidade;


	//M = MATUTINO, V = VESPERTINO, N = NOTURNO, O = OUTRO
	@Column(name = "cs_turno")
	private String turno;
	
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_nivel_turma")
	private NiveisTurmas niveisTurma;
	
	
	@Column(name="id_usuario_apl")
	private Long usuarioAlteracao;
	
	public Turmas() {
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

	public LocalDateTime getDataInicioTurma() {
		return dataInicioTurma;
	}

	public void setDataInicioTurma(LocalDateTime dataInicioTurma) {
		this.dataInicioTurma = dataInicioTurma;
	}

	public LocalDateTime getDataFimTurma() {
		return dataFimTurma;
	}

	public void setDataFimTurma(LocalDateTime dataFimTurma) {
		this.dataFimTurma = dataFimTurma;
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

	public Long getNumeroMaximoParticipantes() {
		return numeroMaximoParticipantes;
	}

	public void setNumeroMaximoParticipantes(Long numeroMaximoParticipantes) {
		this.numeroMaximoParticipantes = numeroMaximoParticipantes;
	}

	public Long getNumeroCargaHoraria() {
		return numeroCargaHoraria;
	}

	public void setNumeroCargaHoraria(Long numeroCargaHoraria) {
		this.numeroCargaHoraria = numeroCargaHoraria;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Programa getPrograma() {
		return programa;
	}

	public void setPrograma(Programa programa) {
		this.programa = programa;
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

	public String getTurno() {
		return turno;
	}

	public void setTurno(String turno) {
		this.turno = turno;
	}

	public NiveisTurmas getNiveisTurma() {
		return niveisTurma;
	}

	public void setNiveisTurma(NiveisTurmas niveisTurma) {
		this.niveisTurma = niveisTurma;
	}

	public Long getUsuarioAlteracao() {
		return usuarioAlteracao;
	}

	public void setUsuarioAlteracao(Long usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}

	
	
}
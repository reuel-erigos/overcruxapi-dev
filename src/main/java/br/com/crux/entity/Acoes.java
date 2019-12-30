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

	@Column(name="dt_fim_acao")
	private LocalDateTime dataFim;

	@Column(name="dt_inicio_acao")
	private LocalDateTime dataInicio;

	@Column(name="dt_prev_fim_acao")
	private LocalDateTime dataPrevisaoFim;

	@Column(name="dt_prev_inicio_acao")
	private LocalDateTime dataPrevisaoInicio;

	@Column(name="nm_plano_acao")
	private String nome;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="atividades_id_atividade")
	private Atividades atividade;

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

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Atividades getAtividade() {
		return atividade;
	}

	public void setAtividade(Atividades atividade) {
		this.atividade = atividade;
	}

	public Long getUsuarioAlteracao() {
		return usuarioAlteracao;
	}

	public void setUsuarioAlteracao(Long usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}

	
}
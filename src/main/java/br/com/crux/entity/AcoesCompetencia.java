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
 * The persistent class for the acoes_competencias database table.
 * 
 */
@Entity
@Table(name = "acoes_competencias")
public class AcoesCompetencia {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_id_acao_competencia")
	@SequenceGenerator(name = "sq_id_acao_competencia", sequenceName = "sq_id_acao_competencia", schema = Constantes.SCHEMA_PUBLIC, initialValue = 1, allocationSize = 1)
	@Column(name = "id_acao_competencia", unique = true, nullable = false, precision = 10)
	private Long id;

	@Column(name = "ds_acao_competencia")
	private String descricao;

	@Column(name = "ds_resultado_acao")
	private String resultadoAcao;

	@Column(name = "ds_resultado_prev_acao")
	private String resultadoPrevAcao;

	@Column(name = "dt_fim_acao")
	private LocalDateTime dataFim;

	@Column(name = "dt_inicio_acao")
	private LocalDateTime dataInicio;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "talentos_pf_id_talento_pf")
	private TalentosPf talentosPf;

	@Column(name = "id_usuario_apl")
	private Long usuarioAlteracao;

	public AcoesCompetencia() {
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

	public String getResultadoAcao() {
		return resultadoAcao;
	}

	public void setResultadoAcao(String resultadoAcao) {
		this.resultadoAcao = resultadoAcao;
	}

	public String getResultadoPrevAcao() {
		return resultadoPrevAcao;
	}

	public void setResultadoPrevAcao(String resultadoPrevAcao) {
		this.resultadoPrevAcao = resultadoPrevAcao;
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

	public TalentosPf getTalentosPf() {
		return talentosPf;
	}

	public void setTalentosPf(TalentosPf talentosPf) {
		this.talentosPf = talentosPf;
	}

	public Long getUsuarioAlteracao() {
		return usuarioAlteracao;
	}

	public void setUsuarioAlteracao(Long usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}

}
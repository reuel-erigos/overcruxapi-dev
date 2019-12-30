package br.com.crux.to;

import java.time.LocalDateTime;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import br.com.crux.infra.adapter.LocalDateTimeAdapter;

public class AcoesCompetenciaTO {

	private Long id;
	private String descricao;
	private String resultadoAcao;
	private String resultadoPrevAcao;

	@XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
	private LocalDateTime dataFim;
	@XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
	private LocalDateTime dataInicio;

	private TalentosPfTO talentosPf;
	private Long usuarioAlteracao;

	public AcoesCompetenciaTO() {
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

	public TalentosPfTO getTalentosPf() {
		return talentosPf;
	}

	public void setTalentosPf(TalentosPfTO talentosPf) {
		this.talentosPf = talentosPf;
	}

	public Long getUsuarioAlteracao() {
		return usuarioAlteracao;
	}

	public void setUsuarioAlteracao(Long usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}

}
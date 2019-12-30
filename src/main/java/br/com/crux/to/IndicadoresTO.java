package br.com.crux.to;

import java.time.LocalDateTime;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import br.com.crux.infra.adapter.LocalDateTimeAdapter;

public class IndicadoresTO {

	private Long idIndicador;
	private String nome;
	private ObjetivoTO objetivo;
	private Long usuarioAlteracao;

	@XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
	private LocalDateTime dataFim;
	@XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
	private LocalDateTime dataInicio;

	public IndicadoresTO() {
	}

	public Long getIdIndicador() {
		return idIndicador;
	}

	public void setIdIndicador(Long idIndicador) {
		this.idIndicador = idIndicador;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public ObjetivoTO getObjetivo() {
		return objetivo;
	}

	public void setObjetivo(ObjetivoTO objetivo) {
		this.objetivo = objetivo;
	}

	public Long getUsuarioAlteracao() {
		return usuarioAlteracao;
	}

	public void setUsuarioAlteracao(Long usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
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

}
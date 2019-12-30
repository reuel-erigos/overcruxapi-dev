package br.com.crux.to;

import java.time.LocalDateTime;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import br.com.crux.infra.adapter.LocalDateTimeAdapter;

public class MetasTO {

	private Long id;
	private String descricao;
	private String nome;
	private IndicadoresTO indicadores;
	private Long usuarioAlteracao;

	@XmlJavaTypeAdapter(LocalDateTimeAdapter.class) 
	private LocalDateTime dataFim;
	@XmlJavaTypeAdapter(LocalDateTimeAdapter.class) 
	private LocalDateTime dataInicio;

	private Long qtdMetas;

	private String descricaoFormula;

	public MetasTO() {
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

	public IndicadoresTO getIndicadores() {
		return indicadores;
	}

	public void setIndicadores(IndicadoresTO indicadores) {
		this.indicadores = indicadores;
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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Long getQtdMetas() {
		return qtdMetas;
	}

	public void setQtdMetas(Long qtdMetas) {
		this.qtdMetas = qtdMetas;
	}

	public String getDescricaoFormula() {
		return descricaoFormula;
	}

	public void setDescricaoFormula(String descricaoFormula) {
		this.descricaoFormula = descricaoFormula;
	}
	
	

}
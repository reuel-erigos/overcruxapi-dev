package br.com.crux.to;

import java.time.LocalDateTime;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import br.com.crux.infra.adapter.LocalDateTimeAdapter;

public class AcaoTO {

	private Long id;
	
	@XmlJavaTypeAdapter(LocalDateTimeAdapter.class) 
	private LocalDateTime dataFim;
	
	@XmlJavaTypeAdapter(LocalDateTimeAdapter.class) 
	private LocalDateTime dataInicio;
	
	@XmlJavaTypeAdapter(LocalDateTimeAdapter.class) 
	private LocalDateTime dataPrevisaoFim;
	
	@XmlJavaTypeAdapter(LocalDateTimeAdapter.class) 
	private LocalDateTime dataPrevisaoInicio;
	
	private String nome;
	private AtividadesTO atividade;
	private Long usuarioAlteracao;

	public AcaoTO() {
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

	public AtividadesTO getAtividade() {
		return atividade;
	}

	public void setAtividade(AtividadesTO atividade) {
		this.atividade = atividade;
	}

	public Long getUsuarioAlteracao() {
		return usuarioAlteracao;
	}

	public void setUsuarioAlteracao(Long usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}

}

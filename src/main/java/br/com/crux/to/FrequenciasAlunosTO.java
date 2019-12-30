package br.com.crux.to;

import java.time.LocalDateTime;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import br.com.crux.infra.adapter.LocalDateTimeAdapter;


public class FrequenciasAlunosTO {

	private Long id;
	private String justificativa;
	
	@XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
	private LocalDateTime dataFrequencia;
	private Boolean frequencia;
	
	private AtividadesAlunoTO atividadesAluno;
	private Long usuarioAlteracao;
	
	public FrequenciasAlunosTO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getJustificativa() {
		return justificativa;
	}

	public void setJustificativa(String justificativa) {
		this.justificativa = justificativa;
	}

	public LocalDateTime getDataFrequencia() {
		return dataFrequencia;
	}

	public void setDataFrequencia(LocalDateTime dataFrequencia) {
		this.dataFrequencia = dataFrequencia;
	}

	public AtividadesAlunoTO getAtividadesAluno() {
		return atividadesAluno;
	}

	public void setAtividadesAluno(AtividadesAlunoTO atividadesAluno) {
		this.atividadesAluno = atividadesAluno;
	}

	public Long getUsuarioAlteracao() {
		return usuarioAlteracao;
	}

	public void setUsuarioAlteracao(Long usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}

	public Boolean getFrequencia() {
		return frequencia;
	}

	public void setFrequencia(Boolean frequencia) {
		this.frequencia = frequencia;
	}


}
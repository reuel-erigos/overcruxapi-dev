package br.com.crux.to;

import java.time.LocalDateTime;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.crux.infra.adapter.LocalDateTimeAdapter;


@JsonIgnoreProperties(ignoreUnknown = true)
public class EncaminhaAlunosTO  {

	private Long id;
	private String descricao;
	
	@XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
	private LocalDateTime dataEncaminhaAluno;
	
	private Long idAluno;
	private EntidadesSociaisTO entidadeSocial;
	private Long usuarioAlteracao;

	public EncaminhaAlunosTO() {
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

	public LocalDateTime getDataEncaminhaAluno() {
		return dataEncaminhaAluno;
	}

	public void setDataEncaminhaAluno(LocalDateTime dataEncaminhaAluno) {
		this.dataEncaminhaAluno = dataEncaminhaAluno;
	}

	public Long getIdAluno() {
		return idAluno;
	}

	public void setIdAluno(Long idAluno) {
		this.idAluno = idAluno;
	}

	public EntidadesSociaisTO getEntidadeSocial() {
		return entidadeSocial;
	}

	public void setEntidadeSocial(EntidadesSociaisTO entidadesSociai) {
		this.entidadeSocial = entidadesSociai;
	}

	public Long getUsuarioAlteracao() {
		return usuarioAlteracao;
	}

	public void setUsuarioAlteracao(Long usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}


}
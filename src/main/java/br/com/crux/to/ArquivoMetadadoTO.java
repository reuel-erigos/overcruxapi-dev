package br.com.crux.to;

import java.io.Serializable;
import java.time.LocalDateTime;


public class ArquivoMetadadoTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String nmArquivo;
	private String dsTipoArquivo;
	private Long nrTamanhoArquivo;
	private LocalDateTime dtCriacao;
	private String hash;
	private Long idInstituicao;
	private Long usuarioAlteracao;

	
	public ArquivoMetadadoTO() {
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getNmArquivo() {
		return nmArquivo;
	}


	public void setNmArquivo(String nmArquivo) {
		this.nmArquivo = nmArquivo;
	}


	public String getDsTipoArquivo() {
		return dsTipoArquivo;
	}


	public void setDsTipoArquivo(String dsTipoArquivo) {
		this.dsTipoArquivo = dsTipoArquivo;
	}


	public Long getNrTamanhoArquivo() {
		return nrTamanhoArquivo;
	}


	public void setNrTamanhoArquivo(Long nrTamanhoArquivo) {
		this.nrTamanhoArquivo = nrTamanhoArquivo;
	}


	public LocalDateTime getDtCriacao() {
		return dtCriacao;
	}


	public void setDtCriacao(LocalDateTime dtCriacao) {
		this.dtCriacao = dtCriacao;
	}


	public String getHash() {
		return hash;
	}


	public void setHash(String hash) {
		this.hash = hash;
	}


	public Long getIdInstituicao() {
		return idInstituicao;
	}


	public void setIdInstituicao(Long idInstituicao) {
		this.idInstituicao = idInstituicao;
	}


	public Long getUsuarioAlteracao() {
		return usuarioAlteracao;
	}


	public void setUsuarioAlteracao(Long usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}

	

}
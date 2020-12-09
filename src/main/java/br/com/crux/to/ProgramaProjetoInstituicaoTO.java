package br.com.crux.to;

import java.time.LocalDateTime;

public class ProgramaProjetoInstituicaoTO {

	private Long id;
	private String nomeProgramaProjeto;
	private LocalDateTime dataImplantacao;
	private LocalDateTime dataTermino;
	private String tipo;
	private Long idInstituicao;
	
	public ProgramaProjetoInstituicaoTO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeProgramaProjeto() {
		return nomeProgramaProjeto;
	}

	public void setNomeProgramaProjeto(String nomeProgramaProjeto) {
		this.nomeProgramaProjeto = nomeProgramaProjeto;
	}

	public LocalDateTime getDataImplantacao() {
		return dataImplantacao;
	}

	public void setDataImplantacao(LocalDateTime dataImplantacao) {
		this.dataImplantacao = dataImplantacao;
	}

	public LocalDateTime getDataTermino() {
		return dataTermino;
	}

	public void setDataTermino(LocalDateTime dataTermino) {
		this.dataTermino = dataTermino;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Long getIdInstituicao() {
		return idInstituicao;
	}

	public void setIdInstituicao(Long idInstituicao) {
		this.idInstituicao = idInstituicao;
	}
	
	
}

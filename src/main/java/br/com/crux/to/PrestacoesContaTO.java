package br.com.crux.to;

import java.time.LocalDateTime;


public class PrestacoesContaTO {

	private Long id;
	private String descricaoAnalise;
	private String descricaoOcorrencias;
	private LocalDateTime dataFim;
	private LocalDateTime dataInicio;
	private ProgramaTO programa;
	private ProjetoTO projeto;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricaoAnalise() {
		return descricaoAnalise;
	}

	public void setDescricaoAnalise(String descricaoAnalise) {
		this.descricaoAnalise = descricaoAnalise;
	}

	public String getDescricaoOcorrencias() {
		return descricaoOcorrencias;
	}

	public void setDescricaoOcorrencias(String descricaoOcorrencias) {
		this.descricaoOcorrencias = descricaoOcorrencias;
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

	public ProgramaTO getPrograma() {
		return programa;
	}

	public void setPrograma(ProgramaTO programa) {
		this.programa = programa;
	}

	public ProjetoTO getProjeto() {
		return projeto;
	}

	public void setProjeto(ProjetoTO projeto) {
		this.projeto = projeto;
	}

}

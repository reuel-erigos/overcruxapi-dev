package br.com.crux.to;

import java.time.LocalDateTime;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import br.com.crux.infra.adapter.LocalDateTimeAdapter;


public class ObjetivoTO {

	private Long idObjetivo;
	private String nome;
	private PerspectivaTO perspectiva;
	private Long usuarioAlteracao;

	@XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
	private LocalDateTime dataImplantacao;

	@XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
	private LocalDateTime dataTermino;
	
	
	public ObjetivoTO() {
	}

	
	public Long getIdObjetivo() {
		return idObjetivo;
	}

	public void setIdObjetivo(Long idObjetivo) {
		this.idObjetivo = idObjetivo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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

	public PerspectivaTO getPerspectiva() {
		return perspectiva;
	}

	public void setPerspectiva(PerspectivaTO perspectiva) {
		this.perspectiva = perspectiva;
	}

	public Long getUsuarioAlteracao() {
		return usuarioAlteracao;
	}

	public void setUsuarioAlteracao(Long usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}
	

}
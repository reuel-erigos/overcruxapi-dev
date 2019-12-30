package br.com.crux.to;

import java.time.LocalDateTime;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import br.com.crux.infra.adapter.LocalDateTimeAdapter;


public class PerspectivaTO {

	private Long idPerspectiva;
	private String nmPerspectiva;
	
	@XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
	private LocalDateTime dtImplantacao;
	
	@XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
	private LocalDateTime dtTermino;
	
	private UnidadeTO unidade;
	private Long usuariosAlteracao;


	public PerspectivaTO() {
	}

	public Long getIdPerspectiva() {
		return this.idPerspectiva;
	}

	public void setIdPerspectiva(Long idPerspectiva) {
		this.idPerspectiva = idPerspectiva;
	}

	public LocalDateTime getDtImplantacao() {
		return this.dtImplantacao;
	}

	public void setDtImplantacao(LocalDateTime dtImplantacao) {
		this.dtImplantacao = dtImplantacao;
	}

	public LocalDateTime getDtTermino() {
		return this.dtTermino;
	}

	public void setDtTermino(LocalDateTime dtTermino) {
		this.dtTermino = dtTermino;
	}

	public String getNmPerspectiva() {
		return this.nmPerspectiva;
	}

	public void setNmPerspectiva(String nmPerspectiva) {
		this.nmPerspectiva = nmPerspectiva;
	}

	public UnidadeTO getUnidade() {
		return this.unidade;
	}

	public void setUnidade(UnidadeTO unidade) {
		this.unidade = unidade;
	}

	public Long getUsuariosAlteracao() {
		return this.usuariosAlteracao;
	}

	public void setUsuariosSistema(Long usuariosAlteracao) {
		this.usuariosAlteracao = usuariosAlteracao;
	}

}
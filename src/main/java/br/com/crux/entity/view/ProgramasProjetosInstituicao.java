package br.com.crux.entity.view;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

@Entity
@Immutable
@Table(name = "vw_programas_projetos_instituicao")
public class ProgramasProjetosInstituicao {

	@Id
	@Column(name = "id")
	private Long id;
	
	@Column(name = "nm_programa_projeto")
	private String nomeProgramaProjeto;
	
	@Column(name = "dt_implantacao")
	private LocalDateTime dataImplantacao;

	@Column(name = "dt_termino")
	private LocalDateTime dataTermino;

	@Column(name = "tipo")
	private String tipo;
	
	@Column(name = "id_instituicao")
	private Long idInstituicao;

	
	public ProgramasProjetosInstituicao() {
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

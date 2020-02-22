package br.com.crux.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.crux.infra.constantes.Constantes;

@Entity
@Table(name = "parcerias_categorias")
public class ParceriasCategorias implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id 
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_id_parceria_categoria") 
	@SequenceGenerator(name = "sq_id_parceria_categoria", sequenceName = "sq_id_parceria_categoria", schema = Constantes.SCHEMA_PUBLIC, initialValue = 1, allocationSize = 1) @Column(name = "id_parceria_categoria") private Long id;

	@ManyToOne 
	@JoinColumn(name = "id_parceria_programa") 
	private ParceriasPrograma parceriasPrograma;

	@ManyToOne 
	@JoinColumn(name = "id_parceria_projeto") 
	private ParceriasProjeto parceriasProjeto;

	@ManyToOne 
	@JoinColumn(name = "id_categoria") 
	private CategoriasContabeis categoriasContabeis;

	@Column(name = "vl_parceria_categoria") 
	private Double valorParceriaCategoria;

	@Column(name = "id_usuario_apl") 
	private Long usuarioAlteracao;

	public ParceriasCategorias() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUsuarioAlteracao() {
		return usuarioAlteracao;
	}

	public void setUsuarioAlteracao(Long usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}

	public ParceriasPrograma getParceriasPrograma() {
		return parceriasPrograma;
	}

	public void setParceriasPrograma(ParceriasPrograma parceriasPrograma) {
		this.parceriasPrograma = parceriasPrograma;
	}

	public ParceriasProjeto getParceriasProjeto() {
		return parceriasProjeto;
	}

	public void setParceriasProjeto(ParceriasProjeto parceriasProjeto) {
		this.parceriasProjeto = parceriasProjeto;
	}

	public CategoriasContabeis getCategoriasContabeis() {
		return categoriasContabeis;
	}

	public void setCategoriasContabeis(CategoriasContabeis categoriasContabeis) {
		this.categoriasContabeis = categoriasContabeis;
	}

	public Double getValorParceriaCategoria() {
		return valorParceriaCategoria;
	}

	public void setValorParceriaCategoria(Double valorParceriaCategoria) {
		this.valorParceriaCategoria = valorParceriaCategoria;
	}

}
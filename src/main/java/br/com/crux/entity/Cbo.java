package br.com.crux.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.crux.infra.constantes.Constantes;


@Entity
@Table(name="CBO")
public class Cbo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_id_cbo")
	@SequenceGenerator(name = "sq_id_cbo", sequenceName = "sq_id_cbo", schema = Constantes.SCHEMA_PUBLIC, initialValue = 1, allocationSize = 1)
	@Column(name="id_cbo")
	private Long id;

	@Column(name="nr_cbo")
	private Long numero;

	@Column(name="nm_titulo")
	private String nomeTitulo;

	@Column(name="ds_tipo")
	private String descricaoTipo;

	@Column(name="id_usuario_apl")
	private Long usuarioAlteracao;

	public Cbo() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long idCbo) {
		this.id = idCbo;
	}

	public Long getNumero() {
		return numero;
	}

	public void setNumero(Long numero) {
		this.numero = numero;
	}

	public String getNomeTitulo() {
		return nomeTitulo;
	}

	public void setNomeTitulo(String nomeTitulo) {
		this.nomeTitulo = nomeTitulo;
	}

	public String getDescricaoTipo() {
		return descricaoTipo;
	}

	public void setDescricaoTipo(String descricaoTipo) {
		this.descricaoTipo = descricaoTipo;
	}

	public Long getUsuarioAlteracao() {
		return usuarioAlteracao;
	}

	public void setUsuarioAlteracao(Long usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}


}
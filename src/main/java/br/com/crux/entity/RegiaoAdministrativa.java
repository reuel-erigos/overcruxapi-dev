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


/**
 * The persistent class for the acoes database table.
 * 
 */
@Entity
@Table(name="regioes_administrativas")
public class RegiaoAdministrativa implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_id_ra")
	@SequenceGenerator(name = "sq_id_ra", sequenceName = "sq_id_ra", schema = Constantes.SCHEMA_PUBLIC, initialValue = 1, allocationSize = 1)
	@Column(name="id_ra", unique=true, nullable=false, precision=10)
	private Long id;

	@Column(name="nr_ra")
	private String numero;	
	
	@Column(name="nm_ra")
	private String nome;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
}
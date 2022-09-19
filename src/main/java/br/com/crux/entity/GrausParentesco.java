package br.com.crux.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.crux.infra.constantes.Constantes;

@Entity
@Table(name = "graus_parentesco")
public class GrausParentesco {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_id_grau_parentesco")
	@SequenceGenerator(name = "sq_id_grau_parentesco", sequenceName = "sq_id_grau_parentesco", schema = Constantes.SCHEMA_PUBLIC, initialValue = 1, allocationSize = 1)
	@Column(name = "id_grau_parentesco")
	private Long id;

	@Column(name = "nm_grau_parentesco")
	private String nome;

	public GrausParentesco() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
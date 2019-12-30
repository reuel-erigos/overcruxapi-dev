package br.com.crux.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.crux.infra.constantes.Constantes;

/**
 * The persistent class for the condicoes_moradia database table.
 * 
 */
@Entity
@Table(name = "condicoes_moradia")
public class CondicoesMoradia {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_id_condicao_moradia")
	@SequenceGenerator(name = "sq_id_condicao_moradia", sequenceName = "sq_id_condicao_moradia", schema = Constantes.SCHEMA_PUBLIC, initialValue = 1, allocationSize = 1)
	@Column(name = "id_condicao_moradia")
	private Long id;

	@Column(name = "ds_condicao_moradia")
	private String descricao;

	@Column(name = "id_usuario_apl")
	private Long usuarioAlteracao;

	public CondicoesMoradia() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Long getUsuarioAlteracao() {
		return usuarioAlteracao;
	}

	public void setUsuarioAlteracao(Long usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}

}
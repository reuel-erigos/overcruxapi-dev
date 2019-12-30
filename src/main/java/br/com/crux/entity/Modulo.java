package br.com.crux.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.crux.infra.constantes.Constantes;


/**
 * The persistent class for the modulos database table.
 * 
 */
@Entity
@Table(name="modulos")
public class Modulo  {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_id_modulo")
	@SequenceGenerator(name = "sq_id_modulo", sequenceName = "sq_id_modulo", schema = Constantes.SCHEMA_PUBLIC, initialValue = 1, allocationSize = 1)
	@Column(name="id_modulo")
	private Long id;

	@Column(name="ds_modulo")
	private String descricao;

	@Column(name="nm_modulo")
	private String nome;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="modulo_pai")
	private Modulo moduloPai;

	@Column(name="id_usuario_apl")
	private Long usuarioAlteracao;

	public Modulo() {
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

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Modulo getModuloPai() {
		return moduloPai;
	}

	public void setModuloPai(Modulo moduloPai) {
		this.moduloPai = moduloPai;
	}

	public Long getUsuarioAlteracao() {
		return usuarioAlteracao;
	}

	public void setUsuarioAlteracao(Long usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}


}
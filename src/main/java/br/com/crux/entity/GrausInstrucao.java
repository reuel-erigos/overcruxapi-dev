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
@Table(name = "graus_instrucao")
public class GrausInstrucao {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_id_grau_instrucao")
	@SequenceGenerator(name = "sq_id_grau_instrucao", sequenceName = "sq_id_grau_instrucao", schema = Constantes.SCHEMA_PUBLIC, initialValue = 1, allocationSize = 1)
	@Column(name = "id_grau_instrucao")
	private Long id;

	@Column(name = "ds_grau_instrucao")
	private String descricao;

	@Column(name = "id_usuario_apl")
	private Long usuarioAlteracao;

	public GrausInstrucao() {
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
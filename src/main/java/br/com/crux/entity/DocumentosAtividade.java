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
 * The persistent class for the documentos_atividades database table.
 * 
 */
@Entity
@Table(name = "documentos_atividades")
public class DocumentosAtividade {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_id_documento_atividade")
	@SequenceGenerator(name = "sq_id_documento_atividade", sequenceName = "sq_id_documento_atividade", schema = Constantes.SCHEMA_PUBLIC, initialValue = 1, allocationSize = 1)
	@Column(name = "id_documento_atividade")
	private Long id;

	@Column(name = "ds_documento_atividade")
	private String descricao;

	@Column(name = "tx_documento_atividade")
	private String observacao;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_atividade")
	private Atividades atividade;

	@Column(name = "id_usuario_apl")
	private Long usuarioAlteracao;

	public DocumentosAtividade() {
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

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Atividades getAtividade() {
		return atividade;
	}

	public void setAtividade(Atividades atividade) {
		this.atividade = atividade;
	}

	public Long getUsuarioAlteracao() {
		return usuarioAlteracao;
	}

	public void setUsuarioAlteracao(Long usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}

}
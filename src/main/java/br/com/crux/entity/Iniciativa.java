package br.com.crux.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

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
 * The persistent class for the iniciativas database table.
 * 
 */
@Entity
@Table(name = "iniciativas")
public class Iniciativa implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_id_iniciativa")
	@SequenceGenerator(name = "sq_id_iniciativa", sequenceName = "sq_id_iniciativa", schema = Constantes.SCHEMA_PUBLIC, initialValue = 1, allocationSize = 1)
	@Column(name = "id_iniciativa")
	private Long id;

	@Column(name = "ds_iniciativa")
	private String nome;

	@Column(name = "dt_fim_iniciativa")
	private LocalDateTime dataFim;

	@Column(name = "dt_inicio_iniciativa")
	private LocalDateTime dataInicio;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "metas_id_meta")
	private Metas meta;

	@Column(name = "id_usuario_apl")
	private Long usuarioAlteracao;

	public Iniciativa() {
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

	public LocalDateTime getDataFim() {
		return dataFim;
	}

	public void setDataFim(LocalDateTime dataFim) {
		this.dataFim = dataFim;
	}

	public LocalDateTime getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(LocalDateTime dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Metas getMeta() {
		return meta;
	}

	public void setMeta(Metas meta) {
		this.meta = meta;
	}

	public Long getUsuarioAlteracao() {
		return usuarioAlteracao;
	}

	public void setUsuarioAlteracao(Long usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}

}
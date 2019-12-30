package br.com.crux.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.crux.infra.constantes.Constantes;



@Entity
@Table(name="avaliacoes")
public class Avaliacoes {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_id_avaliacao")
	@SequenceGenerator(name = "sq_id_avaliacao", sequenceName = "sq_id_avaliacao", schema = Constantes.SCHEMA_PUBLIC, initialValue = 1, allocationSize = 1)
	@Column(name="id_avaliacao")
	private Long id;

	@Column(name="ds_avaliacao")
	private String descricao;

	@Column(name="dt_fim_avaliacao")
	private LocalDateTime dataFim;

	@Column(name="dt_inicio_avaliacao")
	private LocalDateTime dataInicio;

	@Column(name="nm_avaliacao")
	private String nome;

	@Column(name="id_usuario_apl")
	private Long usuarioAlteracao;

	public Avaliacoes() {
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

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Long getUsuarioAlteracao() {
		return usuarioAlteracao;
	}

	public void setUsuarioAlteracao(Long usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}


}
package br.com.crux.entity;

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


@Entity
@Table(name="uniformes_aluno")
public class UniformesAluno {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_id_uniforme_aluno")
	@SequenceGenerator(name = "sq_id_uniforme_aluno", sequenceName = "sq_id_uniforme_aluno", schema = Constantes.SCHEMA_PUBLIC, initialValue = 1, allocationSize = 1)
	@Column(name="id_uniforme_aluno")
	private Long id;

	@Column(name="dt_uniforme_entregue")
	private LocalDateTime dataUniformeEntregue;

	@Column(name="nm_uniforme")
	private String nomeUniforme;

	@Column(name="qt_uniforme_entregue")
	private Long qtdUniformeEntregue;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_atividade_aluno")
	private AtividadesAluno atividadesAluno;

	@Column(name="id_usuario_apl")
	private Long usuarioAlteracao;

	public UniformesAluno() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getDataUniformeEntregue() {
		return dataUniformeEntregue;
	}

	public void setDataUniformeEntregue(LocalDateTime dataUniformeEntregue) {
		this.dataUniformeEntregue = dataUniformeEntregue;
	}

	public String getNomeUniforme() {
		return nomeUniforme;
	}

	public void setNomeUniforme(String nomeUniforme) {
		this.nomeUniforme = nomeUniforme;
	}

	public Long getQtdUniformeEntregue() {
		return qtdUniformeEntregue;
	}

	public void setQtdUniformeEntregue(Long qtdUniformeEntregue) {
		this.qtdUniformeEntregue = qtdUniformeEntregue;
	}

	public AtividadesAluno getAtividadesAluno() {
		return atividadesAluno;
	}

	public void setAtividadesAluno(AtividadesAluno atividadesAluno) {
		this.atividadesAluno = atividadesAluno;
	}

	public Long getUsuarioAlteracao() {
		return usuarioAlteracao;
	}

	public void setUsuarioAlteracao(Long usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}


}
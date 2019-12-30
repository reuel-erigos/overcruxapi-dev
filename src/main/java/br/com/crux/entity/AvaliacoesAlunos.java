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

import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

import br.com.crux.enums.NotaAvaliacao;
import br.com.crux.infra.constantes.Constantes;


@Entity
@Table(name="avaliacoes_alunos")
public class AvaliacoesAlunos {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY, generator = "sq_id_avaliacao_aluno")
	@SequenceGenerator(name = "sq_id_avaliacao_aluno", sequenceName = "sq_id_avaliacao_aluno", schema = Constantes.SCHEMA_PUBLIC, initialValue = 1, allocationSize = 1)
	@Column(name="id_avaliacao_aluno")
	private Long id;

	@Column(name="ds_avaliacao_aluno")
	private String descriaoAvaliacao;

	@Column(name="dt_avaliacao_aluno")
	private LocalDateTime dataAvaliacao;

	@Column(name="nr_avaliacao")
	@Type(type = "br.com.crux.infra.dao.GenericEnumUserType", parameters = {
			@Parameter(name = "enumClass", value = "br.com.crux.enums.NotaAvaliacao"),
			@Parameter(name = "keyName", value = "tipo")})
	private NotaAvaliacao notaAvaliacao;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_atividade_aluno")
	private AtividadesAluno atividadesAluno;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_avaliacao")
	private Avaliacoes avaliacoes;

	@Column(name="id_usuario_apl")
	private Long usuarioAlteracao;

	public AvaliacoesAlunos() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescriaoAvaliacao() {
		return descriaoAvaliacao;
	}

	public void setDescriaoAvaliacao(String descriaoAvaliacao) {
		this.descriaoAvaliacao = descriaoAvaliacao;
	}

	public LocalDateTime getDataAvaliacao() {
		return dataAvaliacao;
	}

	public void setDataAvaliacao(LocalDateTime dataAvaliacao) {
		this.dataAvaliacao = dataAvaliacao;
	}

	public NotaAvaliacao getNotaAvaliacao() {
		return notaAvaliacao;
	}

	public void setNotaAvaliacao(NotaAvaliacao notaAvaliacao) {
		this.notaAvaliacao = notaAvaliacao;
	}

	public AtividadesAluno getAtividadesAluno() {
		return atividadesAluno;
	}

	public void setAtividadesAluno(AtividadesAluno atividadesAluno) {
		this.atividadesAluno = atividadesAluno;
	}

	public Avaliacoes getAvaliacoes() {
		return avaliacoes;
	}

	public void setAvaliacoes(Avaliacoes avaliacoe) {
		this.avaliacoes = avaliacoe;
	}

	public Long getUsuarioAlteracao() {
		return usuarioAlteracao;
	}

	public void setUsuarioAlteracao(Long usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}

}
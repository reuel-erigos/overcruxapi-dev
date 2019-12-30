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


/**
 * The persistent class for the cursos_formacao_pf database table.
 * 
 */
@Entity
@Table(name="cursos_formacao_pf")
public class CursosFormacaoPf  {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_id_curso")
	@SequenceGenerator(name = "sq_id_curso", sequenceName = "sq_id_curso", schema = Constantes.SCHEMA_PUBLIC, initialValue = 1, allocationSize = 1)
	@Column(name="id_curso")
	private Long id;

	@Column(name="dt_fim_curso")
	private LocalDateTime dataFim;

	@Column(name="dt_inicio_curso")
	private LocalDateTime dataInicio;

	@Column(name="nm_curso")
	private String nome;

	@Column(name="nm_instuicao")
	private String nomeInstuicao;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_pessoa_fisica")
	private PessoaFisica pessoaFisica;

	@Column(name="id_usuario_apl")
	private Long usuarioAlteracao;

	public CursosFormacaoPf() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getNomeInstuicao() {
		return nomeInstuicao;
	}

	public void setNomeInstuicao(String nomeInstuicao) {
		this.nomeInstuicao = nomeInstuicao;
	}

	public PessoaFisica getPessoaFisica() {
		return pessoaFisica;
	}

	public void setPessoaFisica(PessoaFisica pessoaFisica) {
		this.pessoaFisica = pessoaFisica;
	}

	public Long getUsuarioAlteracao() {
		return usuarioAlteracao;
	}

	public void setUsuarioAlteracao(Long usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}

	

}
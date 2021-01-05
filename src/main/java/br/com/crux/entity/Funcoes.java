package br.com.crux.entity;

import java.io.Serializable;

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
@Table(name="funcoes")
public class Funcoes implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_id_funcao")
	@SequenceGenerator(name = "sq_id_funcao", sequenceName = "sq_id_funcao", schema = Constantes.SCHEMA_PUBLIC, initialValue = 1, allocationSize = 1)
	@Column(name="id_funcao")
	private Long id;

	@Column(name="nm_funcao", nullable = false)
	private String nome;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_unidade")
	private Unidade unidade;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_cargo")
	private Cargo cargo;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_instituicao", nullable = true)
	private Instituicao instituicao;

	@Column(name="vl_remuneracao")
	private Double valorSalario;

	@Column(name="ds_funcao")
	private String descricao;

	@Column(name="ds_atribuicoes")
	private String atribuicoes;
	
	@Column(name="ds_conhecimentos_especificos")
	private String conhecimentosEspecificos;

	@Column(name="ds_comportamentos_esperados")
	private String comportamentosEsperados;

	@Column(name="id_usuario_apl")
	private Long usuarioAlteracao;
	
	@Column(name="qtd_carga_horaria_minima")
	private Long qtdCargaHorariaMinima;
	
	@Column(name="qtd_carga_horaria_maxima")
	private Long qtdCargaHorariaMaxima;
	
	
	public Funcoes() {
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

	public Unidade getUnidade() {
		return unidade;
	}

	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
	}

	public Instituicao getInstituicao() {
		return instituicao;
	}

	public void setInstituicao(Instituicao instituicao) {
		this.instituicao = instituicao;
	}

	public Long getUsuarioAlteracao() {
		return usuarioAlteracao;
	}

	public void setUsuarioAlteracao(Long usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}

	public Double getValorSalario() {
		return valorSalario;
	}

	public void setValorSalario(Double valorSalario) {
		this.valorSalario = valorSalario;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getAtribuicoes() {
		return atribuicoes;
	}

	public void setAtribuicoes(String atribuicoes) {
		this.atribuicoes = atribuicoes;
	}

	public String getConhecimentosEspecificos() {
		return conhecimentosEspecificos;
	}

	public void setConhecimentosEspecificos(String conhecimentosEspecificos) {
		this.conhecimentosEspecificos = conhecimentosEspecificos;
	}

	public String getComportamentosEsperados() {
		return comportamentosEsperados;
	}

	public void setComportamentosEsperados(String comportamentosEsperados) {
		this.comportamentosEsperados = comportamentosEsperados;
	}

	public Long getQtdCargaHorariaMinima() {
		return qtdCargaHorariaMinima;
	}

	public void setQtdCargaHorariaMinima(Long qtdCargaHorariaMinima) {
		this.qtdCargaHorariaMinima = qtdCargaHorariaMinima;
	}

	public Long getQtdCargaHorariaMaxima() {
		return qtdCargaHorariaMaxima;
	}

	public void setQtdCargaHorariaMaxima(Long qtdCargaHorariaMaxima) {
		this.qtdCargaHorariaMaxima = qtdCargaHorariaMaxima;
	}


}
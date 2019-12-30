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
 * The persistent class for the grupos_modulos database table.
 * 
 */
@Entity
@Table(name="grupos_modulos")
public class GruposModulo {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_id_grupo_modulo")
	@SequenceGenerator(name = "sq_id_grupo_modulo", sequenceName = "sq_id_grupo_modulo", schema = Constantes.SCHEMA_PUBLIC, initialValue = 1, allocationSize = 1)
	@Column(name="id_grupo_modulo")
	private Long id;

	@Column(name="nm_grupo")
	private String nome;

	@Column(name="tx_descricao_grupo")
	private String descricao;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_modulo")
	private Modulo modulo;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_perfil_acesso")
	private PerfilAcesso perfilAcesso;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_unidade")
	private Unidade unidade;
	
	@Column(name="id_usuario_apl")
	private Long usuarioAlteracao;

	
	public GruposModulo() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long idGrupoModulo) {
		this.id = idGrupoModulo;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nmGrupo) {
		this.nome = nmGrupo;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String txDescricaoGrupo) {
		this.descricao = txDescricaoGrupo;
	}

	public Modulo getModulo() {
		return this.modulo;
	}

	public void setModulo(Modulo modulo) {
		this.modulo = modulo;
	}

	public PerfilAcesso getPerfilAcesso() {
		return this.perfilAcesso;
	}

	public void setPerfilAcesso(PerfilAcesso perfisAcesso) {
		this.perfilAcesso = perfisAcesso;
	}

	public Unidade getUnidade() {
		return unidade;
	}

	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
	}

	public Long getUsuarioAlteracao() {
		return usuarioAlteracao;
	}

	public void setUsuarioAlteracao(Long usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}

	

}
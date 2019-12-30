package br.com.crux.entity;

import java.io.Serializable;
import javax.persistence.*;

import br.com.crux.infra.constantes.Constantes;


/**
 * The persistent class for the usuarios_unidades database table.
 * 
 */
@Entity
@Table(name="usuarios_unidades")
public class UsuariosUnidade implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_id_usuario_unidade")
	@SequenceGenerator(name = "sq_id_usuario_unidade", sequenceName = "sq_id_usuario_unidade", schema = Constantes.SCHEMA_PUBLIC, initialValue = 1, allocationSize = 1)
	@Column(name="id_usuario_unidade")
	private Long id;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_unidade")
	private Unidade unidade;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_usuario")
	private UsuariosSistema usuarioSistema;
	
	@Column(name = "id_usuario_apl")
	private Long usuarioAlteracao;
	

	public UsuariosUnidade() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long idUsuarioUnidade) {
		this.id = idUsuarioUnidade;
	}

	public Unidade getUnidade() {
		return this.unidade;
	}

	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
	}

	public UsuariosSistema getUsuarioSistema() {
		return this.usuarioSistema;
	}

	public void setUsuarioSistema(UsuariosSistema usuariosSistema) {
		this.usuarioSistema = usuariosSistema;
	}

	public Long getUsuarioAlteracao() {
		return usuarioAlteracao;
	}

	public void setUsuarioAlteracao(Long usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}

	
}
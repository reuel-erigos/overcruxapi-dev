package br.com.crux.entity;

import java.io.Serializable;
import javax.persistence.*;

import br.com.crux.infra.constantes.Constantes;


/**
 * The persistent class for the usuarios_grupos database table.
 * 
 */
@Entity
@Table(name="usuarios_grupos")
public class UsuariosGrupo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_id_usuario_grupo")
	@SequenceGenerator(name = "sq_id_usuario_grupo", sequenceName = "sq_id_usuario_grupo", schema = Constantes.SCHEMA_PUBLIC, initialValue = 1, allocationSize = 1)
	@Column(name="id_usuario_grupo")
	private Long idUsuarioGrupo;

	//bi-directional many-to-one association to GruposModulo
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_grupo_modulo")
	private GruposModulo gruposModulo;

	//bi-directional many-to-one association to UsuariosSistema
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_usuario")
	private UsuariosSistema usuariosSistema;
	
	@Column(name = "id_usuario_apl")
	private Long idUsuarioApl;


	public UsuariosGrupo() {
	}

	public Long getIdUsuarioGrupo() {
		return this.idUsuarioGrupo;
	}

	public void setIdUsuarioGrupo(Long idUsuarioGrupo) {
		this.idUsuarioGrupo = idUsuarioGrupo;
	}

	public GruposModulo getGruposModulo() {
		return this.gruposModulo;
	}

	public void setGruposModulo(GruposModulo gruposModulo) {
		this.gruposModulo = gruposModulo;
	}

	public UsuariosSistema getUsuariosSistema() {
		return this.usuariosSistema;
	}

	public void setUsuariosSistema(UsuariosSistema usuariosSistema) {
		this.usuariosSistema = usuariosSistema;
	}

	public Long getIdUsuarioApl() {
		return idUsuarioApl;
	}

	public void setIdUsuarioApl(Long idUsuarioApl) {
		this.idUsuarioApl = idUsuarioApl;
	}

	

}
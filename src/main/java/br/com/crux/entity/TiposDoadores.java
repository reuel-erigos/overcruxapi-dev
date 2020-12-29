package br.com.crux.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.crux.infra.constantes.Constantes;


@Entity
@Table(name="tipos_doadores")
public class TiposDoadores implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_tipo_id_doador")
	@SequenceGenerator(name = "sq_tipo_id_doador", sequenceName = "sq_tipo_id_doador", schema = Constantes.SCHEMA_PUBLIC, initialValue = 1, allocationSize = 1)
	@Column(name="id_tipo_doador")
	private Long id;

	@Column(name="cd_tipo_doador")
	private String codigo;

	@Column(name="ds_tipo_doador")
	private String descricao;
	
	@Column(name="id_instituicao", nullable = true)
	private Long idInstituicao;

	@Column(name="id_usuario_apl")
	private Long usuarioAlteracao;
	
	
	public TiposDoadores() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public Long getUsuarioAlteracao() {
		return usuarioAlteracao;
	}

	public void setUsuarioAlteracao(Long usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}

	public Long getIdInstituicao() {
		return idInstituicao;
	}

	public void setIdInstituicao(Long instituicao) {
		this.idInstituicao = instituicao;
	}


}
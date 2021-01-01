package br.com.crux.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.crux.infra.constantes.Constantes;


@Entity
@Table(name="beneficios_sociais")
public class BeneficioSocial  {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_id_beneficio_social")
	@SequenceGenerator(name = "sq_id_beneficio_social", sequenceName = "sq_id_beneficio_social", schema = Constantes.SCHEMA_PUBLIC, initialValue = 1, allocationSize = 1)
	@Column(name="id_beneficio_social")
	private Long id;

	@Column(name="nm_beneficio_social", nullable = false)
	private String nome;

	@Column(name="ds_beneficio_social")
	private String descricao;

	@Column(name="ds_origem_social")
	private String origemSocial;

	@Column(name="id_instituicao")
	private Long idInstituicao;

	@Column(name="id_usuario_apl")
	private Long usuarioAlteracao;

	public BeneficioSocial() {
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

	

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getOrigemSocial() {
		return origemSocial;
	}

	public void setOrigemSocial(String origemSocial) {
		this.origemSocial = origemSocial;
	}

	public Long getIdInstituicao() {
		return idInstituicao;
	}

	public void setIdInstituicao(Long idInstituicao) {
		this.idInstituicao = idInstituicao;
	}

	public Long getUsuarioAlteracao() {
		return usuarioAlteracao;
	}

	public void setUsuarioAlteracao(Long usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}


}
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


@Entity
@Table(name="programas_unidades")
public class ProgramasUnidade  {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_id_programa_unidade")
	@SequenceGenerator(name = "sq_id_programa_unidade", sequenceName = "sq_id_programa_unidade", schema = Constantes.SCHEMA_PUBLIC, initialValue = 1, allocationSize = 1)
	@Column(name="id_programa_unidade")
	private Long id;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_programa")	
	private Programa programa;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_unidade")	
	private Unidade unidade;

	@Column(name="id_usuario_apl")
	private Long usuarioAlteracao;

	public ProgramasUnidade() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Programa getPrograma() {
		return programa;
	}

	public void setPrograma(Programa programa) {
		this.programa = programa;
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
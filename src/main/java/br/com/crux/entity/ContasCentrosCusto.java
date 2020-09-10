package br.com.crux.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.crux.infra.constantes.Constantes;


/**
 * The persistent class for the contas_centros_custo database table.
 * 
 */
@Entity
@Table(name="contas_centros_custo")
public class ContasCentrosCusto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_id_conta_centro_custo")
	@SequenceGenerator(name = "sq_id_conta_centro_custo", sequenceName = "sq_id_conta_centro_custo", schema = Constantes.SCHEMA_PUBLIC, initialValue = 1, allocationSize = 1)
	@Column(name="id_conta_centro_custo")
	private Long id;

	@Column(name = "id_usuario_apl")
	private Long usuarioAlteracao;

	@ManyToOne
	@JoinColumn(name="id_conta_bancaria")
	private ContasBancaria contasBancaria;

	@Column(name="id_programa")
	private Long idPrograma;

	@Column(name="id_projeto")
	private Long idProjeto;

	public ContasCentrosCusto() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUsuarioAlteracao() {
		return usuarioAlteracao;
	}

	public void setUsuarioAlteracao(Long usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}

	public ContasBancaria getContasBancaria() {
		return contasBancaria;
	}

	public void setContasBancaria(ContasBancaria contasBancaria) {
		this.contasBancaria = contasBancaria;
	}

	public Long getIdPrograma() {
		return idPrograma;
	}

	public void setIdPrograma(Long idPrograma) {
		this.idPrograma = idPrograma;
	}

	public Long getIdProjeto() {
		return idProjeto;
	}

	public void setIdProjeto(Long idProjeto) {
		this.idProjeto = idProjeto;
	}

	
}
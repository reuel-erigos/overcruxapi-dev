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
@Table(name="motivos_desligamentos")
public class MotivoDesligamento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_id_motivo_desligamento")
	@SequenceGenerator(name = "sq_id_motivo_desligamento", sequenceName = "sq_id_motivo_desligamento", schema = Constantes.SCHEMA_PUBLIC, initialValue = 1, allocationSize = 1)
	@Column(name="id_motivo_desligamento", nullable = false)
	private Long id;
	
	@Column(name="ds_motivo_desligamento", nullable = false)
	private String motivoDesligamento;

	@Column(name="id_instituicao", nullable = true)
	private Long idInstituicao;
	
	@Column(name="id_usuario_apl")
	private Long usuarioAlteracao;
	
	public MotivoDesligamento() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMotivoDesligamento() {
		return motivoDesligamento;
	}

	public void setMotivoDesligamento(String motivoDesligamento) {
		this.motivoDesligamento = motivoDesligamento;
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

	public void setIdInstituicao(Long idInstituicao) {
		this.idInstituicao = idInstituicao;
	}

	
	
}
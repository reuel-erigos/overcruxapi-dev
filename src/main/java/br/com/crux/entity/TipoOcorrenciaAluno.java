package br.com.crux.entity;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.crux.infra.constantes.Constantes;
import br.com.crux.infra.dao.SimNaoConverter;

@Entity
@Table(name = "tipos_ocorrencias_alunos")
public class TipoOcorrenciaAluno  {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_id_tipo_ocorrencia_aluno")
	@SequenceGenerator(name = "sq_id_tipo_ocorrencia_aluno", sequenceName = "sq_id_tipo_ocorrencia_aluno", schema = Constantes.SCHEMA_PUBLIC, initialValue = 1, allocationSize = 1)
	@Column(name = "id_tipo_ocorrencia_aluno")
	private Long id;
	
	@Column(name = "ds_tipo_ocorrencia")
	private String tipoOcorrencia;
	
	@Convert(converter = SimNaoConverter.class)
	@Column(name = "st_informa_sap")
	private Boolean informaSAP;
	
	@Column(name="id_usuario_apl")
	private Long usuarioAlteracao;
	
	public TipoOcorrenciaAluno() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTipoOcorrencia() {
		return tipoOcorrencia;
	}

	public void setTipoOcorrencia(String tipoOcorrencia) {
		this.tipoOcorrencia = tipoOcorrencia;
	}

	public Boolean getInformaSAP() {
		return informaSAP;
	}

	public void setInformaSAP(Boolean informaSAP) {
		this.informaSAP = informaSAP;
	}

	public Long getUsuarioAlteracao() {
		return usuarioAlteracao;
	}

	public void setUsuarioAlteracao(Long usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}


	
}
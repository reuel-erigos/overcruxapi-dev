package br.com.crux.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

import br.com.crux.enums.TipoQuestionario;
import br.com.crux.infra.constantes.Constantes;

@Entity
@Table(name = "questionarios")
public class Questionario {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_id_quetionario")
	@SequenceGenerator(name = "sq_id_quetionario", sequenceName = "sq_id_quetionario", schema = Constantes.SCHEMA_PUBLIC, initialValue = 1, allocationSize = 1)
	@Column(name = "id_quetionario")
	private Long id;

	@Column(name = "ds_questionario")
	private String descricao;

	@Column(name = "ds_tipo_questionario")
	@Type(type = "br.com.crux.infra.dao.GenericEnumUserType", parameters = {
			@Parameter(name = "enumClass", value = "br.com.crux.enums.TipoQuestionario"),
			@Parameter(name = "keyName", value = "tipo")})
	private TipoQuestionario tipoQuestionario;

	@Column(name = "dt_fim_questionario")
	private LocalDateTime dataFim;

	@Column(name = "dt_inicio_questionario")
	private LocalDateTime dataInicio;

	@Column(name = "id_usuario_apl")
	private Long usuarioAlteracao;

	public Questionario() {
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

	public TipoQuestionario getTipoQuestionario() {
		return tipoQuestionario;
	}

	public void setTipoQuestionario(TipoQuestionario tipoQuestionario) {
		this.tipoQuestionario = tipoQuestionario;
	}

	public LocalDateTime getDataFim() {
		return dataFim;
	}

	public void setDataFim(LocalDateTime dataFim) {
		this.dataFim = dataFim;
	}

	public LocalDateTime getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(LocalDateTime dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Long getUsuarioAlteracao() {
		return usuarioAlteracao;
	}

	public void setUsuarioAlteracao(Long usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}

}
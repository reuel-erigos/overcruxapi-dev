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
@Table(name="participantes_atendimentos")
public class ParticipantesAtendimentos  {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_ID_PART_ATENDIMENTO")
	@SequenceGenerator(name = "SQ_ID_PART_ATENDIMENTO", sequenceName = "SQ_ID_PART_ATENDIMENTO", schema = Constantes.SCHEMA_PUBLIC, initialValue = 1, allocationSize = 1)
	@Column(name="id_part_atendimento")
	private Long id;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_atendimento")
	private Atendimentos atendimento;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_familiar")
	private Familiares familiar;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_funcionario")
	private Funcionario funcionario;

	@Column(name="id_usuario_apl")
	private Long usuarioAlteracao;

	public ParticipantesAtendimentos() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Atendimentos getAtendimento() {
		return atendimento;
	}

	public void setAtendimento(Atendimentos atendimento) {
		this.atendimento = atendimento;
	}

	public Familiares getFamiliar() {
		return familiar;
	}

	public void setFamiliar(Familiares familiar) {
		this.familiar = familiar;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public Long getUsuarioAlteracao() {
		return usuarioAlteracao;
	}

	public void setUsuarioAlteracao(Long idUsuarioApl) {
		this.usuarioAlteracao = idUsuarioApl;
	}


}
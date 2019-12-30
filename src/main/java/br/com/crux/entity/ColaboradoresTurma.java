package br.com.crux.entity;

import java.time.LocalDateTime;

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
@Table(name="colaboradores_turmas")
public class ColaboradoresTurma  {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_id_colaborador_turma")
	@SequenceGenerator(name = "sq_id_colaborador_turma", sequenceName = "sq_id_colaborador_turma", schema = Constantes.SCHEMA_PUBLIC, initialValue = 1, allocationSize = 1)
	@Column(name="id_colaborador_turma")
	private Long id;

	@Column(name="dt_entrada_turma")
	private LocalDateTime dataEntradaTurma;

	@Column(name="dt_saida_turma")
	private LocalDateTime dataSaidaTurma;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_cargo")	
	private Cargo cargo;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_funcionario")	
	private Funcionario funcionario;

	@Column(name="id_turma")	
	private Long idTurma;

	@Column(name="id_usuario_apl")
	private Long usuarioAlteracao;

	public ColaboradoresTurma() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getDataEntradaTurma() {
		return dataEntradaTurma;
	}

	public void setDataEntradaTurma(LocalDateTime dataEntradaTurma) {
		this.dataEntradaTurma = dataEntradaTurma;
	}

	public LocalDateTime getDataSaidaTurma() {
		return dataSaidaTurma;
	}

	public void setDataSaidaTurma(LocalDateTime dataSaidaTurma) {
		this.dataSaidaTurma = dataSaidaTurma;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public Long getIdTurma() {
		return idTurma;
	}

	public void setIdTurma(Long turma) {
		this.idTurma = turma;
	}

	public Long getUsuarioAlteracao() {
		return usuarioAlteracao;
	}

	public void setUsuarioAlteracao(Long usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}

	
}
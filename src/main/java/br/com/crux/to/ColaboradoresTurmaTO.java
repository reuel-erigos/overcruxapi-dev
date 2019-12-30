package br.com.crux.to;

import java.time.LocalDateTime;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import br.com.crux.infra.adapter.LocalDateTimeAdapter;

public class ColaboradoresTurmaTO  {

	private Long id;
	
	@XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
	private LocalDateTime dataEntradaTurma;
	@XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
	private LocalDateTime dataSaidaTurma;
	
	private CargoTO cargo;
	private FuncionarioTO funcionario;
	
	private Long idTurma;
	private Long usuarioAlteracao;
	
	public ColaboradoresTurmaTO() {
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

	public CargoTO getCargo() {
		return cargo;
	}

	public void setCargo(CargoTO cargo) {
		this.cargo = cargo;
	}

	public FuncionarioTO getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(FuncionarioTO funcionario) {
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
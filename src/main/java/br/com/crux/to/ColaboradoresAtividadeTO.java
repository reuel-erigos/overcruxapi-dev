package br.com.crux.to;

import java.time.LocalDateTime;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import br.com.crux.infra.adapter.LocalDateTimeAdapter;

public class ColaboradoresAtividadeTO {

	private Long id;

	@XmlJavaTypeAdapter(LocalDateTimeAdapter.class) 
	private LocalDateTime dtEntradaAtividade;
	@XmlJavaTypeAdapter(LocalDateTimeAdapter.class) 
	private LocalDateTime dtSaidaAtividade;

	private Long idAtividade;

	private CargoTO cargo;

	private FuncionarioTO funcionario;

	private Long usuariosSistema;

	public ColaboradoresAtividadeTO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getDtEntradaAtividade() {
		return dtEntradaAtividade;
	}

	public void setDtEntradaAtividade(LocalDateTime dtEntradaAtividade) {
		this.dtEntradaAtividade = dtEntradaAtividade;
	}

	public LocalDateTime getDtSaidaAtividade() {
		return dtSaidaAtividade;
	}

	public void setDtSaidaAtividade(LocalDateTime dtSaidaAtividade) {
		this.dtSaidaAtividade = dtSaidaAtividade;
	}

	public Long getIdAtividade() {
		return idAtividade;
	}

	public void setIdAtividade(Long idAtividade) {
		this.idAtividade = idAtividade;
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

	public Long getUsuariosSistema() {
		return usuariosSistema;
	}

	public void setUsuariosSistema(Long usuariosSistema) {
		this.usuariosSistema = usuariosSistema;
	}

}
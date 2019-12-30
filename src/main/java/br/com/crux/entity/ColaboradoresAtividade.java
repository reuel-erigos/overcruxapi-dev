package br.com.crux.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.crux.infra.constantes.Constantes;

/**
 * The persistent class for the colaboradores_atividades database table.
 * 
 */
@Entity
@Table(name = "colaboradores_atividades")
@NamedQuery(name = "ColaboradoresAtividade.findAll", query = "SELECT c FROM ColaboradoresAtividade c")
public class ColaboradoresAtividade implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_id_colaborador_atividade")
	@SequenceGenerator(name = "sq_id_colaborador_atividade", sequenceName = "sq_id_colaborador_atividade", schema = Constantes.SCHEMA_PUBLIC, initialValue = 1, allocationSize = 1)
	@Column(name = "id_colaborador_atividade") 
	private Long idColaboradorAtividade;

	@Column(name = "dt_entrada_atividade") 
	private LocalDateTime dtEntradaAtividade;

	@Column(name = "dt_saida_atividade") 
	private LocalDateTime dtSaidaAtividade;

	@Column(name = "id_atividade") 
	private Long idAtividade;

	//bi-directional many-to-one association to Cargo
	@ManyToOne(fetch = FetchType.LAZY) 
	@JoinColumn(name = "id_cargo") 
	private Cargo cargo;

	//bi-directional many-to-one association to Funcionario
	@ManyToOne(fetch = FetchType.LAZY) 
	@JoinColumn(name = "id_funcionario") 
	private Funcionario funcionario;

	@Column(name="id_usuario_apl")
	private Long usuariosSistema;

	public ColaboradoresAtividade() {
	}

	public Long getIdColaboradorAtividade() {
		return this.idColaboradorAtividade;
	}

	public void setIdColaboradorAtividade(Long idColaboradorAtividade) {
		this.idColaboradorAtividade = idColaboradorAtividade;
	}

	public LocalDateTime getDtEntradaAtividade() {
		return this.dtEntradaAtividade;
	}

	public void setDtEntradaAtividade(LocalDateTime dtEntradaAtividade) {
		this.dtEntradaAtividade = dtEntradaAtividade;
	}

	public LocalDateTime getDtSaidaAtividade() {
		return this.dtSaidaAtividade;
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

	public Cargo getCargo() {
		return this.cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public Funcionario getFuncionario() {
		return this.funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public Long getUsuariosSistema() {
		return this.usuariosSistema;
	}

	public void setUsuariosSistema(Long usuariosSistema) {
		this.usuariosSistema = usuariosSistema;
	}

}
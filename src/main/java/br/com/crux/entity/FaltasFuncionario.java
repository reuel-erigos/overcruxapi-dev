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
@Table(name="faltas_funcionarios")
public class FaltasFuncionario  {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_id_falta_funcionario")
	@SequenceGenerator(name = "sq_id_falta_funcionario", sequenceName = "sq_id_falta_funcionario", schema = Constantes.SCHEMA_PUBLIC, initialValue = 1, allocationSize = 1)
	@Column(name="id_falta_funcionario")
	private Long id;

	@Column(name="ds_falta_funcionario")
	private String descricao;

	@Column(name="ds_jutifi_falta")
	private String jutificativaFalta;

	@Column(name="dt_cadastro")
	private LocalDateTime dataCadastro;

	@Column(name="dt_falta_funcionario")
	private LocalDateTime dataFaltaFuncionario;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_funcionario_falta")
	private Funcionario funcionarioFaltou;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_funcionario_cadastro")
	private Funcionario funcionarioCadastrouFalta;

	@Column(name="id_usuario_apl")
	private Long usuarioAlteracao;

	public FaltasFuncionario() {
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

	public String getJutificativaFalta() {
		return jutificativaFalta;
	}

	public void setJutificativaFalta(String jutificativaFalta) {
		this.jutificativaFalta = jutificativaFalta;
	}

	public LocalDateTime getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDateTime dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public LocalDateTime getDataFaltaFuncionario() {
		return dataFaltaFuncionario;
	}

	public void setDataFaltaFuncionario(LocalDateTime dataFaltaFuncionario) {
		this.dataFaltaFuncionario = dataFaltaFuncionario;
	}

	public Funcionario getFuncionarioFaltou() {
		return funcionarioFaltou;
	}

	public void setFuncionarioFaltou(Funcionario funcionarioFaltou) {
		this.funcionarioFaltou = funcionarioFaltou;
	}

	public Funcionario getFuncionarioCadastrouFalta() {
		return funcionarioCadastrouFalta;
	}

	public void setFuncionarioCadastrouFalta(Funcionario funcionarioCadastrouFalta) {
		this.funcionarioCadastrouFalta = funcionarioCadastrouFalta;
	}

	public Long getUsuarioAlteracao() {
		return usuarioAlteracao;
	}

	public void setUsuarioAlteracao(Long usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}


}
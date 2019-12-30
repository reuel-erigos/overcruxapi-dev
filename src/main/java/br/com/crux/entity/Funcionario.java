package br.com.crux.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

import br.com.crux.enums.ConclusaoParecer;
import br.com.crux.enums.ParecerEntrevistador;
import br.com.crux.enums.TipoFuncionario;
import br.com.crux.infra.constantes.Constantes;
import br.com.crux.infra.dao.SimNaoConverter;

@Entity
@Table(name="funcionarios")
public class Funcionario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_id_funcionario")
	@SequenceGenerator(name = "sq_id_funcionario", sequenceName = "sq_id_funcionario", schema = Constantes.SCHEMA_PUBLIC, initialValue = 1, allocationSize = 1)
	@Column(name="id_funcionario")
	private Long id;

	@Column(name="nr_matricula")
	private String matricula;

	@Column(name="dt_admissao")
	private LocalDateTime dataAdmissao;

	@Column(name="dt_demissao")
	private LocalDateTime dataDemissao;
	

	@Column(name="st_tipo_funcionario")
	@Type(type = "br.com.crux.infra.dao.GenericEnumUserType", 
	parameters = { @Parameter(name = "enumClass", value = "br.com.crux.enums.TipoFuncionario"),
			       @Parameter(name = "keyName", value = "tipo")}) 
	private TipoFuncionario tipoFuncionario;

	@Column(name="vl_salario_pretendido")
	private Double salarioPretendido;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_cargo")
	private Cargo cargo;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_pessoa_fisica")
	private PessoaFisica pessoasFisica;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_unidade")
	private Unidade unidade;

	@Column(name="dt_hr_entrevista")
	private LocalDateTime dtHrEntrevista;

	@Column(name="st_parecer_entrevistador")
	@Type(type = "br.com.crux.infra.dao.GenericEnumUserType", 
	parameters = { @Parameter(name = "enumClass", value = "br.com.crux.enums.ParecerEntrevistador"),
			       @Parameter(name = "keyName", value = "tipo")}) 
	private ParecerEntrevistador parecerEntrevistador;
	
	//Descrição do parecer do entrevistador em relação ado candidato a funcionário  (Tipo =  D = CANDIDATO A VAGA DE FUNCIONÁRIO)
	@Column(name="ds_parecer_entrevistador")
	private String descricaoParecerEntrevistador;
	
	@Column(name="st_conclusao_parecer")
	@Type(type = "br.com.crux.infra.dao.GenericEnumUserType", 
	parameters = { @Parameter(name = "enumClass", value = "br.com.crux.enums.ConclusaoParecer"),
			       @Parameter(name = "keyName", value = "tipo")}) 
	private ConclusaoParecer conclusaoParecer;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_funcionario_entrevistador")
	private Funcionario funcionarioEntrevistador;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_empresa_func")
	private Empresa empresaFuncionario;

	@Column(name="id_usuario_apl")
	private Long usuarioAlteracao;

	
	@Convert(converter = SimNaoConverter.class)
	@Column(name = "st_desconta_vt")
	private Boolean descontaValeTransporte;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_departamento")
	private Departamentos departamento;
	
	
	@OneToMany(mappedBy="funcionario")
	private List<Dependentes> dependentes;
	
	@OneToMany(mappedBy="funcionario")
	private List<AlocacoesFuncionario> alocacoesFuncionario;
	

	public Funcionario() {
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getMatricula() {
		return matricula;
	}


	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}


	public LocalDateTime getDataAdmissao() {
		return dataAdmissao;
	}


	public void setDataAdmissao(LocalDateTime dataAdmissao) {
		this.dataAdmissao = dataAdmissao;
	}


	public LocalDateTime getDataDemissao() {
		return dataDemissao;
	}


	public void setDataDemissao(LocalDateTime dataDemissao) {
		this.dataDemissao = dataDemissao;
	}


	public TipoFuncionario getTipoFuncionario() {
		return tipoFuncionario;
	}


	public void setTipoFuncionario(TipoFuncionario tipoFuncionario) {
		this.tipoFuncionario = tipoFuncionario;
	}


	public Double getSalarioPretendido() {
		return salarioPretendido;
	}


	public void setSalarioPretendido(Double salarioPretendido) {
		this.salarioPretendido = salarioPretendido;
	}


	public Cargo getCargo() {
		return cargo;
	}


	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}


	public PessoaFisica getPessoasFisica() {
		return pessoasFisica;
	}


	public void setPessoasFisica(PessoaFisica pessoasFisica) {
		this.pessoasFisica = pessoasFisica;
	}


	public Unidade getUnidade() {
		return unidade;
	}


	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
	}


	public LocalDateTime getDtHrEntrevista() {
		return dtHrEntrevista;
	}


	public void setDtHrEntrevista(LocalDateTime dtHrEntrevista) {
		this.dtHrEntrevista = dtHrEntrevista;
	}


	public ParecerEntrevistador getParecerEntrevistador() {
		return parecerEntrevistador;
	}


	public void setParecerEntrevistador(ParecerEntrevistador parecerEntrevistador) {
		this.parecerEntrevistador = parecerEntrevistador;
	}


	public String getDescricaoParecerEntrevistador() {
		return descricaoParecerEntrevistador;
	}


	public void setDescricaoParecerEntrevistador(String descricaoParecerEntrevistador) {
		this.descricaoParecerEntrevistador = descricaoParecerEntrevistador;
	}


	public ConclusaoParecer getConclusaoParecer() {
		return conclusaoParecer;
	}


	public void setConclusaoParecer(ConclusaoParecer conclusaoParecer) {
		this.conclusaoParecer = conclusaoParecer;
	}


	public Funcionario getFuncionarioEntrevistador() {
		return funcionarioEntrevistador;
	}


	public void setFuncionarioEntrevistador(Funcionario funcionarioEntrevistador) {
		this.funcionarioEntrevistador = funcionarioEntrevistador;
	}


	public Empresa getEmpresaFuncionario() {
		return empresaFuncionario;
	}


	public void setEmpresaFuncionario(Empresa empresaFuncionario) {
		this.empresaFuncionario = empresaFuncionario;
	}


	public Long getUsuarioAlteracao() {
		return usuarioAlteracao;
	}


	public void setUsuarioAlteracao(Long usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}


	public Boolean getDescontaValeTransporte() {
		return descontaValeTransporte;
	}


	public void setDescontaValeTransporte(Boolean descontaValeTransporte) {
		this.descontaValeTransporte = descontaValeTransporte;
	}


	public Departamentos getDepartamento() {
		return departamento;
	}


	public void setDepartamento(Departamentos departamento) {
		this.departamento = departamento;
	}

	public List<Dependentes> getDependentes() {
		return dependentes;
	}


	public void setDependentes(List<Dependentes> dependentes) {
		this.dependentes = dependentes;
	}


	public List<AlocacoesFuncionario> getAlocacoesFuncionario() {
		return alocacoesFuncionario;
	}


	public void setAlocacoesFuncionario(List<AlocacoesFuncionario> alocacoesFuncionario) {
		this.alocacoesFuncionario = alocacoesFuncionario;
	}
	
	

}
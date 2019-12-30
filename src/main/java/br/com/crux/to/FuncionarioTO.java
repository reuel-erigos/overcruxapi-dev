package br.com.crux.to;

import java.time.LocalDateTime;
import java.util.List;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.crux.infra.adapter.LocalDateTimeAdapter;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FuncionarioTO {

	private Long id;
	private String matricula;

	@XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
	private LocalDateTime dataAdmissao;
	@XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
	private LocalDateTime dataDemissao;

	private String tipoFuncionario;
	private Double salarioPretendido;
	private CargoTO cargo;

	private PessoaFisicaTO pessoasFisica;

	private UnidadeTO unidade;
	
	@XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
	private LocalDateTime dtHrEntrevista;

	private String parecerEntrevistador;
	private String descricaoParecerEntrevistador;
	private String conclusaoParecer;
	private FuncionarioTO funcionarioEntrevistador;

	private EmpresaTO empresaFuncionario;

	private Long usuarioAlteracao;
	
	private String descontaValeTransporte;
	private DepartamentoTO departamento;
	
	private List<DependentesTO> dependentes;	
	private List<AlocacoesFuncionarioTO> alocacoesFuncionario;

	public FuncionarioTO() {
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

	public String getTipoFuncionario() {
		return tipoFuncionario;
	}

	public void setTipoFuncionario(String tipoFuncionario) {
		this.tipoFuncionario = tipoFuncionario;
	}

	public Double getSalarioPretendido() {
		return salarioPretendido;
	}

	public void setSalarioPretendido(Double salarioPretendido) {
		this.salarioPretendido = salarioPretendido;
	}

	public CargoTO getCargo() {
		return cargo;
	}

	public void setCargo(CargoTO cargo) {
		this.cargo = cargo;
	}

	public PessoaFisicaTO getPessoasFisica() {
		return pessoasFisica;
	}

	public void setPessoasFisica(PessoaFisicaTO pessoasFisica) {
		this.pessoasFisica = pessoasFisica;
	}

	public UnidadeTO getUnidade() {
		return unidade;
	}

	public void setUnidade(UnidadeTO unidade) {
		this.unidade = unidade;
	}

	public LocalDateTime getDtHrEntrevista() {
		return dtHrEntrevista;
	}

	public void setDtHrEntrevista(LocalDateTime dtHrEntrevista) {
		this.dtHrEntrevista = dtHrEntrevista;
	}

	public String getParecerEntrevistador() {
		return parecerEntrevistador;
	}

	public void setParecerEntrevistador(String parecerEntrevistador) {
		this.parecerEntrevistador = parecerEntrevistador;
	}

	public String getDescricaoParecerEntrevistador() {
		return descricaoParecerEntrevistador;
	}

	public void setDescricaoParecerEntrevistador(String descricaoParecerEntrevistador) {
		this.descricaoParecerEntrevistador = descricaoParecerEntrevistador;
	}

	public String getConclusaoParecer() {
		return conclusaoParecer;
	}

	public void setConclusaoParecer(String conclusaoParecer) {
		this.conclusaoParecer = conclusaoParecer;
	}

	public FuncionarioTO getFuncionarioEntrevistador() {
		return funcionarioEntrevistador;
	}

	public void setFuncionarioEntrevistador(FuncionarioTO funcionarioEntrevistador) {
		this.funcionarioEntrevistador = funcionarioEntrevistador;
	}

	public EmpresaTO getEmpresaFuncionario() {
		return empresaFuncionario;
	}

	public void setEmpresaFuncionario(EmpresaTO empresaFuncionario) {
		this.empresaFuncionario = empresaFuncionario;
	}

	public Long getUsuarioAlteracao() {
		return usuarioAlteracao;
	}

	public void setUsuarioAlteracao(Long usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}

	public String getDescontaValeTransporte() {
		return descontaValeTransporte;
	}

	public void setDescontaValeTransporte(String descontaValeTransporte) {
		this.descontaValeTransporte = descontaValeTransporte;
	}

	public DepartamentoTO getDepartamento() {
		return departamento;
	}

	public void setDepartamento(DepartamentoTO departamento) {
		this.departamento = departamento;
	}

	public List<DependentesTO> getDependentes() {
		return dependentes;
	}

	public void setDependentes(List<DependentesTO> dependentes) {
		this.dependentes = dependentes;
	}

	public List<AlocacoesFuncionarioTO> getAlocacoesFuncionario() {
		return alocacoesFuncionario;
	}

	public void setAlocacoesFuncionario(List<AlocacoesFuncionarioTO> alocacoesFuncionario) {
		this.alocacoesFuncionario = alocacoesFuncionario;
	}

	

}

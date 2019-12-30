package br.com.crux.to;

import java.time.LocalDateTime;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import br.com.crux.infra.adapter.LocalDateTimeAdapter;

public class AlocacoesFuncionarioTO {

	private Long id;
	
	@XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
	private LocalDateTime dataFimVinculacao;
	@XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
	private LocalDateTime dataInicioVinculacao;
	
	private FuncionarioTO funcionario;
	private ProgramaTO programa;
	private ProjetoTO projeto;
	private Double valorAlocacao;
	
	private Long usuarioAlteracao;	
	

	public AlocacoesFuncionarioTO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getDataFimVinculacao() {
		return dataFimVinculacao;
	}

	public void setDataFimVinculacao(LocalDateTime dataFimVinculacao) {
		this.dataFimVinculacao = dataFimVinculacao;
	}

	public LocalDateTime getDataInicioVinculacao() {
		return dataInicioVinculacao;
	}

	public void setDataInicioVinculacao(LocalDateTime dataInicioVinculacao) {
		this.dataInicioVinculacao = dataInicioVinculacao;
	}

	public FuncionarioTO getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(FuncionarioTO funcionario) {
		this.funcionario = funcionario;
	}

	public ProgramaTO getPrograma() {
		return programa;
	}

	public void setPrograma(ProgramaTO programa) {
		this.programa = programa;
	}

	public ProjetoTO getProjeto() {
		return projeto;
	}

	public void setProjeto(ProjetoTO projeto) {
		this.projeto = projeto;
	}

	public Long getUsuarioAlteracao() {
		return usuarioAlteracao;
	}

	public void setUsuarioAlteracao(Long usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}

	public Double getValorAlocacao() {
		return valorAlocacao;
	}

	public void setValorAlocacao(Double valorAlocacao) {
		this.valorAlocacao = valorAlocacao;
	}
	
	
	

}

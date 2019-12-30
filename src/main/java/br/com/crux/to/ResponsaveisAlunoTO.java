package br.com.crux.to;

import java.time.LocalDateTime;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import br.com.crux.infra.adapter.LocalDateTimeAdapter;

public class ResponsaveisAlunoTO {

	private Long id;
	private String descDesligamento;
	private String descGrauParentesco;

	@XmlJavaTypeAdapter(LocalDateTimeAdapter.class) private LocalDateTime dataDesvinculacao;
	@XmlJavaTypeAdapter(LocalDateTimeAdapter.class) private LocalDateTime dataVinculacao;

	private String mesmoEnderResponsavel;
	private String tipoResponsavel;
	private AlunoTO aluno;
	private FamiliarResponsavelTO familiar;
	private Long usuarioAlteracao;
	
	private Boolean transportaAluno;
	private Boolean tutelaAluno;
	private Boolean responsavelFinanceiroPeloAluno;
	

	public ResponsaveisAlunoTO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescDesligamento() {
		return descDesligamento;
	}

	public void setDescDesligamento(String descDesligamento) {
		this.descDesligamento = descDesligamento;
	}

	public String getDescGrauParentesco() {
		return descGrauParentesco;
	}

	public void setDescGrauParentesco(String descGrauParentesco) {
		this.descGrauParentesco = descGrauParentesco;
	}

	public LocalDateTime getDataDesvinculacao() {
		return dataDesvinculacao;
	}

	public void setDataDesvinculacao(LocalDateTime dataDesvinculacao) {
		this.dataDesvinculacao = dataDesvinculacao;
	}

	public LocalDateTime getDataVinculacao() {
		return dataVinculacao;
	}

	public void setDataVinculacao(LocalDateTime dataVinculacao) {
		this.dataVinculacao = dataVinculacao;
	}

	public String getMesmoEnderResponsavel() {
		return mesmoEnderResponsavel;
	}

	public void setMesmoEnderResponsavel(String mesmoEnderResponsavel) {
		this.mesmoEnderResponsavel = mesmoEnderResponsavel;
	}

	public String getTipoResponsavel() {
		return tipoResponsavel;
	}

	public void setTipoResponsavel(String tipoResponsavel) {
		this.tipoResponsavel = tipoResponsavel;
	}

	public AlunoTO getAluno() {
		return aluno;
	}

	public void setAluno(AlunoTO aluno) {
		this.aluno = aluno;
	}

	public FamiliarResponsavelTO getFamiliar() {
		return familiar;
	}

	public void setFamiliar(FamiliarResponsavelTO familiar) {
		this.familiar = familiar;
	}

	public Long getUsuarioAlteracao() {
		return usuarioAlteracao;
	}

	public void setUsuarioAlteracao(Long usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}

	public Boolean getTransportaAluno() {
		return transportaAluno;
	}

	public void setTransportaAluno(Boolean transportaAluno) {
		this.transportaAluno = transportaAluno;
	}

	public Boolean getTutelaAluno() {
		return tutelaAluno;
	}

	public void setTutelaAluno(Boolean tutelaAluno) {
		this.tutelaAluno = tutelaAluno;
	}

	public Boolean getResponsavelFinanceiroPeloAluno() {
		return responsavelFinanceiroPeloAluno;
	}

	public void setResponsavelFinanceiroPeloAluno(Boolean responsavelFinanceiroPeloAluno) {
		this.responsavelFinanceiroPeloAluno = responsavelFinanceiroPeloAluno;
	}
	
	

}
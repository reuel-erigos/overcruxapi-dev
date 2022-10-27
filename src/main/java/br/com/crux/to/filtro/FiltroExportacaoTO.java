package br.com.crux.to.filtro;

import java.time.LocalDateTime;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import br.com.crux.infra.adapter.LocalDateTimeAdapter;

public class FiltroExportacaoTO {
	
	private String beneficiario;
	
	private String cpfAluno;
	
	private String maeAluno;
	
	private String paiAluno;
	
	@XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
	private LocalDateTime dataInicioEntradaInstituicao;
	
	@XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
	private LocalDateTime dataFimEntradaInstituicao;
	
	@XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
	private LocalDateTime dataInicioVigenciaInstituicao;
	
	@XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
	private LocalDateTime dataFimVigenciaInstituicao;
	
	private Boolean ativo;
	
	private Long unidade;
	
	private Long projeto;
	
	private Long programa;

	public String getBeneficiario() {
		return beneficiario;
	}

	public void setBeneficiario(String beneficiario) {
		this.beneficiario = beneficiario;
	}

	public String getCpfAluno() {
		return cpfAluno;
	}

	public void setCpfAluno(String cpfAluno) {
		this.cpfAluno = cpfAluno;
	}

	public String getMaeAluno() {
		return maeAluno;
	}

	public void setMaeAluno(String maeAluno) {
		this.maeAluno = maeAluno;
	}

	public String getPaiAluno() {
		return paiAluno;
	}

	public void setPaiAluno(String paiAluno) {
		this.paiAluno = paiAluno;
	}

	public LocalDateTime getDataInicioEntradaInstituicao() {
		return dataInicioEntradaInstituicao;
	}

	public void setDataInicioEntradaInstituicao(LocalDateTime dataInicioEntradaInstituicao) {
		this.dataInicioEntradaInstituicao = dataInicioEntradaInstituicao;
	}

	public LocalDateTime getDataFimEntradaInstituicao() {
		return dataFimEntradaInstituicao;
	}

	public void setDataFimEntradaInstituicao(LocalDateTime dataFimEntradaInstituicao) {
		this.dataFimEntradaInstituicao = dataFimEntradaInstituicao;
	}

	public LocalDateTime getDataInicioVigenciaInstituicao() {
		return dataInicioVigenciaInstituicao;
	}

	public void setDataInicioVigenciaInstituicao(LocalDateTime dataInicioVigenciaInstituicao) {
		this.dataInicioVigenciaInstituicao = dataInicioVigenciaInstituicao;
	}

	public LocalDateTime getDataFimVigenciaInstituicao() {
		return dataFimVigenciaInstituicao;
	}

	public void setDataFimVigenciaInstituicao(LocalDateTime dataFimVigenciaInstituicao) {
		this.dataFimVigenciaInstituicao = dataFimVigenciaInstituicao;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public Long getUnidade() {
		return unidade;
	}

	public void setUnidade(Long unidade) {
		this.unidade = unidade;
	}

	public Long getProjeto() {
		return projeto;
	}

	public void setProjeto(Long projeto) {
		this.projeto = projeto;
	}

	public Long getPrograma() {
		return programa;
	}

	public void setPrograma(Long programa) {
		this.programa = programa;
	}
	
}

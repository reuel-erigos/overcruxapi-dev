package br.com.crux.to.filtro;

import java.time.LocalDateTime;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import br.com.crux.infra.adapter.LocalDateTimeAdapter;

public class FiltroAlunoTO {

	private String beneficiario;
	
	private String mae;
	
	private String cpf;
	
	@XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
	private LocalDateTime dataInicioEntradaInstituicao;
	
	@XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
	private LocalDateTime dataFimEntradaInstituicao;
	
	private Long idUnidade;

	private Long idInstituicao;

	public String getBeneficiario() {
		return beneficiario;
	}

	public void setBeneficiario(String beneficiario) {
		this.beneficiario = beneficiario;
	}

	public String getMae() {
		return mae;
	}

	public void setMae(String mae) {
		this.mae = mae;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
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

	public Long getIdUnidade() {
		return idUnidade;
	}

	public void setIdUnidade(Long idUnidade) {
		this.idUnidade = idUnidade;
	}

	public Long getIdInstituicao() {
		return idInstituicao;
	}

	public void setIdInstituicao(Long idInstituicao) {
		this.idInstituicao = idInstituicao;
	}
	
}

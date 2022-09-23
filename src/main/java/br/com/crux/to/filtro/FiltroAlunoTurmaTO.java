package br.com.crux.to.filtro;

public class FiltroAlunoTurmaTO {

	private String beneficiario;
	
	private String cpf;
	
	private Long idOficina;
	
	private Long idTurma;
	
	private Long idInstituicao;

	public String getBeneficiario() {
		return beneficiario;
	}

	public void setBeneficiario(String beneficiario) {
		this.beneficiario = beneficiario;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Long getIdOficina() {
		return idOficina;
	}

	public void setIdOficina(Long idOficina) {
		this.idOficina = idOficina;
	}

	public Long getIdTurma() {
		return idTurma;
	}

	public void setIdTurma(Long idTurma) {
		this.idTurma = idTurma;
	}

	public Long getIdInstituicao() {
		return idInstituicao;
	}

	public void setIdInstituicao(Long idInstituicao) {
		this.idInstituicao = idInstituicao;
	}

}

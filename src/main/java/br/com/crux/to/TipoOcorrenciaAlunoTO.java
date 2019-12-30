package br.com.crux.to;

public class TipoOcorrenciaAlunoTO {

	private Long id;
	private String tipoOcorrencia;
	private Boolean informaSAP;
	private Long usuarioAlteracao;

	public TipoOcorrenciaAlunoTO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTipoOcorrencia() {
		return tipoOcorrencia;
	}

	public void setTipoOcorrencia(String tipoOcorrencia) {
		this.tipoOcorrencia = tipoOcorrencia;
	}

	public Boolean getInformaSAP() {
		return informaSAP;
	}

	public void setInformaSAP(Boolean informaSAP) {
		this.informaSAP = informaSAP;
	}

	public Long getUsuarioAlteracao() {
		return usuarioAlteracao;
	}

	public void setUsuarioAlteracao(Long usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}

	


}
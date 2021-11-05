package br.com.crux.to.relatorios.financeiro;

public class MovimentacaoContabilTO {
	
	private String nomeProgramaProjeto  ;
	private String numeroDocumento      ;
	private String dataDocumento        ;
	private String descricaoCategoria   ;
	private String dataMovimentacao     ;
	private Double valorCategoria       ;
	private String contaDestino         ;
	private String contaOrigem          ;
	private Long   idMovimentacao       ;
	private Long   idCategoriaOrigem    ;
	private Long   idCategoriaDestino   ;
	private Long   idPrograma           ;
	private Long   idProjeto            ;
	
	public MovimentacaoContabilTO() {
	}

	public String getNomeProgramaProjeto() {
		return nomeProgramaProjeto;
	}

	public void setNomeProgramaProjeto(String nomeProgramaProjeto) {
		this.nomeProgramaProjeto = nomeProgramaProjeto;
	}

	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public String getDataDocumento() {
		return dataDocumento;
	}

	public void setDataDocumento(String dataDocumento) {
		this.dataDocumento = dataDocumento;
	}

	public String getDescricaoCategoria() {
		return descricaoCategoria;
	}

	public void setDescricaoCategoria(String descricaoCategoria) {
		this.descricaoCategoria = descricaoCategoria;
	}

	public String getDataMovimentacao() {
		return dataMovimentacao;
	}

	public void setDataMovimentacao(String dataMovimentacao) {
		this.dataMovimentacao = dataMovimentacao;
	}

	public Double getValorCategoria() {
		return valorCategoria;
	}

	public void setValorCategoria(Double valorCategoria) {
		this.valorCategoria = valorCategoria;
	}

	public String getContaDestino() {
		return contaDestino;
	}

	public void setContaDestino(String contaDestino) {
		this.contaDestino = contaDestino;
	}

	public String getContaOrigem() {
		return contaOrigem;
	}

	public void setContaOrigem(String contaOrigem) {
		this.contaOrigem = contaOrigem;
	}

	public Long getIdMovimentacao() {
		return idMovimentacao;
	}

	public void setIdMovimentacao(Long idMovimentacao) {
		this.idMovimentacao = idMovimentacao;
	}

	public Long getIdCategoriaOrigem() {
		return idCategoriaOrigem;
	}

	public void setIdCategoriaOrigem(Long idCategoriaOrigem) {
		this.idCategoriaOrigem = idCategoriaOrigem;
	}

	public Long getIdCategoriaDestino() {
		return idCategoriaDestino;
	}

	public void setIdCategoriaDestino(Long idCategoriaDestino) {
		this.idCategoriaDestino = idCategoriaDestino;
	}

	public Long getIdPrograma() {
		return idPrograma;
	}

	public void setIdPrograma(Long idPrograma) {
		this.idPrograma = idPrograma;
	}

	public Long getIdProjeto() {
		return idProjeto;
	}

	public void setIdProjeto(Long idProjeto) {
		this.idProjeto = idProjeto;
	}

	@Override
	public String toString() {
		return "MovimentacaoContabilTO [nomeProgramaProjeto=" + nomeProgramaProjeto + ", numeroDocumento="
				+ numeroDocumento + ", dataDocumento=" + dataDocumento + ", descricaoCategoria=" + descricaoCategoria
				+ ", dataMovimentacao=" + dataMovimentacao + ", valorCategoria=" + valorCategoria + ", contaDestino="
				+ contaDestino + ", contaOrigem=" + contaOrigem + ", idMovimentacao=" + idMovimentacao
				+ ", idCategoriaOrigem=" + idCategoriaOrigem + ", idCategoriaDestino=" + idCategoriaDestino
				+ ", idPrograma=" + idPrograma + ", idProjeto=" + idProjeto + "]";
	}


	

}

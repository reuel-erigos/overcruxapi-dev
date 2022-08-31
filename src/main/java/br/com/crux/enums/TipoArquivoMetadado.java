package br.com.crux.enums;

public enum TipoArquivoMetadado {

	CABECALHO_RELATORIO("CR"), RODAPE_RELATORIO("RR"), LOGOMARCA_INSTITUICAO("LI"), LOGOMARCA_UNIDADE("LU");

	private String codigo;

	private TipoArquivoMetadado(String codigo) {
		this.codigo = codigo;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public static TipoArquivoMetadado getPorCodigo(String codigo) {
		for (TipoArquivoMetadado tipoClassificador : values()) {
			if (tipoClassificador.getCodigo().equals(codigo)) {
				return tipoClassificador;
			}
		}
		return null;
	}

}

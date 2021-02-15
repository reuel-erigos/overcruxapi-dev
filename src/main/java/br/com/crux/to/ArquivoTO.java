package br.com.crux.to;

public class ArquivoTO  {

	private Long id;
	private byte[] blob;
	private ArquivoMetadadoTO metadados;

	
	public ArquivoTO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public byte[] getBlob() {
		return blob;
	}

	public void setBlob(byte[] blob) {
		this.blob = blob;
	}

	public ArquivoMetadadoTO getMetadados() {
		return metadados;
	}

	public void setMetadados(ArquivoMetadadoTO metadados) {
		this.metadados = metadados;
	}
	

}
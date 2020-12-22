package br.com.crux.to.relatorios.gestao_pessoal;

public class AniversariantesDTO {

	private String nomeFuncionario;
	private String diaMes;
	
	public AniversariantesDTO() {
	}
	
	public AniversariantesDTO(Object[] colunas) {
		this.nomeFuncionario         = (colunas[0] != null) ? (String) colunas[0] : "";
		this.diaMes                  = (colunas[1] != null) ? (String) colunas[1] : "";
	}

	public String getNomeFuncionario() {
		return nomeFuncionario;
	}

	public void setNomeFuncionario(String nomeFuncionario) {
		this.nomeFuncionario = nomeFuncionario;
	}

	public String getDiaMes() {
		return diaMes;
	}

	public void setDiaMes(String diaMes) {
		this.diaMes = diaMes;
	}
	
	
}

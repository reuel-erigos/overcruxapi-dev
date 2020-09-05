package br.com.crux.to;

import java.time.LocalDateTime;


public class DoadoresTO {

	private Long id;
	private PessoaFisicaTO pessoasFisica;
	private EmpresaTO empresa;
	private TiposDoadoresTO tipoDoador;
	private LocalDateTime dataInicioVinculo;
	private LocalDateTime dataFimVinculo;
	private Long idInstituicao;
	private Long usuarioAlteracao;
	
	
	public DoadoresTO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public PessoaFisicaTO getPessoasFisica() {
		return pessoasFisica;
	}

	public void setPessoasFisica(PessoaFisicaTO pessoasFisica) {
		this.pessoasFisica = pessoasFisica;
	}

	public EmpresaTO getEmpresa() {
		return empresa;
	}

	public void setEmpresa(EmpresaTO empresa) {
		this.empresa = empresa;
	}

	public TiposDoadoresTO getTipoDoador() {
		return tipoDoador;
	}

	public void setTipoDoador(TiposDoadoresTO tipoDoador) {
		this.tipoDoador = tipoDoador;
	}

	public LocalDateTime getDataInicioVinculo() {
		return dataInicioVinculo;
	}

	public void setDataInicioVinculo(LocalDateTime dataInicioVinculo) {
		this.dataInicioVinculo = dataInicioVinculo;
	}

	public LocalDateTime getDataFimVinculo() {
		return dataFimVinculo;
	}

	public void setDataFimVinculo(LocalDateTime dataFimVinculo) {
		this.dataFimVinculo = dataFimVinculo;
	}

	public Long getIdInstituicao() {
		return idInstituicao;
	}

	public void setIdInstituicao(Long idInstituicao) {
		this.idInstituicao = idInstituicao;
	}

	public Long getUsuarioAlteracao() {
		return usuarioAlteracao;
	}

	public void setUsuarioAlteracao(Long usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}

}
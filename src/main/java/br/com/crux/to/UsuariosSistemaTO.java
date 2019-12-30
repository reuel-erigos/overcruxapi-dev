package br.com.crux.to;

import java.time.LocalDateTime;
import java.util.List;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import br.com.crux.infra.adapter.LocalDateTimeAdapter;

public class UsuariosSistemaTO {
	
	
	private Long id;
	private String nomeUsuario;
	private String senhaUsuario;
	private String descFimVigenciaUsuario;
	
	@XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
	private LocalDateTime dataFimVigencia;
	@XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
	private LocalDateTime dataInicioVigencia;
	@XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
	private LocalDateTime dataUltimoAcesso;
	
	private Long qtdAcessoNegado;
	private Boolean stAtivo;
	private Boolean stTrocaSenha;
	
	private PessoaFisicaTO pessoaFisica;
	private Long usuarioAlteracao;
	
	private List<UsuariosUnidadesTO> unidades;
	
	
	public UsuariosSistemaTO() {
	}

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getSenhaUsuario() {
		return senhaUsuario;
	}

	public void setSenhaUsuario(String senhaUsuario) {
		this.senhaUsuario = senhaUsuario;
	}

	public String getDescFimVigenciaUsuario() {
		return descFimVigenciaUsuario;
	}

	public void setDescFimVigenciaUsuario(String descFimVigenciaUsuario) {
		this.descFimVigenciaUsuario = descFimVigenciaUsuario;
	}

	public LocalDateTime getDataFimVigencia() {
		return dataFimVigencia;
	}

	public void setDataFimVigencia(LocalDateTime dataFimVigencia) {
		this.dataFimVigencia = dataFimVigencia;
	}

	public LocalDateTime getDataInicioVigencia() {
		return dataInicioVigencia;
	}

	public void setDataInicioVigencia(LocalDateTime dataInicioVigencia) {
		this.dataInicioVigencia = dataInicioVigencia;
	}

	public LocalDateTime getDataUltimoAcesso() {
		return dataUltimoAcesso;
	}

	public void setDataUltimoAcesso(LocalDateTime dataUltimoAcesso) {
		this.dataUltimoAcesso = dataUltimoAcesso;
	}

	public Long getQtdAcessoNegado() {
		return qtdAcessoNegado;
	}

	public void setQtdAcessoNegado(Long qtdAcessoNegado) {
		this.qtdAcessoNegado = qtdAcessoNegado;
	}

	public Boolean getStAtivo() {
		return stAtivo;
	}

	public void setStAtivo(Boolean stAtivo) {
		this.stAtivo = stAtivo;
	}

	public Boolean getStTrocaSenha() {
		return stTrocaSenha;
	}

	public void setStTrocaSenha(Boolean stTrocaSenha) {
		this.stTrocaSenha = stTrocaSenha;
	}

	public PessoaFisicaTO getPessoaFisica() {
		return pessoaFisica;
	}

	public void setPessoaFisica(PessoaFisicaTO pessoaFisica) {
		this.pessoaFisica = pessoaFisica;
	}

	public Long getUsuarioAlteracao() {
		return usuarioAlteracao;
	}

	public void setUsuarioAlteracao(Long usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}

	public List<UsuariosUnidadesTO> getUnidades() {
		return unidades;
	}

	public void setUnidades(List<UsuariosUnidadesTO> usuariosUnidades) {
		this.unidades = usuariosUnidades;
	}
	

	
}

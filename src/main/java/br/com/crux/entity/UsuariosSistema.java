package br.com.crux.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.crux.infra.constantes.Constantes;
import br.com.crux.infra.dao.SimNaoConverter;

@Entity
@Table(name="usuarios_sistema")
public class UsuariosSistema {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_id_usuario")
	@SequenceGenerator(name = "sq_id_usuario", sequenceName = "sq_id_usuario", schema = Constantes.SCHEMA_PUBLIC, initialValue = 1, allocationSize = 1)
	@Column(name="id_usuario")
	private Long idUsuario;

	@Column(name="nm_username")
	private String username;
	
	@Column(name="ds_fim_vigencia_usuario")
	private String descFimVigenciaUsuario;

	@Column(name="ds_senha")
	private String senha;

	@Column(name="dt_fim_vigencia")
	private LocalDateTime dataFimVigencia;

	@Column(name="dt_inicio_vigencia")
	private LocalDateTime dataInicioVigencia;

	@Column(name="dt_ultimo_acesso")
	private LocalDateTime dataUltimoAcesso;

	@Column(name="qtd_acesso_negado")
	private Long qtdAcessoNegado;

	@Column(name="st_ativo")
	@Convert(converter = SimNaoConverter.class)
	private Boolean stAtivo;

	@Column(name="st_troca_senha")
	@Convert(converter = SimNaoConverter.class)
	private Boolean stTrocaSenha;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_pessoa_fisica")
	private PessoaFisica pessoaFisica;

	@OneToMany(mappedBy="usuarioSistema")
	private List<UsuariosUnidade> usuariosUnidades;

	@Column(name = "id_usuario_apl")
	private Long usuarioAlteracao;
	
	public UsuariosSistema() {
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getDescFimVigenciaUsuario() {
		return descFimVigenciaUsuario;
	}

	public void setDescFimVigenciaUsuario(String descFimVigenciaUsuario) {
		this.descFimVigenciaUsuario = descFimVigenciaUsuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
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

	public PessoaFisica getPessoaFisica() {
		return pessoaFisica;
	}

	public void setPessoaFisica(PessoaFisica pessoaFisica) {
		this.pessoaFisica = pessoaFisica;
	}

	public List<UsuariosUnidade> getUsuariosUnidades() {
		return usuariosUnidades;
	}

	public void setUsuariosUnidades(List<UsuariosUnidade> usuariosUnidades) {
		this.usuariosUnidades = usuariosUnidades;
	}

	public Long getUsuarioAlteracao() {
		return usuarioAlteracao;
	}

	public void setUsuarioAlteracao(Long usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}

}
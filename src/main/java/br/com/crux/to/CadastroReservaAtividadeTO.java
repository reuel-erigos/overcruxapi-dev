package br.com.crux.to;

import java.time.LocalDateTime;

public class CadastroReservaAtividadeTO {

	private Long id;
	private String descricaoCadastroReserva;
	private String descricaoCancelamentoCadastro;

	private LocalDateTime dataCadastroReserva;
	private LocalDateTime dataCancelamentoCadastro;
	private LocalDateTime dataCadastroAtividade;
	private LocalDateTime dtAlteracaoAtividade;

	private AtividadesTO atividade;
	private PessoaFisicaTO pessoasFisica;

	private Long usuarioAlteracao;

	public CadastroReservaAtividadeTO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricaoCadastroReserva() {
		return descricaoCadastroReserva;
	}

	public void setDescricaoCadastroReserva(String descricaoCadastroReserva) {
		this.descricaoCadastroReserva = descricaoCadastroReserva;
	}

	public String getDescricaoCancelamentoCadastro() {
		return descricaoCancelamentoCadastro;
	}

	public void setDescricaoCancelamentoCadastro(String descricaoCancelamentoCadastro) {
		this.descricaoCancelamentoCadastro = descricaoCancelamentoCadastro;
	}

	public LocalDateTime getDataCadastroReserva() {
		return dataCadastroReserva;
	}

	public void setDataCadastroReserva(LocalDateTime dataCadastroReserva) {
		this.dataCadastroReserva = dataCadastroReserva;
	}

	public LocalDateTime getDataCancelamentoCadastro() {
		return dataCancelamentoCadastro;
	}

	public void setDataCancelamentoCadastro(LocalDateTime dataCancelamentoCadastro) {
		this.dataCancelamentoCadastro = dataCancelamentoCadastro;
	}

	public LocalDateTime getDataCadastroAtividade() {
		return dataCadastroAtividade;
	}

	public void setDataCadastroAtividade(LocalDateTime dataCadastroAtividade) {
		this.dataCadastroAtividade = dataCadastroAtividade;
	}

	public LocalDateTime getDtAlteracaoAtividade() {
		return dtAlteracaoAtividade;
	}

	public void setDtAlteracaoAtividade(LocalDateTime dtAlteracaoAtividade) {
		this.dtAlteracaoAtividade = dtAlteracaoAtividade;
	}

	public AtividadesTO getAtividade() {
		return atividade;
	}

	public void setAtividade(AtividadesTO atividade) {
		this.atividade = atividade;
	}

	public PessoaFisicaTO getPessoasFisica() {
		return pessoasFisica;
	}

	public void setPessoasFisica(PessoaFisicaTO pessoasFisica) {
		this.pessoasFisica = pessoasFisica;
	}

	public Long getUsuarioAlteracao() {
		return usuarioAlteracao;
	}

	public void setUsuarioAlteracao(Long usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}

}

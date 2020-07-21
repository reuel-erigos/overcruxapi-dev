package br.com.crux.to;

import java.time.LocalDateTime;

public class CadastroReservaAtividadeTO {

	private Long id;
	private LocalDateTime dataCadastroReserva;
	private String descricaoCadastroReserva;
	private LocalDateTime dataCancelamentoCadastro;
	private String descricaoCancelamentoCadastro;
	private OficinasTO atividade;
	private String nomeInteressado;
	private String dadosTelefone;
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

	public OficinasTO getAtividade() {
		return atividade;
	}

	public void setAtividade(OficinasTO atividade) {
		this.atividade = atividade;
	}

	public Long getUsuarioAlteracao() {
		return usuarioAlteracao;
	}

	public void setUsuarioAlteracao(Long usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}

	public String getNomeInteressado() {
		return nomeInteressado;
	}

	public void setNomeInteressado(String nomeInteressado) {
		this.nomeInteressado = nomeInteressado;
	}

	public String getDadosTelefone() {
		return dadosTelefone;
	}

	public void setDadosTelefone(String dadosTelefone) {
		this.dadosTelefone = dadosTelefone;
	}

	
}

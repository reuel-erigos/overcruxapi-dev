package br.com.crux.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.crux.infra.constantes.Constantes;

@Entity
@Table(name = "cadastro_reserva_atividades")
public class CadastroReservaAtividade {

	@Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_id_cadastro_reserva") @SequenceGenerator(name = "sq_id_cadastro_reserva", sequenceName = "sq_id_cadastro_reserva", schema = Constantes.SCHEMA_PUBLIC, initialValue = 1, allocationSize = 1) @Column(name = "id_cadastro_reserva") private Long id;

	@Column(name = "dt_cadastro_reserva") private LocalDateTime dataCadastroReserva;

	@Column(name = "ds_cadastro_reserva") private String descricaoCadastroReserva;

	@Column(name = "dt_cancelamento_cadastro") private LocalDateTime dataCancelamentoCadastro;

	@Column(name = "dt_cadastro_atividade") private LocalDateTime dataCadastroAtividade;

	@Column(name = "ds_cancelamento_cadastro") private String descricaoCancelamentoCadastro;

	@Column(name = "dt_alteracao_atividade") private LocalDateTime dtAlteracaoAtividade;

	@ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "id_atividade") private Atividades atividade;

	@ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "pf_id_pessoa_fisica") private PessoaFisica pessoasFisica;

	@Column(name = "id_usuario_apl") private Long usuarioAlteracao;

	public CadastroReservaAtividade() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getDataCadastroReserva() {
		return dataCadastroReserva;
	}

	public void setDataCadastroReserva(LocalDateTime dataCadastroReserva) {
		this.dataCadastroReserva = dataCadastroReserva;
	}

	public String getDescricaoCadastroReserva() {
		return descricaoCadastroReserva;
	}

	public void setDescricaoCadastroReserva(String descricaoCadastroReserva) {
		this.descricaoCadastroReserva = descricaoCadastroReserva;
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

	public String getDescricaoCancelamentoCadastro() {
		return descricaoCancelamentoCadastro;
	}

	public void setDescricaoCancelamentoCadastro(String descricaoCancelamentoCadastro) {
		this.descricaoCancelamentoCadastro = descricaoCancelamentoCadastro;
	}

	public LocalDateTime getDtAlteracaoAtividade() {
		return dtAlteracaoAtividade;
	}

	public void setDtAlteracaoAtividade(LocalDateTime dtAlteracaoAtividade) {
		this.dtAlteracaoAtividade = dtAlteracaoAtividade;
	}

	public Atividades getAtividade() {
		return atividade;
	}

	public void setAtividade(Atividades atividade) {
		this.atividade = atividade;
	}

	public PessoaFisica getPessoasFisica() {
		return pessoasFisica;
	}

	public void setPessoasFisica(PessoaFisica pessoasFisica) {
		this.pessoasFisica = pessoasFisica;
	}

	public Long getUsuarioAlteracao() {
		return usuarioAlteracao;
	}

	public void setUsuarioAlteracao(Long usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}

}
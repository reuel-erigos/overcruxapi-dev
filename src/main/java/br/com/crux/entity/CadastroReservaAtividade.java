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

	@Id 
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_id_cadastro_reserva") 
	@SequenceGenerator(name = "sq_id_cadastro_reserva", sequenceName = "sq_id_cadastro_reserva", schema = Constantes.SCHEMA_PUBLIC, initialValue = 1, allocationSize = 1) 
	@Column(name = "id_cadastro_reserva") 
	private Long id;

	@Column(name = "dt_cadastro_reserva") 
	private LocalDateTime dataCadastroReserva;

	@Column(name = "ds_cadastro_reserva") 
	private String descricaoCadastroReserva;

	@Column(name = "dt_cancelamento_cadastro") 
	private LocalDateTime dataCancelamentoCadastro;

	@Column(name = "ds_cancelamento_cadastro") 
	private String descricaoCancelamentoCadastro;
	
	@ManyToOne(fetch = FetchType.LAZY) 
	@JoinColumn(name = "id_atividade") 
	private Oficinas atividade;

	@Column(name = "nm_interessado") 
	private String nomeInteressado;
	
	@Column(name = "ds_telefone") 
	private String dadosTelefone;
	
	@Column(name = "id_usuario_apl") 
	private Long usuarioAlteracao;

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

	public String getDescricaoCancelamentoCadastro() {
		return descricaoCancelamentoCadastro;
	}

	public void setDescricaoCancelamentoCadastro(String descricaoCancelamentoCadastro) {
		this.descricaoCancelamentoCadastro = descricaoCancelamentoCadastro;
	}

	public Oficinas getAtividade() {
		return atividade;
	}

	public void setAtividade(Oficinas atividade) {
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
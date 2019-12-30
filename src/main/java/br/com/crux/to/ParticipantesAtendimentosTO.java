package br.com.crux.to;

public class ParticipantesAtendimentosTO  {

	private Long id;
	private AtendimentosTO atendimento;
	private FamiliaresTO familiar;
	private FuncionarioTO funcionario;
	private Long usuarioAlteracao;

	public ParticipantesAtendimentosTO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public AtendimentosTO getAtendimento() {
		return atendimento;
	}

	public void setAtendimento(AtendimentosTO atendimento) {
		this.atendimento = atendimento;
	}

	public FamiliaresTO getFamiliar() {
		return familiar;
	}

	public void setFamiliar(FamiliaresTO familiar) {
		this.familiar = familiar;
	}

	public FuncionarioTO getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(FuncionarioTO funcionario) {
		this.funcionario = funcionario;
	}

	public Long getUsuarioAlteracao() {
		return usuarioAlteracao;
	}

	public void setUsuarioAlteracao(Long idUsuarioApl) {
		this.usuarioAlteracao = idUsuarioApl;
	}


}
package br.com.crux.builder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.cmd.GetAtividadeCmd;
import br.com.crux.cmd.GetPessoaFisicaCmd;
import br.com.crux.entity.Atividades;
import br.com.crux.entity.CadastroReservaAtividade;
import br.com.crux.entity.PessoaFisica;
import br.com.crux.to.CadastroReservaAtividadeTO;

@Component
public class CadastroReservaAtividadeTOBuilder {

	@Autowired private AtividadesTOBuilder atividadeBuilder;
	@Autowired private PessoaFisicaTOBuilder pessoaFisicaBuilder;
	@Autowired private GetAtividadeCmd getAtividadeCmd;
	@Autowired private GetPessoaFisicaCmd getPessoaFisicaCmd;

	
	public CadastroReservaAtividade build(CadastroReservaAtividadeTO p) {
		CadastroReservaAtividade retorno = new CadastroReservaAtividade();

		retorno.setId(p.getId());
		retorno.setDescricaoCadastroReserva(p.getDescricaoCadastroReserva());
		retorno.setDescricaoCancelamentoCadastro(p.getDescricaoCancelamentoCadastro());
		retorno.setDataCadastroReserva(p.getDataCadastroReserva());
		retorno.setDataCancelamentoCadastro(p.getDataCancelamentoCadastro());
		retorno.setDtAlteracaoAtividade(LocalDateTime.now());
		
		Optional.ofNullable(p.getAtividade()).ifPresent(atividade -> {
			Atividades atv = getAtividadeCmd.getById(atividade.getId());
			retorno.setDataCadastroAtividade(atv.getDataInicio());
			retorno.setAtividade(atv);
		});
		
		Optional.ofNullable(p.getPessoasFisica()).ifPresent(pessoa -> {
			PessoaFisica pf = getPessoaFisicaCmd.getById(pessoa.getId());
			retorno.setPessoasFisica(pf);
		});
		
		retorno.setUsuarioAlteracao(p.getUsuarioAlteracao());

		return retorno;
	}

	public CadastroReservaAtividadeTO buildTO(CadastroReservaAtividade p) {
		CadastroReservaAtividadeTO retorno = new CadastroReservaAtividadeTO();
		
		if(Objects.isNull(p)) {
			return retorno;
		}
		
		retorno.setId(p.getId());
		retorno.setDescricaoCadastroReserva(p.getDescricaoCadastroReserva());
		retorno.setDescricaoCancelamentoCadastro(p.getDescricaoCancelamentoCadastro());
		retorno.setDataCadastroReserva(p.getDataCadastroReserva());
		retorno.setDataCancelamentoCadastro(p.getDataCancelamentoCadastro());
		retorno.setDataCadastroAtividade(p.getDataCadastroAtividade());
		retorno.setDtAlteracaoAtividade(p.getDtAlteracaoAtividade());
		
		retorno.setAtividade(atividadeBuilder.buildTO(p.getAtividade()));
		retorno.setPessoasFisica(pessoaFisicaBuilder.buildTO(p.getPessoasFisica()));
		
		retorno.setUsuarioAlteracao(p.getUsuarioAlteracao());

		return retorno;
	}

	public List<CadastroReservaAtividadeTO> buildAll(List<CadastroReservaAtividade> dtos) {
		return dtos.stream().map(dto -> buildTO(dto)).collect(Collectors.toList());
	}

}

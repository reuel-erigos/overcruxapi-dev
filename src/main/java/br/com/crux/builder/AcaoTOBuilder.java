package br.com.crux.builder;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.cmd.GetAtividadeCmd;
import br.com.crux.cmd.GetFuncionarioCmd;
import br.com.crux.entity.Acoes;
import br.com.crux.entity.Atividades;
import br.com.crux.entity.Funcionario;
import br.com.crux.to.AcaoTO;

@Component
public class AcaoTOBuilder {

	@Autowired private AtividadesTOBuilder atividadeBuilder;
	@Autowired private FuncionarioTOBuilder funcionarioTOBuilder;
	@Autowired private GetAtividadeCmd getAtividadeCmd;
	@Autowired private GetFuncionarioCmd getFuncionarioCmd;

	public Acoes build(AcaoTO p) {
		Acoes retorno = new Acoes();

		retorno.setId(p.getId());
		retorno.setDescricao(p.getDescricao());
		
		retorno.setDataInicio(p.getDataInicio());
		retorno.setDataFim(p.getDataFim());
		retorno.setDataPrevisaoInicio(p.getDataPrevisaoInicio());
		retorno.setDataPrevisaoFim(p.getDataPrevisaoFim());
		retorno.setDataAprovaAcao(p.getDataAprovaAcao());
		
		retorno.setDescricaoAvaliacaoAcao(p.getDescricaoAvaliacaoAcao());
		retorno.setDescricaoMetodologiaAcao(p.getDescricaoMetodologiaAcao());
		retorno.setDescricaoObjetivoAcao(p.getDescricaoObjetivoAcao());
		retorno.setDescricaoOcorrenciaAcao(p.getDescricaoOcorrenciaAcao());
		
		Optional.ofNullable(p.getFuncionarioAprovaAcao()).ifPresent(func -> {
			if(Objects.nonNull(func.getId())) {
				Funcionario funcionario = getFuncionarioCmd.getById(func.getId());
				retorno.setFuncionarioAprovaAcao(funcionario);
			}
		});
		
		Optional.ofNullable(p.getFuncionarioExecutaAcao()).ifPresent(func -> {
			if(Objects.nonNull(func.getId())) {
				Funcionario funcionario = getFuncionarioCmd.getById(func.getId());
				retorno.setFuncionarioExecutaAcao(funcionario);
			}
		});
		
		Optional.ofNullable(p.getFuncionarioPlanejamentoAcao()).ifPresent(func -> {
			if(Objects.nonNull(func.getId())) {
				Funcionario funcionario = getFuncionarioCmd.getById(func.getId());
				retorno.setFuncionarioPlanejamentoAcao(funcionario);
			}
		});


		Optional.ofNullable(p.getAtividade()).ifPresent(atv -> {
			Atividades atividade = getAtividadeCmd.getById(atv.getId());
			retorno.setAtividade(atividade);
		});

		retorno.setUsuarioAlteracao(p.getUsuarioAlteracao());

		return retorno;
	}

	public AcaoTO buildTO(Acoes p) {
		AcaoTO retorno = new AcaoTO();

		if (Objects.isNull(p)) {
			return retorno;
		}

		retorno.setId(p.getId());
		retorno.setDescricao(p.getDescricao());
		
		retorno.setDataInicio(p.getDataInicio());
		retorno.setDataFim(p.getDataFim());
		retorno.setDataPrevisaoInicio(p.getDataPrevisaoInicio());
		retorno.setDataPrevisaoFim(p.getDataPrevisaoFim());
		retorno.setDataAprovaAcao(p.getDataAprovaAcao());
		
		retorno.setDescricaoAvaliacaoAcao(p.getDescricaoAvaliacaoAcao());
		retorno.setDescricaoMetodologiaAcao(p.getDescricaoMetodologiaAcao());
		retorno.setDescricaoObjetivoAcao(p.getDescricaoObjetivoAcao());
		retorno.setDescricaoOcorrenciaAcao(p.getDescricaoOcorrenciaAcao());
		
		retorno.setAtividade(atividadeBuilder.buildTO(p.getAtividade()));
		
		retorno.setFuncionarioAprovaAcao(funcionarioTOBuilder.buildTO(p.getFuncionarioAprovaAcao()));
		retorno.setFuncionarioExecutaAcao(funcionarioTOBuilder.buildTO(p.getFuncionarioExecutaAcao()));
		retorno.setFuncionarioPlanejamentoAcao(funcionarioTOBuilder.buildTO(p.getFuncionarioPlanejamentoAcao()));

		retorno.setUsuarioAlteracao(p.getUsuarioAlteracao());

		return retorno;
	}

	public List<AcaoTO> buildAll(List<Acoes> dtos) {
		return dtos.stream().map(dto -> buildTO(dto)).collect(Collectors.toList());
	}

}

package br.com.crux.builder;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.cmd.GetAtividadeCmd;
import br.com.crux.entity.Acoes;
import br.com.crux.entity.Atividades;
import br.com.crux.to.AcaoTO;

@Component
public class AcaoTOBuilder {

	@Autowired private AtividadesTOBuilder atividadeBuilder;
	@Autowired private GetAtividadeCmd getAtividadeCmd;

	public Acoes build(AcaoTO p) {
		Acoes retorno = new Acoes();

		retorno.setId(p.getId());
		retorno.setNome(p.getNome());
		retorno.setDataInicio(p.getDataInicio());
		retorno.setDataFim(p.getDataFim());
		retorno.setDataPrevisaoInicio(p.getDataPrevisaoInicio());
		retorno.setDataPrevisaoFim(p.getDataPrevisaoFim());

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
		retorno.setNome(p.getNome());
		retorno.setDataInicio(p.getDataInicio());
		retorno.setDataFim(p.getDataFim());
		retorno.setDataPrevisaoInicio(p.getDataPrevisaoInicio());
		retorno.setDataPrevisaoFim(p.getDataPrevisaoFim());
		retorno.setAtividade(atividadeBuilder.buildTO(p.getAtividade()));

		retorno.setUsuarioAlteracao(p.getUsuarioAlteracao());

		return retorno;
	}

	public List<AcaoTO> buildAll(List<Acoes> dtos) {
		return dtos.stream().map(dto -> buildTO(dto)).collect(Collectors.toList());
	}

}

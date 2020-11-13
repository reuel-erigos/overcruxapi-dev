package br.com.crux.builder;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.cmd.GetContasBancariaCmd;
import br.com.crux.cmd.GetParceriasProgramaCmd;
import br.com.crux.cmd.GetParceriasProjetoCmd;
import br.com.crux.cmd.GetUsuarioLogadoCmd;
import br.com.crux.entity.ContasCentrosCusto;
import br.com.crux.entity.ParceriasPrograma;
import br.com.crux.entity.ParceriasProjeto;
import br.com.crux.to.ContasCentrosCustoTO;

@Component
public class ContasCentrosCustoTOBuilder {

	@Autowired
	private GetUsuarioLogadoCmd getUsuarioLogadoCmd;
	@Autowired
	private GetContasBancariaCmd getContasBancariaCmd;
	@Autowired
	private ContasBancariaTOBuilder contasBancariaTOBuilder;
	@Autowired
	private GetParceriasProjetoCmd getParceriasProjetoCmd;
	@Autowired
	private GetParceriasProgramaCmd getParceriasProgramaCmd;

	public ContasCentrosCusto build(ContasCentrosCustoTO to, ParceriasPrograma parceriasPrograma, ParceriasProjeto parceriasProjeto) {
		ContasCentrosCusto entity = new ContasCentrosCusto();

		BeanUtils.copyProperties(to, entity);

		if (Objects.nonNull(to.getContasBancaria()) || Objects.nonNull(to.getContasBancaria().getId())) {
			entity.setContasBancaria(getContasBancariaCmd.getById(to.getContasBancaria().getId()));
		}

		if (Objects.nonNull(parceriasPrograma) && Objects.nonNull(parceriasPrograma.getId())) {
			entity.setParceriasPrograma(getParceriasProgramaCmd.get(parceriasPrograma.getId()));
		}

		if (Objects.nonNull(parceriasProjeto) && Objects.nonNull(parceriasProjeto.getId())) {
			entity.setParceriasProjeto(getParceriasProjetoCmd.get(parceriasProjeto.getId()));
		}

		entity.setUsuarioAlteracao(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());

		return entity;
	}

	public ContasCentrosCustoTO buildTO(ContasCentrosCusto entity) {
		ContasCentrosCustoTO to = new ContasCentrosCustoTO();

		if (Objects.isNull(entity)) {
			return to;
		}

		BeanUtils.copyProperties(entity, to);

		to.setContasBancaria(contasBancariaTOBuilder.buildTOCombo(entity.getContasBancaria()));

		return to;
	}

	public List<ContasCentrosCustoTO> buildAllTO(List<ContasCentrosCusto> lista) {
		return lista.stream().map(this::buildTO).collect(Collectors.toList());
	}

	public List<ContasCentrosCusto> buildAll(List<ContasCentrosCustoTO> lista,ParceriasPrograma parceriasPrograma, ParceriasProjeto parceriasProjeto) {
		return lista.stream().map(ccc -> build(ccc, parceriasPrograma, parceriasProjeto)).collect(Collectors.toList());
	}

}

package br.com.crux.builder;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.cmd.GetEstoquesCmd;
import br.com.crux.cmd.GetFuncionarioCmd;
import br.com.crux.cmd.GetItensMovimentacoesCmd;
import br.com.crux.cmd.GetItensPedidosMateriaisCmd;
import br.com.crux.cmd.GetMaterialCmd;
import br.com.crux.cmd.GetUsuarioLogadoCmd;
import br.com.crux.entity.Estoques;
import br.com.crux.entity.Funcionario;
import br.com.crux.entity.ItensMovimentacoes;
import br.com.crux.entity.ItensMovimentacoesMateriais;
import br.com.crux.entity.ItensPedidosMateriais;
import br.com.crux.entity.Material;
import br.com.crux.entity.MovimentacoesMateriais;
import br.com.crux.to.ItensMovimentacoesMateriaisTO;

@Component
public class ItensMovimentacoesMateriaisTOBuilder {

	@Autowired EstoquesTOBuilder estoqueTOBuilder;
	@Autowired FuncionarioTOBuilder funcionarioTOBuilder;
	@Autowired ItensMovimentacoesTOBuilder itensMovimentacoesTOBuilder;
	@Autowired ItensPedidosMateriaisTOBuilder itensPedidosMateriaisTOBuilder;
	@Autowired MaterialTOBuilder materialTOBuilder;

	@Autowired GetEstoquesCmd getEstoquesCmd;
	@Autowired GetFuncionarioCmd getFuncionarioCmd;
	@Autowired GetItensMovimentacoesCmd getItensMovimentacoesCmd;
	@Autowired GetItensPedidosMateriaisCmd getItensPedidosMateriaisCmd;
	@Autowired GetMaterialCmd getMaterialCmd;
	@Autowired GetUsuarioLogadoCmd getUsuarioLogadoCmd;

	public ItensMovimentacoesMateriaisTO buildTO(ItensMovimentacoesMateriais e) {
		ItensMovimentacoesMateriaisTO to = new ItensMovimentacoesMateriaisTO();
		
		if(Objects.isNull(e)) {
			return new ItensMovimentacoesMateriaisTO();
		}

		BeanUtils.copyProperties(e, to);

		to.setId(e.getId());

		to.setMaterial(materialTOBuilder.buildTOCombo(e.getMaterial()));
		to.setEstoque(estoqueTOBuilder.buildTOCombo(e.getEstoque()));
		to.setFuncionarioEnvio(funcionarioTOBuilder.buildTOCombo(e.getFuncionarioEnvio()));
		to.setFuncionarioRecebe(funcionarioTOBuilder.buildTOCombo(e.getFuncionarioRecebe()));
		to.setItensMovimentacoes(itensMovimentacoesTOBuilder.buildTO(e.getItensMovimentacoes()));
		to.setItensPedidosMateriais(itensPedidosMateriaisTOBuilder.buildTOCombo(e.getItensPedidosMateriais()));

		return to;
	}

	public List<ItensMovimentacoesMateriaisTO> buildAll(List<ItensMovimentacoesMateriais> lista) {
		return lista.stream()
				.map(this::buildTO)
				.collect(Collectors.toList());
	}

	public ItensMovimentacoesMateriais build(MovimentacoesMateriais movimentacoesMateriais, ItensMovimentacoesMateriaisTO to) {
		ItensMovimentacoesMateriais e = new ItensMovimentacoesMateriais();

		BeanUtils.copyProperties(to, e);

		if (Objects.nonNull(to.getEstoque()) && Objects.nonNull(to.getEstoque()
				.getId())) {
			Estoques entity = getEstoquesCmd.getById(to.getEstoque()
					.getId());
			e.setEstoque(entity);

		}

		if (Objects.nonNull(to.getFuncionarioEnvio()) && Objects.nonNull(to.getFuncionarioEnvio()
				.getId())) {
			Funcionario entity = getFuncionarioCmd.getById(to.getFuncionarioEnvio()
					.getId());
			e.setFuncionarioEnvio(entity);

		}

		if (Objects.nonNull(to.getFuncionarioRecebe()) && Objects.nonNull(to.getFuncionarioRecebe()
				.getId())) {
			Funcionario entity = getFuncionarioCmd.getById(to.getFuncionarioRecebe()
					.getId());
			e.setFuncionarioRecebe(entity);

		}

		if (Objects.nonNull(to.getItensMovimentacoes()) && Objects.nonNull(to.getItensMovimentacoes()
				.getId())) {
			ItensMovimentacoes entity = getItensMovimentacoesCmd.getById(to.getItensMovimentacoes()
					.getId());
			e.setItensMovimentacoes(entity);

		}

		if (Objects.nonNull(to.getItensPedidosMateriais()) && Objects.nonNull(to.getItensPedidosMateriais()
				.getId())) {
			ItensPedidosMateriais entity = getItensPedidosMateriaisCmd.getById(to.getItensPedidosMateriais()
					.getId());
			e.setItensPedidosMateriais(entity);

		}

		if (Objects.nonNull(to.getMaterial()) && Objects.nonNull(to.getMaterial()
				.getId())) {
			Material entity = getMaterialCmd.getById(to.getMaterial()
					.getId());
			e.setMaterial(entity);
			
		}

		e.setMovimentacoesMateriais(movimentacoesMateriais);

		e.setUsuarioAlteracao(getUsuarioLogadoCmd.getUsuarioLogado()
				.getIdUsuario());

		return e;
	}

}

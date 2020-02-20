package br.com.crux.builder;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.cmd.GetDepartamentoCmd;
import br.com.crux.cmd.GetFuncionarioCmd;
import br.com.crux.cmd.GetItensPedidosMateriaisCmd;
import br.com.crux.cmd.GetProgramaCmd;
import br.com.crux.cmd.GetProjetoCmd;
import br.com.crux.cmd.GetUnidadeCmd;
import br.com.crux.cmd.GetUsuarioLogadoCmd;
import br.com.crux.entity.Departamentos;
import br.com.crux.entity.Funcionario;
import br.com.crux.entity.PedidosMateriais;
import br.com.crux.entity.Programa;
import br.com.crux.entity.Projeto;
import br.com.crux.entity.Unidade;
import br.com.crux.to.PedidosMateriaisTO;

@Component
public class PedidosMateriaisTOBuilder {

	@Autowired private DepartamentoTOBuilder departamentoTOBuilder;
	@Autowired private FuncionarioTOBuilder funcionarioTOBuilder;
	@Autowired private ProgramaTOBuilder programaTOBuilder;
	@Autowired private ProjetoTOBuilder projetoTOBuilder;
	@Autowired private UnidadeTOBuilder unidadeTOBuilder;

	@Autowired private GetUnidadeCmd getUnidadeCmd;
	@Autowired private GetProjetoCmd getProjetoCmd;
	@Autowired private GetProgramaCmd getProgramaCmd;
	@Autowired private GetDepartamentoCmd getDepartamentoCmd;
	@Autowired private GetFuncionarioCmd getFuncionarioCmd;
	@Autowired private GetItensPedidosMateriaisCmd getItensPedidosMateriaisCmd;

	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;

	public PedidosMateriais build(PedidosMateriaisTO to) {
		PedidosMateriais e = new PedidosMateriais();

		BeanUtils.copyProperties(to, e);

		if (Objects.nonNull(to.getUnidade()) && Objects.nonNull(to.getUnidade()
				.getIdUnidade())) {
			Unidade unidade = getUnidadeCmd.getById(to.getUnidade()
					.getIdUnidade());
			e.setUnidade(unidade);
		}

		if (Objects.nonNull(to.getPrograma()) && Objects.nonNull(to.getPrograma()
				.getId())) {
			Programa entity = getProgramaCmd.getById(to.getPrograma()
					.getId());
			e.setPrograma(entity);
		}

		if (Objects.nonNull(to.getProjeto()) && Objects.nonNull(to.getProjeto()
				.getId())) {
			Projeto entity = getProjetoCmd.getById(to.getProjeto()
					.getId());
			e.setProjeto(entity);
		}

		if (Objects.nonNull(to.getDepartamento()) && Objects.nonNull(to.getDepartamento()
				.getIdDepartamento())) {
			Departamentos entity = getDepartamentoCmd.getById(to.getDepartamento()
					.getIdDepartamento());
			e.setDepartamento(entity);
		}

		if (Objects.nonNull(to.getFuncionarioPedido()) && Objects.nonNull(to.getFuncionarioPedido()
				.getId())) {
			Funcionario entity = getFuncionarioCmd.getById(to.getFuncionarioPedido()
					.getId());
			e.setFuncionarioPedido(entity);
		}

		if (Objects.nonNull(to.getFuncionarioRecPed()) && Objects.nonNull(to.getFuncionarioRecPed()
				.getId())) {
			Funcionario entity = getFuncionarioCmd.getById(to.getFuncionarioRecPed()
					.getId());
			e.setFuncionarioRecPed(entity);
		}

		e.setUsuarioAlteracao(getUsuarioLogadoCmd.getUsuarioLogado()
				.getIdUsuario());

		return e;

	}

	public PedidosMateriaisTO buildTO(PedidosMateriais e) {
		PedidosMateriaisTO to = new PedidosMateriaisTO();

		if (Objects.isNull(e)) {
			return to;
		}

		to.setId(e.getId());
		to.setDescricaoPedido(e.getDescricaoPedido());

		to.setDepartamento(departamentoTOBuilder.buildTOCombo(e.getDepartamento()));
		to.setFuncionarioPedido(funcionarioTOBuilder.buildTOCombo(e.getFuncionarioPedido()));
		to.setFuncionarioRecPed(funcionarioTOBuilder.buildTOCombo(e.getFuncionarioRecPed()));
		to.setPrograma(programaTOBuilder.buildTOCombo(e.getPrograma()));
		to.setProjeto(projetoTOBuilder.buildTOCombo(e.getProjeto()));
		to.setUnidade(unidadeTOBuilder.buildTOCombo(e.getUnidade()));

		to.setItensPedidosMateriais(getItensPedidosMateriaisCmd.getItensPedidosMateriaisTOByPedidosMateriais(e));

		return to;
	}

	public PedidosMateriaisTO buildTOCombo(PedidosMateriais pedidosMateriais) {
		PedidosMateriaisTO to = new PedidosMateriaisTO();

		if (Objects.isNull(pedidosMateriais)) {
			return to;
		}

		to.setId(pedidosMateriais.getId());
		to.setDescricaoPedido(pedidosMateriais.getDescricaoPedido());

		return to;
	}

	public List<PedidosMateriaisTO> buildAllCombo(List<PedidosMateriais> list) {
		return list.stream()
				.map(this::buildTOCombo)
				.collect(Collectors.toList());
	}

	public List<PedidosMateriaisTO> buildAll(List<PedidosMateriais> list) {
		return list.stream()
				.map(this::buildTO)
				.collect(Collectors.toList());
	}

}

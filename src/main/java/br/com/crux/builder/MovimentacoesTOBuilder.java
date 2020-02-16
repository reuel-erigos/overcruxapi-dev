package br.com.crux.builder;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.cmd.GetDepartamentoCmd;
import br.com.crux.cmd.GetEmpresaCmd;
import br.com.crux.cmd.GetItensMovimentacoesCmd;
import br.com.crux.cmd.GetProgramaCmd;
import br.com.crux.cmd.GetProjetoCmd;
import br.com.crux.cmd.GetUnidadeCmd;
import br.com.crux.cmd.GetUsuarioLogadoCmd;
import br.com.crux.entity.Movimentacoes;
import br.com.crux.to.MovimentacoesTO;

@Component
public class MovimentacoesTOBuilder {

	@Autowired private EmpresaTOBuilder empresaTOBuilder;
	@Autowired private ProjetoTOBuilder projetoTOBuilder;
	@Autowired private ProgramaTOBuilder programaTOBuilder;
	@Autowired private UnidadeTOBuilder unidadeTOBuilder;
	@Autowired private DepartamentoTOBuilder departamentoTOBuilder;
	@Autowired private GetEmpresaCmd getEmpresaCmd;
	@Autowired private GetProjetoCmd getProjetoCmd;
	@Autowired private GetProgramaCmd getProgramaCmd;
	@Autowired private GetUnidadeCmd getUnidadeCmd;
	@Autowired private GetDepartamentoCmd getDepartamentoCmd;
	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;
	@Autowired private GetItensMovimentacoesCmd getItensMovimentacoesCmd;

	public MovimentacoesTO buildTO(Movimentacoes m) {
		MovimentacoesTO to = new MovimentacoesTO();

		if (Objects.isNull(m)) {
			return to;
		}

		BeanUtils.copyProperties(m, to);
		to.setEmpresa(empresaTOBuilder.buildTOCombo(m.getEmpresa()));
		to.setProjeto(projetoTOBuilder.buildTOCombo(m.getProjeto()));
		to.setPrograma(programaTOBuilder.buildTOCombo(m.getPrograma()));
		to.setPrograma(programaTOBuilder.buildTOCombo(m.getPrograma()));
		to.setUnidade(unidadeTOBuilder.buildTOCombo(m.getUnidade()));
		to.setDepartamento(departamentoTOBuilder.buildTOCombo(m.getDepartamento()));
		
		to.setItensMovimentacoes(getItensMovimentacoesCmd.getItensMovimentacoesTOByMovimentacao(m));

		return to;
	}

	public List<MovimentacoesTO> buildAll(List<Movimentacoes> list) {
		return list.stream()
				.map(this::buildTO)
				.collect(Collectors.toList());
	}

	public Movimentacoes build(MovimentacoesTO to) {
		Movimentacoes p = new Movimentacoes();

		BeanUtils.copyProperties(to, p);

		if (Objects.nonNull(to.getEmpresa()) && Objects.nonNull(to.getEmpresa()
				.getId())) {
			p.setEmpresa(getEmpresaCmd.getById(to.getEmpresa()
					.getId()));
		}

		if (Objects.nonNull(to.getPrograma()) && Objects.nonNull(to.getPrograma()
				.getId())) {
			p.setPrograma(getProgramaCmd.getById(to.getPrograma()
					.getId()));
		}

		if (Objects.nonNull(to.getProjeto()) && Objects.nonNull(to.getProjeto()
				.getId())) {
			p.setProjeto(getProjetoCmd.getById(to.getProjeto()
					.getId()));
		}

		if (Objects.nonNull(to.getUnidade()) && Objects.nonNull(to.getUnidade()
				.getIdUnidade())) {
			p.setUnidade(getUnidadeCmd.getById(to.getUnidade()
					.getIdUnidade()));
		}

		if (Objects.nonNull(to.getDepartamento()) && Objects.nonNull(to.getDepartamento()
				.getIdDepartamento())) {
			p.setDepartamento(getDepartamentoCmd.getById(to.getDepartamento()
					.getIdDepartamento()));

		}

		p.setUsuarioAlteracao(getUsuarioLogadoCmd.getUsuarioLogado()
				.getIdUsuario());

		return p;
	}

}

//		retorno.setUnidades(getProjetosUnidadeCmd.getUnidadesTOByIdProjeto(p.getId()));
//		retorno.setColaboradoresProjeto((getColaboradoresProjetoCmd.getColaboradoresProjetoTOByProjeto(p)));
//		retorno.setParceriasProjeto(getParceriasProjetoCmd.getParceriasProjetoTOByProjeto(p));
//		retorno.setComposicaoRhProjeto(getComposicaoRhProjetoCmd.getComposicaoRhProjetoByProjeto(p));
//		retorno.setContasCentrosCusto(getContasCentrosCustoCmd.getTOPorProjeto(p));
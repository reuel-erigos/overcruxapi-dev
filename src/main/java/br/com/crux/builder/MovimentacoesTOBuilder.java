package br.com.crux.builder;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.cmd.GetContasBancariaCmd;
import br.com.crux.cmd.GetDepartamentoCmd;
import br.com.crux.cmd.GetEmpresaCmd;
import br.com.crux.cmd.GetFaturaCmd;
import br.com.crux.cmd.GetItensMovimentacoesCmd;
import br.com.crux.cmd.GetPagamentosFaturaCmd;
import br.com.crux.cmd.GetRateiosMovimentacoesCmd;
import br.com.crux.cmd.GetUnidadeCmd;
import br.com.crux.cmd.GetUsuarioLogadoCmd;
import br.com.crux.entity.Movimentacoes;
import br.com.crux.to.MovimentacoesTO;

@Component
public class MovimentacoesTOBuilder {

	@Autowired private EmpresaTOBuilder empresaTOBuilder;
	@Autowired private UnidadeTOBuilder unidadeTOBuilder;
	@Autowired private DepartamentoTOBuilder departamentoTOBuilder;
	@Autowired private GetEmpresaCmd getEmpresaCmd;
	@Autowired private GetUnidadeCmd getUnidadeCmd;
	@Autowired private GetDepartamentoCmd getDepartamentoCmd;
	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;
	@Autowired private GetItensMovimentacoesCmd getItensMovimentacoesCmd;
	@Autowired private GetFaturaCmd getFaturaCmd;
	@Autowired private GetPagamentosFaturaCmd getPagamentosFaturaCmd;
	@Autowired private ContasBancariaTOBuilder contasBancariaTOBuilder;
	@Autowired private GetContasBancariaCmd getContasBancariaCmd;
	@Autowired private RateiosMovimentacoesTOBuilder rateiosMovimentacoesTOBuilder;
	@Autowired private GetRateiosMovimentacoesCmd getRateiosMovimentacoesCmd;
	
	
	public MovimentacoesTO buildTO(Movimentacoes m) {
		MovimentacoesTO to = new MovimentacoesTO();

		if (Objects.isNull(m)) {
			return to;
		}

		BeanUtils.copyProperties(m, to);
		
		to.setEmpresa(empresaTOBuilder.buildTOCombo(m.getEmpresa()));
		to.setUnidade(unidadeTOBuilder.buildTOCombo(m.getUnidade()));
		to.setDepartamento(departamentoTOBuilder.buildTOCombo(m.getDepartamento()));
		
		to.setItensMovimentacoes(getItensMovimentacoesCmd.getItensMovimentacoesTOByMovimentacao(m));
		to.setFaturas(getFaturaCmd.getFaturaTOByMovimentacao(m));
		to.setPagamentosFatura(getPagamentosFaturaCmd.getPagamentoFaturaTOByMovimentacao(m));
		to.setContaBancaria(contasBancariaTOBuilder.buildTO(m.getContaBancaria()));
		to.setRateios(rateiosMovimentacoesTOBuilder.buildAllTO(getRateiosMovimentacoesCmd.getPorMovimentacoes(m)));

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

		if (Objects.nonNull(to.getEmpresa()) && Objects.nonNull(to.getEmpresa().getId())) {
			p.setEmpresa(getEmpresaCmd.getById(to.getEmpresa().getId()));
		}

		if (Objects.nonNull(to.getUnidade()) && Objects.nonNull(to.getUnidade().getIdUnidade())) {
			p.setUnidade(getUnidadeCmd.getById(to.getUnidade().getIdUnidade()));
		}

		if (Objects.nonNull(to.getDepartamento()) && Objects.nonNull(to.getDepartamento().getIdDepartamento())) {
			p.setDepartamento(getDepartamentoCmd.getById(to.getDepartamento().getIdDepartamento()));
		}
		
		if (Objects.nonNull(to.getContaBancaria()) && Objects.nonNull(to.getContaBancaria().getId())) {
			p.setContaBancaria(getContasBancariaCmd.getById(to.getContaBancaria().getId()));
		}

		p.setUsuarioAlteracao(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());
		return p;
	}

}


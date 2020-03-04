package br.com.crux.builder;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.cmd.GetDepartamentoCmd;
import br.com.crux.cmd.GetEmpresaCmd;
import br.com.crux.cmd.GetFaturaCmd;
import br.com.crux.cmd.GetItensMovimentacoesCmd;
import br.com.crux.cmd.GetPagamentosFaturaCmd;
import br.com.crux.cmd.GetProgramaCmd;
import br.com.crux.cmd.GetProjetoCmd;
import br.com.crux.cmd.GetSaldosContasBancariaCmd;
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
	@Autowired private GetFaturaCmd getFaturaCmd;
	@Autowired private GetPagamentosFaturaCmd getPagamentosFaturaCmd;
	@Autowired private SaldosContasBancariaTOBuilder saldosContasBancariaTOBuilder;
	@Autowired private GetSaldosContasBancariaCmd getSaldosContasBancariaCmd;

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
		to.setFaturas(getFaturaCmd.getFaturaTOByMovimentacao(m));
		to.setPagamentosFatura(getPagamentosFaturaCmd.getPagamentoFaturaTOByMovimentacao(m));
		
		
		to.setValorICMS(m.getValorICMS());
		to.setValorInss(m.getValorInss());
		to.setValorIPI(m.getValorIPI());
		to.setValorISS(m.getValorISS());
		to.setValorMovimentacao(m.getValorMovimentacao());
		to.setValorPisConfinsCsll(m.getValorPisConfinsCsll());
		to.setSaldoContaBancaria(saldosContasBancariaTOBuilder.buildTO(m.getSaldoContaBancaria()));

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

		p.setUsuarioAlteracao(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());
		
		p.setValorICMS(to.getValorICMS());
		p.setValorInss(to.getValorInss());
		p.setValorIPI(to.getValorIPI());
		p.setValorISS(to.getValorISS());
		p.setValorMovimentacao(to.getValorMovimentacao());
		p.setValorPisConfinsCsll(to.getValorPisConfinsCsll());
		
		if (Objects.nonNull(to.getSaldoContaBancaria()) && Objects.nonNull(to.getSaldoContaBancaria().getId())) {
			p.setSaldoContaBancaria(getSaldosContasBancariaCmd.getById(to.getSaldoContaBancaria().getId()));
		}

		return p;
	}

}


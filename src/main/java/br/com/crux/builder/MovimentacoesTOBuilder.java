package br.com.crux.builder;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.cmd.GetCategoriasMovimentosCmd;
import br.com.crux.cmd.GetContasBancariaCmd;
import br.com.crux.cmd.GetDepartamentoCmd;
import br.com.crux.cmd.GetDoadoresCmd;
import br.com.crux.cmd.GetEmpresaCmd;
import br.com.crux.cmd.GetFaturaCmd;
import br.com.crux.cmd.GetItensMovimentacoesCmd;
import br.com.crux.cmd.GetPagamentosFaturaCmd;
import br.com.crux.cmd.GetPessoaFisicaCmd;
import br.com.crux.cmd.GetRateiosMovimentacoesCmd;
import br.com.crux.cmd.GetRateiosMovimentacoesUnidadesCmd;
import br.com.crux.cmd.GetTributoMovimentacaoCmd;
import br.com.crux.cmd.GetUnidadeCmd;
import br.com.crux.cmd.GetUsuarioLogadoCmd;
import br.com.crux.dao.repository.FaturaRepository;
import br.com.crux.dao.repository.PagamentosFaturaRepository;
import br.com.crux.entity.Doadores;
import br.com.crux.entity.Fatura;
import br.com.crux.entity.Movimentacoes;
import br.com.crux.entity.PagamentosFatura;
import br.com.crux.entity.PessoaFisica;
import br.com.crux.to.CategoriasContabeisTO;
import br.com.crux.to.CategoriasMovimentosTO;
import br.com.crux.to.EmpresaTO;
import br.com.crux.to.FaturaTO;
import br.com.crux.to.MovimentacoesTO;
import br.com.crux.to.PagamentosFaturaTO;
import br.com.crux.to.PessoaFisicaTO;
import br.com.crux.to.ProgramaTO;
import br.com.crux.to.ProjetoTO;

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
	@Autowired private RateiosMovimentacoesUnidadesTOBuilder rateiosMovimentacoesUnidadesTOBuilder;
	@Autowired private GetRateiosMovimentacoesUnidadesCmd getRateiosMovimentacoesUnidadesCmd;
	@Autowired private DoadoresTOBuilder doadoresTOBuilder;
	@Autowired private GetDoadoresCmd getDoadoresCmd; 
	@Autowired private GetTributoMovimentacaoCmd getTributoMovimentacaoCmd;
	@Autowired private PessoaFisicaTOBuilder pessoaFisicaTOBuilder;
	@Autowired private GetPessoaFisicaCmd getPessoaFisicaCmd;
	@Autowired private GetCategoriasMovimentosCmd getCategoriasMovimentosCmd;
	@Autowired private PagamentosFaturaRepository pagamentosFaturarepository;
	@Autowired private FaturaRepository faturaRepository;
	
	public MovimentacoesTO buildTO(Movimentacoes m) {
		MovimentacoesTO to = new MovimentacoesTO();

		if (Objects.isNull(m)) {
			return to;
		}

		BeanUtils.copyProperties(m, to);
		
		to.setEmpresa(empresaTOBuilder.buildTOCombo(m.getEmpresa()));
		to.setUnidade(unidadeTOBuilder.buildTOCombo(m.getUnidade()));
		to.setDepartamento(departamentoTOBuilder.buildTOCombo(m.getDepartamento()));
		
		
		if(!to.getStTipoMovimentacao().toUpperCase().equals("T")) {
			to.setItensMovimentacoes(getItensMovimentacoesCmd.getItensMovimentacoesTOByMovimentacao(m));
			to.setFaturas(getFaturaCmd.getFaturaTOByMovimentacao(m));
			to.setPagamentosFatura(getPagamentosFaturaCmd.getPagamentoFaturaTOByMovimentacao(m));
			to.setRateios(rateiosMovimentacoesTOBuilder.buildAllTO(getRateiosMovimentacoesCmd.getPorMovimentacoes(m)));
			to.setRateiosUnidades(rateiosMovimentacoesUnidadesTOBuilder.buildAllTO(getRateiosMovimentacoesUnidadesCmd.getPorMovimentacoes(m)));
			to.setTributos(getTributoMovimentacaoCmd.getAllTOByIdMovimentacao(m.getId()));
		}
		
		to.setCategoriasMovimentos(getCategoriasMovimentosCmd.getCategoriasMovimentosTOByMovimentacao(m));
		to.setContaBancaria(contasBancariaTOBuilder.buildTO(m.getContaBancaria()));
		to.setContaBancariaDestino(contasBancariaTOBuilder.buildTO(m.getContaBancariaDestino()));
		to.setDoador(doadoresTOBuilder.buildTO(m.getDoador()));
		to.setFornecedorColaborador(pessoaFisicaTOBuilder.buildTO(m.getFornecedorColaborador()));

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

		if (Objects.nonNull(to.getContaBancariaDestino()) && Objects.nonNull(to.getContaBancariaDestino().getId())) {
			p.setContaBancariaDestino(getContasBancariaCmd.getById(to.getContaBancariaDestino().getId()));
		}
		
		if (Objects.nonNull(to.getDoador()) && Objects.nonNull(to.getDoador().getId())) {
			Doadores obj = getDoadoresCmd.getById(to.getDoador().getId());
			p.setDoador(obj);
		}

		if (Objects.nonNull(to.getFornecedorColaborador()) && Objects.nonNull(to.getFornecedorColaborador().getId())) {
			PessoaFisica obj = getPessoaFisicaCmd.getById(to.getFornecedorColaborador().getId());
			p.setFornecedorColaborador(obj);
		}
		
		
		p.setUsuarioAlteracao(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());
		return p;
	}

	public MovimentacoesTO toDTOList(Movimentacoes entity) {
		MovimentacoesTO to = new MovimentacoesTO();
		to.setId(entity.getId());
		if(Objects.nonNull(entity.getEmpresa())) {
			to.setEmpresa(new EmpresaTO());
			to.getEmpresa().setId(entity.getEmpresa().getId());
			to.getEmpresa().setNomeRazaoSocial(entity.getEmpresa().getNomeRazaoSocial());
			to.getEmpresa().setCnpj(entity.getEmpresa().getCnpj());
		}
		if(Objects.nonNull(entity.getFornecedorColaborador())) {
			to.setFornecedorColaborador(new PessoaFisicaTO());
			to.getFornecedorColaborador().setId(entity.getFornecedorColaborador().getId());
			to.getFornecedorColaborador().setNome(entity.getFornecedorColaborador().getNome());
			to.getFornecedorColaborador().setCpf(entity.getFornecedorColaborador().getCpf());
		}
		if(Objects.nonNull(entity.getCategorias())) {
			to.setCategoriasMovimentos(entity.getCategorias().stream().map(item -> {
				CategoriasMovimentosTO categoriasTO = new CategoriasMovimentosTO();
				categoriasTO.setId(item.getId());
				if(Objects.nonNull(item.getPrograma())) {
					categoriasTO.setPrograma(new ProgramaTO());
					categoriasTO.getPrograma().setId(item.getPrograma().getId());
					categoriasTO.getPrograma().setNome(item.getPrograma().getNome());
				}
				if(Objects.nonNull(item.getProjeto())) {
					categoriasTO.setProjeto(new ProjetoTO());
					categoriasTO.getProjeto().setId(item.getProjeto().getId());
					categoriasTO.getProjeto().setNome(item.getProjeto().getNome());
				}
				if(Objects.nonNull(item.getCategoriaAdicional())) {
					categoriasTO.setCategoriaAdicional(new CategoriasContabeisTO());
					categoriasTO.getCategoriaAdicional().setId(item.getCategoriaAdicional().getId());
					categoriasTO.getCategoriaAdicional().setNome(item.getCategoriaAdicional().getNome());
					categoriasTO.getCategoriaAdicional().setDescricaoCategoria(item.getCategoriaAdicional().getDescricaoCategoria());
				}
				if(Objects.nonNull(item.getCategoriaDestino())) {
					categoriasTO.setCategoriaDestino(new CategoriasContabeisTO());
					categoriasTO.getCategoriaDestino().setId(item.getCategoriaDestino().getId());
					categoriasTO.getCategoriaDestino().setNome(item.getCategoriaDestino().getNome());
					categoriasTO.getCategoriaDestino().setDescricaoCategoria(item.getCategoriaDestino().getDescricaoCategoria());
				}
				if(Objects.nonNull(item.getCategoriaOrigem())) {
					categoriasTO.setCategoriaOrigem(new CategoriasContabeisTO());
					categoriasTO.getCategoriaOrigem().setId(item.getCategoriaOrigem().getId());
					categoriasTO.getCategoriaOrigem().setNome(item.getCategoriaOrigem().getNome());
					categoriasTO.getCategoriaOrigem().setDescricaoCategoria(item.getCategoriaOrigem().getDescricaoCategoria());
				}
				return categoriasTO;
			}).collect(Collectors.toList()));
		} else {
			to.setCategoriasMovimentos(new ArrayList<CategoriasMovimentosTO>());
		}
		Optional<List<PagamentosFatura>> listaPagamentosFatura = pagamentosFaturarepository.findByIdMovimentacao(entity.getId());
		if(listaPagamentosFatura.isPresent()) {
			to.setPagamentosFatura(listaPagamentosFatura.get().stream().map(item -> {
				PagamentosFaturaTO pagamentosFaturaTO = new PagamentosFaturaTO();
				pagamentosFaturaTO.setId(item.getId());
				pagamentosFaturaTO.setDataPagamento(item.getDataPagamento());
				pagamentosFaturaTO.setNumeroDocPagamento(item.getNumeroDocPagamento());
				return pagamentosFaturaTO;
			}).collect(Collectors.toList()));
		} else {
			to.setPagamentosFatura(new ArrayList<PagamentosFaturaTO>());
		}
		Optional<List<Fatura>> listaFatura = faturaRepository.findByIdMovimentacao(entity.getId());
		if(listaFatura.isPresent()) {
			to.setFaturas(listaFatura.get().stream().map(item -> {
				FaturaTO faturaTO = new FaturaTO();
				faturaTO.setId(item.getId());
				faturaTO.setDataVencimento(item.getDataVencimento());
				return faturaTO;
			}).collect(Collectors.toList()));
		} else {
			to.setFaturas(new ArrayList<FaturaTO>());
		}
		to.setNrDocumento(entity.getNrDocumento());
		to.setDataDocumento(entity.getDataDocumento());
		to.setValorMovimentacao(entity.getValorMovimentacao());
		return to;
	}

}


package br.com.crux.cmd;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import br.com.crux.dao.repository.FaturaRepository;
import br.com.crux.dao.repository.ItensMovimentacoesRepository;
import br.com.crux.dao.repository.MovimentacoesRepository;
import br.com.crux.dao.repository.PagamentosFaturaRepository;
import br.com.crux.dao.repository.RateiosMovimentacoesRepository;
import br.com.crux.dao.repository.RateiosMovimentacoesUnidadesRepository;
import br.com.crux.dao.repository.RateiosPagamentosRepository;
import br.com.crux.dao.repository.ReembolsosPagamentosRepository;
import br.com.crux.dao.repository.TributosItensMovimentacaoRepository;
import br.com.crux.entity.Fatura;
import br.com.crux.entity.ItensMovimentacoes;
import br.com.crux.entity.PagamentosFatura;
import br.com.crux.entity.RateiosMovimentacoes;
import br.com.crux.entity.RateiosMovimentacoesUnidades;
import br.com.crux.entity.RateiosPagamentos;
import br.com.crux.entity.ReembolsosPagamentos;
import br.com.crux.entity.TributosItensMovimentacoes;
import br.com.crux.exception.ParametroNaoInformadoException;
import br.com.crux.exception.TabaleReferenciaEncontradaException;
import br.com.crux.exception.base.NegocioException;

@Component
public class ExcluirMovimentacoesCmd {

	
	@Autowired private MovimentacoesRepository repository;
	@Autowired private RateiosMovimentacoesRepository rateiosMovimentacoesRepository;
	@Autowired private RateiosMovimentacoesUnidadesRepository rateiosMovimentacoesUnidadesRepository;
	@Autowired private PagamentosFaturaRepository pagamentosFaturaRepository;
	@Autowired private ItensMovimentacoesRepository itensMovimentacoesRepository;
	@Autowired private FaturaRepository faturaRepository;
	@Autowired private TributosItensMovimentacaoRepository tributosItensMovimentacaoRepository;
	@Autowired private RateiosPagamentosRepository rateiosPagamentosRepository;
	@Autowired private ReembolsosPagamentosRepository reembolsosPagamentosRepository;
	
	public void excluir(Long id) {
		try {
			if (Objects.isNull(id)) {
				throw new ParametroNaoInformadoException("Erro ao excluir a entidade. Parâmetro 'id' ausente.");
			}


			Optional<List<PagamentosFatura>> pagamentos = pagamentosFaturaRepository.findByIdMovimentacao(id);
			if(pagamentos.isPresent()) {
				pagamentos.get().stream().forEach(pag -> {
					Optional<List<RateiosPagamentos>> rateios = rateiosPagamentosRepository.findByIdPagamentoFatura(pag.getId());
					if(rateios.isPresent()) {
						rateiosPagamentosRepository.deleteAll(rateios.get());
					}
					Optional<List<ReembolsosPagamentos>> reembolsos = reembolsosPagamentosRepository.findByIdPagamentoFatura(pag.getId());
					if(reembolsos.isPresent()) {
						reembolsosPagamentosRepository.deleteAll(reembolsos.get());
					}
				});
				
				pagamentosFaturaRepository.deleteAll(pagamentos.get());
				pagamentosFaturaRepository.flush();
			}
			
			Optional<List<Fatura>> faturas = faturaRepository.findByIdMovimentacao(id);
			if(faturas.isPresent()) {
				faturaRepository.deleteAll(faturas.get());
				faturaRepository.flush();
			}
			
			Optional<List<ItensMovimentacoes>> itens = itensMovimentacoesRepository.findByIdMovimentacao(id);
			if(itens.isPresent()) {
				itensMovimentacoesRepository.deleteAll(itens.get());
				itensMovimentacoesRepository.flush();
			}

			
			Optional<List<TributosItensMovimentacoes>> tributos = tributosItensMovimentacaoRepository.findAllByIdMovimentacao(id);
			if(tributos.isPresent()) {
				tributosItensMovimentacaoRepository.deleteInBatch(tributos.get());
				tributosItensMovimentacaoRepository.flush();
			}
			
			Optional<List<RateiosMovimentacoes>> rateios = rateiosMovimentacoesRepository.findByIdMovimento(id);
			if(rateios.isPresent()) {
				rateiosMovimentacoesRepository.deleteInBatch(rateios.get());
				rateiosMovimentacoesRepository.flush();
			}
			
			Optional<List<RateiosMovimentacoesUnidades>> rateiosUnidades = rateiosMovimentacoesUnidadesRepository.findByIdMovimento(id);
			if(rateiosUnidades.isPresent()) {
				rateiosMovimentacoesUnidadesRepository.deleteInBatch(rateiosUnidades.get());
				rateiosMovimentacoesUnidadesRepository.flush();
			}
					
			repository.deleteById(id);
			repository.flush();
			
		} catch (NegocioException e) {
			throw new NegocioException(e.getMessage());
		}
	}
}

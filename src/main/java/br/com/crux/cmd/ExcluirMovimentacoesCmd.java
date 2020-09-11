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
import br.com.crux.entity.Fatura;
import br.com.crux.entity.ItensMovimentacoes;
import br.com.crux.entity.PagamentosFatura;
import br.com.crux.entity.RateiosMovimentacoes;
import br.com.crux.entity.RateiosMovimentacoesUnidades;
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
	
	
	public void excluir(Long id) {
		try {
			if (Objects.isNull(id)) {
				throw new ParametroNaoInformadoException("Erro ao excluir a entidade. Parâmetro 'id' ausente.");
			}
			
			Optional<List<Fatura>> faturas = faturaRepository.findByIdMovimentacao(id);
			if(faturas.isPresent()) {
				throw new TabaleReferenciaEncontradaException("Não é possível excluir o movimento, pois há faturas cadastradas.");
			}
			
			Optional<List<PagamentosFatura>> pagamentos = pagamentosFaturaRepository.findByIdMovimentacao(id);
			if(pagamentos.isPresent()) {
				throw new TabaleReferenciaEncontradaException("Não é possível excluir o movimento, pois há pagamentos cadastrados.");
			}
			
			Optional<List<ItensMovimentacoes>> itens = itensMovimentacoesRepository.findByIdMovimentacao(id);
			if(itens.isPresent()) {
				throw new  TabaleReferenciaEncontradaException("Não é possível excluir o movimento, pois há itens cadastrados.");
			}
			
			Optional<List<RateiosMovimentacoes>> rateios = rateiosMovimentacoesRepository.findByIdMovimento(id);
			if(rateios.isPresent()) {
				rateiosMovimentacoesRepository.deleteInBatch(rateios.get());
			}
			
			Optional<List<RateiosMovimentacoesUnidades>> rateiosUnidades = rateiosMovimentacoesUnidadesRepository.findByIdMovimento(id);
			if(rateiosUnidades.isPresent()) {
				rateiosMovimentacoesUnidadesRepository.deleteInBatch(rateiosUnidades.get());
			}
						
			repository.deleteById(id);
			
		} catch (NegocioException e) {
			throw new NegocioException(e.getMessage());
			
		}catch (Exception e) {
			if(Objects.nonNull(e.getCause())) {
				if(e.getCause() instanceof DataIntegrityViolationException || e.getCause().toString().contains("ConstraintViolationException")) {
					throw new TabaleReferenciaEncontradaException("Erro ao excluir, verifique se há outro cadastro com referência com esse registro.");
				}
			}
			
			throw new RuntimeException(e.getMessage());
		}

	}
}

package br.com.crux.cmd;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import br.com.crux.dao.repository.MovimentacoesRepository;
import br.com.crux.dao.repository.RateiosMovimentacoesRepository;
import br.com.crux.dao.repository.RateiosMovimentacoesUnidadesRepository;
import br.com.crux.dao.repository.RateiosPagamentosRepository;
import br.com.crux.dao.repository.ReembolsosPagamentosRepository;
import br.com.crux.entity.RateiosMovimentacoes;
import br.com.crux.entity.RateiosMovimentacoesUnidades;
import br.com.crux.entity.RateiosPagamentos;
import br.com.crux.entity.ReembolsosPagamentos;
import br.com.crux.exception.ParametroNaoInformadoException;
import br.com.crux.exception.TabaleReferenciaEncontradaException;

@Component
public class ExcluirMovimentacoesCmd {

	
	@Autowired private MovimentacoesRepository repository;
	@Autowired private RateiosMovimentacoesRepository rateiosMovimentacoesRepository;
	@Autowired private RateiosMovimentacoesUnidadesRepository rateiosMovimentacoesUnidadesRepository;
	@Autowired private RateiosPagamentosRepository rateiosPagamentosRepository ;
	@Autowired private ReembolsosPagamentosRepository reembolsosPagamentosRepository;
	public void excluir(Long id) {
		try {
			if (Objects.isNull(id)) {
				throw new ParametroNaoInformadoException("Erro ao excluir a entidade. Parâmetro 'id' ausente.");
			}
			
			Optional<List<ReembolsosPagamentos>> reembolsos = reembolsosPagamentosRepository.findByIdMovimento(id);
			if(reembolsos.isPresent()) {
				reembolsosPagamentosRepository.deleteInBatch(reembolsos.get());
			}
			
			Optional<List<RateiosPagamentos>> rateiosPagamentos = rateiosPagamentosRepository.findByIdMovimento(id);
			if(rateiosPagamentos.isPresent()) {
				rateiosPagamentosRepository.deleteInBatch(rateiosPagamentos.get());
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
		} catch (Exception e) {
			if(e.getCause() instanceof DataIntegrityViolationException || e.getCause().toString().contains("ConstraintViolationException")) {
				throw new TabaleReferenciaEncontradaException("Erro ao excluir, verifique se há outro cadastro com referência a esta entidade.");
			}
			
			throw new RuntimeException(e.getMessage());
		}

	}
}

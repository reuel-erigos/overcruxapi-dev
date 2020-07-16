package br.com.crux.cmd;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import br.com.crux.dao.repository.MovimentacoesRepository;
import br.com.crux.dao.repository.RateiosMovimentacoesRepository;
import br.com.crux.entity.RateiosMovimentacoes;
import br.com.crux.exception.ParametroNaoInformadoException;
import br.com.crux.exception.TabaleReferenciaEncontradaException;

@Component
public class ExcluirMovimentacoesCmd {

	
	@Autowired private MovimentacoesRepository repository;
	@Autowired private RateiosMovimentacoesRepository rateiosMovimentacoesRepository;

	public void excluir(Long id) {
		try {
			if (Objects.isNull(id)) {
				throw new ParametroNaoInformadoException("Erro ao excluir a entidade. Parâmetro 'id' ausente.");
			}
			
			Optional<List<RateiosMovimentacoes>> rateios = rateiosMovimentacoesRepository.findByIdMovimento(id);
			if(rateios.isPresent()) {
				rateios.get().forEach(rateio -> rateiosMovimentacoesRepository.delete(rateio));
			}
			
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new TabaleReferenciaEncontradaException("Erro ao excluir, verifique se há outro cadastro com referência a esta entidade.");
		}

	}
}

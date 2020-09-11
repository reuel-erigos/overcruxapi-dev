package br.com.crux.cmd;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import br.com.crux.dao.repository.DoadoresRepository;
import br.com.crux.dao.repository.MovimentacoesRepository;
import br.com.crux.entity.Movimentacoes;
import br.com.crux.exception.ParametroNaoInformadoException;
import br.com.crux.exception.TabaleReferenciaEncontradaException;

@Component
public class ExcluirDoadoresCmd {

	
	@Autowired private DoadoresRepository repository;
	@Autowired private MovimentacoesRepository movimentacoesRepository;
	@Autowired private GetUnidadeLogadaCmd getUnidadeLogadaCmd; 
	
	public void excluir(Long id) {
		try {
			if (Objects.isNull(id)) {
				throw new ParametroNaoInformadoException("Erro ao excluir a entidade. Parâmetro 'id' ausente.");
			}
			
			Long idInstituicao = getUnidadeLogadaCmd.getUnidadeTO().getInstituicao().getId();
			Optional<List<Movimentacoes>> rateios = movimentacoesRepository.getAllByIdDoador(idInstituicao, id);
			if(rateios.isPresent()) {
				throw new TabaleReferenciaEncontradaException("Existem movimentos com esse doador, exclua antes o movimento.");
			}
			
			repository.deleteById(id);
		} catch (Exception e) {
			if(Objects.nonNull(e.getCause())) {
				if(e.getCause() instanceof DataIntegrityViolationException || e.getCause().toString().contains("ConstraintViolationException")) {
					throw new TabaleReferenciaEncontradaException("Erro ao excluir, verifique se há outro cadastro com referência com esse registro.");
				}
			}

			throw new RuntimeException(e.getMessage());
		}

	}
}

package br.com.crux.cmd;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import br.com.crux.dao.repository.AlunosTurmaRepository;
import br.com.crux.dao.repository.AtividadesAlunoRepository;
import br.com.crux.entity.AlunosTurma;
import br.com.crux.entity.AtividadesAluno;
import br.com.crux.exception.ParametroNaoInformadoException;
import br.com.crux.exception.TabaleReferenciaEncontradaException;

@Component
public class ExcluirAlunosTurmaCmd {

	@Autowired private AlunosTurmaRepository repository;
	@Autowired private AtividadesAlunoRepository atividadesAlunoRepository;
	
	public void excluir(Long id) {
		try {
			if(Objects.isNull(id)) {throw new ParametroNaoInformadoException("Erro ao excluir, parâmetro ausente.");}
			
			Optional<AlunosTurma> alunoTurma = repository.findById(id);
			
			if(alunoTurma.isPresent()) {
				Optional<List<AtividadesAluno>> atividades = atividadesAlunoRepository.findByTurmaAndAluno(alunoTurma.get().getTurma().getId(), alunoTurma.get().getAluno().getId());
				if(atividades.isPresent()){
					atividadesAlunoRepository.deleteAll(atividades.get());
				}
			}
			
			repository.deleteById(id);
			
		} catch (Exception e) {
			if(e.getCause() instanceof DataIntegrityViolationException || e.getCause().toString().contains("ConstraintViolationException")) {
				throw new TabaleReferenciaEncontradaException("Erro ao excluir, apague antes os cadastros com referência a esse registro.");
			}
			throw new RuntimeException(e.getMessage());
		}
	}
}

package br.com.crux.cmd;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import br.com.crux.dao.repository.AlunosTurmaRepository;
import br.com.crux.entity.AlunosTurma;
import br.com.crux.exception.ParametroNaoInformadoException;
import br.com.crux.exception.TabaleReferenciaEncontradaException;
import br.com.crux.to.AtividadesAlunoTO;

@Component
public class ExcluirAlunosTurmaCmd {

	@Autowired private AlunosTurmaRepository repository;
	@Autowired private GetAtividadesAlunoCmd getAtividadesAlunoCmd;
	
	public void excluir(Long id) {
		try {
			if(Objects.isNull(id)) {throw new ParametroNaoInformadoException("Erro ao excluir, parâmetro ausente.");}
			
			Optional<AlunosTurma> alunoTurma = repository.findById(id);
			
			if(alunoTurma.isPresent()) {
				List<AtividadesAlunoTO> atividades = getAtividadesAlunoCmd.getAllFilter(alunoTurma.get().getTurma().getId(), alunoTurma.get().getAluno().getId(), null);
				if(Objects.nonNull(atividades) && !atividades.isEmpty()) {
					throw new TabaleReferenciaEncontradaException("Erro ao excluir, verifique se há oficinas matriculadas para esse aluno/turma.");
				}
				
			}
			
			repository.deleteById(id);
			
		} catch (DataIntegrityViolationException e) {
			throw new TabaleReferenciaEncontradaException("Erro ao excluir, verifique se há outros cadastro com referência a este registro.");
		}
	}
}

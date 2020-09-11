package br.com.crux.cmd;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import br.com.crux.dao.repository.AlunoRepository;
import br.com.crux.dao.repository.EncaminhaAlunosRepository;
import br.com.crux.entity.EncaminhaAlunos;
import br.com.crux.exception.ParametroNaoInformadoException;
import br.com.crux.exception.TabaleReferenciaEncontradaException;
import br.com.crux.exception.base.NegocioException;
import br.com.crux.to.AlunoTO;

@Component
public class ExcluirAlunoCmd {

	@Autowired private AlunoRepository repository;
	@Autowired private GetAlunoCmd getAlunoCmd;
	@Autowired private ExcluirAlunoCmd excluirAlunoCmd;
	@Autowired private ExcluirPessoaFisicaCmd excluirPessoaFisicaCmd;
	@Autowired private EncaminhaAlunosRepository encaminhaAlunosRepository;
	@Autowired private GetUnidadeLogadaCmd getUnidadeLogadaCmd;

	public void excluir(Long id) {
		try {
			if (Objects.isNull(id)) {
				throw new ParametroNaoInformadoException("Erro ao excluir o aluno. Parâmetro ausente.");
			}

			AlunoTO alunoTO = getAlunoCmd.getTOById(id);

			//Apaga todos as vulnerabilidades desse aluno.
			alunoTO.getVulnerabilidades().stream().forEach(r -> excluirAlunoCmd.excluir(r.getId()));

			//Apaga todos os encaminhamentos do aluno na unidade logada
			Optional<List<EncaminhaAlunos>> encaminhamentos = encaminhaAlunosRepository.findByUnidadeAluno(getUnidadeLogadaCmd.getUnidadeTO().getIdUnidade(), alunoTO.getId());
			if(encaminhamentos.isPresent()) {
				encaminhaAlunosRepository.deleteInBatch(encaminhamentos.get());
			}
			
			repository.deleteById(id);

			//Apaga a pessoa fisica
			excluirPessoaFisicaCmd.excluirPorId(alunoTO.getPessoaFisica().getId());

		} catch (Exception e) {
			if(Objects.nonNull(e.getCause())) {
				if(e.getCause() instanceof DataIntegrityViolationException || e.getCause().toString().contains("ConstraintViolationException")) {
					throw new TabaleReferenciaEncontradaException("Erro ao excluir, verifique se há outro cadastro com referência com esse registro.");
				}
			}

			throw new NegocioException(e.getMessage());
		}

	}
}

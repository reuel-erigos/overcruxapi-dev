package br.com.crux.cmd;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import br.com.crux.dao.repository.ArquivoRepository;
import br.com.crux.dao.repository.InstituicaoRepository;
import br.com.crux.entity.Instituicao;
import br.com.crux.exception.TabaleReferenciaEncontradaException;
import br.com.crux.to.UnidadeTO;

@Component
public class ExcluirInstituicaoCmd {

	@Autowired private InstituicaoRepository repository;
	@Autowired private GetInstituicaoCmd getInstituicaoCmd;
	
	@Autowired private ArquivoRepository arquivoRepository;
	@Autowired private GetUnidadeCmd getUnidadeCmd;
	
	public void excluir(Long idInstituicao) {
		Instituicao instituicaoApagar = getInstituicaoCmd.getById(idInstituicao);
		
		List<UnidadeTO> unidades = getUnidadeCmd.getAllUnidadesByInstituicao(idInstituicao);
		if (Objects.nonNull(unidades)) {
			throw new TabaleReferenciaEncontradaException("Por favor, exclua as unidades dessa instituição antes.");
		}
		
		try {
			if(instituicaoApagar.getIdArquivo() != null) {
				arquivoRepository.deleteById(instituicaoApagar.getIdArquivo());
			}
			
			repository.delete(instituicaoApagar);
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

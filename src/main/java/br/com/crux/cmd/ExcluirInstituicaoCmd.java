package br.com.crux.cmd;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.dao.repository.ArquivoMetadadosRepository;
import br.com.crux.dao.repository.ArquivoRepository;
import br.com.crux.dao.repository.InstituicaoRepository;
import br.com.crux.entity.Arquivo;
import br.com.crux.entity.Instituicao;
import br.com.crux.exception.TabaleReferenciaEncontradaException;
import br.com.crux.to.UnidadeTO;

@Component
public class ExcluirInstituicaoCmd {

	@Autowired private InstituicaoRepository repository;
	@Autowired private GetInstituicaoCmd getInstituicaoCmd;
	
	@Autowired private ArquivoRepository arquivoRepository;
	@Autowired private ArquivoMetadadosRepository arquivoMetadadosRepository;
	@Autowired private GetUnidadeCmd getUnidadeCmd;
	
	public void excluir(Long idInstituicao) {
		Instituicao instituicaoApagar = getInstituicaoCmd.getById(idInstituicao);
		
		List<UnidadeTO> unidades = getUnidadeCmd.getAllUnidadesByInstituicao(idInstituicao);
		if (Objects.nonNull(unidades)) {
			throw new TabaleReferenciaEncontradaException("Por favor, exclua as unidades dessa instituição antes.");
		}
		
		if(instituicaoApagar.getMetadados() != null) {
			Optional<Arquivo> arquivo = arquivoRepository.findByIdMetadados(instituicaoApagar.getMetadados().getId());
			arquivoRepository.deleteById(arquivo.get().getId());
			arquivoMetadadosRepository.deleteById(instituicaoApagar.getMetadados().getId());
		}
		
		repository.delete(instituicaoApagar);
	}
}

package br.com.crux.cmd;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import br.com.crux.dao.repository.ArquivoRepository;
import br.com.crux.dao.repository.PerspectivaRepository;
import br.com.crux.dao.repository.UnidadeRepository;
import br.com.crux.dao.repository.UsuariosUnidadeRepository;
import br.com.crux.entity.Perspectiva;
import br.com.crux.entity.Unidade;
import br.com.crux.entity.UsuariosUnidade;
import br.com.crux.exception.TabaleReferenciaEncontradaException;
import br.com.crux.exception.base.NegocioException;

@Component
public class ExcluirUnidadeCmd {

	@Autowired private UnidadeRepository unidadeRepository;
	@Autowired private UsuariosUnidadeRepository usuariosUnidadeRepository;
	
	@Autowired private ArquivoRepository arquivoRepository;
	@Autowired private PerspectivaRepository perspectivaRepository;
	
	
	public void excluir(Long idUnidade) {
		try {
			Optional<Unidade> unidade = unidadeRepository.findById(idUnidade);
			
			Optional<List<Perspectiva>> perspectiva = perspectivaRepository.findByIdUnidade(unidade.get().getIdUnidade());
			if(perspectiva.isPresent()) {
				throw new TabaleReferenciaEncontradaException("Por favor, exclua a Perspectiva antes.");
			}
			if(unidade.get().getIdArquivo() != null) {
				arquivoRepository.deleteById(unidade.get().getIdArquivo());
			}
			
			
			Optional<List<UsuariosUnidade>> usuariosUnidade = usuariosUnidadeRepository.findByUnidade(unidade.get());
			if(usuariosUnidade.isPresent()) {
				usuariosUnidade.get().stream().forEach(uu -> {
					usuariosUnidadeRepository.deleteById(uu.getId());
				});
			}
			
			unidadeRepository.delete(unidade.get());
			
		} catch (DataIntegrityViolationException e) {
			throw new NegocioException("Erro ao excluir, existem dados vinculados a essa unidade.");
		}
	}
}

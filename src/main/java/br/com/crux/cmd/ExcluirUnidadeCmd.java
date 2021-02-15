package br.com.crux.cmd;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.dao.repository.ArquivoMetadadosRepository;
import br.com.crux.dao.repository.ArquivoRepository;
import br.com.crux.dao.repository.CertificadoUnidadeRepository;
import br.com.crux.dao.repository.EstruturaUnidadeRepository;
import br.com.crux.dao.repository.PerspectivaRepository;
import br.com.crux.dao.repository.UnidadeRepository;
import br.com.crux.dao.repository.UsuariosUnidadeRepository;
import br.com.crux.entity.Arquivo;
import br.com.crux.entity.Perspectiva;
import br.com.crux.entity.Unidade;
import br.com.crux.entity.UsuariosUnidade;
import br.com.crux.exception.TabaleReferenciaEncontradaException;

@Component
public class ExcluirUnidadeCmd {

	@Autowired private UnidadeRepository unidadeRepository;
	@Autowired private EstruturaUnidadeRepository estruturaUnidadeRepository;
	@Autowired private CertificadoUnidadeRepository certificadoUnidadeRepository;
	@Autowired private UsuariosUnidadeRepository usuariosUnidadeRepository;
	@Autowired private ArquivoRepository arquivoRepository;
	@Autowired private ArquivoMetadadosRepository arquivoMetadadosRepository;
	@Autowired private PerspectivaRepository perspectivaRepository;
	
	
	public void excluir(Long idUnidade) {
		Optional<Unidade> unidade = unidadeRepository.findById(idUnidade);
		
		estruturaUnidadeRepository.deleteAll(unidade.get().getEstruturasUnidades());
		certificadoUnidadeRepository.deleteAll(unidade.get().getCertificadosUnidade());
		
		Optional<List<Perspectiva>> perspectiva = perspectivaRepository.findByIdUnidade(unidade.get().getIdUnidade());
		if(perspectiva.isPresent()) {
			throw new TabaleReferenciaEncontradaException("Por favor, exclua a Perspectiva antes.");
		}
		if(unidade.get().getArquivoMetadados() != null) {
			Optional<Arquivo> arquivo = arquivoRepository.findByIdMetadados(unidade.get().getArquivoMetadados().getId());
			
			arquivoRepository.deleteById(arquivo.get().getId());
			arquivoMetadadosRepository.deleteById(unidade.get().getArquivoMetadados().getId());
		}
		
		
		Optional<List<UsuariosUnidade>> usuariosUnidade = usuariosUnidadeRepository.findByUnidade(unidade.get());
		if(usuariosUnidade.isPresent()) {
			usuariosUnidade.get().stream().forEach(uu -> {
				usuariosUnidadeRepository.deleteById(uu.getId());
			});
		}
		
		unidadeRepository.delete(unidade.get());
	}
}

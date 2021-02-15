package br.com.crux.cmd;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.dao.repository.ArquivoMetadadosRepository;
import br.com.crux.dao.repository.ArquivoRepository;
import br.com.crux.entity.Arquivo;

@Component
public class ExcluirArquivoPessoaFisicaCmd {

	@Autowired private ArquivoRepository repository;
	@Autowired private ArquivoMetadadosRepository arquivoMetadadosRepository;

	public void excluirPorIdMetadado(Long idArquivoMetadados) {
		Optional<Arquivo> arquivo = repository.findByIdMetadados(idArquivoMetadados);
		if(arquivo.isPresent()) {
			repository.deleteById(arquivo.get().getId());
			arquivoMetadadosRepository.deleteById(idArquivoMetadados);
		}
		
	}
}

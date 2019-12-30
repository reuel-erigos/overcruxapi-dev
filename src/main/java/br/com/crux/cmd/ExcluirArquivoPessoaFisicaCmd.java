package br.com.crux.cmd;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import br.com.crux.dao.repository.ArquivoRepository;
import br.com.crux.entity.Arquivo;
import br.com.crux.exception.ArquivoNaoEncontradoException;
import br.com.crux.exception.ParametroNaoInformadoException;
import br.com.crux.exception.TabaleReferenciaEncontradaException;

@Component
public class ExcluirArquivoPessoaFisicaCmd {

	@Autowired private ArquivoRepository repository;

	public void excluirPorId(Long idArquivo) {
		try {
			if (Objects.isNull(idArquivo)) {
				throw new ParametroNaoInformadoException("identificador do arquivo não informado.");
			}
			
			Arquivo arquivo = repository.findById(idArquivo).orElseThrow(() -> new ArquivoNaoEncontradoException("Arquivo informado não existe."));
			
			repository.delete(arquivo);
			
		} catch (DataIntegrityViolationException e) {
			throw new TabaleReferenciaEncontradaException("Erro ao excluir, verifique se há outro cadastro com referência a este registro.");
		}	

	}
}

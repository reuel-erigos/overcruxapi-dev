package br.com.crux.cmd;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import br.com.crux.dao.repository.ParceriasProjetoRepository;
import br.com.crux.entity.MateriaisProjeto;
import br.com.crux.entity.ParceriasCategorias;
import br.com.crux.entity.ParceriasProjeto;
import br.com.crux.exception.TabaleReferenciaEncontradaException;

@Component
public class ExcluirParceriasProjetoCmd {

	@Autowired private ExcluirMateriaisParceriasProjetoCmd excluirMateriaisParceriasProjetoCmd;
	@Autowired private ExcluirParceriasCategoriasCmd excluirParceriasCategoriasCmd;
	@Autowired private GetMateriaisParceirosProjetoCmd getMateriaisParceirosProjetoCmd;
	@Autowired private ParceriasProjetoRepository parceriasProjetoRepository;
	@Autowired private GetParceriasCategoriasCmd getParceriasCategoriasCmd;

	public void deletar(ParceriasProjeto parceriasProjeto) {
		List<MateriaisProjeto> listaMateriasParceiros = getMateriaisParceirosProjetoCmd.getMateriaisProjetoByParceriasProjeto(parceriasProjeto);
		excluirMateriaisParceriasProjetoCmd.deletarAll(listaMateriasParceiros);
		
		List<ParceriasCategorias> listaParceriasCategorias = getParceriasCategoriasCmd.getParceriasCategoriasByParceriasProjeto(parceriasProjeto);
		excluirParceriasCategoriasCmd.deletarAll(listaParceriasCategorias);

		try {
			parceriasProjetoRepository.delete(parceriasProjeto);

		} catch (Exception e) {
			if(e.getCause() instanceof DataIntegrityViolationException || e.getCause().toString().contains("ConstraintViolationException")) {
				throw new TabaleReferenciaEncontradaException("Erro ao excluir, apague antes os cadastros com referência a esse registro.");
			}
			throw new RuntimeException(e.getMessage());
		}

	}

}

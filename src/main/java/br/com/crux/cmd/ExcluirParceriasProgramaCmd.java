package br.com.crux.cmd;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import br.com.crux.dao.repository.ParceriasProgramaRepository;
import br.com.crux.entity.MateriaisPrograma;
import br.com.crux.entity.ParceriasCategorias;
import br.com.crux.entity.ParceriasPrograma;
import br.com.crux.exception.TabaleReferenciaEncontradaException;

@Component
public class ExcluirParceriasProgramaCmd {

	@Autowired private ExcluirMateriaisParceriasProgramaCmd excluirMateriaisParceriasProgramaCmd;
	@Autowired private ExcluirParceriasCategoriasCmd excluirParceriasCategoriasCmd;
	@Autowired private GetMateriaisParceirosProgramaCmd getMateriaisParceirosProgramaCmd;
	@Autowired private GetParceriasCategoriasCmd getParceriasCategoriasCmd;
	@Autowired private ParceriasProgramaRepository parceriasProgramaRepository;

	public void deletar(ParceriasPrograma parceriasPrograma) {
		List<MateriaisPrograma> listaMateriasParceiros = getMateriaisParceirosProgramaCmd.getMateriaisProgramaByParceriasPrograma(parceriasPrograma);
		List<ParceriasCategorias> listaParceriasCategorias = getParceriasCategoriasCmd.getParceriasCategoriasByParceriasPrograma(parceriasPrograma);
		excluirMateriaisParceriasProgramaCmd.deletarAll(listaMateriasParceiros);
		excluirParceriasCategoriasCmd.deletarAll(listaParceriasCategorias);

		try {
			parceriasProgramaRepository.delete(parceriasPrograma);

		} catch (Exception e) {
			if(e.getCause() instanceof DataIntegrityViolationException || e.getCause().toString().contains("ConstraintViolationException")) {
				throw new TabaleReferenciaEncontradaException("Erro ao excluir, apague antes os cadastros com referência a esse registro.");
			}
			throw new RuntimeException(e.getMessage());
		}

	}

}

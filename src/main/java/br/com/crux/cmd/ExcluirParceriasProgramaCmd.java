package br.com.crux.cmd;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import br.com.crux.dao.repository.ParceriasProgramaRepository;
import br.com.crux.entity.MateriaisPrograma;
import br.com.crux.entity.ParceriasPrograma;
import br.com.crux.exception.TabaleReferenciaEncontradaException;

@Component
public class ExcluirParceriasProgramaCmd {

	@Autowired private ExcluirMateriaisParceriasProgramaCmd excluirMateriaisParceriasProgramaCmd;
	@Autowired private GetMateriaisParceirosProgramaCmd getMateriaisParceirosProgramaCmd;
	@Autowired private ParceriasProgramaRepository parceriasProgramaRepository;

	public void deletar(ParceriasPrograma parceriasPrograma) {
		List<MateriaisPrograma> listaMateriasParceiros = getMateriaisParceirosProgramaCmd.getMateriaisProgramaByParceriasPrograma(parceriasPrograma);
		excluirMateriaisParceriasProgramaCmd.deletarAll(listaMateriasParceiros);

		try {
			parceriasProgramaRepository.delete(parceriasPrograma);

		} catch (DataIntegrityViolationException e) {
			throw new TabaleReferenciaEncontradaException("Erro ao excluir, verifique se há outro cadastro com referência a este parceiro.");
		}

	}

}

package br.com.crux.cmd;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import br.com.crux.dao.repository.ParceriasProjetoRepository;
import br.com.crux.entity.MateriaisProjeto;
import br.com.crux.entity.ParceriasProjeto;
import br.com.crux.exception.TabaleReferenciaEncontradaException;

@Component
public class ExcluirParceriasProjetoCmd {

	@Autowired private ExcluirMateriaisParceriasProjetoCmd excluirMateriaisParceriasProjetoCmd;
	@Autowired private GetMateriaisParceirosProjetoCmd getMateriaisParceirosProjetoCmd;
	@Autowired private ParceriasProjetoRepository parceriasProjetoRepository;

	public void deletar(ParceriasProjeto parceriasProjeto) {
		List<MateriaisProjeto> listaMateriasParceiros = getMateriaisParceirosProjetoCmd.getMateriaisProjetoByParceriasProjeto(parceriasProjeto);
		excluirMateriaisParceriasProjetoCmd.deletarAll(listaMateriasParceiros);

		try {
			parceriasProjetoRepository.delete(parceriasProjeto);

		} catch (DataIntegrityViolationException e) {
			throw new TabaleReferenciaEncontradaException("Erro ao excluir, verifique se há outro cadastro com referência a este parceiro.");
		}

	}

}

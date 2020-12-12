package br.com.crux.cmd;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.dao.repository.ParceriasProgramaRepository;
import br.com.crux.entity.MateriaisPrograma;
import br.com.crux.entity.ParceriasCategorias;
import br.com.crux.entity.ParceriasPrograma;

@Component
public class ExcluirParceriasProgramaCmd {

	@Autowired private ExcluirMateriaisParceriasProgramaCmd excluirMateriaisParceriasProgramaCmd;
	@Autowired private ExcluirParceriasCategoriasCmd excluirParceriasCategoriasCmd;
	@Autowired private ExcluirAditivoParceriaProgramaCmd excluirAditivoParceriaProgramaCmd;
	@Autowired private GetMateriaisParceirosProgramaCmd getMateriaisParceirosProgramaCmd;
	@Autowired private GetParceriasCategoriasCmd getParceriasCategoriasCmd;
	@Autowired private ParceriasProgramaRepository parceriasProgramaRepository;

	public void deletar(ParceriasPrograma parceriasPrograma) {
		List<MateriaisPrograma> listaMateriasParceiros = getMateriaisParceirosProgramaCmd.getMateriaisProgramaByParceriasPrograma(parceriasPrograma);
		List<ParceriasCategorias> listaParceriasCategorias = getParceriasCategoriasCmd.getParceriasCategoriasByParceriasPrograma(parceriasPrograma);
		excluirMateriaisParceriasProgramaCmd.deletarAll(listaMateriasParceiros);
		excluirParceriasCategoriasCmd.deletarAll(listaParceriasCategorias);
		excluirAditivoParceriaProgramaCmd.deletarAll(parceriasPrograma.getAditivosParceriaPrograma());

		parceriasProgramaRepository.delete(parceriasPrograma);
	}

}

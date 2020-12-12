package br.com.crux.cmd;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.dao.repository.ParceriasProjetoRepository;
import br.com.crux.entity.MateriaisProjeto;
import br.com.crux.entity.ParceriasCategorias;
import br.com.crux.entity.ParceriasProjeto;

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

		parceriasProjetoRepository.delete(parceriasProjeto);
	}

}

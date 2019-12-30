package br.com.crux.cmd;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.dao.repository.MateriaisProgramaRepository;
import br.com.crux.entity.MateriaisPrograma;

@Component
public class ExcluirMateriaisParceriasProgramaCmd {

	@Autowired MateriaisProgramaRepository materiaisProgramaRepository;

	public void deletarAll(List<MateriaisPrograma> listaMateriasParceiros) {
		materiaisProgramaRepository.deleteInBatch(listaMateriasParceiros);

	}

}

package br.com.crux.cmd;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.dao.repository.MateriaisProjetoRepository;
import br.com.crux.entity.MateriaisProjeto;

@Component
public class ExcluirMateriaisParceriasProjetoCmd {

	@Autowired MateriaisProjetoRepository materiaisProjetoRepository;

	public void deletarAll(List<MateriaisProjeto> listaMateriasParceiros) {
		materiaisProjetoRepository.deleteInBatch(listaMateriasParceiros);

	}

}

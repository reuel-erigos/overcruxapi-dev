package br.com.crux.cmd;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.dao.repository.ParceriasCategoriasRepository;
import br.com.crux.entity.ParceriasCategorias;

@Component
public class ExcluirParceriasCategoriasCmd {

	@Autowired ParceriasCategoriasRepository repository;
	
	//TODO: josue fazer o excluir dos aditivos!!!!

	public void deletarAll(List<ParceriasCategorias> listaParceriasCategorias) {
		repository.deleteInBatch(listaParceriasCategorias);

	}

}

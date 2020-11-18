package br.com.crux.cmd;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.dao.repository.ParceriasCategoriasRepository;
import br.com.crux.entity.ParceriasCategorias;

@Component
public class ExcluirParceriasCategoriasCmd {

	@Autowired ParceriasCategoriasRepository repository;
	@Autowired ExcluirAditivoParceriaCategoriaCmd excluirAditivoParceriaCategoriaCmd;

	public void excluir(ParceriasCategorias entity) {
		excluirAditivoParceriaCategoriaCmd.deletarAll(entity.getAditivoParceriaCategoria());
		repository.delete(entity);
	}
	
	public void deletarAll(List<ParceriasCategorias> listaParceriasCategorias) {
		
		listaParceriasCategorias
		.forEach(pc -> excluirAditivoParceriaCategoriaCmd.deletarAll(pc.getAditivoParceriaCategoria())); 
		
		repository.deleteInBatch(listaParceriasCategorias);

	}






}

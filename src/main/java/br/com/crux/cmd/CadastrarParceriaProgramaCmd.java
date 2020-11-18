package br.com.crux.cmd;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.ParceriasProgramaTOBuilder;
import br.com.crux.dao.repository.ParceriasProgramaRepository;
import br.com.crux.entity.ParceriasPrograma;
import br.com.crux.entity.Programa;
import br.com.crux.to.ParceriasProgramaTO;

@Component
public class CadastrarParceriaProgramaCmd {

	@Autowired private ParceriasProgramaRepository programasUnidadeRepository;
	@Autowired private ParceriasProgramaTOBuilder parceriasProgramaTOBuilder;
	@Autowired private AlterarMateriaisParceriaProgramaCmd alterarMateriaisParceriaProgramaCmd;
	@Autowired private AlterarParceriasCategoriasProgramaCmd alterarParceriasCategoriasProgramaCmd;
	@Autowired private AlterarContasCentrosCustoProgramaCmd alterarContasCentrosCustoCmd;
	@Autowired private AlterarAditivoParceriaProgramaCmd alterarAditivoParceriaProgramaCmd;
//	@Autowired private CadastrarContasCentrosCustoCmd cadastrarContasCentrosCustoCmd;
	
	public ParceriasPrograma cadastrar(Programa programa, ParceriasProgramaTO to) {
		ParceriasPrograma entity = parceriasProgramaTOBuilder.build(programa, to);
		ParceriasPrograma parceriasPrograma = programasUnidadeRepository.save(entity);
		
		alterarMateriaisParceriaProgramaCmd.alterarAll(programa, parceriasPrograma, to.getMateriaisPrograma());
		alterarParceriasCategoriasProgramaCmd.alterarAll(to.getParceriasCategorias(), parceriasPrograma);
		alterarAditivoParceriaProgramaCmd.alterarAll(to.getAditivosParceriasProgramas(), parceriasPrograma);
//		cadastrarContasCentrosCustoCmd.cadastrarLista(parceriasPrograma,null,to.getContasCentrosCusto());
		alterarContasCentrosCustoCmd.alterarAll(to.getContasCentrosCusto(), parceriasPrograma);
		
		return entity;
	}

	public List<ParceriasPrograma> cadastrarLista(Programa programa, List<ParceriasProgramaTO> parceriasPrograma) {
		return parceriasPrograma.stream().map(parceriaPrograma -> cadastrar(programa, parceriaPrograma)).collect(Collectors.toList());

	}

}

package br.com.crux.cmd;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.OficinasTOBuilder;
import br.com.crux.dao.repository.AcaoRepository;
import br.com.crux.dao.repository.ColaboradoresAtividadeRepository;
import br.com.crux.dao.repository.MateriaisAtividadeRepository;
import br.com.crux.dao.repository.OficinasRepository;
import br.com.crux.entity.Acoes;
import br.com.crux.entity.ColaboradoresAtividade;
import br.com.crux.entity.MateriaisAtividade;
import br.com.crux.entity.Oficinas;
import br.com.crux.exception.TabaleReferenciaEncontradaException;
import br.com.crux.rule.CamposObrigatoriosAtividadeRule;
import br.com.crux.to.OficinasTO;
import br.com.crux.to.TurmasTO;

@Component
public class AlterarOficinasCmd {

	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;
	@Autowired private OficinasRepository repository;
	@Autowired private OficinasTOBuilder toBuilder;
	@Autowired private AlterarColaboradesAtividadeCmd alterarColaboradesAtividadeCmd;
	@Autowired private AlterarMateriaisAtividadeCmd alterarMateriaisAtividadeCmd;

	@Autowired private CamposObrigatoriosAtividadeRule camposObrigatoriosRule;
	
	@Autowired private AcaoRepository acaoRepository;
	@Autowired private ColaboradoresAtividadeRepository colaboradoresRepository;
	@Autowired private MateriaisAtividadeRepository materiaisRepository;
	
	public void alterar(OficinasTO to) {
		camposObrigatoriosRule.verificar(to);

		to.setUsuarioAlteracao(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());
		
		Oficinas entity = toBuilder.build(to);
		repository.save(entity);
		
		alterarColaboradesAtividadeCmd.alterarAll(to.getColaboradoresAtividade(),entity.getId());
		alterarMateriaisAtividadeCmd.alterarAll(to.getMateriaisAtividade(), entity.getId());
	}
	
	
	private void validarIntegridade(Oficinas oficina) {
		Optional<List<Acoes>> acoes = acaoRepository.findByOficina(oficina.getId());
		if(acoes.isPresent()) {
			throw new TabaleReferenciaEncontradaException("Erro ao excluir, existem ações cadastradas para essa oficina (" + oficina.getDescricao() + ")");
		}
		
		Optional<List<ColaboradoresAtividade>> colaboradores = colaboradoresRepository.getPorAtividade(oficina.getId());
		if(colaboradores.isPresent()) {
			throw new TabaleReferenciaEncontradaException("Erro ao excluir, existem colaboradores cadastrados para essa oficina (" + oficina.getDescricao() + ")");
		}
		
		Optional<List<MateriaisAtividade>> materiais = materiaisRepository.getPorAtividade(oficina.getId());
		if(materiais.isPresent()) {
			throw new TabaleReferenciaEncontradaException("Erro ao excluir, existem materiais cadastrados para essa oficina (" + oficina.getDescricao() + ")");
		}
		
	}

	public void alterarAll(List<OficinasTO> listaTO, TurmasTO turmaTO) {
		
		//Lista de oficinas do banco de dados
		List<Oficinas> listaBD = repository.findByIdTurma(turmaTO.getId()).orElse(new ArrayList<Oficinas>());
		
		BiPredicate<OficinasTO, List<OficinasTO>> contemNaLista  = (parte, lista) -> lista.stream()
                                                                                          .anyMatch(registroTO -> Objects.nonNull(registroTO.getId()) 
                                                                                        		                 && 
                                                                                        		                 registroTO.getId().equals(parte.getId()));
		
		
		//Remove da lista todos os registros que não contém no Banco de Dados
		listaBD.removeIf(registro -> {
										if(!contemNaLista.test(toBuilder.buildTO(registro), listaTO)){
											validarIntegridade(registro);
											repository.delete(registro); 
											return true;
										}
										return false;
						             }
		                 );
		
		//Adiciona os novos registros
		List<OficinasTO> novos = listaTO.stream()
				                        .filter(registro -> Objects.isNull(registro.getId()))
				                        .collect(Collectors.toList());
		
		if(Objects.nonNull(novos)){
			novos.forEach(novo -> {
				novo.setIdTurma(turmaTO.getId());
				novo.setUnidade(turmaTO.getUnidade());
				alterar(novo);
			});
		}

		//Atualiza os registros 
		listaTO.stream()
		       .filter(registro -> Objects.nonNull(registro.getId()))
		       .forEach( registro -> {
		    	   
			if(contemNaLista.test(registro, toBuilder.buildAll(listaBD))){
				registro.setIdTurma(turmaTO.getId());
				alterar(registro);
			}
		});
	}	
	
	
	
}

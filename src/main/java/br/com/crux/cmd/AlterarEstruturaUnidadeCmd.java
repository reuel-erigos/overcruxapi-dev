package br.com.crux.cmd;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.EstruturaUnidadeTOBuilder;
import br.com.crux.dao.repository.EstruturaUnidadeRepository;
import br.com.crux.entity.EstruturaUnidade;
import br.com.crux.entity.Unidade;
import br.com.crux.to.EstruturaUnidadeTO;

@Component
public class AlterarEstruturaUnidadeCmd extends AbstractAlterarListaCmd<EstruturaUnidade, EstruturaUnidadeTO, Unidade> {
	
	@Autowired private EstruturaUnidadeRepository repository;
	@Autowired private EstruturaUnidadeTOBuilder toBuilder;
	@Autowired private CadastrarEstruturaUnidadeCmd cadastrarCmd;
	@Autowired private ExcluirEstruturaUnidadeCmd excluirCmd;
	
	@Override
	protected EstruturaUnidadeTO getTO(EstruturaUnidade entity) {
		return toBuilder.buildTO(entity);
	}
	@Override
	protected List<EstruturaUnidadeTO> getTOListaBanco(
			List<EstruturaUnidade> lista) {
		return toBuilder.buildAll(lista);
	}
	@Override
	protected List<EstruturaUnidade> getListaBanco(Unidade pai) {
		return repository.findByUnidade(pai).orElse(new ArrayList<EstruturaUnidade>());
	}
	@Override
	protected Long getIdentificadorTO(EstruturaUnidadeTO to) {
		return to.getId();
	}
	@Override
	protected void cadastrar(EstruturaUnidadeTO to, Unidade p) {
		cadastrarCmd.cadastrar(p,to);
		
	}
	@Override
	protected void deletar(EstruturaUnidade registro) {
		excluirCmd.excluir(registro.getId());
	}
	
}

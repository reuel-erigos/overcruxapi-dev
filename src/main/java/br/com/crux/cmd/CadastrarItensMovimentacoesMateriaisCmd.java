package br.com.crux.cmd;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.ItensMovimentacoesMateriaisTOBuilder;
import br.com.crux.dao.repository.ItensMovimentacoesMateriaisRepository;
import br.com.crux.entity.ItensMovimentacoesMateriais;
import br.com.crux.entity.MovimentacoesMateriais;
import br.com.crux.to.ItensMovimentacoesMateriaisTO;

@Component
public class CadastrarItensMovimentacoesMateriaisCmd {

	@Autowired ItensMovimentacoesMateriaisRepository repository;
	@Autowired ItensMovimentacoesMateriaisTOBuilder tOBuilder;

	public ItensMovimentacoesMateriais cadastrar(ItensMovimentacoesMateriaisTO itensMovimentacoesMateriaisTO, MovimentacoesMateriais movimentacoesMateriais) {
		ItensMovimentacoesMateriais entity = tOBuilder.build(movimentacoesMateriais, itensMovimentacoesMateriaisTO);
		return repository.save(entity);
	}

	public List<ItensMovimentacoesMateriais> cadastrarLista(MovimentacoesMateriais movimentacoesMateriais, List<ItensMovimentacoesMateriaisTO> list) {
		return list.stream()
				.map(item -> cadastrar(item,movimentacoesMateriais))
				.collect(Collectors.toList());

	}

}

package br.com.crux.cmd;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.ReembolsosPagamentosTOBuilder;
import br.com.crux.dao.repository.ReembolsosPagamentosRepository;
import br.com.crux.entity.PagamentosFatura;
import br.com.crux.entity.ReembolsosPagamentos;
import br.com.crux.rule.ValidarContaReembolsoRule;
import br.com.crux.to.ReembolsosPagamentosTO;

@Component
public class CadastrarReembolsosPagamentosCmd {

	@Autowired private ReembolsosPagamentosRepository repository;
	@Autowired private ReembolsosPagamentosTOBuilder tOBuilder;
	@Autowired private ValidarContaReembolsoRule validarContaReembolsoRule; 

	public ReembolsosPagamentos cadastrar(ReembolsosPagamentosTO pagamentosFaturaTO, PagamentosFatura pagamento) {
		ReembolsosPagamentos entity = tOBuilder.build(pagamento, pagamentosFaturaTO);
		return repository.save(entity);
	}

	public List<ReembolsosPagamentos> cadastrarLista(PagamentosFatura pagamento, List<ReembolsosPagamentosTO> lista) {
		if(Objects.nonNull(pagamento.getContaBancaria())) {
			validarContaReembolsoRule.verificar(pagamento.getContaBancaria().getId(), lista);
		}
		
		return lista.stream()
				.map(item -> cadastrar(item, pagamento))
				.collect(Collectors.toList());

	}

}

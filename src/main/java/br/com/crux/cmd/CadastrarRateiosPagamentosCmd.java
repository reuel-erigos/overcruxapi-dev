package br.com.crux.cmd;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.RateiosPagamentosTOBuilder;
import br.com.crux.dao.repository.RateiosPagamentosRepository;
import br.com.crux.entity.PagamentosFatura;
import br.com.crux.entity.RateiosPagamentos;
import br.com.crux.rule.ValidarContaRateioPagamentoRule;
import br.com.crux.to.RateiosPagamentosTO;

@Component
public class CadastrarRateiosPagamentosCmd {

	@Autowired private RateiosPagamentosRepository repository;
	@Autowired private RateiosPagamentosTOBuilder tOBuilder;
	@Autowired private ValidarContaRateioPagamentoRule rule; 

	public RateiosPagamentos cadastrar(RateiosPagamentosTO to, PagamentosFatura pagamento) {
		RateiosPagamentos entity = tOBuilder.build(pagamento, to);
		return repository.save(entity);
	}

	public List<RateiosPagamentos> cadastrarLista(PagamentosFatura pagamento, List<RateiosPagamentosTO> lista) {
		if(Objects.nonNull(pagamento.getContaBancaria())) {
			rule.verificar(pagamento.getContaBancaria().getId(), lista);
		}
		
		return lista.stream()
				.map(item -> cadastrar(item, pagamento))
				.collect(Collectors.toList());

	}

}

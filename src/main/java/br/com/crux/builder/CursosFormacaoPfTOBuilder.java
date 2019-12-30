package br.com.crux.builder;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.entity.CursosFormacaoPf;
import br.com.crux.to.CursosFormacaoPfTO;

@Component
public class CursosFormacaoPfTOBuilder {

	@Autowired
	private PessoaFisicaTOBuilder pessoaFisicaBuilder;

	public CursosFormacaoPf build(CursosFormacaoPfTO p) {
		CursosFormacaoPf retorno = new CursosFormacaoPf();

		retorno.setId(p.getId());
		retorno.setNome(p.getNome());
		retorno.setNomeInstuicao(p.getNomeInstuicao());
		retorno.setDataInicio(p.getDataInicio());
		retorno.setDataFim(p.getDataFim());
		retorno.setPessoaFisica(pessoaFisicaBuilder.build(p.getPessoaFisica()));
		retorno.setUsuarioAlteracao(p.getUsuarioAlteracao());

		return retorno;
	}

	public CursosFormacaoPfTO buildTO(CursosFormacaoPf p) {
		CursosFormacaoPfTO retorno = new CursosFormacaoPfTO();
		
		retorno.setId(p.getId());
		retorno.setNome(p.getNome());
		retorno.setNomeInstuicao(p.getNomeInstuicao());
		retorno.setDataInicio(p.getDataInicio());
		retorno.setDataFim(p.getDataFim());
		retorno.setPessoaFisica(pessoaFisicaBuilder.buildTO(p.getPessoaFisica()));
		retorno.setUsuarioAlteracao(p.getUsuarioAlteracao());
		
		retorno.setUsuarioAlteracao(p.getUsuarioAlteracao());

		return retorno;
	}

	public List<CursosFormacaoPfTO> buildAll(List<CursosFormacaoPf> dtos) {
		return dtos.stream().map(dto -> buildTO(dto)).collect(Collectors.toList());
	}

}

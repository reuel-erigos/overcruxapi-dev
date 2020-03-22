package br.com.crux.builder;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import br.com.crux.dao.dto.MovimentoExtratoDTO;
import br.com.crux.to.MovimentosExtratoTO;

@Component
public class MovimentosExtratoTOBuilder {

	public MovimentosExtratoTO build(MovimentoExtratoDTO p) {
		MovimentosExtratoTO retorno = new MovimentosExtratoTO();

		if(Objects.isNull(p)) {
			return retorno;
		}
		
		retorno.setData(p.getData());
		retorno.setDescricao(p.getDescricao());
		retorno.setNumero(p.getNumero());
		retorno.setSaldo(p.getSaldo());
		retorno.setValorEntrada(p.getValorEntrada());
		retorno.setValorSaida(p.getValorSaida());

		return retorno;
	}

	public List<MovimentosExtratoTO> buildAll(List<MovimentoExtratoDTO> dtos) {
		return dtos.stream().map(dto -> build(dto)).collect(Collectors.toList());
	}

}

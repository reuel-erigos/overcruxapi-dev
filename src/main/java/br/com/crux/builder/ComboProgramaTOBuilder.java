package br.com.crux.builder;

import java.util.Objects;

import org.springframework.stereotype.Component;

import br.com.crux.dao.dto.ComboProgramaDTO;
import br.com.crux.to.ComboProgramaTO;

@Component
public class ComboProgramaTOBuilder
		extends
			Builder<ComboProgramaDTO, ComboProgramaTO> {


	@Override
	public ComboProgramaTO buildTO(ComboProgramaDTO dto) {
		ComboProgramaTO to = new ComboProgramaTO();

		if (Objects.isNull(dto)) {
			return to;
		}

		to.setId(dto.getId());
		to.setNome(dto.getNome());
		to.setDataInicio(dto.getDataInicio());
		to.setDataFim(dto.getDataFim());

		return to;
	}

}

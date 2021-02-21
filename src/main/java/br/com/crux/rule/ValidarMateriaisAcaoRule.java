package br.com.crux.rule;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import br.com.crux.exception.MaterialAcaoException;
import br.com.crux.to.AcaoTO;
import br.com.crux.to.MateriaisAcoesTO;

@Component
public class ValidarMateriaisAcaoRule {

	public void validar(AcaoTO to) {
		
		if(Objects.nonNull(to.getMateriaisAcao()) && !to.getMateriaisAcao().isEmpty()) {
			MateriaisAcoesTO temQuantidadeInvalida = to.getMateriaisAcao().stream()
													                      .filter( m -> Objects.isNull(m.getQuantidadeMaterial()) || m.getQuantidadeMaterial() == 0 )
													                      .findAny()
													                      .orElse(null);
			
			if(Objects.nonNull(temQuantidadeInvalida)) {
				throw new MaterialAcaoException("Existem materiais com quantidade não informada ou com valor zero(0).");
			}
			
			List<String> materiaisTemp = to.getMateriaisAcao().stream()
					                                          .map(m -> m.getMaterial())
					                                          .map(m -> m.getNome())
					                                          .collect(Collectors.toList());
			
			List<String> jaExiste = materiaisTemp.stream()
									             .filter(i -> Collections.frequency(materiaisTemp, i) >1)
						                         .collect(Collectors.toList());
			
			if(Objects.nonNull(jaExiste) && !jaExiste.isEmpty()) {
				throw new MaterialAcaoException("Existem materiais duplicados na atividade.");
		    }
			
		}
		

	}
}

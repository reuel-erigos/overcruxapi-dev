				
package br.com.crux.cmd;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.MotivoDesligamentoTOBuilder;
import br.com.crux.dao.repository.MotivoDesligamentoRepository;
import br.com.crux.entity.MotivoDesligamento;
import br.com.crux.exception.NotFoundException;
import br.com.crux.to.MotivoDesligamentoTO;

@Component
public class GetMotivoDesligamentoCmd {

	@Autowired private MotivoDesligamentoRepository repository;
	@Autowired private MotivoDesligamentoTOBuilder toBuilder;
	@Autowired private GetUnidadeLogadaCmd getUnidadeLogadaCmd;

	public List<MotivoDesligamentoTO> getAll() {
		Long idInstituicao = getUnidadeLogadaCmd.getUnidadeTO().getInstituicao().getId();
		List<MotivoDesligamento> lista = repository.findByInstituicao(idInstituicao);
		if(lista.isEmpty()) {
			return Collections.emptyList();
		}
			return toBuilder.buildTO(lista);
	}

	public MotivoDesligamento getById(Long id) {
		return repository.findById(id).orElse(null);
	}

	public MotivoDesligamentoTO getTOById(Long id) {
		MotivoDesligamento entity = repository.findById(id).orElseThrow(() -> new NotFoundException("Registro não encontrado."));
		return toBuilder.buildTO(entity);
	}
}

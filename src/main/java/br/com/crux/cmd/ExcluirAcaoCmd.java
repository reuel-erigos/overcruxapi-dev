package br.com.crux.cmd;

import java.util.Objects;

import br.com.crux.dao.repository.GrupoAcoesRepository;
import br.com.crux.entity.GrupoAcoes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.dao.repository.AcaoRepository;
import br.com.crux.exception.ParametroNaoInformadoException;

@Component
public class ExcluirAcaoCmd {

	@Autowired
	private AcaoRepository repository;

	@Autowired
	private GrupoAcoesRepository grupoAcoesRepository;
	
	
	public void excluir(Long id) {
		if(Objects.isNull(id)) {
			throw new ParametroNaoInformadoException("Erro ao excluir a Ação. Parâmetro ausente.");
		}
		GrupoAcoes grupoAcoes = grupoAcoesRepository.findByAcaoId(id)
				.orElseThrow(() -> new ParametroNaoInformadoException("Grupo de Ação não encontrado."));

		repository.deleteById(id);

		if (grupoAcoes.getAcoes().isEmpty())
			grupoAcoesRepository.deleteById(grupoAcoes.getId());
	}
}

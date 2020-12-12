package br.com.crux.cmd;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.dao.repository.DepartamentoRepository;
import br.com.crux.exception.ParametroNaoInformadoException;

@Component
public class ExcluirDepartamentoCmd {

	@Autowired
	private DepartamentoRepository departamentoRepository;
	
	
	public void excluir(Long idDepartamento) {
		if(Objects.isNull(idDepartamento)) {
			throw new ParametroNaoInformadoException("Erro ao excluir o departamento. Parâmetro 'departamento' ausente.");
		}
		departamentoRepository.deleteById(idDepartamento);
	}
}

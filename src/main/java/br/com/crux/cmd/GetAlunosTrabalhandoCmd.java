package br.com.crux.cmd;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.AlunosTrabalhandoTOBuilder;
import br.com.crux.dao.AlunoTrabalhandoDao;
import br.com.crux.dao.dto.AlunoTrabalhandoDTO;
import br.com.crux.dao.repository.AlunosTrabalhandoRepository;
import br.com.crux.entity.AlunosTrabalhando;
import br.com.crux.exception.NotFoundException;
import br.com.crux.to.AlunosTrabalhandoTO;
import br.com.crux.to.ComboAlunoTrabalhandoTO;

@Component
public class GetAlunosTrabalhandoCmd {

	@Autowired private AlunosTrabalhandoRepository repository;
	@Autowired private AlunosTrabalhandoTOBuilder toBuilder;
	@Autowired private GetUnidadeLogadaCmd getUnidadeLogadaCmd;
	@Autowired private AlunoTrabalhandoDao alunoTrabalhandoDao;
	
	public List<ComboAlunoTrabalhandoTO> getAllByCombo() {
		Long idInstituicao = getUnidadeLogadaCmd.getUnidadeTO().getInstituicao().getId();
		List<AlunoTrabalhandoDTO> alunos = alunoTrabalhandoDao.getAllByInstituicao(idInstituicao);
		
		return toBuilder.buildAllDTO(alunos);
	}
	
	
	public List<AlunosTrabalhandoTO> getAll() {
		Long idInstituicao = getUnidadeLogadaCmd.getUnidadeTO().getInstituicao().getId();
		Optional<List<AlunosTrabalhando>> entitys = repository.findByInstituicao(idInstituicao);
		if(entitys.isPresent()) {
			return toBuilder.buildAll(entitys.get());
		}
		return new ArrayList<AlunosTrabalhandoTO>();
	}
	
	public AlunosTrabalhandoTO getById(Long id) {
		Optional<AlunosTrabalhando> entityOptional = repository.findById(id);
		if(!entityOptional.isPresent()) {
			return null;
		}
		return toBuilder.buildTO(entityOptional.get());
	}
			
}

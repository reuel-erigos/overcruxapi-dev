package br.com.crux.builder;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.cmd.GetAlunoCmd;
import br.com.crux.cmd.GetUsuarioLogadoCmd;
import br.com.crux.dao.dto.ComboSituacaoExAlunoDTO;
import br.com.crux.entity.SituacaoExAluno;
import br.com.crux.to.ComboSituacaoExAlunoTO;
import br.com.crux.to.SituacaoExAlunoTO;

@Component
public class SituacaoExAlunoTOBuilder extends Builder<SituacaoExAluno, SituacaoExAlunoTO>{

	@Autowired
	private GetUsuarioLogadoCmd getUsuarioLogadoCmd;
	@Autowired
	private GetAlunoCmd getAlunoCmd;
	@Autowired
	private AlunoTOBuilder alunoTOBuilder;

	public SituacaoExAlunoTO buildTO(SituacaoExAluno entity) {
		SituacaoExAlunoTO to = new SituacaoExAlunoTO();
		
		if(Objects.isNull(entity)) {
			return to;
		}
		
		BeanUtils.copyProperties(entity, to);
		
		to.setAluno(alunoTOBuilder.buildTO(entity.getAluno()));
		
		return to;
	}


	public SituacaoExAluno build(SituacaoExAlunoTO to) {
		SituacaoExAluno entity = new SituacaoExAluno();

		BeanUtils.copyProperties(to, entity);
		
		if(Objects.nonNull(to.getAluno()) && Objects.nonNull(to.getAluno().getId())) {
			entity.setAluno(getAlunoCmd.getById(to.getAluno().getId()));
		}
		
		entity.setUsuarioAlteracao(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());

		return entity;
	}


	public ComboSituacaoExAlunoTO buildComboTO(ComboSituacaoExAlunoDTO p) {
		ComboSituacaoExAlunoTO retorno = new ComboSituacaoExAlunoTO();
		
		if(Objects.isNull(p)) {
			return retorno;
		}
		
		BeanUtils.copyProperties(p,retorno);
		
		return retorno;
	}

	
	public List<ComboSituacaoExAlunoTO> buildAllDTO(List<ComboSituacaoExAlunoDTO> dtos) {
		return dtos.stream().map(dto -> buildComboTO(dto)).collect(Collectors.toList());
	}

}

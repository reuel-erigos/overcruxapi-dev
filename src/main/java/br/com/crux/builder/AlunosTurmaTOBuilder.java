package br.com.crux.builder;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.cmd.GetAtividadesAlunoCmd;
import br.com.crux.entity.AlunosTurma;
import br.com.crux.to.AlunosTurmaTO;

@Component
public class AlunosTurmaTOBuilder {

	@Autowired private AlunoTOBuilder alunoBuilder;
	@Autowired private TurmasTOBuilder turmaBuilder;
	@Autowired private GetAtividadesAlunoCmd getAtividadesAlunoCmd;

	public AlunosTurma build(AlunosTurmaTO p) {
		AlunosTurma retorno = new AlunosTurma();

		retorno.setId(p.getId());
		retorno.setDataAlteracao(p.getDataAlteracao());
		retorno.setDataCadastro(p.getDataCadastro());
		retorno.setDataDesvinculacao(p.getDataDesvinculacao());
		retorno.setDataInicio(p.getDataInicio());
		retorno.setDescricaoDesligamento(p.getDescricaoDesligamento());
		retorno.setUsuarioAlteracao(p.getUsuarioAlteracao());
		
		if(Objects.nonNull(p.getTurma()) && Objects.nonNull(p.getTurma().getId())) {
			retorno.setTurma(turmaBuilder.build(p.getTurma()));
		}
		
		if(Objects.nonNull(p.getAluno()) && Objects.nonNull(p.getAluno().getId())) {
			retorno.setAluno(alunoBuilder.build(p.getAluno()));
		}
		
		return retorno;
	}

	public AlunosTurmaTO buildTO(AlunosTurma p) {
		AlunosTurmaTO retorno = new AlunosTurmaTO();
		
		if(Objects.isNull(p)) {
			return retorno;
		}
		
		retorno.setId(p.getId());
		retorno.setDataAlteracao(p.getDataAlteracao());
		retorno.setDataCadastro(p.getDataCadastro());
		retorno.setDataDesvinculacao(p.getDataDesvinculacao());
		retorno.setDataInicio(p.getDataInicio());
		retorno.setDescricaoDesligamento(p.getDescricaoDesligamento());
		retorno.setUsuarioAlteracao(p.getUsuarioAlteracao());
		
		if(Objects.nonNull(p.getTurma()) && Objects.nonNull(p.getTurma().getId())) {
			retorno.setTurma(turmaBuilder.buildTO(p.getTurma()));
		}
		
		if(Objects.nonNull(p.getAluno()) && Objects.nonNull(p.getAluno().getId())) {
			retorno.setAluno(alunoBuilder.buildTO(p.getAluno()));
		}

		retorno.setOficinas(getAtividadesAlunoCmd.getAllFilter(p.getTurma().getId(), null, null));
		
		
		return retorno;
	}

	public List<AlunosTurmaTO> buildAll(List<AlunosTurma> dtos) {
		return dtos.stream().map(dto -> buildTO(dto)).collect(Collectors.toList());
	}

}

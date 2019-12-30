package br.com.crux.builder;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.cmd.GetAtividadeCmd;
import br.com.crux.cmd.GetColaboradoresTurmaCmd;
import br.com.crux.cmd.GetNiveisTurmasCmd;
import br.com.crux.cmd.GetProgramaCmd;
import br.com.crux.cmd.GetProjetoCmd;
import br.com.crux.cmd.GetUnidadeCmd;
import br.com.crux.entity.NiveisTurmas;
import br.com.crux.entity.Programa;
import br.com.crux.entity.Projeto;
import br.com.crux.entity.Turmas;
import br.com.crux.entity.Unidade;
import br.com.crux.to.TurmasTO;

@Component
public class TurmasTOBuilder {

	@Autowired private ProgramaTOBuilder programaTOBuilder;
	@Autowired private ProjetoTOBuilder projetoTOBuilder;
	@Autowired private UnidadeTOBuilder unidadeTOBuilder;
	@Autowired private NiveisTurmasTOBuilder niveisTurmasTOBuilder;
	
	@Autowired private GetProgramaCmd getProgramaCmd;
	@Autowired private GetProjetoCmd getProjetoCmd;
	@Autowired private GetNiveisTurmasCmd getNiveisTurmasCmd;
	@Autowired private GetUnidadeCmd getUnidadeCmd;
	
	@Autowired private GetColaboradoresTurmaCmd colaboradoresTurmaCmd;
	@Autowired private GetAtividadeCmd getAtividadeCmd;
	

	public Turmas build(TurmasTO param) {
		Turmas retorno = new Turmas();

		retorno.setId(param.getId());
		retorno.setDescricao(param.getDescricao());
		retorno.setDataPrevisaoInicio(param.getDataPrevisaoInicio());
		retorno.setDataPrevisaoTermino(param.getDataPrevisaoTermino());
		retorno.setDataInicioTurma(param.getDataInicioTurma());
		retorno.setDataFimTurma(param.getDataFimTurma());
		retorno.setHoraInicio(param.getHoraInicio());	
		retorno.setHoraFim(param.getHoraFim());
		retorno.setNumeroMaximoParticipantes(param.getNumeroMaximoParticipantes());	
		retorno.setNumeroCargaHoraria(param.getNumeroCargaHoraria());	
		retorno.setObservacao(param.getObservacao());

		Optional.ofNullable(param.getPrograma()).ifPresent(p -> {
			if (Objects.nonNull(p.getId())) {
				Programa programa = getProgramaCmd.getById(p.getId());
				retorno.setPrograma(programa);
			}
		});

		Optional.ofNullable(param.getProjeto()).ifPresent(p -> {
			if (Objects.nonNull(p.getId())) {
				Projeto projeto = getProjetoCmd.getById(p.getId());
				retorno.setProjeto(projeto );
			}
		});
		
		Optional.ofNullable(param.getNiveisTurma()).ifPresent(p -> {
			if (Objects.nonNull(p.getId())) {
				NiveisTurmas nivelTurma = getNiveisTurmasCmd.getById(p.getId());
				retorno.setNiveisTurma(nivelTurma);
			}
		});
		
		Optional.ofNullable(param.getUnidade()).ifPresent(p -> {
			if (Objects.nonNull(p.getIdUnidade())) {
				Unidade unidade = getUnidadeCmd.getById(p.getIdUnidade());
				retorno.setUnidade(unidade);
			}
		});

		//M = MATUTINO, V = VESPERTINO, N = NOTURNO, O = OUTRO
		retorno.setTurno(param.getTurno());
		retorno.setUsuarioAlteracao(param.getUsuarioAlteracao());
		
		return retorno;
	}

	public TurmasTO buildTO(Turmas param) {
		TurmasTO retorno = new TurmasTO();

		if (Objects.isNull(param)) {
			return retorno;
		}


		retorno.setId(param.getId());
		retorno.setDescricao(param.getDescricao());
		retorno.setDataPrevisaoInicio(param.getDataPrevisaoInicio());
		retorno.setDataPrevisaoTermino(param.getDataPrevisaoTermino());
		retorno.setDataInicioTurma(param.getDataInicioTurma());
		retorno.setDataFimTurma(param.getDataFimTurma());
		retorno.setHoraInicio(param.getHoraInicio());	
		retorno.setHoraFim(param.getHoraFim());
		retorno.setNumeroMaximoParticipantes(param.getNumeroMaximoParticipantes());	
		retorno.setNumeroCargaHoraria(param.getNumeroCargaHoraria());	
		retorno.setObservacao(param.getObservacao());

		Optional.ofNullable(param.getPrograma()).ifPresent(p -> {
			retorno.setPrograma(programaTOBuilder.buildTO(p));
		});

		Optional.ofNullable(param.getProjeto()).ifPresent(p -> {
			retorno.setProjeto(projetoTOBuilder.buildTO(p) );
		});
		
		Optional.ofNullable(param.getNiveisTurma()).ifPresent(p -> {
			retorno.setNiveisTurma(niveisTurmasTOBuilder.buildTO(p));
		});
		
		Optional.ofNullable(param.getUnidade()).ifPresent(p -> {
			retorno.setUnidade(unidadeTOBuilder.buildTO(p));
		});

		//M = MATUTINO, V = VESPERTINO, N = NOTURNO, O = OUTRO
		retorno.setTurno(param.getTurno());
		retorno.setUsuarioAlteracao(param.getUsuarioAlteracao());
		
		retorno.setColaboradores(colaboradoresTurmaCmd.getColaboradoresProjetoTOByTurma(param.getId()));
		retorno.setOficinas(getAtividadeCmd.getTOByIdTurma(param.getId()));

		return retorno;
	}

	public List<TurmasTO> buildAll(List<Turmas> dtos) {
		return dtos.stream().map(this::buildTO).collect(Collectors.toList());
	}

}

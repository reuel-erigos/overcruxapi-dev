package br.com.crux.builder;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.cmd.GetColaboradoresProgramaCmd;
import br.com.crux.cmd.GetComposicaoRhProgramaCmd;
import br.com.crux.cmd.GetContasCentrosCustoCmd;
import br.com.crux.cmd.GetMateriaisProgramaCmd;
import br.com.crux.cmd.GetObjetivoCmd;
import br.com.crux.cmd.GetParceriasProgramaCmd;
import br.com.crux.cmd.GetProgramaUnidadeCmd;
import br.com.crux.dao.dto.ComboProgramaDTO;
import br.com.crux.entity.Objetivo;
import br.com.crux.entity.Programa;
import br.com.crux.to.ComboProgramaTO;
import br.com.crux.to.ProgramaTO;

@Component
public class ProgramaTOBuilder {

	@Autowired private ObjetivoTOBuilder objetivoTOBuilder;
	@Autowired private GetObjetivoCmd getObjetivoCmd;
	@Autowired private GetProgramaUnidadeCmd getProgramaUnidadeCmd;
	@Autowired private GetColaboradoresProgramaCmd getColaboradoresProjetoCmd;
	@Autowired private GetParceriasProgramaCmd getParceriasProgramaCmd;
	@Autowired private GetComposicaoRhProgramaCmd getComposicaoRhProgramaCmd;
	@Autowired private GetMateriaisProgramaCmd getMateriaisProgramaCmd;
	@Autowired private GetContasCentrosCustoCmd getContasCentrosCustoCmd;

	
	public Programa build(ProgramaTO param) {
		Programa retorno = new Programa();
		
		BeanUtils.copyProperties(param, retorno);

		if (Objects.nonNull(param.getObjetivo()) && Objects.nonNull(param.getObjetivo().getIdObjetivo())) {
			Objetivo obj = getObjetivoCmd.getById(param.getObjetivo().getIdObjetivo());
			retorno.setObjetivo(obj);
		}

		return retorno;
	}

	public ProgramaTO buildTO(Programa param) {
		ProgramaTO retorno = new ProgramaTO();

		if (Objects.isNull(param)) {
			return retorno;
		}
		
		BeanUtils.copyProperties(param, retorno);
		retorno.setObjetivo(objetivoTOBuilder.buildTO(param.getObjetivo()));
		
		return retorno;
	}

	public ProgramaTO buildTOComDependencias(Programa param) {
		ProgramaTO retorno = new ProgramaTO();

		if (Objects.isNull(param)) {
			return retorno;
		}
		
		BeanUtils.copyProperties(param, retorno);

		retorno.setObjetivo(objetivoTOBuilder.buildTO(param.getObjetivo()));
		retorno.setUnidades(getProgramaUnidadeCmd.getUnidadesTOByIdPrograma(param.getId()));
		retorno.setColaboradoresPrograma((getColaboradoresProjetoCmd.getColaboradoresProgramaTOByPrograma(param)));
		retorno.setParceriasPrograma(getParceriasProgramaCmd.getParceriasProgramaTOByPrograma(param));
		retorno.setComposicaoRhPrograma(getComposicaoRhProgramaCmd.getComposicaoRhProgramaByPrograma(param));
		retorno.setMateriaisPrograma(getMateriaisProgramaCmd.getMateriaisProgramaByPrograma(param));
		retorno.setContasCentrosCusto(getContasCentrosCustoCmd.getTOPorPrograma(param));
		
		return retorno;
	}

	public List<ProgramaTO> buildAll(List<Programa> dtos) {
		return dtos.stream()
				.map(this::buildTO)
				.collect(Collectors.toList());
	}

	public ProgramaTO buildTOEnxuto(Programa programa) {
		if (Objects.isNull(programa)) {return null;}
		ProgramaTO to = new ProgramaTO();
		to.setId(programa.getId());
		to.setNome(programa.getNome());
		return to;
	}

	public List<ComboProgramaTO> buildAllCombo(List<ComboProgramaDTO> dtos) {
		return dtos.stream()
				.map(this::buildTOCombo)
				.collect(Collectors.toList());
	}

	public ComboProgramaTO buildTOCombo(ComboProgramaDTO dto) {
		ComboProgramaTO to = new ComboProgramaTO();
		if (Objects.isNull(dto)) {return to;}
		to.setId(dto.getId());
		to.setNome(dto.getNome());
		return to;
	}

}

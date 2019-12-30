package br.com.crux.builder;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.cmd.GetColaboradoresProgramaCmd;
import br.com.crux.cmd.GetComposicaoRhProgramaCmd;
import br.com.crux.cmd.GetMateriaisProgramaCmd;
import br.com.crux.cmd.GetObjetivoCmd;
import br.com.crux.cmd.GetParceriasProgramaCmd;
import br.com.crux.cmd.GetProgramaUnidadeCmd;
import br.com.crux.entity.Objetivo;
import br.com.crux.entity.Programa;
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

	public Programa build(ProgramaTO param) {
		Programa retorno = new Programa();

		retorno.setId(param.getId());
		retorno.setNome(param.getNome());
		retorno.setDescricao(param.getDescricao());

		retorno.setDataInicio(param.getDataInicio());
		retorno.setDataFim(param.getDataFim());
		retorno.setUsuarioAlteracao(param.getUsuarioAlteracao());
		retorno.setPublicoAlvo(param.getPublicoAlvo());
		retorno.setJustificativa(param.getJustificativa());
		retorno.setObjetivoGeral(param.getObjetivoGeral());

		retorno.setIdCoordenador(param.getIdCoordenador());
		retorno.setFaixaEtariaInicio(param.getFaixaEtariaInicio());
		retorno.setFaixaEtariaFim(param.getFaixaEtariaFim());

		Optional.ofNullable(param.getObjetivo()).ifPresent(o -> {
			Objetivo obj = getObjetivoCmd.getById(o.getIdObjetivo());
			retorno.setObjetivo(obj);
		});

		return retorno;
	}

	public ProgramaTO buildTO(Programa param) {
		ProgramaTO retorno = new ProgramaTO();

		if (Objects.isNull(param)) {
			return retorno;
		}

		retorno.setId(param.getId());
		retorno.setNome(param.getNome());
		retorno.setIdCoordenador(param.getIdCoordenador());
		retorno.setDescricao(param.getDescricao());
		retorno.setFaixaEtariaInicio(param.getFaixaEtariaInicio());
		retorno.setFaixaEtariaFim(param.getFaixaEtariaFim());
		retorno.setDataInicio(param.getDataInicio());
		retorno.setDataFim(param.getDataFim());
		retorno.setUsuarioAlteracao(param.getUsuarioAlteracao());
		retorno.setObjetivo(objetivoTOBuilder.buildTO(param.getObjetivo()));

		retorno.setPublicoAlvo(param.getPublicoAlvo());
		retorno.setJustificativa(param.getJustificativa());
		retorno.setObjetivoGeral(param.getObjetivoGeral());


		return retorno;
	}
	
	public ProgramaTO buildTOComDependencias(Programa param) {
		ProgramaTO retorno = new ProgramaTO();

		if (Objects.isNull(param)) {
			return retorno;
		}

		retorno.setId(param.getId());
		retorno.setNome(param.getNome());
		retorno.setIdCoordenador(param.getIdCoordenador());
		retorno.setDescricao(param.getDescricao());
		retorno.setFaixaEtariaInicio(param.getFaixaEtariaInicio());
		retorno.setFaixaEtariaFim(param.getFaixaEtariaFim());
		retorno.setDataInicio(param.getDataInicio());
		retorno.setDataFim(param.getDataFim());
		retorno.setUsuarioAlteracao(param.getUsuarioAlteracao());
		retorno.setObjetivo(objetivoTOBuilder.buildTO(param.getObjetivo()));

		retorno.setPublicoAlvo(param.getPublicoAlvo());
		retorno.setJustificativa(param.getJustificativa());
		retorno.setObjetivoGeral(param.getObjetivoGeral());

		retorno.setUnidades(getProgramaUnidadeCmd.getUnidadesTOByIdPrograma(param.getId()));
		retorno.setColaboradoresPrograma((getColaboradoresProjetoCmd.getColaboradoresProgramaTOByPrograma(param)));
		retorno.setParceriasPrograma(getParceriasProgramaCmd.getColaboradoresProgramaTOByPrograma(param));
		retorno.setComposicaoRhPrograma(getComposicaoRhProgramaCmd.getComposicaoRhProgramaByPrograma(param));
		retorno.setMateriaisPrograma(getMateriaisProgramaCmd.getMateriaisProgramaByPrograma(param));

		return retorno;
	}


	public List<ProgramaTO> buildAll(List<Programa> dtos) {
		return dtos.stream().map(this::buildTO).collect(Collectors.toList());
	}

}

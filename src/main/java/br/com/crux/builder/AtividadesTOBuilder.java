package br.com.crux.builder;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.cmd.GetColaboradoresAtividadeCmd;
import br.com.crux.cmd.GetMateriaisAtividadeCmd;
import br.com.crux.cmd.GetPlanosAcaoCmd;
import br.com.crux.cmd.GetProgramaCmd;
import br.com.crux.cmd.GetProjetoCmd;
import br.com.crux.cmd.GetUnidadeCmd;
import br.com.crux.entity.Atividades;
import br.com.crux.entity.PlanosAcao;
import br.com.crux.entity.Programa;
import br.com.crux.entity.Projeto;
import br.com.crux.entity.Unidade;
import br.com.crux.infra.util.Java8DateUtil;
import br.com.crux.to.AtividadesTO;
import br.com.crux.to.ColaboradoresAtividadeTO;
import br.com.crux.to.MateriaisAtividadeTO;

@Component
public class AtividadesTOBuilder {

	@Autowired private UnidadeTOBuilder unidadeBuilder;
	@Autowired private ProjetoTOBuilder projetoBuilder;
	@Autowired private ProgramaTOBuilder programaTOBuilder;
	@Autowired private PlanosAcaoTOBuilder planosAcaoBuilder;
	@Autowired private GetUnidadeCmd getUnidadeCmd;
	@Autowired private GetProjetoCmd getProjetoCmd;
	@Autowired private GetProgramaCmd getProgramaCmd;
	@Autowired private GetPlanosAcaoCmd getPlanosAcaoCmd;
	@Autowired private GetColaboradoresAtividadeCmd getColaboradoresAtividadeCmd;
	@Autowired private GetMateriaisAtividadeCmd getMateriaisAtividadeCmd;

	public Atividades build(AtividadesTO p) {
		Atividades retorno = new Atividades();

		retorno.setId(p.getId());
		retorno.setDescricao(p.getDescricao());
		retorno.setDescricaoLocalExecucao(p.getDescricaoLocalExecucao());
		retorno.setDataFim(p.getDataFim());
		retorno.setDataInicio(p.getDataInicio());
		retorno.setDataPrevisaoInicio(p.getDataPrevisaoInicio());
		retorno.setDataPrevisaoTermino(p.getDataPrevisaoTermino());

		Optional.ofNullable(p.getHoraFim()).ifPresent(hora -> {
			retorno.setHoraFim(Java8DateUtil.horaStringToLong(p.getHoraFim()));
		});

		Optional.ofNullable(p.getHoraInicio()).ifPresent(hora -> {
			retorno.setHoraInicio(Java8DateUtil.horaStringToLong(p.getHoraInicio()));
		});

		retorno.setNumeroAulas(p.getNumeroAulas());
		retorno.setCargaHoraria(p.getCargaHoraria());
		retorno.setMaximoParticipantes(p.getMaximoParticipantes());
		retorno.setPeriodoAtividade(p.getPeriodoAtividade());
		retorno.setHorarioFixo(p.getHorarioFixo());
		retorno.setLocalExecucao(p.getLocalExecucao());

		retorno.setSegunda(Objects.isNull(p.getSegunda()) || !p.getSegunda() ? "N" : "S");
		retorno.setTerca(Objects.isNull(p.getTerca()) || !p.getTerca() ? "N" : "S");
		retorno.setQuarta(Objects.isNull(p.getQuarta()) || !p.getQuarta() ? "N" : "S");
		retorno.setQuinta(Objects.isNull(p.getQuinta()) || !p.getQuinta() ? "N" : "S");
		retorno.setSexta(Objects.isNull(p.getSexta()) || !p.getSexta() ? "N" : "S");
		retorno.setSabado(Objects.isNull(p.getSabado()) || !p.getSabado() ? "N" : "S");
		retorno.setDomingo(Objects.isNull(p.getDomingo()) || !p.getDomingo() ? "N" : "S");

		retorno.setObservacoes(p.getObservacoes());
		retorno.setValorCustoAtividade(p.getValorCustoAtividade());

		Optional.ofNullable(p.getUnidade()).ifPresent(u -> {
			if (Objects.nonNull(u.getIdUnidade())) {
				Unidade unidade = getUnidadeCmd.getById(u.getIdUnidade());
				retorno.setUnidade(unidade);
			}
		});

		Optional.ofNullable(p.getProjeto()).ifPresent(pj -> {
			if (Objects.nonNull(pj.getId())) {
				Projeto projeto = getProjetoCmd.getById(pj.getId());
				retorno.setProjeto(projeto);
			}
		});
		
		Optional.ofNullable(p.getPrograma()).ifPresent(pj -> {
			if (Objects.nonNull(pj.getId())) {
				Programa programa = getProgramaCmd.getById(pj.getId());
				retorno.setPrograma(programa);
			}

		});
		
		retorno.setIdTurma(p.getIdTurma());

		Optional.ofNullable(p.getPlanosAcao()).ifPresent(pa -> {
			if (Objects.nonNull(pa.getId())) {
				PlanosAcao planos = getPlanosAcaoCmd.getById(pa.getId());
				retorno.setPlanosAcao(planos);
			}

		});

		retorno.setUsuarioAlteracao(p.getUsuarioAlteracao());

		return retorno;
	}

	public AtividadesTO buildTO(Atividades p) {
		AtividadesTO retorno = new AtividadesTO();

		if (Objects.isNull(p)) {
			return retorno;
		}

		retorno.setId(p.getId());
		retorno.setDescricao(p.getDescricao());
		retorno.setDescricaoLocalExecucao(p.getDescricaoLocalExecucao());
		retorno.setDataFim(p.getDataFim());
		retorno.setDataInicio(p.getDataInicio());
		retorno.setDataPrevisaoInicio(p.getDataPrevisaoInicio());
		retorno.setDataPrevisaoTermino(p.getDataPrevisaoTermino());

		Optional.ofNullable(p.getHoraFim()).ifPresent(hora -> {

			retorno.setHoraFim(formatarHora(hora));
		});

		Optional.ofNullable(p.getHoraInicio()).ifPresent(hora -> {
			retorno.setHoraInicio(formatarHora(hora));
		});

		retorno.setNumeroAulas(p.getNumeroAulas());
		retorno.setCargaHoraria(p.getCargaHoraria());
		retorno.setMaximoParticipantes(p.getMaximoParticipantes());
		retorno.setPeriodoAtividade(p.getPeriodoAtividade());
		retorno.setHorarioFixo(p.getHorarioFixo());
		retorno.setLocalExecucao(p.getLocalExecucao());

		retorno.setSegunda(Objects.nonNull(p.getSegunda()) && p.getSegunda().equalsIgnoreCase("S") ? true : false);
		retorno.setTerca(Objects.nonNull(p.getTerca()) && p.getTerca().equalsIgnoreCase("S") ? true : false);
		retorno.setQuarta(Objects.nonNull(p.getQuarta()) && p.getQuarta().equalsIgnoreCase("S") ? true : false);
		retorno.setQuinta(Objects.nonNull(p.getQuinta()) && p.getQuinta().equalsIgnoreCase("S") ? true : false);
		retorno.setSexta(Objects.nonNull(p.getSexta()) && p.getSexta().equalsIgnoreCase("S") ? true : false);
		retorno.setSabado(Objects.nonNull(p.getSabado()) && p.getSabado().equalsIgnoreCase("S") ? true : false);
		retorno.setDomingo(Objects.nonNull(p.getDomingo()) && p.getDomingo().equalsIgnoreCase("S") ? true : false);

		retorno.setObservacoes(p.getObservacoes());
		retorno.setValorCustoAtividade(p.getValorCustoAtividade());
		retorno.setUnidade(unidadeBuilder.buildTO(p.getUnidade()));
		retorno.setProjeto(projetoBuilder.buildTO(p.getProjeto()));
		retorno.setPrograma(programaTOBuilder.buildTO(p.getPrograma()));
		retorno.setIdTurma(p.getIdTurma());
		
		retorno.setPlanosAcao(planosAcaoBuilder.buildTO(p.getPlanosAcao()));

		if(Objects.nonNull(p.getId())) {
			List<ColaboradoresAtividadeTO> colaboradores = getColaboradoresAtividadeCmd.getPorAtividade(p.getId());
			retorno.setColaboradoresAtividade(colaboradores);
		}
		
		if(Objects.nonNull(p.getId())) {
			List<MateriaisAtividadeTO> materiaisAtividade = getMateriaisAtividadeCmd.getAllTOPorAtividade(p.getId());
			retorno.setMateriaisAtividade(materiaisAtividade);
		}

		retorno.setUsuarioAlteracao(p.getUsuarioAlteracao());

		return retorno;
	}

	private String formatarHora(Long hora) {
		String horaString = String.valueOf(hora);
		if (horaString.length() == 3) {
			horaString = "0" + horaString;
		}
		return horaString.substring(0, 2) + ":" + horaString.substring(2, 4);
	}

	public List<AtividadesTO> buildAll(List<Atividades> dtos) {
		return dtos.stream().map(dto -> buildTO(dto)).collect(Collectors.toList());
	}
	
	

}

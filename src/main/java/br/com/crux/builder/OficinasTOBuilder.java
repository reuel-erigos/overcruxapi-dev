package br.com.crux.builder;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.cmd.GetColaboradoresAtividadeCmd;
import br.com.crux.cmd.GetMateriaisAtividadeCmd;
import br.com.crux.cmd.GetProgramaCmd;
import br.com.crux.cmd.GetProjetoCmd;
import br.com.crux.cmd.GetTiposAtividadesCmd;
import br.com.crux.cmd.GetUnidadeCmd;
import br.com.crux.entity.Oficinas;
import br.com.crux.entity.Programa;
import br.com.crux.entity.Projeto;
import br.com.crux.entity.Unidade;
import br.com.crux.infra.util.Java8DateUtil;
import br.com.crux.to.ColaboradoresAtividadeTO;
import br.com.crux.to.MateriaisAtividadeTO;
import br.com.crux.to.OficinasTO;

@Component
public class OficinasTOBuilder {

	@Autowired
	private UnidadeTOBuilder unidadeBuilder;
	@Autowired
	private ProjetoTOBuilder projetoBuilder;
	@Autowired
	private ProgramaTOBuilder programaTOBuilder;
	@Autowired
	private GetUnidadeCmd getUnidadeCmd;
	@Autowired
	private GetProjetoCmd getProjetoCmd;
	@Autowired
	private GetProgramaCmd getProgramaCmd;
	@Autowired
	private GetColaboradoresAtividadeCmd getColaboradoresAtividadeCmd;
	@Autowired
	private GetMateriaisAtividadeCmd getMateriaisAtividadeCmd;
	@Autowired
	private GetTiposAtividadesCmd getTiposAtividadesCmd;

	public Oficinas build(OficinasTO p) {
		Oficinas retorno = new Oficinas();

		BeanUtils.copyProperties(p, retorno);

		Optional.ofNullable(p.getHoraFim()).ifPresent(hora -> {
			retorno.setHoraFim(Java8DateUtil.horaStringToLong(p.getHoraFim()));
		});

		Optional.ofNullable(p.getHoraInicio()).ifPresent(hora -> {
			retorno.setHoraInicio(
					Java8DateUtil.horaStringToLong(p.getHoraInicio()));
		});

		retorno.setSegunda(
				Objects.isNull(p.getSegunda()) || !p.getSegunda() ? "N" : "S");
		retorno.setTerca(
				Objects.isNull(p.getTerca()) || !p.getTerca() ? "N" : "S");
		retorno.setQuarta(
				Objects.isNull(p.getQuarta()) || !p.getQuarta() ? "N" : "S");
		retorno.setQuinta(
				Objects.isNull(p.getQuinta()) || !p.getQuinta() ? "N" : "S");
		retorno.setSexta(
				Objects.isNull(p.getSexta()) || !p.getSexta() ? "N" : "S");
		retorno.setSabado(
				Objects.isNull(p.getSabado()) || !p.getSabado() ? "N" : "S");
		retorno.setDomingo(
				Objects.isNull(p.getDomingo()) || !p.getDomingo() ? "N" : "S");


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
		
		if(Objects.nonNull(p.getTiposAtividades())) {
			retorno.setTipoAtividade(getTiposAtividadesCmd.getById(p.getTiposAtividades()));
		}

		return retorno;
	}

	public OficinasTO buildTO(Oficinas p) {
		OficinasTO retorno = new OficinasTO();

		if (Objects.isNull(p)) {
			return retorno;
		}

		BeanUtils.copyProperties(p, retorno);
		

		Optional.ofNullable(p.getHoraFim()).ifPresent(hora -> {

			retorno.setHoraFim(formatarHora(hora));
		});

		Optional.ofNullable(p.getHoraInicio()).ifPresent(hora -> {
			retorno.setHoraInicio(formatarHora(hora));
		});

		retorno.setSegunda(Objects.nonNull(p.getSegunda())
				&& p.getSegunda().equalsIgnoreCase("S") ? true : false);
		retorno.setTerca(Objects.nonNull(p.getTerca())
				&& p.getTerca().equalsIgnoreCase("S") ? true : false);
		retorno.setQuarta(Objects.nonNull(p.getQuarta())
				&& p.getQuarta().equalsIgnoreCase("S") ? true : false);
		retorno.setQuinta(Objects.nonNull(p.getQuinta())
				&& p.getQuinta().equalsIgnoreCase("S") ? true : false);
		retorno.setSexta(Objects.nonNull(p.getSexta())
				&& p.getSexta().equalsIgnoreCase("S") ? true : false);
		retorno.setSabado(Objects.nonNull(p.getSabado())
				&& p.getSabado().equalsIgnoreCase("S") ? true : false);
		retorno.setDomingo(Objects.nonNull(p.getDomingo())
				&& p.getDomingo().equalsIgnoreCase("S") ? true : false);

		retorno.setUnidade(unidadeBuilder.buildTO(p.getUnidade()));
		retorno.setProjeto(projetoBuilder.buildTO(p.getProjeto()));
		retorno.setPrograma(programaTOBuilder.buildTO(p.getPrograma()));
		

		if(Objects.nonNull(p.getTipoAtividade())) {
			retorno.setTiposAtividades(p.getTipoAtividade().getId());
		}

		if (Objects.nonNull(p.getId())) {
			List<ColaboradoresAtividadeTO> colaboradores = getColaboradoresAtividadeCmd
					.getPorAtividade(p.getId());
			retorno.setColaboradoresAtividade(colaboradores);
		}

		if (Objects.nonNull(p.getId())) {
			List<MateriaisAtividadeTO> materiaisAtividade = getMateriaisAtividadeCmd
					.getAllTOPorAtividade(p.getId());
			retorno.setMateriaisAtividade(materiaisAtividade);
		}

		return retorno;
	}

	private String formatarHora(Long hora) {
		String horaString = String.valueOf(hora);
		if (horaString.length() == 3) {
			horaString = "0" + horaString;
		}
		return horaString.substring(0, 2) + ":" + horaString.substring(2, 4);
	}

	public List<OficinasTO> buildAll(List<Oficinas> dtos) {
		return dtos.stream().map(dto -> buildTO(dto))
				.collect(Collectors.toList());
	}

	public List<OficinasTO> buildAllCombo(List<Oficinas> dtos) {
		return dtos.stream().map(this::buildComboTO).collect(Collectors.toList());
	}
	
	public OficinasTO buildComboTO(Oficinas param) { 
		OficinasTO retorno = new OficinasTO();

		if (Objects.isNull(param)) {
			return retorno;
		}

		retorno.setId(param.getId());
		retorno.setDescricao(param.getDescricao());
		retorno.setIdTurma(param.getIdTurma());
		return retorno;
	}
}

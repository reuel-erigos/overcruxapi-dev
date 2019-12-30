package br.com.crux.builder;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.cmd.GetEmpresaCmd;
import br.com.crux.cmd.GetMateriaisParceirosProgramaCmd;
import br.com.crux.cmd.GetUsuarioLogadoCmd;
import br.com.crux.entity.Empresa;
import br.com.crux.entity.ParceriasPrograma;
import br.com.crux.entity.Programa;
import br.com.crux.to.MateriaisProgramaTO;
import br.com.crux.to.ParceriasProgramaTO;

@Component
public class ParceriasProgramaTOBuilder {

	@Autowired GetUsuarioLogadoCmd getUsuarioLogadoCmd;
	@Autowired GetEmpresaCmd empresaCmd;
	@Autowired EmpresaTOBuilder empresaTOBuilder;
	@Autowired ProjetoTOBuilder projetoTOBuilder;
	@Autowired GetMateriaisParceirosProgramaCmd getMateriaisParceirosProgramaCmd;

	public ParceriasPrograma build(Programa programa, ParceriasProgramaTO parceriaProgramaTO) {

		ParceriasPrograma parceriasPrograma = new ParceriasPrograma();

		BeanUtils.copyProperties(parceriaProgramaTO, parceriasPrograma, "programa", "empresa");

		parceriasPrograma.setPrograma(programa);

		if (Objects.nonNull(parceriaProgramaTO.getEmpresa()) && Objects.nonNull(parceriaProgramaTO.getEmpresa().getId())) {
			Empresa e = empresaCmd.getById(parceriaProgramaTO.getEmpresa().getId());
			parceriasPrograma.setEmpresa(e);
		}

		parceriasPrograma.setUsuarioAlteracao(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());

		return parceriasPrograma;
	}

	public ParceriasProgramaTO buildTO(ParceriasPrograma parceriasPrograma) {

		ParceriasProgramaTO to = new ParceriasProgramaTO();

		BeanUtils.copyProperties(parceriasPrograma, to, "projeto", "empresa");

		to.setEmpresa(empresaTOBuilder.buildTO(parceriasPrograma.getEmpresa()));

		List<MateriaisProgramaTO> materiaisProjetoByPrograma = getMateriaisParceirosProgramaCmd.getMateriaisProgramaTOByParceriasPrograma(parceriasPrograma);

		to.setMateriaisPrograma(materiaisProjetoByPrograma);

		return to;
	}

	public List<ParceriasProgramaTO> buildAll(List<ParceriasPrograma> lista) {

		return lista.stream().map(this::buildTO).collect(Collectors.toList());
	}

}

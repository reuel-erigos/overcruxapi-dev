package br.com.crux.builder;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.cmd.GetEmpresaCmd;
import br.com.crux.cmd.GetMateriaisParceirosProgramaCmd;
import br.com.crux.cmd.GetParceriasCategoriasCmd;
import br.com.crux.cmd.GetUsuarioLogadoCmd;
import br.com.crux.entity.Empresa;
import br.com.crux.entity.ParceriasPrograma;
import br.com.crux.entity.Programa;
import br.com.crux.to.ParceriasProgramaTO;

@Component
public class ParceriasProgramaTOBuilder {

	@Autowired
	GetUsuarioLogadoCmd getUsuarioLogadoCmd;
	@Autowired
	GetEmpresaCmd empresaCmd;
	@Autowired
	EmpresaTOBuilder empresaTOBuilder;
	@Autowired
	ProjetoTOBuilder projetoTOBuilder;
	@Autowired
	GetMateriaisParceirosProgramaCmd getMateriaisParceirosProgramaCmd;
	@Autowired
	GetParceriasCategoriasCmd getParceriasCategoriasCmd;
	@Autowired
	MateriaisProgramaTOBuilder materiaisProgramaTOBuilder;
	@Autowired
	ParceriasCategoriasTOBuilder parceriasCategoriasTOBuilder;
	@Autowired
	AditivoParceriaProgramaTOBuilder aditivoParceriaProgramaTOBuilder;
	@Autowired
	ContasCentrosCustoTOBuilder contasCentrosCustoTOBuilder;

	public ParceriasPrograma build(Programa programa, ParceriasProgramaTO parceriaProgramaTO) {
		ParceriasPrograma parceriasPrograma = new ParceriasPrograma();

		BeanUtils.copyProperties(parceriaProgramaTO, parceriasPrograma, "programa", "empresa");

		parceriasPrograma.setPrograma(programa);

		if (Objects.nonNull(parceriaProgramaTO.getEmpresa())
				&& Objects.nonNull(parceriaProgramaTO.getEmpresa().getId())) {
			Empresa e = empresaCmd.getById(parceriaProgramaTO.getEmpresa().getId());
			parceriasPrograma.setEmpresa(e);
		}
		parceriasPrograma.setMateriaisProgramas(
				materiaisProgramaTOBuilder.buildAllTO(parceriaProgramaTO.getMateriaisPrograma()));
		parceriasPrograma.setParceriasCategorias(
				parceriasCategoriasTOBuilder.buildAll(parceriaProgramaTO.getParceriasCategorias()));

		parceriasPrograma.setContasCentrosCusto(contasCentrosCustoTOBuilder
				.buildAll(parceriaProgramaTO.getContasCentrosCusto(), parceriasPrograma, null));

		parceriasPrograma.setUsuarioAlteracao(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());

		return parceriasPrograma;
	}

	public ParceriasProgramaTO buildTO(ParceriasPrograma parceriasPrograma) {

		ParceriasProgramaTO to = new ParceriasProgramaTO();

		if (Objects.isNull(parceriasPrograma)) {
			return to;
		}

		BeanUtils.copyProperties(parceriasPrograma, to, "projeto", "empresa");

		to.setEmpresa(empresaTOBuilder.buildTO(parceriasPrograma.getEmpresa()));

		to.setMateriaisPrograma(materiaisProgramaTOBuilder.buildAll(parceriasPrograma.getMateriaisProgramas()));
		to.setParceriasCategorias(parceriasCategoriasTOBuilder.buildAllTO(parceriasPrograma.getParceriasCategorias()));
		to.setAditivosParceriasProgramas(
				aditivoParceriaProgramaTOBuilder.buildTO(parceriasPrograma.getAditivosParceriaPrograma()));
		to.setContasCentrosCusto(contasCentrosCustoTOBuilder.buildAllTO(parceriasPrograma.getContasCentrosCusto()));

		return to;
	}

	public ParceriasProgramaTO buildSemDependencia(ParceriasPrograma p) {

		ParceriasProgramaTO to = new ParceriasProgramaTO();

		if (Objects.isNull(p)) {
			return to;
		}

		to.setDsTipoParceria(p.getDsTipoParceria());
		to.setDtFimParceria(p.getDtFimParceria());
		to.setDtInicioParceria(p.getDtInicioParceria());
		to.setId(p.getId());
		to.setValorParceria(p.getValorParceria());
		to.setEmpresa(empresaTOBuilder.buildTO(p.getEmpresa()));

		return to;
	}

	public ParceriasPrograma buildEntity(ParceriasProgramaTO parceriasPrograma) {
		ParceriasPrograma to = new ParceriasPrograma();

		if (Objects.isNull(parceriasPrograma)) {
			return to;
		}

		BeanUtils.copyProperties(parceriasPrograma, to, "projeto", "empresa");

		to.setEmpresa(empresaTOBuilder.build(parceriasPrograma.getEmpresa()));

		if (Objects.nonNull(parceriasPrograma.getMateriaisPrograma())) {
			to.setMateriaisProgramas(materiaisProgramaTOBuilder.buildAllTO(parceriasPrograma.getMateriaisPrograma()));
		}

		if (Objects.nonNull(parceriasPrograma.getParceriasCategorias())) {
			to.setParceriasCategorias(
					parceriasCategoriasTOBuilder.buildAll(parceriasPrograma.getParceriasCategorias()));
		}

		return to;
	}

	public List<ParceriasProgramaTO> buildAll(List<ParceriasPrograma> lista) {

		return lista.stream().map(this::buildTO).collect(Collectors.toList());
	}

	public ParceriasProgramaTO buildTOCombo(ParceriasPrograma e) {
		ParceriasProgramaTO to = new ParceriasProgramaTO();

		if (Objects.isNull(e)) {
			return to;
		}

		BeanUtils.copyProperties(e, to);

		return to;

	}

}

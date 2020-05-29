package br.com.crux.builder;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.cmd.GetEmpresaCmd;
import br.com.crux.cmd.GetMateriaisParceirosProjetoCmd;
import br.com.crux.cmd.GetParceriasCategoriasCmd;
import br.com.crux.cmd.GetUsuarioLogadoCmd;
import br.com.crux.entity.Empresa;
import br.com.crux.entity.ParceriasProjeto;
import br.com.crux.entity.Projeto;
import br.com.crux.to.MateriaisProjetoTO;
import br.com.crux.to.ParceriasCategoriasTO;
import br.com.crux.to.ParceriasProjetoTO;

@Component
public class ParceriasProjetoTOBuilder {

	@Autowired GetUsuarioLogadoCmd getUsuarioLogadoCmd;
	@Autowired GetEmpresaCmd empresaCmd;
	@Autowired EmpresaTOBuilder empresaTOBuilder;
	@Autowired ProjetoTOBuilder projetoTOBuilder;
	@Autowired GetMateriaisParceirosProjetoCmd getMateriaisParceirosProjetoCmd;
	@Autowired MateriaisProjetoTOBuilder materiaisProjetoTOBuilder;
	@Autowired GetParceriasCategoriasCmd getParceriasCategoriasCmd;
	@Autowired ParceriasCategoriasTOBuilder parceriasCategoriasTOBuilder;

	public ParceriasProjeto buildEntity(Projeto projeto, ParceriasProjetoTO parceriaProjetoTO) {

		ParceriasProjeto parceriasProjeto = new ParceriasProjeto();

		BeanUtils.copyProperties(parceriaProjetoTO, parceriasProjeto, "projeto", "empresa");
		parceriasProjeto.setProjeto(projeto);

		if (Objects.nonNull(parceriaProjetoTO.getEmpresa()) && Objects.nonNull(parceriaProjetoTO.getEmpresa().getId())) {
			Empresa e = empresaCmd.getById(parceriaProjetoTO.getEmpresa().getId());
			parceriasProjeto.setEmpresa(e);
		}

		parceriasProjeto.setUsuarioAlteracao(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());
		
		if(Objects.nonNull(parceriaProjetoTO.getMateriaisProjeto())) {
			parceriasProjeto.setMateriaisProjetos(materiaisProjetoTOBuilder.buildAll(parceriaProjetoTO.getMateriaisProjeto()));
		}
		if(Objects.nonNull(parceriaProjetoTO.getParceriasCategorias())) {
			parceriasProjeto.setParceriasCategorias(parceriasCategoriasTOBuilder.buildAll(parceriaProjetoTO.getParceriasCategorias()));
		}

		return parceriasProjeto;
	}

	public ParceriasProjetoTO buildTO(ParceriasProjeto parceriaProjeto) {
		ParceriasProjetoTO to = new ParceriasProjetoTO();

		if (Objects.isNull(parceriaProjeto)) {
			return to;
		}

		BeanUtils.copyProperties(parceriaProjeto, to, "projeto", "empresa");

		to.setEmpresa(empresaTOBuilder.buildTO(parceriaProjeto.getEmpresa()));

		List<MateriaisProjetoTO> materiaisProjetoByProjeto = getMateriaisParceirosProjetoCmd.getMateriaisProjetoTOByParceriasProjeto(parceriaProjeto);
		to.setMateriaisProjeto(materiaisProjetoByProjeto);
		
		List<ParceriasCategoriasTO> listaParceriasCategarias = getParceriasCategoriasCmd.getParceriasCategoriasTOByParceriasProjeto(parceriaProjeto);
        to.setParceriasCategorias(listaParceriasCategarias);		

		return to;
	}

	public ParceriasProjeto buildTO(ParceriasProjetoTO parceriaProjeto) {
		ParceriasProjeto to = new ParceriasProjeto();

		if (Objects.isNull(parceriaProjeto)) {
			return to;
		}

		BeanUtils.copyProperties(parceriaProjeto, to, "projeto", "empresa");

		to.setEmpresa(empresaTOBuilder.build(parceriaProjeto.getEmpresa()));
        
		if(Objects.nonNull(parceriaProjeto.getMateriaisProjeto())) {
			to.setMateriaisProjetos(materiaisProjetoTOBuilder.buildAll(parceriaProjeto.getMateriaisProjeto()));
		}
		if(Objects.nonNull(parceriaProjeto.getParceriasCategorias())) {
			to.setParceriasCategorias(parceriasCategoriasTOBuilder.buildAll(parceriaProjeto.getParceriasCategorias()));
		}
		
		return to;
	}

	
	public List<ParceriasProjetoTO> buildAll(List<ParceriasProjeto> lista) {
		return lista.stream()
				.map(this::buildTO)
				.collect(Collectors.toList());
	}

	public ParceriasProjetoTO buildTOCombo(ParceriasProjeto e) {
		ParceriasProjetoTO to = new ParceriasProjetoTO();

		if (Objects.isNull(e)) {
			return to;
		}

		BeanUtils.copyProperties(e, to);

		return to;

	}

}

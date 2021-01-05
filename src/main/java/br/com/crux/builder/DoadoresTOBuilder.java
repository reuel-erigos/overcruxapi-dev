package br.com.crux.builder;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.cmd.GetEmpresaCmd;
import br.com.crux.cmd.GetPessoaFisicaCmd;
import br.com.crux.cmd.GetTiposDoadoresCmd;
import br.com.crux.cmd.GetUnidadeLogadaCmd;
import br.com.crux.cmd.GetUsuarioLogadoCmd;
import br.com.crux.dao.dto.ComboDoadoresDTO;
import br.com.crux.entity.Doadores;
import br.com.crux.to.DoadoresTO;

@Component
public class DoadoresTOBuilder {

	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;
	
	@Autowired private GetEmpresaCmd getEmpresaCmd;
	@Autowired private EmpresaTOBuilder empresaTOBuilder;
	@Autowired private GetTiposDoadoresCmd getTiposDoadoresCmd;
	@Autowired private TiposDoadoresTOBuilder tiposDoadoresTOBuilder;
	@Autowired private GetPessoaFisicaCmd getPessoaFisicaCmd;
	@Autowired private PessoaFisicaTOBuilder pessoaFisicaTOBuilder;
	@Autowired private GetUnidadeLogadaCmd getUnidadeLogadaCmd;

	public DoadoresTO buildTO(Doadores entity) {
		DoadoresTO to = new DoadoresTO();
		if (Objects.isNull(entity)) {
			return to;
		}
		
		BeanUtils.copyProperties(entity, to);
		
		to.setEmpresa(empresaTOBuilder.buildTOCombo(entity.getEmpresa()));
		to.setTipoDoador(tiposDoadoresTOBuilder.buildTO(entity.getTipoDoador()));
		to.setPessoasFisica(pessoaFisicaTOBuilder.buildParaCombo(entity.getPessoasFisica()));
		
		return to;
	}

	public Doadores build(DoadoresTO to) {
		Doadores entity = new Doadores();

		BeanUtils.copyProperties(to, entity);
		
		if (Objects.nonNull(to.getEmpresa()) && Objects.nonNull(to.getEmpresa().getId())) {
			entity.setEmpresa(getEmpresaCmd.getById(to.getEmpresa().getId()));
		}

		if (Objects.nonNull(to.getTipoDoador()) && Objects.nonNull(to.getTipoDoador().getId())) {
			entity.setTipoDoador(getTiposDoadoresCmd.getById(to.getTipoDoador().getId()));
		}
		
		if (Objects.nonNull(to.getPessoasFisica()) && Objects.nonNull(to.getPessoasFisica().getId())) {
			entity.setPessoasFisica(getPessoaFisicaCmd.getById(to.getPessoasFisica().getId()));
		}
		
		Long idInstituicao = getUnidadeLogadaCmd.getUnidadeTO().getInstituicao().getId();
		entity.setIdInstituicao(idInstituicao);
		entity.setUsuarioAlteracao(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());

		return entity;
	}
	
	public List<DoadoresTO> buildAll(List<Doadores> lista) {
		return lista.stream()
				.map(this::buildTO)
				.collect(Collectors.toList());
	}

	public ComboDoadoresDTO buildComboTO(ComboDoadoresDTO p) {
		ComboDoadoresDTO retorno = new ComboDoadoresDTO();
		
		if(Objects.isNull(p)) {
			return retorno;
		}
		
		BeanUtils.copyProperties(p, retorno);
		
		return retorno;
	}

	
	public List<ComboDoadoresDTO> buildAllDTO(List<ComboDoadoresDTO> dtos) {
		return dtos.stream().map(dto -> buildComboTO(dto)).collect(Collectors.toList());
	}


}

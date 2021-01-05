package br.com.crux.builder;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.dao.repository.CboRepository;
import br.com.crux.dao.repository.InstituicaoRepository;
import br.com.crux.entity.Cargo;
import br.com.crux.entity.Cbo;
import br.com.crux.entity.Instituicao;
import br.com.crux.enums.TipoCargo;
import br.com.crux.to.CargoTO;


@Component
public class CargosTOBuilder {
	
	@Autowired private CboTOBuilder cboTOBuilder;
	@Autowired private CboRepository repository;
	@Autowired private InstituicaoRepository instituicaoRepository;
	@Autowired private InstituicaoTOBuilder instituicaoTOBuilder;
	
	public Cargo build(CargoTO param) {
		Cargo retorno = new Cargo();
		
		retorno.setId(param.getId());
		retorno.setCodigo(param.getCodigo());
		retorno.setNome(param.getNome());
		retorno.setTipoCargo(TipoCargo.getPorTipo(param.getTipoCargo()));
		retorno.setUsuarioAlteracao(param.getUsuarioAlteracao());
		
		if(Objects.nonNull(param.getCbo()) && Objects.nonNull(param.getCbo().getId())) {
			Optional<Cbo> cbo = repository.findById(param.getCbo().getId());
			retorno.setCbo(cbo.get());
		} 

		if(Objects.nonNull(param.getInstituicao()) && Objects.nonNull(param.getInstituicao().getId())) {
			Optional<Instituicao> instituicao = instituicaoRepository.findById(param.getInstituicao().getId());
			retorno.setInstituicao(instituicao.get());
		} 

		retorno.setDescricaoPerfilProfissional(param.getDescricaoPerfilProfissional());
		retorno.setDescricaoResumoAtividades(param.getDescricaoResumoAtividades());
		retorno.setQtdHoras(param.getQtdHoras());
		
		
		return retorno;
	}

	
	public CargoTO buildTO(Cargo param) {
		CargoTO retorno = new CargoTO();
		

		if(Objects.isNull(param)) {
			return retorno;
		}
		
		retorno.setId(param.getId());
		retorno.setCodigo(param.getCodigo());
		retorno.setNome(param.getNome());
		retorno.setTipoCargo(param.getTipoCargo().getTipo());
		retorno.setUsuarioAlteracao(param.getUsuarioAlteracao());
		
		if(Objects.nonNull(param.getCbo())) {
			retorno.setCbo(cboTOBuilder.buildTO(param.getCbo()));
		}
		if(Objects.nonNull(param.getInstituicao())) {
			retorno.setInstituicao(instituicaoTOBuilder.buildTO(param.getInstituicao()));
		}
			
		retorno.setDescricaoPerfilProfissional(param.getDescricaoPerfilProfissional());
		retorno.setDescricaoResumoAtividades(param.getDescricaoResumoAtividades());
		retorno.setQtdHoras(param.getQtdHoras());
		
		return retorno;
	}
	
	
	public List<CargoTO> buildAll(List<Cargo> dtos){
		return dtos.stream().map(dto -> buildTO(dto)).collect(Collectors.toList());
	}

}

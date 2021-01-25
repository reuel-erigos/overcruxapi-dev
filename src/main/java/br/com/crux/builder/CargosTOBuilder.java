package br.com.crux.builder;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.cmd.GetGrausInstrucaoCmd;
import br.com.crux.cmd.GetUnidadeLogadaCmd;
import br.com.crux.cmd.GetUsuarioLogadoCmd;
import br.com.crux.dao.repository.CboRepository;
import br.com.crux.entity.Cargo;
import br.com.crux.entity.Cbo;
import br.com.crux.entity.GrausInstrucao;
import br.com.crux.enums.TipoCargo;
import br.com.crux.to.CargoTO;


@Component
public class CargosTOBuilder {
	
	@Autowired private CboTOBuilder cboTOBuilder;
	@Autowired private CboRepository repository;
	@Autowired private GetGrausInstrucaoCmd getGrausInstrucaoCmd;
	@Autowired private GrausInstrucaoTOBuilder grausInstrucaoTOBuilder;
	@Autowired private GetUnidadeLogadaCmd getUnidadeLogadaCmd;
	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;
	
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

		
		Long idInstituicao = getUnidadeLogadaCmd.getUnidadeTO().getInstituicao().getId();
		retorno.setIdInstituicao(idInstituicao);
		retorno.setUsuarioAlteracao(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());
		
		
		if(Objects.nonNull(param.getGrausInstrucao()) && Objects.nonNull(param.getGrausInstrucao().getId())) {
			GrausInstrucao grauInstrucao = getGrausInstrucaoCmd.getById(param.getGrausInstrucao().getId());
			retorno.setGrausInstrucao(grauInstrucao);
		} 
		
		

		retorno.setDescricaoPerfilProfissional(param.getDescricaoPerfilProfissional());
		retorno.setDescricaoResumoAtividades(param.getDescricaoResumoAtividades());
		retorno.setQtdHoras(param.getQtdHoras());
		retorno.setCodigo("Codigo enquanto nao ajusta a tabela");
		
		
		
		
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
			
		if(Objects.nonNull(param.getGrausInstrucao())) {
			retorno.setGrausInstrucao(grausInstrucaoTOBuilder.buildTO(param.getGrausInstrucao()));
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

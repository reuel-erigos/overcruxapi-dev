package br.com.crux.builder;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.cmd.GetFuncionarioCmd;
import br.com.crux.cmd.GetUnidadeLogadaCmd;
import br.com.crux.dao.repository.AnexosAcaoPlanejamentoRepository;
import br.com.crux.dao.repository.GrupoAcoesRepository;
import br.com.crux.dao.repository.MateriaisAcoesRepository;
import br.com.crux.entity.Acoes;
import br.com.crux.entity.AnexosAcaoPlanejamento;
import br.com.crux.entity.Funcionario;
import br.com.crux.entity.GrupoAcoes;
import br.com.crux.entity.MateriaisAcoes;
import br.com.crux.to.AcaoTO;

@Component
public class AcaoTOBuilder {

	@Autowired private FuncionarioTOBuilder funcionarioTOBuilder;
	@Autowired private GetFuncionarioCmd getFuncionarioCmd;
	@Autowired private MateriaisAcoesTOBuilder materiaisAcoesTOBuilder;
	@Autowired private MateriaisAcoesRepository materiaisAcoesRepository;
	@Autowired private GetUnidadeLogadaCmd getUnidadeLogadaCmd;
	@Autowired private AnexosAcaoPlanejamentoTOBuilder anexosAcaoPlanejamentoTOBuilder;
	@Autowired private AnexosAcaoPlanejamentoRepository anexosAcaoPlanejamentoRepository;
	@Autowired private GrupoAcoesRepository grupoAcoesRepository;
	@Autowired private GrupoAcoesTOBuilder grupoAcoesTOBuilder;
	
	

	public Acoes build(AcaoTO p) {
		Acoes retorno = new Acoes();

		BeanUtils.copyProperties(p, retorno);
		
		Optional.ofNullable(p.getFuncionarioAprovaAcao()).ifPresent(func -> {
			if(Objects.nonNull(func.getId())) {
				Funcionario funcionario = getFuncionarioCmd.getById(func.getId());
				retorno.setFuncionarioAprovaAcao(funcionario);
			}
		});
		
		Optional.ofNullable(p.getFuncionarioExecutaAcao()).ifPresent(func -> {
			if(Objects.nonNull(func.getId())) {
				Funcionario funcionario = getFuncionarioCmd.getById(func.getId());
				retorno.setFuncionarioExecutaAcao(funcionario);
			}
		});
		
		Optional.ofNullable(p.getFuncionarioPlanejamentoAcao()).ifPresent(func -> {
			if(Objects.nonNull(func.getId())) {
				Funcionario funcionario = getFuncionarioCmd.getById(func.getId());
				retorno.setFuncionarioPlanejamentoAcao(funcionario);
			}
		});
		
		retorno.setIdInstituicao(getUnidadeLogadaCmd.getUnidadeTO().getInstituicao().getId());
		retorno.setIdGrupoAcao(p.getGrupoAcao().getId());

		return retorno;
	}

	public AcaoTO buildTO(Acoes p) {
		AcaoTO retorno = new AcaoTO();

		if (Objects.isNull(p)) {
			return retorno;
		}

		BeanUtils.copyProperties(p, retorno);
		
		retorno.setFuncionarioAprovaAcao(funcionarioTOBuilder.buildTO(p.getFuncionarioAprovaAcao()));
		retorno.setFuncionarioExecutaAcao(funcionarioTOBuilder.buildTO(p.getFuncionarioExecutaAcao()));
		retorno.setFuncionarioPlanejamentoAcao(funcionarioTOBuilder.buildTO(p.getFuncionarioPlanejamentoAcao()));
		
		if(Objects.nonNull(p.getId())) {
			Optional<List<MateriaisAcoes>> materiais = materiaisAcoesRepository.findAllByIdAcao(p.getId());
			if(materiais.isPresent()) {
				retorno.setMateriaisAcao(materiaisAcoesTOBuilder.buildAll(materiais.get()));
			}
		}
		retorno.setIdInstituicao(getUnidadeLogadaCmd.getUnidadeTO().getInstituicao().getId());
		
		
		if(Objects.nonNull(p.getId())) {
			Optional<List<AnexosAcaoPlanejamento>> anexos = anexosAcaoPlanejamentoRepository.findAllByIdAcao(p.getId());
			if(anexos.isPresent()) {
				retorno.setAnexos(anexosAcaoPlanejamentoTOBuilder.buildAllTO(anexos.get()));
			}
		}
		
		if(Objects.nonNull(p.getIdGrupoAcao())) {
			Optional<GrupoAcoes> grupoAcao = grupoAcoesRepository.findById(p.getIdGrupoAcao());
			retorno.setGrupoAcao(grupoAcoesTOBuilder.buildSimplesTO(grupoAcao.get()));
		}
		
		return retorno;
	}

	public List<AcaoTO> buildAll(List<Acoes> dtos) {
		return dtos.stream().map(dto -> buildTO(dto)).collect(Collectors.toList());
	}

	public List<Acoes> buildTOAll(List<AcaoTO> dtos) {
		return dtos.stream().map(dto -> build(dto)).collect(Collectors.toList());
	}
	
}

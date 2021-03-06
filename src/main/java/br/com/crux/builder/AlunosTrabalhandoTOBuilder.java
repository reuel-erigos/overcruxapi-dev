package br.com.crux.builder;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.cmd.GetAlunoCmd;
import br.com.crux.cmd.GetEmpresaCmd;
import br.com.crux.dao.dto.ComboAlunoTrabalhandoDTO;
import br.com.crux.entity.Aluno;
import br.com.crux.entity.AlunosTrabalhando;
import br.com.crux.entity.Empresa;
import br.com.crux.to.AlunosTrabalhandoTO;
import br.com.crux.to.ComboAlunoTrabalhandoTO;

@Component
public class AlunosTrabalhandoTOBuilder {

	@Autowired private GetAlunoCmd getAlunoCmd;
	@Autowired private AlunoTOBuilder alunoBuilder;

	@Autowired private GetEmpresaCmd getEmpresaCmd;
	@Autowired private EmpresaTOBuilder empresaBuilder;

	
	public AlunosTrabalhando build(AlunosTrabalhandoTO p) {
		AlunosTrabalhando retorno = new AlunosTrabalhando();

		retorno.setId(p.getId());
		retorno.setDescTipoEmpreendimento(p.getDescTipoEmpreendimento());
		retorno.setNomeEmpreendimento(p.getNomeEmpreendimento());
		retorno.setDataFimAlunoTrabalhando(p.getDataFimAlunoTrabalhando());
		retorno.setDataInicioAlunoTrabalhando(p.getDataInicioAlunoTrabalhando());

		Optional.ofNullable(p.getAluno()).ifPresent((a) -> {
			Aluno aluno = getAlunoCmd.getById(a.getId());
			retorno.setAluno(aluno);
		});

		Optional.ofNullable(p.getEmpresa()).ifPresent((a) -> {
			Empresa empresa = getEmpresaCmd.getById(a.getId());
			retorno.setEmpresa(empresa);
		});
		
		retorno.setUsuarioAlteracao(p.getUsuarioAlteracao());
		return retorno;
	}

	public AlunosTrabalhandoTO buildTO(AlunosTrabalhando p) {
		AlunosTrabalhandoTO retorno = new AlunosTrabalhandoTO();

		if (Objects.isNull(p)) {
			return retorno;
		}

		retorno.setId(p.getId());
		retorno.setDescTipoEmpreendimento(p.getDescTipoEmpreendimento());
		retorno.setNomeEmpreendimento(p.getNomeEmpreendimento());
		retorno.setDataFimAlunoTrabalhando(p.getDataFimAlunoTrabalhando());
		retorno.setDataInicioAlunoTrabalhando(p.getDataInicioAlunoTrabalhando());
		retorno.setAluno(alunoBuilder.buildTO(p.getAluno()));
		retorno.setEmpresa(empresaBuilder.buildTO(p.getEmpresa()));
		retorno.setUsuarioAlteracao(p.getUsuarioAlteracao());

		return retorno;
	}
	
	public ComboAlunoTrabalhandoTO buildComboTO(ComboAlunoTrabalhandoDTO p) {
		ComboAlunoTrabalhandoTO retorno = new ComboAlunoTrabalhandoTO();
		
		if(Objects.isNull(p)) {
			return retorno;
		}
		
		retorno.setId(p.getId());
		retorno.setNome(p.getNome());
		return retorno;
	}

	public List<AlunosTrabalhandoTO> buildAll(List<AlunosTrabalhando> dtos) {
		return dtos.stream().map(dto -> buildTO(dto)).collect(Collectors.toList());
	}
	
	public List<ComboAlunoTrabalhandoTO> buildAllDTO(List<ComboAlunoTrabalhandoDTO> dtos) {
		return dtos.stream().map(dto -> buildComboTO(dto)).collect(Collectors.toList());
	}

}

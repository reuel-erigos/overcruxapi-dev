package br.com.crux.cmd;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.EncaminhaAlunosTOBuilder;
import br.com.crux.dao.repository.EncaminhaAlunosRepository;
import br.com.crux.entity.EncaminhaAlunos;
import br.com.crux.rule.CamposObrigatoriosEncaminhaAlunosRule;
import br.com.crux.to.AlunoTO;
import br.com.crux.to.EncaminhaAlunosTO;
import br.com.crux.to.UsuarioLogadoTO;

@Component
public class CadastrarEncaminhaAlunosCmd {

	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;
	
	@Autowired private EncaminhaAlunosRepository repository;
	@Autowired private CamposObrigatoriosEncaminhaAlunosRule camposObrigatoriosRule;
	@Autowired private EncaminhaAlunosTOBuilder encaminhaAlunosTOBuilder;
	
	
	public EncaminhaAlunos cadastrar(Long idAluno, EncaminhaAlunosTO encaminhamentoTO) {
		encaminhamentoTO.setIdAluno(idAluno);

		camposObrigatoriosRule.verificar(encaminhamentoTO.getEntidadeSocial().getId(), encaminhamentoTO.getIdAluno());
		
		EncaminhaAlunos entity = encaminhaAlunosTOBuilder.build(encaminhamentoTO);
		UsuarioLogadoTO usuarioLogado = getUsuarioLogadoCmd.getUsuarioLogado();
		entity.setUsuarioAlteracao(usuarioLogado.getIdUsuario());
		
		return repository.save(entity);
	}
	
	
	public void cadastrarLista(AlunoTO aluno, List<EncaminhaAlunosTO> lista) {
		if(Objects.nonNull(lista)) {
			lista.stream()
			     .map(item -> cadastrar(aluno.getId(), item))
			     .collect(Collectors.toList());
		}
	}
}

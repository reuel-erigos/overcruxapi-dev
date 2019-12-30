package br.com.crux.cmd;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.AtividadesAlunoTOBuilder;
import br.com.crux.dao.repository.AtividadesAlunoRepository;
import br.com.crux.dao.repository.FrequenciasAlunosRepository;
import br.com.crux.entity.AtividadesAluno;
import br.com.crux.entity.FrequenciasAlunos;
import br.com.crux.exception.NotFoundException;
import br.com.crux.rule.CamposObrigatoriosFrequenciasAlunosRule;
import br.com.crux.to.FrequenciasAlunosTO;
import br.com.crux.to.UsuarioLogadoTO;

@Component
public class CadastrarFrequenciasAlunosCmd {

	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;
	@Autowired private FrequenciasAlunosRepository repository;
	@Autowired private CamposObrigatoriosFrequenciasAlunosRule camposObrigatoriosRule;

	@Autowired private AtividadesAlunoRepository atividadesAlunoRepository;
	@Autowired private AtividadesAlunoTOBuilder atividadesAlunoBuilder;
	
	
	public void cadastrar(FrequenciasAlunosTO to) {
		
		if(Objects.isNull(to.getAtividadesAluno())) {
			throw new NotFoundException("Atividade do alno não informada.");
		}
		
		camposObrigatoriosRule.verificar(to);
		
		Optional<AtividadesAluno> atividadeOptional = atividadesAlunoRepository.findById(to.getAtividadesAluno().getId());
		if(!atividadeOptional.isPresent()) {
			throw new NotFoundException("Atividade do aluno informada não existe.");
		}
		
		FrequenciasAlunos entity = new FrequenciasAlunos();

		entity.setDataFrequencia(to.getDataFrequencia().toLocalDate());
		entity.setJustificativa(to.getJustificativa());
		entity.setAtividadesAluno(atividadesAlunoBuilder.build(to.getAtividadesAluno()));
		
		UsuarioLogadoTO usuarioLogado = getUsuarioLogadoCmd.getUsuarioLogado();
		entity.setUsuarioAlteracao(usuarioLogado.getIdUsuario());
		
		repository.save(entity);
		
	}
}

package br.com.crux.cmd;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.AlunoTOBuilder;
import br.com.crux.builder.EntidadesSociaisTOBuilder;
import br.com.crux.dao.repository.EncaminhaAlunosRepository;
import br.com.crux.entity.EncaminhaAlunos;
import br.com.crux.exception.NotFoundException;
import br.com.crux.rule.CamposObrigatoriosEncaminhaAlunosRule;
import br.com.crux.to.EncaminhaAlunosTO;
import br.com.crux.to.UsuarioLogadoTO;

@Component
public class AlterarEncaminhaAlunosCmd {

	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;
	
	@Autowired private EncaminhaAlunosRepository repository;
	@Autowired private CamposObrigatoriosEncaminhaAlunosRule camposObrigatoriosRule;
	
	@Autowired private AlunoTOBuilder alunoTOBuilder;
	@Autowired private EntidadesSociaisTOBuilder entidadesSociaisTOBuilder;
	
	
	public void alterar(EncaminhaAlunosTO to) {
		Optional<EncaminhaAlunos> entityOptional = repository.findById(to.getId());
		if(!entityOptional.isPresent()) {
			throw new NotFoundException("Encaminhamento de aluno informado não existe.");
		}
		
		if(Objects.isNull(to.getAluno())) {
			throw new NotFoundException("Aluno não informado.");
		}
		if(Objects.isNull(to.getEntidadeSocial())) {
			throw new NotFoundException("Entidade social não informada.");
		}
		
		camposObrigatoriosRule.verificar(to.getEntidadeSocial().getId(), to.getAluno().getId());
		
		EncaminhaAlunos entity = entityOptional.get();
		
		entity.setDataEncaminhaAluno(to.getDataEncaminhaAluno());
		entity.setDescricao(to.getDescricao());
		entity.setEntidadesSocial(entidadesSociaisTOBuilder.build(to.getEntidadeSocial()));
		entity.setAluno(alunoTOBuilder.build(to.getAluno()));
		
		UsuarioLogadoTO usuarioLogado = getUsuarioLogadoCmd.getUsuarioLogado();
		entity.setUsuarioAlteracao(usuarioLogado.getIdUsuario());
		
		repository.save(entity);
		
	}
}

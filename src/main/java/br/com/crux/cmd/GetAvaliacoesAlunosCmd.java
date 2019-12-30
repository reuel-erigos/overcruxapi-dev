package br.com.crux.cmd;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.BiPredicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.AtividadesAlunoTOBuilder;
import br.com.crux.builder.AvaliacoesAlunosTOBuilder;
import br.com.crux.dao.repository.AvaliacoesAlunosRepository;
import br.com.crux.entity.Avaliacoes;
import br.com.crux.entity.AvaliacoesAlunos;
import br.com.crux.exception.NotFoundException;
import br.com.crux.to.AtividadesAlunoTO;
import br.com.crux.to.AvaliacoesAlunosTO;

@Component
public class GetAvaliacoesAlunosCmd {

	@Autowired private AvaliacoesAlunosRepository repository;
	@Autowired private AvaliacoesAlunosTOBuilder toBuilder;
	@Autowired private GetUnidadeLogadaCmd getUnidadeLogadaCmd;
	@Autowired private GetAlunoCmd getAlunoCmd;
	@Autowired private GetAtividadesAlunoCmd getAtividadesAlunoCmd;
	@Autowired private AtividadesAlunoTOBuilder atividadeAlunoTOBuilder;
	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;
	@Autowired private GetAvaliacoesCmd getAvaliacoesCmd;
	
	
	public List<AvaliacoesAlunosTO> getAllAlunosMatriculadosTO(Long idAtividade, Long idAvaliacao) {
		List<AvaliacoesAlunos> frequencias = getAlunosMatriculados(idAtividade, idAvaliacao);
		return toBuilder.buildAll(frequencias);
	}
	
	public List<AvaliacoesAlunos> getAllAlunosMatriculados(Long idAtividade, Long idAvaliacao) {
		return getAlunosMatriculados(idAtividade, idAvaliacao);
	}	
	
	private List<AvaliacoesAlunos> getAlunosMatriculados(Long idAtividade, Long idAvaliacao) {
		Avaliacoes avaliacaoEntity = getAvaliacoesCmd.getById(idAvaliacao);
		
		List<AvaliacoesAlunos> avaliacoes = new ArrayList<AvaliacoesAlunos>();
		
		List<AtividadesAlunoTO> atividadesAlunos = getAtividadesAlunoCmd.getAllFilter(null, null, idAtividade);
		
		
		// Lista de alunos que já tiveram a avaliacao registrada nessa atividade
		List<AvaliacoesAlunosTO> listaAvaliacoesTO = getListaAvaliacoesTO(idAtividade, idAvaliacao);
		
		BiPredicate<AtividadesAlunoTO, List<AvaliacoesAlunosTO>> contemNaLista  = (parte, lista) -> lista.stream()
									  											                         .anyMatch(registroTO -> isAvaliado(parte,registroTO));
		
		
		atividadesAlunos.stream().forEach( atividadeAluno -> {
			AvaliacoesAlunos avaliacao = new AvaliacoesAlunos();
			
			if(contemNaLista.test(atividadeAluno, listaAvaliacoesTO)) {
				AvaliacoesAlunosTO frequenciasAlunosTO = listaAvaliacoesTO.stream().filter(registroTO -> isAvaliado(atividadeAluno, registroTO)).findFirst().orElse(null);
				if(Objects.nonNull(frequenciasAlunosTO)) {
					avaliacao = toBuilder.build(frequenciasAlunosTO);
				}
			} else {
				avaliacao.setAtividadesAluno(atividadeAlunoTOBuilder.build(atividadeAluno));
				avaliacao.setDataAvaliacao(LocalDateTime.now());
				avaliacao.setAvaliacoes(avaliacaoEntity);
				avaliacao.setUsuarioAlteracao(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());
			}
			
			avaliacoes.add(avaliacao);
		});
		
		
		return avaliacoes;		
	}

	private boolean isAvaliado(AtividadesAlunoTO parte, AvaliacoesAlunosTO registroTO) {
		return Objects.nonNull(registroTO.getId()) 
		                       && 
		                       registroTO.getAtividadesAluno().getAluno().getId().equals(parte.getAluno().getId())
		                       &&
		                       registroTO.getAtividadesAluno().getAtividade().getId().equals(parte.getAtividade().getId());
	}	
	
	
	
	private List<AvaliacoesAlunos> get(Long idAtividade, Long idAvaliacao) {
		Optional<List<AvaliacoesAlunos>> entitys = Optional.empty();
		entitys = repository.findByAtividadeAndAvaliacao(idAtividade,idAvaliacao);
		
		if(entitys.isPresent()) {
			return entitys.get();
		}
		return new ArrayList<AvaliacoesAlunos>();		
	}	
	
	
	public List<AvaliacoesAlunosTO> getListaAvaliacoesTO(Long idAtividade, Long idAvaliacao) {
		List<AvaliacoesAlunos> avaliacoes = get(idAtividade, idAvaliacao);
		return toBuilder.buildAll(avaliacoes);
	}
	
	
	public List<AvaliacoesAlunosTO> getAll() {
		Long idUnidade = getUnidadeLogadaCmd.get().getId();
		Optional<List<AvaliacoesAlunos>> entitys = repository.findByUnidade(idUnidade);
		if(entitys.isPresent()) {
			return toBuilder.buildAll(entitys.get());
		}
		return new ArrayList<AvaliacoesAlunosTO>();
	}
	
	public AvaliacoesAlunosTO getById(Long id) {
		Optional<AvaliacoesAlunos> entityOptional = repository.findById(id);
		if(!entityOptional.isPresent()) {
			throw new NotFoundException("Avaliação do aluno não encontrada.");
		}
		return toBuilder.buildTO(entityOptional.get());
	}
		
	
	
	public List<AvaliacoesAlunosTO>  getAllAlunosMatriculadosTO(Long idAtividade) {
		return toBuilder.buildAll(getAllFilter(null, idAtividade));
	}
	
	public List<AvaliacoesAlunos>  getAllAlunosMatriculados(Long idAtividade) {
		return getAllFilter(null, idAtividade);
	}
	
	public List<AvaliacoesAlunos> getAllFilter(Long idAluno, Long idAtividade) {
		Long idUnidade = null;
		
		if(Objects.isNull(idAluno)) {
			idUnidade = getUnidadeLogadaCmd.get().getId(); 
		}else {
			idUnidade= getAlunoCmd.getTOById(idAluno).getUnidade().getIdUnidade();
		}
		Optional<List<AvaliacoesAlunos>> entitys = Optional.empty();
		
		if(Objects.isNull(idAluno) && Objects.isNull(idAtividade)) {
			entitys = repository.findByUnidade(idUnidade);	
		}else if(Objects.isNull(idAluno) && Objects.nonNull(idAtividade)) {
			entitys = repository.findByAtividade(idAtividade);
		}else if(Objects.isNull(idAtividade)  && Objects.nonNull(idAluno)) {
			entitys = repository.findByAluno(idAluno);
		}else if(Objects.nonNull(idAluno) && Objects.nonNull(idAtividade)) {
			entitys = repository.findByAlunoAndAtividade(idAluno, idAtividade);
		}
		
		if(entitys.isPresent()) {
			return entitys.get();
		}
		return new ArrayList<AvaliacoesAlunos>();
	}
}

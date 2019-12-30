package br.com.crux.cmd;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import br.com.crux.dao.repository.FamiliaresRepository;
import br.com.crux.exception.ParametroNaoInformadoException;
import br.com.crux.exception.TabaleReferenciaEncontradaException;
import br.com.crux.to.FamiliaresTO;

@Component
public class ExcluirFamiliaresCmd {

	@Autowired private FamiliaresRepository repository;
	@Autowired private GetFamiliaresCmd getFamiliaresCmd;
	@Autowired private ExcluirReprovacoesAlunoCmd excluirReprovacoesAlunoCmd;
	@Autowired private ExcluirPessoaFisicaCmd excluirPessoaFisicaCmd;
	@Autowired private ExcluirVulnerabilidadesFamiliarCmd excluirVulnerabilidadesFamiliarCmd;
	
	
	public void excluir(Long idFamiliar) {
		
		try {
			if(Objects.isNull(idFamiliar)) {
				throw new ParametroNaoInformadoException("Erro ao excluir o familiar.");
			}
			
			FamiliaresTO familiaresTO = getFamiliaresCmd.getTOById(idFamiliar);
			
			//Apaga todos os responsáveis desse familiar.
			familiaresTO.getResponsaveis().stream().forEach(r -> excluirReprovacoesAlunoCmd.excluir(r.getId()));
			
			//Apaga todas a vulnuerabilidades desse familiar
			familiaresTO.getVulnerabilidades().stream().forEach( v -> excluirVulnerabilidadesFamiliarCmd.excluir(v.getId()));
			
			//Apaga o familiar
			repository.deleteById(idFamiliar);
			
			//Apaga a pessoa fisica
			excluirPessoaFisicaCmd.excluirPorId(familiaresTO.getPessoasFisica().getId());
			
		} catch (DataIntegrityViolationException e) {
			throw new TabaleReferenciaEncontradaException("Erro ao excluir, verifique se há outro cadastro com referência a este familiar.");
		}	
		
	}
}

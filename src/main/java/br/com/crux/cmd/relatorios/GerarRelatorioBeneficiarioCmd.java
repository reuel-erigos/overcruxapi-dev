package br.com.crux.cmd.relatorios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.exception.RelatorioException;
import br.com.crux.exception.base.NegocioException;

@Component
public class GerarRelatorioBeneficiarioCmd {
	
	@Autowired private GerarRelatorioFichaMatriculaBeneficiarioCmd gerarRelatorioFichaMatriculaCmd;
	@Autowired private GerarRelatorioDeclaracaoBeneficiarioCmd gerarRelatorioDeclaracaoBeneficiarioCmd;
	@Autowired private GerarRelatorioPasseEstudantilBeneficiarioCmd gerarRelatorioPasseEstudantilBeneficiarioCmd;
	
	private static final String FICHA_MATRICULA  = "FM";
	private static final String DECLARACAO       = "DE";
	private static final String PASSE_ESTUDANTIL = "PE";
	
	
	public byte[] gerar(List<Integer> listaIdsPessoaFisica,String mimeType, String tipoRelatorio)  {
		try {
			byte[] dados = null;
			
			switch (tipoRelatorio) {
			case FICHA_MATRICULA:
				dados = gerarRelatorioFichaMatriculaCmd.gerar(listaIdsPessoaFisica, mimeType);
				break;
			case DECLARACAO:
				dados = gerarRelatorioDeclaracaoBeneficiarioCmd.gerar(listaIdsPessoaFisica, mimeType);
				break;
			case PASSE_ESTUDANTIL:
				dados = gerarRelatorioPasseEstudantilBeneficiarioCmd.gerar(listaIdsPessoaFisica, mimeType);
				break;				
			default:
				throw new RelatorioException("Tipo de relatório informátido está inválido.");
			}
			
			return dados;
			
		} catch (RelatorioException e) {
			throw new NegocioException(e.getMessage());
		} catch (Exception e) {
			throw new NegocioException("Não foi possível gerar o relatório.");
		}
	}
	
}

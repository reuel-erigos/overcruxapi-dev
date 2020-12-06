package br.com.crux.cmd.relatorios;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.relatorios.FichaMatriculaTOBuilder;
import br.com.crux.dao.relatorios.FichaMatriculaDao;
import br.com.crux.infra.relatorio.GeradorRelatorio;
import br.com.crux.to.relatorios.FichaMatriculaTO;

@Component
public class GerarRelatorioFichaMatriculaBeneficiarioCmd {

	@Autowired private GeradorRelatorio geradorRelatorio;
	@Autowired private FichaMatriculaDao fichaMatriculaDao;
	@Autowired private FichaMatriculaTOBuilder fichaMatriculaTOBuilder;
	
	public byte[] gerar(List<Integer> listaIdsPessoaFisica,String mimeType) throws Exception  {
		String nomeRelatorio = "Ficha_Matricula";
		String[] path = {"casa_azul", "ficha_matricula"};
		
		Map<String, Object> parametros = new HashMap<>();
		
		List<FichaMatriculaTO> dados = fichaMatriculaTOBuilder.buildAll(fichaMatriculaDao.get(listaIdsPessoaFisica));
		
		return geradorRelatorio.gerar(parametros, dados, nomeRelatorio, path, mimeType);
	}
	
}

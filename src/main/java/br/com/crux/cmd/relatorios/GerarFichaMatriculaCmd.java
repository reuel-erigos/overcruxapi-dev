package br.com.crux.cmd.relatorios;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.exception.base.NegocioException;
import br.com.crux.infra.relatorio.GeradorRelatorio;
import br.com.crux.infra.relatorio.TipoRelatorio;

@Component
public class GerarFichaMatriculaCmd {

	@Autowired
	private GeradorRelatorio geradorRelatorio;
	
	public byte[] gerarPDF(List<ParametrosTO> param, TipoRelatorio tipoRelatorio)  {
		try {
			String nomeRelatorio = "Ficha_Matricula";
			String pathRelatorio = "ficha_matricula";
			
			Map<String, Object> parametros = new HashMap<>();
			
			List<String> dados = new ArrayList<String>();
			
			
			switch (tipoRelatorio) {
			case PDF:
				return geradorRelatorio.gerarPDF(parametros, dados, nomeRelatorio, pathRelatorio);
			case XLS:
				return geradorRelatorio.gerarExcel(parametros, dados, nomeRelatorio, pathRelatorio);
			default:
				throw new RuntimeException("Tipo de relatório inválido.");
			}
			
		} catch (Exception e) {
			throw new NegocioException("Não foi possível gerar o relatório.");
		}
	}
	
	
	
	
	
	
}

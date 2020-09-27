package br.com.crux.cmd;

import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.excel.ConciliacaoBancariaExcelFileExporter;
import br.com.crux.exception.ConciliacaoNaoGeradoException;
import br.com.crux.exception.ConciliacaoSemDocumentoInvalidaException;
import br.com.crux.to.ConciliacaoTO;

@Component
public class GerarArquivoConciliacaoCmd {

	@Autowired private AjustarConciliacaoCmd ajustarCmd;

	public byte[] gerar(List<ConciliacaoTO> conciliacoes) {
		try {
			//
			boolean fornecedoresSemDocumento = conciliacoes.stream()
			                                            .anyMatch(c -> StringUtils.isEmpty(c.getFornecedor()) && (Objects.isNull(c.getSemDocumento()) || !c.getSemDocumento()));
			if(fornecedoresSemDocumento) {
				throw new ConciliacaoSemDocumentoInvalidaException("Não é possível exportar, pois existem fornecedores sem documentos.");
			}
			
			ajustarCmd.ajustar(conciliacoes);
			
	        ByteArrayInputStream stream = ConciliacaoBancariaExcelFileExporter.gerarFileExcel(conciliacoes);
	        byte[] targetArray = new byte[stream.available()];
	        stream.read(targetArray);
			return targetArray;
			
		} catch (Exception e) {
			throw new ConciliacaoNaoGeradoException(e.getMessage());
		}
	}


}

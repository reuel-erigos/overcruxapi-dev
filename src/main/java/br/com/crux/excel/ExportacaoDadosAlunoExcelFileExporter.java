package br.com.crux.excel;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.cmd.GetAlunoCmd;
import br.com.crux.cmd.GetFamiliaresCmd;
import br.com.crux.cmd.GetPessoaFisicaCmd;
import br.com.crux.exception.ProvisionamentoNaoGeradoException;
import br.com.crux.exception.base.NegocioException;
import br.com.crux.to.exportacao.GrupoDadosExportar;
import br.com.crux.to.exportacao.ListaCompletaDadosExportar;


@Component
public class ExportacaoDadosAlunoExcelFileExporter {
	
	@Autowired private GetAlunoCmd getAlunoCmd;
	@Autowired private GetPessoaFisicaCmd getPessoaFisicaCmd;
	@Autowired private GetFamiliaresCmd getFamiliaresCmd;
	
	
	public byte[] gerar(ListaCompletaDadosExportar listaCompletaDadosExportar) {
		
		ByteArrayInputStream stream = gerarFileExcel(listaCompletaDadosExportar);
        byte[] targetArray = new byte[stream.available()];
        try {
			stream.read(targetArray);
		} catch (IOException e) {
			throw new NegocioException	(e.getMessage());
		}
        return targetArray;
	}
	
	private ByteArrayInputStream gerarFileExcel(ListaCompletaDadosExportar listaCompletaDadosExportar) {
		
		try(Workbook workbook = new XSSFWorkbook()){
			Sheet sheet = workbook.createSheet("Aluno/Familiar");
			
			List<GrupoDadosExportar> grupoAluno    = listaCompletaDadosExportar.getExportarDados().getGrupo().stream().filter(p -> p.getEntidade().equals("aluno")).collect(Collectors.toList());
			List<GrupoDadosExportar> grupoFamiliar = listaCompletaDadosExportar.getExportarDados().getGrupo().stream().filter(p -> p.getEntidade().equals("familiar")).collect(Collectors.toList());
			
		
			Row row = sheet.createRow(0);
			
			AtomicInteger indexColunaAluno = new AtomicInteger(0);
			grupoAluno.stream().filter(grupo -> grupo.isExportar()).forEach(grupo -> {
				CellStyle headerCellStyle = workbook.createCellStyle();
				headerCellStyle.setFillForegroundColor(IndexedColors.AQUA.getIndex());
				headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
				
				grupo.getColunas().stream()
				                  .filter(coluna -> coluna.isExportar())
				                  .forEach(coluna -> {
					Cell cell = row.createCell(indexColunaAluno.getAndIncrement());
					cell.setCellValue(coluna.getDescricao());
					cell.setCellStyle(headerCellStyle);
				});				
			});
			

			AtomicInteger indexColunaFamiliar = new AtomicInteger(0);
			grupoFamiliar.stream().filter(grupo -> grupo.isExportar()).forEach(grupo -> {
				CellStyle headerCellStyle = workbook.createCellStyle();
				headerCellStyle.setFillForegroundColor(IndexedColors.AQUA.getIndex());
				headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
				
				grupo.getColunas().stream()
				                  .filter(coluna -> coluna.isExportar())
				                  .forEach(coluna -> {
					Cell cell = row.createCell(indexColunaFamiliar.getAndIncrement());
					cell.setCellValue(coluna.getDescricao());
					cell.setCellStyle(headerCellStyle);
				});				
			});
			
	        
	        // Creating data rows for each customer
	        for(int i = 0; i < listaCompletaDadosExportar.getListaDadosExportacao().size(); i++) {
	        	Row dataRow = sheet.createRow(i + 1);
	        	/*
	        	dataRow.createCell(0).setCellValue(dados.get(i).getId());
	        	dataRow.createCell(1).setCellValue(dados.get(i).getSituacao());
	        	dataRow.createCell(2).setCellValue(dados.get(i).getNumeroDocumento());
	        	dataRow.createCell(3).setCellValue(Java8DateUtil.getLocalDateFormater(dados.get(i).getData()));
	        	dataRow.createCell(4).setCellValue(dados.get(i).getValor());
	        	dataRow.createCell(5).setCellValue(dados.get(i).getComplemento());
	        	dataRow.createCell(6).setCellValue(dados.get(i).getCategoria());
	        	dataRow.createCell(7).setCellValue(dados.get(i).getCentroCusto());
	        	dataRow.createCell(8).setCellValue(dados.get(i).getGrupoContas());
	        	dataRow.createCell(9).setCellValue(dados.get(i).getDescricaoFornecedor());
	        	dataRow.createCell(10).setCellValue(dados.get(i).getNomeFornecedor());
	        	*/
	        }
	        
	        for (int i = 0; i < indexColunaAluno.get(); i++) {
	        	sheet.autoSizeColumn(i);
			}
	        for (int i = 0; i < indexColunaFamiliar.get(); i++) {
	        	sheet.autoSizeColumn(i);
			}
	        
	        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	        workbook.write(outputStream);
	        return new ByteArrayInputStream(outputStream.toByteArray());
	        
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ProvisionamentoNaoGeradoException("Erro ao exportar os dados do aluno: " + ex.getMessage());
		}
	}
}

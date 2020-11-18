package br.com.crux.excel;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
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
import br.com.crux.cmd.GetResponsavelFamiliarVigenteCmd;
import br.com.crux.exception.ProvisionamentoNaoGeradoException;
import br.com.crux.exception.base.NegocioException;
import br.com.crux.infra.util.Java8DateUtil;
import br.com.crux.to.AlunoTO;
import br.com.crux.to.EncaminhaAlunosTO;
import br.com.crux.to.ExportacaoDadosAlunoTO;
import br.com.crux.to.ResponsaveisAlunoTO;
import br.com.crux.to.exportacao.GrupoDadosExportar;
import br.com.crux.to.exportacao.ListaCompletaDadosExportar;


@Component
public class ExportacaoDadosAlunoExcelFileExporter {
	
	@Autowired private GetAlunoCmd getAlunoCmd;
	@Autowired private GetResponsavelFamiliarVigenteCmd getResponsavelFamiliarVigenteCmd;
	
	private String[] origemBeneficiosAluno =  {"Entidade Social", "Data Encaminhamento", "Descrição Encaminhamento"};
	
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
			Sheet sheet = workbook.createSheet("Aluno_Familiar");
			
			List<GrupoDadosExportar> grupoAluno    = listaCompletaDadosExportar.getExportarDados().getDados().stream().filter(p -> p.getEntidade().equals("aluno")).collect(Collectors.toList());
			List<GrupoDadosExportar> grupoFamiliar = listaCompletaDadosExportar.getExportarDados().getDados().stream().filter(p -> p.getEntidade().equals("familiar")).collect(Collectors.toList());
			
		
			Row row = sheet.createRow(0);
			
			AtomicInteger indexColuna = new AtomicInteger(0);
			grupoAluno.stream().filter(grupo -> grupo.isExportar()).forEach(grupo -> {
				
				CellStyle headerCellStyle = workbook.createCellStyle();
				headerCellStyle.setFillForegroundColor(IndexedColors.AQUA.getIndex());
				headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
				
				grupo.getColunas().stream()
				                  .filter(coluna -> coluna.isExportar())
				                  .forEach(coluna -> {
				                
				    if(grupo.getNomeGrupo().equals("outras_informacoes") && coluna.getNome().equals("origemBeneficio")) {
				    	for (String valor : origemBeneficiosAluno) {
				    		Cell cell = row.createCell(indexColuna.getAndIncrement());
				    		cell.setCellValue(valor);
				    		cell.setCellStyle(headerCellStyle);
						}
				    } else {
				    	Cell cell = row.createCell(indexColuna.getAndIncrement());
				    	cell.setCellValue(coluna.getDescricao());
				    	cell.setCellStyle(headerCellStyle);
				    }
				});				
			});
			

			grupoFamiliar.stream().filter(grupo -> grupo.isExportar()).forEach(grupo -> {
				CellStyle headerCellStyle = workbook.createCellStyle();
				headerCellStyle.setFillForegroundColor(IndexedColors.LIME.getIndex());
				headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
				
				grupo.getColunas().stream()
				                  .filter(coluna -> coluna.isExportar())
				                  .forEach(coluna -> {
					Cell cell = row.createCell(indexColuna.getAndIncrement());
					cell.setCellValue(coluna.getDescricao());
					cell.setCellStyle(headerCellStyle);
				});				
			});
			
	        
	        for(int i = 0; i < listaCompletaDadosExportar.getListaDadosExportacao().size(); i++) {
	        	AtomicInteger indexDados = new AtomicInteger(0);
	        	Row dataRow = sheet.createRow(i + 1);
	        	
	        	ExportacaoDadosAlunoTO dados = listaCompletaDadosExportar.getListaDadosExportacao().get(i);

	        	AlunoTO alunoTO        = getAlunoCmd.getTOById(dados.getIdAluno());
	        	ResponsaveisAlunoTO rv = getResponsavelFamiliarVigenteCmd.getResponsavelVigente(alunoTO.getId());
	        	
	        	List<GrupoDadosExportar> ga = grupoAluno.stream().filter(grupo -> grupo.isExportar()).collect(Collectors.toList());
	        	List<GrupoDadosExportar> gf = grupoFamiliar.stream().filter(grupo -> grupo.isExportar()).collect(Collectors.toList());
	        	
	        	
	        	//Dados do Aluno
          	  	preencherDadosPessoaisAluno(ga,indexDados, dataRow, alunoTO);
          	    preencherAdmissaoAluno(ga, indexDados, dataRow, alunoTO);
          	    preencherEscolaridadeAluno(ga, indexDados, dataRow, alunoTO);
          	    preencherDocumentosAluno(ga, indexDados, dataRow, alunoTO);
          	    preencherOutrasInformacoesAluno(ga, indexDados, dataRow, alunoTO);
          	    
          	    //Dados do familiar
          	    preencherDadosPessoaisFamiliar(gf,indexDados, dataRow, rv);
          	    preencherParentescoFamiliar(gf,indexDados, dataRow, rv);
                preencherResponsavelFamiliar(gf,indexDados, dataRow, rv);
                preencherEscolaridadeFamiliar(gf,indexDados, dataRow, rv);
                preencherDocumentosFamiliar(gf,indexDados, dataRow, rv);
                preencherDadosProfissionaisFamiliar(gf,indexDados, dataRow, rv);
                preencherOutrasInformacoesFamiliar(gf,indexDados, dataRow, rv);
                
	        }
	        
	        for (int i = 0; i < indexColuna.get(); i++) {
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

	
	
	private void preencherOutrasInformacoesFamiliar(List<GrupoDadosExportar> gf, AtomicInteger indexDados, Row dataRow, ResponsaveisAlunoTO rv) {
    	Optional<GrupoDadosExportar> grupoDados = gf.stream().filter(p -> p.getNomeGrupo().equals("outras_informacoes")).findFirst();
    	if(grupoDados.isPresent()) {
    		grupoDados.get().getColunas()
    		                   .stream()
                               .filter(coluna -> coluna.isExportar())
                               .forEach(coluna -> {
                            	   
            	  if(coluna.getNome().equals("tipoEscolaRegular")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(rv.getFamiliar().getPessoasFisica().getTipoEscola());           	  
            	  }
            	  
            	  if(coluna.getNome().equals("turnoEscolaRegular")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(rv.getFamiliar().getPessoasFisica().getTurno());
            	  }
            	  
            	  if(coluna.getNome().equals("identificacaoEscolaFrequentada")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(rv.getFamiliar().getPessoasFisica().getEscola());
            	  }
            	  
            	  if(coluna.getNome().equals("nomeCursoEscolaRegular")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(rv.getFamiliar().getPessoasFisica().getCursoEscola());
            	  }
            	  
            	  if(coluna.getNome().equals("periodoCursoEscolaRegular")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(rv.getFamiliar().getPessoasFisica().getPeriodoEscola());
            	  }
            	  
            	  if(coluna.getNome().equals("serieCursoEscolaRegular")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(rv.getFamiliar().getPessoasFisica().getSerieEscola());
            	  }
            	  
            	  if(coluna.getNome().equals("regiaoAdministrativaEscola")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(rv.getFamiliar().getPessoasFisica().getRegiaoEscola());
            	  }
            	  
            	  if(coluna.getNome().equals("possuiDeficiencia")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(rv.getFamiliar().getPessoasFisica().getEhDeficiente());
            	  }
            	  
            	  if(coluna.getNome().equals("descricaoDeficiencia")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(rv.getFamiliar().getPessoasFisica().getDescricaoDeficiencia());
            	  }
            	  
            	  if(coluna.getNome().equals("descricaoProblemaSaude")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(rv.getFamiliar().getPessoasFisica().getProblemaSaude());
            	  }
            	  
            	  if(coluna.getNome().equals("descricaoMedicamentosControlados")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(rv.getFamiliar().getPessoasFisica().getMedicamentosControlados());
            	  }
            	  
            	  if(coluna.getNome().equals("descricaoRelevanteAtendimentoOutroOrgao")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(rv.getFamiliar().getPessoasFisica().getDescricaoPessoaFisicaAtendidoOrgaoRede());
            	  }
            	  
            	  if(coluna.getNome().equals("descricaoOutrosBeneficiosFamilia")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(rv.getFamiliar().getPessoasFisica().getOutrosBenSoc());
            	  }
            	  
            	  if(coluna.getNome().equals("descricaoRelevanteRedeApoioSocial")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(rv.getFamiliar().getPessoasFisica().getRedeApoioSocial());
            	  }
            	  
            	  if(coluna.getNome().equals("descricaoRedeApoioSocial")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(rv.getFamiliar().getPessoasFisica().getRedeApSocRelev());
            	  }
            	  
            	  if(coluna.getNome().equals("valorOutrosBeneficiosSociais")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(rv.getFamiliar().getPessoasFisica().getValorOutrosBenerficiosSoc());
            	  }
            	  
            	  if(coluna.getNome().equals("identificacaoOrigemRenda")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(rv.getFamiliar().getPessoasFisica().getOrigemRendaFamiliar());
            	  }

			});		
    	}	

	}

	private void preencherDadosProfissionaisFamiliar(List<GrupoDadosExportar> gf, AtomicInteger indexDados, Row dataRow, ResponsaveisAlunoTO rv) {
    	Optional<GrupoDadosExportar> grupoDados = gf.stream().filter(p -> p.getNomeGrupo().equals("dados_profissionais")).findFirst();
    	if(grupoDados.isPresent()) {
    		grupoDados.get().getColunas()
    		                   .stream()
                               .filter(coluna -> coluna.isExportar())
                               .forEach(coluna -> {
                            	   
            	  if(coluna.getNome().equals("profissao")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(rv.getFamiliar().getPessoasFisica().getProfissao());           	  
            	  }
            	  
            	  if(coluna.getNome().equals("empresa")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(rv.getFamiliar().getPessoasFisica().getNomeEmpresaTrabalho());
            	  }
            	  
            	  if(coluna.getNome().equals("possuiBolsaFamilia")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(rv.getFamiliar().getPessoasFisica().getBeneficiarioBolsaFamilia());
            	  }
            	  
            	  if(coluna.getNome().equals("valorBolsaFamilia")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(rv.getFamiliar().getPessoasFisica().getValorBolsaFamilia());
            	  }
            	  
            	  if(coluna.getNome().equals("valorRenda")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(rv.getFamiliar().getPessoasFisica().getValorRenda());
            	  }
            	  
            	  if(coluna.getNome().equals("telefoneComercial")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(rv.getFamiliar().getPessoasFisica().getTelefoneComercial());
            	  }
            	  
            	  if(coluna.getNome().equals("situacaoTrabalho")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(rv.getFamiliar().getPessoasFisica().getSituacaoTrabalho());
            	  }
            	  
            	  if(coluna.getNome().equals("motivoNaoTrabalhar")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(rv.getFamiliar().getPessoasFisica().getMotivoNaoTrab());
            	  }
            	  
			});		
    	}	

	}

	private void preencherDocumentosFamiliar(List<GrupoDadosExportar> gf, AtomicInteger indexDados, Row dataRow, ResponsaveisAlunoTO rv) {
    	Optional<GrupoDadosExportar> grupoDados = gf.stream().filter(p -> p.getNomeGrupo().equals("documentos")).findFirst();
    	if(grupoDados.isPresent()) {
    		grupoDados.get().getColunas()
    		                   .stream()
                               .filter(coluna -> coluna.isExportar())
                               .forEach(coluna -> {
                            	   
            	  if(coluna.getNome().equals("cpf")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(rv.getFamiliar().getPessoasFisica().getCpf());           	  
            	  }
            	  
            	  if(coluna.getNome().equals("nis")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(rv.getFamiliar().getPessoasFisica().getNis());
            	  }
            	  
            	  if(coluna.getNome().equals("pis")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(rv.getFamiliar().getPessoasFisica().getNumeroPisPasep());
            	  }
            	  
            	  if(coluna.getNome().equals("identidade")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(rv.getFamiliar().getPessoasFisica().getIdentidade());
            	  }
            	  
            	  if(coluna.getNome().equals("orgaoExpedidor")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(rv.getFamiliar().getPessoasFisica().getOrgaoCi());
            	  }
            	  
            	  if(coluna.getNome().equals("ufCI")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(rv.getFamiliar().getPessoasFisica().getUfCi());
            	  }
            	  
            	  if(coluna.getNome().equals("dataEmissao")) {
            		  if(Objects.nonNull(rv.getFamiliar().getPessoasFisica().getDataEmissaoCI())) {
            			  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Java8DateUtil.getLocalDateFormater(rv.getFamiliar().getPessoasFisica().getDataEmissaoCI().toLocalDate()));
            		  }else {
            			  dataRow.createCell(indexDados.getAndIncrement()).setCellValue("");
            		  }
            	  }
            	  
            	  if(coluna.getNome().equals("tituloEleitoral")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(rv.getFamiliar().getPessoasFisica().getTituloEleitor());
            	  }
            	  
            	  if(coluna.getNome().equals("zona")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(rv.getFamiliar().getPessoasFisica().getZonaTitulo());
            	  }
            	  
            	  if(coluna.getNome().equals("sessao")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(rv.getFamiliar().getPessoasFisica().getSessaoTitulo());
            	  }
            	  
            	  if(coluna.getNome().equals("numeroReservista")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(rv.getFamiliar().getPessoasFisica().getNumeroReservista());
            	  }
            	  
            	  if(coluna.getNome().equals("numeroRegiaoMilitar")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(rv.getFamiliar().getPessoasFisica().getRegiaoMilitarReservista());
            	  }
            	  
            	  if(coluna.getNome().equals("ufReservista")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(rv.getFamiliar().getPessoasFisica().getUfRegiaoMilitar());
            	  }
            	  
            	  if(coluna.getNome().equals("cnh")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(rv.getFamiliar().getPessoasFisica().getNumeroCNH());
            	  }
            	  
            	  if(coluna.getNome().equals("categoriaCnh")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(rv.getFamiliar().getPessoasFisica().getCategoriaCNH());
            	  }

            	  if(coluna.getNome().equals("dataVencimentoCnh")) {
            		  if(Objects.nonNull(rv.getFamiliar().getPessoasFisica().getVencimentoCNH())) {
            			  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Java8DateUtil.getLocalDateFormater(rv.getFamiliar().getPessoasFisica().getVencimentoCNH().toLocalDate()));
            		  }else {
            			  dataRow.createCell(indexDados.getAndIncrement()).setCellValue("");
            		  }
            	  }
            	  
            	  if(coluna.getNome().equals("numeroCarteiraTrabalho")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(rv.getFamiliar().getPessoasFisica().getCts());
            	  }
            	  
            	  if(coluna.getNome().equals("numeroSerie")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(rv.getFamiliar().getPessoasFisica().getSerieCtps());
            	  }
            	  
            	  if(coluna.getNome().equals("ufCarteiraTrabalho")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(rv.getFamiliar().getPessoasFisica().getUfCTS());
            	  }
			});		
    	}	
		
	}

	private void preencherEscolaridadeFamiliar(List<GrupoDadosExportar> gf, AtomicInteger indexDados, Row dataRow, ResponsaveisAlunoTO rv) {
    	Optional<GrupoDadosExportar> grupoDados = gf.stream().filter(p -> p.getNomeGrupo().equals("escolaridade")).findFirst();
    	if(grupoDados.isPresent()) {
    		grupoDados.get().getColunas()
    		                   .stream()
                               .filter(coluna -> coluna.isExportar())
                               .forEach(coluna -> {
                            	   
            	  if(coluna.getNome().equals("descricao")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(rv.getFamiliar().getPessoasFisica().getEscolaridade());
            	  }
            	  
            	  if(coluna.getNome().equals("nivel")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(rv.getFamiliar().getPessoasFisica().getNivelEscolaridade());
            	  }
            	  
            	  if(coluna.getNome().equals("grauInstrucao")) {
            		  if(Objects.nonNull(rv.getFamiliar().getPessoasFisica().getGrausInstrucao())) {
            			  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(rv.getFamiliar().getPessoasFisica().getGrausInstrucao().getDescricao());
            		  }else {
            			  dataRow.createCell(indexDados.getAndIncrement()).setCellValue("");
            		  }
            	  }
			});		
    	}
	}

	private void preencherResponsavelFamiliar(List<GrupoDadosExportar> gf, AtomicInteger indexDados, Row dataRow, ResponsaveisAlunoTO rv) {
    	Optional<GrupoDadosExportar> grupoDados = gf.stream().filter(p -> p.getNomeGrupo().equals("responsavel")).findFirst();
    	if(grupoDados.isPresent()) {
    		grupoDados.get().getColunas()
    		                   .stream()
                               .filter(coluna -> coluna.isExportar())
                               .forEach(coluna -> {
                            	   
                  if(coluna.getNome().equals("dataVinculacao")) {
                	  if(Objects.nonNull(rv.getDataVinculacao())) {
                		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Java8DateUtil.getLocalDateFormater(rv.getDataVinculacao().toLocalDate()));
                	  }else {
                		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue("");
                	  }
                  }
                  
                  if(coluna.getNome().equals("dataDesvinculacao")) {
                	  if(Objects.nonNull(rv.getDataDesvinculacao())) {
                		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Java8DateUtil.getLocalDateFormater(rv.getDataDesvinculacao().toLocalDate()));
                	  }else {
                		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue("");
                	  }
                  }
            	  
                  if(coluna.getNome().equals("beneficiarioMesmoEndereco")) {
                	  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(rv.getMesmoEnderResponsavel());
                  }

                  if(coluna.getNome().equals("autorizaLevarBuscar")) {
                	  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(rv.getTransportaAluno());
                  }
                  
                  if(coluna.getNome().equals("temTutela")) {
                	  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(rv.getTutelaAluno());
                  }
                  
                  if(coluna.getNome().equals("responsavelFinanceiro")) {
                	  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(rv.getResponsavelFinanceiroPeloAluno());
                  }
                  
                  if(coluna.getNome().equals("descricaoGrauParentesco")) {
                	  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(rv.getDescGrauParentesco());
                  }
                  
                  if(coluna.getNome().equals("descricaoDesligamento")) {
                	  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(rv.getDescDesligamento());
                  }
			});		
    	}
	}

	private void preencherParentescoFamiliar(List<GrupoDadosExportar> gf, AtomicInteger indexDados, Row dataRow, ResponsaveisAlunoTO rv) {
    	Optional<GrupoDadosExportar> grupoDados = gf.stream().filter(p -> p.getNomeGrupo().equals("parentesco")).findFirst();
    	if(grupoDados.isPresent()) {
    		grupoDados.get().getColunas()
    		                   .stream()
                               .filter(coluna -> coluna.isExportar())
                               .forEach(coluna -> {
                            	   
                  if(coluna.getNome().equals("dataCadastro")) {
                	  if(Objects.nonNull(rv.getFamiliar().getDataCadastro())) {
                		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Java8DateUtil.getLocalDateFormater(rv.getFamiliar().getDataCadastro().toLocalDate()));
                	  }else {
                		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue("");
                	  }
                  }
                  
                  if(coluna.getNome().equals("dataDesligamento")) {
                	  if(Objects.nonNull(rv.getFamiliar().getDataDesligamento())) {
                		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Java8DateUtil.getLocalDateFormater(rv.getFamiliar().getDataDesligamento().toLocalDate()));
                	  }else {
                		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue("");
                	  }
                  }
            	  
                  if(coluna.getNome().equals("situacaoParentesco")) {
                	  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(rv.getFamiliar().getSituacaoParentesco());
                  }

                  if(coluna.getNome().equals("descricaoGrauParentesco")) {
                	  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(rv.getFamiliar().getDescGrauParentesco());
                  }
                  
                  if(coluna.getNome().equals("descricaoDesligamento")) {
                	  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(rv.getFamiliar().getDescDesligamento());
                  }
                  
                  if(coluna.getNome().equals("outrasInformacoes")) {
                	  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(rv.getFamiliar().getDescOutrasInformacoes());
                  }
			});		
    	}

	}

	private void preencherDadosPessoaisFamiliar(List<GrupoDadosExportar> gf, AtomicInteger indexDados, Row dataRow, ResponsaveisAlunoTO rv) {
    	Optional<GrupoDadosExportar> grupoDados = gf.stream().filter(p -> p.getNomeGrupo().equals("dados_pessoais")).findFirst();
    	if(grupoDados.isPresent()) {
    		grupoDados.get().getColunas()
    		                   .stream()
                               .filter(coluna -> coluna.isExportar())
                               .forEach(coluna -> {
                            	   
                  if(coluna.getNome().equals("nomeCompleto")) {
                	  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(rv.getFamiliar().getPessoasFisica().getNome());
                  }
            	  
                  if(coluna.getNome().equals("dataNascimento")) {
                	  if(Objects.nonNull(rv.getFamiliar().getPessoasFisica().getDataNascimento())) {
                		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Java8DateUtil.getLocalDateFormater(rv.getFamiliar().getPessoasFisica().getDataNascimento().toLocalDate()));
                	  }else {
                		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue("");
                	  }
                  }
            	  
                  if(coluna.getNome().equals("cidadeNaturalidade")) {
                	  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(rv.getFamiliar().getPessoasFisica().getCidadeNaturalidade());
                  }

                  if(coluna.getNome().equals("ufNaturalidade")) {
                	  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(rv.getFamiliar().getPessoasFisica().getUfNascimento());
                  }
                  
                  if(coluna.getNome().equals("sexo")) {
                	  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(rv.getFamiliar().getPessoasFisica().getSexo());
                  }
                  
                  if(coluna.getNome().equals("racaCor")) {
                	  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(rv.getFamiliar().getPessoasFisica().getRaca());
                  }
                  
                  if(coluna.getNome().equals("nomeMae")) {
                	  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(rv.getFamiliar().getPessoasFisica().getNomeMae());
                  }
                  
                  if(coluna.getNome().equals("nomePai")) {
                	  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(rv.getFamiliar().getPessoasFisica().getNomePai());
                  }
                  
                  if(coluna.getNome().equals("estadoCivil")) {
                	  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(rv.getFamiliar().getPessoasFisica().getEstadoCivil());
                  }
                  
                  if(coluna.getNome().equals("tipoSangue")) {
                	  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(rv.getFamiliar().getPessoasFisica().getTipoSangue());
                  }
                  
                  if(coluna.getNome().equals("cep")) {
                	  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(rv.getFamiliar().getPessoasFisica().getCep());
                  }
                  
                  if(coluna.getNome().equals("endereco")) {
                	  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(rv.getFamiliar().getPessoasFisica().getEndereco());
                  }
                  
                  if(coluna.getNome().equals("cidade")) {
                	  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(rv.getFamiliar().getPessoasFisica().getCidade());
                  }
                  
                  if(coluna.getNome().equals("bairro")) {
                	  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(rv.getFamiliar().getPessoasFisica().getBairro());
                  }
                  
                  if(coluna.getNome().equals("ufEndereco")) {
                	  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(rv.getFamiliar().getPessoasFisica().getUf());
                  }
                  
                  if(coluna.getNome().equals("pontoReferncia")) {
                	  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(rv.getFamiliar().getPessoasFisica().getPontoReferencia());
                  }
                  
                  if(coluna.getNome().equals("telefoneResidencial")) {
                	  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(rv.getFamiliar().getPessoasFisica().getTelefoneResidencial());
                  }
                  
                  if(coluna.getNome().equals("celular")) {
                	  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(rv.getFamiliar().getPessoasFisica().getCelular());
                  }
                  
                  if(coluna.getNome().equals("email")) {
                	  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(rv.getFamiliar().getPessoasFisica().getEmail());
                  }
                  
                  if(coluna.getNome().equals("autorizoRecebimentoEmail")) {
                	  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(rv.getFamiliar().getPessoasFisica().getAutorizaEmail());
                  }
			});		
    	}
	}

	
	
	private void preencherDadosPessoaisAluno(List<GrupoDadosExportar> ga, AtomicInteger indexDados, Row dataRow, AlunoTO alunoTO) {
    	Optional<GrupoDadosExportar> grupoDados = ga.stream().filter(p -> p.getNomeGrupo().equals("dados_pessoais")).findFirst();
    	if(grupoDados.isPresent()) {
    		grupoDados.get().getColunas()
    		                   .stream()
                               .filter(coluna -> coluna.isExportar())
                               .forEach(coluna -> {
                            	   
                  if(coluna.getNome().equals("nomeCompleto")) {
                	  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(alunoTO.getPessoaFisica().getNome());
                  }
            	  
                  if(coluna.getNome().equals("dataNascimento")) {
                	  if(Objects.nonNull(alunoTO.getPessoaFisica().getDataNascimento())) {
                		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Java8DateUtil.getLocalDateFormater(alunoTO.getPessoaFisica().getDataNascimento().toLocalDate()));
                	  }else {
                		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue("");
                	  }
                  }
            	  
                  if(coluna.getNome().equals("cidadeNaturalidade")) {
                	  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(alunoTO.getPessoaFisica().getCidadeNaturalidade());
                  }

                  if(coluna.getNome().equals("uf")) {
                	  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(alunoTO.getPessoaFisica().getUfNascimento());
                  }
                  
                  if(coluna.getNome().equals("sexo")) {
                	  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(alunoTO.getPessoaFisica().getSexo());
                  }
                  
                  if(coluna.getNome().equals("racaCor")) {
                	  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(alunoTO.getPessoaFisica().getRaca());
                  }
                  
                  if(coluna.getNome().equals("nomeMae")) {
                	  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(alunoTO.getPessoaFisica().getNomeMae());
                  }
                  
                  if(coluna.getNome().equals("nomePai")) {
                	  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(alunoTO.getPessoaFisica().getNomePai());
                  }
                  
                  if(coluna.getNome().equals("estadoCivil")) {
                	  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(alunoTO.getPessoaFisica().getEstadoCivil());
                  }
                  
                  if(coluna.getNome().equals("tipoSangue")) {
                	  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(alunoTO.getPessoaFisica().getTipoSangue());
                  }
                  
                  if(coluna.getNome().equals("cep")) {
                	  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(alunoTO.getPessoaFisica().getCep());
                  }
                  
                  if(coluna.getNome().equals("endereco")) {
                	  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(alunoTO.getPessoaFisica().getEndereco());
                  }
                  
                  if(coluna.getNome().equals("cidade")) {
                	  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(alunoTO.getPessoaFisica().getCidade());
                  }
                  
                  if(coluna.getNome().equals("bairro")) {
                	  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(alunoTO.getPessoaFisica().getBairro());
                  }
                  
                  if(coluna.getNome().equals("ufEndereco")) {
                	  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(alunoTO.getPessoaFisica().getUf());
                  }
                  
                  if(coluna.getNome().equals("pontoReferncia")) {
                	  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(alunoTO.getPessoaFisica().getPontoReferencia());
                  }
                  
                  if(coluna.getNome().equals("telefoneResidencial")) {
                	  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(alunoTO.getPessoaFisica().getTelefoneResidencial());
                  }
                  
                  if(coluna.getNome().equals("celular")) {
                	  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(alunoTO.getPessoaFisica().getCelular());
                  }
                  
                  if(coluna.getNome().equals("email")) {
                	  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(alunoTO.getPessoaFisica().getEmail());
                  }
                  
                  if(coluna.getNome().equals("autorizoRecebimentoEmail")) {
                	  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(alunoTO.getPessoaFisica().getAutorizaEmail());
                  }
			});		
    	}	
	}
	
	private void preencherAdmissaoAluno(List<GrupoDadosExportar> ga, AtomicInteger indexDados, Row dataRow, AlunoTO alunoTO) {
    	Optional<GrupoDadosExportar> grupoDados = ga.stream().filter(p -> p.getNomeGrupo().equals("admissao")).findFirst();
    	if(grupoDados.isPresent()) {
    		grupoDados.get().getColunas()
    		                   .stream()
                               .filter(coluna -> coluna.isExportar())
                               .forEach(coluna -> {
                            	   
	        	  if(coluna.getNome().equals("matricula")) {
	        		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(alunoTO.getMatriculaAluno());
	        	  }
	        	  
	        	  if(coluna.getNome().equals("unidade")) {
	        		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(alunoTO.getUnidade().getNomeUnidade());
	        	  }
	        	  
	        	  if(coluna.getNome().equals("nivelTurma")) {
	        		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(alunoTO.getNivelTurma().getDescricao());
	        	  }
            	  
	        	  if(coluna.getNome().equals("dataEntrada")) {
	        		  if(Objects.nonNull(alunoTO.getDataEntrada())) {
	        			  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Java8DateUtil.getLocalDateFormater(alunoTO.getDataEntrada().toLocalDate()));
	        		  }else {
	        			  dataRow.createCell(indexDados.getAndIncrement()).setCellValue("");
	        		  }
	        	  }
            	  
	        	  if(coluna.getNome().equals("dataDesligamento")) {
	        		  if(Objects.nonNull(alunoTO.getDataDesligamento())) {
	        			  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Java8DateUtil.getLocalDateFormater(alunoTO.getDataDesligamento().toLocalDate()));
	        		  }else {
	        			  dataRow.createCell(indexDados.getAndIncrement()).setCellValue("");
	        		  }
	        	  }
            	  
	        	  if(coluna.getNome().equals("moraComOsPais")) {
	        		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(alunoTO.getMoraPais());
	        	  }
	        	  
	        	  if(coluna.getNome().equals("paisSaoCasados")) {
	        		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(alunoTO.getPaisCasados());
	        	  }
	        	  
	        	  if(coluna.getNome().equals("alunoMatriculadoEscolaPublica")) {
	        		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(alunoTO.getMatriculadoEscPub());
	        	  }
	        	  
	        	  if(coluna.getNome().equals("alunoPublicoPrioritario")) {
	        		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(alunoTO.getPublicoPrioritario());
	        	  }
	        	  
	        	  if(coluna.getNome().equals("descPessoaBuscaAlunoEscola")) {
	        		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(alunoTO.getDescBuscaEscola());
	        	  }
	        	  
	        	  if(coluna.getNome().equals("desligamento")) {
	        		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(alunoTO.getDescDesligamento());            	  
	        	  }
	        	  
	        	  if(coluna.getNome().equals("observacao")) {
	        		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(alunoTO.getObservacoes());
	        	  }
            	  
			});		
    	}	
	}
	
	private void preencherEscolaridadeAluno(List<GrupoDadosExportar> ga, AtomicInteger indexDados, Row dataRow, AlunoTO alunoTO) {
    	Optional<GrupoDadosExportar> grupoDados = ga.stream().filter(p -> p.getNomeGrupo().equals("escolaridade")).findFirst();
    	if(grupoDados.isPresent()) {
    		grupoDados.get().getColunas()
    		                   .stream()
                               .filter(coluna -> coluna.isExportar())
                               .forEach(coluna -> {
                            	   
            	  if(coluna.getNome().equals("descricao")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(alunoTO.getPessoaFisica().getEscolaridade());
            	  }
            	  
            	  if(coluna.getNome().equals("nivelEscolaridade")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(alunoTO.getPessoaFisica().getNivelEscolaridade());
            	  }
            	  
            	  if(coluna.getNome().equals("grauInstrucao")) {
            		  if(Objects.nonNull(alunoTO.getPessoaFisica().getGrausInstrucao())) {
            			  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(alunoTO.getPessoaFisica().getGrausInstrucao().getDescricao());
            		  }else {
            			  dataRow.createCell(indexDados.getAndIncrement()).setCellValue("");
            		  }
            	  }
            	  
            	  if(coluna.getNome().equals("tipoEscolaRegular")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(alunoTO.getPessoaFisica().getTipoEscola());
            	  }
            	  
            	  if(coluna.getNome().equals("TurnoEscolaRegular")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(alunoTO.getPessoaFisica().getTurno());
            	  }
            	  
            	  if(coluna.getNome().equals("identificacaoEscolaFrequentada")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(alunoTO.getPessoaFisica().getEscola());
            	  }
            	  
            	  if(coluna.getNome().equals("nomeCursoEscolaRegular")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(alunoTO.getPessoaFisica().getCursoEscola());
            	  }
            	  
            	  if(coluna.getNome().equals("periodoCursoEscolaRegular")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(alunoTO.getPessoaFisica().getPeriodoEscola());
            	  }
            	  
            	  if(coluna.getNome().equals("serieCursoEscolaRegular")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(alunoTO.getPessoaFisica().getSerieEscola());
            	  }
            	  
            	  if(coluna.getNome().equals("regiaoAdministrativaEscola")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(alunoTO.getPessoaFisica().getRegiaoEscola());            	  
            	  }
			});		
    	}	
	}
	
	private void preencherDocumentosAluno(List<GrupoDadosExportar> ga, AtomicInteger indexDados, Row dataRow, AlunoTO alunoTO) {
    	Optional<GrupoDadosExportar> grupoDados = ga.stream().filter(p -> p.getNomeGrupo().equals("documentos")).findFirst();
    	if(grupoDados.isPresent()) {
    		grupoDados.get().getColunas()
    		                   .stream()
                               .filter(coluna -> coluna.isExportar())
                               .forEach(coluna -> {
                            	   
            	  if(coluna.getNome().equals("cpf")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(alunoTO.getPessoaFisica().getCpf());           	  
            	  }
            	  
            	  if(coluna.getNome().equals("nis")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(alunoTO.getPessoaFisica().getNis());
            	  }
            	  
            	  if(coluna.getNome().equals("pis")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(alunoTO.getPessoaFisica().getNumeroPisPasep());
            	  }
            	  
            	  if(coluna.getNome().equals("identidade")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(alunoTO.getPessoaFisica().getIdentidade());
            	  }
            	  
            	  if(coluna.getNome().equals("orgaoExpedidor")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(alunoTO.getPessoaFisica().getOrgaoCi());
            	  }
            	  
            	  if(coluna.getNome().equals("ufCI")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(alunoTO.getPessoaFisica().getUfCi());
            	  }
            	  
            	  if(coluna.getNome().equals("dataEmissao")) {
            		  if(Objects.nonNull(alunoTO.getPessoaFisica().getDataEmissaoCI())) {
            			  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Java8DateUtil.getLocalDateFormater(alunoTO.getPessoaFisica().getDataEmissaoCI().toLocalDate()));
            		  }else {
            			  dataRow.createCell(indexDados.getAndIncrement()).setCellValue("");
            		  }
            	  }
            	  
            	  if(coluna.getNome().equals("tituloEleitoral")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(alunoTO.getPessoaFisica().getTituloEleitor());
            	  }
            	  
            	  if(coluna.getNome().equals("zona")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(alunoTO.getPessoaFisica().getZonaTitulo());
            	  }
            	  
            	  if(coluna.getNome().equals("sessao")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(alunoTO.getPessoaFisica().getSessaoTitulo());
            	  }
            	  
            	  if(coluna.getNome().equals("numeroReservista")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(alunoTO.getPessoaFisica().getNumeroReservista());
            	  }
            	  
            	  if(coluna.getNome().equals("numeroRegiaoMilitar")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(alunoTO.getPessoaFisica().getRegiaoMilitarReservista());
            	  }
            	  
            	  if(coluna.getNome().equals("ufReservista")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(alunoTO.getPessoaFisica().getUfRegiaoMilitar());
            	  }
            	  
            	  if(coluna.getNome().equals("cnh")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(alunoTO.getPessoaFisica().getNumeroCNH());
            	  }
            	  
            	  if(coluna.getNome().equals("categoriaCnh")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(alunoTO.getPessoaFisica().getCategoriaCNH());
            	  }

            	  if(coluna.getNome().equals("dataVencimentoCnh")) {
            		  if(Objects.nonNull(alunoTO.getPessoaFisica().getVencimentoCNH())) {
            			  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Java8DateUtil.getLocalDateFormater(alunoTO.getPessoaFisica().getVencimentoCNH().toLocalDate()));
            		  }else {
            			  dataRow.createCell(indexDados.getAndIncrement()).setCellValue("");
            		  }
            	  }
            	  
            	  if(coluna.getNome().equals("numeroCarteiraTrabalho")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(alunoTO.getPessoaFisica().getCts());
            	  }
            	  
            	  if(coluna.getNome().equals("numeroSerie")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(alunoTO.getPessoaFisica().getSerieCtps());
            	  }
            	  
            	  if(coluna.getNome().equals("ufCarteiraTrabalho")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(alunoTO.getPessoaFisica().getUfCTS());
            	  }
			});		
    	}	
	}
		
	private void preencherOutrasInformacoesAluno(List<GrupoDadosExportar> ga, AtomicInteger indexDados, Row dataRow, AlunoTO alunoTO) {
    	Optional<GrupoDadosExportar> grupoDados = ga.stream().filter(p -> p.getNomeGrupo().equals("outras_informacoes")).findFirst();
    	if(grupoDados.isPresent()) {
    		grupoDados.get().getColunas()
    		                   .stream()
                               .filter(coluna -> coluna.isExportar())
                               .forEach(coluna -> {
                            	   
            	  if(coluna.getNome().equals("possuiDeficiencia")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(alunoTO.getPessoaFisica().getEhDeficiente());           	  
            	  }
            	  
            	  if(coluna.getNome().equals("descricaoDeficiencia")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(alunoTO.getPessoaFisica().getDescricaoDeficiencia());
            	  }
            	  
            	  if(coluna.getNome().equals("descricaoProblemaSaude")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(alunoTO.getPessoaFisica().getProblemaSaude());
            	  }
            	  
            	  if(coluna.getNome().equals("descricaoMedicamentosControlados")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(alunoTO.getPessoaFisica().getMedicamentosControlados());
            	  }
            	  
            	  if(coluna.getNome().equals("condicaoMoradia")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(alunoTO.getPessoaFisica().getCondicoesMoradia().getDescricao());
            	  }
            	  
            	  if(coluna.getNome().equals("descricaoCondicaoMoradia")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(alunoTO.getPessoaFisica().getCondicaoMoradia());
            	  }
            	  
            	  if(coluna.getNome().equals("jaAtendidoEmOutroOrgao")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(alunoTO.getAtendidoOrgaoRede());
            	  }
            	  
            	  if(coluna.getNome().equals("descricaoRelevanteAtendimentoOutroOrgao")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(alunoTO.getPessoaFisica().getDescricaoPessoaFisicaAtendidoOrgaoRede());
            	  }
            	  
            	  if(coluna.getNome().equals("descricaoOutrosBeneficiosFamilia")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(alunoTO.getPessoaFisica().getOutrosBenSoc());
            	  }
            	  
            	  if(coluna.getNome().equals("descricaoRelevanteRedeApoioSocial")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(alunoTO.getPessoaFisica().getRedeApoioSocial());
            	  }
            	  
            	  if(coluna.getNome().equals("descricaoRedeApoioSocial")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(alunoTO.getPessoaFisica().getRedeApSocRelev());
            	  }
            	  
            	  if(coluna.getNome().equals("valorOutrosBeneficiosSociais")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(alunoTO.getPessoaFisica().getValorOutrosBenerficiosSoc());
            	  }
            	  
            	  if(coluna.getNome().equals("identificacaoOrigemRenda")) {
            		  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(alunoTO.getPessoaFisica().getOrigemRendaFamiliar());
            	  }
            	  
            	  if(coluna.getNome().equals("origemBeneficio")) {
            		  if(Objects.nonNull(alunoTO.getEncaminhamentos()) && !alunoTO.getEncaminhamentos().isEmpty()) {
            			  alunoTO.getEncaminhamentos().sort(Comparator.comparing(EncaminhaAlunosTO::getDataEncaminhaAluno).reversed());
            			  EncaminhaAlunosTO encaminhamento = alunoTO.getEncaminhamentos().get(0);

        				  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(encaminhamento.getEntidadeSocial().getEmpresa().getNomeFantasia());
        				  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(Java8DateUtil.getLocalDateFormater(encaminhamento.getDataEncaminhaAluno().toLocalDate()));
        				  dataRow.createCell(indexDados.getAndIncrement()).setCellValue(encaminhamento.getDescricao());
            		  }
            	  }
			});		
    	}	
	}
	
}

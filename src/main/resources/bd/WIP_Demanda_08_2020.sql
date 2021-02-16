-------------------------------------------------------------------------------
ALTER TABLE unidades  DROP CONSTRAINT arquivo_unidade;

update unidades
   set id_arquivo_logomarca = ( select id_arquivo_metadado from arquivos a where a.id_arquivo = id_arquivo_logomarca );
  
ALTER TABLE unidades
  ADD CONSTRAINT arquivo_unidade FOREIGN KEY (id_arquivo_logomarca)
  REFERENCES arquivos_metadados (id_arquivo_metadado)
  ON UPDATE NO ACTION
  ON DELETE NO ACTION;
  
COMMIT;


-------------------------------------------------------------------------------
ALTER TABLE instituicoes  DROP CONSTRAINT arquivo_instituicao_fk;

update instituicoes
   set id_arquivo_logomarca = ( select id_arquivo_metadado from arquivos a where a.id_arquivo = id_arquivo_logomarca );
  
ALTER TABLE instituicoes
  ADD CONSTRAINT arquivo_instituicao_fk FOREIGN KEY (id_arquivo_logomarca)
  REFERENCES arquivos_metadados (id_arquivo_metadado)
  ON UPDATE NO ACTION
  ON DELETE NO ACTION;
  
COMMIT;



-------------------------------------------------------------------------------
ALTER TABLE pessoas_fisicas  DROP CONSTRAINT arquivo_pessoa_fisica_fk;

update pessoas_fisicas
   set id_arquivo_foto = ( select id_arquivo_metadado from arquivos a where a.id_arquivo = id_arquivo_foto );
  
ALTER TABLE pessoas_fisicas
  ADD CONSTRAINT arquivo_pessoa_fisica_fk FOREIGN KEY (id_arquivo_foto)
  REFERENCES arquivos_metadados (id_arquivo_metadado)
  ON UPDATE NO ACTION
  ON DELETE NO ACTION;
  
COMMIT;


-------------------------------------------------------------------------------

DROP TABLE IF EXISTS anexos_acao_planejamento CASCADE;
create table anexos_acao_planejamento (
  id_anexo_acao_planejamento numeric(10) not null,
  id_acao numeric(10) not null,
  id_arquivo_metadado numeric(10) not null
);

ALTER TABLE anexos_acao_planejamento
   ADD CONSTRAINT id_anexo_acao_planejamento_pk
   PRIMARY KEY (id_anexo_acao_planejamento);
   
ALTER TABLE anexos_acao_planejamento
  ADD CONSTRAINT id_acao_fk FOREIGN KEY (id_acao)
  REFERENCES acoes (id_acao)
  ON UPDATE NO ACTION
  ON DELETE NO ACTION;
  
ALTER TABLE anexos_acao_planejamento
  ADD CONSTRAINT id_arquivo_metadado_fk FOREIGN KEY (id_arquivo_metadado)
  REFERENCES arquivos_metadados (id_arquivo_metadado)
  ON UPDATE NO ACTION
  ON DELETE NO ACTION;
  

DROP SEQUENCE IF EXISTS sq_id_anexo_acao_planejamento;
CREATE SEQUENCE sq_id_anexo_acao_planejamento
       INCREMENT BY 1
       MINVALUE 1
       CACHE 1
       NO CYCLE;

COMMIT;

-------------------------------------------------------------------------------

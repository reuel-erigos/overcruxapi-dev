--NOVOS MODULOS

INSERT INTO public.modulos (id_modulo, ds_modulo, nm_modulo, id_usuario_apl, modulo_pai) VALUES(nextval('sq_id_modulo'), 'Tipos Atividades', 'TIPOS_ATIVIDADES', 19, 199);
INSERT INTO public.modulos (id_modulo, ds_modulo, nm_modulo, id_usuario_apl, modulo_pai) VALUES(nextval('sq_id_modulo'), 'Situações ex-alunos', 'SITUACOES_EX_ALUNOS', 19, 98);
INSERT INTO public.modulos (id_modulo, ds_modulo, nm_modulo, id_usuario_apl, modulo_pai) VALUES(nextval('sq_id_modulo'), 'Motivos Desligamentos', 'MOTIVOS_DESLIGAMENTOS', 19, 199);
INSERT INTO public.modulos (id_modulo, ds_modulo, nm_modulo, id_usuario_apl, modulo_pai) VALUES(nextval('sq_id_modulo'), 'Público Prioritário', 'TIPOS_PUBLICOS_PRIORITARIOS', 19, 199);
INSERT INTO public.modulos (id_modulo, ds_modulo, nm_modulo, id_usuario_apl, modulo_pai) VALUES(nextval('sq_id_modulo'), 'Tabelas de Referência - Programas e Projetos', 'TB_REFERENCIA_PROGRAMAS_PROJETOS', 3, 159);

--Verifcar o id gerado no script anterior - TB_REFERENCIA_PROGRAMAS_PROJETOS.. esse id será o modulo pai nesse script, no caso de desenvolvimento o id foi 216.
INSERT INTO public.modulos (id_modulo, ds_modulo, nm_modulo, id_usuario_apl, modulo_pai) VALUES(nextval('sq_id_modulo'), 'Tipos Doadores', 'TIPOS_DOADORES', 3, 216);

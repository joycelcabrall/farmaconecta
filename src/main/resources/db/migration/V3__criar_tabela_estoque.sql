CREATE TABLE estoque (

                         id BIGSERIAL PRIMARY KEY,

                         ubs_id BIGINT NOT NULL,

                         medicamento_id BIGINT NOT NULL,

                         lote VARCHAR(100) NOT NULL,

                         validade DATE NOT NULL,

                         quantidade INTEGER NOT NULL DEFAULT 0,

                         CONSTRAINT fk_estoque_ubs
                             FOREIGN KEY (ubs_id)
                                 REFERENCES ubs(id),

                         CONSTRAINT fk_estoque_medicamento
                             FOREIGN KEY (medicamento_id)
                                 REFERENCES medicamentos(id),

                         CONSTRAINT ck_estoque_quantidade
                             CHECK (quantidade >= 0),

                         CONSTRAINT uk_estoque_ubs_medicamento_lote_validade
                             UNIQUE (
                                     ubs_id,
                                     medicamento_id,
                                     lote,
                                     validade
                                 )
);
CREATE TABLE medicamentos (
                              id BIGSERIAL PRIMARY KEY,

                              nome VARCHAR(150) NOT NULL,

                              principio_ativo VARCHAR(150) NOT NULL,

                              concentracao VARCHAR(100),

                              forma_farmaceutica VARCHAR(100),

                              unidade_medida VARCHAR(50),

                              ativo BOOLEAN NOT NULL DEFAULT TRUE
);
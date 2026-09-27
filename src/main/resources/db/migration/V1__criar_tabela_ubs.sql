CREATE TABLE ubs (
                     id BIGSERIAL PRIMARY KEY,

                     nome VARCHAR(150) NOT NULL,

                     cnes VARCHAR(7) NOT NULL UNIQUE,

                     endereco VARCHAR(255),

                     cidade VARCHAR(100),

                     uf VARCHAR(2) NOT NULL,

                     ativa BOOLEAN NOT NULL DEFAULT TRUE
);
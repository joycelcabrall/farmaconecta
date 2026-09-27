package br.com.farmaconecta.domain.exception;

public class RecursoNaoEncontradoException
        extends RegraDeNegocioException {

    public RecursoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}
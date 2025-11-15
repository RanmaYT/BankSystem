package Exceptions;

public class OperacaoNaoConcluidaException extends RuntimeException {
    public OperacaoNaoConcluidaException(String message) {
        super(message);
    }
}

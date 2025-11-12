package State;

public interface IContaState {
    boolean podeCreditar();
    boolean podeDebitar();
    boolean podeDeletarConta();
    boolean podePegarExtrato();
    String getStateName();
}

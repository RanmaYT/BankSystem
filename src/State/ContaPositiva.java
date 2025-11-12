package State;

public class ContaPositiva implements IContaState{
    @Override
    public boolean podeCreditar() {
        return true;
    }

    @Override
    public boolean podeDebitar() {
        return true;
    }

    @Override
    public boolean podeDeletarConta() {
        return true;
    }

    @Override
    public boolean podePegarExtrato() {
        return true;
    }

    @Override
    public String getStateName(){
        return "POSITIVA";
    }
}

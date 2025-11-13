package State;

public class ContaNegativada implements IContaState{
    @Override
    public boolean podeCreditar() {
        return true;
    }

    @Override
    public boolean podeDebitar() {
        System.out.println("A conta está com saldo negativo, permissão negada");
        return false;
    }

    @Override
    public boolean podeDeletarConta() {
        System.out.println("A conta está com saldo negativo, permissão negada");
        return false;
    }

    @Override
    public boolean podePegarExtrato() {
        return true;
    }

    @Override
    public String getStateName(){
        return "negativada";
    }
}

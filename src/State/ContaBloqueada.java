package State;

public class ContaBloqueada implements IContaState{
    @Override
    public boolean podeCreditar() {
        System.out.println("A conta está bloqueada, permissão negada");
        return false;
    }

    @Override
    public boolean podeDebitar() {
        System.out.println("A conta está bloqueada, permissão negada");
        return false;
    }

    @Override
    public boolean podeDeletarConta() {
        System.out.println("A conta está bloqueada, permissão negada");
        return false;
    }

    @Override
    public boolean podePegarExtrato() {
        System.out.println("A conta está bloqueada, permissão negada");
        return false;
    }

    @Override
    public String getStateName(){
        return "BLOQUEADA";
    }
}

package State;

public class ContaEncerrada implements IContaState{
    @Override
    public boolean podeCreditar() {
        System.out.println("Essa conta já foi encerrada, impossível realizar operações!");
        return false;
    }

    @Override
    public boolean podeDebitar() {
        System.out.println("Essa conta já foi encerrada, impossível realizar operações!");
        return false;
    }

    @Override
    public boolean podeDeletarConta() {
        System.out.println("Essa conta já foi encerrada, impossível realizar operações!");
        return false;
    }

    @Override
    public boolean podePegarExtrato() {
        return true;
    }

    @Override
    public String getStateName(){
        return "encerrada";
    }
}

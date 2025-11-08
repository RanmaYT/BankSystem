package Model;

import SingletonRepositories.IStorable;
import State.ContaNegativada;
import State.ContaPositiva;
import State.IContaState;

public abstract class Conta implements IStorable {
    private int id;
    private static int idCount = 1;

    private double saldo;
    private Usuario titularConta;
    private ExtratoBancario extrato;
    private IContaState estadoConta;

    public Conta(double saldo, Usuario titularConta, ExtratoBancario extrato) {
        this.saldo = saldo;
        this.titularConta = titularConta;
        this.extrato = extrato;
        this.estadoConta = new ContaPositiva(this);

        this.id = idCount;
        idCount++;
    }

    public void mudarEstado(IContaState novoEstado){
        estadoConta = novoEstado;
    }

    public void creditar(double valor){
        if(!estadoConta.podeCreditar()) { return; }

        saldo += valor;

        if(saldo >= 0 && estadoConta.getClass() != ContaPositiva.class) {
            System.out.println("A conta agora está positivada");
            mudarEstado(new ContaPositiva(this));
        }
    }

    public void debitar(double valor){
        if(!estadoConta.podeDebitar()) { return; }

        saldo -= valor;

        if(saldo < 0) {
            System.out.println("A conta agora está negativada");
            mudarEstado(new ContaNegativada(this));
        }
    }

    public void deletarConta(){
        if(!estadoConta.podeDeletarConta()) { return; }

        System.out.println("Deletando conta de: " + titularConta.getNome());
    }

    public double getSaldo(){
        return saldo;
    }

    public Usuario getTitularConta(){
        return titularConta;
    }

    public abstract String converterParaStringArmazenavel();
    public int getId() { return id; }
}

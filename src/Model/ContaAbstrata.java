package Model;

import SingletonRepositories.ContaRepository;
import SingletonRepositories.IStorable;
import State.ContaEncerrada;
import State.ContaNegativada;
import State.ContaPositiva;
import State.IContaState;

public abstract class ContaAbstrata implements IStorable {
    private double saldo;
    private String tipoConta;
    private String cpfTitular;
    private String nomeEstado;
    private transient IContaState estadoConta;

    public ContaAbstrata(double saldo, String cpfTitular, IContaState state, String tipoConta) {
        this.saldo = saldo;
        this.cpfTitular = cpfTitular;
        this.estadoConta = state;
        this.nomeEstado = state.getStateName();
        this.tipoConta = tipoConta;
    }

    public void mudarEstado(IContaState novoEstado){
        estadoConta = novoEstado;
        nomeEstado = estadoConta.getStateName();

        // GAMBIARRA MUITO LOUCA
        ContaRepository.getInstance().atualizarLinha(cpfTitular, this);
    }

    public void creditar(double valor){
        if(!estadoConta.podeCreditar()) { return; }

        saldo += valor;

        if(saldo >= 0 && !nomeEstado.equals("positiva")) {
            System.out.println("A conta agora está positiva");
            mudarEstado(new ContaPositiva());
        }

        // GAMBIARRA MUITO LOUCA
        ContaRepository.getInstance().atualizarLinha(cpfTitular, this);
    }

    public void debitar(double valor){
        if(!estadoConta.podeDebitar()) { return; }

        saldo -= valor;

        if(saldo < 0 && !nomeEstado.equals("negativada")) {
            System.out.println("A conta agora está negativada");
            mudarEstado(new ContaNegativada());
        }

        // GAMBIARRA MUITO LOUCA
        ContaRepository.getInstance().atualizarLinha(cpfTitular, this);
    }

    public void deletarConta(){
        if(!estadoConta.podeDeletarConta()) { return; }

        if(saldo != 0) {
            System.out.println("A conta deve estar zerada para ser encerrada!");
            return;
        }

        mudarEstado(new ContaEncerrada());
    }

    // Getters e Setters
    public double getSaldo(){
        return saldo;
    }

    public String getCpfTitular() { return cpfTitular; }

    public String getNomeEstado() { return nomeEstado; }

    public String getTipoConta(){ return tipoConta; }

    public IContaState getEstadoConta(){ return estadoConta; }
}

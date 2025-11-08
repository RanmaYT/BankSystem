package Model.Services;

import Factory.ContaFactory;
import Model.Conta;

import Model.ExtratoBancario;
import Model.OperacaoExtratavel;
import Model.Usuario;

import SingletonRepositories.ContaRepository;
import SingletonSession.SessionManager;
import State.ContaBloqueada;
import State.ContaNegativada;
import State.ContaPositiva;
import Strategy.IPaymentStrategy;

public class ContaService {
    private SessionManager sessionManager;
    private ContaRepository contaRepo;

    public ContaService(SessionManager sessionManager, ContaRepository contaRepo) {
        this.sessionManager = sessionManager;
        this.contaRepo = contaRepo;
    }

    public void criarConta(Usuario usuario, ContaFactory contaFactory){
        Conta conta = contaFactory.criarConta(usuario);

        contaRepo.salvar(conta);

        System.out.println("Conta criada e cadastrada com sucesso!");
    }

    public void bloquearConta(Usuario cliente) {
        // TODO: Validar se a conta já está bloqueada

        Conta conta = contaRepo.acharPorTitular(cliente);

        conta.mudarEstado(new ContaBloqueada(conta));
    }

    public void desbloquearConta(Usuario cliente) {
        // TODO: Validar se a conta já está desbloqueada

        Conta conta = contaRepo.acharPorTitular(cliente);

        conta.mudarEstado(conta.getSaldo() >= 0 ? new ContaPositiva(conta) : new ContaNegativada(conta));
    }

    public ExtratoBancario verExtrato(){
        Conta conta = contaRepo.acharPorTitular(sessionManager.getUsuarioLogado());

        System.out.println(conta.getExtrato());

        return conta.getExtrato();
    }

    public double verSaldo(){
        Conta conta = contaRepo.acharPorTitular(sessionManager.getUsuarioLogado());

        return conta.getSaldo();
    }
}

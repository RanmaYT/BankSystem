package Model.Services;

import Factory.ContaFactory;
import Model.ContaAbstrata;

import Model.ExtratoBancario;
import Model.UsuarioAbstrato;

import SingletonRepositories.ContaRepository;
import SingletonRepositories.ExtratoRepository;
import SingletonSession.SessionManager;
import State.ContaBloqueada;
import State.ContaNegativada;
import State.ContaPositiva;

import java.util.ArrayList;

public class ContaService {
    private SessionManager sessionManager;
    private ContaRepository contaRepo;

    public ContaService(SessionManager sessionManager, ContaRepository contaRepo) {
        this.sessionManager = sessionManager;
        this.contaRepo = contaRepo;
    }

    public void criarConta(UsuarioAbstrato usuario, ContaFactory contaFactory){
        ContaAbstrata conta = contaFactory.criarConta(usuario);

        if(conta == null) {
            System.out.println("Falha ao criar conta");
            return;
        }

        contaRepo.salvar(conta);

        // Gambiarra: uso direto do extrato, sem injeção de dependência;
        ExtratoBancario extrato = new ExtratoBancario(new ArrayList<>(), conta.getEmailTitular());
        ExtratoRepository.getInstance().salvar(extrato);

        System.out.println("Conta criada e cadastrada com sucesso!");
    }

    public void bloquearConta(UsuarioAbstrato cliente) {
        // TODO: Validar se a conta já está bloqueada

         ContaAbstrata conta = contaRepo.pegarPorTitular(cliente.getEmail());

         conta.mudarEstado(new ContaBloqueada());
    }

    public void desbloquearConta(UsuarioAbstrato cliente) {
        // TODO: Validar se a conta já está desbloqueada

        ContaAbstrata conta = contaRepo.pegarPorTitular(cliente.getEmail());

        conta.mudarEstado(conta.getSaldo() >= 0 ? new ContaPositiva() : new ContaNegativada());
    }

    // TODO: Transformar o ver saldo em ver informações (Saldo, estado da conta, cheque especial, etc...)
    public double pegarSaldo(){
        ContaAbstrata conta = sessionManager.getContaAtiva();

        return conta.getSaldo();
    }
}

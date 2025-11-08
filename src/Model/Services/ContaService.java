package Model.Services;

import Factory.ContaFactory;
import Model.Conta;

import Model.Usuario;

import SingletonRepositories.ContaRepository;
import SingletonSession.SessionManager;

public class ContaService {
    private SessionManager sessionManager;
    private ContaRepository contaRepo;
    private PagamentoService pagamentoService;

    public ContaService(SessionManager sessionManager, ContaRepository contaRepo, PagamentoService pagamentoService) {
        this.sessionManager = sessionManager;
        this.contaRepo = contaRepo;
        this.pagamentoService = pagamentoService;
    }

    public void criarConta(Usuario usuario, ContaFactory contaFactory){
        Conta conta = contaFactory.criarConta(usuario);

        contaRepo.salvar(conta);

        System.out.println("Conta criada e cadastrada com sucesso!");
    }

    public void sacar(double valor){
        // Pegar a conta no repo
        Conta conta = contaRepo.acharPorId(1);

        // Realizar a operação
        conta.debitar(valor);
    }

    public void depositar(double valor){
        // Pegar a conta no repo
        Conta conta = contaRepo.acharPorId(1);

        // Realizar a operação
        conta.creditar(valor);
    }

    public void realizarPagamento(double valor) {
        // Pegar a conta no repo
        Conta conta = contaRepo.acharPorId(1);

        // Realizar a operação
        pagamentoService.realizarPagamento(conta, valor);
    }
}

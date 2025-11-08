package Model.Services;

import Factory.ContaCorrenteFactory;
import Model.Cliente;
import Model.Usuario;
import SingletonRepositories.UserRepository;

public class AdminService {
    private UserRepository userRepo;
    private ContaService contaService;

    public AdminService(UserRepository userRepo, ContaService contaService) {
        this.userRepo = userRepo;
        this.contaService = contaService;
    }

    public void cadastrarCliente(String nome, String senha, String email, String cpf, double rendaMensal) {
        // TODO: FAZER VALIDAÇÕES (CPF DUPLICADO, EMAIL DUPLICADO...)

        // Criar o objeto do cliente
        Usuario novoCliente = new Cliente(nome, senha, email, cpf, rendaMensal);

        // Salvar esse objeto no hashmap do repositório
        userRepo.salvar(novoCliente);
        System.out.println("Cliente cadastrado com sucesso");

        // Cria uma conta para o cliente
        contaService.criarConta(novoCliente, new ContaCorrenteFactory());
    }

    public void bloquearCliente(int id) {

    }
}
package Model.Services;

import DTOs.UserDTO;
import Factory.ContaFactory;
import Mappers.UsersMapper;
import Model.Cliente;
import Model.Usuario;
import SingletonRepositories.UserRepository;

public class AdminService {
    private UserRepository userRepo;
    private ContaService contaService;
    private UsersMapper usersMapper;

    public AdminService(UserRepository userRepo, ContaService contaService, UsersMapper usersMapper) {
        this.userRepo = userRepo;
        this.contaService = contaService;
        this.usersMapper = usersMapper;
    }

    public void cadastrarCliente(String nome, String senha, String email, String cpf, double rendaMensal, ContaFactory tipoConta) {
        // TODO: FAZER VALIDAÇÕES (CPF DUPLICADO, EMAIL DUPLICADO...)

        // Criar o objeto do cliente
        Usuario novoCliente = new Cliente(nome, senha, email, cpf, rendaMensal);

        // Salvar esse objeto no hashmap do repositório
        userRepo.salvar(novoCliente);
        System.out.println("Cliente cadastrado com sucesso");

        // Cria uma conta para o cliente
        contaService.criarConta(novoCliente, tipoConta);
    }

    public UserDTO getUserInfo(int clienteId){
        // Fica dependente da classe concreta!
        Cliente usuario = (Cliente) userRepo.acharPorId(clienteId);

        UserDTO userDTO = usersMapper.clienteToDTO(usuario);
        return userDTO;
    }

    public void bloquearCliente(int id) {
        Usuario cliente = userRepo.acharPorId(id);

        contaService.bloquearConta(cliente);
    }

    public void desbloquearCliente(int id) {
        Usuario cliente = userRepo.acharPorId(id);

        contaService.desbloquearConta(cliente);
    }
}
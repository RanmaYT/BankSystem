package Model.Services;

import DTOs.UserDTOs.UserDTO;
import Mappers.UsersMapper;
import Model.Cliente;
import Model.UsuarioAbstrato;
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

    public void cadastrarCliente(String nome, String senha, String email, String cpf, double rendaMensal, String tipoConta) {
        // TODO: FAZER SISTEMA DE VALIDAÇÕES (CPF DUPLICADO, EMAIL DUPLICADO...)

        // Criar o objeto do cliente
        UsuarioAbstrato novoCliente = new Cliente(nome, senha, email, cpf, rendaMensal);

        // Salvar esse objeto no hashmap do repositório
        userRepo.salvar(novoCliente);

        // Cria uma conta para o cliente
        contaService.criarConta(novoCliente, tipoConta);
    }

    public UserDTO getUserInfo(String cpf){
        // Fica dependente da classe concreta!
        Cliente usuario = (Cliente) userRepo.pegarPorCpf(cpf);

        UserDTO userDTO = usersMapper.userToDTO(usuario);
        return userDTO;
    }

    public void bloquearCliente(String cpf) {
        UsuarioAbstrato cliente = userRepo.pegarPorCpf(cpf);

        contaService.bloquearConta(cliente);
    }

    public void desbloquearCliente(String cpf) {
        UsuarioAbstrato cliente = userRepo.pegarPorCpf(cpf);

        contaService.desbloquearConta(cliente);
    }
}
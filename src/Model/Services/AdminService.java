package Model.Services;

import DTOs.UserDTOs.UserDTO;
import Factory.ContaFactory.ContaCorrenteFactory;
import Factory.ContaFactory.ContaPoupancaFactory;
import Factory.ContaFactory.IContaFactory;
import Factory.UserFactory.ClienteFactory;
import Factory.UserFactory.IUserFactory;
import Mappers.UsersMapper;
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
        IUserFactory userFactory = new ClienteFactory(rendaMensal);

        // ALERTA: GAMBIARRA QUE RESULTA EM ACOPLAMENTO
        IContaFactory contaFactory = switch (tipoConta) {
            case "Corrente" -> new ContaCorrenteFactory();
            case "Poupança" -> new ContaPoupancaFactory(); //
            default -> null;
        };

        // Retorna caso o tipo de conta seja inválido.
        if(contaFactory == null) {
            throw new IllegalArgumentException("Tipo de conta inválido!");
        }

        // Criar o objeto do cliente
        UsuarioAbstrato novoCliente = userFactory.criarUsuario(nome, senha, email, cpf);

        // Valida o cliente criado
        boolean clienteValido = UserValidationService.usuarioValido(novoCliente, userRepo);

        if(!clienteValido) {
            throw new RuntimeException("CPF ou Email já foram cadastrados");
        }

        // Salvar esse objeto no hashmap do repositório
        userRepo.salvar(novoCliente);

        // Cria uma conta para o cliente
        contaService.criarConta(novoCliente, contaFactory);
    }

    public UserDTO getUserInfo(String cpf){
        UsuarioAbstrato usuario = userRepo.pegarPorCpf(cpf);

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
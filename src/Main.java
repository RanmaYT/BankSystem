import Controller.AdminController;
import Controller.ContaController;
import Model.Services.AdminService;
import Model.Services.ContaService;
import Model.Services.PagamentoService;
import SingletonRepositories.ContaRepository;
import SingletonRepositories.UserRepository;
import SingletonSession.SessionManager;
import Util.InputUtil;

public class Main {
    public static void main(String[] args) {
        // Singletons
        SessionManager sM = SessionManager.getInstance();
        ContaRepository cR = ContaRepository.getInstance();

        // Criando dependencias
        UserRepository userRepo = UserRepository.getInstance();
        ContaService contaService = new ContaService(sM, cR, new PagamentoService());
        AdminService adminService = new AdminService(userRepo, contaService);

        AdminController adminController = new AdminController(adminService, new InputUtil());

        adminController.cadastrarCliente();

        // Seta o usuário logado como o 1° usuário cadastrado
        sM.setUsuarioLogado(UserRepository.getInstance().acharPorId(1));

        System.out.println(adminController.getUserInfo(1));
    }
}

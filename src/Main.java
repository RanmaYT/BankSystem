import Controller.AdminController;
import Controller.ContaController;
import Mappers.ExtratoMapper;
import Mappers.UsersMapper;
import Model.Services.AdminService;
import Model.Services.ContaService;
import Model.Services.MonetaryService;
import Model.Services.PagamentoService;
import SingletonRepositories.ContaRepository;
import SingletonRepositories.UserRepository;
import SingletonSession.SessionManager;
import Strategy.EspeciePayment;
import Util.InputUtil;
import View.Menu;

public class Main {
    public static void main(String[] args) {
        // Singletons
        UserRepository userRepo = UserRepository.getInstance();
        ContaRepository contaRepo = ContaRepository.getInstance();
        SessionManager sessionManager = SessionManager.getInstance();

        // Services
        ContaService contaService = new ContaService(sessionManager, contaRepo, new ExtratoMapper());
        AdminService adminService = new AdminService(userRepo, contaService, new UsersMapper());
        MonetaryService monetaryService = new MonetaryService(contaRepo, sessionManager, new PagamentoService(new EspeciePayment()));

        // Controllers
        AdminController adminController = new AdminController(adminService);
        ContaController contaController = new ContaController(contaService, monetaryService);

        InputUtil input = new InputUtil();
        Menu menu = new Menu(input, adminController, contaController);

        menu.menuPrincipal();
    }
}

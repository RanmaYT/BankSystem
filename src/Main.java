import Controller.AdminController;
import Controller.ContaController;
import Mappers.ContaMapper;
import Mappers.ExtratoMapper;
import Mappers.UsersMapper;
import Model.Services.*;
import SingletonRepositories.ContaRepository;
import SingletonRepositories.ExtratoRepository;
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
        ExtratoRepository extratoRepository = ExtratoRepository.getInstance();
        SessionManager sessionManager = SessionManager.getInstance();

        // Services
        ContaService contaService = new ContaService(sessionManager, contaRepo, new ContaMapper());
        AdminService adminService = new AdminService(userRepo, contaService, new UsersMapper());
        MonetaryService monetaryService = new MonetaryService(contaRepo, sessionManager, new PagamentoService(new EspeciePayment()));
        ExtratoService extratoService = new ExtratoService(new ExtratoMapper(), extratoRepository, sessionManager);

        // Controllers
        AdminController adminController = new AdminController(adminService);
        ContaController contaController = new ContaController(contaService, monetaryService, extratoService);

        InputUtil input = new InputUtil();
        Menu menu = new Menu(input, adminController, contaController);

        menu.menuPrincipal();
    }
}

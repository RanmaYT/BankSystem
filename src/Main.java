import Controller.AdminController;
import Controller.ContaController;
import Controller.UIController;
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
import View.View;


public class Main {
    public static void main(String[] args) {
        InputUtil input = new InputUtil();

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
        UIController uiController = new UIController(input, adminController, contaController, new View());

        uiController.menuPrincipal();

    }
}

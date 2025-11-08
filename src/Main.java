import Controller.AdminController;
import Controller.ContaController;
import Model.Services.AdminService;
import Model.Services.ContaService;
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
        SessionManager sM = SessionManager.getInstance();
        ContaRepository cR = ContaRepository.getInstance();
        UserRepository uR = UserRepository.getInstance();

        // Services
        PagamentoService pagamentoService = new PagamentoService(new EspeciePayment());
        ContaService contaService = new ContaService(sM, cR, pagamentoService);
        AdminService adminService = new AdminService(uR, contaService);

        // Controllers
        AdminController adminController = new AdminController(adminService);
        ContaController contaController = new ContaController(contaService);

        // Menu
        InputUtil inputUtil = new InputUtil();
        Menu menu = new Menu(inputUtil, adminController, contaController);

        while(true) {
            menu.menuPrincipalAdmin();
        }
    }
}

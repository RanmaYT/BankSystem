package Model.Services;

import SingletonRepositories.UserRepository;

public class AdminService {
    private UserRepository userRepo;

    public AdminService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public void cadastrarCliente() {

    }

    public void bloquearCliente(int id) {

    }
}
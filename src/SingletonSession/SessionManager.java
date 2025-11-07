package SingletonSession;

import Model.Conta;
import Model.Usuario;

public class SessionManager {
    private SessionManager instance;
    private Usuario usuarioLogado;
    private Conta contaAtiva;

    private SessionManager() {

    }

    public SessionManager getInstance(){
        if(instance == null) { instance = new SessionManager(); }
        return instance;
    }
}

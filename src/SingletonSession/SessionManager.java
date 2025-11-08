package SingletonSession;

import Model.Conta;
import Model.Usuario;

public class SessionManager {
    private static SessionManager instance;
    private Usuario usuarioLogado;
    private Conta contaAtiva;

    private SessionManager() {

    }

    public static SessionManager getInstance(){
        if(instance == null) { instance = new SessionManager(); }
        return instance;
    }
}

package SingletonSession;

import Model.ContaAbstrata;
import Model.UsuarioAbstrato;

public class SessionManager {
    private static SessionManager instance;
    private UsuarioAbstrato usuarioLogado;
    private ContaAbstrata contaAtiva;

    private SessionManager() {

    }

    public static SessionManager getInstance(){
        if(instance == null) { instance = new SessionManager(); }
        return instance;
    }

    public UsuarioAbstrato getUsuarioLogado(){
        return usuarioLogado;
    }

    public void setUsuarioLogado(UsuarioAbstrato usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }

    public ContaAbstrata getContaAtiva() {
        return contaAtiva;
    }

    public void setContaAtiva(ContaAbstrata contaAtiva){
        this.contaAtiva = contaAtiva;
    }
}

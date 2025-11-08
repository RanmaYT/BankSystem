package SingletonRepositories;

public interface Repository<T extends IStorable>{
    public void salvar(T entidade);
    public void deletar(T entidade);
}

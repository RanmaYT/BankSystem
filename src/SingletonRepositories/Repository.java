package SingletonRepositories;

public interface Repository<T extends IStorable>{
    public T acharPorId();
    public void salvar(T entidade);
    public void deletar(T entidade);
}

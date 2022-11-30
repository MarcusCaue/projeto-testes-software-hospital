public abstract class Funcionario extends Pessoa{

  public Funcionario(String novoCpf, String novoNome, String novoEndereco) {
    super(novoCpf, novoNome, novoEndereco);
    this.nome = novoNome;
    this.cpf = novoCpf;
    this.endereco = novoEndereco;
  }
  public Funcionario(String novoCpf, String novoNome) {
    super(novoCpf, novoNome);
    this.nome = novoNome;
    this.cpf = novoCpf;

  }
  public abstract void atende(Paciente p);
}
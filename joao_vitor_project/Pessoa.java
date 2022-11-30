
/**
 * Essa classe representa uma Pessoa.
 * 
 * @author Mirna Maia
 * @date 25/08/2022
 */
public abstract class Pessoa{
  // Atributos da classe
  protected String nome;
  protected String cpf;
  protected String endereco;

 

  public Pessoa(String novoCpf, String novoNome) {
    this.nome = novoNome;
    this.cpf = novoCpf;
   
    this.endereco = "";
  }

   public Pessoa(String novoCpf, String novoNome, String endereco) {
    this.nome = novoNome;
    this.cpf = novoCpf;
    this.endereco = endereco;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String novo) {
    this.nome = novo;
  }

    public String getEndereco() {
    return endereco;
  }

  public void setEndereco(String novo) {
    this.endereco = novo;
  }
  public String getCpf() {
    return cpf;
  }

  public void setCpf(String novoCpf) {
    this.cpf = novoCpf;
  }

  public String toString() {
    String retorno = "";
    if (!getNome().equals(""))
      retorno += "NOME: " + getNome();
    if (!getCpf().equals(""))
      retorno += "\nCPF: " + getCpf();
    
    return retorno;
  }

  // todos os métodos equals devem ter essa assinatura
  public boolean equals(Object obj) {
    if (obj instanceof Pessoa) {
      // faremos um cast para conseguir compara os atributos
      Pessoa p = (Pessoa) obj;
      if (getCpf().equals(p.getCpf()) )
        return true;
    }
    // qualquer outra situação deve retornar false
    return false;
  }

}

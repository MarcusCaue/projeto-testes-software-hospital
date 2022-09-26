
/**
* Essa classe representa um paciente que está dando entrada num hospital.
* @author Mirna Maia
* @date 15/08/2022
*/
public class Paciente extends Pessoa{
  // Atributos da classe
  private String procedimento;
  private boolean estaNaUti;
  private double peso; // em kilos
  private int altura; //em centimetros


  /*
  * Método construtor para um Paciente que tenha as informações do cpf, novo e endereço
  */
  public Paciente (String novoCpf, String novoNome, String novoEndereco) {
    super(novoCpf, novoNome);
    super.endereco = novoEndereco;
    estaNaUti= false;
    
   }


  public double getPeso() {
    return peso;
  }

  
  public int getAltura() {
    return altura;
  }

  public void setPeso(double p) {
    this.peso= p;
  }

  public void setAltura(int a) {
    this.altura= a;
  }
  public boolean estaNaUti() {
    return estaNaUti;
  }

  public String getProcedimento() {
    return procedimento;
  }

  public void setEstaNaUti(boolean uti) {
    this.estaNaUti = uti;
  }

  public void setProcedimento (String proc) {
    this.procedimento = proc;
  }

  
 public String toString() {
   String retorno = super.toString();
   if (estaNaUti) {
     retorno += "\nInternado na Uti";
   }
   if (!getProcedimento().equals("")) {
     retorno += "\nProcedimento: " + getProcedimento();
   }
  
    return retorno;
 }

  
  
}


/**
* Essa classe representa um médico que está trabalhando em um hospital.
* @author Mirna Maia
* @date 25/08/2022
*/
public class Medico  extends Funcionario  {
  // Atributos da classe
  private String crm;
  
  /*
  * Método para construir um objeto do tipo Médico. 
  * @param cpf O cpf do médico
  * @param nome O nome do médico
  * @param crm O crm do médico
  */
  public Medico (String novoCpf, String novoNome, String crm) {
    super(novoCpf, novoNome);
    this.crm = crm;
  }

  
  public String getCrm() {
    return crm;
  }

  public void setCrm(String novo) {
    this.crm = novo;
  }
  
 public String toString() {
   String retorno = super.toString();
   if (!getCrm().equals(""))
     retorno += "\nCRM: " + getCrm();
    return retorno;
 }

  
  // todos os métodos equals devem ter essa assinatura
  public boolean equals(Object obj) {
    //se o objeto passado como parametro for do tipo Medico
    if (obj instanceof Medico) {
      //faremos um cast para conseguir compara os atributos 
      Medico m = (Medico) obj;
      if (getCpf().equals(m.getCpf()) && getRg().equals(m.getRg()) && getCrm().equals(m.getCrm()))
        return true;
    }
    //qualquer outra situação deve retornar false
    return false;
  }

  public void atende(Paciente p) {
    System.out.println("\nDr. " +getNome() + " está examinando o paciente " + p.getNome());
    System.out.println("Receita do remedio X na dose: " + calculaDoseRemedio(p));
  
    System.out.println("Fim da consulta.");
  }

  private double calculaDoseRemedio(Paciente p){
    //nao deve ser implementada agora
    //aguarde orientações da professora
    //dose = 10% do IMC do paciente
    
    return 0;
  }
}

import java.util.*;

public class Sistema implements FuncionalidadesIF {
  /**
  * Esse método deve inicializar o banco de dados e carregar a informação do hospital em um  objeto do tipo Hospital.
  */
  public void inicializaSistema(){}
 
  public void finalizaSistema(){}


  //Funcionalidade que cadastra o paciente no sistema 
  public void cadastraPaciente(String novoCpf, String novoNome, String novoEndereco){}
  public Paciente localizaPaciente(String cpf){return null;}

  //altera o valor dos parametros da pessoa que é cadastrada no sistema com o cpf passado como parametro
  public void alteraNome(String cpf, String novoNome){}
  public void alteraEndereco(String cpf, String novoEndereco){}

  //verifica o valor dos atributos da pessoa que está cadastrada no sistema com esse cpf
  public String confirmaNome(String cpf, String nome){return null;}
  public String confirmaEndereco(String cpf, String endereco){return null;}

  public void internaNaUti(Paciente p){}
  public void altaDaUti(Paciente p){}

  public void alteraAltura(Paciente p, int altura){}
  public void altaPeso(Paciente p, double peso){}

  public void cadastraMedico(String cpf, String nome, String crm){}
  public Medico localizaMedico(String crm){return null;}
  public void cadastraEnfermeiro(String novoCpf, String novoNome){}
  public Enfermeiro localizaEnfermeiro(String cpf){return null;}
  public void cadastraFisioterapeuta(String cpf, String nome){}
  public Enfermeiro localizaFisioterapeuta(String cpf){return null;}
  public ArrayList<Paciente> listagemDePacientesInternados(){return null;}
  public ArrayList<Funcionario> listagemDeFuncionarios(){return null;}
  
  public int getNumeroDePacientesInternados(){return 0;}
  public int getNumeroDeFuncionarios(){return 0;}
  public void atendimento(String cpfFuncionario, String cpfPaciente){}
  public double atendimentoMedico(String crmMedico, String cpfPaciente){return 0;}
  
  
}
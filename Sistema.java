import java.util.*;
import classes.*;
import java.io.*;
import interfaces.FuncionalidadesIF;

public class Sistema implements FuncionalidadesIF {

  Hospital hospital;

  /**
    * Esse método deve inicializar o banco de dados e carregar a informação do hospital em um objeto do tipo Hospital.
    * Casos de Teste: 
    *    1 - Arquivo Existente e com Conteúdo;
    *    2 - Arquivo Existente e sem Conteúdo;
    *    3 - Arquivo Inexistente;
    *    4 - Tratamento do IOException;   
  */
  public void inicializaSistema() {

    Scanner entrada = new Scanner(System.in);
    String nomeHospital;

    /* Acessando o BD para obter o nome do Hospital */

    // Acessa o caminho do arquivo no BD
    File arqPath = new File("./database/hospital.txt");

    try {
      FileReader arqReader = new FileReader(arqPath); 
      BufferedReader arq = new BufferedReader(arqReader);

      // Caso o arquivo contenha o dado esperado, o mesmo será lido.
      // Do contrário, pedirá para o usuário cadastrar esse Hospital
      if (arq.ready()) {
        nomeHospital = arq.readLine();
      } else {
        nomeHospital = cadastraHospital(arqPath, entrada);
      }

      // Caso o nome do arquivo seja nulo, encerra o programa
      if (nomeHospital == null) {
        System.out.println("Sistema encerrado.");
        System.exit(0);
      }

      // Do contrário, instancia um objeto do tipo Hospital
      this.hospital = new Hospital(nomeHospital);
      System.out.printf("O sistema do Hospital '%s' está funcionário com sucesso!", nomeHospital);

      // Fechando os arquivos abertos
      arq.close();
      arqReader.close();

    } 
    // Se o arquivo for inexistente, ele será criado e o usuário cadastrará o Hospital.
    catch (FileNotFoundException e) {
      try {
        // Criando o arquivo
        arqPath.createNewFile();

        // Cadastrando o hospital
        nomeHospital = cadastraHospital(arqPath, entrada);

        // Caso o nome do arquivo seja nulo, encerra o programa
        if (nomeHospital == null) {
          System.out.println("Sistema encerrado.");
          System.exit(0);
        }

        // Do contrário, instancia um objeto do tipo Hospital
        this.hospital = new Hospital(nomeHospital);
        System.out.printf("O sistema do Hospital '%s' está funcionário com sucesso!", nomeHospital);

      } 
      // Caso algum erro do tipo IO ocorra, encerra o programa.
      catch (IOException e1) {
        System.out.println("Houve algum problema ao tentar acessar o arquivo. Tente novamente mais tarde.");
        System.out.println("Sistema encerrado.");
        System.exit(0);
      }

    } 
    // Caso algum erro do tipo IO ocorra, encerra o programa.
    catch (IOException e) {
      System.out.println("Houve algum problema ao tentar acessar o arquivo. Tente novamente mais tarde.");
      System.out.println("Sistema encerrado.");
      System.exit(0);
    }
  }
 
  public void finalizaSistema(){



  }

  /**
   * Função para abrir o BD e cadastrar um Hospital
   * @param arqPath = variável do tipo File que conterá o caminho de acesso ao arquivo do BD
   * @param entrada = um Scanner que será utilizado para fazer a leitura do nome do Hospital
   * @return String = o nome do Hospital quando funciona sem erros e null quando ocorre a IOException
   */
  public String cadastraHospital(File arqPath, Scanner entrada) {
    System.out.print("Parece que o Hospital ainda não foi cadastrado. \nDigite um nome para ele: ");
    String nomeHospital = entrada.nextLine();

    // Cadastrando esse Hospital no BD
    try {
      FileWriter gravarArq = new FileWriter(arqPath);
      gravarArq.write(nomeHospital);
      gravarArq.close();
      return nomeHospital;
    } catch (IOException e) {
      System.out.println("Houve algum problema ao tentar acessar o arquivo. Tente novamente mais tarde.");
      return null;
    }
  }













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
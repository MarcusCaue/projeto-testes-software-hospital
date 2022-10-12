import java.util.*;
import classes.*;
import java.io.*;
import interfaces.FuncionalidadesIF;

public class Sistema implements FuncionalidadesIF {

  Hospital hospital;

  /**
  * Esse método deve inicializar o banco de dados e carregar a informação do hospital em um objeto do tipo Hospital.
  */
  public void inicializaSistema() {
    inicializaHospital();
    getPacientesOnBd();
    getFuncionariosOnBd();
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
      return null;
    }
  }

  /**
   * Encerra o programa quando uma IOException é acionada
   */
  public void encerraPrograma() {
    System.out.println("Houve algum problema ao tentar acessar o arquivo. Tente novamente mais tarde.");
    System.out.println("Sistema encerrado.");
    System.exit(0);
  }

  /**
   * Inicializa o Hospital acessando o BD
   * Casos de Teste: 
   *     1 - Arquivo Existente e com Conteúdo;
   *     2 - Arquivo Existente e sem Conteúdo;
   *     3 - Arquivo Inexistente;
   *     4 - Tratamento do IOException;   
   */
  public void inicializaHospital() {
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
      System.out.printf("O sistema do Hospital '%s' está funcionário com sucesso!\n", nomeHospital);

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
          encerraPrograma();
        }

        // Do contrário, instancia um objeto do tipo Hospital
        this.hospital = new Hospital(nomeHospital);
        System.out.printf("O sistema do Hospital '%s' está funcionário com sucesso!\n", nomeHospital);

      } 
      // Caso algum erro do tipo IO ocorra, encerra o programa.
      catch (IOException e1) {
        encerraPrograma();
      }

    } 
    // Caso algum erro do tipo IO ocorra, encerra o programa.
    catch (IOException e) {
      encerraPrograma();
    }
  }

  /**
   * Busca no BD os dados dos pacientes cadastrados
   * Casos de Teste:
   *  1 - Arquivo existente com e sem conteúdo (funciona da mesma forma).
   *  2 - Arquivo inexistente.
   */
  public void getPacientesOnBd() {
    File arqPath = new File("./database/pacientes.txt");

    try {
      FileReader arqReader = new FileReader(arqPath);
      BufferedReader arq = new BufferedReader(arqReader);

      while (arq.ready()) {
        // Lendo os dados do arquivo
        String[] dados = arq.readLine().split(";");

        // Instanciando um paciente
        Paciente p = new Paciente(dados[0], dados[1], dados[2]);

        /* Verificando se ele possui os outros atributos */
        String rg = dados[3];
        Double peso = Double.parseDouble(dados[4]);
        Integer altura = Integer.parseInt(dados[5]);
        boolean estaNaUti = Boolean.parseBoolean(dados[6]);
        String procedimento = dados[7]; 

        if (rg != null) { p.setRg(rg); }
        if (peso != null) { p.setPeso(peso); }
        if (altura != null) { p.setAltura(altura); }
        if (estaNaUti) { p.setEstaNaUti(estaNaUti); }
        if (procedimento != null) { p.setProcedimento(procedimento); }
        
        // Por fim, adiciona o paciente à lista de Pacientes do Hospital
        this.hospital.addPaciente(p);
      }

      arqReader.close();
      arq.close();

    } catch (FileNotFoundException e) {
      System.out.println("O arquivo de pacientes não foi encontrado. Vamos criá-lo.");
      try {
        arqPath.createNewFile();
      } catch (IOException e1) {
        encerraPrograma();
      }
      System.out.println("A lista de pacientes no total é de 0.");
    } catch (IOException e) {
      encerraPrograma();
    }
  }

  /**
   * Busca no BD os dados dos funcionários cadastrados
   */
  public void getFuncionariosOnBd() {

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
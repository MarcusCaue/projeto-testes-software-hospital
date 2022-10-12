import java.util.*;
import classes.*;
import java.io.*;
import interfaces.FuncionalidadesIF;

public class Sistema implements FuncionalidadesIF {

  Hospital hospital;

  // Função CONCLUÍDA
  /**
  * Esse método deve inicializar o banco de dados e carregar a informação do hospital em um objeto do tipo Hospital.
  */
  public void inicializaSistema() {
    inicializaHospital(); // Pega os dados do Hospital no BD
    getPacientesOnBd(); // Consulta os pacientes cadastrados no BD
    getFuncionariosOnBd(); // Consulta os funcionários cadastrados no BD
  }
 
  public void finalizaSistema(){
    salvarPacientesOnBd();
    salvarFuncionariosOnBd();
  }

  /**
   * Salva os dados dos pacientes cadastrados no Banco de Dados
   * 
   */
  private void salvarPacientesOnBd() {
    File arqPath = new File("./database/pacientes.txt");

    // Obtendo a lista de pacientes atuais no hospital
    ArrayList<Paciente> pacientes = this.hospital.getPacientes();

    try {
      FileWriter arqWriter = new FileWriter(arqPath);
      PrintWriter arq = new PrintWriter(arqWriter);

      // Salvando os dados de cada paciente no BD
      escreverPacientes(pacientes, arq);
     
      arqWriter.close();
      arq.close();

    } catch (IOException e) {
      encerraPrograma();
    }

    System.out.println("Pacientes cadastrados com sucesso!");

  }

  public void escreverPacientes(ArrayList<Paciente> pacientes, PrintWriter arq) {
    for (Paciente p : pacientes) {
      String line = String.format("%s;%s;%s;",
      p.getCpf(), p.getNome(), p.getEndereco());

      String rg = p.getRg();
      double peso = p.getPeso();
      int altura = p.getAltura();
      boolean estaNaUti = p.estaNaUti();
      String procedimento = p.getProcedimento();

      if (rg.equals("")) {
        line += "null;";
      } else {
        line += rg + ";";
      }

      if (peso == 0) {
        line += "null;";
      } else {
        line += peso + ";";
      }

      if (altura == 0) {
        line += "null;";
      } else {
        line += altura + ";";
      }

      line += estaNaUti + ";";

      if (procedimento.equals("") || procedimento == null || procedimento.equals(" ")) {
        line += "null";
      } else {
        line += procedimento;
      }

      arq.println(line);
    }
  }

  private void salvarFuncionariosOnBd() {
  }
  

  // Função CONCLUÍDA
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

  // Função CONCLUÍDA
  /**
   * Encerra o programa quando uma IOException é acionada
   */
  public void encerraPrograma() {
    System.out.println("Houve algum problema ao tentar acessar o arquivo. Tente novamente mais tarde.");
    System.out.println("Sistema encerrado.");
    System.exit(0);
  }

  // Função CONCLUÍDA
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

  // Função CONCLUÍDA
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
        String peso = dados[4];
        String altura = dados[5];
        String estaNaUti = dados[6];
        String procedimento = dados[7]; 

        if (! rg.equals("null")) { 
          p.setRg(rg); 
        }
        if (! peso.equals("null")) { 
          p.setPeso(Double.parseDouble(peso)); 
        }
        if (! altura.equals("null")) { 
          p.setAltura(Integer.parseInt(altura)); 
        }
        if (! estaNaUti.equals("null")) { 
          p.setEstaNaUti(Boolean.parseBoolean(estaNaUti)); 
        }
        if (! procedimento.equals("null")) { 
          p.setProcedimento(procedimento); 
        }
        
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

  // Função CONCLUÍDA
  /**
   * Busca no BD os dados dos funcionários cadastrados
   * Casos de Teste:
   *  1 - Arquivo existente com e sem conteúdo (funciona da mesma forma).
   *  2 - Arquivo inexistente.
   */
  public void getFuncionariosOnBd() {
    File arqPath = new File("./database/funcionarios.txt");

    try {
      FileReader arqReader = new FileReader(arqPath);
      BufferedReader arq = new BufferedReader(arqReader);

      while (arq.ready()) {
        // Lendo os dados do arquivo
        String[] dados = arq.readLine().split(";");

        Funcionario f;

        /* Analisando os atributos e verificando qual é o tipo de funcionário */
        String funcao = dados[0];
        String cpf = dados[1];
        String nome = dados[2];
        String crm = dados[3];
        String endereco = dados[4];
        String rg = dados[5];

        // Identificando a função
        if (! crm.equals("null")) { f = new Medico(cpf, nome, crm); } 
        else if (funcao.equals("Enfermeiro")) { f = new Enfermeiro(cpf, nome); } 
        else { f = new Fisioterapeuta(cpf, nome); }

        // Adicionando os outros atributos
        if (! endereco.equals("null")) { f.setEndereco(endereco); }
        if (! rg.equals("null")) { f.setRg(rg); }

        // Adicionando o funcionário
        this.hospital.addFuncionario(f);
      }

      arqReader.close();
      arq.close();

    } catch (FileNotFoundException e) {
      System.out.println("O arquivo de funcionarios não foi encontrado. Vamos criá-lo.");
      try {
        arqPath.createNewFile();
      } catch (IOException e1) {
        encerraPrograma();
      }
      System.out.println("A lista de funcionários no total é de 0.");
    } catch (IOException e) {
      encerraPrograma();
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
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
    inicializaHospital(); // Pega os dados do Hospital no BD
    getPacientesOnBd(); // Consulta os pacientes cadastrados no BD
    getFuncionariosOnBd(); // Consulta os funcionários cadastrados no BD
  }
 
  public void finalizaSistema(){
    salvarPacientesOnBd(); // Cadastra/Atualiza os dados dos pacientes no Banco de Dados
    salvarFuncionariosOnBd(); // Cadastra/Atualiza os dados dos funcionários no Banco de Dados
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

    System.out.println("Pacientes cadastrados/atualizados com sucesso!");

  }

  private void escreverPacientes(ArrayList<Paciente> pacientes, PrintWriter arq) {
    for (Paciente p : pacientes) {
      String line = String.format("%s;%s;%s;",
      p.getCpf(), p.getNome(), p.getEndereco());

      String rg = p.getRg();
      double peso = p.getPeso();
      int altura = p.getAltura();
      boolean estaNaUti = p.estaNaUti();
      String procedimento = p.getProcedimento().trim();

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

      if (procedimento == null || procedimento.equals("") || procedimento.equals("inexistente")) {
        line += "null";
      } else {
        line += procedimento;
      }

      arq.println(line);
    }
  }

  private void salvarFuncionariosOnBd() {
    File arqPath = new File("./database/funcionarios.txt");

    // Obtendo a lista de funcionários cadastrados no Hospital
    ArrayList<Funcionario> funcionarios = this.hospital.getFuncionarios();

    try {
      FileWriter arqWriter = new FileWriter(arqPath);
      PrintWriter arq = new PrintWriter(arqWriter);

      // Salvando os dados de cada funcionário no BD
      escreverFuncionarios(funcionarios, arq);
     
      arqWriter.close();
      arq.close();

    } catch (IOException e) {
      encerraPrograma();
    }

    System.out.println("Dados dos funcionários cadastrados/atualizados com sucesso!");

  }
  
  private void escreverFuncionarios(ArrayList<Funcionario> funcionarios, PrintWriter arq) {
    for (Funcionario f : funcionarios) {
      
      String funcao = f.getClass().getSimpleName();
      String nome = f.getNome();
      String cpf = f.getCpf();
      String endereco = f.getEndereco().trim();
      String rg = f.getRg();

      String line = String.format("%s;%s;%s;",
      funcao, cpf, nome);

      if (funcao.equals("Medico")) {
        Medico medico = (Medico) f;
        String crm = medico.getCrm();
        line += crm + ";";
      } else {
        line += "null;";
      }

      if (endereco.equals("inexistente") || endereco.equals("")) {
        line += "null;";
      } else {
        line += endereco + ";";
      }

      if (rg.equals("")) {
        line += "null";
      } else {
        line += rg;
      }

      arq.println(line);
    }
  }

  /**
   * Função para abrir o BD e cadastrar um Hospital
   * @param arqPath = variável do tipo File que conterá o caminho de acesso ao arquivo do BD
   * @param entrada = um Scanner que será utilizado para fazer a leitura do nome do Hospital
   * @return String = o nome do Hospital quando funciona sem erros e null quando ocorre a IOException
   */
  private String cadastraHospital(File arqPath, Scanner entrada) {
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
  private void encerraPrograma() {
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
  private void inicializaHospital() {
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
  private void getPacientesOnBd() {
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
        if (procedimento.equals("null")) {
          p.setProcedimento("inexistente");
        } else {
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

  /**
   * Busca no BD os dados dos funcionários cadastrados
   * Casos de Teste:
   *  1 - Arquivo existente com e sem conteúdo (funciona da mesma forma).
   *  2 - Arquivo inexistente.
   */
  private void getFuncionariosOnBd() {
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
        if (! endereco.equals("null")) { 
          f.setEndereco(endereco); 
        } else {
          f.setEndereco("inexistente");
        }
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
  public void cadastraPaciente(String novoCpf, String novoNome, String novoEndereco){
    ArrayList<Paciente> pacientes = this.hospital.getPacientes();

    if (verificaCPF(novoCpf)) {
      System.out.println("Já tem uma pessoa cadastrada com esse CPF digite outro.");
    } else {
      Paciente paciente = new Paciente(novoCpf, novoNome, novoEndereco);
      pacientes.add(paciente);
    }

  }

  public Paciente localizaPaciente(String cpf){
    ArrayList<Paciente> pacientes = this.hospital.getPacientes();
    for (int paciente = 0; paciente < pacientes.size();paciente++){
      if (pacientes.get(paciente).getClass().getSimpleName().equals("Paciente")){
        Paciente p1 = (Paciente) pacientes.get(paciente);
        if (p1.getCpf().equals(cpf)){
          return p1;
        }
      }
    }
    return null;
  }

  //altera o valor dos parametros da pessoa que é cadastrada no sistema com o cpf passado como parametro
  public void alteraNome(String cpf, String novoNome){
    Pessoa p = null;
    ArrayList<Paciente> pacientes = this.hospital.getPacientes();
    ArrayList<Funcionario> funcionarios = this.hospital.getFuncionarios();

    // Buscando por Pacientes com esse CPF
    for (Paciente paciente: pacientes){
      String cpfPacienteNaLista = paciente.getCpf();

      if (cpfPacienteNaLista.equals(cpf)){
        p = paciente;
        break;
      }
    }  

    // Buscando por Funcionários com esse CPF
    if (p == null) {
      for (Funcionario funcionario: funcionarios){
        String cpfFuncNaLista = funcionario.getCpf();

        if (cpfFuncNaLista.equals(cpf)){
          p = funcionario;
          break;
        }
      }
    }

    // Se p == null, quer dizer que ele encontrou ninguém com esse CPF
    if (p == null) {
      System.out.println("Não encontramos ninguém cadastrado com esse CPF, tente novamente mais tarde.");
    }
    // Do contrário, altera o nome
    else {
      p.setNome(novoNome);
      System.out.println("Nome modificado com sucesso!");
    }

  }

  public void alteraEndereco(String cpf, String novoEndereco){
    Pessoa p = null;
    ArrayList<Paciente> pacientes = this.hospital.getPacientes();
    ArrayList<Funcionario> funcionarios = this.hospital.getFuncionarios();

    // Buscando por Pacientes com esse CPF
    for (Paciente paciente: pacientes){
      String cpfPacienteNaLista = paciente.getCpf();

      if (cpfPacienteNaLista.equals(cpf)){
        p = paciente;
        break;
      }
    }  

    // Buscando por Funcionários com esse CPF
    if (p == null) {
      for (Funcionario funcionario: funcionarios){
        String cpfFuncNaLista = funcionario.getCpf();

        if (cpfFuncNaLista.equals(cpf)){
          p = funcionario;
          break;
        }
      }
    }

    // Se p == null, quer dizer que ele encontrou ninguém com esse CPF
    if (p == null) {
      System.out.println("Não encontramos ninguém cadastrado com esse CPF, tente novamente mais tarde.");
    }
    // Do contrário, altera o endereço
    else {
      p.setEndereco(novoEndereco);
      System.out.println("Endereço modificado com sucesso!");
    }

  }

  //verifica o valor dos atributos da pessoa que está cadastrada no sistema com esse cpf
  public String recuperaNome(String cpf){

    ArrayList<Funcionario> funcionarios = this.hospital.getFuncionarios();
    Funcionario f = null;

    for (int funcionario = 0; funcionario < funcionarios.size(); funcionario++) {
      if (funcionarios.get(funcionario).getCpf().equals(cpf)) {
        f = funcionarios.get(funcionario);
        return "Nome do funcionário de CPF " + cpf + ": " + f.getNome();
      }
    }

    Paciente p = localizaPaciente(cpf);

    if (p == null) {
      return "Não existe nenhum usuário cadastrado com esse CPF";
    }

    return "Nome do paciente de CPF " + cpf + ": " + p.getNome();
  
  }
  
  public String recuperaEndereco(String cpf){
    
    ArrayList<Funcionario> funcionarios = this.hospital.getFuncionarios();
    Funcionario f = null;

    for (int funcionario = 0; funcionario < funcionarios.size(); funcionario++) {
      if (funcionarios.get(funcionario).getCpf().equals(cpf)) {
        f = funcionarios.get(funcionario);
        return "Endereço do funcionário de CPF " + cpf + ": " + f.getEndereco();
      }
    }

    Paciente p = localizaPaciente(cpf);

    if (p == null) {
      return "Não existe nenhum usuário cadastrado com esse CPF";
    }

    return "Endereço do paciente de CPF " + cpf + ": " + p.getEndereco();
  
  }

  public void internaNaUti(Paciente p){
    if (p.estaNaUti() == false) { 
      p.setEstaNaUti(true);
      System.out.println("Paciente de CPF " + p.getCpf() + " foi internado!");
    } else {
      System.out.println("Paciente de CPF " + p.getCpf() + " já está internado.");
    }
  }
  
  public void altaDaUti(Paciente p){
    ArrayList<Paciente> pacientes = this.hospital.getPacientes();
    boolean encontrouInternado = false;

    for (int internado = 0; internado < pacientes.size(); internado++) {
      if (pacientes.get(internado).equals(p) && pacientes.get(internado).estaNaUti() == true) {
        pacientes.get(internado).setEstaNaUti(false);
        System.out.printf("Paciente '%s' recebeu alta!\n", p.getNome());
        encontrouInternado = true;
        break;
      }
    }

    if (encontrouInternado == false) {
      System.out.println("Não temos pacientes internados no momento.");
    }

  }

  public void alteraAltura(Paciente p, int altura){
    p.setAltura(altura);
    System.out.println("Altura modificada com sucesso!");
  }
  
  public void altaPeso(Paciente p, double peso){
    p.setPeso(peso);
    System.out.println("Peso modificado com sucesso!");
  }

  public void cadastraMedico(String cpf, String nome, String crm){
    ArrayList<Funcionario> funcionarios = this.hospital.getFuncionarios();

    if (verificaCPF(cpf)) {
      System.out.println("Já tem uma pessoa cadastrada com esse CPF, digite outro.");
    } 
    else if (verificaCRM(crm)) {
      System.out.println("Já tem um médico cadastrado com esse CRM, digite outro.");
    } 
    else {
      Medico medico = new Medico(cpf, nome, crm);
      funcionarios.add(medico);
    }
  }

  public Medico localizaMedico(String crm){
    ArrayList<Funcionario> funcionarios = this.hospital.getFuncionarios();

    for (int funcionario = 0; funcionario < funcionarios.size(); funcionario++) {
      if (funcionarios.get(funcionario).getClass().getSimpleName().equals("Medico")) {
        Medico m1 = (Medico) funcionarios.get(funcionario);
        if (m1.getCrm().equals(crm)) {
          return m1;
        }
      }
    }

    return null;
  }

  public void cadastraEnfermeiro(String novoCpf, String novoNome){
    ArrayList<Funcionario> funcionarios = this.hospital.getFuncionarios();

    if (verificaCPF(novoCpf)) {
      System.out.println("Já tem uma pessoa cadastrada com esse CPF, digite outro.");
    } 
    else {
      Enfermeiro enfermeiro = new Enfermeiro(novoCpf, novoNome);
      funcionarios.add(enfermeiro);
    }
  }

  public Enfermeiro localizaEnfermeiro(String cpf){
    ArrayList<Funcionario> funcionarios = this.hospital.getFuncionarios();
    for (int funcionario = 0; funcionario < funcionarios.size(); funcionario++) {
      if (funcionarios.get(funcionario).getClass().getSimpleName().equals("Enfermeiro")){
        Enfermeiro e1 = (Enfermeiro) funcionarios.get(funcionario);
        if (e1.getCpf().equals(cpf)) {
          return e1;
        }
      }
    }
    return null;
  }
  
  public void cadastraFisioterapeuta(String cpf, String nome){
    ArrayList<Funcionario> funcionarios = this.hospital.getFuncionarios();

    if (verificaCPF(cpf)) {
      System.out.println("Já tem uma pessoa cadastrada com esse CPF, digite outro.");
    } 
    else {
      Fisioterapeuta fisioterapeuta = new Fisioterapeuta(cpf,nome);
      funcionarios.add(fisioterapeuta);
    }

  }

  public Fisioterapeuta localizaFisioterapeuta(String cpf) {
    ArrayList<Funcionario> funcionarios = this.hospital.getFuncionarios();
    for (int funcionario = 0; funcionario < funcionarios.size();funcionario++){
      if (funcionarios.get(funcionario).getClass().getSimpleName().equals("Fisioterapeuta")) {
        Fisioterapeuta f1 = (Fisioterapeuta) funcionarios.get(funcionario);
        if (f1.getCpf().equals(cpf)) {
          return f1;
        }
      }
    }
    return null;
  }

  public ArrayList<Paciente> listagemDePacientesInternados() {
    // Cria-se uma lista que vai armazenar os objetos dos pacientes internados
    ArrayList<Paciente> pacientesInternados = new ArrayList<Paciente>();

    // Pacientes cadastrados no hospital
    ArrayList<Paciente> pacientes = this.hospital.getPacientes();

    // Buscando pacientes internados
    for (Paciente p : pacientes) {
      boolean internado = p.estaNaUti();

      // Se estiver na Uti -> está internado -> será adicionado na lista "pacientesInternados"
      if (internado == true) {
        pacientesInternados.add(p);
      }

    }

    // Retorna essa lista
    return pacientesInternados;
  }
  
  public ArrayList<Funcionario> listagemDeFuncionarios(){
    ArrayList<Funcionario> nFuncionarios = new ArrayList<Funcionario>();
    ArrayList<Funcionario> funcionarios = this.hospital.getFuncionarios();

    for (Funcionario f : funcionarios){
        nFuncionarios.add(f);
    }

    return nFuncionarios;
  }
  
  public int getNumeroDePacientesInternados(){
    ArrayList<Paciente> pacientesInternados = listagemDePacientesInternados();
    return pacientesInternados.size();
  }
  
  public int getNumeroDeFuncionarios(){
    ArrayList<Funcionario> nFuncionarios = listagemDeFuncionarios();
    return nFuncionarios.size();
  }
  
  public void atendimento(String cpfFuncionario, String cpfPaciente){
    ArrayList<Funcionario> funcionarios = this.hospital.getFuncionarios();
    ArrayList<Paciente> pacientes = this.hospital.getPacientes();

    Funcionario funcionarioAtende = null;
    Paciente pacienteAtendido = null;

    for (Funcionario f : funcionarios) {
      if (f.getCpf().equals(cpfFuncionario)) {
        funcionarioAtende = f;

        pacienteAtendido = localizaPaciente(cpfPaciente);
        
        if (pacienteAtendido == null) {
          System.out.println("Não existe paciente cadastrado com esse CPF.");
        } else {

          String funcao = funcionarioAtende.getClass().getSimpleName();

          if (funcao.equals("Medico")) {
            Medico m1 = (Medico) funcionarioAtende;
            atendimentoMedico(m1.getCrm(), cpfPaciente);
          } else {
            funcionarioAtende.atende(pacienteAtendido);
          }

        }
        break;
      }
    }

    if (funcionarioAtende == null) {
      System.out.println("Não existe funcionário cadastrado com esse CPF.");
    } 
  }
  
  public double atendimentoMedico(String crmMedico, String cpfPaciente){
    Medico medico = localizaMedico(crmMedico);

    if (medico == null) {
      System.out.println("Não existe médico cadastrado com esse CRM.");
    } else {
      Paciente pacienteAtendido = localizaPaciente(cpfPaciente);
      medico.atende(pacienteAtendido);
    }
  
    return 0;
  }

  public boolean validaCPF(String cpf){
    String regexcpf = "[0-9]{3}.[0-9]{3}.[0-9]{3}-[0-9]{2}";
    boolean matchFound = cpf.matches(regexcpf);
    if(matchFound == false){
      return false;
    }
    return true;
  }

  public boolean validaRG(String rg){
    String regexRg = "()|([0-9]{2}.[0-9]{3}.[0-9]{3}-[0-9]{2})";
    boolean matchFound = rg.matches(regexRg);
    if(matchFound == false){
      return false;
    }
    return true;
  }

  public boolean validaNome(String nome){
    nome = nome.trim();
    if(nome.equals("")){
      return false;
    }
    return true;
  }

  public boolean validaCRM(String crm){
    String regexCrm = "[0-9]{6}";
    boolean matchFound = crm.matches(regexCrm);

    if(matchFound == false){
      return false;
    }
    return true;
  }

  public boolean verificaCPF(String cpf) {
    ArrayList<Funcionario> funcionarios = this.hospital.getFuncionarios();
    ArrayList<Paciente> pacientes = this.hospital.getPacientes();

    for (Funcionario f : funcionarios) {
      if (f.getCpf().equals(cpf)) {
        return true;
      }
    }

    for (Paciente p : pacientes) {
      if (p.getCpf().equals(cpf)) {
        return true;
      }
    }

    return false;
  }

  public boolean verificaRG(String rg) {
    ArrayList<Funcionario> funcionarios = this.hospital.getFuncionarios();
    ArrayList<Paciente> pacientes = this.hospital.getPacientes();

    for (Funcionario f : funcionarios) {
      if (f.getRg().equals(rg)) {
        return true;
      }
    }

    for (Paciente p : pacientes) {
      if (p.getRg().equals(rg)) {
        return true;
      }
    }

    return false;
  }

  public boolean verificaCRM(String crm) {
    ArrayList<Funcionario> funcionarios = this.hospital.getFuncionarios();

    for (Funcionario f : funcionarios) {
      if (f.getClass().getSimpleName().equals("Medico")) {
        Medico m1 = (Medico) f;
        if (m1.getCrm().equals(crm)) {
          return true;
        }
      }
    }

    return false;
  }

  public Pessoa localizaPessoa(String cpf) {
    Pessoa p = null;
    ArrayList<Paciente> pacientes = this.hospital.getPacientes();
    ArrayList<Funcionario> funcionarios = this.hospital.getFuncionarios();

    // Buscando por Pacientes com esse CPF
    for (Paciente paciente: pacientes){
      String cpfPacienteNaLista = paciente.getCpf();

      if (cpfPacienteNaLista.equals(cpf)){
        p = paciente;
        break;
      }
    }  

    // Buscando por Funcionários com esse CPF
    if (p == null) {
      for (Funcionario funcionario: funcionarios){
        String cpfFuncNaLista = funcionario.getCpf();

        if (cpfFuncNaLista.equals(cpf)){
          p = funcionario;
          break;
        }
      }
    }

    return p;
  }

} 
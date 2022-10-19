import java.util.*;
import classes.*;
import java.lang.Thread;

class Main {
  public static void main(String[] args) throws InterruptedException {
    Scanner s = new Scanner(System.in);

    // Inicializando o Sistema do Hospital
    Sistema sistemaHospital = new Sistema();
    sistemaHospital.inicializaSistema(); 

    String enfeite = "\n=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=\n";

    while (true) {
      // Menu
      System.out.println(enfeite);
      System.out.println("Bem vindo ao nosso sistema hospitalar. \nExperimente nossas funcionalidades!");
      System.out.println(enfeite);
      System.out.println("O que deseja fazer hoje?\n");
      System.out.println("1  - Alterar Nomes\n2  - Alterar Endereços\n3  - Atualizar Altura do Paciente\n4  - Atualizar Peso do Paciente\n5  - Listar Número de Pacientes internados \n6  - Listar Número de Funcionários\n7  - Cadastrar Funcionário\n8  - Localizar Funcionários\n9  - Confirmar Nome\n10 - Soltar uma Alta da UTI\n11 - Cadastrar Paciente\n12 - Localizar Paciente\n13 - Listar Pacientes Internados\n14 - Listar Funcionários\n15 - Atendimento\n16 - Confirmar Endereço\n17 - Internar Paciente\n18 - Fechar o Programa");
      
      // Validando a escolha do usuário
      int acao;
      do {
        System.out.print("Digite a sua escolha: ");
        acao = s.nextInt();
      } while (acao < 1 || acao > 18);

      // Pausando o programa por 1 segundo
      Thread.sleep(1000);

      // Alterar Nomes
      if (acao == 1) {
        System.out.println(enfeite);
        System.out.println("MODIFICANDO NOME DE UMA PESSOA");
        System.out.println(enfeite);

        System.out.print("Informe o CPF da pessoa que se deseja alterar o nome (XXX.XXX.XXX-XX): "); s.nextLine();
        String cpf = s.nextLine(); 

        // Validando o CPF e o NOME
        if (sistemaHospital.validaCPF(cpf)) {
          System.out.print("Informe o novo nome: ");
          String novoNome = s.nextLine();

          if (sistemaHospital.validaNome(novoNome)) {
            sistemaHospital.alteraNome(cpf, novoNome);        
          } else {
            System.out.println("Nome inválido. O nome não será modificado.");
          }
        } else {
          System.out.println("CPF inválido. O nome não será modificado.");
        }
        Thread.sleep(1000);    
      } 
      // Alterar Endereços
      else if (acao == 2) {
        System.out.print("Informe o CPF da pessoa que se deseja alterar o endereço (XXX.XXX.XXX-XX): "); s.nextLine();
        String cpf = s.nextLine();

        if (sistemaHospital.validaCPF(cpf)) {
          System.out.print("Informe o novo endereço: ");
          String novoEndereco = s.nextLine();
          
          sistemaHospital.alteraEndereco(cpf, novoEndereco);
        } else {
          System.out.println();
        }

        
        Thread.sleep(1000);
      }
      // Atualizar altura do paciente
      else if (acao == 3) {
        System.out.print("Informe o CPF do paciente que se deseja alterar a altura: ");
        String cpf = s.nextLine(); s.nextLine();
        System.out.print("Informe a altura desse paciente: ");
        int altura = s.nextInt();
        
        // Obtendo um objeto de paciente dado o seu CPF
        Paciente p = null;
        ArrayList<Paciente> pacientes = sistemaHospital.hospital.getPacientes();

        for (Paciente paciente: pacientes){
          String cpfPacienteNaLista = paciente.getCpf();

          if (cpfPacienteNaLista.equals(cpf)){
            p = paciente;
            break;
          }       
        }

        sistemaHospital.alteraAltura(p, altura);
        Thread.sleep(1000);  
      }
      // Atualizar peso do paciente
      else if (acao == 4) {
        System.out.print("Informe o CPF do paciente que se deseja alterar o peso: ");
        String cpf = s.nextLine(); s.nextLine();
        System.out.print("Informe o peso desse paciente: ");
        double peso = s.nextDouble();
          
        // Obtendo um objeto de paciente dado o seu CPF
        Paciente p = null;
        ArrayList<Paciente> pacientes = sistemaHospital.hospital.getPacientes();

        for (Paciente paciente: pacientes){
          String cpfPacienteNaLista = paciente.getCpf();

          if (cpfPacienteNaLista.equals(cpf)){
            p = paciente;
          }       
        }

        sistemaHospital.altaPeso(p, peso);  
        Thread.sleep(1000);
      }    
      // Listar número de pacientes internados
      else if (acao == 5) {
        int numPacientesInternados = sistemaHospital.getNumeroDePacientesInternados();
        System.out.println("No momento há " + numPacientesInternados + " pacientes internados.");
        Thread.sleep(1000);
      }
      // Listar número de funcionários
      else if (acao == 6) {
        int numFuncionarios = sistemaHospital.getNumeroDeFuncionarios();
        System.out.println("No momento há " + numFuncionarios + " Funcionários.");
        Thread.sleep(1000);
      } 
      // Cadastrar Funcionário
      else if (acao == 7) {
        System.out.println(enfeite);
        System.out.println("CADASTRANDO FUNCIONÁRIO");
        System.out.println(enfeite);

        System.out.println("Você tem as seguintes opções de funcionário para cadastrar:\n1 - Médico\n2 - Enfermeiro\n3 - Fisioterapeuta");
        System.out.print("\nDigite o número da opção que você deseja: ");
        int resposta = s.nextInt(); s.nextLine();

        if(resposta == 1) {
          // Recebe os dados//
          System.out.print("Digite o CPF do médico que você quer cadastrar (XXX.XXX.XXX-XX): ");
          String ocpf = s.nextLine();
          System.out.print("Digite o Nome do médico que você quer cadastrar: ");
          String onome = s.nextLine();
          System.out.print("Digite o CRM do médico que você quer cadastrar (XXXXXX): ");
          String ocrm = s.nextLine();
          System.out.print("Digite o endereço (se necessário): ");
          String endereco = s.nextLine();
          System.out.print("Digite o RG (se necessário) (XX.XXX.XXX-XX): ");
          String rg = s.nextLine();

          // Valida os dados e usa o comando para enviá-los ao banco //
          if(sistemaHospital.validaCPF(ocpf) && sistemaHospital.validaNome(onome) && sistemaHospital.validaCRM(ocrm)){
            sistemaHospital.cadastraMedico(ocpf, onome, ocrm);
            Medico m1 = sistemaHospital.localizaMedico(ocrm);
            m1.setEndereco(endereco);
            m1.setRg(rg);
            System.out.println("\nCadastrado!");
          }       
          else{
            System.out.println("\nVocê digitou algum dos seus dados no formato inválido... tente novamente!");
          }
        }
        // Recebe os dados//
        else if (resposta == 2){
          System.out.print("Digite o CPF do enfermeiro que você quer cadastrar (XXX.XXX.XXX-XX): ");
          String ocpf = s.nextLine();
          System.out.print("Digite o Nome do enfermeiro que você quer cadastrar: ");
          String onome = s.nextLine();
          System.out.print("Digite o endereço (se necessário): ");
          String endereco = s.nextLine();
          System.out.print("Digite o RG (se necessário) (XX.XXX.XXX-XX): ");
          String rg = s.nextLine();
          
          // Valida os dados e usa o comando para enviá-los ao banco//
          if(sistemaHospital.validaCPF(ocpf) && sistemaHospital.validaNome(onome)){
            sistemaHospital.cadastraEnfermeiro(ocpf, onome);
            Enfermeiro e1 = sistemaHospital.localizaEnfermeiro(ocpf);
            e1.setEndereco(endereco);
            e1.setRg(rg);
            System.out.println("\nCadastrado!");
          }       
          else{
            System.out.println("\nVocê digitou algum dos seus dados no formato inválido... tente novamente!");
          }
        }
        // Recebe os dados//
        else if (resposta == 3) {
          System.out.print("Digite o CPF do  fisioterapeuta que você quer cadastrar (XXX.XXX.XXX-XX): ");
          String ocpf = s.nextLine();
          System.out.print("Digite o Nome do fisioterapeuta que você quer cadastrar: ");
          String onome = s.nextLine();
          System.out.print("Digite o endereço (se necessário): ");
          String endereco = s.nextLine();
          System.out.print("Digite o RG (se necessário) (XX.XXX.XXX-XX): ");
          String rg = s.nextLine();

          // Valida os dados e usa o comando para enviá-los ao banco //
          if(sistemaHospital.validaCPF(ocpf) && sistemaHospital.validaNome(onome)){
            sistemaHospital.cadastraFisioterapeuta(ocpf, onome);   
            Fisioterapeuta f1 = sistemaHospital.localizaFisioterapeuta(ocpf);
            f1.setEndereco(endereco);
            f1.setRg(rg);
            System.out.println("\nCadastrado!");
          }       
          else{
            System.out.println("\nVocê digitou algum dos seus dados no formato inválido... tente novamente!");
          }
        }
        else {
          System.out.println("\nOpção inválida... tente novamente!");
        }
        Thread.sleep(1000);
      }
      // Localizar Funcionário
      else if (acao == 8) {
        System.out.println(enfeite);
        System.out.println("LOCALIZANDO FUNCIONÁRIO");
        System.out.println(enfeite);

        System.out.print("Indique o tipo de funcionário que você deseja localizar:\n1 - Médico\n2 - Enfermeiro\n3 - Fisioterapeuta");
        System.out.print("\nDigite o número da opção que você deseja: ");
        int resposta = s.nextInt(); s.nextLine();

        // Localizando Médico
        if(resposta == 1){
          System.out.print("\nDigite o CRM do Médico (XXXXXX): ");
          String crm = s.nextLine();
          boolean crmValido = sistemaHospital.validaCRM(crm);

          if (crmValido) {
            Medico medicoAtual = sistemaHospital.localizaMedico(crm);
            if (medicoAtual == null) {
              System.out.println("\nMédico não encontrado");
            } else {
              System.out.println("\nDados do médico:\nNome: " + medicoAtual.getNome() + "\nCPF: " + medicoAtual.getCpf());
            }
          } else {
            System.out.println("\nO CRM informado é inválido.");
          }

          
        }
        // Localizando Enfermeiro
        else if(resposta == 2){
          System.out.print("\nDigite o CPF do Enfermeiro: ");
          String cpf = s.nextLine();

          Enfermeiro enfermeiroAtual = sistemaHospital.localizaEnfermeiro(cpf);
          if (enfermeiroAtual == null) {
            System.out.println("\nEnfermeiro não encontrado");
          } else {
            System.out.println("\nDados do enfermeiro:\nNome: " + enfermeiroAtual.getNome() + "\nCPF: " + enfermeiroAtual.getCpf() );
          }
        }
        // Localiza Fisioterapeuta
        else if(resposta == 3){
          System.out.print("\nDigite o CPF do Fisioterapeuta: ");
          String cpf = s.nextLine();

          Fisioterapeuta fisioterapeutaAtual = sistemaHospital.localizaFisioterapeuta(cpf);
          if (fisioterapeutaAtual == null) {
            System.out.println("\nFisioterapeuta não encontrado");
          } else {
            System.out.println("\nDados do fisioterapeuta:\nNome: " + fisioterapeutaAtual.getNome() + "\nCPF: " + fisioterapeutaAtual.getCpf() );
          }
        }
        else{
          System.out.println("\nOpção inválida... Tente novamente!");
        }
        Thread.sleep(1000);
      }
      // Recuperar Nome
      else if (acao == 9) {
        System.out.println(enfeite);
        System.out.println("RECUPERANDO NOME");
        System.out.println(enfeite);

        System.out.print("CPF da pessoa: "); s.nextLine();
        String cpf = s.nextLine(); 
        System.out.print("Nome da pessoa: ");
        String nome = s.nextLine();

        if (sistemaHospital.validaCPF(cpf) && sistemaHospital.validaNome(nome)) {
          String resposta = sistemaHospital.recuperaNome(cpf);
          System.out.println(resposta);
        } else {
          System.out.println("Você digitou pelo menos um dos dados de forma inválida. Tente novamente");
        }
        Thread.sleep(1000);
      }
      // Alta da UTI
      else if (acao == 10) {

        System.out.println(enfeite);
        System.out.println("ALTA DA UTI");
        System.out.println(enfeite);

        System.out.print("Digite o CPF do Paciente: "); s.nextLine();
        String cpf = s.nextLine();
        boolean cpfValido = sistemaHospital.validaCPF(cpf);

        if (cpfValido) {
          Paciente p = sistemaHospital.localizaPaciente(cpf);
          if (p == null) {
            System.out.println("Paciente não cadastrado no sistema");
          } else {
            sistemaHospital.altaDaUti(p);
          }
        } else {
          System.out.println("CPF inválido. Tente novamente");
        }
        Thread.sleep(1000);
      }
      // Cadastrar Paciente
      else if (acao == 11) {

        System.out.println(enfeite);
        System.out.println("CADASTRANDO UM PACIENTE");
        System.out.println(enfeite);

        // Como a pessoa está adicionando um novo paciente, vamos considerar que ele não está internado.
        // Logo, 'estaNaUti' será false
        System.out.print("Digite o nome do seu paciente: "); s.nextLine();
        String nomePaciente = s.nextLine();
        System.out.print("Digite o CPF do seu paciente (XXX.XXX.XXX-XX): ");
        String cpfPaciente = s.nextLine();
        System.out.print("Digite o endereço do seu paciente: ");
        String enderecoPaciente = s.nextLine();
        System.out.print("Digite o RG do seu paciente (XX.XXX.XXX-XX): ");
        String rgPaciente = s.nextLine();
        System.out.print("Digite o procedimento do seu paciente (se quiser): ");
        String procedimentoPaciente = s.nextLine().trim();

        if (procedimentoPaciente.equals("")) {
          procedimentoPaciente = "inexistente";
        }

        System.out.print("Altura em centímetros: ");
        int altura = s.nextInt();
        System.out.print("Peso em quilos: ");
        double peso = s.nextDouble();

        // Variaveis de validação
        boolean validaNome = sistemaHospital.validaNome(nomePaciente);
        boolean validaCPF = sistemaHospital.validaCPF(cpfPaciente);
        boolean validaEndereco = sistemaHospital.validaNome(enderecoPaciente);
        boolean validaRg = sistemaHospital.validaRG(rgPaciente);

        Thread.sleep(1000);

        if (validaNome && validaCPF && validaEndereco && validaRg) {
          // Verificando se já existe um Paciente com o mesmo CPF e/ou RG
          boolean verificaCPF = sistemaHospital.verificaCPF(cpfPaciente);
          boolean verificaRG = sistemaHospital.verificaRG(rgPaciente);

          if (verificaCPF) {
            System.out.println("Já existe um paciente cadastrado com esse CPF.");
          } else if (verificaRG) {
            System.out.println("Já existe um paciente cadastrado com esse RG.");
          } else {
            sistemaHospital.cadastraPaciente(cpfPaciente, nomePaciente, enderecoPaciente);
            Paciente paciente = sistemaHospital.localizaPaciente(cpfPaciente);
  
            paciente.setAltura(altura); paciente.setPeso(peso); paciente.setProcedimento(procedimentoPaciente); paciente.setRg(rgPaciente);
  
            System.out.println("Cadastrado com sucesso!");
          }
        } else{
          System.out.println("Você digitou algum dos campos de forma errada, tente novamente!");
        }
        Thread.sleep(1000);
      }
      // Localizar Paciente
      else if (acao == 12) {

        System.out.println(enfeite);
        System.out.println("LOCALIZANDO PACIENTE");
        System.out.println(enfeite);

        System.out.print("Digite o CPF do paciente que você deseja encontrar (XXX.XXX.XXX-XX): "); s.nextLine();
        String cpfpaciente = s.nextLine();

        if(sistemaHospital.validaCPF(cpfpaciente)){
          Paciente p = sistemaHospital.localizaPaciente(cpfpaciente);

          if (p == null) {
            System.out.println("Não temos pacientes cadastrados com esse CPF");
          } else {
            System.out.println("Seguem os dados do paciente:\nCPF: "+p.getCpf()+"\nNome: "+p.getNome()+"\nEndereço: "+p.getEndereco()+"\nRG: "+p.getRg()+"\nPeso: "+p.getAltura()+"\nUTI: "+p.estaNaUti()+"\nProcedimento: "+p.getProcedimento());
            Thread.sleep(10000);
          }
        }else{
          System.out.println("Você errou em algum dos campos, tente novamente!");
          Thread.sleep(1000);
        }
      }
      // Listar Pacientes Internados
      else if (acao == 13) {
        System.out.println(enfeite);
        System.out.println("PACIENTES INTERNADOS ATÉ O MOMENTO");
        System.out.println(enfeite);

        ArrayList<Paciente> pacientesInternados = sistemaHospital.listagemDePacientesInternados();

        System.out.println("Confira abaixo os pacientes internados no momento: \n");
        for (Paciente internado : pacientesInternados) {
          System.out.println("- " + internado.getNome());
          Thread.sleep(1000);
        }
        
        Thread.sleep(1000);
      }
      // Listar Funcionários
      else if (acao == 14) {
        System.out.println(enfeite);
        System.out.println("FUNCIONÁRIOS CADASTRADOS");
        System.out.println(enfeite);

        ArrayList<Funcionario> funcionarios = sistemaHospital.listagemDeFuncionarios();

        for (Funcionario f : funcionarios) {
          System.out.println(f.getNome() + " - " + f.getClass().getSimpleName());
          Thread.sleep(1000);
        }
      }
      // Atendimento 
      else if (acao == 15) {
        System.out.println(enfeite);
        System.out.println("ATENDIMENTO");
        System.out.println(enfeite);

        System.out.print("Digite o CPF do funcionário: "); s.nextLine();
        String cpfFuncionario = s.nextLine();
        System.out.print("Digite o CPF do Paciente: ");
        String cpfPaciente = s.nextLine();

        if (sistemaHospital.validaCPF(cpfFuncionario) && sistemaHospital.validaCPF(cpfPaciente)) {
          sistemaHospital.atendimento(cpfFuncionario, cpfPaciente);
          Thread.sleep(10000);
          System.out.println("Fim do atendimento.");
        } else {
          System.out.println("Você digitou algum dos campos de forma errada. Tente novamente.");
          Thread.sleep(1000);
        }
      }
      // Confirmar Endereço
      else if (acao == 16) {}
      // Internar Paciente
      else if (acao == 17) {}
      // Encerrando o Programa
      else {
        System.out.println("Sistema Encerrado!");
        break;
      }
    }

    // Finalizando o Sistema do Hospital
    sistemaHospital.finalizaSistema();
    System.out.println("\nSistema finalizado com sucesso! Obrigado e volte sempre!");
    System.out.print(enfeite);
  }
}
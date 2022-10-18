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
      System.out.println("1  - Alterar Nomes\n2  - Alterar Endereços\n3  - Atualizar Altura do Paciente\n4  - Atualizar Peso do Paciente\n5  - Listar Número de Pacientes internados \n6  - Listar Número de Funcionários\n7  - Cadastrar Funcionário\n8  - Localizar Funcionários\n9  - Confirmar Nome\n10 - Soltar uma Alta da UTI\n11 - Cadastrar Paciente\n12 - Localizar Paciente\n13 - Listar Pacientes Internados\n14 - Listar Funcionários\n15 - Atendimento Normal\n16 - Atendimento com Médico\n17 - Confirmar Endereço\n18 - Internar Paciente\n19 - Fechar o Programa");
      
      // Validando a escolha do usuário
      int acao;
      do {
        System.out.print("Digite a sua escolha: ");
        acao = s.nextInt();
      } while (acao < 1 || acao > 19);

      // Pausando o programa por 1 segundo
      Thread.sleep(1000);

      // Alterar Nomes
      if (acao == 1) {
        System.out.println(enfeite);
        System.out.println("MODIFICANDO NOME DE UMA PESSOA");
        System.out.println(enfeite);

        System.out.print("Informe o CPF da pessoa que se deseja alterar o nome: ");
        String cpf = s.nextLine(); s.nextLine();

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
        System.out.print("Informe o CPF da pessoa que se deseja alterar o endereço: ");
        String cpf = s.nextLine(); s.nextLine();
        System.out.print("Informe o novo endereço: ");
        String novoEndereco = s.nextLine();
        
        sistemaHospital.alteraEndereco(cpf, novoEndereco);
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
      // Confimar Nome
      else if (acao == 9) {
        System.out.print("CPF da pessoa: "); s.nextLine();
        String cpf = s.nextLine(); 
        System.out.print("Nome da pessoa: ");
        String nome = s.nextLine();

        if (sistemaHospital.validaCPF(cpf) && sistemaHospital.validaNome(nome)) {
          String resposta = sistemaHospital.confirmaNome(cpf, nome);
          System.out.println(resposta);
        } else {
          System.out.println("Você digitou pelo menos um dos dados de forma inválida. Tente novamente");
        }
        Thread.sleep(1000);
      }
      // Alta da UTI
      else if (acao == 10) {
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
      else if (acao == 11) {}
      // Localizar Paciente
      else if (acao == 12) {}
      // Listar Pacientes Internados
      else if (acao == 13) {}
      // Listar Funcionários
      else if (acao == 14) {}
      // Atendimento Normal
      else if (acao == 15) {}
      // Atendimento com Médico
      else if (acao == 16) {}
      // Confirmar Endereço
      else if (acao == 17) {}
      // Internar Paciente
      else if (acao == 18) {}
      // Encerrando o Programa
      else {
        System.out.println("Sistema Encerrado!");
        break;
      }
    }

    // Finalizando o Sistema do Hospital
    sistemaHospital.finalizaSistema();
    System.out.println("\nSistema finalizado com sucesso! Obrigado e volte sempre!");
    System.out.println(enfeite);
  }
}
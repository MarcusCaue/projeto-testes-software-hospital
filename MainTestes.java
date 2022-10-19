import java.util.*;
import classes.*;

public class MainTestes {
  public static void main(String[] args) {
    Scanner s = new Scanner(System.in);

    Sistema sistemaHospital = new Sistema();
    sistemaHospital.inicializaSistema();

    // sistemaHospital.cadastraPaciente("433.434.544-00", "Weidson Costa", "Rua Matematica");
    // Paciente p1 = sistemaHospital.localizaPaciente("918.321.533-12");
    // System.out.println(p1);

    // sistemaHospital.atendimento("123.456.239-10", "099.322.553-43");

    sistemaHospital.atendimentoMedico("554233", "099.322.553-43");

    sistemaHospital.finalizaSistema();



  }

}

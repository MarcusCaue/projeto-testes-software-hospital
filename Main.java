import classes.*;
import interfaces.*;
import java.util.*;

class Main {
  public static void main(String[] args) {
    Hospital hp1 = new Hospital("Hospital da Grécia");

    Paciente p1 = new Paciente("123.456.789-10", "Jonas", "Brabos");
    p1.setProcedimento("Alta");
    Paciente p2 = new Paciente("987.654.321-09", "Amanda", "Brabos");
    p2.setProcedimento("Fila de Espera");
    Paciente p3 = new Paciente("121.343.565-90", "Kleito", "Brabos");
    p3.setProcedimento("Internado");

    Fisioterapeuta f1 = new Fisioterapeuta("897.323.564-12", "Jô Soares Lima");
    Enfermeiro e1 = new Enfermeiro("987.321.322-32", "Mariana Oliveira Silva");
    Medico m1 = new Medico("431.553.667-22", "Lídia Antônia", "4532");

    hp1.addPaciente(p1); hp1.addPaciente(p2); hp1.addPaciente(p3);
    hp1.addFuncionario(f1); hp1.addFuncionario(e1); hp1.addFuncionario(m1);

    System.out.println(hp1.getPacientes());
    System.out.println(hp1.getFuncionarios());

  }
}
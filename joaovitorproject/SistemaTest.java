import org.junit.*;
import junit.*;
import static org.junit.Assert.*;

public class SistemaTest {
    Sistema sistema;

    // @Before
    // void setUpBeforeAll() {
    //   this.sistema = new Sistema();
    //   this.sistema.inicializaSistema();

    //   Paciente p1 = new Paciente("123.456.789-10", "Jonas de Samos", "Rua dos Filósofos Brabos");
    //   Enfermeiro e1 = new Enfermeiro("111.222.333-45", "Jéssica Dias");
    //   Fisioterapeuta f1 = new Fisioterapeuta("222.333.444-10", "Olivia");
    //   Medico m1 = new Medico("333.444.555-77", "Kamila Silva", "554231");

    //   this.sistema.h.addFuncionario(m1);
    //   this.sistema.h.addFuncionario(e1);
    //   this.sistema.h.addFuncionario(f1);
    //   this.sistema.h.addPaciente(p1);
    // }

    // @After
    // void setDownAfterAll() {
    //   this.sistema.finalizaSistema();
    // }

    @Test
    public void cadastraPacienteTest() throws Exception {
        // setUpBeforeAll();

        boolean condition = false;

        assertEquals("Jonas", condition);
    }

    @Test
    public void pegaAiPoh() throws Exception {
        // setUpBeforeAll();

        boolean condition = true;

        assertTrue(condition);
    }



}
import classes.*; // Permite utilizar as classes criadas ao longo do Projeto

// Importando o JUnit
import org.junit.*;
import junit.*;
import static org.junit.Assert.*;

// Classe de teste e seus métodos
public class SistemaTest {

  @Test
  public void testName() {
    assertEquals("Jonas", "Jonas");
  }

}

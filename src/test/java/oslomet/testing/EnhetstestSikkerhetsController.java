package oslomet.testing;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mock.web.MockHttpSession;
import oslomet.testing.DAL.BankRepository;
import oslomet.testing.Sikkerhet.Sikkerhet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class EnhetstestSikkerhetsController {

    @InjectMocks
    // denne skal testes
    private Sikkerhet sikkerhetsController;

    @Mock
    // denne skal Mock'es
    private BankRepository repository;

    @Mock
    // denne skal Mock'es
    private MockHttpSession session;

    @Test
    public void test_sjekkLoggetInn() {
        // arrange
        when(repository.sjekkLoggInn(anyString(),anyString())).thenReturn("OK");

        // setningen under setter ikke attributten, dvs. at det ikke er mulig å sette en attributt i dette oppsettet
        session.setAttribute("Innlogget", "12345678901");

        // act
        String resultat = sikkerhetsController.sjekkLoggInn("12345678901","HeiHeiHei");
        // assert
        assertEquals("OK", resultat);
    }
    @Test
    public void test_sjekkLoggInnAdmin(){


        when(repository.sjekkLoggInn(anyString(),anyString())).thenReturn("Logget inn");

        session.setAttribute("Innlogget", "Admin");
        String results = sikkerhetsController.loggInnAdmin("Admin", "Admin");

        assertEquals("Logget inn", results);

    }
    @Test
    public void test_sjekkLoggInnAdminFeil(){
        when(repository.sjekkLoggInn(anyString(),anyString())).thenReturn("Ikke logget inn");

        session.setAttribute("Innlogget", "Admin");

        String resluts = sikkerhetsController.loggInnAdmin("Admin", "Admin123");
        String resluts1 = sikkerhetsController.loggInnAdmin("Admin2", "Admin");

        assertEquals("Ikke logget inn", resluts);
        assertEquals("Ikke logget inn", resluts1);

    }
    @Test
    public void test_sjekkFeilPersonnummerPassord(){
        when(repository.sjekkLoggInn(anyString(),anyString())).thenReturn("OK");
        session.setAttribute("Innlogget", "12345678901");
        String resluts = sikkerhetsController.sjekkLoggInn("1235678901", "HeiHeiHei");
        assertEquals("OK", resluts);
    }
}
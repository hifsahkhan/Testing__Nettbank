package oslomet.testing;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import org.springframework.mock.web.MockHttpSession;
import oslomet.testing.DAL.BankRepository;
import oslomet.testing.Sikkerhet.Sikkerhet;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertAll;
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
    public void loggInn_FeilPersonnummer(){
        session.setAttribute("Ikke innlogget","12345678901");

        // arrange
        when(repository.sjekkLoggInn(anyString(),anyString())).thenReturn("Feil i personnummer");

        //act
        String resultat = sikkerhetsController.sjekkLoggInn("1234567890","HeiHeiHei");

        //assert
        assertEquals("Feil i personnummer",resultat);
    }

    @Test
    public void loggInn_FeilPassord(){
        session.setAttribute("Ikke innlogget", "12345678901");
        session.setAttribute("Ikke innlogget", "HeiHeiHei");


        // arrange
        when(repository.sjekkLoggInn(anyString(),anyString())).thenReturn("Feil i passord");

        //act
        String resultat = sikkerhetsController.sjekkLoggInn("12345678901","HeiHei");

        //assert
        assertEquals("Feil i passord",resultat);

    }

    @Test
    public void loggInn_FeilPersonnummerEllerPassord(){
    //    session.setAttribute("");
        // arrange
        when(repository.sjekkLoggInn(anyString(),anyString())).thenReturn("Feil i personnummer eller passord");

        //act
        String resultat = sikkerhetsController.sjekkLoggInn("12345678901","HeiHeiHei");

        //assert
        assertEquals("Feil i personnummer eller passord",resultat);

    }





}

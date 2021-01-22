package oslomet.testing;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import oslomet.testing.API.AdminKundeController;
import oslomet.testing.API.BankController;
import oslomet.testing.DAL.AdminRepository;
import oslomet.testing.DAL.BankRepository;
import oslomet.testing.Models.Konto;
import oslomet.testing.Models.Kunde;
import oslomet.testing.Sikkerhet.Sikkerhet;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EnhetstestAdminKundeController {

    @InjectMocks
    // denne skal testes
    private AdminKundeController adminKundeController;

    @Mock
    // denne skal Mock'es
    private AdminRepository repository;

    @Mock
    // denne skal Mock'es
    private Sikkerhet sjekk;

    @Test
    public void hentAlleKunderOk() {

        Kunde kunde1 = new Kunde("01010110523", "Line", "Jensen",
                "Osloveien 82", "0850", "Oslo",
                "99462336", "HeiHei123");
        Kunde kunde2 = new Kunde("01070230525", "Ola", "Normann",
                "Bærumsveien 2", "0855", "Oslo",
                "41662377", "123HeiHei");
        List<Kunde> kundeliste = new ArrayList<>();
        kundeliste.add(kunde1);
        kundeliste.add(kunde2);

        when(sjekk.loggetInn()).thenReturn("01010110523");

        // arrange
        Mockito.when(repository.hentAlleKunder()).thenReturn(kundeliste);

        // act
        List<Kunde> resultat = adminKundeController.hentAlle();

        // assert
        assertEquals(kundeliste, resultat);

    }

    @Test
    public void hentAlleKunderFeil() {

        when(sjekk.loggetInn()).thenReturn("01010110523");

        // arrage
        Mockito.when(repository.hentAlleKunder()).thenReturn(null);

        // act
        List<Kunde> resultat = adminKundeController.hentAlle();

        // assert
        assertNull(resultat);
    }

    @Test
    public void lagreKundeOk() {

        Kunde kunde1 = new Kunde("01010110523", "Line", "Jensen",
                "Osloveien 82", "0850", "Oslo",
                "99462336", "HeiHei123");

        when(sjekk.loggetInn()).thenReturn("01010110523");

        //arrange
        Mockito.when(repository.registrerKunde(any(Kunde.class))).thenReturn("OK");

        // act
        String resultat = adminKundeController.lagreKunde(kunde1);

        // assert
        assertEquals("OK", resultat);

    }

    @Test
    public void lagreKundeFeil() {

        Kunde kunde1 = new Kunde("01010110523", "Line", "Jensen",
                "Osloveien 82", "0850", "Oslo",
                "99462336", "HeiHei123");

        when(sjekk.loggetInn()).thenReturn("01010110523");


        //arrange
        Mockito.when(repository.registrerKunde(any(Kunde.class))).thenReturn("Ikke logget inn");

        // act
        String resultat = adminKundeController.lagreKunde(kunde1);

        // assert
        assertEquals("Ikke logget inn", resultat);
    }

    @Test
    public void endre() {
        Kunde kunde1 = new Kunde("01010110523", "Line", "Jensen",
                "Osloveien 82", "0850", "Oslo",
                "99462336", "HeiHei123");

        when(sjekk.loggetInn()).thenReturn("01010110523");

        //arrange
        Mockito.when(repository.endreKundeInfo(any(Kunde.class))).thenReturn("OK");

        // act
        String resultat = adminKundeController.endre(kunde1);

        // arrange
        assertEquals("OK", resultat);
    }

    @Test
    public void endreFeil() {
        Kunde kunde1 = new Kunde("01010110523", "Line", "Jensen",
                "Osloveien 82", "0850", "Oslo",
                "99462336", "HeiHei123");

         when(sjekk.loggetInn()).thenReturn(null);

        //arrange
        Mockito.when(repository.endreKundeInfo(any(Kunde.class))).thenReturn("Ikke logget inn");

        // act
        String resultat = adminKundeController.endre(kunde1);

        // arrange
        assertEquals("Ikke logget inn", resultat);
    }

    @Test
    public void slett() {
        String p = "01010110523";

        when(sjekk.loggetInn()).thenReturn(p);

        //arrange
        Mockito.when(repository.slettKunde(p)).thenReturn("OK"); // eller anyString()?

        // act
        String resultat = adminKundeController.slett(p);

        // arrange
        assertEquals("OK", resultat);
    }

    @Test
    public void slettFeil() {
        when(sjekk.loggetInn()).thenReturn(null);

        //arrange
        Mockito.when(repository.slettKunde(null)).thenReturn("Ikke logget inn");

        // act
        String resultat = adminKundeController.slett(null);

        // arrange
        assertEquals("Ikke logget inn", resultat);
    }
}

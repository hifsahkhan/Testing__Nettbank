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
    public void hentAlleKunderOk(){

        // arrange
        Kunde kunde1 = new Kunde("01010110523", "Line", "Jensen",
                "Osloveien 82", "0850", "Oslo",
                "99462336", "HeiHei123");
        Kunde kunde2 = new Kunde("01070230525", "Ola", "Normann",
                "Bærumsveien 2", "0855", "Oslo",
                "41662377", "123HeiHei");
        List<Kunde>kundeliste= new ArrayList<>();
        kundeliste.add(kunde1);
        kundeliste.add(kunde2);

        when(sjekk.loggetInn()).thenReturn("01010110523");

        Mockito.when(repository.hentAlleKunder()).thenReturn(kundeliste);

        List<Kunde> resultat = adminKundeController.hentAlle();

        Assert.assertEquals(kundeliste, resultat);

    }

    @Test
    public void hentAlleKunderFeil(){

        // arrage
        Mockito.when(repository.hentAlleKunder()).thenReturn(null);

        // act
        List<Kunde> resultat = adminKundeController.hentAlle();

        // assert
        assertNull(resultat);
    }
}

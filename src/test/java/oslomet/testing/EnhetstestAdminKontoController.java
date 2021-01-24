package oslomet.testing;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import oslomet.testing.API.AdminKontoController;
import oslomet.testing.API.BankController;
import oslomet.testing.DAL.AdminRepository;
import oslomet.testing.DAL.BankRepository;
import oslomet.testing.Models.Konto;
import oslomet.testing.Models.Kunde;
import oslomet.testing.Models.Transaksjon;
import oslomet.testing.Sikkerhet.Sikkerhet;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EnhetstestAdminKontoController {

    @InjectMocks
    // denne skal testes
    private AdminKontoController adminKontoController;

    @Mock
    // denne skal Mock'es
    private AdminRepository repository;


    @Mock
    // denne skal Mock'es
    private Sikkerhet sjekk;

    @Test
    public void hentAlleKonti(){

    }

    @Test
    public void hentAlleKontiFeil(){

    }

    @Test
    public void registrerKonto(){
        ArrayList<Konto> konto = new ArrayList<>();
        ArrayList<Transaksjon>transaksjoner = new ArrayList<>();

        Transaksjon t = new Transaksjon(20, "1234.56.78999",1200, "04.01.21", "Tilbakebetaling", "Avventer","1111.11.1111");
        transaksjoner.add(t);

        Konto konto1 = new Konto("01010110523", "1309.34.23456", 12_901.35, "Type?", "Euro", transaksjoner);
        konto.add(konto1);

        String p = "01010110523";

        when(sjekk.loggetInn()).thenReturn(p);

        //arrange
        Mockito.when(repository.registrerKonto(konto1)).thenReturn("Ok");

        //act
        String resultat = adminKontoController.registrerKonto(konto1);

        //arrange
        assertEquals("Ok", resultat);
    }

    @Test
    public void registrerKontoFeil(){
        ArrayList<Konto> konto = new ArrayList<>();
        ArrayList<Transaksjon>transaksjoner = new ArrayList<>();

        Transaksjon t1 = new Transaksjon(20, "1234.56.78999",1200, "04.01.21", "Tilbakebetaling", "Avventer","1111.11.1111");
        Transaksjon t2 = new Transaksjon(30, "1234.56.79999",1300, "04.01.21", "Tilbakebetaling", "Avventer","1111.12.1111");
        transaksjoner.add(t1);
        transaksjoner.add(t2);

        Konto konto1 = new Konto("01010110523", "1309.34.23456", 12_901.35, "Type?", "Euro", transaksjoner);
        konto.add(konto1);

        when(sjekk.loggetInn()).thenReturn(null);

        //arrange
        Mockito.when(repository.registrerKonto(konto1)).thenReturn("Ikke innlogget");

        //act
        String resultat = adminKontoController.registrerKonto(konto1);

        //arrange
        assertEquals("Ikke innlogget", resultat);
    }

    @Test
    public void endreKonto(){
        ArrayList<Konto> konto = new ArrayList<>();
        ArrayList<Transaksjon>transaksjoner = new ArrayList<>();

        Transaksjon t1 = new Transaksjon(20, "1234.56.78999",1200, "04.01.21", "Tilbakebetaling", "Avventer","1111.11.1111");
        Transaksjon t2 = new Transaksjon(30, "1234.56.79999",1300, "04.01.21", "Tilbakebetaling", "Avventer","1111.12.1111");
        transaksjoner.add(t1);
        transaksjoner.add(t2);

        Konto konto1 = new Konto("01010110523", "1309.34.23456", 12_901.35, "Type?", "Euro", transaksjoner);
        konto.add(konto1);

        String p = "01010110523";

        when(sjekk.loggetInn()).thenReturn(p);

        //arrange
        Mockito.when(repository.endreKonto(konto1)).thenReturn("Ok");

        //act
        String resultat = adminKontoController.endreKonto(konto1);

        //arrange
        assertEquals("Ok", resultat);
    }

    @Test
    public void endreKontoFeil(){
        ArrayList<Konto> konto = new ArrayList<>();
        ArrayList<Transaksjon>transaksjoner = new ArrayList<>();

        Transaksjon t1 = new Transaksjon(20, "1234.56.78999",1200, "04.01.21", "Tilbakebetaling", "Avventer","1111.11.1111");
        Transaksjon t2 = new Transaksjon(30, "1234.56.79999",1300, "04.01.21", "Tilbakebetaling", "Avventer","1111.12.1111");
        transaksjoner.add(t1);
        transaksjoner.add(t2);

        Konto konto1 = new Konto("01010110523", "1309.34.23456", 12_901.35, "Type?", "Euro", transaksjoner);
        konto.add(konto1);

        when(sjekk.loggetInn()).thenReturn(null);

        //arrange
        Mockito.when(repository.endreKonto(konto1)).thenReturn("Ikke innlogget");

        //act
        String resultat = adminKontoController.endreKonto(konto1);

        //arrange
        assertEquals("Ikke innlogget", resultat);
    }

    @Test
    public void slettKonto(){

    }

    @Test
    public void slettKontoFeil(){

    }

}

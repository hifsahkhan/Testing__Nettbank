package oslomet.testing.API;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import oslomet.testing.DAL.AdminRepository;
import oslomet.testing.Models.Konto;
import oslomet.testing.Sikkerhet.Sikkerhet;

import java.util.List;

@RestController
@RequestMapping("/adminKonto")
public class AdminKontoController {
    @Autowired
    AdminRepository repository;

    @Autowired
    private Sikkerhet sjekk;

    @GetMapping("/hentAlle")                // Marius
    public List<Konto> hentAlleKonti() {
        String personnummer = sjekk.loggetInn();
        if (personnummer!=null) {
            return repository.hentAlleKonti();
        }
        return null;
    }

    @PostMapping("/registrer")          // Marius
    public String registrerKonto( Konto konto) {
        String personnummer = sjekk.loggetInn();
        if (personnummer!=null) {
            String retur = repository.registrerKonto(konto);
            return retur;
        }
        return "Ikke innlogget";
    }

    @PostMapping("/endre")      // Marius
    public String endreKonto( Konto konto) {
        String personnummer = sjekk.loggetInn();
        if (personnummer!=null) {
            return repository.endreKonto(konto);
        }
        return "Ikke innlogget";
    }

    @GetMapping("/slett")       // Zuhuur
    public String slettKonto(String kontonummer) {
        String personnummer = sjekk.loggetInn();
        if (personnummer!=null) {
           return repository.slettKonto(kontonummer);
        }
        return "Ikke innlogget";
    }
}

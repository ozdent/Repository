package no.husbanken.laan.controller;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import no.husbanken.laan.model.Laanesoeknad;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import no.husbanken.laan.repository.LaanetakerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import javax.validation.Valid;

@RestController
@RequestMapping("/laan")
@Api(value = "minilaan", description = "Rest Api for innsending av søknader", tags = "Mini-lånesøknad")
public class LaaneSoeknadController {

    @Autowired
    private LaanetakerRepository laanetakerRepository;

    @RequestMapping(value = "/sendSoknad", method = RequestMethod.POST)
    @ApiOperation(value = "Denne tjenesten tar i mot en lånesøknad, lagrer denne og returnerer tilbake soknadsid", response = Number.class)
    public int sendSoknad(@Valid @RequestBody Laanesoeknad laanesoeknad) {
        laanetakerRepository.putLaaneSoknad(laanesoeknad) ;
        System.out.println("Registrert soknad: " + laanesoeknad.getSoknadsid());
        return laanesoeknad.getSoknadsid();
    }

    @RequestMapping(value = "/sendSoknadJSON", method = RequestMethod.POST, produces = "application/json")
    @ApiOperation(value = "Denne tjenesten tar i mot en lånesøknad, lagrer denne og returnerer tilbake søknaden med soknadsid", response = Laanesoeknad.class)
    public Laanesoeknad sendSoknadJSON(@Valid @RequestBody Laanesoeknad laanesoeknad) {
        laanetakerRepository.putLaaneSoknad(laanesoeknad) ;
        System.out.println("Registrert soknad: " + laanesoeknad.getSoknadsid());
        return laanesoeknad;
    }

    @RequestMapping(value = "/finnSoknad/{soknadsid}", method = RequestMethod.GET)
    @ApiOperation(value = "Denne tjenesten tar i mot et soknadsid og returnerer tilbake søknadsstatus", response = String.class)
    public String finnSoknad(@PathVariable int soknadsid) {
        String soknadsstatus = laanetakerRepository.hentStatus(soknadsid);
        System.out.println("Søknadsstatus: " + soknadsstatus);
        return soknadsstatus;
    }

    @RequestMapping(value = "/finnSoknadJSON/{soknadsid}", method = RequestMethod.GET)
    @ApiOperation(value = "Denne tjenesten tar i mot et soknadsid og returnerer tilbake HTTPStatus kode", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "MOTTATT"),
            @ApiResponse(code = 404, message = "UKJENT")
    }
    )
    public ResponseEntity<Laanesoeknad> finnSoknadJSON(@PathVariable int soknadsid) {
        Laanesoeknad laanesoeknad =   laanetakerRepository.hentLaaneSoknad(soknadsid);
        System.out.println("Funnet søknad: " + laanesoeknad);
        if(laanesoeknad != null ) {
               return new ResponseEntity<Laanesoeknad>(laanesoeknad,HttpStatus.OK);
        } else {
               return new ResponseEntity<Laanesoeknad>(HttpStatus.NOT_FOUND);
        }
    }
}

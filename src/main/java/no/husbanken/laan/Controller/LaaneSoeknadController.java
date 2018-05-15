package no.husbanken.laan.controller;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import no.husbanken.laan.model.Laanesoeknad;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import no.husbanken.laan.repository.LaanetakerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    final static Logger logger = LoggerFactory.getLogger(LaaneSoeknadController.class);

    @RequestMapping(value = "/lagreMottattSoknad", method = RequestMethod.POST, produces = "application/json")
    @ApiOperation(value = "Denne tjenesten tar i mot en lånesøknad, lagrer denne og returnerer tilbake soknadsid")
    public int lagreMottattSoknad(@Valid @RequestBody Laanesoeknad laanesoeknad) {
        laanetakerRepository.putLaaneSoknad(laanesoeknad) ;
        logger.info("Registrert soknad: " + laanesoeknad.getSoknadsid());
        return laanesoeknad.getSoknadsid();
    }

    @RequestMapping(value = "/finnSoknad/{soknadsid}", method = RequestMethod.GET,  produces = "application/json")
    @ApiOperation(value = "Denne tjenesten tar i mot et soknadsid og returnerer tilbake søknadsstatus")
    public String finnSoknad(@PathVariable int soknadsid) {
        String soknadsstatus = laanetakerRepository.hentStatus(soknadsid);
        logger.info(soknadsid + " er " + soknadsstatus);
        return "\"" + soknadsstatus + "\"";
    }

    @RequestMapping(value = "/finnSoknadHTTP/{soknadsid}", method = RequestMethod.GET, produces = "application/json")
    @ApiOperation(value = "Denne tjenesten tar i mot et soknadsid og returnerer tilbake HTTP-statuskode")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "MOTTATT"), @ApiResponse(code = 404, message = "UKJENT") })
    public ResponseEntity<Laanesoeknad> finnSoknadHTTP(@PathVariable int soknadsid) {
        Laanesoeknad laanesoeknad = laanetakerRepository.hentLaaneSoknad(soknadsid);
        if(laanesoeknad != null ) {
            logger.info(soknadsid + " er mottatt");
            return new ResponseEntity<Laanesoeknad>(laanesoeknad,HttpStatus.OK);
        } else {
            logger.info(soknadsid + " er ukjent");
            return new ResponseEntity<Laanesoeknad>(HttpStatus.NOT_FOUND);
        }
    }
}

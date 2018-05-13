package no.husbanken.laan.Controller;

import no.husbanken.laan.model.Laanesoeknad;
import no.husbanken.laan.repository.LaanetakerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;


// Flytt ut hashmap
@RestController
@RequestMapping("/laan")
//@Api(value = "laan", description = "Sample hello world application")
public class LaaneSoeknadRestController {

    @Autowired
    private LaanetakerRepository laanetakerRepository;

    private Map<Integer, Laanesoeknad> soknadsMap = new HashMap<Integer, Laanesoeknad>();

    private int soknadsid = 8000;


    @RequestMapping(value = "/sendSoknad", method = RequestMethod.POST)
 //   @ApiOperation(value = "Just to test the sample test api of My App Service")
    public Laanesoeknad sendSoknad(@Valid @RequestBody Laanesoeknad laanesoeknad) {
        laanesoeknad.setSoknadsid(++soknadsid);
        laanesoeknad.setStatus("Mottatt");       //Kan kommenteres ut
        soknadsMap.put(soknadsid, laanesoeknad) ;
        System.out.println("Registrert soknad: " + soknadsid);
        return laanesoeknad;
    }

    @RequestMapping(value = "/finnSoknad/{soknadsid}", method = RequestMethod.GET)
 //   @ApiOperation(value = "Just to test the sample test api of My App Service")
    public String finnSoknad(@PathVariable String soknadsid) {
        String response = laanetakerRepository.finnSoeknad(Integer.parseInt(soknadsid),soknadsMap);
        System.out.println("Søknaden er " + response);
        return response;
    }

    /**
     *
     * @param soknadsid
     * @return
     */
    @RequestMapping(value = "/finnSoknadJSON/{soknadsid}", method = RequestMethod.GET)
  //  @ApiOperation(value = "Just to test the sample test api of My App Service")
    public ResponseEntity<Laanesoeknad> finnSoknadJSON(@PathVariable String soknadsid) {
        Laanesoeknad laanesoeknad =  soknadsMap.get(Integer.parseInt(soknadsid));

        System.out.println("Treff: " + laanesoeknad);
        if(laanesoeknad != null ) {
            return new ResponseEntity<Laanesoeknad>(laanesoeknad,HttpStatus.OK);
           } else {
               return new ResponseEntity<Laanesoeknad>(HttpStatus.NOT_FOUND);
           }
    }
}

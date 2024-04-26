package guru.springframework.spring6restmvc.controller;

import guru.springframework.spring6restmvc.exception.DemoException;
import guru.springframework.spring6restmvc.exception.NotFoundException;
import guru.springframework.spring6restmvc.model.BeerDTO;
import guru.springframework.spring6restmvc.services.BeerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@RestController
public class BeerController {
    private final BeerService beerService;

    public static final String BEER_PATH = "/api/v1/beer";
    public static final String BEER_PATH_ID = BEER_PATH + "/{beerId}";


    @PatchMapping(BEER_PATH_ID)
    public ResponseEntity updateBeerPatchById(@PathVariable("beerId") UUID beerId,
                                              @RequestBody BeerDTO beer) {
        beerService.patchBeerById(beerId, beer);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(BEER_PATH_ID)
    public ResponseEntity deleteById(@PathVariable("beerId") UUID id) {
        if(!this.beerService.deleteById(id)) {
            throw new NotFoundException();
        };
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PutMapping(BEER_PATH_ID)
    public ResponseEntity updateById(@PathVariable("beerId") UUID beerId, @Validated @RequestBody BeerDTO beer) {
        if(beerService.updateBeerById(beerId, beer).isEmpty()) {
            throw new NotFoundException();
        }
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(method = RequestMethod.GET, value = BEER_PATH)
    public List<BeerDTO> listBeers() {
        return beerService.listBeers();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/api/testMock")
    public void getErrorBeer() {
        throw new NotFoundException();
    }

    @RequestMapping(value = BEER_PATH_ID, method = RequestMethod.GET)
    public BeerDTO getBeerById(@PathVariable("beerId") UUID beerId) {
        log.debug("Get Beer By Id");
        return beerService.getBeerById(beerId).orElseThrow(NotFoundException::new);
    }

    @PostMapping(value = BEER_PATH)
    // @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity handlePost(@Validated @RequestBody BeerDTO beer) {
        BeerDTO savedBeer = beerService.saveNewBeer(beer);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Location", "/api/v1/beer/" + savedBeer.getId().toString());
        return new ResponseEntity(responseHeaders, HttpStatus.CREATED);
    }

    /*
    Customized exception handling allowed for this controller for any 404 not found error,
    but only this controller, other controllers are not affected, move it to ControllerAdvice for
    so it impact all controllers
     */
    @ExceptionHandler(DemoException.class)
    public ResponseEntity<BeerDTO> handleDemoException() {
        BeerDTO newBeer = BeerDTO.builder().beerName("BigBeer").build();
        ResponseEntity<BeerDTO> responseEntity = new ResponseEntity(newBeer, HttpStatus.BAD_REQUEST);
        return responseEntity;
    }

    /*
    For the ExceptionController and NotFoundException class, you only need one, the former gives you
    more fine-grained control over what to throw in the error, the second just simplify that and just
    let you output response
     */

}

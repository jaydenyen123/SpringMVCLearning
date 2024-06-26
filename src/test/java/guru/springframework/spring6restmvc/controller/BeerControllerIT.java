package guru.springframework.spring6restmvc.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import guru.springframework.spring6restmvc.entities.Beer;
import guru.springframework.spring6restmvc.exception.NotFoundException;
import guru.springframework.spring6restmvc.mappers.BeerMapper;
import guru.springframework.spring6restmvc.model.BeerDTO;
import guru.springframework.spring6restmvc.model.BeerStyle;
import guru.springframework.spring6restmvc.repositories.BeerRepository;
import jakarta.transaction.Transactional;
import org.hamcrest.core.IsNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class BeerControllerIT {

    @Autowired
    BeerRepository beerRepository;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    private BeerController beerController;

    @Autowired
    BeerMapper beerMapper;

    @Autowired
    WebApplicationContext wac;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    void testListBeersByStyleAndNameShowInventoryTruePage2() throws Exception {
        mockMvc.perform(get(BeerController.BEER_PATH)
                .queryParam("beerName", "IPA")
                .queryParam("beerStyle", BeerStyle.IPA.name())
                .queryParam("showInventory", "true")
                .queryParam("pageNumber", "2")
                .queryParam("pageSize", "50"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.length()", is(50)))
                .andExpect(jsonPath("$.content.[0].quantityOnHand").value(IsNull.notNullValue()));
    }

    @Test
    void testListBeersByNameAndBeerStyle() throws Exception {
        mockMvc.perform(get(BeerController.BEER_PATH)
                        .queryParam("beerName", "IPA")
                        .queryParam("beerStyle", BeerStyle.IPA.name()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.length()", is(25)));
    }

    @Test
    void testListBeersByNameAndBeerStyleAndInventoryTrue() throws Exception {
        mockMvc.perform(get(BeerController.BEER_PATH)
                        .queryParam("beerName", "IPA")
                        .queryParam("beerStyle", BeerStyle.IPA.name())
                        .queryParam("showInventory", "true"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.length()", is(25)))
                .andExpect(jsonPath("$.content.[0].quantityOnHand").value(IsNull.notNullValue()));
    }

    @Test
    void testListBeersByNameAndBeerStyleAndInventoryFalse() throws Exception {
        mockMvc.perform(get(BeerController.BEER_PATH)
                        .queryParam("beerName", "IPA")
                        .queryParam("beerStyle", BeerStyle.IPA.name())
                        .queryParam("showInventory", "False"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.length()", is(25)))
                .andExpect(jsonPath("$.content.[0].quantityOnHand").value(IsNull.nullValue()));
    }


    @Test
    void testListBeersByName() throws Exception {
        mockMvc.perform(get(BeerController.BEER_PATH)
                .queryParam("beerName", "IPA"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.length()", is(25)));
    }

    @Test
    void testListBeersByBeerStyle() throws Exception {
        mockMvc.perform(get(BeerController.BEER_PATH)
                        .queryParam("beerStyle", BeerStyle.IPA.name()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.length()", is(25)));
    }

    @Test
    void testPatchBeerBadName() throws Exception {
        Beer beer = beerRepository.findAll().get(0);
        Map<String, Object> beerMap = new HashMap<>();
        beerMap.put("beerName", "New NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew Name, New NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew Name,New NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew NameNew Name");

        MvcResult result = mockMvc.perform(patch(BeerController.BEER_PATH_ID, beer.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(beerMap))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andReturn();
        System.out.println("Test is printing here");
        System.out.println(result.getResponse().getContentAsString());
    }

    @Rollback
    @Transactional
    @Test
    void deleteByIdFound() {
        Beer beer = beerRepository.findAll().get(0);
        ResponseEntity responseEntity = beerController.deleteById(beer.getId());
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.valueOf(204));
        assertThat(beerRepository.findById(beer.getId()).isEmpty());
    }

    @Rollback
    @Transactional
    @Test
    void deleteByIdNotFound() {
        assertThrows(NotFoundException.class,() -> {
            beerController.deleteById(UUID.randomUUID());
        });
    }

    @Test
    void testUpdateNotFound() {
        assertThrows(NotFoundException.class,() -> {
            beerController.updateById(UUID.randomUUID(), BeerDTO.builder().build());
        });
    }

    @Rollback
    @Transactional
    @Test
    void updateExistingBeer() {
        Beer beer = beerRepository.findAll().get(0);
        BeerDTO beerDTO = beerMapper.beerToBeerDto(beer);
        beerDTO.setId(null);
        beerDTO.setVersion(null);
        final String beerName = "UPDATED";
        beerDTO.setBeerName(beerName);

        ResponseEntity responseEntity = beerController.updateById(beer.getId(), beerDTO);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(204));

        Beer updatedBeer = beerRepository.findById(beer.getId()).get();
        assertThat(updatedBeer.getBeerName()).isEqualTo(beerName);
    }

    /* make sure test does not as DB need to rollback after beerController.handlePost(beerDTO); */
    @Transactional
    @Rollback
    @Test
    void saveNewBeerTest() {
        BeerDTO beerDTO = BeerDTO.builder()
                .beerName("New Beer")
                .build();

        ResponseEntity responseEntity = beerController.handlePost(beerDTO);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.valueOf(201));
        assertThat(responseEntity.getHeaders().getLocation()).isNotNull();

        String[] locationUUID = responseEntity.getHeaders().getLocation().getPath().split("/");
        UUID savedUUID = UUID.fromString(locationUUID[4]);

        Beer beer = beerRepository.findById(savedUUID).get();
        assertThat(beer).isNotNull();

    }

    @Test
    void testBeerIdNotFound() {
        assertThrows(NotFoundException.class, () -> {
            beerController.getBeerById(UUID.randomUUID());
        });
    }

    @Test
    void testGetById() {
        Beer beer = beerRepository.findAll().get(0);
        BeerDTO dto = beerController.getBeerById(beer.getId());
        assertThat(dto).isNotNull();
    }


    @Test
    void testListBeers() {
        Page<BeerDTO> dtos = beerController.listBeers(null, null, false, 1, 25);
        assertThat(dtos.getContent().size()).isEqualTo(25);
    }

    /* make sure test does not as DB need to rollback after beerRepository.deleteAll(); */
    @Rollback
    @Transactional
    @Test
    void testEmptyList() {
        beerRepository.deleteAll();
        Page<BeerDTO> dtos = beerController.listBeers(null, null, false, 1, 25);
        assertThat(dtos.getContent().size()).isEqualTo(0);
    }

}
package pl.jitsolution.jitstock.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import pl.jitsolution.jitstock.JitStockApplication;
import pl.jitsolution.jitstock.model.Category;
import pl.jitsolution.jitstock.model.OfferType;
import pl.jitsolution.jitstock.model.Quality;
import pl.jitsolution.jitstock.model.Unit;
import pl.jitsolution.jitstock.model.entity.Offer;
import pl.jitsolution.jitstock.repository.OfferRepository;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * Created by JIT on 03.07.2017.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = JitStockApplication.class)
@WebAppConfiguration
public class OfferControllerTest {

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    private MockMvc mockMvc;


    private HttpMessageConverter mappingJackson2HttpMessageConverter;

    @Autowired
    private OfferRepository offerRepository;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    void setConverters(HttpMessageConverter<?>[] converters) {

        this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream()
                .filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter)
                .findAny()
                .orElse(null);

        assertNotNull("the JSON message converter must not be null",
                this.mappingJackson2HttpMessageConverter);
    }

    @Before
    public void setup() throws Exception {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();

    }


    @Test
    public void readSingleOffer() throws Exception {
        //GIVEN
        Offer offer = new Offer();
        offer.setName("ABC1");
        offer.setOfferType(OfferType.SELL);
        offer.setCategory(Category.FRUITS);
        offer.setPrice(BigDecimal.valueOf(12.3));
        offer.setQuality(Quality.I);
        offer.setUnit(Unit.KG);

        Offer savedOffer = offerRepository.save(offer);

        //WHEN
        mockMvc.perform(get("/api/offer/" + savedOffer.getId()))
                //THEN
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.id", is(savedOffer.getId().intValue())))
                .andExpect(jsonPath("$.name", is(offer.getName())))
                .andExpect(jsonPath("$.offerType", is(offer.getOfferType().toString())))
                .andExpect(jsonPath("$.category", is(offer.getCategory().toString())))
                .andExpect(jsonPath("$.price").value(offer.getPrice()))
                .andExpect(jsonPath("$.quality", is(offer.getQuality().toString())))
                .andExpect(jsonPath("$.unit", is(offer.getUnit().toString())))
                .andExpect(jsonPath("$.publishDate", is(savedOffer.getPublishDate().toString())));
    }

    @Test
    public void readSingleOfferWhenOfferDoesNotExist() throws Exception {
        //GIVEN
        Offer offer = new Offer();
        offer.setName("ABC1");
        offer.setOfferType(OfferType.SELL);
        offer.setCategory(Category.FRUITS);
        offer.setPrice(BigDecimal.valueOf(2.1));
        offer.setQuality(Quality.I);
        offer.setUnit(Unit.KG);
        offer.setPublishDate(LocalDateTime.now());
        Offer savedOffer = offerRepository.save(offer);


        //WHEN
        mockMvc.perform(get("/api/offer/" + savedOffer.getId() + 1))
                //THEN
                .andExpect(status().isNotFound());
    }

    @Test
    public void readOffers() throws Exception {
        //GIVEN
        offerRepository.deleteAll();

        Offer offer1 = new Offer();
        offer1.setName("ABC1");
        offer1.setOfferType(OfferType.SELL);
        offer1.setCategory(Category.FRUITS);
        offer1.setPrice(BigDecimal.valueOf(8.1));
        offer1.setQuality(Quality.I);
        offer1.setUnit(Unit.KG);
        offer1.setPublishDate(LocalDateTime.now());

        Offer offer2 = new Offer();
        offer2.setName("ABC2");
        offer2.setOfferType(OfferType.BUY);
        offer2.setCategory(Category.VEGETABLES);
        offer2.setPrice(BigDecimal.valueOf(7.1));
        offer2.setQuality(Quality.II);
        offer2.setUnit(Unit.KG);
        offer2.setPublishDate(LocalDateTime.now());

        Offer savedOffer1 = offerRepository.save(offer1);
        Offer savedOffer2 = offerRepository.save(offer2);

        //WHEN
        mockMvc.perform(get("/api/offers"))
                //THEN
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(savedOffer1.getId().intValue())))
                .andExpect(jsonPath("$[0].name", is(offer1.getName())))
                .andExpect(jsonPath("$[0].offerType", is(offer1.getOfferType().toString())))
                .andExpect(jsonPath("$[0].category", is(offer1.getCategory().toString())))
                .andExpect(jsonPath("$[0].price").value(offer1.getPrice()))
                .andExpect(jsonPath("$[0].quality", is(offer1.getQuality().toString())))
                .andExpect(jsonPath("$[0].unit", is(offer1.getUnit().toString())))
                .andExpect(jsonPath("$[0].publishDate", is(savedOffer1.getPublishDate().toString())))
                .andExpect(jsonPath("$[1].id", is(savedOffer2.getId().intValue())))
                .andExpect(jsonPath("$[1].name", is(offer2.getName())))
                .andExpect(jsonPath("$[1].offerType", is(offer2.getOfferType().toString())))
                .andExpect(jsonPath("$[1].category", is(offer2.getCategory().toString())))
                .andExpect(jsonPath("$[1].price").value(offer2.getPrice()))
                .andExpect(jsonPath("$[1].quality", is(offer2.getQuality().toString())))
                .andExpect(jsonPath("$[1].unit", is(offer2.getUnit().toString())))
                .andExpect(jsonPath("$[1].publishDate", is(savedOffer2.getPublishDate().toString())));
    }

    @Test
    public void readOffersWhenNoContent() throws Exception {
        //GIVEN
        offerRepository.deleteAll();

        //WHEN
        mockMvc.perform(get("/api/offers"))
                //THEN
                .andExpect(status().isNoContent());
    }

    @Test
    public void createNewOffer() throws Exception {

        //GIVEN
        Offer offer = new Offer();
        offer.setName("ABC1");
        offer.setOfferType(OfferType.SELL);
        offer.setCategory(Category.FRUITS);
        offer.setPrice(BigDecimal.valueOf(8.7));
        offer.setQuality(Quality.I);
        offer.setUnit(Unit.KG);

        //WHEN
        String offerJson = json(offer);
        this.mockMvc.perform(post("/api/offer/create").contentType(contentType).content(offerJson))
                //THEN
                .andExpect(status().isCreated());
    }

    @Test
    public void createOfferWithGivenId() throws Exception {

        //GIVEN
        Offer offerWithId = new Offer();
        offerWithId.setId(5L);
        offerWithId.setName("ABC1");
        offerWithId.setOfferType(OfferType.SELL);
        offerWithId.setCategory(Category.FRUITS);
        offerWithId.setPrice(BigDecimal.valueOf(8.7));
        offerWithId.setQuality(Quality.I);
        offerWithId.setUnit(Unit.KG);

        //WHEN
        String offerJson = json(offerWithId);
        this.mockMvc.perform(post("/api/offer/create").contentType(contentType).content(offerJson))
                //THEN
                .andExpect(status().isBadRequest());
    }

    protected String json(Object o) throws IOException {
        MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
        this.mappingJackson2HttpMessageConverter.write(
                o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
        return mockHttpOutputMessage.getBodyAsString();
    }


}

package no.husbanken.laan.controller;
import no.husbanken.laan.repository.LaanetakerRepository;
import org.junit.runner.RunWith;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@WebMvcTest(value = LaaneSoeknadController.class, secure = false)
public class LaanSoeknadControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LaanetakerRepository repository;

    String exampleLaaneSoeknadJson = "{\n" +
            "  \"lanetakere\": [{ \"fnr\" : \"01056000069\", \"navn\" : \"Kari Nordmann\" }],\n" +
            "  \"lanebelop\": 2450000,\n" +
            "  \"behov\": \"Vi skal låne penger til........\"\n" +
            "}";

    @Test
    public void lagreMottattSoknad() throws Exception{
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(
                "/laan/lagreMottattSoknad")
                .accept(MediaType.APPLICATION_JSON).content(exampleLaaneSoeknadJson)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }


    @Test
    public void finnSoknadHTTPNeg() throws Exception{
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/laan/finnSoknadHTTP/7000")
                .accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
    }

}
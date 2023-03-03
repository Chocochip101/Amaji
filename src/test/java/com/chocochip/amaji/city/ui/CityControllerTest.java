package com.chocochip.amaji.city.ui;

import com.chocochip.amaji.city.service.CityService;
import com.chocochip.amaji.city.ui.dto.CityResponse;
import com.chocochip.amaji.global.util.CityEnum;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CityControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired private ObjectMapper objectMapper;
    @MockBean
    private CityService cityService;

    private static final CityResponse CITY_OSAKA =
            new CityResponse(CityEnum.OSAKA);

    private static final CityResponse CITY_TOKYO =
            new CityResponse(CityEnum.TOKYO);

    @DisplayName("모든 도시 조회에 성공한다.")
    @Test
    void getAllCities() throws Exception {
        // given & when
        List<CityResponse> cityResponseList = List.of(CITY_OSAKA, CITY_TOKYO);
        given(cityService.findAllCities()).willReturn(cityResponseList);

        // then
        mockMvc.perform(get("/cities").contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0]").exists())
                .andExpect(jsonPath("$[1]").exists())
                .andExpect(jsonPath("$[0].city_name").value("OSAKA"))
                .andExpect(jsonPath("$[1].city_name").value("TOKYO"))
                .andDo(print());
    }
}
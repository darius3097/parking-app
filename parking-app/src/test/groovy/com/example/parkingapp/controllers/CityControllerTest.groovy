package com.example.parkingapp.controllers

import com.example.parkingapp.UnitTestBase
import com.example.parkingapp.model.CityEntity
import com.example.parkingapp.model.dtos.CityDTO
import com.example.parkingapp.services.CityService
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import spock.mock.DetachedMockFactory

import static groovy.json.JsonOutput.toJson
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath

@WebMvcTest(controllers = [CityController])
class CityControllerTest extends UnitTestBase {
    @Autowired
    protected MockMvc mvc

    @Autowired
    CityService cityService

    @Autowired
    ObjectMapper objectMapper

    def "should return city requested and status 'ok' when method getById is called"() {
        given:
        cityService.getCityById(1L) >> Optional.of(CityEntity.builder()
                .id(1L)
                .name("Cluj")
                .code("CJ")
                .build())

        when:
        def results = mvc.perform(get("/city/{id}", 1))

        then:
        results.andExpect(status().isOk())

        and:
        results.andExpect(jsonPath('$.id').value(1))
        results.andExpect(jsonPath('$.name').value("Cluj"))
        results.andExpect(jsonPath('$.code').value("CJ"))

    }

    def "should save and return city and status 'created' when method saveCity is called"() {
        given:
        Map request = [
                name : 'Cluj',
                code : 'CJ'
        ]
        var city = CityDTO.builder()
                .name("Cluj")
                .code("CJ")
                .build()

        cityService.saveCity(city) >> city

        when:
        def results = mvc.perform(post("/city")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(request)))

        then:
        results.andExpect(status().isCreated())

        and:
        results.andExpect(jsonPath('$.name').value("Cluj"))
        results.andExpect(jsonPath('$.code').value("CJ"))

    }

    @TestConfiguration
    static class StubConfig {
        DetachedMockFactory detachedMockFactory = new DetachedMockFactory()

        @Bean
        CityService cityService() {
            return detachedMockFactory.Stub(CityService)
        }
    }
}

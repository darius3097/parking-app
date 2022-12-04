package com.example.parkingapp.services

import com.example.parkingapp.UnitTestBase
import com.example.parkingapp.exceptions.ResourceNotFoundException
import com.example.parkingapp.model.CityEntity
import com.example.parkingapp.model.dtos.CityDTO
import com.example.parkingapp.repositories.CityRepository

class CityServiceTest extends UnitTestBase {
    private CityRepository cityRepository
    private CityService cityService

    def setup() {
        cityRepository = Mock(CityRepository.class)
        cityService = new CityService(cityRepository)
    }

    def "it should return city when calling getCityById method"() {
        given:
        var city = CityEntity.builder()
                .id(1L)
                .name("Cluj")
                .code("CJ")
                .build()
        cityRepository.findById(1L) >> Optional.of(city)

        when:
        var cityReturned = cityService.getCityById(1L)

        then:
        assert cityReturned.get() == city
    }

    def "it should throw ResourceNotFoundException when the city is not found on DB"(){
        given:
        cityRepository.findById(1L) >> Optional.empty()

        when:
        cityService.getCityById(1L)

        then:
        thrown(ResourceNotFoundException)
    }

    def "it should return city when calling getCityByCode method"() {
        given:
        var city = CityEntity.builder()
                .id(1L)
                .name("Cluj")
                .code("CJ")
                .build()
        cityRepository.findCityEntityByCode("CJ") >> Optional.of(city)

        when:
        var cityReturned = cityService.getCityByCode("CJ")

        then:
        assert cityReturned.get() == city
    }

    def "it should throw ResourceNotFoundException when calling getCityByCode method  and the city is not found on DB"(){
        given:
        cityRepository.findCityEntityByCode("CJ") >> Optional.empty()

        when:
        cityService.getCityByCode("CJ")

        then:
        thrown(ResourceNotFoundException)
    }

    def "it should save the city when calling saveCity method"() {
        given:
        var city = CityEntity.builder()
                .id(1L)
                .name("Cluj")
                .code("CJ")
                .build()
        cityRepository.save(city) >> city
        cityRepository.findById(1L) >> Optional.empty()
        cityRepository.findCityEntityByCode("CJ") >> Optional.empty()

        when:
        var citySaved = cityService.saveCity(CityDTO.convertToDTO(city))

        then:
        assert citySaved == CityDTO.convertToDTO(city)
    }
}

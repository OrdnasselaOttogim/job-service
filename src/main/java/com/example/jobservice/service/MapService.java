package com.example.jobservice.service;

import com.example.jobservice.model.Itinerary;
import com.example.jobservice.model.Position;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
public class MapService {

    private final String base = "http://localhost:9080/api/v1/itinerary/";

    Position userAddress = new Position(46.06672925347145,11.149817900662818);
    Position jobAddress = new Position(46.06726990853974,11.12143499033619);
    private final RestTemplate restTemplate;

    @Autowired
    public MapService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    public Map<String, Itinerary> getItineraries(){
        Gson gson = new Gson();

        //convert json string to object
      /*  try {
            Itinerary itineraryWalk = objectMapper.readValue(restTemplate.getForObject(base+"walk?startLat="+userAddress.getLat()+"&startLong="+userAddress.getLng()+"&endLat="+jobAddress.getLat()+"&endLong="+jobAddress.getLng(), String.class), Itinerary.class);
            Map<String, Itinerary> result = new HashMap<>();
            result.put("walk", itineraryWalk);
            return result;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }*/
        Itinerary itineraryWalk = gson.fromJson(restTemplate.getForObject(base+"walk?startLat="+userAddress.getLat()+"&startLong="+userAddress.getLng()+"&endLat="+jobAddress.getLat()+"&endLong="+jobAddress.getLng(), String.class), Itinerary.class);
        Itinerary itineraryDrive = gson.fromJson(restTemplate.getForObject(base+"drive?startLat="+userAddress.getLat()+"&startLong="+userAddress.getLng()+"&endLat="+jobAddress.getLat()+"&endLong="+jobAddress.getLng(), String.class), Itinerary.class);
        Itinerary itineraryTransit = gson.fromJson(restTemplate.getForObject(base+"transit?startLat="+userAddress.getLat()+"&startLong="+userAddress.getLng()+"&endLat="+jobAddress.getLat()+"&endLong="+jobAddress.getLng(), String.class), Itinerary.class);
        Map<String, Itinerary> result = new HashMap<>();
        result.put("walk", itineraryWalk);
        result.put("drive", itineraryDrive);
        result.put("transit", itineraryTransit);
        return result;
    }

}

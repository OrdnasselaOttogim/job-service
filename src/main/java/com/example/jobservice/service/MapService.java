package com.example.jobservice.service;

import com.example.jobservice.model.Itinerary;
import com.example.jobservice.model.Position;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class MapService {

    private final String base = "http://localhost:9080/api/v1/itinerary/";

    private final RestTemplate restTemplate;

    @Autowired
    public MapService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Position getPosition(String address){
        Gson gson = new Gson();
        return gson.fromJson(restTemplate.getForObject(base+"geocode?address="+address, String.class), Position.class);
    }

    public Map<String, Itinerary> getItineraries(Position jobAddress, Position userAddress){
        Gson gson = new Gson();
        Itinerary itineraryWalk = gson.fromJson (restTemplate.getForObject(base+"walk?startLat="+userAddress.getLat()+"&startLong="+userAddress.getLng()+"&endLat="+jobAddress.getLat()+"&endLong="+jobAddress.getLng(), String.class), Itinerary.class);
        Itinerary itineraryDrive = gson.fromJson(restTemplate.getForObject(base+"drive?startLat="+userAddress.getLat()+"&startLong="+userAddress.getLng()+"&endLat="+jobAddress.getLat()+"&endLong="+jobAddress.getLng(), String.class), Itinerary.class);
        Itinerary itineraryTransit = gson.fromJson(restTemplate.getForObject(base+"transit?startLat="+userAddress.getLat()+"&startLong="+userAddress.getLng()+"&endLat="+jobAddress.getLat()+"&endLong="+jobAddress.getLng(), String.class), Itinerary.class);
        Map<String, Itinerary> result = new HashMap<>();
        result.put("walk", itineraryWalk);
        result.put("drive", itineraryDrive);
        result.put("transit", itineraryTransit);
        return result;
    }

}

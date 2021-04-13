package com.application.hotspotapplication.requests.hotspots;

import com.application.hotspotapplication.exceptions.ApiRequestException;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class LocationService {

  @Autowired
  private LocationDAO dao;



}

package com.application.hotspotapplication.requests.blank.v1;


import com.application.hotspotapplication.exceptions.ApiRequestException;
import com.application.hotspotapplication.requests.blank.Blank;
import com.application.hotspotapplication.requests.blank.BlankService;
import com.application.hotspotapplication.utils.Constants;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("hotspot/v1/blank/")
@Slf4j
public class BlankControllerV1 {

  @Inject
  private BlankService blankService;

  @PostMapping(path = "/create", consumes = Constants.APPLICATION_JSON_VALUE, produces = Constants.APPLICATION_JSON_VALUE)
  public ResponseEntity createBlank(@RequestBody Blank blank) {
    try {
      blankService.createBlank(blank);
      return new ResponseEntity<Blank>(blank, HttpStatus.OK);
    } catch (ApiRequestException e) {
      throw new ApiRequestException(e.getMessage(), e, e.getHttpStatus());
    }
  }

  @PostMapping(path = "/update", consumes = Constants.APPLICATION_JSON_VALUE, produces = Constants.APPLICATION_JSON_VALUE)
  public ResponseEntity updateBlank(@RequestBody Blank blank) {
    try {
      blankService.updateBlank(blank);
      return new ResponseEntity(HttpStatus.OK);
    } catch (ApiRequestException e) {
      throw new ApiRequestException(e.getMessage(), e, e.getHttpStatus());
    }
  }

  @GetMapping(value = "/id/{id}")
  public ResponseEntity findUserById(
      @PathVariable(name = "id", required = true) Long userId) {
    try {
      Blank blank = (Blank) blankService.findBlankById(userId);
      return new ResponseEntity<Blank>(blank, HttpStatus.OK);
    } catch (ApiRequestException e) {
      throw new ApiRequestException(e.getMessage(), e.getHttpStatus());
    }
  }



}

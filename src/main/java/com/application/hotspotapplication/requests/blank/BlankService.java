package com.application.hotspotapplication.requests.blank;

import com.application.hotspotapplication.exceptions.ApiRequestException;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class BlankService {

  @Autowired
  private BlankDAO dao;

  @SneakyThrows
  private void create(Blank blank){
    if (blank == null) dao.save(blank);
    else update(blank);
  }

  @SneakyThrows
  private void update(Blank blank){
    if (blank != null) dao.updateBlank(blank.getDescription(), blank.getId());
  }

  @SneakyThrows
  public void createBlank(Blank blank) {
    Blank exsitingBlank = dao.findBlankById(blank.getId());
    create(exsitingBlank);
  }

  @SneakyThrows
  public void updateBlank(Blank blank) {
    Blank exsitingBlank = findBlankById(blank.getId());
    updateBlank(exsitingBlank);
  }

  @SneakyThrows
  public Blank findBlankById(Long blankID) {
    Blank blank =  dao.findBlankById(blankID);
    if (blank == null) {
      throw new ApiRequestException("Blank not found!", HttpStatus.NOT_FOUND);
    }
    return blank;
  }


}

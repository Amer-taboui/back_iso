package com.crm.operis_app.dto;

import lombok.Data;

import javax.persistence.*;
@Data

public class FileModelDto {

     private Long id;

     private String name;

     private String mimetype;

    @Lob
     private byte[] pic;

  //  private BusinessIdentityDto businessIdentityDto;

}

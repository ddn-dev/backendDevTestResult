package com.ddn.backenddevtest.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

  private Long id;
  private String name;
  private Float price;
  private Boolean availability;
}

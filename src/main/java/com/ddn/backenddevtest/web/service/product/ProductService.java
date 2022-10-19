package com.ddn.backenddevtest.web.service.product;

import com.ddn.backenddevtest.web.dto.ProductDTO;

import java.util.List;

public interface ProductService {

  String PRODUCT_URL = "http://localhost:3001/product/{0}";
  String PRODUCT_SIMILAR_IDS_URL = PRODUCT_URL + "/similarids";

  /**
   * Obtain a list of products of a given one by its id
   *
   * @param id Product id
   * @return List of products {@code List<ProductDTO>}
   */
  List<ProductDTO> getSimilarProductsById(Long id);
}

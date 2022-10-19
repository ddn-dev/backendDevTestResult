package com.ddn.backenddevtest.web.service.product.impl;

import com.ddn.backenddevtest.web.dto.ProductDTO;
import com.ddn.backenddevtest.web.handler.RestTemplateResponseErrorHandler;
import com.ddn.backenddevtest.web.exception.NotFoundException;
import com.ddn.backenddevtest.web.exception.ServerErrorException;
import com.ddn.backenddevtest.web.service.product.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

  private RestTemplate restTemplate;

  /** {@inheritDoc} */
  public List<ProductDTO> getSimilarProductsById(Long id) {
    restTemplate =
        new RestTemplateBuilder().errorHandler(new RestTemplateResponseErrorHandler()).build();
    List<Long> similarIdsList = getSimilarProductIdsById(id);
    List<ProductDTO> productDTOList = new ArrayList<>();
    try {
      for (Long similarId : similarIdsList) {
        ProductDTO productById = getProductById(similarId);
        productDTOList.add(productById);
      }
    } catch (NotFoundException notFoundException) {
      log.warn("Product not found");
    } catch (ServerErrorException serverErrorException) {
      log.error("Internal server error");
    }
    return productDTOList;
  }

  /**
   * Obtain a product by id
   *
   * @param id Product id
   * @return Product {@code ProductDTO}
   */
  private ProductDTO getProductById(Long id) {
    restTemplate =
        new RestTemplateBuilder().errorHandler(new RestTemplateResponseErrorHandler()).build();
    return restTemplate.getForObject(
        MessageFormat.format(PRODUCT_URL, id.toString()), ProductDTO.class);
  }

  /**
   * Obtain a list of similar product ids of a given one by its id
   *
   * @param id Product id
   * @return List of ids {@code List<Long>}
   */
  private List<Long> getSimilarProductIdsById(Long id) {
    return Arrays.asList(
        Objects.requireNonNull(
            restTemplate.getForObject(
                MessageFormat.format(PRODUCT_SIMILAR_IDS_URL, id), Long[].class)));
  }
}

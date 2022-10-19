package com.ddn.backenddevtest.web.controller.product;

import com.ddn.backenddevtest.web.dto.ProductDTO;
import com.ddn.backenddevtest.web.exception.NotFoundException;
import com.ddn.backenddevtest.web.exception.ServerErrorException;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface ProductApi {

  /**
   * Obtain a list of products of a given one by its id
   *
   * @param id Product id
   * @return List of products {@code List<ProductDTO>}
   */
  @ApiOperation(
      value = "Similar Product Request",
      notes = "Request to get the similar products of a given one by its id",
      httpMethod = "GET")
  @ApiResponses(
      value = {
        @ApiResponse(code = 200, message = "Ok", response = List.class),
        @ApiResponse(code = 204, message = "No content", response = List.class),
        @ApiResponse(code = 404, message = "Not found", response = NotFoundException.class),
        @ApiResponse(
            code = 500,
            message = "Internal server error",
            response = ServerErrorException.class)
      })
  @GetMapping(value = "/{id}/similar", produces = "application/json")
  ResponseEntity<List<ProductDTO>> getSimilarProductsById(
      @ApiParam(name = "id", value = "Id of the product to search its similars") @PathVariable("id")
          Long id);
}

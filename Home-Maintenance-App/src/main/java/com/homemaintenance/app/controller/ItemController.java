package com.homemaintenance.app.controller;


import com.homemaintenance.app.model.ErrorDetail;
import com.homemaintenance.app.model.Items;
import com.homemaintenance.app.service.ItemService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/items")
public class ItemController {
    @Autowired
    private ItemService itemService;

    // Adding custom swagger documentation for list all items with paging
    @ApiOperation(value = "Return all Courses", response = Items.class,
            responseContainer = "List")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query",
                    value = "Results page you want to retrieve (0..N)"),
            @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query",
                    value = "Number of records per page."),
            @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string",
                    paramType = "query",
                    value = "Sorting criteria in the format: property(,asc|desc). " +
                            "Default sort order is ascending. " +
                            "Multiple sort criteria are supported.")})

    // paging and sorting
    // localhost:2019/items/items/paging/?page=1&size=10
    @GetMapping(value = "/items/paging",
            produces = {"application/json"})
    public ResponseEntity<?> ListAllItemsByPage(@PageableDefault(page = 1,
            size = 3) Pageable pageable)
    {                        // findAllPageable(pageable.unpaged()) <- returns everything
        List<Items> myItems = itemService.findAllPageable(pageable);
        return new ResponseEntity<>(myItems, HttpStatus.OK);
    }

    // Adding custom swagger documentation for list all items
    @ApiOperation(value = "Return all Items", response = Items.class,
            responseContainer = "List")
    @ApiResponses(value = {@ApiResponse(code = 200,
            message = "Item List Found",
            response = Items.class),
            @ApiResponse(code = 404,
                    message = "Item List Not Found",
                    response = ErrorDetail.class)})

    @GetMapping(value = "/items", produces = {"application/json"})
    public ResponseEntity<?> listAllItems()
    {
        ArrayList<Items> myCourses = itemService.findAll();
        return new ResponseEntity<>(myCourses, HttpStatus.OK);
    }

    // Adding custom swagger documentation for delete item by id
    @ApiOperation(value = "Delete a item",
            response = void.class)
    @ApiResponses(value = {@ApiResponse(code = 200,
            message = "Item Deleted",
            response = void.class),
            @ApiResponse(code = 500,
                    message = "Could Not Delete Item",
                    response = ErrorDetail.class)})

    @DeleteMapping("/items/{itemid}")
    public ResponseEntity<?> deleteItemById(
            // Adding custom swagger params for delete item
            @ApiParam(value = "Item Id",
                    required = true,
                    example = "1")
            @PathVariable long itemid)
    {
        itemService.delete(itemid);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

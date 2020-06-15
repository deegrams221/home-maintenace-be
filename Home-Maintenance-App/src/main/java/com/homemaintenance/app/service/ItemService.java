package com.homemaintenance.app.service;

import com.homemaintenance.app.model.Items;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;


public interface ItemService {
    ArrayList<Items> findAll();

    // paging and sorting
    List<Items> findAllPageable(Pageable pageable);

    void delete(long id);
}

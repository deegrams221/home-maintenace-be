package com.homemaintenance.app.repository;

import com.homemaintenance.app.model.Items;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.ArrayList;

public interface ItemRepository extends PagingAndSortingRepository<Items, Long> {
    ArrayList<Items> findItemsByItemNameEquals(String name);

}

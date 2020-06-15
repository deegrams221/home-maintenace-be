package com.homemaintenance.app.service;

import com.homemaintenance.app.model.Items;
import com.homemaintenance.app.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service(value = "itemService")
public class ItemServiceImpl implements ItemService {
    @Autowired
    private ItemRepository itemrepos;

    @Override
    public ArrayList<Items> findAll() {
        ArrayList<Items> list = new ArrayList<Items>();
        itemrepos.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    // paging and sorting - generate findAllPageable
    @Override
    public List<Items> findAllPageable(Pageable pageable)
    {
        List<Items> list = new ArrayList<>();
        itemrepos.findAll(pageable).iterator().forEachRemaining(list::add);
        return list;
    }

    @Transactional
    @Override
    public void delete(long id) throws EntityNotFoundException
    {
        if (itemrepos.findById(id).isPresent())
        {
            itemrepos.deleteById(id);
        } else
        {
            throw new EntityNotFoundException(Long.toString(id));
        }
    }
}

package com.homemaintenance.app.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

// adding custom swagger documentation in models
@ApiModel(value = "Items", description = "The Item Entity")

@Entity
@Table(name = "items")
public class Items {
    // adding custom swagger documentation for itemid
    @ApiModelProperty(name = "itemid",
            value = "primary key for Items",
            required = true, example = "1")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long itemid;

    // adding custom swagger documentation for itemname
    @ApiModelProperty(name = "itemname",
            value = "Item Name",
            required = true,
            example = "SumpPump")
    private String itemname;

    public Items()
    {
    }

    public Items(String itemname)
    {
        this.itemname = itemname;
    }

    public long getItemid() {
        return itemid;
    }

    public void setItemid(long itemid) {
        this.itemid = itemid;
    }

    public String getItemname() {
        return itemname;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
    }

    @Override
    public String toString() {
        return "Items{" +
                "itemid=" + itemid +
                ", itemname='" + itemname + '\'' +
                '}';
    }
}

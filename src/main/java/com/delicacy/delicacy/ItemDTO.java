package com.delicacy.delicacy;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.stream.Stream;

public class ItemDTO extends ArrayList<ItemDTO> {
    private String itemCode;


    private String foodName;
    private FileInputStream imageFile;
    private String ima;
    private String Description;

    private String Price;



    public ItemDTO(String itemCode,String food,String unitPrice, String description,String ima) {
        this.itemCode = itemCode;
       this.ima = ima;
        Description = description;

        Price = unitPrice;
        foodName=food;
    }


    public String getItemCode()
    {

        return itemCode;
    }

    public void setItemCode(String itemCode)
    {
        this.itemCode = itemCode;
    }


    public String getDescription()
    {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }



    public String getPrice()
    {
        return Price;
    }

    public void setPrice(String unitPrice)
    {
        Price = unitPrice;
    }

    public String getFoodName()
    {
        return foodName;
    }

    public void setFoodName(String food)
    {
       foodName = food;
    }



    @Override
    public Stream<ItemDTO> stream() {
        return null;
    }
}

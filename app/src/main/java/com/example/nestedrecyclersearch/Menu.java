package com.example.nestedrecyclersearch;

/**
 * Designed and Developed by Mohammad suhail ahmed on 19/02/2020
 */
public class Menu {
    public int menuid;
    public int categoryid;
    public String name;
    public double price;
    public String type;

    public Menu(int menuid,int categoryid,String name,double price,String type)
    {
        this.menuid = menuid;
        this.categoryid =categoryid;
        this.name = name;
        this.price = price;
        this.type = type;
    }


    public int getCategoryid() {
        return categoryid;
    }

    public double getPrice() {
        return price;
    }

    public int getMenuid() {
        return menuid;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }
}

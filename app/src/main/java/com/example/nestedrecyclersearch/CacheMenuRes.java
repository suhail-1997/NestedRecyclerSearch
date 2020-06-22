package com.example.nestedrecyclersearch;

import java.util.List;

/**
 * Designed and Developed by Mohammad suhail ahmed on 24/02/2020
 */
public class CacheMenuRes {
    private String subCategoryName;

    private List<Menu> menus;

    public List<Menu> getMenus() {
        return menus;
    }

    public String getSubCategoryName() {
        return subCategoryName;
    }

    public void setSubCategoryName(String subCategoryName) {
        this.subCategoryName = subCategoryName;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }
    public CacheMenuRes(String subCategoryName,List<Menu> menus)
    {
        this.subCategoryName = subCategoryName;
        this.menus = menus;
    }
}

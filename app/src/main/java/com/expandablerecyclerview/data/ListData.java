package com.expandablerecyclerview.data;

import java.util.ArrayList;

/**
 * Created by krunal on 8/22/2017.
 */

public class ListData {
    private String mName;
    private ArrayList<String> mSubCategories = new ArrayList<>();
    private boolean isListVisible;

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public ArrayList<String> getSubCategories() {
        return mSubCategories;
    }

    public void addSubCategories(String categoryName) {
        mSubCategories.add(categoryName);
    }

    public boolean isListVisible() {
        return isListVisible;
    }

    public void setListVisible(boolean b) {
        isListVisible = b;
    }
}

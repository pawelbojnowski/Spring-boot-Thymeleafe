package pl.pb.web.spring.thymeleafe.controllers;

import java.util.Date;
import java.util.List;

/**
 * Created by bojnop01 on 2016-02-23.
 */
public class OtherModel {

    private String name;
    private Date date;
    private List<String> listOfObject;
    private List<String> emptyList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<String> getListOfObject() {
        return listOfObject;
    }

    public void setListOfObject(List<String> listOfObject) {
        this.listOfObject = listOfObject;
    }

    public List<String> getEmptyList() {
        return emptyList;
    }

    public void setEmptyList(List<String> emptyList) {
        this.emptyList = emptyList;
    }

    @Override
    public String toString() {
        return "OtherModel{" +
               " name:'" + name + '\'' +
               ", date:'" + date +
               ", listOfObject:" + listOfObject +
               " }";
    }
}

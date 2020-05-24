package com.sidgo.cinerama.model.dto;

public class SctPermissionDTO {

    private long id;
    private String name;
    private String displayName;
    private String icon;
    private String route;
    private int state;
    private int orderNumber;
    private int father;
    private int family;

    public SctPermissionDTO(long id, String name, String displayName, String icon, String route, int state, int orderNumber, int father, int family) {
        this.id = id;
        this.name = name;
        this.displayName = displayName;
        this.icon = icon;
        this.route = route;
        this.state = state;
        this.orderNumber = orderNumber;
        this.father = father;
        this.family = family;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public int getFather() {
        return father;
    }

    public void setFather(int father) {
        this.father = father;
    }

    public int getFamily() {
        return family;
    }

    public void setFamily(int family) {
        this.family = family;
    }
}

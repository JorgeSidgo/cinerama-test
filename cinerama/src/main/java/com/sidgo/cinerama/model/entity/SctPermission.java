package com.sidgo.cinerama.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "sct_permission", schema = "cinerama")
public class SctPermission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "display_name")
    private String displayName;

    @Column(name = "icon")
    private String icon;

    @Column(name = "route")
    private String route;

    @Column(name = "state")
    private int state;

    @Column(name = "order_number")
    private int orderNumber;

    @Column(name = "father")
    private int father;

    @Column(name = "family")
    private int family;

    public SctPermission() {
    }

    public SctPermission(long id) {
        this.id = id;
    }

    public SctPermission(long id, String name, String displayName, String icon, String route, int state, int orderNumber, int father, int family) {
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

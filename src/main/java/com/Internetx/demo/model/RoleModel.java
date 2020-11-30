package com.Internetx.demo.model;

public class RoleModel {

    private boolean ROLE_ADMIN;
    private boolean ROLE_DEVELOP;
    private boolean ROLE_CCTLD;
    private boolean ROLE_GTLD;

    public boolean isROLE_ADMIN() {
        return ROLE_ADMIN;
    }

    public void setROLE_ADMIN(boolean ROLE_ADMIN) {
        this.ROLE_ADMIN = ROLE_ADMIN;
    }

    public boolean isROLE_DEVELOP() {
        return ROLE_DEVELOP;
    }

    public void setROLE_DEVELOP(boolean ROLE_DEVELOP) {
        this.ROLE_DEVELOP = ROLE_DEVELOP;
    }

    public boolean isROLE_CCTLD() {
        return ROLE_CCTLD;
    }

    public void setROLE_CCTLD(boolean ROLE_CCTLD) {
        this.ROLE_CCTLD = ROLE_CCTLD;
    }

    public boolean isROLE_GTLD() {
        return ROLE_GTLD;
    }

    public void setROLE_GTLD(boolean ROLE_GTLD) {
        this.ROLE_GTLD = ROLE_GTLD;
    }

    public boolean isROLE_BILLING() {
        return ROLE_BILLING;
    }

    public void setROLE_BILLING(boolean ROLE_BILLING) {
        this.ROLE_BILLING = ROLE_BILLING;
    }

    public boolean isROLE_REGISTRY() {
        return ROLE_REGISTRY;
    }

    public void setROLE_REGISTRY(boolean ROLE_REGISTRY) {
        this.ROLE_REGISTRY = ROLE_REGISTRY;
    }

    public boolean isROLE_PURCHASE_READ() {
        return ROLE_PURCHASE_READ;
    }

    public void setROLE_PURCHASE_READ(boolean ROLE_PURCHASE_READ) {
        this.ROLE_PURCHASE_READ = ROLE_PURCHASE_READ;
    }

    public boolean isROLE_PURCHASE_WRITE() {
        return ROLE_PURCHASE_WRITE;
    }

    public RoleModel(boolean ROLE_ADMIN, boolean ROLE_DEVELOP, boolean ROLE_CCTLD, boolean ROLE_GTLD, boolean ROLE_BILLING, boolean ROLE_REGISTRY, boolean ROLE_PURCHASE_READ, boolean ROLE_PURCHASE_WRITE, boolean ROLE_SALE_WRITE, boolean ROLE_SQL) {
        this.ROLE_ADMIN = ROLE_ADMIN;
        this.ROLE_DEVELOP = ROLE_DEVELOP;
        this.ROLE_CCTLD = ROLE_CCTLD;
        this.ROLE_GTLD = ROLE_GTLD;
        this.ROLE_BILLING = ROLE_BILLING;
        this.ROLE_REGISTRY = ROLE_REGISTRY;
        this.ROLE_PURCHASE_READ = ROLE_PURCHASE_READ;
        this.ROLE_PURCHASE_WRITE = ROLE_PURCHASE_WRITE;
        this.ROLE_SALE_WRITE = ROLE_SALE_WRITE;
        this.ROLE_SQL = ROLE_SQL;
    }

    public void setROLE_PURCHASE_WRITE(boolean ROLE_PURCHASE_WRITE) {
        this.ROLE_PURCHASE_WRITE = ROLE_PURCHASE_WRITE;
    }

    public boolean isROLE_SALE_WRITE() {
        return ROLE_SALE_WRITE;
    }

    public void setROLE_SALE_WRITE(boolean ROLE_SALE_WRITE) {
        this.ROLE_SALE_WRITE = ROLE_SALE_WRITE;
    }

    public boolean isROLE_SQL() {
        return ROLE_SQL;
    }

    public void setROLE_SQL(boolean ROLE_SQL) {
        this.ROLE_SQL = ROLE_SQL;
    }

    private boolean ROLE_BILLING;
    private boolean ROLE_REGISTRY;
    private boolean ROLE_PURCHASE_READ;
    private boolean ROLE_PURCHASE_WRITE;
    private boolean ROLE_SALE_WRITE;
    private boolean ROLE_SQL;

}

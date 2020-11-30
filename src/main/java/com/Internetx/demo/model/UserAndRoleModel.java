package com.Internetx.demo.model;

public class UserAndRoleModel{
    public UserAndRoleModel(UserModel userModel, RoleModel roleModel) {
        this.userModel = userModel;
        this.roleModel = roleModel;
    }

    public UserModel getUserModel() {
        return userModel;
    }

    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }

    public RoleModel getRoleModel() {
        return roleModel;
    }

    public void setRoleModel(RoleModel roleModel) {
        this.roleModel = roleModel;
    }

    private UserModel userModel;
    private RoleModel roleModel;
}
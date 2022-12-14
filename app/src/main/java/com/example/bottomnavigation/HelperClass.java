package com.example.bottomnavigation;

public class HelperClass {

    String name, email, username, password, role, class_id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void SetRole(String password) {
        this.class_id = password;
    }

    public String getClassID() {
        return class_id;
    }

    public void SetClassID(String password) {
        this.class_id = password;
    }

    public HelperClass(String name, String email, String username, String password, String role, String class_id) {
        this.name = name;
        this.email = email;
        this.username = username;
        this.password = password;
        this.role = role;
        this.class_id = class_id;
    }

    public HelperClass() {
    }
}
package com.android.mylibrary;

public class ModelPerson {
    
    
    private String id     = "";
    private String name   = "";
    private String email  = "";
    private String pw     = "";
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
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
    public String getPw() {
        return pw;
    }
    public void setPw(String pw) {
        this.pw = pw;
    }
    @Override
    public String toString() {
        return "ModelPerson [id=" + id + ", name=" + name + ", email=" + email
                + ", pw=" + pw + "]";
    }
    public ModelPerson(String id, String name, String email, String pw) {
        super();
        this.id = id;
        this.name = name;
        this.email = email;
        this.pw = pw;
    }
    public ModelPerson(String id, String pw) {
        super();
        this.id = id;
        this.pw = pw;
    }
    public ModelPerson() {
        super();
    }
    
}

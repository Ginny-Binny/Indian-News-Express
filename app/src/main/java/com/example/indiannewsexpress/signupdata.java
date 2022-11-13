package com.example.indiannewsexpress;

public class signupdata {
    String email,name;
    public  signupdata()
    {

    }

    public signupdata(String email, String name) {
        this.email = email;

        this.name = name;
    }



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

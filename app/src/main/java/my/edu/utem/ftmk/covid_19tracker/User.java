package my.edu.utem.ftmk.covid_19_tracker;

public class User {

    public String name, email, password, phoneNo;

    public User () {

    }

    public User(String name, String email, String password, String phoneNo) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phoneNo = phoneNo;
    }
}

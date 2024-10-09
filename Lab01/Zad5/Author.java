package Zad5;

public class Author {
    public String name;
    public String email;
    public Gender gender;

    Author(String  name, String email, Gender gender){
        this.name = name;
        this.email = email;
        this.gender = gender; 
    }
    
    public String getName() {
        return name;
    }
    
    public String getEmail() {
        return email;
    }
    
    public Gender getGender() {
        return gender;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String toString() {
        return "Author [Name = " + name + ", email = " + email + ", gender = " + gender + " ]" ;
    }
}

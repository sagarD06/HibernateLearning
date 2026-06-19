package org.example;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Student {

    @Id
    private int Id;
    private String Name;
    private int age;
    @ManyToMany(mappedBy = "students") //mappedBy is what the variable name in Laptop for Student clas
    private List<Laptop> laptops;

    public Student() {
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public List<Laptop> getLaptops() {
        return laptops;
    }

    public void setLaptops(List<Laptop> laptops) {
        this.laptops = laptops;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "Id=" + Id +
                ", Name='" + Name + '\'' +
                ", age=" + age +
                ", laptop=" + laptops +
                '}';
    }
}

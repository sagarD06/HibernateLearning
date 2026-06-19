package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.Arrays;

/*
        session.persist(student); //saves the values from student obj -> Create
        Student s2 = session.find(Student.class, 7); // -> Read
        s2.setAge(30);
        session.merge(s2); // -> Update
        session.remove(s2); // -> Delete
*/

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Student student1 = new Student();
        Student student2 = new Student();
        Student student3 = new Student();


        Laptop laptop1 = new Laptop();
        Laptop laptop2 = new Laptop();
        Laptop laptop3 = new Laptop();


        laptop1.setId(101);
        laptop1.setBrand("Dell");
        laptop1.setModel("Inspiron");

        laptop2.setId(102);
        laptop2.setBrand("Apple");
        laptop2.setModel("M4Pro");

        laptop3.setId(103);
        laptop3.setBrand("Asus");
        laptop3.setModel("Rog");

        student1.setId(1);
        student1.setName("Sagar");
        student1.setAge(28);
        student1.setLaptops(Arrays.asList(laptop1, laptop3));

        student2.setId(2);
        student2.setName("NedStark");
        student2.setAge(55);
        student2.setLaptops(Arrays.asList(laptop1,laptop2));

        student3.setId(3);
        student3.setName("JonSnow");
        student3.setAge(28);
        student3.setLaptops(Arrays.asList(laptop3));

        laptop1.setStudents(Arrays.asList(student1, student2));
        laptop2.setStudents(Arrays.asList(student2));
        laptop3.setStudents(Arrays.asList(student1, student3));

        //1st step configure the Hibernate -> need hibernate.cfg.xml
//        Configuration config = new Configuration();
//        config.addAnnotatedClass(org.example.Student.class);
//        config.configure();

        //Create Session
        SessionFactory sf = new Configuration()
                .addAnnotatedClass(org.example.Student.class)
                .addAnnotatedClass(org.example.Laptop.class)
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();

        Session session = sf.openSession();

        //Always a DB write is a transaction
        Transaction transaction = session.beginTransaction(); // Begin tran

        session.persist(laptop1);
        session.persist(laptop2);
        session.persist(laptop3);

        session.persist(student1);
        session.persist(student2);
        session.persist(student3);

        transaction.commit(); //commits the changes

        sf.close();
        session.close();
    }
}
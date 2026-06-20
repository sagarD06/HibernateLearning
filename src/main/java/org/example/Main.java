package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.Arrays;
import java.util.List;

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

        Laptop laptop1 = new Laptop();



        //1st step configure the Hibernate -> need hibernate.cfg.xml
//        Configuration config = new Configuration();
//        config.addAnnotatedClass(org.example.Student.class);
//        config.configure();

        //Create Session
        SessionFactory sf = new Configuration()
                .addAnnotatedClass(org.example.Laptop.class)
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();

        Session session = sf.openSession();

        //Always a DB write is a transaction
        Transaction transaction = session.beginTransaction(); // Begin tran

        laptop1 = session.find(Laptop.class, 103); //problem here is cannot find rows other than id clause

        //select * from laptop where name = 'Dell'; -> SQL
        //from Laptop where nam = "Dell" -> HQL Laptop is class Entity here

        Query query = session.createQuery("from Laptop where brand = 'Dell'",  Laptop.class); //normal query
        List<Laptop> laptops = query.getResultList();

        final String brand = "Dell";
        Query query2 = session.createQuery("select brand, model from Laptop where brand like ?1", Laptop.class);
        query2.setParameter(1, brand);
        List<Laptop> laptops2 = query.getResultList();

        System.out.println(laptop1);
        System.out.println(laptops2);

        for(Laptop data : laptops2){
            System.out.println(data.getBrand() + " " + data.getModel());
        }

        System.out.println(laptops);

        transaction.commit(); //commits the changes

        sf.close();
        session.close();
    }
}
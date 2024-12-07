package com.klef.jfsd.exam;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class ClientDemo {  // Corrected class name
    public static void main(String[] args) {
        // Configure Hibernate
        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");
        cfg.addAnnotatedClass(Client.class);

        // Create session factory and session
        SessionFactory factory = cfg.buildSessionFactory();
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            // Insert a record
            Client client = new Client();
            client.setName("John Doe");
            client.setGender("Male");
            client.setAge(30);
            client.setLocation("New York");
            client.setEmail("john.doe@example.com");
            client.setMobile("1234567890");
            session.save(client);

            // Fetch all records using HQL
            List<Client> clients = session.createQuery("from Client", Client.class).list();
            for (Client c : clients) {
                System.out.println("ID: " + c.getId() + ", Name: " + c.getName() + ", Gender: " + c.getGender() +
                        ", Age: " + c.getAge() + ", Location: " + c.getLocation() + ", Email: " + c.getEmail() +
                        ", Mobile: " + c.getMobile());
            }

            // Commit the transaction
            transaction.commit();
        } catch (Exception e) {
            // Handle any exceptions (rollback if any)
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            // Close session and factory
            session.close();
            factory.close();
        }
    }
}
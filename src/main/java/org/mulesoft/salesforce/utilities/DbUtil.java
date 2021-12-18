package org.mulesoft.salesforce.utilities;

import lombok.extern.slf4j.Slf4j;
import org.mulesoft.salesforce.model.Person;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class DbUtil {

    private static EntityManager entityManager = null;
    private static EntityTransaction entityTransaction = null;

    private DbUtil() {
    }

    public static EntityManager openDbConnection() {
        if (null == entityManager) {
            try {
                EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("mulesoft.salesforce.integration");
                entityManager = entityManagerFactory.createEntityManager();
                log.info("Database connection established successfully");
            } catch (Exception ex) {
                log.error("Failed to create session factory object: ", ex);
            }
        }
        return entityManager;
    }

    public static Person getPerson(Integer id) {
        Person person = null;
        if (null != entityManager) {
            entityTransaction = entityManager.getTransaction();
            entityTransaction.begin();
            person = entityManager.find(Person.class, id);
            ReportUtil.addTestStepLog("Person retrieved from db with Id: " + id + " is :" + person);
            entityTransaction.commit();
        }
        return person;
    }

    public static List<Person> getPersons() {
        ArrayList<Person> persons = new ArrayList<>();
        if (null != entityManager) {
            entityTransaction = entityManager.getTransaction();
            entityTransaction.begin();
            persons.addAll(entityManager.createQuery("from Person", Person.class).getResultList());
            ReportUtil.addTestStepLog("Persons retrieved from db: " + persons);
            entityTransaction.commit();
        }
        return persons;
    }

    public static void savePerson(Person person) {
        if (null != openDbConnection()) {
            entityTransaction = entityManager.getTransaction();
            entityTransaction.begin();
            entityManager.persist(person);
            ReportUtil.addTestStepLog("Person saved: " + person);
            entityTransaction.commit();
        }
    }

    public static void removePerson(Person person) {
        if (null != entityManager) {
            entityTransaction = entityManager.getTransaction();
            entityTransaction.begin();
            entityManager.remove(person);
            ReportUtil.addTestStepLog("Person removed: " + person);
            entityTransaction.commit();
        }
    }

    public static void closeConnection() {
        if (null != entityManager) {
            try {
                entityManager.clear();
                log.info("Database connection closed successfully");
            } catch (Exception e) {
                log.error("Error in closing database connection: ", e);
            }

        }
    }

}
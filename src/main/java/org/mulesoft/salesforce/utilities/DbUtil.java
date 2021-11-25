package org.mulesoft.salesforce.utilities;

import lombok.extern.slf4j.Slf4j;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@Slf4j
public class DbUtil {

    private static EntityManager entityManager = null;

    private DbUtil() {
    }

    public static EntityManager getEntityManager() {
        if (null == entityManager) {
            try {
                EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("mulesoft.salesforce.integration");
                entityManager = entityManagerFactory.createEntityManager();
            } catch (Exception ex) {
                log.error("Failed to create session factory object: ", ex);
            }
        }
        return entityManager;
    }
}
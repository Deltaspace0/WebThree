package com.deltaspace.webthree.history;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import java.util.List;

public class HistoryManager {
    private final SessionFactory sessionFactory;

    public HistoryManager() {
        Configuration configuration = new Configuration().configure();
        configuration.addAnnotatedClass(HistoryEntry.class);
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties());
        sessionFactory = configuration.buildSessionFactory(builder.build());
    }

    public List<HistoryEntry> findHistory() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from HistoryEntry").list();
        }
    }

    public void addNew(HistoryEntry entry) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(entry);
            transaction.commit();
        }
    }

    public void eraseHistory() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.createQuery("delete from HistoryEntry").executeUpdate();
            session.getTransaction().commit();
        }
    }
}

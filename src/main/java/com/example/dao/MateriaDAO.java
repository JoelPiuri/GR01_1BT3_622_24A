package com.example.dao;

import com.example.model.Materia;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class MateriaDAO {

    private SessionFactory factory;

    public MateriaDAO() {
        factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Materia.class).buildSessionFactory();
    }

    // Método para obtener todas las materias
    public List<Materia> getAllMaterias() {
        Session session = null;
        try {
            session = factory.openSession();
            session.beginTransaction();
            consultarMaterias(session);
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return null;
    }

    private static void consultarMaterias(Session session) {
        List<Materia> result = session.createQuery("from Materia", Materia.class).getResultList();
    }

    //Método para guardar o actualizar una materia
    public void saveMateria(Materia materia) {
        Session session = null;
        try {
            session = factory.openSession();
            session.beginTransaction();

            session.saveOrUpdate(materia);

            session.getTransaction().commit();
        } catch (Exception e) {
            if (session != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    // Método para eliminar una materia por su código
    public void deleteMateria(int codigomateria) {
        Session session = null;
        try {
            session = factory.openSession();
            session.beginTransaction();

            Materia materia = session.get(Materia.class, codigomateria);
            if (materia != null) {
                session.delete(materia);
            }

            session.getTransaction().commit();
        } catch (Exception e) {
            if (session != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    // Método para obtener una materia por su código
    public Materia getMateria(int codigomateria) {
        Session session = null;
        Materia materia = null;
        try {
            session = factory.openSession();
            session.beginTransaction();

            materia = session.get(Materia.class, codigomateria);

            session.getTransaction().commit();
        } catch (Exception e) {
            if (session != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return materia;
    }
}
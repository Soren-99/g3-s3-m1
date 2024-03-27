package sorenrahimi.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import sorenrahimi.entities.Evento;
import sorenrahimi.entities.Persona;

public class PersonaDAO {

    private static EntityManager entityManager;

    public PersonaDAO(){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("g3-s3-m1");
        entityManager = entityManagerFactory.createEntityManager();
    }

    public static void save(Persona persona){
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(persona);
            entityManager.getTransaction().commit();
            System.out.println("Persona salvata: " + persona);
        }catch (Exception e){
            if (entityManager.getTransaction().isActive()){
                entityManager.getTransaction().rollback();
            }
            System.out.println("Errore durante il salvataggio della persona: " + e.getMessage());
        }
    }

    public Persona getById(Long id){
        try {
            Persona persona = entityManager.find(Persona.class, id);
            if (persona != null) {
                System.out.println("Persona trovata per l'ID " + id + ": " + persona);
            }else {
                System.out.println("Nessuna persona trovata per l'ID " + id);
            }
            return persona;
        }catch (Exception e){
            System.out.println("Errore durante il recupero della persona per l'ID " + id + ": " + e.getMessage());
            return null;
        }
    }

    public void delete(Persona persona){
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(persona);
            entityManager.getTransaction().commit();
            System.out.println("Persona eliminata " + persona);
        }catch (Exception e){
            if (entityManager.getTransaction().isActive()){
                entityManager.getTransaction().rollback();
            }
            System.out.println("Errore durante l'eliminazione della persona: " + e.getMessage());
        }
    }

    public void close(){
        if (entityManager != null && entityManager.isOpen()) {
            entityManager.close();
        }
    }
}

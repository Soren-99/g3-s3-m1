package sorenrahimi.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.hibernate.sql.Delete;
import sorenrahimi.entities.Evento;

public class EventoDAO {
    private EntityManager entityManager;

    public EventoDAO(){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("g3-s3-m1");
        entityManager = entityManagerFactory.createEntityManager();
    }

    public void save(Evento evento){
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(evento);
            entityManager.getTransaction().commit();
            System.out.println("Elemento salvato: " + evento);
        }catch (Exception e){
            if (entityManager.getTransaction().isActive()){
                entityManager.getTransaction().rollback();
            }
            System.out.println("Errore durante il salvataggio dell'evento: " + e.getMessage());
        }
    }

    public Evento getById(Long id){
        try {
            Evento evento = entityManager.find(Evento.class, id);
            if (evento != null) {
                System.out.println("Evento trovato per l'ID " + id + ": " + evento);
            }else {
                System.out.println("Nessun elemento trovato per l'ID " + id);
            }
            return evento;
        }catch (Exception e){
            System.out.println("Errore durante il recupero dell'evento per l'ID " + id + ": " + e.getMessage());
            return null;
        }
    }


    public void delete(Evento evento){
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(evento);
            entityManager.getTransaction().commit();
            System.out.println("Evento eliminato " + evento);
        }catch (Exception e){
            if (entityManager.getTransaction().isActive()){
                entityManager.getTransaction().rollback();
            }
            System.out.println("Errore durante l'eliminazione dell'evento: " + e.getMessage());
        }
    }
    public void close(){
        if (entityManager != null && entityManager.isOpen()) {
            entityManager.close();
        }
    }
}

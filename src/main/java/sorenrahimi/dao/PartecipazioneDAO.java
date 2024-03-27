package sorenrahimi.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import sorenrahimi.entities.Location;
import sorenrahimi.entities.Partecipazione;

public class PartecipazioneDAO {
    private EntityManager entityManager;

    public PartecipazioneDAO(){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("g3-s3-m1");
        entityManager = entityManagerFactory.createEntityManager();
    }

    public void save(Partecipazione partecipazione){
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(partecipazione);
            entityManager.getTransaction().commit();
            System.out.println("Partecipazione salvata: " + partecipazione);
        }catch (Exception e){
            if (entityManager.getTransaction().isActive()){
                entityManager.getTransaction().rollback();
            }
            System.out.println("Errore durante il salvataggio della partecipazione: " + e.getMessage());
        }
    }

    public Partecipazione getById(Long id){
        try {
            Partecipazione partecipazione = entityManager.find(Partecipazione.class, id);
            if (partecipazione != null) {
                System.out.println("Partecipazione trovata per l'ID " + id + ": " + partecipazione);
            }else {
                System.out.println("Nessuna partecipazione trovata per l'ID " + id);
            }
            return partecipazione;
        }catch (Exception e){
            System.out.println("Errore durante il recupero della partecipazione per l'ID " + id + ": " + e.getMessage());
            return null;
        }
    }

    public void delete(Partecipazione partecipazione){
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(partecipazione);
            entityManager.getTransaction().commit();
            System.out.println("Partecipazione eliminata " + partecipazione);
        }catch (Exception e){
            if (entityManager.getTransaction().isActive()){
                entityManager.getTransaction().rollback();
            }
            System.out.println("Errore durante l'eliminaziome della partecipazione: " + e.getMessage());
        }
    }

    public void close(){
        if (entityManager != null && entityManager.isOpen()) {
            entityManager.close();
        }
    }
}

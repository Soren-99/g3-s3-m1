package sorenrahimi.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import sorenrahimi.entities.Location;
import sorenrahimi.entities.Persona;

public class LocationDAO {
    private EntityManager entityManager;

    public LocationDAO(){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("g3-s3-m1");
        entityManager = entityManagerFactory.createEntityManager();
    }

    public void save(Location location){
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(location);
            entityManager.getTransaction().commit();
            System.out.println("Location salvata: " + location);
        }catch (Exception e){
            if (entityManager.getTransaction().isActive()){
                entityManager.getTransaction().rollback();
            }
            System.out.println("Errore durante il salvataggio della location: " + e.getMessage());
        }
    }

    public Location getById(Long id){
        try {
            Location location = entityManager.find(Location.class, id);
            if (location != null) {
                System.out.println("Location trovata per l'ID " + id + ": " + location);
            }else {
                System.out.println("Nessuna location trovata per l'ID " + id);
            }
            return location;
        }catch (Exception e){
            System.out.println("Errore durante il recupero della location per l'ID " + id + ": " + e.getMessage());
            return null;
        }
    }

    public void delete(Location location){
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(location);
            entityManager.getTransaction().commit();
            System.out.println("Location eliminata " + location);
        }catch (Exception e){
            if (entityManager.getTransaction().isActive()){
                entityManager.getTransaction().rollback();
            }
            System.out.println("Errore durante l'eliminazione della location: " + e.getMessage());
        }
    }

    public void close(){
        if (entityManager != null && entityManager.isOpen()) {
            entityManager.close();
        }
    }
}

package sorenrahimi;


import sorenrahimi.dao.EventoDAO;
import sorenrahimi.dao.LocationDAO;
import sorenrahimi.dao.PartecipazioneDAO;
import sorenrahimi.dao.PersonaDAO;
import sorenrahimi.entities.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Application {
    public static void main(String[] args){
        PersonaDAO personaDAO = new PersonaDAO();
        LocationDAO locationDAO = new LocationDAO();
        EventoDAO eventoDAO = new EventoDAO();
        PartecipazioneDAO partecipazioneDAO = new PartecipazioneDAO();

        try {
            List<Partecipazione> listaPartecipazioni = new ArrayList<>();

            Persona persona = new Persona("soren", "rahimi", "sorenrahimi1234@.com", new Date(), Sesso.M, listaPartecipazioni);
            personaDAO.save(persona);

            Location location = new Location("Torino", "Via Roma 12");
            locationDAO.save(location);

            Evento evento = new Evento("Festa", new Date(), "Grandissima festa", TipoEvento.PUBBLICO, 100, location);
            eventoDAO.save(evento);

            Partecipazione partecipazione = new Partecipazione(persona, evento, StatoPartecipazione.CONFERMATA);
            partecipazioneDAO.save(partecipazione);
        } finally {
            personaDAO.close();
            locationDAO.close();
            eventoDAO.close();
            partecipazioneDAO.close();
        }
    }
}




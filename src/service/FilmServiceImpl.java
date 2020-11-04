package service;

import dao.FilmDAO;
import dao.FilmDAOImpl;
import model.Film;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;
import static dao.FilmDAOImpl.films;

@Service
public class FilmServiceImpl implements FilmService {
    private FilmDAO filmDAO = new FilmDAOImpl();

    @Override
    public List<Film> allFilms() {
        return filmDAO.allFilms();
    }

    @Override
    public void add(Film film) {
        filmDAO.add(film);
    }

    @Override
    public void delete(Film film) {
        filmDAO.delete(film);
    }

    @Override
    public void edit(Film film) {
        filmDAO.edit(film);
    }

    @Override
    public Film getById(int id) {
        return filmDAO.getById(id);
    }


    static Logger logger = Logger.getLogger(FilmServiceImpl.class.getName());


    @Async
    public Film findFilm(String filmName) throws InterruptedException {
        Film filmw = null;
        for (Map.Entry<Integer, Film> entry : films.entrySet()) {
          if(filmName.equals(entry.getValue().getTitle())){
              filmw = films.get(entry);
              LOGGER.log(Level.INFO, "метод применен");
          }
        }
        return filmw;
    }
}


package testgroup.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import testgroup.dao.FilmDAO;
import testgroup.model.Film;

import java.util.List;

@Service
public class FilmServiceImpl implements FilmService {

    private FilmDAO filmDAO;

    @Autowired
    public void setFilmDAO(FilmDAO filmDAO) {
        this.filmDAO = filmDAO;
    }

    @Override
    public List<Film> allFilms() {
        return filmDAO.allFilms();
    }

    @Override
    public void add(Film film) {
        filmDAO.addFilm(film);
    }

    @Override
    public void edit(Film film) {
        filmDAO.editFilm(film);
    }

    @Override
    public void delete(Film film) {
        filmDAO.deleteFilm(film);
    }

    @Override
    public Film getById(int id) {
        return filmDAO.getById(id);
    }
}

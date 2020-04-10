package testgroup.service;

import testgroup.dao.FilmDAO;
import testgroup.dao.FilmDAOimpl;
import testgroup.model.Film;

import java.util.List;

public class FilmServiceImpl implements FilmService {

    private FilmDAO filmDAO = new FilmDAOimpl();

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

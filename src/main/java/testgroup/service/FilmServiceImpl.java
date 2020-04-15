package testgroup.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

    /**
     * Переходим на уровень выше, в сервис. В классе FilmServiceImpl помечаем метод
     * allFilms spring аннотацией @Transactional, которая укажет на то, что метод
     * должен выполняться в транзакции (без этого Hibernate работать откажется
     */
    @Transactional
    @Override
    public List<Film> allFilms() {
        return filmDAO.allFilms();
    }

    @Transactional
    @Override
    public void add(Film film) {
        filmDAO.addFilm(film);
    }

    @Transactional
    @Override
    public void edit(Film film) {
        filmDAO.editFilm(film);
    }

    @Transactional
    @Override
    public void delete(Film film) {
        filmDAO.deleteFilm(film);
    }

    @Transactional
    @Override
    public Film getById(int id) {
        return filmDAO.getById(id);
    }
}

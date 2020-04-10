package testgroup.dao;

import testgroup.model.Film;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class FilmDAOimpl implements FilmDAO {
    private static final AtomicInteger AUTO_ID = new AtomicInteger(0);
    private static Map<Integer, Film> films = new HashMap<>();

    static {
        Film film1 = new Film();
        film1.setId(0);
        film1.setGenre("action");
        film1.setYear(1999);
        film1.setWatched(true);
        film1.setTitle("Title1");
        films.put(film1.getId(), film1);

        Film film2 = new Film();
        film1.setId(1);
        film1.setGenre("action");
        film1.setYear(2000);
        film1.setWatched(true);
        film1.setTitle("Title2");
        films.put(film1.getId(), film1);
    }

    @Override
    public List<Film> allFilms() {
        return new ArrayList<>(films.values());
    }

    @Override
    public void addFilm(Film film) {
        film.setId(AUTO_ID.getAndIncrement());
        films.put(film.getId(), film);
    }

    @Override
    public void deleteFilm(Film film) {
        films.remove(film.getId());
    }

    @Override
    public void editFilm(Film film) {
        films.put(film.getId(), film);
    }

    @Override
    public Film getById(int id) {
        return films.get(id);
    }
}

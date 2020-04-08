package testgroup.dao;

import testgroup.model.Film;

import java.util.List;

/**
 * Data Access Object (DAO) — это такой паттерн проектирования. Смысл в том, чтобы
 * создать специальную прослойку, которая будет отвечать исключительно за доступ к
 * данным (работа с базой данных или другим механизмом хранения). В пакете dao создадим
 * интерфейс FilmDAO в котором будут такие методы как добавить, удалить и т.д., они
 * соответствуют основным CRUD операциям (Create, Read, Update, Delete).
 */
public interface FilmDAO {
    List<Film> allFilms();
    void addFilm(Film film);
    void deleteFilm(Film film);
    void editFilm(Film film);
    Film getById(int id);
 }

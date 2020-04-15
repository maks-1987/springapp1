package testgroup.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import testgroup.model.Film;

import java.util.List;

@Repository("testgroup.dao")
public class FilmDAOimpl implements FilmDAO {

    private SessionFactory sessionFactory;

    /**
     * Добавляем фабрику сессий и будем работать через нее
     */
    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * Для начала сделаем метод для отображения страницы со списком фильмов, в нем
     * будем получать сессию и делать запрос к БД (вытаскивать все записи и формировать список)
     * Во-первых, высветилось предупреждение. Это связано с тем, что мы хотим получить
     * параметризованный List<Film>, но метод возвращает просто List, потому что во время
     * компиляции неизвестно какой тип вернет запрос. Так что идея нас предупреждает, что мы
     * делаем небезопасное преобразование, в следствие чего могут возникнуть неприятности.
     * Есть несколько более правильных способов как это сделать, чтоб такого вопроса не возникало.
     * Можно поискать информацию в интернете. Но сейчас не будем с этим заморачиваться. Дело
     * в том, что мы то точно знаем какой тип будет возвращен, так что никаких проблем тут не
     * возникнет, можно просто не обращать на предупреждение внимание. Но, чтоб глаза не
     * мозолило, можно повесить над методом аннотацию @SupressWarning("unchecked").
     * Во-вторых, идея подчеркивает красным "from Film". Просто это HQL (Hibernate Query Language)
     * запрос и идея не понимает, правильно там все или есть ошибка. Можно зайти в настройки идеи
     * и вручную все отрегулировать (ищем в интернете если интересно). Либо можно просто добавить
     * поддержку Hibernate фреймворка, для этого жмем правой кнопкой по проекту, выбираем Add
     * Framework Support, ставим галочку для Hibernate и жмем ОК.
     * После этого скорее всего в классе-сущности (Film) тоже много всего подчеркнет красным,
     * например там где аннотация @Table(name = "films") выдаст предупреждение Cannot resolve
     * table 'films'. Здесь опять же ничего страшного, это не ошибка проекта, все скомпилируется
     * и будет работать. Идея подчеркивает потому что ничего не знает про нашу базу. Чтобы это
     * исправить сделаем интеграцию идеи с БД. View -> Tool Windows -> Persistense (откроется
     * вкладка) -> правая кнопка мыши выбираем Assign Data Sources -> в Data Source указываем
     * соединение с БД и жмем ОК.
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<Film> allFilms() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Film").list();
    }

    @Override
    public void addFilm(Film film) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(film);
    }

    @Override
    public void deleteFilm(Film film) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(film);
    }

    @Override
    public void editFilm(Film film) {
        Session session = sessionFactory.getCurrentSession();
        session.update(film);
    }

    @Override
    public Film getById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Film.class, id);
    }
}

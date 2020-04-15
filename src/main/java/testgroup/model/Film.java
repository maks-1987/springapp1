package testgroup.model;

import javax.persistence.*;

/**
 * Объекты таких классов еще называют POJO (Plain Old Java Object), ну т.е. "простой джава объект".
 * Когда мы пишем в строке браузера запрос, его принимает Dispatcher Servlet, далее он находит для
 * обработки этого запроса подходящий контроллер с помощью HandlerMapping (это такой интерфейс для
 * выбора контроллера, проверяет в каком из имеющихся контроллеров есть метод, принимающий такой
 * адрес), вызывается подходящий метод и Controller возвращает информацию о представлении, затем
 * диспетчер находит нужное представления по имени при помощи ViewResolver'а, после чего на это
 * представление передаются данные модели и на выход мы получаем нашу страничку.
 */
@Entity
@Table(name = "films")
public class Film {
    /**
     * В JPA для этого есть такое понятие как Сущность (Entity). Класс-сущность это обыкновенный
     * POJO класс, с приватными полями и геттерами и сеттерами для них. У него обязательно должен
     * быть не приватный конструктор без параметров (или конструктор по-умолчанию), и он должен
     * иметь первичный ключ, т.е. то что будет однозначно идентифицировать каждую запись этого
     * класса в БД.
     * @Entity — указывает на то, что данный класс является сущностью.
     * @Table — указывает на конкретную таблицу для отображения этой сущности.
     * @Id — указывает, что данное поле является первичным ключом, т.е. это свойство будет
     * использоваться для идентификации каждой уникальной записи.
     * @Column — связывает поле со столбцом таблицы. Если имена поля и столбца таблицы совпадают,
     * можно не указывать.
     * @GeneratedValue — свойство будет генерироваться автоматически, в скобках можно указать
     * каким образом. Достаточно знать, что в данном случае каждое новое значение будет увеличиваться
     * на 1 от предыдущего. В документации Hibernate применять аннотации рекомендуется не к полям, а к геттерам
     * Сделаем класс Film сущностью при помощи JPA аннотаций.
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "year")
    private int year;

    @Column(name = "genre")
    private String genre;

    @Column(name = "watched")
    private boolean watched;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public boolean isWatched() {
        return watched;
    }

    public void setWatched(boolean watched) {
        this.watched = watched;
    }

    @Override
    public String toString() {
        return "Film{" +
                "id= " + id +
                ", title= '" + title + '\'' +
                ", year= " + year +
                ", genre= '" + genre + '\'' +
                ", watched= " + watched +
                '}';
    }
}

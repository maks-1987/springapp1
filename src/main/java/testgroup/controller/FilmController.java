package testgroup.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import testgroup.model.Film;

/**
 * У Spring MVC есть такая штука, как DispatcherServlet. Это как бы главный контроллер,
 * все входящие запросы проходят через него и он уже дальше передает их конкретному контроллеру.
 * Аннотация @Controller как раз и сообщает Spring MVC, что данный класс является контроллером,
 * диспетчер будет проверять аннотации @RequestMapping чтобы вызвать подходящий метод. Аннотация
 * @RequestMapping позволяет задать адреса методам контроллера, по которым они будут доступны в
 * клиенте (браузер). Ее можно применять также и к классу контроллера, чтобы задать, так сказать,
 * корневой адрес для всех методов.
 * Для метода allFilms() параметр value установлен "/", поэтому он будет вызван сразу, когда в
 * браузере будет набрана комбинация http://host:port/ (т.е. по-умолчанию это http://localhost:8080/
 * или http://127.0.0.1:8080/). Параметр method указывает кокой тип запроса поддерживается (GET, POST,
 * PUT и т.д.). Поскольку тут мы только получаем данные то используется GET. Позднее, когда появятся
 * методы для добавления и редактирования там уже будут POST запросы. (Кстати вместо аннотации
 * @RequestMapping с указанием метода, можно использовать аннотации @GetMapping, @PostMapping и т.д.
 * @GetMapping эквивалентно @RequestMapping(method = RequestMethod.GET)). В наших методах создаем
 * объект ModelAndView и устанавливаем имя представления, которое нужно вернуть.
 */
@Controller
public class FilmController {

    private static Film film;
    static {
        film = new Film();
        film.setTitle("Inception");
        film.setYear(2011);
        film.setGenre("sci-fi");
        film.setWatched(true);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView allFilms() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("films");

        modelAndView.addObject("film", film);
        return modelAndView;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public ModelAndView editPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editPage");
        return modelAndView;
    }

}

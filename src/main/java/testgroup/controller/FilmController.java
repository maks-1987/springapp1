package testgroup.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import testgroup.model.Film;
import testgroup.service.FilmService;
import testgroup.service.FilmServiceImpl;

import java.util.List;

/**
 * У Spring MVC есть такая штука, как DispatcherServlet. Это как бы главный контроллер,
 * все входящие запросы проходят через него и он уже дальше передает их конкретному контроллеру.
 * Аннотация @Controller как раз и сообщает Spring MVC, что данный класс является контроллером,
 * диспетчер будет проверять аннотации @RequestMapping чтобы вызвать подходящий метод. Аннотация
 *
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
    private FilmService filmService = new FilmServiceImpl();

    /**
     * Тут ничего нового. Получаем список фильмов из сервиса и добавляем его в модель.
     */
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView allFilms() {
        List<Film> films = filmService.allFilms();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("films");
        modelAndView.addObject("filmList", films);
        return modelAndView;
    }

    /**
     * @PathVariable аннотация указывает на то, что данный параметр (int id) получается из
     * адресной строки. Чтобы указать место этого параметра в адресной строке используется
     * конструкция {id} (если имя переменной совпадает, как в данном случае, то в скобках
     * это можно не указывать, а написать просто @PathVariable int id).
     */
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView editPage(@PathVariable("id") int id) {
        Film film = filmService.getById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editPage");
        //modelAndView.addObject("film", film);
        modelAndView.addObject("film", filmService.getById(id));
        return modelAndView;
    }

    /**
     * И теперь с помощью аннотации @ModelAttribute мы получаем атрибут "film" из строки
     * modelAndView.addObject("film", filmService.getById(id)); и можем его изменить.
     * Метод запроса POST, потому что здесь будем передавать данные.
     * "redirect:/" означает, что после выполнения данного метода мы будем перенаправлены
     * на адрес "/", т.е. запустится метод allFilms и мы вернемся на главную страницу.
     */
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ModelAndView editFilm(@ModelAttribute("film") Film film) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/");
        filmService.edit(film);
        return modelAndView;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView addPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editPage");
        return modelAndView;
    }
}

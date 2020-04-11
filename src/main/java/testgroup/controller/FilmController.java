package testgroup.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import testgroup.model.Film;
import testgroup.service.FilmService;

import java.util.List;

/**
 * У Spring MVC есть такая штука, как DispatcherServlet. Это как бы главный контроллер,
 * все входящие запросы проходят через него и он уже дальше передает их конкретному контроллеру.
 * Аннотация @Controller как раз и сообщает Spring MVC, что данный класс является контроллером,
 * диспетчер будет проверять аннотации @RequestMapping чтобы вызвать подходящий метод.
 * Аннотация @RequestMapping позволяет задать адреса методам контроллера, по которым они будут доступны в
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

    private FilmService filmService;

    /**
     * И теперь нам больше не нужно самим создавать конкретные объекты этих классов:
     *          private FilmService filmService = new FilmServiceImpl();
     * Вместо этого можно пометить поле специальной аннотацией и Spring сам подберет подходящую реализацию.
     * Аннотация @Autowired (автосвязывание) сообщает Spring о том, что он должен покопаться у
     * себя в контексте и подставить сюда подходящий бин. Очень удобно. Если до этого мы использовали
     * интерфейсы, чтобы не беспокоиться насчет конкретной реализации методов, то теперь нам не нужно
     * беспокоиться даже насчет реализации самого интерфейса и даже знать ее название.
     */
    @Autowired
    public void setFilmService(FilmService filmService) {
        this.filmService = filmService;
    }

    /**
     * Тут ничего нового. Получаем список фильмов из сервиса и добавляем его в модель.
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
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
     * Это был метод для получения страницы редактирования
     */
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView editPage(@PathVariable("id") int id) {
        Film film = filmService.getById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editPage");
        modelAndView.addObject("film", filmService.getById(id));
        return modelAndView;
    }

    /**
     * И теперь с помощью аннотации @ModelAttribute мы получаем атрибут "film" из строки
     * modelAndView.addObject("film", filmService.getById(id)); и можем его изменить.
     * Метод запроса POST, потому что здесь будем передавать данные.
     * "redirect:/" означает, что после выполнения данного метода мы будем перенаправлены
     * на адрес "/", т.е. запустится метод allFilms и мы вернемся на главную страницу.
     * Метод для самого редактирования.
     */
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ModelAndView editFilm(@ModelAttribute("film") Film film) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/");
        filmService.edit(film);
        return modelAndView;
    }

    /**
     * Метод для получения страницы:
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView addPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editPage");
        return modelAndView;
    }

    /**
     * Поскольку атрибут мы сюда не передавали, здесь будет создан новый объект Film.
     * Стоит также обратить внимание, что у нас оба метода доступны по адресу "/add".
     * Это возможно благодаря тому, что они реагируют на разные типы запроса. Переходя
     * по ссылке на главной странице мы делаем GET-запрос, что приводит нас в метод addPage.
     * А когда на странице добавления мы жмем кнопку отправки данных, делается POST-запрос,
     * за это уже отвечает метод addFilm. Это метод для добавления:
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView addFilm(@ModelAttribute("film") Film film, BindingResult bindingResult) { // BindingResult позволит отображать на странице добавленные фильмы
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/");
        filmService.add(film);
        return modelAndView;
    }

    /**
     * метод контроллера для удаления фильма из списка
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public ModelAndView deleteFilm(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/");
        Film film = filmService.getById(id);
        filmService.delete(film);
        return modelAndView;
    }


}

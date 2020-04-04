package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * У Spring MVC есть такая штука, как DispatcherServlet. Это как бы главный контроллер,
 * все входящие запросы проходят через него и он уже дальше передает их конкретному контроллеру.
 * Аннотация @Controller как раз и сообщает Spring MVC, что данный класс является контроллером,
 * диспетчер будет проверять аннотации @RequestMapping чтобы вызвать подходящий метод. Аннотация
 * @RequestMapping позволяет задать адреса методам контроллера, по которым они будут доступны в
 * клиенте (браузер). Ее можно применять также и к классу контроллера, чтобы задать, так сказать,
 * корневой адрес для всех методов.
 */
@Controller
public class FilmController {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView allFilms() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("films");
        return modelAndView;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public ModelAndView editPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editPage");
        return modelAndView;
    }

}

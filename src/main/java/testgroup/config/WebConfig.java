package testgroup.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * В нем будет только один метод возвращающий объект типа ViewResolver, это такой
 * интерфейс, необходимый для нахождения представления по имени.
 * @Configuration сообщает Spring что данный класс является конфигурационным и содержит
 * определения и зависимости bean-компонентов. Бины (bean) — это объекты, которые
 * управляются Spring'ом. Для определения бина используется аннотация @Bean.
 * @EnableWebMvc позволяет импортировать конфигурацию Spring MVC из класса WebMvcConfigurationSupport.
 * Можно также реализовать, например, интерфейс WebMvcConfigurer, у которого есть целая
 * куча методов, и настроить все по своему вкусу.
 * @ComponentScan сообщает Spring где искать компоненты, которыми он должен управлять,
 * т.е. классы, помеченные аннотацией @Component или ее производными, такими как @Controller,
 * @Repository, @Service. Эти аннотации автоматически определяют бин класса. В методе
 * viewResolver() мы создаем его реализацию и определяем где именно искать представления
 * в webapp. Поэтому когда в методе контроллера мы устанавливали имя "films" представление
 * найдется как "/pages/films.jsp". Нам нужно зарегистрировать эту конфигурацию в контексте
 * Spring. Для этого нужен класс AbstractAnnotationConfigDispatcherServletInitializer,
 * создаем его наследника, допустим AppInitializer, и реализуем его методы.
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "testgroup")
public class WebConfig {

    @Bean
    ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/pages/"); // То, что внутри, будет скрыто для публики и
                                                    // получить доступ можно будет только через контроллер.
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }
}

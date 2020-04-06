package testgroup.config;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;

/**
 * В пакете testgroup.config создаем наследника AbstractAnnotationConfigDispatcherServletInitializer,
 * допустим AppInitializer, и реализуем его методы.
 * В getServletMappings() методе регистрируются адреса и еще есть 2 метода для регистрации классов
 * конфигурации. Веб-конфигурации, где определяются ViewResolver'ы и тому подобное, помещаем
 * в getServletConfigClasses(), в этом случае не обязательно пока в это углубляться, наш WebConfig
 * в принципе можно и в RootClasses определить, можно даже и в оба сразу, все равно будет работать.
 */
public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[0];
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{WebConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    /**
     * Возможно будут проблемы с кодировкой, когда при отправке с формы значений с русскими
     * символами в результате будут получаться каракули. Для решения этой проблемы добавим
     * фильтр, который будет заниматься предварительной обработкой запросов. В классе
     * AppInitializer переопределяем метод getServletFilters(), в котором укажем нужную кодировку,
     * она разумеется должна быть такой же как и везде, как на страницах и в базе данных.
     */
    @Override
    protected Filter[] getServletFilters() {
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);
        return new Filter[] {characterEncodingFilter};
    }
}

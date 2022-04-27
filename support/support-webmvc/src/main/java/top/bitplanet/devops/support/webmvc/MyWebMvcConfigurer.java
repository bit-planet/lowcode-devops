package top.bitplanet.devops.support.webmvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


//
// @Configuration
public class MyWebMvcConfigurer implements WebMvcConfigurer {

    @Value("${spring.jackson.date-format}")
    private String dateFormat;
    /**
     * 配置静态访问资源
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // registry.addResourceHandler("/oss/**").addResourceLocations("");
    }



    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        for (int i = 0; i < converters.size(); i++) {
            HttpMessageConverter<?> messageConverter = converters.get(i);
            if (messageConverter instanceof MappingJackson2HttpMessageConverter) {
                appendTo((MappingJackson2HttpMessageConverter)messageConverter);
                break;
            }
        }

    }

    /**
     * 解决springboot LocalDateTime 日期格式兼容问题
     * @param mappingJackson2HttpMessageConverter
     */
    public void appendTo(MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter) {
        ObjectMapper objectMapper = mappingJackson2HttpMessageConverter.getObjectMapper() ;
        SimpleModule javaTimeModule = new SimpleModule();
        // LocalDateTime的 序列化 和 反序列化
        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(dateFormat)));
        javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(dateFormat)));
        objectMapper.registerModule(javaTimeModule);
        // 设置日期格式
        objectMapper.setDateFormat(new SimpleDateFormat(dateFormat));
        // 设置中文编码格式
        List<MediaType> list = new ArrayList<MediaType>();
        list.add(MediaType.APPLICATION_JSON);
        mappingJackson2HttpMessageConverter.setSupportedMediaTypes(list);
    }


}

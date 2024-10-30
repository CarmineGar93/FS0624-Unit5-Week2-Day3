package CarmineGargiulo.FS0624_Unit5_Week2_Day3;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Configuration
public class FormatterConfiguration {
    @Bean
    public DateTimeFormatter getStringoneFormatter() {
        return DateTimeFormatter.ofPattern("EEE MMM dd yyyy HH:mm:ss 'GMT'Z '('zzzz')'",
                Locale.ENGLISH);
    }

    @Bean
    public DateTimeFormatter getFormatter1() {
        return DateTimeFormatter.ofPattern("dd/MM/yyyy");
    }

    @Bean
    public DateTimeFormatter getFormatter2() {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd");
    }
}
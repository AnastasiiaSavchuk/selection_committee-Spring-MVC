package selection_committee.api.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import selection_committee.interceptor.UserInterceptor;

@Configuration
@RequiredArgsConstructor
public class UserInterceptorConfig implements WebMvcConfigurer {
    private final UserInterceptor interceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(interceptor);
    }
}

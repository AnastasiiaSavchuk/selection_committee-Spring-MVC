package selection_committee.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Slf4j
@Component
public class UserInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        long start = System.currentTimeMillis();
        log.info("'preHandle' --> User session : " + request.getSession().getId());
        request.setAttribute("start", start);
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        long start = (long) request.getAttribute("start");
        long finish = System.currentTimeMillis();
        log.info("'afterCompletion' --> Finish time : " + (finish - start));
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}

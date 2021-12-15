package ee.siimp.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class AppController {

    private static final Logger LOG = LoggerFactory.getLogger(AppController.class);

    @GetMapping("/")
    public String index(@RequestHeader(HttpHeaders.USER_AGENT) String userAgent) {
        LOG.info("User is using browser {}", userAgent);
        return "Hello World";
    }

}

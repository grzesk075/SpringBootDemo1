import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class CLI {
    @RequestMapping("/")
    String hello() {
        return "Hello world."
    }
}
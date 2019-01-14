package io.pivotal.lars.friday.resilience.resilience;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * MessageController
 */
@RestController
public class MessageController {

    private static final int SECOND = 1000;
    private static final int MINUTE = 60000;

    @GetMapping("/")
    public String okay() {
        return "I'm fine.";
    }

    @GetMapping("/slow")
    public String slow() throws InterruptedException {
        Thread.sleep(SECOND);
        return "I'm fine, just slow";
    }

    @GetMapping("/extremelyslow")
    public String extremelySlow() throws InterruptedException {
        Thread.sleep(10 * MINUTE);
        return "I'm not fine, but will respond, most likely after your timeout has expired";
    }

    @GetMapping("/error")
    public String error() {
        throw new InternalServerErrorException("I'm definitely not fine!");
    }

}
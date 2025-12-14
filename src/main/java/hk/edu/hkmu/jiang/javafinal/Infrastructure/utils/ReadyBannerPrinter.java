package hk.edu.hkmu.jiang.javafinal.Infrastructure.utils;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ReadyBannerPrinter {

    @EventListener(ApplicationReadyEvent.class)
    public void printReadyBanner() {

        System.out.println();
        System.out.println("==================================================================");
        System.out.println("    🚀 Application started successfully!                   🚀");
        System.out.println("    🚀 The service is ready and can be accessed normally.  🚀");
        System.out.println("==================================================================");
        System.out.println();
    }
}
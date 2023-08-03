package spi;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {


        LoggerService service = LoggerService.getService();
        System.in.read();
//        LoggerService.testLoad();

        service.info("Hello SPI");
        service.debug("Hello SPI");
    }
}

package ru.bashkeev.spring.postprocessor;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.bashkeev.spring.config.TestConfig;
import ru.bashkeev.spring.service.CalculatorService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CachingBeanPostProcessorTest {

    @Test
    void testCalculatorServiceCaching() {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(TestConfig.class);

        CalculatorService service = context.getBean(CalculatorService.class);

        long   startTimeFirst = System.nanoTime();
        double first          = service.complexCalculation(2, 3);
        long   endTimeFirst   = System.nanoTime();
        long   durationFirst  = endTimeFirst - startTimeFirst;

        long   startTimeSecond = System.nanoTime();
        double second          = service.complexCalculation(2, 3);
        long   endTimeSecond   = System.nanoTime();
        long   durationSecond  = endTimeSecond - startTimeSecond;

        double durationFirstSec  = durationFirst / 1_000_000_000.0;
        double durationSecondSec = durationSecond / 1_000_000_000.0;

        System.out.println("First call duration: " + durationFirstSec + " seconds");
        System.out.println("Second call duration: " + durationSecondSec + " seconds");

        assertEquals(first, second, "Результат должен быть кеширован");
        assertTrue(durationSecond < durationFirst, "Время второго вызова должно быть меньше первого, если результат кешируется.");

        long   startTimeThird = System.nanoTime();
        double third          = service.complexCalculation(3, 4);
        long   endTimeThird   = System.nanoTime();
        long   durationThird  = endTimeThird - startTimeThird;

        long   startTimeFourth = System.nanoTime();
        double fourth          = service.complexCalculation(3, 4);
        long   endTimeFourth   = System.nanoTime();
        long   durationFourth  = endTimeFourth - startTimeFourth;

        double durationThirdSec  = durationThird / 1_000_000_000.0;
        double durationFourthSec = durationFourth / 1_000_000_000.0;

        System.out.println("Third call duration: " + durationThirdSec + " seconds");
        System.out.println("Fourth call duration: " + durationFourthSec + " seconds");

        assertEquals(third, fourth, "Результат для новых аргументов тоже должен кешироваться.");
        assertTrue(durationFourth < durationThird, "Время четвертого вызова должно быть меньше третьего.");

        context.close();
    }
}

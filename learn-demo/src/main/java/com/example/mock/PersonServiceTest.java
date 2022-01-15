package com.example.mock;

import org.joda.time.DateTimeFieldType;
import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;

/**
 * @author Chen Xiao
 * @since 2021-05-23 13:52
 */
public class PersonServiceTest {
    private PersonDao     mockDao;
    private PersonService personService;

    @Before
    public void setUp() throws Exception {
        //模拟PersonDao对象
        mockDao = mock(PersonDao.class);
        when(mockDao.getPerson(1)).thenReturn(new Person(1, "Person1"));
        when(mockDao.update(isA(Person.class))).thenReturn(true);

        personService = new PersonService(mockDao);
    }

    @Test
    public void testUpdate() throws Exception {
        boolean result = personService.update(1, "new name");
        assertTrue("must true", result);
        //验证是否执行过一次getPerson(1)
        verify(mockDao, times(1)).getPerson(eq(1));
        //验证是否执行过一次update
        verify(mockDao, times(1)).update(isA(Person.class));
    }

    @Test
    public void testUpdateNotFind() throws Exception {
//        boolean result = personService.update(2, "new name");
//        assertFalse("must true", result);
        //验证是否执行过一次getPerson(1)
        verify(mockDao, times(1)).getPerson(eq(1));
        //验证是否执行过一次update
        verify(mockDao, never()).update(isA(Person.class));
    }

    @Test
    public void a(){
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setTime(new Date(1604073600000L));
        int weekOfYear = calendar.get(Calendar.WEEK_OF_YEAR);
        // calendar.getWeekYear() 解决了跨年问题
//        return (calendar.getWeekYear() - 2000) + String.format("%02d", weekOfYear);

        System.out.println(weekOfYear);
        System.out.println(calendar.getWeekYear());
        System.out.println((calendar.getWeekYear() - 2000) + String.format("%02d", weekOfYear));

        System.out.println(calendar.get(Calendar.YEAR)+""+weekOfYear);
        System.out.println(Integer.valueOf(calendar.getWeekYear()+""+String.format("%02d", weekOfYear)));
    }

    @Test
    public void b(){
        int i = new LocalDate(1577721600000L).get(DateTimeFieldType.weekOfWeekyear());
        System.out.println(i);

    }

}

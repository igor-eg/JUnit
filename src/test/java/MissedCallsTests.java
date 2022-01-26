
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class MissedCallsTests {

    MissedCalls sut;


    private static long suiteStartTime;
    private long testStartTime;

    @BeforeAll
    public static void initSuite() {
        System.out.println("Running StringTest");
        suiteStartTime = System.nanoTime();
    }

    @AfterAll
    public static void completeSuite() {
        System.out.println("StringTest complete: " + (System.nanoTime() - suiteStartTime));
    }

    @BeforeEach
    public void initTest() {
        System.out.println("Starting new nest");
        testStartTime = System.nanoTime();
        sut = new MissedCalls();
    }

    @AfterEach
    public void finalizeTest() {
        System.out.println("Test complete:" + (System.nanoTime() - testStartTime));
    }


    @ParameterizedTest
    @MethodSource("source1")

    public void testAddContact(String phone, Contact contact) {

        // when:
        boolean result = sut.addContact(phone, contact);

        // then:
        Assertions.assertTrue(result);

    }

    private static Stream<Arguments> source1() {
        return Stream.of(Arguments.of("121-111-38", new Contact("Вася", "Иванов", "111-111-38", Group.FRIENDS)),
                Arguments.of("222", new Contact("Федор", "Петров", "222", Group.FAMILY)));

    }


    @ParameterizedTest
    @MethodSource("source")

    public void testAddMissedCalls(LocalDateTime dt, MissedCalls missedCall) {
        // when:
        boolean result = sut.addMissedCalls(dt, missedCall);

        // then:
        Assertions.assertTrue(result);

    }

    private static Stream<Arguments> source() {

        return Stream.of(Arguments.of(LocalDateTime.parse("2018-11-10T12:45:30"), new MissedCalls(LocalDateTime.parse("2018-11-10T12:45:30"), "111-111")),
                Arguments.of(LocalDateTime.parse("2018-11-03T12:45:30"), new MissedCalls(LocalDateTime.parse("2018-11-03T12:45:30"), "222-222")));


    }


    @Test
    public void testClearMissedCalls() {
        // given:


        // when:
        boolean result = sut.clearMissedCalls();

        // then:
        Assertions.assertTrue(result);
    }

    @Test
    public void givenMapAndKey_whenKeyFoundInMap_thenCorrect() {
        Map<String, Contact> contacts = new HashMap<>();
        contacts.put("333-333", new Contact("Саша", "Федорова", "333-333", Group.FRIENDS));
        assertThat(contacts, hasKey("333-333"));
    }

    @Test
    public void givenMapAndValue_whenValueFoundInMap_thenCorrect() {
        Map<String, Contact> contacts = new HashMap<>();
        contacts.put("444-444", new Contact("Коля", "Николаев", "444-444", Group.WORK));
        assertThat(contacts, hasValue(new Contact("Коля", "Николаев", "444-444", Group.WORK)));
    }

    @Test
    public void givenMapAndEntry_whenEntryFoundInMap_thenCorrect() {
        Map<String, Contact> contacts = new HashMap<>();
        contacts.put("555-555-555", new Contact("Игнат", "Семенов", "555-555-555", Group.FRIENDS));
        assertThat(contacts, hasEntry("555-555-555", new Contact("Игнат", "Семенов", "555-555-555", Group.FRIENDS)));

    }
}






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

    MissedCalls sut = new MissedCalls();


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
                Arguments.of("111-111-3444", new Contact("Вася", "Иванов", "111-111-3444", Group.FRIENDS)));

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

        return Stream.of(Arguments.of(LocalDateTime.parse("2018-11-03T12:45:30"), new MissedCalls(LocalDateTime.parse("2018-11-03T12:45:30"), "111-111-36")),
                Arguments.of(LocalDateTime.parse("2018-11-03T12:45:40"), new MissedCalls(LocalDateTime.parse("2018-11-03T12:45:40"), "111-111-37")));


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
        contacts.put("111-111-38", new Contact("Вася", "Иванов", "111-111-38", Group.FRIENDS));
        assertThat(contacts, hasKey("111-111-38"));
    }

    @Test
    public void givenMapAndValue_whenValueFoundInMap_thenCorrect() {
        Map<String, Contact> contacts = new HashMap<>();
        contacts.put("111-111-38", new Contact("Вася", "Иванов", "111-111-38", Group.FRIENDS));
        assertThat(contacts, hasValue(new Contact("Вася", "Иванов", "111-111-38", Group.FRIENDS)));
    }

    @Test
    public void givenMapAndEntry_whenEntryFoundInMap_thenCorrect() {
        Map<String, Contact> contacts = new HashMap<>();
        contacts.put("111-111-38", new Contact("Вася", "Иванов", "111-111-38", Group.FRIENDS));
        assertThat(contacts, hasEntry("111-111-38", new Contact("Вася", "Иванов", "111-111-38", Group.FRIENDS)));

    }
}





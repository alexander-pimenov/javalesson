package covariance;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;


public class ProducerTest {

    @Test
    public void whenInputIsArbitrary_thenProducerProducesStringTest() {
        String arbitraryInput = "just a random text";
        Producer producer = new Producer();

        Object objectOutput = producer.produce(arbitraryInput);

        assertEquals(arbitraryInput, objectOutput);
        assertEquals(String.class, objectOutput.getClass());
    }

    @Test
    public void whenInputIsSupported_thenProducerCreatesIntegerTest() {
        String integerAsString = "42";
        Producer producer = new IntegerProducer();

        Object result = producer.produce(integerAsString);

        assertEquals(Integer.class, result.getClass());
        assertEquals(Integer.parseInt(integerAsString), result);
    }

    @Test
    public void whenInputIsSupported_thenIntegerProducerCreatesIntegerWithoutCastingTest() {
        String integerAsString = "42";
        IntegerProducer producer = new IntegerProducer();

        Integer result = producer.produce(integerAsString);

        assertEquals(Integer.parseInt(integerAsString), result);
    }

    @Test
    public void whenInputIsSupported_andCheckReturnedTypeTest() {
        String integerAsString = "42";
        var producer = new Producer();
        var integerProducer = new IntegerProducer();

        Object object = producer.produce(integerAsString);
        Integer integer = integerProducer.produce(integerAsString);

        assertEquals(String.class, object.getClass());
        assertEquals(Integer.class, integer.getClass());
    }
}
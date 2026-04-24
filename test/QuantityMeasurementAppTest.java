import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    QuantityMeasurementApp.Length l(double v, QuantityMeasurementApp.Length.LengthUnit u) {
        return new QuantityMeasurementApp.Length(v, u);
    }

    // ---------- CONVERSION TESTS ----------

    @Test
    public void feetToInches() {
        assertEquals(12.0,
                QuantityMeasurementApp.Length.convert(1,
                        QuantityMeasurementApp.Length.LengthUnit.FEET,
                        QuantityMeasurementApp.Length.LengthUnit.INCHES),
                0.0001);
    }

    @Test
    public void yardToFeet() {
        assertEquals(3.0,
                QuantityMeasurementApp.Length.convert(1,
                        QuantityMeasurementApp.Length.LengthUnit.YARDS,
                        QuantityMeasurementApp.Length.LengthUnit.FEET),
                0.0001);
    }

    @Test
    public void cmToInches() {
        assertEquals(0.393701,
                QuantityMeasurementApp.Length.convert(1,
                        QuantityMeasurementApp.Length.LengthUnit.CENTIMETERS,
                        QuantityMeasurementApp.Length.LengthUnit.INCHES),
                0.0001);
    }

    @Test
    public void instanceConversion() {
        var result = l(3, QuantityMeasurementApp.Length.LengthUnit.FEET)
                .convertTo(QuantityMeasurementApp.Length.LengthUnit.INCHES);

        assertEquals(36.0, result.value, 0.0001);
    }

    // ---------- INVALID INPUT TESTS ----------

    @Test
    public void nullUnitThrowsException() {
        assertThrows(IllegalArgumentException.class, () ->
                QuantityMeasurementApp.Length.convert(1, null,
                        QuantityMeasurementApp.Length.LengthUnit.FEET));
    }

    @Test
    public void invalidValueThrowsException() {
        assertThrows(IllegalArgumentException.class, () ->
                QuantityMeasurementApp.Length.convert(Double.NaN,
                        QuantityMeasurementApp.Length.LengthUnit.FEET,
                        QuantityMeasurementApp.Length.LengthUnit.INCHES));
    }

    // ---------- EQUALITY TESTS ----------

    @Test
    public void yardEqualsFeet() {
        assertTrue(l(1, QuantityMeasurementApp.Length.LengthUnit.YARDS)
                .equals(l(3, QuantityMeasurementApp.Length.LengthUnit.FEET)));
    }

    @Test
    public void yardEqualsInches() {
        assertTrue(l(1, QuantityMeasurementApp.Length.LengthUnit.YARDS)
                .equals(l(36, QuantityMeasurementApp.Length.LengthUnit.INCHES)));
    }

    @Test
    public void centimeterEqualsInches() {
        assertTrue(l(1, QuantityMeasurementApp.Length.LengthUnit.CENTIMETERS)
                .equals(l(0.393701, QuantityMeasurementApp.Length.LengthUnit.INCHES)));
    }

    @Test
    public void transitiveProperty() {
        var yard = l(1, QuantityMeasurementApp.Length.LengthUnit.YARDS);
        var feet = l(3, QuantityMeasurementApp.Length.LengthUnit.FEET);
        var inches = l(36, QuantityMeasurementApp.Length.LengthUnit.INCHES);

        assertTrue(yard.equals(feet));
        assertTrue(feet.equals(inches));
        assertTrue(yard.equals(inches));
    }

    @Test
    public void notEqualCase() {
        assertFalse(l(1, QuantityMeasurementApp.Length.LengthUnit.YARDS)
                .equals(l(2, QuantityMeasurementApp.Length.LengthUnit.FEET)));
    }
}
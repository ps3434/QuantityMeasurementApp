class QuantityMeasurementAppTest {

    private static final double EPSILON = 1e-6;

    // -------- Equality --------
    @Test
    void testKgToGramEquality() {
        var w1 = new QuantityMeasurementApp.Weight(1, QuantityMeasurementApp.WeightUnit.KILOGRAM);
        var w2 = new QuantityMeasurementApp.Weight(1000, QuantityMeasurementApp.WeightUnit.GRAM);
        assertEquals(w1, w2);
    }

    @Test
    void testKgToPoundEquality() {
        var w1 = new QuantityMeasurementApp.Weight(1, QuantityMeasurementApp.WeightUnit.KILOGRAM);
        var w2 = new QuantityMeasurementApp.Weight(2.20462, QuantityMeasurementApp.WeightUnit.POUND);
        assertEquals(w1, w2);
    }

    @Test
    void testWeightVsLength_NotEqual() {
        var w = new QuantityMeasurementApp.Weight(1, QuantityMeasurementApp.WeightUnit.KILOGRAM);
        var l = new QuantityMeasurementApp.Length(1, QuantityMeasurementApp.LengthUnit.FEET);
        assertNotEquals(w, l);
    }

    // -------- Conversion --------
    @Test
    void testPoundToKg() {
        var result = new QuantityMeasurementApp.Weight(2.20462,
                QuantityMeasurementApp.WeightUnit.POUND)
                .convertTo(QuantityMeasurementApp.WeightUnit.KILOGRAM);

        assertEquals(1.0, result.convertTo(QuantityMeasurementApp.WeightUnit.KILOGRAM)
                .convertTo(QuantityMeasurementApp.WeightUnit.KILOGRAM)
                .convertTo(QuantityMeasurementApp.WeightUnit.KILOGRAM)
                .convertTo(QuantityMeasurementApp.WeightUnit.KILOGRAM)
                .convertTo(QuantityMeasurementApp.WeightUnit.KILOGRAM)
                .value, EPSILON);
    }

    @Test
    void testSameUnitConversion() {
        var w = new QuantityMeasurementApp.Weight(5,
                QuantityMeasurementApp.WeightUnit.KILOGRAM);
        assertEquals(w, w.convertTo(QuantityMeasurementApp.WeightUnit.KILOGRAM));
    }

    // -------- Addition --------
    @Test
    void testAddition_KgPlusKg() {
        var result = new QuantityMeasurementApp.Weight(1,
                QuantityMeasurementApp.WeightUnit.KILOGRAM)
                .add(new QuantityMeasurementApp.Weight(2,
                        QuantityMeasurementApp.WeightUnit.KILOGRAM));

        assertEquals(new QuantityMeasurementApp.Weight(3,
                QuantityMeasurementApp.WeightUnit.KILOGRAM), result);
    }

    @Test
    void testAddition_KgPlusGram() {
        var result = new QuantityMeasurementApp.Weight(1,
                QuantityMeasurementApp.WeightUnit.KILOGRAM)
                .add(new QuantityMeasurementApp.Weight(1000,
                        QuantityMeasurementApp.WeightUnit.GRAM));

        assertEquals(new QuantityMeasurementApp.Weight(2,
                QuantityMeasurementApp.WeightUnit.KILOGRAM), result);
    }

    @Test
    void testAddition_ExplicitUnit() {
        var result = new QuantityMeasurementApp.Weight(1,
                QuantityMeasurementApp.WeightUnit.KILOGRAM)
                .add(new QuantityMeasurementApp.Weight(1000,
                                QuantityMeasurementApp.WeightUnit.GRAM),
                        QuantityMeasurementApp.WeightUnit.GRAM);

        assertEquals(new QuantityMeasurementApp.Weight(2000,
                QuantityMeasurementApp.WeightUnit.GRAM), result);
    }

    @Test
    void testAddition_WithZero() {
        var result = new QuantityMeasurementApp.Weight(5,
                QuantityMeasurementApp.WeightUnit.KILOGRAM)
                .add(new QuantityMeasurementApp.Weight(0,
                        QuantityMeasurementApp.WeightUnit.GRAM));

        assertEquals(new QuantityMeasurementApp.Weight(5,
                QuantityMeasurementApp.WeightUnit.KILOGRAM), result);
    }

    @Test
    void testInvalidInput() {
        assertThrows(IllegalArgumentException.class, () ->
                new QuantityMeasurementApp.Weight(Double.NaN,
                        QuantityMeasurementApp.WeightUnit.KILOGRAM));
    }
}
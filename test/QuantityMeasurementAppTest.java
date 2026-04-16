package com.apps.quantitymeasurement;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.apps.quantitymeasurement.QuantityMeasurementApp.Quantity;
import com.apps.quantitymeasurement.QuantityMeasurementApp.LengthUnit;

public class QuantityMeasurementAppTest {

    // SAME UNIT - SAME VALUE
    @Test
    public void testEquality_FeetToFeet_SameValue() {
        Quantity q1 = new Quantity(1.0, LengthUnit.FEET);
        Quantity q2 = new Quantity(1.0, LengthUnit.FEET);
        assertTrue(q1.equals(q2));
    }

    @Test
    public void testEquality_InchToInch_SameValue() {
        Quantity q1 = new Quantity(1.0, LengthUnit.INCH);
        Quantity q2 = new Quantity(1.0, LengthUnit.INCH);
        assertTrue(q1.equals(q2));
    }

    // CROSS UNIT EQUALITY
    @Test
    public void testEquality_FeetToInch_EquivalentValue() {
        Quantity q1 = new Quantity(1.0, LengthUnit.FEET);
        Quantity q2 = new Quantity(12.0, LengthUnit.INCH);
        assertTrue(q1.equals(q2));
    }

    @Test
    public void testEquality_InchToFeet_EquivalentValue() {
        Quantity q1 = new Quantity(12.0, LengthUnit.INCH);
        Quantity q2 = new Quantity(1.0, LengthUnit.FEET);
        assertTrue(q1.equals(q2));
    }

    // DIFFERENT VALUES
    @Test
    public void testEquality_FeetToFeet_DifferentValue() {
        Quantity q1 = new Quantity(1.0, LengthUnit.FEET);
        Quantity q2 = new Quantity(2.0, LengthUnit.FEET);
        assertFalse(q1.equals(q2));
    }

    @Test
    public void testEquality_InchToInch_DifferentValue() {
        Quantity q1 = new Quantity(1.0, LengthUnit.INCH);
        Quantity q2 = new Quantity(2.0, LengthUnit.INCH);
        assertFalse(q1.equals(q2));
    }

    // NULL CHECK
    @Test
    public void testEquality_NullComparison() {
        Quantity q1 = new Quantity(1.0, LengthUnit.FEET);
        assertFalse(q1.equals(null));
    }

    // SAME REFERENCE
    @Test
    public void testEquality_SameReference() {
        Quantity q1 = new Quantity(1.0, LengthUnit.FEET);
        assertTrue(q1.equals(q1));
    }

    // INVALID UNIT
    @Test
    public void testEquality_NullUnit() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Quantity(1.0, null);
        });
    }
}
package com.apps.quantitymeasurement;

import java.util.Objects;

public class QuantityMeasurementApp {

    static class Length {

        private final double value;
        private final LengthUnit unit;

        public enum LengthUnit {
            FEET(12.0),
            INCHES(1.0),
            YARDS(36.0),
            CENTIMETERS(0.393701);

            private final double factor;

            LengthUnit(double factor) {
                this.factor = factor;
            }

            public double getFactor() {
                return factor;
            }
        }

        public Length(double value, LengthUnit unit) {
            if (unit == null)
                throw new IllegalArgumentException("Unit cannot be null");
            if (!Double.isFinite(value))
                throw new IllegalArgumentException("Invalid value");

            this.value = value;
            this.unit = unit;
        }

        private double toBase() {
            return value * unit.getFactor(); // base = inches
        }

        public static double convert(double value, LengthUnit from, LengthUnit to) {
            if (from == null || to == null)
                throw new IllegalArgumentException("Units cannot be null");

            if (!Double.isFinite(value))
                throw new IllegalArgumentException("Invalid value");

            double base = value * from.getFactor();
            return base / to.getFactor();
        }

        public Length convertTo(LengthUnit target) {
            double newValue = convert(this.value, this.unit, target);
            return new Length(newValue, target);
        }

        public boolean compare(Length other) {
            if (other == null) return false;
            return Math.abs(this.toBase() - other.toBase()) < 0.0001;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Length)) return false;
            return compare((Length) o);
        }

        @Override
        public int hashCode() {
            return Objects.hash(toBase());
        }

        @Override
        public String toString() {
            return value + " " + unit;
        }
    }


    public static double demonstrateLengthConversion(
            double value, Length.LengthUnit from, Length.LengthUnit to) {

        double result = Length.convert(value, from, to);
        System.out.println(value + " " + from + " = " + result + " " + to);
        return result;
    }

    public static double demonstrateLengthConversion(
            Length length, Length.LengthUnit to) {

        Length result = length.convertTo(to);
        System.out.println(length + " = " + result);
        return result.value;
    }

    public static boolean demonstrateLengthEquality(Length l1, Length l2) {
        boolean result = l1.equals(l2);
        System.out.println("Equal? " + result);
        return result;
    }

    public static void main(String[] args) {

        demonstrateLengthConversion(1, Length.LengthUnit.FEET, Length.LengthUnit.INCHES);
        demonstrateLengthConversion(1, Length.LengthUnit.YARDS, Length.LengthUnit.FEET);
        demonstrateLengthConversion(100, Length.LengthUnit.CENTIMETERS, Length.LengthUnit.INCHES);

        Length len = new Length(3, Length.LengthUnit.FEET);
        demonstrateLengthConversion(len, Length.LengthUnit.INCHES);

        demonstrateLengthEquality(
                new Length(1, Length.LengthUnit.YARDS),
                new Length(36, Length.LengthUnit.INCHES)
        );
    }
}
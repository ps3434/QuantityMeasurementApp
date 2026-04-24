import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementApp {

    // ================= LENGTH =================
    enum LengthUnit {
        FEET(1.0),
        INCHES(1.0 / 12.0),
        YARDS(3.0),
        CENTIMETERS(0.0328084);

        private final double factor;

        LengthUnit(double factor) { this.factor = factor; }

        double toBase(double value) { return value * factor; }
        double fromBase(double base) { return base / factor; }
    }

    static class Length {
        private final double value;
        private final LengthUnit unit;
        private static final double EPSILON = 1e-6;

        public Length(double value, LengthUnit unit) {
            if (unit == null || !Double.isFinite(value))
                throw new IllegalArgumentException();
            this.value = value;
            this.unit = unit;
        }

        public Length convertTo(LengthUnit target) {
            double base = unit.toBase(value);
            return new Length(target.fromBase(base), target);
        }

        private double toBase() {
            return unit.toBase(value);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Length other = (Length) obj;
            return Math.abs(this.toBase() - other.toBase()) < EPSILON;
        }
    }

    // ================= WEIGHT =================
    enum WeightUnit {
        KILOGRAM(1.0),
        GRAM(0.001),
        POUND(0.453592);

        private final double factor;

        WeightUnit(double factor) { this.factor = factor; }

        double toBase(double value) { return value * factor; }
        double fromBase(double base) { return base / factor; }
    }

    static class Weight {
        private final double value;
        private final WeightUnit unit;
        private static final double EPSILON = 1e-6;

        public Weight(double value, WeightUnit unit) {
            if (unit == null || !Double.isFinite(value))
                throw new IllegalArgumentException();
            this.value = value;
            this.unit = unit;
        }

        public Weight convertTo(WeightUnit target) {
            double base = unit.toBase(value);
            return new Weight(target.fromBase(base), target);
        }

        public Weight add(Weight other) {
            double sumBase = this.toBase() + other.toBase();
            return new Weight(unit.fromBase(sumBase), unit);
        }

        public Weight add(Weight other, WeightUnit target) {
            double sumBase = this.toBase() + other.toBase();
            return new Weight(target.fromBase(sumBase), target);
        }

        private double toBase() {
            return unit.toBase(value);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Weight other = (Weight) obj;
            return Math.abs(this.toBase() - other.toBase()) < EPSILON;
        }

        @Override
        public String toString() {
            return value + " " + unit;
        }
    }

    // ================= MAIN =================
    public static void main(String[] args) {

        // Equality
        System.out.println(new Weight(1, WeightUnit.KILOGRAM)
                .equals(new Weight(1000, WeightUnit.GRAM))); // true

        // Conversion
        System.out.println(new Weight(2, WeightUnit.POUND)
                .convertTo(WeightUnit.KILOGRAM));

        // Addition
        System.out.println(new Weight(1, WeightUnit.KILOGRAM)
                .add(new Weight(1000, WeightUnit.GRAM)));

        System.out.println(new Weight(1, WeightUnit.KILOGRAM)
                .add(new Weight(1000, WeightUnit.GRAM), WeightUnit.GRAM));
    }
}
package com.scriptbasic.utility.functions;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import com.scriptbasic.api.BasicFunction;
import com.scriptbasic.api.ScriptBasicException;
import com.scriptbasic.interfaces.BasicRuntimeException;
import com.scriptbasic.utility.CastUtility;
import com.scriptbasic.utility.NoInstance;

/**
 * This class contains static methods that are place holders to register the
 * methods of the class {@code java.lang.Math} for the BASIC programs. If the
 * documentation of the method does not specify different then the method in
 * this class can be invoked directly from a BASIC program and does exactly the
 * same as the method of the same name in the class {@code java.lang.Math}.
 * <p>
 * When a method accepts {@code Number} arguments it means that the BASIC can
 * call the function with integer or with floating point arguments.
 *
 * @author Peter Verhas
 */
@SuppressWarnings({"SameReturnValue", "UnusedReturnValue"})
public class MathFunctions {

    /**
     * Rounding mode used by round function
     */
    static public RoundingMode roundingMode = RoundingMode.HALF_EVEN;

    private MathFunctions() {
        NoInstance.isPossible();
    }

    @BasicFunction(classification = com.scriptbasic.classification.Math.class)
    static public Number abs(final Number x) throws ScriptBasicException {
        if (x instanceof Double) {
            return Math.abs((Double) x);
        }
        if (x instanceof Long) {
            return ((Long) x) > 0 ? x : -((Long) x);

        }
        throw new BasicRuntimeException(
                "MathFunctions.abs(Number x) was called with an argument that is neither Double, nor Long: "
                        + x.toString() + "of type " + x.getClass().toString());
    }

    @BasicFunction(substituteClass = java.lang.Math.class, classification = com.scriptbasic.classification.Math.class)
    static public double acos(final double x) {
        return 0.0;
    }

    @BasicFunction(substituteClass = java.lang.Math.class, classification = com.scriptbasic.classification.Math.class)
    static public double asin(final double x) {
        return 0.0;
    }

    @BasicFunction(substituteClass = java.lang.Math.class, classification = com.scriptbasic.classification.Math.class)
    static public double atan(final double x) {
        return 0.0;
    }

    @BasicFunction(substituteClass = java.lang.Math.class, classification = com.scriptbasic.classification.Math.class)
    static public double atan2(final double x, final double y) {
        return 0.0;
    }

    @BasicFunction(substituteClass = java.lang.Math.class, classification = com.scriptbasic.classification.Math.class)
    static public double cbrt(final double x) {
        return 0.0;
    }

    @BasicFunction(substituteClass = java.lang.Math.class, classification = com.scriptbasic.classification.Math.class)
    static public double ceil(final double x) {
        return 0.0;
    }

    @BasicFunction(substituteClass = java.lang.Math.class, classification = com.scriptbasic.classification.Math.class)
    public static double copySign(final double magnitude, final double sign) {
        return 0.0;
    }

    @BasicFunction(substituteClass = java.lang.Math.class, classification = com.scriptbasic.classification.Math.class)
    static public double cos(final double x) {
        return 0.0;
    }

    @BasicFunction(substituteClass = java.lang.Math.class, classification = com.scriptbasic.classification.Math.class)
    static public double cosh(final double x) {
        return 0.0;
    }

    @BasicFunction(substituteClass = java.lang.Math.class, classification = com.scriptbasic.classification.Math.class)
    public static double exp(final double a) {
        return 0.0;
    }

    @BasicFunction(substituteClass = java.lang.Math.class, classification = com.scriptbasic.classification.Math.class)
    public static double expm1(final double x) {
        return 0.0;
    }

    /**
     * Convert a value to floating point. This method can be called directly
     * from the BASIC program and it will return a java Double, that is
     * internally a BASIC float. (Note that ScriptBasic for Java does not use
     * float or int, only Long and Double.)
     * <p>
     * Use this function from the BASIC program if the BASIC interpreter does
     * not convert automatically a number to floating point but you need a
     * floating point number and not an integer.
     *
     * @param s the number to convert
     * @return the converted number or {@code null}, which means undefined in
     * BASIC in case the parameter {@code s} passed can not be converted
     * to floating point
     */
    @BasicFunction(classification = {com.scriptbasic.classification.Utility.class}, alias = "float")
    static public Double floatF(final Object s) {
        try {
            return (Double) CastUtility.cast(s, Double.class);
        } catch (final ClassCastException cce) {
            return null;
        }
    }

    /**
     * Convert a value to integer. This method can be called directly from the
     * BASIC program and it will return a java Long, that is internally a BASIC
     * integer. (Note that ScriptBasic for Java does not use float or int, only
     * Long and Double.)
     * <p>
     * Use this function from the BASIC program if the BASIC interpreter does
     * not convert automatically a number to integer but you need an integer
     * number and not a floating point number.
     *
     * @param s the number to convert
     * @return the converted number or {@code null}, which means undefined in
     * BASIC in case the parameter {@code s} passed can not be converted
     * to integer
     */
    @BasicFunction(classification = {com.scriptbasic.classification.Utility.class})
    static public Long integer(final Object s) {
        try {
            return (Long) CastUtility.cast(s, Long.class);
        } catch (final ClassCastException cce) {
            return null;
        }
    }

    @BasicFunction(substituteClass = java.lang.Math.class, classification = com.scriptbasic.classification.Math.class)
    static public double floor(final double x) {
        return 0.0;
    }

    @BasicFunction(substituteClass = java.lang.Math.class, classification = com.scriptbasic.classification.Math.class)
    public static Long getExponent(final double d) {
        return (Long) (long) Math.getExponent(d);
    }

    @BasicFunction(substituteClass = java.lang.Math.class, classification = com.scriptbasic.classification.Math.class)
    public static double hypot(final double x, final double y) {
        return 0.0;
    }

    @BasicFunction(substituteClass = java.lang.Math.class, classification = com.scriptbasic.classification.Math.class)
    public static double IEEEremainder(final double f1, final double f2) {
        return 0.0;
    }

    @BasicFunction(substituteClass = java.lang.Math.class, classification = com.scriptbasic.classification.Math.class)
    static public double log(final double x) {
        return 0.0;
    }

    @BasicFunction(substituteClass = java.lang.Math.class, classification = com.scriptbasic.classification.Math.class)
    static public double log10(final double x) {
        return 0.0;
    }

    @BasicFunction(substituteClass = java.lang.Math.class, classification = com.scriptbasic.classification.Math.class)
    public static double log1p(final double x) {
        return 0.0;
    }

    @BasicFunction(classification = com.scriptbasic.classification.Math.class)
    public static Number max(final Number a, final Number b) {
        if (a instanceof Double && b instanceof Double) {
            return Math.max((Double) a, (Double) b);
        }
        if (a instanceof Long && b instanceof Long) {
            return Math.max((Long) a, (Long) b);

        }
        return null;
    }

    @BasicFunction(classification = com.scriptbasic.classification.Math.class)
    public static Number min(final Number a, final Number b) {
        if (a instanceof Double && b instanceof Double) {
            return Math.min((Double) a, (Double) b);
        }
        if (a instanceof Long && b instanceof Long) {
            return Math.min((Long) a, (Long) b);

        }
        return null;
    }

    @BasicFunction(substituteClass = java.lang.Math.class, classification = com.scriptbasic.classification.Math.class)
    public static double pow(final double a, final double b) {
        return 0.0;
    }

    @BasicFunction(substituteClass = java.lang.Math.class, classification = com.scriptbasic.classification.Math.class)
    public static double random() {
        return 0.0;
    }

    @BasicFunction(substituteClass = java.lang.Math.class, classification = com.scriptbasic.classification.Math.class)
    public static double rint(final double a) {
        return 0.0;
    }

    /**
     * Returns a number rounded to a specified number of decimal places.
     * 
     * This function returns something commonly referred to as bankers rounding. 
     * So be careful before using this function. Same behavior has 'round' in VBA.
     * 
     * @param value Numeric value being rounded
     * @param numdecimalplaces Number indicating how many places to the right of the decimal 
     *        are included in the rounding.
     * @return rounded value
     */
    @BasicFunction(classification = com.scriptbasic.classification.Math.class)
    static public Number round(final double value, Integer numdecimalplaces) {
        
        final BigDecimal bd = BigDecimal.valueOf(value);

        if (numdecimalplaces == null || numdecimalplaces == 0) {
            return bd.setScale(0, roundingMode).intValue();
        } else {
            final MathContext mc = new MathContext(numdecimalplaces, roundingMode);
            return bd.round(mc).doubleValue();
        }
    }

    @BasicFunction(substituteClass = java.lang.Math.class, classification = com.scriptbasic.classification.Math.class)
    public static double scalb(final double d, final int scaleFactor) {
        return 0.0;
    }

    @BasicFunction(substituteClass = java.lang.Math.class, classification = com.scriptbasic.classification.Math.class)
    public static double signum(final double d) {
        return 0.0;
    }

    @BasicFunction(substituteClass = java.lang.Math.class, classification = com.scriptbasic.classification.Math.class)
    static public double sin(final double x) {
        return 0.0;
    }

    @BasicFunction(substituteClass = java.lang.Math.class, classification = com.scriptbasic.classification.Math.class)
    public static double sinh(final double x) {
        return 0.0;
    }

    @BasicFunction(substituteClass = java.lang.Math.class, classification = com.scriptbasic.classification.Math.class)
    public static double sqrt(final double a) {
        return 0.0;
    }

    @BasicFunction(substituteClass = java.lang.Math.class, classification = com.scriptbasic.classification.Math.class)
    static public double tan(final double x) {
        return 0.0;
    }

    @BasicFunction(substituteClass = java.lang.Math.class, classification = com.scriptbasic.classification.Math.class)
    static public double tanh(final double x) {
        return 0.0;
    }

    @BasicFunction(substituteClass = java.lang.Math.class, classification = com.scriptbasic.classification.Math.class)
    public static double toDegrees(final double angrad) {
        return 0.0;
    }

    @BasicFunction(substituteClass = java.lang.Math.class, classification = com.scriptbasic.classification.Math.class)
    public static double toRadians(final double angdeg) {
        return 0.0;
    }
}

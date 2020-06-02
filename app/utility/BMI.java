package utility;

public enum BMI
        /* Programming assignment instructions:
    Returns the category the BMI belongs to, based on the following values:
    BMI less than 16 (exclusive) is "SEVERELY UNDERWEIGHT"
    BMI between 16 (inclusive) and 18.5 (exclusive) is "UNDERWEIGHT"
    BMI between 18.5 (inclusive) and 25(exclusive) is "NORMAL"
    BMI between 25 (inclusive) and 30 (exclusive) is "OVERWEIGHT"
    BMI between 30 (inclusive) and 35 (exclusive) is "MODERATELY OBESE"
    BMI greater than 35 (inclusive) and is "SEVERELY OBESE"
     */
{
    SEVERELY_UNDERWEIGHT
            (0.0, 16.0) {
        @Override
        public String toString(){ return "Severely Underweight";}
    },
    UNDERWEIGHT
            (16, 18.5) {
        @Override
        public String toString(){ return "Underweight";}
    },
    NORMAL
            (18.5, 25) {
        @Override
        public String toString(){ return "Normal";}
    },
    OVERWEIGHT
            (25, 30) {
        @Override
        public String toString(){ return "Overweight";}
    },
    MODERATELY_OBESE
            (30, 35) {
        @Override
        public String toString(){ return "Moderately Obese";}
    },
    SEVERELY_OBESE
            (35, 100) {
        @Override
        public String toString(){ return "Severely Obese";}
    };

    private double rangeLow;
    private double rangeHigh;

    private BMI(double rangeLow, double rangeHigh) {
        this
                .rangeLow = rangeLow;
        this
                .rangeHigh = rangeHigh;
    }
    public boolean bmiCategory(double bmiValue){
        if ((bmiValue >= this
                .rangeLow) && (bmiValue < this
                .rangeHigh)){
            return true;
        }
        return false;
    }



    //Conversion formula
    public static class Conversion
    {
        public static double round(double numberToConvert, double precision)
        {
            double p = Math.pow(10, precision);
            return (double) Math.round(numberToConvert * p) / p;
        }
        public double convertKGtoPounds(double numberToConvert, double precision)
        {
            return round(numberToConvert * 2.2, precision);
        }
        public static double convertMetresToInches(double numberToConvert, double precision)
        {
            return round(numberToConvert * 39.37, precision);
        }
    }

}


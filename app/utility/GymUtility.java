package utility;

import models.Assessment;
import models.Member;
import play.Logger;

import java.util.List;

public class GymUtility {
    
    public static MemberReport generateMemberReports(Member member) {
        MemberReport stats = new MemberReport();
        double weight = member.startingweight;
        List<Assessment> assessments = member.assessments;
        if (assessments.size() > 0) {
            Assessment assessment = assessments.get(assessments.size() - 1);
            weight = assessment.weight;
        }
        stats.bmi = calculateBMI(member, weight);
        stats.bmiCategory = determineBMICategory(stats.bmi);
        stats.isIdealBodyweight = isIdealBodyWeight(member, weight);
        stats.trend = true;
        if (assessments.size()>1) {
            stats.trend = assessments.get(assessments.size() - 2).weight > assessments.get(assessments.size() - 1).weight;
        }
        return stats;
    }

    public static double calculateBMI(Member member, double weight) {
        if (member.height <= 0)
            return 0;
        else
            return BMI.Conversion.round((weight / (member.height * member.height)), 2);
    }

    public static String determineBMICategory(double bmiValue) {
        for (BMI bmi : BMI.values()) {
            if (bmi.bmiCategory(bmiValue)) {
                return bmi.toString();
            }
        }
        return "No category available.";
    }

    public static boolean isIdealBodyWeight(Member member, double weight) {
        /* From Programming assignment:
         For males (1 as value assigned in drop down menu in the SignUp), an ideal body weight is: 50 kg + 2.3 kg for each inch over 5 feet.
         For females (0 as value assigned in drop down menu in the SignUp), an ideal body weight is: 45.5 kg + 2.3 kg for each inch over 5 feet.
         Note: if no gender is specified (2 as value assigned in drop down menu in the SignUp), return the result of the female calculation.
         Note: if the member is 5 feet or less, return 50kg for male and 45.5kg for female.
         To allow for different calculations and rounding, when testing for the ideal body weight, if it is +/- .2 of the devine formula, return true */

        double fiveFeet = 60.0;
        double idealBodyWeight;
        double inches = BMI.Conversion.convertMetresToInches(member.height, 2);
        if (inches <= fiveFeet) {
            if (member.gender == ("M")) {
                idealBodyWeight = 50;
            } else {
                idealBodyWeight = 45.5;
            }
        } else {
            if (member.gender == ("M")) {
                idealBodyWeight = 50 + ((inches - fiveFeet) * 2.3);
            } else {
                idealBodyWeight = 45.5 + ((inches - fiveFeet) * 2.3);
            }
        }
        Logger.info("Ideal Weight" + idealBodyWeight);
        if ((idealBodyWeight <= (weight + 2.0)) && (idealBodyWeight >= (weight - 2.0))){
            return true;
        }
        else {
            return false;
        }
    }

}
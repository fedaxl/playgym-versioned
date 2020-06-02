package controllers;

import java.util.Collections;
import java.util.List;
import models.Member;
import models.Assessment;
import play.Logger;
import play.mvc.Controller;
import utility.GymUtility;
import utility.MemberReport;

public class MemberDashboard extends Controller
{
  public static void index()
  {
    Logger.info("Rendering Member Dashboard");
    Member member = Accounts.getLoggedInMember();
    List<Assessment> assessments = member.assessments;
    MemberReport memberReports = GymUtility.generateMemberReports(member);
    Collections.reverse(assessments);
    render("dashboard.html", member, assessments, memberReports);
  }
  public static void addAssessment(double weight, double chest, double thigh,
                                   double upperarm, double waist, double hips)
  {
    Logger.info("Creating Assessment");
    Member member = Accounts.getLoggedInMember();
    Assessment assessment = new Assessment(weight, chest, thigh, upperarm, waist, hips);
    MemberReport memberReports = GymUtility.generateMemberReports(member);
    assessment.trend = memberReports.trend;
    member.assessments.add(assessment);
    member.save();
    redirect("/dashboard");
  }

  public static void deleteAssessment(Long memberid, Long assessmentid)
  {
    Member member = Member.findById(memberid);
    Assessment assessment = Assessment.findById(assessmentid);
    member.assessments.remove(assessment);
    member.save();
    assessment.delete();
    redirect("/dashboard");
  }
}
package controllers;

import java.util.Collections;
import java.util.List;

import models.Assessment;
import models.Member;
import play.Logger;
import play.mvc.Controller;
import utility.GymUtility;
import utility.MemberReport;

public class TrainerDashboard extends Controller
{
    public static void index()
    {
        List<Member> members = Member.findAll();
        Logger.info("Rendering Trainer Dashboard");
        render("trainerdashboard.html", members);
    }
    public static void trainerAssessment(Long id)
    {
        Member member = Member.findById(id);
        List<Assessment> assessments = member.assessments;
        MemberReport memberReports = GymUtility.generateMemberReports(member);
        Collections.reverse(assessments);
        render("trainerassessment.html", member, assessments, memberReports);
    }
    public static void editComment(Long id, String comment)
    {
        Logger.info("Comment " + comment);
        Assessment assessment = Assessment.findById(id);
        assessment.comment = comment;
        assessment.save();
        redirect("/trainerdashboard");
    }
    public static void deleteMember(Long id)
    {
        Member member = Member.findById(id);
        if (member != null) {
            member.delete();
        }
        redirect("/trainerdashboard");
    }
}
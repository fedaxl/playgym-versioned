package models;

import play.db.jpa.Model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
public class Member extends Model
{
  public String email;
  public String firstname;
  public String lastname;
  public String password;
  public String address;
  public String gender;
  public double height;
  public double startingweight;
  @OneToMany(cascade = CascadeType.ALL)
  public List<Assessment> assessments = new ArrayList<Assessment>();
  public Member(String email, String firstname, String lastname, String password, String address,
                String gender, double height, double startingweight)
  {
    this.email = email;
    this.firstname = firstname;
    this.lastname = lastname;
    this.password = password;
    this.address = address;
    this.gender = gender;
    this.height = height;
    this.startingweight = startingweight;
  }
  public static Member findByEmail(String email)
  {

    return find("email", email).first();
  }
  public boolean checkPassword(String password)
  {
    return this.password.equals(password);
  }


  public List<Assessment> getSortedAssessments() {
    ArrayList<Assessment> sortedAssessments = new ArrayList();
    sortedAssessments.addAll(assessments);
    Collections.sort(sortedAssessments, (arg0, arg1) -> arg1.getTimeStamp().compareTo(arg0.getTimeStamp()));
    return sortedAssessments;
  }

  public Assessment getLatestAssessment() {
    return (assessments == null || assessments.isEmpty()) ? null : getSortedAssessments().get(0);
  }

}
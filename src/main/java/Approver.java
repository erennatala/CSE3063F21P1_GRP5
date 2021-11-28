import org.json.JSONObject;
import org.json.simple.JSONArray;

import java.util.ArrayList;

public class Approver
{

    private Student student;
    private Course course;

    public Approver(Student student)
    {
        this.student = student;
    }

    // getter setter silinince sari highlight ile hata veriyor
    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }



    // capacityChecker checks the capacity of related courses for student
    public boolean capacityChecker()
    {

        int capacity = course.getCapacity();
        ArrayList<Student> studentList = course.getStudents();
        if (capacity > studentList.size())
        {

            return true;
        }
        else
        {
            QuotaError quotaError = new QuotaError(student,course);
            //quotaError.raiseError();
            student.addError(quotaError);
            return false;
        }


    }
    // the method below compares the student's passed courses and prerequisite courses
    public boolean prerequisiteChecker()
    {
        ArrayList<Course> takenCourses = student.getPastCourses();
        ArrayList<Course> prerequisiteCourses = course.getPrerequisites();

        if (takenCourses.containsAll(prerequisiteCourses))
            return true;
        else
            for (Course required :
                    prerequisiteCourses) {
                if (!takenCourses.contains(required)) {
                    PrerequisiteError prerequisiteError = new PrerequisiteError(student,course,required);
                    //prerequisiteError.raiseError();
                    return false;

                }
            }

        return false;
    }
    // approveCourse checks the both method above returns true or false
    public boolean approveCourse(Course course)
    {
        this.course = course;
        return capacityChecker() && prerequisiteChecker();
    }

}

from Transcript import Transcript


class Student:
    # Computed attributes may be declared as properties in methods
    # Semester, Instructor (nonprimitive types might be declared as properties)

    def __init__(self,student_id,name, surname,semester, advisor=None):
        # We can convert all course lists(Active, Past, Failed)
        # To map so that we can hold grades or keep going with 1 grade map as in JAVA
        self.name = name
        self.surname = surname
        self.student_id = student_id
        self.advisor = advisor
        self.active_courses = list()
        self.course_basket = list()
        self.past_courses = list()
        self.non_taken_courses = list()
        self.failed_courses = list()
        self.transcript = Transcript(semester.semester_id)
        self.semester = semester
        self.gpa = 0
        self.cgpa = 0
        self.completed_credit = 0
        self.grade_map = dict()

    def next_semester(self,semester):
        """
            Grade pass-fail
            Active credit - cumulative credit - completed_credit
            calculate gpa-cgpa
            set active - past - failed courses
            add semester to transcript
        """
    """
    Basket_To_Active_Courses:
        create grade
        We can assign next semester here but it is not logical
        clear basket
    """

    def add_error(self,error,course):
        self.non_taken_courses.append(course)
        self.transcript.add_error(error)

    def add_course_basket(self,course):
        self.course_basket.append(course)

    def remove_from_basket(self,will_be_removed):
        self.course_basket = list(set(self.course_basket) - set(will_be_removed))

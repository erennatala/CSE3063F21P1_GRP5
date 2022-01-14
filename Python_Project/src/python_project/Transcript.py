class Transcript:
    active_credit = 0
    active_grade = 0
    cumulative_credit = 0
    cumulative_grade = 0

    def __init__(self, student):
        # advisor and student id can be added.
        self.student = student
        self.transcript_map = {}

    def create_semester(self):
        self.semester_id = "1"  # this pseudocode semester id needs to be taken from student.
        semester_map = {"Courses": {}, "Errors": []}
        # semester id which we get from student is necessary for all functions to work.
        self.transcript_map["Semester-" + self.semester_id] = semester_map

    def add_course(self, course, grade):  # grade will be taken from student.
        # semester_id = semester.id
        self.transcript_map["Semester-" + self.semester_id]["Courses"][course] = grade
        print(self.transcript_map)

    def add_error(self, error):
        self.transcript_map["Semester-" + self.semester_id]["Errors"].append(error)
        print(self.transcript_map)

    def add_gpa(self):  # grade will be taken from student.
        # gpa = student.gpa
        self.transcript_map["Semester-" + self.semester_id]["GPA"] = 10
        self.transcript_map["Semester-" + self.semester_id]["Active Credit"] = self.active_credit
        return self.transcript_map

    # cgpa can be added.


class Transcript:

    def __init__(self, semester_id):
        self.transcript_map = dict()
        self.semester_id = semester_id
        self.create_semester()

    def create_semester(self):
        semester_map = {"Courses": dict(), "Errors": list()}
        self.transcript_map["Semester-" + self.semester_id] = semester_map


    def add_course(self, course_id, letter_grade):
        self.transcript_map["Semester-" + self.semester_id]["Courses"][course_id] = letter_grade

    def add_error(self, error):
        self.transcript_map["Semester-" + self.semester_id]["Errors"].append(error)

    def add_gpa(self, gpa, active_credit, cgpa, cumulative_credit):
        self.transcript_map["Semester-" + self.semester_id]["GPA"] = gpa
        self.transcript_map["Semester-" + self.semester_id]["Active Credit"] = active_credit
        self.transcript_map["CGPA"] = cgpa
        self.transcript_map["Cumulative Credit"] = cumulative_credit

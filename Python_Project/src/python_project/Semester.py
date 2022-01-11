class Semester:
    def __init__(self, semester_id, season):
        self.semester_id = semester_id
        self.course_list = []
        self.season = season

    def __str__(self) -> str:
        return "Semester{" + "semesterId =" + self.semester_id + "}"

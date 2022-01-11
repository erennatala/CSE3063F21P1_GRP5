class Course:

    def __init__(self, course_id, name, capacity=0, credit=0, ects=0, instructor: str = None, schedule: str = None):
        self.course_id = course_id
        self.name = name
        self.capacity = capacity
        self.credit = credit
        self.ects = ects
        self.instructor = instructor
        self.students = list()
        self.prerequisites = list()
        self.schedule = schedule

    def add_student(self, student):
        self.students.append(student)

    def add_prerequisites(self, prerequisites):
        self.prerequisites.append(prerequisites)

from Schedule import Schedule


class Course:

    def __init__(self, course_id, name, course_type, capacity=0, instructor=None, credit=0, ects=0, schedule_list=None):
        self.course_id = course_id
        self.name = name
        self.course_type = course_type
        self.capacity = capacity
        self.instructor = instructor
        self.credit = credit
        self.ects = ects
        self.students = list()
        self.prerequisites = None
        self.schedule = dict()  # { "Day": Schedule() }
        self.set_schedule(schedule_list)
        """self.error_map=dict()"""
    def add_student(self, student):
        self.students.append(student)

    """def add_prerequisites(self, prerequisites):
       May be read all of them together
        self.prerequisites.extend(prerequisites)
"""
    def set_schedule(self, schedule_list):
        """This method creates each schedule object of course"""
        for val in schedule_list:
            day = val['Day']
            self.schedule[day] = Schedule(val['Day'], val['Start'], val['End'])

    def compare(self,second_course):
        for sch in self.schedule:
            second_sch = second_course.schedule[sch.Day]
            if sch.compare(second_sch):
                """Collision error
                    second_course.error_map add
                """

    def __str__(self) -> str:
        return f'Course_id={self.course_id}, name={self.name}, instructor={self.instructor}, schedule={self.schedule}'

    def __repr__(self) -> str:
        return f'Course_id={self.course_id}, name={self.name}, instructor={self.instructor}, schedule={self.schedule}'


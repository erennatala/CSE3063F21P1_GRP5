class Student:
    # Computed attributes may be declared as properties in methods
    # Semester, Instructor (nonprimitive types might be declared as properties)

    def __init__(self, name, surname, id=0, advisor: str = None):
        # We can convert all course lists(Active, Past, Failed)
        # To map so that we can hold grades or keep going with 1 grade map as in JAVA
        self.name = name
        self.surname = surname
        self.id = id
        self.advisor = advisor
        self.active_courses = list()
        self.course_basket = list()
        self.past_courses = list()
        self.non_taken_courses = list()
        self.failed_courses = list()
        # self.transcript = Transcript()

    @property
    def id(self):
        return self._id

    @id.setter
    def id(self, value):
        if value < 0:
            raise ValueError('Student Id can not be negative')
        self._id = value

    def __str__(self) -> str:
        return 'ID: %d Name: %s Surname: %s' % (self._id, self.name, self.surname)

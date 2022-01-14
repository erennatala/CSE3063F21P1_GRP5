from Student import Student
from Semester import Semester
from Instructor import Instructor
from Course import Course
import random


class Department:

    def __init__(self):
        self.students = dict()
        self.courses = dict()
        self.semesters = dict()
        self.instructors = dict()
        self.create_semesters()

    def create_semesters(self):
        for semester_id in range(1, 9):
            if semester_id % 2 == 1:
                season = 'Fall'
            else:
                season = 'Spring'
            self.semesters[semester_id] = Semester(semester_id, season)

    def create_instructor(self, instructor_id, name, surname):
        fullname = name + ' ' + surname
        self.instructors[fullname] = Instructor(instructor_id, name, surname)

    def create_course(self, course_id, name, course_type, instructor_fullname, capacity=0, credit=0, ects=0,
                      schedule_list=None, semester_id=None):
        try:
            instructor = self.instructors[instructor_fullname]
        except KeyError:
            instructor = None
        course = Course(course_id, name, course_type, capacity, instructor, credit, ects, schedule_list)
        try:
            self.courses[course_type].append(course)
        except KeyError:
            self.courses[course_type] = list()
            self.courses[course_type].append(course)
        try:
            """ Creating Semester Course """
            self.semesters[semester_id].add_course(course)
        except KeyError:
            """ Creating Elective Course """
            pass

    def add_prerequisites(self, main_course, prerequisite_courses):
        """Prerequisite courses will be a list of courses"""
        main_course.prerequisites = prerequisite_courses

    def create_student(self, student_id, name, surname, semester_id=1):
        semester = self.semesters[semester_id]
        advisor = random.choice(list(self.instructors.values()))
        student = Student(student_id, name, surname, semester, advisor)
        self.students[student_id] = student

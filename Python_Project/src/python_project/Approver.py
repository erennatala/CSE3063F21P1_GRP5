class Approver:

    def __init__(self, student):
        self.student = student

    def capacity_checker(self, course):
        student_list = course.students
        capacity = course.capacity
        # capacity = 0

        if capacity > len(student_list):
            return True
        else:
            # error will be added.
            return False

    def prerequisite_checker(self, course):
        past_courses_list = self.student.past_courses
        prerequisites_courses_list = course.prerequisites

        for required in prerequisites_courses_list:
            if required not in past_courses_list:
                # error will be added
                pass
                if course not in self.student.non_taken_courses:
                    self.student.non_taken_courses.append(course)

    def is_elective_taken(self, course):
        past_courses_list = self.student.past_courses
        course_basket_list = self.student.course_basket

        if course in past_courses_list:  # if and elif can be merged
            return False
        elif course in course_basket_list:
            return False
        else:
            return True

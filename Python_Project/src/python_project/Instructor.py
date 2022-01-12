from Course import Course
from Student import Student
from Error import Error

class Instructor:

    course_basket = list()

    def __init__(self, id, name, surname):
        self.id = id
        self.name = name
        self.surname = surname
        self.fullname = name + ' ' + surname

    def __iter__(self):
        return self

    # def __next__(self):
    #
    #     basket_size = len(self.course_basket)
    #
    #     if self.course_basket[] ==

    def check_two_technical(self):

        #course_basket = student.course_basket
        math103 = Course('math103', 'mat')
        uni103 = Course('uni103', 'mat')
        engr101 = Course('engr101', 'emgr')
        phys104 = Course('phys104', 'fizk')
        course_basket = [math103, engr101, uni103, phys104]
        count = 0
        course_iter = iter(course_basket)
        new_course_basket = list()
        while True:
            try:
                item = next(course_iter)

                if isinstance(item, Course): #burası technical olcal
                    count+=1

                    if count>2:
                        technicalElevtiveError = Error('advisor', item.course_id, 'twoTechnicalElective')
                        technicalElevtiveError.raise_error()

                        count -= 1
                    else:
                        new_course_basket.append(item)
                else:
                    new_course_basket.append(item)
            except StopIteration:
                break

    def check_fte(self, student):
        # course_basket = student.course_basket
        pass


    def check_graduation_project(self, student):
        pass

    def check_minimum_credit(self, student):
        pass

    def select_non_taken_course(self, course):
        pass

    def check_collision(self, course_basket, student):
        pass

    def approve_student_basket(self, student):
        pass

inst = Instructor(1, 'ahmet', 'mehmet')

inst.check_two_technical()
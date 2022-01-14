import json
import pathlib
import os

class InputReader:
    """ Try to remove department"""
    def read_instructor(self, department):
        path = pathlib.Path(__file__).parent.parent.joinpath('resources/instructors.json')
        f = open(path, 'r', encoding='utf-8')
        data = json.load(f)
        for i in data:
            id = i['instructorID']
            name = i['name']
            surname = i['surname']

            department.create_instructor(id, name, surname)
        f.close()

    def read_courses(self, department):
        path = pathlib.Path(__file__).parent.parent.joinpath('resources/curriculum.json')
        f = open(path, 'r', encoding='utf-8')
        data = json.load(f)

        for title in data:
            for courses in data[title]:

                course_id = courses['courseId']
                course_name = courses['courseName']
                course_type = courses['Type']
                credit = courses['Credit']
                ects = courses['ECTS']
                instructor = courses['Instructor']
                capacity = courses['Capacity']
                schedule_list = courses['Schedule']
                semester_id = title

                department.create_course(course_id, course_name, course_type, instructor, capacity, credit, ects, schedule_list, semester_id)
        f.close()

    def read_students(self, department, start_index):
        path = pathlib.Path(__file__).parent.parent.joinpath('resources/students.json')
        f = open(path, 'r', encoding='utf-8')
        data = json.load(f)
        index = start_index
        for i in data:

            index = i['index']
            name = i['name']
            surname = i['surname']
            id = 1000+index

            if index < start_index:
                continue

            if index == start_index + 70:
                return index

            department.create_student(id, name, surname)
        f.close()

    def read_prerequisite(self, department):
        path = pathlib.Path(__file__).parent.parent.joinpath('resources/prerequisite.json')
        f = open(path, 'r', encoding='utf-8')
        data = json.load(f)

        for course, pre_list in data.items():
            department.add_prerequisites(course, pre_list)
        f.close()

    def read_transcript(self):
        path = 'transcripts/'

        for file_name in [file for file in os.listdir(path) if file.endswith('.json')]: #reads all files in a dir, using for loop
            print(file_name)
            f = open(path+file_name, 'r', encoding='utf-8')
            data = json.load(f)
            print(data)
            f.close()
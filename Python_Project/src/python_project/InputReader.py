import json
import pathlib
import os

from Instructor import Instructor

class InputReader:

    def read_instructor(self):
        path = pathlib.Path(__file__).parent.parent.joinpath('resources/instructors.json')
        f = open(path, 'r', encoding='utf-8')
        data = json.load(f)
        for i in data:
            id = i['instructorID']
            name = i['name']
            surname = i['surname']
            fullname = i['fullName']
            #Instructor(id, name, surname, fullname)

        f.close()

    def read_courses(self):
        path = pathlib.Path(__file__).parent.parent.joinpath('resources/curriculum.json')
        f = open(path, 'r', encoding='utf-8')
        data = json.load(f)
        for i in data:
            for title_key, title_value in i.items():
                for courseiter in title_value:

                    course_id = courseiter['courseId']
                    course_name = courseiter['courseName']
                    type = courseiter['Type']
                    credit = courseiter['Credit']
                    ects = courseiter['ECTS']
                    instructor = courseiter['Instructor']
                    capacity = courseiter['Capacity']
                    schedule = courseiter['Schedule']

                    if title_key[0:8] == 'Semester': #semester mandatory dersler
                        print("Mandatory course")
                        # Course(courseId, courseName, capacity, credit, ects, instructor)

                    elif title_key[1:3] == 'TE': #technical electives
                        print("Technical Elective")

                    elif title_key[1:8] == 'ENG-FTE': #eng-fte's
                        print("Faculty Elective")

                    elif title_key[1:13] == 'NTE / ENG-UE': #ntes
                        print("Non-Technical Elective")

        f.close()

    def read_students(self):
        path = pathlib.Path(__file__).parent.parent.joinpath('resources/students.json')
        f = open(path, 'r', encoding='utf-8')
        data = json.load(f)

        for i in data:
            index = i['index']
            name = i['name']
            surname = i['surname']
            id = 1000+index
            #Student(name, surname, id)

    def read_transcript(self):
        path = 'transcripts/'

        for file_name in [file for file in os.listdir(path) if file.endswith('.json')]: #reads all files in a dir, using for loop
            print(file_name)
            f = open(path+file_name, 'r', encoding='utf-8')
            data = json.load(f)
            print(data)
            f.close()


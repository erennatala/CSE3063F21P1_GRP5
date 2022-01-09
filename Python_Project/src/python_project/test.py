from Student import Student
from Grade import Grade
import json
import os
import pathlib

### print(pathlib.Path(__file__).parent.parent.joinpath("resources/students.json")) -----> Path name for resources

# Student Reader
path = pathlib.Path(__file__).parent.parent.joinpath("resources/students.json")
f = open(path)
data = json.load(f)
students = list()
for i in data:
    name = i["name"]
    surname = i["surname"]
    index = i["index"]
    std = Student(name, surname, index)
    students.append(std)
    print(std)
f.close()
# Student Reader End


# print(os.path.join(package_dir,"students.json"))

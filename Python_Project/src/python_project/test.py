from InputReader import InputReader
from OutputWriter import OutputWriter
from Department import Department
from Registrator import Registrator
from Student import Student
from Grade import Grade
import json
import os
import pathlib

### print(pathlib.Path(__file__).parent.parent.joinpath("resources/students.json")) -----> Path name for resources
dpt = Department()
inputReadObj = InputReader()
outputWriteObj = OutputWriter()

inputReadObj.read_instructor(dpt)
inputReadObj.read_courses(dpt)
inputReadObj.read_prerequisite(dpt)
inputReadObj.read_students(dpt,0)

registrator = Registrator(dpt.courses,dpt.semesters)
registrator.start_registration(list(dpt.students.values())[0])

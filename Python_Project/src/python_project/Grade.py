import math
import random


class Grade:
    # Instead of creating grader class grade can be deal with random grading mechanism.
    # May hold course object to calculate credit * letter_grade
    _letter_map = {
        100: "AA", 95: "AA",
        90: "AA", 85: "BA",
        80: "BB", 75: "CB",
        70: "CC", 65: "CC",
        60: "DC", 55: "DC",
        50: "DD", 45: "FD",
        0: "FF"
    }

    def __init__(self):
        self.assign_random_grade()

    # May be computed property
    @property
    def succes_grade(self):
        return self.YIS * 0.6 + self.YSS * 0.4

    @property
    def letter_grade(self):
        try:
            if self.round() < 45:
                print("fail")
                return self._letter_map[0]
            return self._letter_map[self.round()]
        except KeyError:
            print("Letter grade not found")

    def round(self):
        return int(math.floor(self.succes_grade / 5) * 5)

    def generate_random_grade(self):
        random_grade = random.randint(0, 50)
        if random_grade <= 1:
            return random.randint(0, 45)
        else:
            return random.randint(45, 101)

    def assign_random_grade(self):
        self.YIS = self.generate_random_grade()
        self.YSS = self.generate_random_grade()

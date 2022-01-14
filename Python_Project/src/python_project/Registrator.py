class Registrator:
    """
        Registrator object registers a student to current semester. Registration process happens in three steps:
            Try to register student to failed courses
            Try to register student to non taken courses (Courses that advisor didn't allow student to take)
            Try to register student to current semester courses
                If an elective course must be taken, select a random elective course and try to register
            After selecting courses send course basket to advisors approval
                (Courses those are not approved by advisor will be deleted from course basket)
            Add course basket to student's active course list
    """

    def start_registration():
        pass
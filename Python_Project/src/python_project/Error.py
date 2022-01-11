class Error:
    def __init__(self,actor,course_id,reason):
        self.actor = actor
        self.course_id = course_id
        self.reason = reason

    def raise_error(self):
        return self.actor + " did not approve " + self.course_id + self.reason
                # Advisor/System + course which student could not take
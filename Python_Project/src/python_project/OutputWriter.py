import json
import pathlib
import os

class OutputWriter:

    def write_transcript(self, id):
        newpath = 'transcripts'
        if not os.path.exists(newpath):
            os.makedirs(newpath)

        json_obj = {}
        json_obj['name'] = 'Kerem'
        json_obj['surname'] = 'Mican'


        with open('transcripts/sa.json', "w") as transcript_json:
            json.dump(json_obj, transcript_json, indent=4)
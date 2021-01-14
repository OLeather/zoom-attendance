import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

public class ZoomAttendance {
    String ROSTER_FILE_PATH = "Roster.txt";
    String AUTH_TOKEN = "";
    String MEETING_ID = "";
    public void start(){
        ArrayList<Participant> participants = APIManager.getInstance().getActiveParticipants(AUTH_TOKEN, MEETING_ID);
        System.out.println(getParticipantsReport(readRosterText(ROSTER_FILE_PATH), readParticipantsName(participants)));
    }

    public String[] readRosterText(String filePath){
        try {
            return Files.readString(Paths.get(filePath), StandardCharsets.US_ASCII).split(",");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String[]{};
    }

    public String[] readParticipantsName(ArrayList<Participant> participants){
        ArrayList<String> participantsString = new ArrayList<String>();
        for(Participant participant : participants){
            if(participant.getUserName() != null){
                participantsString.add(participant.getUserName());
            }
        }
        return participantsString.toArray(new String[]{});
    }

    public ParticipantsReport getParticipantsReport(String[] roster, String[] participants){
        ArrayList<String> absentStudents = new ArrayList<String>();
        ArrayList<String> presentStudents = new ArrayList<String>();

        for(String student : roster){
            boolean present = false;
            for(String participant : participants){
                if(student.equals(participant)){
                    present = true;
                }
            }
            if(!present){
                absentStudents.add(student);
            }
            else{
                presentStudents.add(student);
            }
        }

        return new ParticipantsReport(absentStudents.toArray(new String[]{}), presentStudents.toArray(new String[]{}));
    }

    public class ParticipantsReport{
        private final String[] absentStudents;
        private final String[] presentStudents;

        public ParticipantsReport(String[] absentStudents, String[] presentStudents){
            this.absentStudents = absentStudents;
            this.presentStudents = presentStudents;
        }

        public String[] getAbsentStudents() {
            return absentStudents;
        }

        public String[] getPresentStudents() {
            return presentStudents;
        }

        @Override
        public String toString() {
            return "ParticipantsReport{" +
                    "absentStudents=" + Arrays.toString(absentStudents) +
                    ", presentStudents=" + Arrays.toString(presentStudents) +
                    '}';
        }
    }
}

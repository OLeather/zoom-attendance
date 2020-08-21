import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class APIManager {
    public static APIManager instance = new APIManager();

    public static APIManager getInstance() {
        return instance;
    }

    private JSONObject receiveParticipantsFromAPI() {
        String authToken = "";
        String meetingId = "";
        String requestURL =
                String.format("https://api.zoom.us/v2/metrics/meetings/%s/participants?page_size=30&type=live",
                        meetingId);
        try {
           return Unirest.get(requestURL)
                    .header("authorization", String.format("Bearer %s", authToken))
                    .asJson().getBody().getObject();
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Participant[] getParticipants() {
        JSONObject participantsResponse = receiveParticipantsFromAPI();
        try {
            JSONArray array = participantsResponse.getJSONArray("participants");
            for(int i = 0; i < array.length(); i++){
                JSONObject participantJsonObject = array.getJSONObject(i);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new Participant[]{};
    }

    public Participant[] getActiveParticipants() {
        Participant[] allParticipants = getParticipants();
        ArrayList<Participant> activeParticipants = new ArrayList<Participant>();
        for (Participant participant : allParticipants) {
            if (participant.getLeaveTime().equals("null")) {
                activeParticipants.add(participant);
            }
        }
        return new Participant[]{};
    }
}

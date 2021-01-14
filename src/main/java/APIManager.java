import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class APIManager {
    public static APIManager instance = new APIManager();

    public static APIManager getInstance() {
        return instance;
    }

    private JSONObject receiveParticipantsFromAPI(String authToken, String meetingId) {
        String requestURL =
                String.format("https://api.zoom.us/v2/metrics/meetings/%s/participants?page_size=30&type=live",
                        meetingId);
        try {
            return Unirest.get(requestURL)
                    .header("Authorization", String.format("Bearer %s", authToken))
                    .asJson().getBody().getObject();
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String testAPI(String authToken) {
        try {
            return Unirest.get("https://api.zoom.us/v2/contacts?page_size=1")
                    .header("authorization", "Bearer " + authToken)
                    .asString().getBody();
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        return "";
    }

    public ArrayList<Participant> getParticipants(String authToken, String meetingId) {
        JSONObject participantsResponse = receiveParticipantsFromAPI(authToken, meetingId);
        System.out.println(participantsResponse);
        ArrayList<Participant> allParticipants = new ArrayList<>();
        try {
            JSONArray array = participantsResponse.getJSONArray("participants");
            for (int i = 0; i < array.length(); i++) {
                JSONObject participantJsonObject = array.getJSONObject(i);
                allParticipants.add(Participant.fromDatabase(participantJsonObject));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return allParticipants;
    }

    public ArrayList<Participant> getActiveParticipants(String authToken, String meetingId) {
        ArrayList<Participant> allParticipants = getParticipants(authToken, meetingId);
        ArrayList<Participant> activeParticipants = new ArrayList<Participant>();
        for (Participant participant : allParticipants) {
            if (participant.getLeaveTime().equals("null")) {
                activeParticipants.add(participant);
            }
        }
        return activeParticipants;
    }
}

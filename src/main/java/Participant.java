import org.json.JSONException;
import org.json.JSONObject;

public class Participant {
    private String id;
    private String userId;
    private String userName;
    private String joinTime;
    private String leaveTime;
    private String leaveReason;
    private String email;

    public Participant() {
    }

    public Participant(String id, String userId, String userName, String joinTime, String leaveTime,
                       String leaveReason, String email) {
        this.id = id;
        this.userId = userId;
        this.userName = userName;
        this.joinTime = joinTime;
        this.leaveTime = leaveTime;
        this.leaveReason = leaveReason;
        this.email = email;
    }

    public static Participant fromDatabase(JSONObject jsonObject) {
        String[] jsonValueIds = {"id", "user_id", "user_name", "join_time", "leave_time", "leave_reason", "email"};
        String[] responseValues = new String[jsonValueIds.length];
        for (int i = 0; i < jsonValueIds.length; i++) {
            try {
                responseValues[i] = jsonObject.getString(jsonValueIds[i]);
            } catch (JSONException e) {
                responseValues[i] = "null";
            }
        }
        return new Participant(responseValues[0], responseValues[1], responseValues[2], responseValues[3],
                responseValues[4], responseValues[5], responseValues[6]);
    }

    public String getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getJoinTime() {
        return joinTime;
    }

    public String getLeaveTime() {
        return leaveTime;
    }

    public String getLeaveReason() {
        return leaveReason;
    }

    public String getEmail() {
        return email;
    }
}

public class APIManager {
    public static APIManager instance = new APIManager();
    public static APIManager getInstance(){
        return instance;
    }

    private String receiveParticipantsFromAPI(){
        return "";
    }

    public Participant[] getParticipants(){
        String participantsResponse = receiveParticipantsFromAPI();
        return new Participant[]{};
    }
}

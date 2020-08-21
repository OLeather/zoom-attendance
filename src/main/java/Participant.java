public class Participant {
    private String id;
    private String userId;
    private String userName;
    private String joinTime;
    private String leaveTime;
    private String leaveReason;
    private String email;

    public Participant(){
    }

    public Participant(String id, String userId, String userName, String joinTime, String leaveTime,
                       String leaveReason, String email){
        this.id = id;
        this.userId = userId;
        this.userName = userName;
        this.joinTime = joinTime;
        this.leaveTime = leaveTime;
        this.leaveReason = leaveReason;
        this.email = email;
    }

    public static Participant fromDatabase(String databaseResponse){
        return new Participant();
    }
}

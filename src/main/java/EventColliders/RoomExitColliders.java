package EventColliders;

public enum RoomExitColliders {
    HUB_TO_COAST("hub", "coast"),
    //TODO: Add more room exits here.
    COAST_TO_HUB("coast", "hub");

    private String pathLeadsTo;
    private String roomName;

    RoomExitColliders(String roomName, String pathLeadsTo) {
        this.roomName = roomName;
        this.pathLeadsTo = pathLeadsTo;
    }

}


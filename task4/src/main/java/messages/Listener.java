package messages;

public interface Listener<Got> {
    void recieveMessage(Got event);
}

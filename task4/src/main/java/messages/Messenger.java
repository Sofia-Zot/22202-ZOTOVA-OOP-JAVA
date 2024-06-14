package messages;

public interface Messenger<Send> {
    void sendMessage(Send event);
}

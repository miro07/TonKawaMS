package auth.services.authservices;

import auth.payload.response.MessageResponse;

public class SignupResult<T> {



    private T user;

    private MessageResponse messageResponse;


    public SignupResult(T user, MessageResponse messageResponse) {
        this.user = user;
        this.messageResponse = messageResponse;
    }
    public SignupResult() {
    }
    public T getUser() {
        return user;
    }

    public void setUser(T user) {
        this.user = user;
    }

    public MessageResponse getMessageResponse() {
        return messageResponse;
    }

    public void setMessageResponse(MessageResponse messageResponse) {
        this.messageResponse = messageResponse;
    }


}

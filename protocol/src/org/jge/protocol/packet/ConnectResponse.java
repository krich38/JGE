package org.jge.protocol.packet;

/**
 * @author Kyle Richards
 * @version 1.0
 */
public class ConnectResponse extends Packet {
    private Response response;

    public ConnectResponse() {
        super(Packet.PacketType.CONNECT);
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public enum Response {
        OK,
        INCORRECT_DETAILS,
        SERVER_CLOSED, BANNED, REGISTER_SUCCESS, REGISTER_FAIL
    }
}

package org.jge.protocol.packet;

/**
 * @author Kyle Richards
 * @version 1.0
 */
public class ProtocolNotice {
    private NoticeType noticeType;
    private Object attachment;

    public NoticeType getNoticeType() {
        return noticeType;
    }

    public ProtocolNotice(NoticeType noticeType) {

        this.noticeType = noticeType;
    }

    public Object getAttachment() {
        return attachment;
    }

    public enum NoticeType {
        CONNECT,
        DISCONNECT,
        REGISTER
    }
}

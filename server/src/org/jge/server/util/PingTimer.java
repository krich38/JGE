package org.jge.server.util;

import com.esotericsoftware.kryonet.Connection;
import org.jge.model.Id;
import org.jge.model.server.PlayerEncap;
import org.jge.model.world.Entity;
import org.jge.protocol.packet.Ping;
import org.jge.server.Server;

import java.util.*;

/**
 * @author Kyle Richards
 * @version 1.0
 */
public class PingTimer {
    private final Map<Id<Entity>, PlayerEncap> players;

    private final Server server;
    private final List<Ping.Pong> replies;
    private long lastTime;
    private List<Id<Entity>> expectingReplies;


    public PingTimer() {
        server = Server.getInstance();
        players = server.getPlayers();
        replies = new ArrayList<>();
        expectingReplies = new ArrayList<>();

    }


    public void pingAll() {
        //System.out.println(players.size());
        lastTime = System.currentTimeMillis();
        for (PlayerEncap pe : players.values()) {
            server.ping(server.getConnectionById(pe.getId()), lastTime);
            expectingReplies.add(pe.getId());
        }
    }

    public void timeout() {
        for (Ping.Pong p : replies) {
            Id<Entity> id = server.getIdByConnection(p.getConnection());
            if ((long) p.getAttachment() == lastTime) {
                players.get(id).setPonged(true);
            } else {
                players.get(id).setPonged(false);
            }

        }
        for (Id<Entity> id : expectingReplies) {
            if (!players.get(id).getPonged()) {
                server.disconnect(id, "Ping timeout");
            }
        }
        for (Ping.Pong p : replies) {
            Id<Entity> id = server.getIdByConnection(p.getConnection());
            players.get(id).setPonged(false);
        }
        replies.clear();
        expectingReplies.clear();


    }

    public void addReply(Ping.Pong pong) {
        replies.add(pong);
    }
}

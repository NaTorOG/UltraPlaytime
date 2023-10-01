package net.pinodev.ultraplaytime.Tasks;

import net.pinodev.ultraplaytime.cache.TopUser;
import org.bukkit.Bukkit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import static net.pinodev.ultraplaytime.UltraPlaytime.*;
import static net.pinodev.ultraplaytime.database.queries.Statements.GET_TOP_USERS;
import static net.pinodev.ultraplaytime.utils.LogUtils.ANSI_RED;
import static net.pinodev.ultraplaytime.utils.LogUtils.ANSI_RESET;

public class TaskLeaderboard {

    void getLeaderboard(){
            userManager.getTopUsers().clear();
            try(Connection conn = database.getConnection();
                PreparedStatement ps = conn.prepareStatement(GET_TOP_USERS.getStatement());
                ResultSet rs = ps.executeQuery()){
                while (rs.next()){
                    final UUID uuid = utilsManager.uuid.fromBytes(rs.getBytes("player_uuid"));
                    String username = Bukkit.getOfflinePlayer(uuid).getName();
                    if(username == null) username = "UNKNOWN";
                    final Long playtime = rs.getLong("playtime");
                    userManager.addTopUser(new TopUser(
                            uuid,
                            playtime,
                            username
                    ));
                }
                logger.info("Fetched "+userManager.getTopUsers().size() + "/10 Top Users from database!");
            }catch (SQLException exception){
                logger.info(ANSI_RED.getColor() + "ERROR: "+ANSI_RESET.getColor() + " while fetching leaderboard... retrying in a while!");
                exception.printStackTrace();
            }
    }

}

package API;

import dev.tjxjnoobie.ffa.Main;
import info.techwizmatt.ServerCore.API.Grade;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

public class ScoreBoard {
    public static String getTime() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM HH:mm");
        return sdf.format(cal.getTime());
    }

    public static String getDate() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("d MMM yyyy");
        return sdf.format(cal.getTime());
    }

    public static String getAdvancedTime() {
        TimeZone timeZone = Calendar.getInstance().getTimeZone();

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        return sdf.format(cal.getTime()) + " " + timeZone.getDisplayName(false, 0);
    }



    public static void leadboard(final Player player) {

        Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective obj = board.registerNewObjective("main", "dummy");
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        obj.setDisplayName(ChatColor.LIGHT_PURPLE + "UHCFFA");


        final Objective objective = board.registerNewObjective("health", "health");
        objective.setDisplayName("§4 ❤");
        objective.setDisplaySlot(DisplaySlot.BELOW_NAME);

        Score you = obj.getScore(
                Bukkit.getOfflinePlayer("§7» §d§lYou §7«"));
        you.setScore(16);

        Score spacer1 = obj.getScore(
                Bukkit.getOfflinePlayer(ChatColor.GRAY + " "));
        spacer1.setScore(14);


        Score p = obj.getScore(Bukkit.getOfflinePlayer("§7» §d§lRank §7«"));
        p.setScore(13);


        Score info = obj.getScore(Bukkit.getOfflinePlayer("§7» §d§lInfo§7 «"));
        info.setScore(10);

        Score blank2 = obj.getScore(Bukkit.getOfflinePlayer(org.bukkit.ChatColor.GRAY + "    "));
        blank2.setScore(4);


        Score server = obj.getScore(
                Bukkit.getOfflinePlayer("§7» §d§lGame §7«"));
        server.setScore(6);


        Score blank6 = obj.getScore(Bukkit.getOfflinePlayer(org.bukkit.ChatColor.GRAY + "              "));
        blank6.setScore(7);




        final Team Playing = board.registerNewTeam("playing");
        Playing.addEntry(ChatColor.WHITE.toString() + "§dPlaying§8:" + ChatColor.WHITE);
        obj.getScore(ChatColor.WHITE.toString() + "§dPlaying§8:" + ChatColor.WHITE).setScore(3);
        int left = Grade.getPlacementMatches(player.getUniqueId().toString(), "UUID");
        if (left > 0) {
            final Team POINTS = board.registerNewTeam("points");
            POINTS.addEntry("§c" + left + " Placements");
            obj.getScore("§c" + left + " Placements ").setScore(11);
            final Team Rank = board.registerNewTeam("rank");
            Rank.addEntry("        ");
            obj.getScore("        ").setScore(12);


        } else {
            final Team POINTS = board.registerNewTeam("points2");
            POINTS.addEntry("§c" + Grade.getGradePoints(player.getUniqueId().toString(), "UUID") + "§8/§a1600");
            obj.getScore("§c" + Grade.getGradePoints(player.getUniqueId().toString(), "UUID") + "§8/§a1600").setScore(11);
            final Team Rank = board.registerNewTeam("rank2");
            Rank.addEntry(Grade.getGrade(player.getUniqueId().toString(), "UUID") + " " + org.bukkit.ChatColor.WHITE);
            obj.getScore(Grade.getGrade(player.getUniqueId().toString(), "UUID") + " " + org.bukkit.ChatColor.WHITE).setScore(12);

        }


        final Team date = board.registerNewTeam("date");
        date.addEntry(org.bukkit.ChatColor.WHITE.toString());
        date.setSuffix(ChatColor.GRAY + getDate());
        obj.getScore(org.bukkit.ChatColor.WHITE.toString()).setScore(9);


        final Team advtime = board.registerNewTeam("advtime");
        advtime.addEntry(org.bukkit.ChatColor.GREEN.toString() + "" + org.bukkit.ChatColor.WHITE);
        obj.getScore(org.bukkit.ChatColor.GREEN.toString() + org.bukkit.ChatColor.WHITE).setScore(8);

        final Team Server = board.registerNewTeam("server");
        Server.addEntry(org.bukkit.ChatColor.DARK_AQUA.toString() + "§5US§8:" + " " + org.bukkit.ChatColor.WHITE);
        Server.setSuffix(ChatColor.GREEN + Bukkit.getServer().getServerId());
        obj.getScore(org.bukkit.ChatColor.DARK_AQUA.toString() + "§5US§8:" + " " + org.bukkit.ChatColor.WHITE).setScore(5);


        final Team You = board.registerNewTeam("you");
        You.addEntry(ChatColor.GRAY.toString() + player.getName() + " " + org.bukkit.ChatColor.WHITE);
        obj.getScore(org.bukkit.ChatColor.GRAY.toString() + player.getName() + " " + org.bukkit.ChatColor.WHITE).setScore(15);

        player.setScoreboard(board);
        new BukkitRunnable() {
            public void run() {
                int online = Main.playing.size();
                int minutes = Map.map / 60;
                int seconds = Map.map % 60;
                String disMinu = (minutes < 10 ? "0" : "") + minutes;
                String disSec = (seconds < 10 ? "0" : "") + seconds;
                String formattedTime = disMinu + ":" + disSec;
                advtime.setSuffix(ChatColor.DARK_GRAY + getAdvancedTime());
                obj.setDisplayName(
                        ChatColor.translateAlternateColorCodes('&', "&5Next Map " + ChatColor.GREEN + formattedTime));
                board.getTeam("playing").setSuffix("§a " + online);


            }
        }.runTaskTimer(Main.getPlugin(), 0L, 20L);

    }
}

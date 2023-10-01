package net.pinodev.ultraplaytime.utils;

import net.md_5.bungee.api.ChatColor;
import net.pinodev.ultraplaytime.placeholder.Placeholders;
import net.pinodev.ultraplaytime.configs.files.Locale;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MessageUtils {
    public String colorized(String message) {
        Pattern pattern = Pattern.compile("#[a-fA-F0-9]{6}");
        Matcher matcher = pattern.matcher(message);
        while (matcher.find()) {
            String hexCode = message.substring(matcher.start(), matcher.end());
            String replaceSharp = hexCode.replace('#', 'x');

            char[] ch = replaceSharp.toCharArray();
            StringBuilder builder = new StringBuilder("");
            for (char c : ch) {
                builder.append("&").append(c);
            }

            message = message.replace(hexCode, builder.toString());
            matcher = pattern.matcher(message);
        }

        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public  void send(Locale message, CommandSender target, List<Placeholders> placeholders){
        final Object messageType = message.value.apply(message.key);
        if(messageType instanceof String){
            String text = (String) messageType;
            text = colorized(text);
            target.sendMessage(replacePlaceholders(text, placeholders));
        }else if(messageType instanceof List<?>){
            final List<?> messages = (List<?>) messageType;
             for(Object value : messages){
                 if(value instanceof String){
                     String text = (String) value;
                     text = colorized(text);
                     target.sendMessage(replacePlaceholders(text, placeholders));
                 }
             }

        }
    }

    public void send(String message, CommandSender target, List<Placeholders> placeholders){
        message = colorized(message);
        target.sendMessage(replacePlaceholders(message, placeholders));
    }
    public void send(List<String> messages, CommandSender target, List<Placeholders> placeholders){
        for(String message : messages){
            message = colorized(message);
            target.sendMessage(replacePlaceholders(message, placeholders));
        }
    }

    public void sendTitle(String upperTitle, String subTitle, final Player target, List<Placeholders> placeholders){
        upperTitle = colorized(upperTitle);
        subTitle = colorized(subTitle);
        target.sendTitle(
                replacePlaceholders(upperTitle, placeholders),
                replacePlaceholders(subTitle, placeholders),
                20,
                100,
                100
        );
    }

    public void send(String message, CommandSender target){
        message = colorized(message);
        target.sendMessage(message);
    }
    public void send(List<String> messages, CommandSender target){
        for(String message : messages){
            message = colorized(message);
            target.sendMessage(message);
        }
    }

    public void sendTitle(String upperTitle, String subTitle, final Player target){
        upperTitle = colorized(upperTitle);
        subTitle = colorized(subTitle);
        target.sendTitle(
                upperTitle,
                subTitle,
                20,
                100,
                100
        );
    }

    public String replacePlaceholders(String text, List<Placeholders> placeholders){
        for(Placeholders placeholder : placeholders){
            text = text.replace(placeholder.getToSubstitute(), placeholder.getSubstitutor());
        }
        return text;
    }
}

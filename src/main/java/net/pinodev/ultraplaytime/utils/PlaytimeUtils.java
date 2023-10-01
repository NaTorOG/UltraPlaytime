package net.pinodev.ultraplaytime.utils;

import net.pinodev.ultraplaytime.configs.files.Locale;

public class PlaytimeUtils {

    public  String formatted(long playtimeInMilliseconds) {
        long playtimeInSeconds = playtimeInMilliseconds / 1000;

        long years = playtimeInSeconds / (60 * 60 * 24 * 365);
        playtimeInSeconds %= (60 * 60 * 24 * 365);

        long months = playtimeInSeconds / (60 * 60 * 24 * 30);
        playtimeInSeconds %= (60 * 60 * 24 * 30);

        long days = playtimeInSeconds / (60 * 60 * 24);
        playtimeInSeconds %= (60 * 60 * 24);

        long hours = playtimeInSeconds / (60 * 60);
        playtimeInSeconds %= (60 * 60);

        long minutes = playtimeInSeconds / 60;
        long seconds = playtimeInSeconds % 60;

        StringBuilder formattedTime = new StringBuilder();

        if (years > 1) {
            formattedTime.append(years).append(" ").append(Locale.TIME_YEARS.getString()).append(" ");
        }else if(years == 1){
            formattedTime.append(years).append(" ").append(Locale.TIME_YEAR.getString()).append(" ");
        }

        if (months > 1) {
            formattedTime.append(months).append(" ").append(Locale.TIME_MONTHS.getString()).append(" ");
        }else if(months == 1){
            formattedTime.append(months).append(" ").append(Locale.TIME_MONTH.getString()).append(" ");
        }

        if (days > 1) {
            formattedTime.append(days).append(" ").append(Locale.TIME_DAYS.getString()).append(" ");
        }else if(days == 1){
            formattedTime.append(days).append(" ").append(Locale.TIME_DAY.getString()).append(" ");
        }

        if (hours > 1) {
            formattedTime.append(hours).append(" ").append(Locale.TIME_HOURS.getString()).append(" ");
        }else if(hours == 1){
            formattedTime.append(hours).append(" ").append(Locale.TIME_HOUR.getString()).append(" ");
        }

        if (minutes > 1) {
            formattedTime.append(minutes).append(" ").append(Locale.TIME_MINUTES.getString()).append(" ");
        }else if(minutes == 1){
            formattedTime.append(minutes).append(" ").append(Locale.TIME_MINUTE.getString()).append(" ");
        }
        if (seconds > 1 || formattedTime.length() == 0) {
            formattedTime.append(seconds).append(" ").append(Locale.TIME_SECONDS.getString()).append(" ");
        }else if(seconds == 1){
            formattedTime.append(seconds).append(" ").append(Locale.TIME_SECOND.getString()).append(" ");
        }

        return formattedTime.toString().trim();
    }

}

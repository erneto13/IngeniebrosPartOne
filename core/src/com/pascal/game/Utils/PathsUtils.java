package com.pascal.game.Utils;

public class PathsUtils {
    public static String MAIN_FONT = "fonts/rocky-rockin.ttf";
    public static String SECOND_FONT = "fonts/headliner.ttf";
    public static String THIRD_FONT = "fonts/Bitfantasy.ttf";

    private final String filePath;

    PathsUtils(String filePath) {
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }
}

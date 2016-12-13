package com.iflytek.ossp.imeutils.novelspider.entity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/** 一些配置
 * Created by sypeng on 2016/12/8.
 */
public class Config {

    private static final Logger LOGGER = LoggerFactory.getLogger(Config.class);

    public static int BOOKLIST_NUMBER_MAXIMUN = 0;
    public static int BOOK_NUMBER_MAXIMUN = 0;

    public static boolean BOOK_STYLE_ENABLE = false;
    public static Map<String, Integer> BOOK_STYLE_NUMBER = new HashMap<>();

    public static boolean CHAPTERLIST_ISUSEVOLUME = true;

    public static String CRAWLSITE ;

    public static String STORAGE_DIR ;

    static {
        Properties properties = new Properties();
        InputStream in = Config.class.getClassLoader().getResourceAsStream("config.properties");
        try {
            properties.load(new InputStreamReader(in, Charset.forName("UTF-8")));
        } catch (IOException e) {
            LOGGER.error("加载配置错误");
        }

        try {
            BOOK_NUMBER_MAXIMUN = Integer.parseInt(properties.getProperty("book.number.maximum"));
            BOOKLIST_NUMBER_MAXIMUN = Integer.parseInt(properties.getProperty("bookList.number.maximum"));

            CHAPTERLIST_ISUSEVOLUME = Boolean.parseBoolean(properties.getProperty("book.chapterlist.isUseVolume"));

            CRAWLSITE = properties.getProperty("site.config.name", "");

            STORAGE_DIR = properties.getProperty("storage.dir", "");

            BOOK_STYLE_ENABLE = Boolean.parseBoolean(properties.getProperty("book.style.enable"));

            if(BOOK_STYLE_ENABLE) {
                String[] styles = properties.getProperty("book.style.number").split(";");
                for (String s : styles) {
                    String[] token = s.split(":");
                    BOOK_STYLE_NUMBER.put(token[0], Integer.parseInt(token[1]));
                }
            }

        }catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }
}

package models.converters;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class SubRipToMicroDVDFrameConverterTest {
    private SubRipToMicroDVDFrameConverter converter = new SubRipToMicroDVDFrameConverter();

    @Test
    public void canConvertFrame(){
        ArrayList<String> frames = new ArrayList<String>();
        ArrayList<String> converted = new ArrayList<String>();
        //given
        frames.add("1485\n" +
                "02:09:07,520 --> 02:09:08,851\n" +
                "Please pass me a glass of water.\n" +
                "\n");
        frames.add("1450\n" +
                "02:05:17,840 --> 02:05:19,046\n" +
                "- You crazy.\n" +
                "- Hands, quickly.\n" +
                "\n");
        frames.add("1\n" +
                "00:00:22,320 --> 00:00:23,924\n" +
                "Come in.\n" +
                "\n");
        frames.add("6\n" +
                "00:00:47,800 --> 00:00:50,770\n" +
                "Won in the FIFA lottery. More details in\n" +
                "the letter.\n" +
                "\n");
        //when
        converted.add(converter.convertFrame(frames.get(0),24,3300));
        converted.add(converter.convertFrame(frames.get(1),24,3200));
        converted.add(converter.convertFrame(frames.get(2),24,1));
        converted.add(converter.convertFrame(frames.get(3),24,27));
        //then
        assertEquals("{185940}{185972}Please pass me a glass of water.\n", converted.get(0));
        assertEquals("{180428}{180457}- You crazy.|- Hands, quickly.\n", converted.get(1));
        assertEquals("{535}{574}Come in.\n", converted.get(2));
        assertEquals("{1147}{1218}Won in the FIFA lottery. More details in|the letter.\n", converted.get(3));



    }
}
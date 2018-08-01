package models.converters;

import models.exceptions.BadFormatException;
import org.junit.Test;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class MicroDVDToSubRipFrameConverterTest {
    private MicroDVDToSubRipFrameConverter converter = new MicroDVDToSubRipFrameConverter();
    @Test
    public void canConvertFrames(){
        ArrayList<String> frames = new ArrayList<String>();
        ArrayList<String> converted = new ArrayList<String>();
        //given
        frames.add("{301}{420}I deliver perfection...|and don't brag about it! :D\n");
        frames.add("{701}{736}Sorry,|I forgot my key.\n");
        frames.add("{742}{805}Oh, thanks...\n");
        frames.add("{848}{900}Hello, Fawlty Towers.\n");
        //when
        converted.add(converter.convertFrame(frames.get(0),24,3300));
        converted.add(converter.convertFrame(frames.get(1),24,3200));
        converted.add(converter.convertFrame(frames.get(2),24,1));
        converted.add(converter.convertFrame(frames.get(3),24,27));
        //then
        assertEquals("1\n" +
                     "00:00:12,541 --> 00:00:17,500\n" +
                     "I deliver perfection...\n" +
                     "and don't brag about it! :D\n" +
                     "\n", converted.get(0));
        assertEquals("2\n" +
                     "00:00:29,208 --> 00:00:30,666\n" +
                     "Sorry,\n" +
                     "I forgot my key.\n" +
                     "\n", converted.get(1));
        assertEquals("3\n" +
                     "00:00:30,916 --> 00:00:33,541\n" +
                     "Oh, thanks...\n" +
                     "\n", converted.get(2));
        assertEquals("4\n" +
                     "00:00:35,333 --> 00:00:37,500\n" +
                     "Hello, Fawlty Towers.\n" +
                     "\n", converted.get(3));

    }

    @Test(expected = BadFormatException.class)
    public void shouldThrowBadFormattingExcWhenBadlyFormattedFrameGiven(){
        converter.convertFrame("{21}mistake{71}Hello there!.\n",24,1);
    }

}
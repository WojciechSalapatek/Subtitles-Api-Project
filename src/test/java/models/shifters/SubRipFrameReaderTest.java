package models.shifters;

import models.IO.SubRipFrameReader;
import models.IO.ToFileWriter;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class SubRipFrameReaderTest {

    private SubRipFrameReader reader = new SubRipFrameReader();
    private ToFileWriter writer = new ToFileWriter();

    @Rule
    public TemporaryFolder tmp = new TemporaryFolder();

    @Test
    public void canReadSingleFrame() throws IOException {
        //given
        File file = tmp.newFile("test.srt");
        writer.setOutputFile(file);
        writer.writeFrame("1554\n" +
                "02:16:21,520 --> 02:16:24,888\n" +
                "Darling, you'll always be my baby boy,\n" +
                "even when you're a Formula One champion.\n" +
                "\n");
        writer.close();
        reader.setInputFile(file);
        //when
        String readFrame = reader.readFrame();
        int startingLineNumber = reader.getLineNumber();

        //then
        assertEquals("1554\n" +
                "02:16:21,520 --> 02:16:24,888\n" +
                "Darling, you'll always be my baby boy,\n" +
                "even when you're a Formula One champion.\n" +
                "\n", readFrame);
        assertEquals(1, startingLineNumber);
        reader.close();
    }

    @Test
    public void canReadMultipleFrames() throws IOException {
        //given
        File file = tmp.newFile("test.srt");
        writer.setOutputFile(file);
        writer.writeFrame("1554\n" +
                "02:16:21,520 --> 02:16:24,888\n" +
                "Darling, you'll always be my baby boy,\n" +
                "even when you're a Formula One champion.\n" +
                "\n");

        writer.writeFrame("1555\n" +
                "02:16:25,320 --> 02:16:27,049\n" +
                "- I'll be the world champion.\n" +
                "- All right.\n" +
                "\n");
        writer.writeFrame("1556\n" +
                "02:16:35,640 --> 02:16:37,210\n" +
                "- Good morning.\n" +
                "- Hi, welcome.\n" +
                "\n");
        writer.close();
        reader.setInputFile(file);
        //when
        ArrayList<String> readFrames = new ArrayList<String>();
        ArrayList<Integer> readStartingLineNumbers = new ArrayList<Integer>();
        readFrames.add(reader.readFrame());
        readStartingLineNumbers.add(reader.getLineNumber());
        readFrames.add(reader.readFrame());
        readStartingLineNumbers.add(reader.getLineNumber());
        readFrames.add(reader.readFrame());
        readStartingLineNumbers.add(reader.getLineNumber());
        readFrames.add(reader.readFrame());
        //then
        assertEquals("1554\n" +
                "02:16:21,520 --> 02:16:24,888\n" +
                "Darling, you'll always be my baby boy,\n" +
                "even when you're a Formula One champion.\n" +
                "\n", readFrames.get(0));
        assertEquals("1555\n" +
                "02:16:25,320 --> 02:16:27,049\n" +
                "- I'll be the world champion.\n" +
                "- All right.\n" +
                "\n", readFrames.get(1));
        assertEquals("1556\n" +
                "02:16:35,640 --> 02:16:37,210\n" +
                "- Good morning.\n" +
                "- Hi, welcome.\n" +
                "\n", readFrames.get(2));
        assertEquals("null\n",readFrames.get(3));
        assertEquals(1, (int) readStartingLineNumbers.get(0));
        assertEquals(6, (int) readStartingLineNumbers.get(1));
        assertEquals(11, (int) readStartingLineNumbers.get(2));
        reader.close();
    }



}
package models.shifters;

import models.IO.MicroDVDFrameReader;
import models.IO.ToFileWriter;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class MicroDVDFrameReaderTest {

    private MicroDVDFrameReader reader;
    private ToFileWriter writer;

    @Rule
    public TemporaryFolder tmp = new TemporaryFolder();

    @Test
    public void canReadSingleFrame() throws IOException {
        //given
        File file = tmp.newFile("test.srt");
        writer = new ToFileWriter(file);
        writer.writeFrame("{19538}{19573}Ouch!|You're hurting me!\n");
        writer.close();
        reader = new MicroDVDFrameReader(file);
        //when
        String readFrame = reader.readFrame();
        int startingLineNumber = reader.getLineNumber();

        //then
        assertEquals("{19538}{19573}Ouch!|You're hurting me!\n", readFrame);
        assertEquals(1, startingLineNumber);
        reader.close();
    }

    @Test
    public void canReadMultipleFrames() throws IOException {
        //given
        File file = tmp.newFile("test.srt");
        writer = new ToFileWriter(file);
        writer.writeFrame("{19538}{19573}Ouch!|You're hurting me!\n");

        writer.writeFrame("{19579}{19602}What have you done|with my dining room door?\n");
        writer.writeFrame("{19608}{19643}- Where is it?|- I don't know.\n");
        writer.close();
        reader = new MicroDVDFrameReader(file);
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
        assertEquals("{19538}{19573}Ouch!|You're hurting me!\n", readFrames.get(0));
        assertEquals("{19579}{19602}What have you done|with my dining room door?\n", readFrames.get(1));
        assertEquals("{19608}{19643}- Where is it?|- I don't know.\n", readFrames.get(2));
        assertEquals("null\n",readFrames.get(3));

        assertEquals(1, (int) readStartingLineNumbers.get(0));
        assertEquals(2, (int) readStartingLineNumbers.get(1));
        assertEquals(3, (int) readStartingLineNumbers.get(2));
        reader.close();
    }

}
package models.shifters;

import models.exceptions.BadFormatException;
import models.exceptions.EndBeforeStartException;
import models.exceptions.NegativeFrameAfterShiftException;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class MicroDVDShiftProcessorTest {

    private MicroDVDShiftProcessor pr = new MicroDVDShiftProcessor();

    @Test
    public void shiftFrameCorrectCases() {
        //given
        ArrayList<String> expected = new ArrayList<String>();

        String frame1 = "{1063}{1126}No, it's 16 Elwood Avenue.\n";
        String frame2 = "{11263}{11324}- Seňor O'Reilly...|- Ah, when men come...?\n";

        expected.add("{1063}{1126}No, it's 16 Elwood Avenue.\n");
        expected.add("{1163}{1226}No, it's 16 Elwood Avenue.\n");
        expected.add("{763}{826}No, it's 16 Elwood Avenue.\n");
        expected.add("{563}{626}No, it's 16 Elwood Avenue.\n");
        expected.add("{12263}{12324}- Seňor O'Reilly...|- Ah, when men come...?\n");


        //when
        ArrayList<String> results = new ArrayList<String>();
        results.add(pr.shiftFrame(frame1,0,0));
        results.add(pr.shiftFrame(frame1,100,0));
        results.add(pr.shiftFrame(frame1,-300,0));
        results.add(pr.shiftFrame(frame1,-500,0));
        results.add(pr.shiftFrame(frame2,1000, 0));

        //then
        assertEquals(expected.get(0), results.get(0));
        assertEquals(expected.get(1), results.get(1));
        assertEquals(expected.get(2), results.get(2));
        assertEquals(expected.get(3), results.get(3));
        assertEquals(expected.get(4), results.get(4));
    }

    @Test(expected = NegativeFrameAfterShiftException.class)
    public void shouldThrowNegativeFrameExceptionWhenItHappens() {
        pr.shiftFrame("{21294}{21374}He made this mess,|he can clear it up.\n", -23*1000,0);

    }

    @Test(expected = EndBeforeStartException.class)
    public void shouldThrowEndBeforeStartExceptionWhenItHappens() {
        pr.shiftFrame("{21294}{21174}He made this mess,|he can clear it up.\n" ,
                250,0);
    }

    @Test(expected = BadFormatException.class)
    public void shouldThrowBadFormatExceptionWhenItHappens() {
        pr.shiftFrame("{21294}mistake!{21374}He made this mess,|he can clear it up.\n" ,
                250,0);
    }
}
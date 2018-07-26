package shifters;

import exceptions.BadFormatException;
import exceptions.EndBeforeStartException;
import exceptions.NegativeFrameAfterShiftException;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class SubRipShiftProcessorTest {

    private SubRipShiftProcessor pr = new SubRipShiftProcessor();

    @Test
    public void shiftFrameCorrectCases() {
        //given
        ArrayList<String> expected = new ArrayList<String>();

        String frame1 = "0\n" +
                "00:00:22,320 --> 00:00:23,924\n" +
                "Come in.\n" +
                "\n";
        String frame2 = "0\n" +
                "00:07:16,160 --> 00:07:17,844\n" +
                "You would have received\n" +
                "an award from us.\n" +
                "\n";

        expected.add("0\n" +
                "00:00:22,320 --> 00:00:23,924\n" +
                "Come in.\n" +
                "\n");
        expected.add("0\n" +
                "00:00:22,420 --> 00:00:24,024\n" +
                "Come in.\n" +
                "\n");
        expected.add("0\n" +
                "00:00:22,020 --> 00:00:23,624\n" +
                "Come in.\n" +
                "\n");
        expected.add("0\n" +
                "00:00:21,820 --> 00:00:23,424\n" +
                "Come in.\n" +
                "\n");
        expected.add("0\n" +
                "00:08:06,160 --> 00:08:07,844\n" +
                "You would have received\n" +
                "an award from us.\n" +
                "\n");

        //when
        ArrayList<String> results = new ArrayList<String>();
        results.add(pr.shiftFrame(frame1,0,0));
        results.add(pr.shiftFrame(frame1,100,0));
        results.add(pr.shiftFrame(frame1,-300,0));
        results.add(pr.shiftFrame(frame1,-500,0));
        results.add(pr.shiftFrame(frame2,50*1000, 0));

        //then
        assertEquals(expected.get(0), results.get(0));
        assertEquals(expected.get(1), results.get(1));
        assertEquals(expected.get(2), results.get(2));
        assertEquals(expected.get(3), results.get(3));
        assertEquals(expected.get(4), results.get(4));
    }

    @Test(expected = NegativeFrameAfterShiftException.class)
    public void shouldThrowNegativeFrameExceptionWhenItHappens() {
        pr.shiftFrame("0\n" +
                "00:00:22,320 --> 00:00:23,924\n" +
                "Come in.\n" +
                "\n", -23*1000,0);

    }

    @Test(expected = EndBeforeStartException.class)
    public void shouldThrowEndBeforeStartExceptionWhenItHappens() {
        pr.shiftFrame("0\n" +
                        "00:01:14,880 --> 00:01:12,086\n" +
                        "What, there's no hotel?\n" +
                        "\n" ,
                250,0);
    }

    @Test(expected = BadFormatException.class)
    public void shouldThrowBadFormatExceptionWhenItHappens() {
        pr.shiftFrame("0threissomemistake\n" +
                        "00:01:14,880 --> 00:01:12,086\n" +
                        "What, there's no hotel?\n" +
                        "\n" ,
                250,0);
    }


}
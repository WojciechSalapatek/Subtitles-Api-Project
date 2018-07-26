package models.shifters;

public class ShiftManagerSubRipTest {

//    private SubRipShiftProcessor pr = new SubRipShiftProcessor();
//    private SubRipShiftProcessor pr2 = new SubRipShiftProcessor();
//
//    @Test
//    public void matchesTest() {
//        //given
//        String frame1 = "0\n" +
//                "00:00:22,320 --> 00:00:23,924\n" +
//                "Come in.\n" +
//                "\n";
//        String frame2 = "770\n" +
//                "00:08:06,160 --> 00:08:07,844\n" +
//                "You would have received\n" +
//                "an award from us.\n" +
//                "\n";
//        pr.setFrame(frame1,1);
//        pr2.setFrame(frame2,1100);
//        //when
//        boolean result1 = pr.matches();
//        boolean result2 = pr2.matches();
//        //then
//        assertTrue(result1);
//        assertTrue(result2);
//    }
//
//    @Test
//    public void getStartTest() {
//        //given
//        String frame1 = "0\n" +
//                "00:00:22,320 --> 00:00:23,924\n" +
//                "Come in.\n" +
//                "\n";
//        String frame2 = "770\n" +
//                "00:08:06,160 --> 00:08:07,844\n" +
//                "You would have received\n" +
//                "an award from us.\n" +
//                "\n";
//        pr.setFrame(frame1,1);
//        pr2.setFrame(frame2,1100);
//        //when
//        boolean result1 = pr.matches();
//        boolean result2 = pr2.matches();
//        String start1 = pr.getStart();
//        String start2 = pr2.getStart();
//        //then
//        assertTrue(result1);
//        assertTrue(result2);
//        assertEquals("00:00:22,320",start1);
//        assertEquals("00:08:06,160",start2);
//    }
//
//    @Test
//    public void getEndTest() {
//        //given
//        String frame1 = "971\n" +
//                "01:22:55,600 --> 01:22:58,331\n" +
//                "Separate the equipment and transport it\n" +
//                "in different cars.\n" +
//                "\n";
//        String frame2 = "925\n" +
//                "01:20:15,040 --> 01:20:18,044\n" +
//                "Maybe you purchased 40 bags using loan\n" +
//                "money?\n" +
//                "\n";
//        pr.setFrame(frame1,1500);
//        pr2.setFrame(frame2, 1300);
//        //when
//        boolean result1 = pr.matches();
//        boolean result2 = pr2.matches();
//        String end1 = pr.getEnd();
//        String end2 = pr2.getEnd();
//        //then
//        assertTrue(result1);
//        assertTrue(result2);
//        assertEquals("01:22:58,331",end1);
//        assertEquals("01:20:18,044",end2);
//    }
//
//    @Test
//    public void getFrameNumberTest() {
//        //given
//        String frame1 = "971\n" +
//                "01:22:55,600 --> 01:22:58,331\n" +
//                "Separate the equipment and transport it\n" +
//                "in different cars.\n" +
//                "\n";
//        String frame2 = "925\n" +
//                "01:20:15,040 --> 01:20:18,044\n" +
//                "Maybe you purchased 40 bags using loan\n" +
//                "money?\n" +
//                "\n";
//        pr.setFrame(frame1,1500);
//        pr2.setFrame(frame2, 1300);
//        //when
//        boolean result1 = pr.matches();
//        boolean result2 = pr2.matches();
//        int number1 = pr.getFrameNumber();
//        int number2 = pr2.getFrameNumber();
//        //then
//        assertTrue(result1);
//        assertTrue(result2);
//        assertEquals(971,number1);
//        assertEquals(925,number2);
//    }
//
//    @Test
//    public void makeDelayedFrameTest(){
//        //given
//        String frame1 = "1018\n" +
//                "01:26:06,440 --> 01:26:07,805\n" +
//                "Please, please, the tablet.\n" +
//                "\n";
//        String frame2 = "1556\n" +
//                "02:16:35,640 --> 02:16:37,210\n" +
//                "- Good morning.\n" +
//                "- Hi, welcome.\n" +
//                "\n";
//        pr.setFrame(frame1,2000);
//        pr2.setFrame(frame2,2100);
//        //when
//        boolean result1 = pr.matches();
//        boolean result2 = pr2.matches();
//        String newStart1 = "01:26:07,440";
//        String newStart2 = "02:16:35,540";
//        String newEnd1 = "01:26:08,805";
//        String newEnd2 = "02:16:37,110";
//        //then
//        assertTrue(result1);
//        assertTrue(result2);
//        assertEquals("1018\n" +
//                "01:26:07,440 --> 01:26:08,805\n" +
//                "Please, please, the tablet.\n" +
//                "\n", pr.makeDelayedFrame(newStart1,newEnd1));
//        assertEquals("1556\n" +
//                "02:16:35,540 --> 02:16:37,110\n" +
//                "- Good morning.\n" +
//                "- Hi, welcome.\n" +
//                "\n", pr2.makeDelayedFrame(newStart2, newEnd2));
//    }
//
//    @Test
//    public void makeFrameTest(){
//        //given
//        int number1 = 1018;
//        int number2 = 1556;
//        String start1 = "01:26:07,440";
//        String start2 = "02:16:35,540";
//        String end1 = "01:26:08,805";
//        String end2 = "02:16:37,110";
//        String text1 = "Please, please, the tablet.\n";
//        String text2 = "- Good morning.\n" +
//                "- Hi, welcome.\n";
//        //when
//        String frame1 = pr.makeFrame(number1, start1, end1, text1);
//        String frame2 = pr.makeFrame(number2, start2, end2, text2);
//
//        //then
//        assertEquals("1018\n" +
//                "01:26:07,440 --> 01:26:08,805\n" +
//                "Please, please, the tablet.\n" +
//                "\n", frame1);
//        assertEquals("1556\n" +
//                "02:16:35,540 --> 02:16:37,110\n" +
//                "- Good morning.\n" +
//                "- Hi, welcome.\n" +
//                "\n", frame2);
//    }

}
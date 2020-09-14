package persistance;

import model.user.MyProfile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class WriterTest {
    private static final String TEST_FILE = "./data/myProfilesTest.txt";
    private Writer testWriter;
    private MyProfile testMyProfile;
    private List<Boolean> booleanListHolder = Arrays.asList(true, false, false, true ,true, true, false);

    @BeforeEach
    void runBefore() throws FileNotFoundException, UnsupportedEncodingException {

        testWriter = new Writer(new File(TEST_FILE));
        testMyProfile = new MyProfile();
        testMyProfile.setMaxTime(200);
        testMyProfile.setExperienceLevel("novel");
        testMyProfile.setMuscleFocus("chest");
        testMyProfile.setLiftingGoal("strength");
        testMyProfile.setDlPR(485);
        testMyProfile.setBenchPR(225);
        testMyProfile.setSquatPR(385);
        testMyProfile.setMyWeek(booleanListHolder);

    }

    @Test
    void testWriteMyProfile() {
        // save myProfile to file
        testWriter.write(testMyProfile);
        testWriter.close();

        // now read them back in and verify that the accounts have the expected values
        try {
            MyProfile myTestProfile = Reader.readAccounts(new File(TEST_FILE));
            assertEquals(200, myTestProfile.getMaxTime());
            assertEquals("novel", myTestProfile.getExperienceLevel());
            assertEquals("chest", myTestProfile.getMuscleFocus().get(0));
            assertEquals("strength", myTestProfile.getLiftingGoal());
            assertEquals(booleanListHolder, myTestProfile.getMyWeek());
            assertEquals(485, myTestProfile.getDlPR());
            assertEquals(225, myTestProfile.getBenchPR());
            assertEquals(385, myTestProfile.getSquatPR());

        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }
}

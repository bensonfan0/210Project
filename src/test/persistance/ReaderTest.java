package persistance;

import model.user.MyProfile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ReaderTest {

    @BeforeEach
    void createFiles() throws IOException {
        List<String> lines = new ArrayList<>();
        lines.add("200,novel,chest,strength,true,true,true,true,true,false,false,485,225,385");
        Files.write(Paths.get("./data/myProfilesReaderTest.txt"), lines);

        lines.clear();
        lines.add("90,intermediate,reardelt,sidedelt,abs,strength,true,false,false,true,true,true,false,405,135,315");
        Files.write(Paths.get("./data/myProfilesReaderTest1.txt"), lines);
    }

    @Test
    void testReaderMyProfileFileNovel() {
        try {
            List<Boolean> booleanListHolder = Arrays.asList(true, true, true, true ,true, false, false);
            MyProfile myTestProfile = Reader.readAccounts(new File("./data/myProfilesReaderTest.txt"));

//            200,novel,chest,strength,true,true,true,true,true,false,false,485,225,385

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

    @Test
    void testReaderMyProfileFileExperienced() {
        try {
            List<Boolean> booleanListHolder = Arrays.asList(true, false, false, true ,true, true, false);
            MyProfile myTestProfile = Reader.readAccounts(new File("./data/myProfilesReaderTest1.txt"));

//            90,intermediate,reardelt,sidedelt,abs,strength,true,false,false,true,true,true,false,405,135,315

            assertEquals(90, myTestProfile.getMaxTime());
            assertEquals("intermediate", myTestProfile.getExperienceLevel());
            assertEquals("reardelt", myTestProfile.getMuscleFocus().get(0));
            assertEquals("sidedelt", myTestProfile.getMuscleFocus().get(1));
            assertEquals("abs", myTestProfile.getMuscleFocus().get(2));
            assertEquals("strength", myTestProfile.getLiftingGoal());
            assertEquals(booleanListHolder, myTestProfile.getMyWeek());
            assertEquals(405, myTestProfile.getDlPR());
            assertEquals(135, myTestProfile.getBenchPR());
            assertEquals(315, myTestProfile.getSquatPR());
        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }

    @Test
    void testIOException() {
        try {
            Reader.readAccounts(new File("./path/does/not/exist/testAccount.txt"));
        } catch (IOException e) {
            // expected
        }
    }
}

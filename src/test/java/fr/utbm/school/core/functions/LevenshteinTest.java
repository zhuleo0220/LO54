package fr.utbm.school.core.functions;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
class LevenshteinTest {

    @Mock
    Levenshtein levenshtein;

    /**
     * Testing the calculation of levenshtein distance
     */
    @Test
    public void testLevenshteinDistance(){
        Assert.assertEquals(levenshtein.calculate("NICHE", "CHIENS"), 5);
    }

    /**
     * Testing the function to find the closest keyword in a list
     */
    @Test
    public void testClosestWord(){
        ArrayList<String> words = new ArrayList<>();
        words.add("Paris");
        words.add("Lyon");
        words.add("Belfort");
        words.add("Marseille");

        Assert.assertEquals(levenshtein.closestKeyword("Lion" ,words, 1, 5), "Lyon");
    }
}
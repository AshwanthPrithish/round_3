package helper;

import java.util.*;

public class Taxi
{
    public static int tId = 0;
    public char currentPos = 'A';
    public float earnings = 0;
    public int freeAfter = 0;
    public int id;

    public void updateDetails(char currentPos, float earnings, int freeAfter)
    {
        this.id = tId++;
        this.currentPos = currentPos;
        this.earnings = earnings;
        this.freeAfter = freeAfter;
    }
}

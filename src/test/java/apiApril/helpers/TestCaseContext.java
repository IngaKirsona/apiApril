package apiApril.helpers;

import apiApril.domain.Board;
import apiApril.domain.List;
import io.cucumber.java.Scenario;
import lombok.Getter;
import lombok.Setter;
//THIS IS THE PLACE WHERE WE CAN SAVE THINGS
public class TestCaseContext {
    @Setter @Getter
    private static Board board;
    //variable for deleting the list when it is created (in hooks):
    @Setter @Getter
    private static List list;
    @Setter @Getter
    private static Scenario scenario;

    // to clean the db each time the test is completed:
    //static - to be able accessible from anywhere not created the object
    public static void init(){
        board = null;
        list = null;
        scenario = null;
    }
}

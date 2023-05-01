package apiApril.stepdefinitions;

import apiApril.domain.Board;
import apiApril.domain.List;
import apiApril.helpers.TestCaseContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;

import static apiApril.clients.TrelloClient.getBoardInfo;
import static apiApril.clients.TrelloClient.createList;
import static apiApril.clients.TrelloClient.updateBoardInfo;
//to import everything from TrelloClient, but it is not suggested, longer leading time:import static apiApril.clients.TrelloClient.*;
import static apiApril.constants.ProjectConstants.BOARD_ID;
import static apiApril.constants.ProjectConstants.BOARD_NAME;

//HERE WE ARE CALLING THE METHODS FROM TrelloClient AND EXECUTING THEM!!!!!!!
public class TrelloSteps {
    //void= after the method is executed, it doesn't return anything
    //Classes must start with capital letter
    //Option + enter - choose create all definition, save in TrelloSteps
    //if more var are needed to be added:  @And("I check that board name was updated to {string} and the id is {string}")
    //Also here must be decond variable: public void iCheckThatBoardNameWasUpdatedTo(String title, String id)
    //They are read in the order from left to right
    //to launch the test, click
    @Given("The board exists and contains the correct information")
    public void getBoardDataAndCheckInfo(){
        //Data type, variable name = board info
        Response response = getBoardInfo(BOARD_ID);
        Board board = response.as(Board.class);
        //System.out.println(board.getId());
        //System.out.println(board.getName());
        //to check the if the board id and name is correct:
        Assertions.assertThat(board.getId())
                //will be printed out if something will go wrong
                .as("We assert that board id is correct")
                //to check if the board id is correct
                .isEqualTo(BOARD_ID);
        //to check the name
        Assertions.assertThat(board.getName())
                .as("We assert that board name is correct")
                .isEqualTo(BOARD_NAME);
    }

    @When("I change the board title to {string}")
    public void iChangeTheBoardTitleTo(String title) {
        //change the title to "New title"
        Response response = updateBoardInfo(title, BOARD_ID);
        Board board = response.as(Board.class);
        //to save data in TestCaseContext database
        TestCaseContext.setBoard(board);
    }

    @And("I check that board name was updated to {string}")
    public void iCheckThatBoardNameWasUpdatedTo(String title) {
        System.out.println("The 3rd step was executed");
        //asserting that the boards's name is correct
        Assertions.assertThat(TestCaseContext.getBoard().getName())
                .as("We check that the board's name was updated to " + title)
                .isEqualTo(title);
    }

    @Then("I add a list with a name {string} to the board")
    public void iAddAListWithANameToTheBoard(String listName) {
        //System.out.println("The 4th step was executed");
        Response response = createList(listName, TestCaseContext.getBoard().getId());
        List list = response.as(List.class);
        //to save the list to database, we can get the list id from here:
        TestCaseContext.setList(list);
        //assert that the name of the list is correct
        Assertions.assertThat(list.getName())
                .as("We check that the list was created with correct name")
                .isEqualTo(listName);

        //to show the id in test report:
        TestCaseContext.getScenario().log(list.getId());
    }
}

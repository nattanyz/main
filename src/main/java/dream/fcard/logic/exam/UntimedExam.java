package dream.fcard.logic.exam;

import java.util.ArrayList;

import dream.fcard.model.cards.FlashCard;
import dream.fcard.model.exceptions.IndexNotFoundException;

/**
 * Untimed Exam mode.
 */
public class UntimedExam implements Exam {

    private final ArrayList<FlashCard> testDeck;
    private Result result;
    private int index = 0;
    private ArrayList<FlashCard> initialDeck;
    private int durationInSeconds;

    public UntimedExam(ArrayList<FlashCard> deck, int durationInSeconds) {
        this.initialDeck = deck;
        this.testDeck = createTestDeck();
        this.result = new Result(testDeck.size());
        this.durationInSeconds = durationInSeconds;
    }

    @Override
    public FlashCard getCurrentCard() {
        return testDeck.get(index);
    }

    @Override
    public void gradeQuestion(Boolean isCorrect) throws IndexNotFoundException {
        if (isCorrect) {
            result.mark(true);
        }
    }

    @Override
    public void upIndex() {
        if (index < testDeck.size()) {
            this.index++;
        }
    }

    @Override
    public void downIndex() {
        if (this.index != 0) {
            this.index--;
        }
    }

    @Override
    public String getResult() {
        return this.result.getScore();
    }

    @Override
    public ArrayList<FlashCard> getDeck() {
        return this.testDeck;
    }

    @Override
    public int getIndex() {
        return index;
    }

    /**
     * Duplicates the test deck so that stats class can use it (for Nat).
     * @return ArrayList of Flashcards from the initial deck.
     */
    private ArrayList<FlashCard> createTestDeck() {
        ArrayList<FlashCard> testDeckConsumer = new ArrayList<>();
        for (FlashCard card : initialDeck) {
            FlashCard duplicateCard = card.duplicate();
            testDeckConsumer.add(duplicateCard);
        }
        return testDeckConsumer;
    }

    @Override
    public int getDuration() {
        return this.durationInSeconds;
    }
}
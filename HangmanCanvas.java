/*
 * File: HangmanCanvas.java
 * ------------------------
 * This file keeps track of the Hangman display.
 */

import acm.graphics.*;


public class HangmanCanvas extends GCanvas {

	/** Resets the display so that only the scaffold appears */
	public void reset() {

		correctWordGuess = new GLabel("");
		correctWordGuess.setFont("sansarif-30");
		correctWordGuess.setLocation(50, 450);
		add (correctWordGuess);

		wrongLetterGuess = new GLabel("");
		wrongLetterGuess.setFont("sansarif-20");
		wrongLetterGuess.setLocation(50, 500);
		add (wrongLetterGuess);

		GLabel life = new GLabel("");
		life.setFont("sansarif-20");
		life.setLocation(290, 20);
		add(life);
		life.setLabel("Live = " + lives);
		
		GLine scaffold = new GLine(50, 50, 50, SCAFFOLD_HEIGHT + 50);
		GLine beam = new GLine(50, 50, (BEAM_LENGTH + 50), 50);
		GLine rope = new GLine((BEAM_LENGTH + 50), 50, (BEAM_LENGTH + 50), (ROPE_LENGTH + 50));
		add(scaffold);
		add(beam);
		add(rope);

	}

	/**
	 * Updates the word on the screen to correspond to the current
	 * state of the game.  The argument string shows what letters have
	 * been guessed so far; unguessed letters are indicated by hyphens.
	 */
	public void displayWord(String word) {

		correctWordGuess.setLabel(word);

	}

	/**
	 * Updates the display to correspond to an incorrect guess by the
	 * user.  Calling this method causes the next body part to appear
	 * on the scaffold and adds the letter to the list of incorrect
	 * guesses that appears at the bottom of the window.
	 */
	public void noteIncorrectGuess(String letter, int turn) {

		wrongLetterGuess.setLabel(letter);
		if (turn == 7) {
			GOval head = new GOval((BEAM_LENGTH + xIntercept), (ROPE_LENGTH + 50), HEAD_RADIUS, HEAD_RADIUS);
			add(head);
		}
		else if (turn == 6) {

			GLine body = new GLine((BEAM_LENGTH + xIntercept + HEAD_RADIUS/2), 
					(ROPE_LENGTH + HEAD_RADIUS + yIntercept), 
					(BEAM_LENGTH + xIntercept + HEAD_RADIUS/2), 
					(ROPE_LENGTH + HEAD_RADIUS + yIntercept + BODY_LENGTH));
			GLine arm = new GLine((BEAM_LENGTH + xIntercept + HEAD_RADIUS/2 + UPPER_ARM_LENGTH/2 + 10), 
					(ROPE_LENGTH + HEAD_RADIUS + yIntercept + ARM_OFFSET_FROM_HEAD), 
					((BEAM_LENGTH + xIntercept + HEAD_RADIUS/2 + -UPPER_ARM_LENGTH/2 - 10)),
					(ROPE_LENGTH + HEAD_RADIUS + yIntercept + ARM_OFFSET_FROM_HEAD));
			GLine hip = new GLine((BEAM_LENGTH + xIntercept + HEAD_RADIUS/2 + HIP_WIDTH/2 + 10), 
					(ROPE_LENGTH + HEAD_RADIUS + yIntercept + BODY_LENGTH), 
					((BEAM_LENGTH + xIntercept + HEAD_RADIUS/2 + -HIP_WIDTH/2 - 10)),
					(ROPE_LENGTH + HEAD_RADIUS + yIntercept + BODY_LENGTH));
			add(body);
			add(arm);
			add(hip);
		}

		else if (turn == 5) {

			GLine rightArm = new GLine((BEAM_LENGTH + xIntercept + HEAD_RADIUS/2 + UPPER_ARM_LENGTH/2 + 10),
					(ROPE_LENGTH + HEAD_RADIUS + yIntercept + ARM_OFFSET_FROM_HEAD),
					(BEAM_LENGTH + xIntercept + HEAD_RADIUS/2 + UPPER_ARM_LENGTH/2 + 10),
					(ROPE_LENGTH + HEAD_RADIUS + yIntercept + ARM_OFFSET_FROM_HEAD + LOWER_ARM_LENGTH));
			add(rightArm);
		}
		else if (turn == 4) {

			GLine leftArm = new GLine((BEAM_LENGTH + xIntercept + HEAD_RADIUS/2 + -UPPER_ARM_LENGTH/2 - 10),
					(ROPE_LENGTH + HEAD_RADIUS + yIntercept + ARM_OFFSET_FROM_HEAD),
					(BEAM_LENGTH + xIntercept + HEAD_RADIUS/2 + -UPPER_ARM_LENGTH/2 - 10),
					(ROPE_LENGTH + HEAD_RADIUS + yIntercept + ARM_OFFSET_FROM_HEAD + LOWER_ARM_LENGTH));
			add(leftArm);

		}
		else if (turn == 3) {

			GLine rightLeg = new GLine((BEAM_LENGTH + xIntercept + HEAD_RADIUS/2 + HIP_WIDTH/2 + 10),
					(ROPE_LENGTH + HEAD_RADIUS + yIntercept + BODY_LENGTH),
					(BEAM_LENGTH + xIntercept + HEAD_RADIUS/2 + HIP_WIDTH/2 + 10),
					(ROPE_LENGTH + HEAD_RADIUS + yIntercept + BODY_LENGTH + LEG_LENGTH));
			add(rightLeg);
		}
		else if (turn == 2) {

			GLine leftLeg = new GLine((BEAM_LENGTH + xIntercept + HEAD_RADIUS/2 + -HIP_WIDTH/2 - 10),
					(ROPE_LENGTH + HEAD_RADIUS + yIntercept + BODY_LENGTH),
					(BEAM_LENGTH + xIntercept + HEAD_RADIUS/2 + -HIP_WIDTH/2 - 10),
					(ROPE_LENGTH + HEAD_RADIUS + yIntercept + BODY_LENGTH + LEG_LENGTH));
			add(leftLeg);
		}
		else if (turn == 1) {

			GLine rightFoot = new GLine((BEAM_LENGTH + xIntercept + HEAD_RADIUS/2 + HIP_WIDTH/2 + 10),
					(ROPE_LENGTH + HEAD_RADIUS + yIntercept + BODY_LENGTH + LEG_LENGTH),
					(BEAM_LENGTH + xIntercept + HEAD_RADIUS/2 + HIP_WIDTH/2 + 10 + FOOT_LENGTH),
					(ROPE_LENGTH + HEAD_RADIUS + yIntercept + BODY_LENGTH + LEG_LENGTH));
			add(rightFoot);
		}
		else if (turn == 0) {

			GLine leftFoot = new GLine((BEAM_LENGTH + xIntercept + HEAD_RADIUS/2 + -HIP_WIDTH/2 - 10),
					(ROPE_LENGTH + HEAD_RADIUS + yIntercept + BODY_LENGTH + LEG_LENGTH),
					(BEAM_LENGTH + xIntercept + HEAD_RADIUS/2 + -HIP_WIDTH/2 - 10 - FOOT_LENGTH),
					(ROPE_LENGTH + HEAD_RADIUS + yIntercept + BODY_LENGTH + LEG_LENGTH));
			add(leftFoot);
			lives--;

		}
	}

	/* Constants for the simple version of the picture (in pixels) */
	private static final int SCAFFOLD_HEIGHT = 360;
	private static final int BEAM_LENGTH = 144;
	private static final int ROPE_LENGTH = 18;
	private static final int HEAD_RADIUS = 50;
	private static final int BODY_LENGTH = 144;
	private static final int ARM_OFFSET_FROM_HEAD = 28;
	private static final int UPPER_ARM_LENGTH = 72;
	private static final int LOWER_ARM_LENGTH = 44;
	private static final int HIP_WIDTH = 36;
	private static final int LEG_LENGTH = 108;
	private static final int FOOT_LENGTH = 28;
	private static final int yIntercept = 52;
	private static final int xIntercept = 26;



	/* Private instance variable */
	private GLabel correctWordGuess, wrongLetterGuess;
	private int lives = 3;





}


import acm.graphics.*;


public class HangmanDrawing {
	private GCanvas canvas;
	GLabel hiddenWordLabel, guessesList, displayPoints, Lifeline1, Lifeline2,yes,no,response;

	private static final int SCAFFOLD_HEIGHT = 360;
	private static final int BEAM_LENGTH = 144;
	private static final int ROPE_LENGTH = 18;
	private static final int HEAD_RADIUS = 36;
	private static final int BODY_LENGTH = 144;
	private static final int ARM_OFFSET_FROM_HEAD = 28;
	private static final int UPPER_ARM_LENGTH = 72;
	private static final int LOWER_ARM_LENGTH = 44;
	private static final int HIP_WIDTH = 36;
	private static final int LEG_LENGTH = 108;
	private static final int FOOT_LENGTH = 28;

	public HangmanDrawing() {
		canvas = new GCanvas();
	}

	public GCanvas getCanvas() {
		return canvas;
	}

	public void resetCanvas() {
		canvas.removeAll();
	}
	/*
	 * Thread.sleep was because sometimes the three components and labels were not
	 * showing up, or some of them were pushed to the left Maybe it was because the
	 * program wasn't running it properly, even though i put it in the init method
	 */

	public void drawScaffold() {
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		double startScaffoldX = canvas.getWidth() / 2 - BEAM_LENGTH;
		canvas.add(new GLine(startScaffoldX, 50, startScaffoldX, 50 + SCAFFOLD_HEIGHT));

	}

	public void drawBeam() {
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		double startBeamX = canvas.getWidth() / 2 - BEAM_LENGTH;
		canvas.add(new GLine(startBeamX, 50, canvas.getWidth() / 2, 50));

	}

	public void drawRope() {

		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		canvas.add(new GLine(canvas.getWidth() / 2, 50, canvas.getWidth() / 2, 50 + ROPE_LENGTH));

	}

	public void drawHead() {
		canvas.add(new GOval(canvas.getWidth() / 2 - HEAD_RADIUS, 50 + ROPE_LENGTH, HEAD_RADIUS * 2, HEAD_RADIUS * 2));

	}

	public void drawBody() {
		double startBodyX = 50 + ROPE_LENGTH + HEAD_RADIUS * 2;
		canvas.add(new GLine(canvas.getWidth() / 2, startBodyX, canvas.getWidth() / 2, startBodyX + BODY_LENGTH));

	}

	public void drawLeftArm1() {
		double startArmY = 50 + ROPE_LENGTH + HEAD_RADIUS * 2 + ARM_OFFSET_FROM_HEAD;
		canvas.add(new GLine(canvas.getWidth() / 2, startArmY, canvas.getWidth() / 2 - UPPER_ARM_LENGTH, startArmY));

	}

	public void drawLeftArm2() {
		double startX = canvas.getWidth() / 2 - UPPER_ARM_LENGTH;
		double startArmY = 50 + ROPE_LENGTH + HEAD_RADIUS * 2 + ARM_OFFSET_FROM_HEAD;
		canvas.add(new GLine(startX, startArmY, startX, startArmY + LOWER_ARM_LENGTH));
	}

	public void drawRightArm1() {
		double startArmY = 50 + ROPE_LENGTH + HEAD_RADIUS * 2 + ARM_OFFSET_FROM_HEAD;
		canvas.add(new GLine(canvas.getWidth() / 2, startArmY, canvas.getWidth() / 2 + UPPER_ARM_LENGTH, startArmY));

	}

	public void drawRightArm2() {
		double startX = canvas.getWidth() / 2 + UPPER_ARM_LENGTH;
		double startArmY = 50 + ROPE_LENGTH + HEAD_RADIUS * 2 + ARM_OFFSET_FROM_HEAD;
		canvas.add(new GLine(startX, startArmY, startX, startArmY + LOWER_ARM_LENGTH));

	}

	public void drawLeftLeg() {
		double startHipY = 50 + ROPE_LENGTH + HEAD_RADIUS * 2 + BODY_LENGTH;
		double startLeftLegX = canvas.getWidth() / 2 - HIP_WIDTH;
		canvas.add(new GLine(canvas.getWidth() / 2, startHipY, startLeftLegX, startHipY));
		canvas.add(new GLine(startLeftLegX, startHipY, startLeftLegX, startHipY + LEG_LENGTH));
	}

	public void drawRightLeg() {
		double startHipY = 50 + ROPE_LENGTH + HEAD_RADIUS * 2 + BODY_LENGTH;
		double startRightLegX = canvas.getWidth() / 2 + HIP_WIDTH;
		canvas.add(new GLine(canvas.getWidth() / 2, startHipY, startRightLegX, startHipY));
		canvas.add(new GLine(startRightLegX, startHipY, startRightLegX, startHipY + LEG_LENGTH));
	}

	public void drawLeftFoot() {
		double startLeftFootX = canvas.getWidth() / 2 - HIP_WIDTH;
		double startFootY = 50 + ROPE_LENGTH + HEAD_RADIUS * 2 + BODY_LENGTH + LEG_LENGTH;
		canvas.add(new GLine(startLeftFootX, startFootY, startLeftFootX - FOOT_LENGTH, startFootY));
	}

	public void drawRightFoot() {
		double startRightFootX = canvas.getWidth() / 2 + HIP_WIDTH;
		double startFootY = 50 + ROPE_LENGTH + HEAD_RADIUS * 2 + BODY_LENGTH + LEG_LENGTH;
		canvas.add(new GLine(startRightFootX, startFootY, startRightFootX + FOOT_LENGTH, startFootY));

	}

	public void addGuessesOnCanvas(String wrongGuessesList) {
		guessesList = new GLabel(wrongGuessesList);
		guessesList.setFont("Garamond-12");
		canvas.add(guessesList, canvas.getWidth() / 2 - hiddenWordLabel.getWidth() / 2, 420);
	}

	public void addHiddenWordOnCanvas(String hiddenWord) {
		hiddenWordLabel = new GLabel(hiddenWord);
		hiddenWordLabel.setFont("Garamond-24");
		canvas.add(hiddenWordLabel, canvas.getWidth() / 2 - hiddenWordLabel.getWidth() / 2, 400);
	}

	public void updateGuessesOnCanvas(String wrongGuessesList) {
		guessesList.setLabel(wrongGuessesList);
	}

	public void updateHiddenWordOnCanvas(String hiddenWord) {
		hiddenWordLabel.setLabel(hiddenWord);
	}

	public void winningImage() {
		GImage puppy = new GImage("puppy.png");
		canvas.add(puppy, canvas.getWidth() / 2 - puppy.getWidth() / 2, canvas.getHeight() / 2);
	}

	public void displayPoints(int points) {
		displayPoints = new GLabel("Points: " + points);
		canvas.add(displayPoints, 10, 20);
	}

	public void updatePoints(int points) {
		displayPoints.setLabel("Points: " + points);
	}

	public void displayLifeline1() {
		Lifeline1 = new GLabel("80 Points- Get Letter");
		canvas.add(Lifeline1, 10, 30);
	}

	public void displayLifeline2() {
		Lifeline2 = new GLabel("50 Points- Life");
		canvas.add(Lifeline2, 10, 40);

	}

	public void displayHappyPuppy() {
		GImage win = new GImage("happypuppy.png");
		canvas.add(win);
	}

	public void displaySadPuppy() {
		GImage lose = new GImage("sadpuppy3.png");
		canvas.add(lose, 0, 0);
	}
	
	public void yesLabel() {
		yes = new GLabel("YES", canvas.getWidth() / 2 -60, 450);
		yes.setFont("Garamond-24");
		no = new GLabel("NO :,(",canvas.getWidth() / 2 + 30, 450);
		no.setFont("Garamond-24");
		response = new GLabel("<------  Answer here!", 10, 480);
		response.setFont("Garamond-24");
		canvas.add(yes);
		canvas.add(no);
		canvas.add(response);
	}

}

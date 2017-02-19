package com.snakegame.control;

import org.assertj.swing.annotation.GUITest;
import org.assertj.swing.core.EmergencyAbortListener;
import org.assertj.swing.core.MouseButton;
import org.assertj.swing.core.matcher.JButtonMatcher;
import org.assertj.swing.core.matcher.JLabelMatcher;
import org.assertj.swing.finder.WindowFinder;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.launcher.ApplicationLauncher;
import org.assertj.swing.testng.testcase.AssertJSwingTestngTestCase;
import org.testng.annotations.Test;

import com.snakegame.view.Board;
import com.snakegame.view.MainWindow;

public class GameRunnerTest extends AssertJSwingTestngTestCase {
    private EmergencyAbortListener listener;

    @Override
    public void onSetUp() {
	listener = EmergencyAbortListener.registerInToolkit();
    }

    @Test(groups = "gui")
    @GUITest
    public void test() throws InterruptedException {
	ApplicationLauncher.application(GameRunner.class).start();
	FrameFixture mainFrame = WindowFinder.findFrame(MainWindow.GAME_WINDOW_TITLE).withTimeout(5 * 1000)
	        .using(robot());
	mainFrame.button(MainWindow.START_BUTTON_NAME).requireEnabled().requireVisible();
	mainFrame.label(MainWindow.BACKGROUND_PANEL_NAME).requireEnabled().requireVisible();
	mainFrame.button(MainWindow.START_BUTTON_NAME).click(MouseButton.LEFT_BUTTON);
	mainFrame.button(JButtonMatcher.withName(MainWindow.START_BUTTON_NAME)).requireNotVisible();
	mainFrame.label(JLabelMatcher.withName(MainWindow.BACKGROUND_PANEL_NAME)).requireNotVisible();
	mainFrame.panel(Board.BOARD_NAME).requireEnabled().requireVisible();
    }

    @Override
    public void onTearDown() {
	listener.unregister();
    }

}

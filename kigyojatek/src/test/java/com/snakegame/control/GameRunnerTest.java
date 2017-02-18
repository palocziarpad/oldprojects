package com.snakegame.control;

import static org.assertj.swing.finder.WindowFinder.findFrame;
import static org.assertj.swing.launcher.ApplicationLauncher.application;

import org.assertj.swing.core.EmergencyAbortListener;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.testng.testcase.AssertJSwingTestngTestCase;
import org.testng.annotations.Test;

import com.snakegame.view.MainWindow;

public class GameRunnerTest extends AssertJSwingTestngTestCase {
    private EmergencyAbortListener listener;

    @Override
    public void onSetUp() {
	listener = EmergencyAbortListener.registerInToolkit();
    }

    @Test(groups = "gui")
    public void test() throws InterruptedException {
	application(GameRunner.class).start();
	FrameFixture mainFrame = findFrame(MainWindow.GAME_WINDOW_TITLE).withTimeout(5 * 1000).using(robot());
	mainFrame.button("Start").requireEnabled().requireVisible();
	// mainFrame.button("Start").click(MouseButton.LEFT_BUTTON);
    }

    @Override
    public void onTearDown() {
	listener.unregister();
    }

}

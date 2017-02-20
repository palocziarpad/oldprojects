package com.snakegame.control;

import java.awt.event.KeyEvent;

import javax.swing.JLabel;

import org.assertj.swing.annotation.GUITest;
import org.assertj.swing.core.EmergencyAbortListener;
import org.assertj.swing.core.KeyPressInfo;
import org.assertj.swing.core.MouseButton;
import org.assertj.swing.core.matcher.JButtonMatcher;
import org.assertj.swing.core.matcher.JLabelMatcher;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.finder.WindowFinder;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.launcher.ApplicationLauncher;
import org.assertj.swing.testng.testcase.AssertJSwingTestngTestCase;
import org.assertj.swing.timing.Condition;
import org.assertj.swing.timing.Pause;
import org.assertj.swing.timing.Timeout;
import org.testng.annotations.Test;

import com.snakegame.view.Board;
import com.snakegame.view.MainWindow;

public class GameRunnerTest extends AssertJSwingTestngTestCase {
    private EmergencyAbortListener listener;

    @Override
    public void onSetUp() {
        listener = EmergencyAbortListener.registerInToolkit();
    }

    @Test(groups = { "gui", "function" })
    @GUITest
    public void testGameStartEndWithWallHit() throws InterruptedException {
        ApplicationLauncher.application(GameRunner.class).start();
        FrameFixture mainFrame = WindowFinder.findFrame(MainWindow.GAME_WINDOW_TITLE).withTimeout(5 * 1000)
                .using(robot());
        JLabel backGroundPanel = mainFrame.label(MainWindow.BACKGROUND_PANEL_NAME).target();
        mainFrame.button(MainWindow.START_BUTTON_NAME).requireEnabled().requireVisible();
        mainFrame.label(MainWindow.BACKGROUND_PANEL_NAME).requireEnabled().requireVisible();
        mainFrame.button(MainWindow.START_BUTTON_NAME).click(MouseButton.LEFT_BUTTON);
        mainFrame.button(JButtonMatcher.withName(MainWindow.START_BUTTON_NAME)).requireNotVisible();
        mainFrame.label(JLabelMatcher.withName(MainWindow.BACKGROUND_PANEL_NAME)).requireNotVisible();
        mainFrame.panel(Board.BOARD_NAME).requireEnabled().requireVisible();
        mainFrame.pressAndReleaseKey(KeyPressInfo.keyCode(KeyEvent.VK_W));

        Pause.pause(new Condition(MainWindow.BACKGROUND_PANEL_NAME + " to be enabled") {

            public boolean test() {
                return GuiActionRunner.execute(backGroundPanel::isVisible);
            }
        }, Timeout.timeout(10000));

        // Thread.sleep(4 * 1000);
        // mainFrame.label(MainWindow.BACKGROUND_PANEL_NAME).requireEnabled(Timeout.timeout(10
        // * 1000));
        /*
         * GenericTypeMatcher<JLabel> textMatcher = new
         * GenericTypeMatcher<JLabel>(JLabel.class) {
         * 
         * @Override protected boolean isMatching(JLabel label) {
         * 
         * return MainWindow.BACKGROUND_PANEL_NAME.equals(label.getName()) &&
         * label.isVisible(); } };
         * 
         * JLabelFinder.findJLabel(textMatcher).withTimeout(100000).using(robot(
         * )).requireEnabled(10000);
         */
        // JLabelFixture fixture = new JLabelFixture(robot(),
        // MainWindow.BACKGROUND_PANEL_NAME);
        // System.out.println(fixture.isEnabled());

    }

    @Override
    public void onTearDown() {
        listener.unregister();
    }

}

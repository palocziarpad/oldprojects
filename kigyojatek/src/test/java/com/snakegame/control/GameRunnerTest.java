package com.snakegame.control;

import java.awt.event.KeyEvent;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

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
import org.assertj.swing.image.ScreenshotTaker;
import org.assertj.swing.launcher.ApplicationLauncher;
import org.assertj.swing.testng.testcase.AssertJSwingTestngTestCase;
import org.assertj.swing.timing.Condition;
import org.assertj.swing.timing.Pause;
import org.assertj.swing.timing.Timeout;
import org.testng.annotations.Test;

import com.snakegame.Util;
import com.snakegame.view.Board;
import com.snakegame.view.MainWindow;

public class GameRunnerTest extends AssertJSwingTestngTestCase {
    private static final String GUI_SCREENSHOT_OUTPUT_DIR = "target/test-output/";
    private EmergencyAbortListener listener;

    @Override
    public void onSetUp() {
        File testoutput = new File(GUI_SCREENSHOT_OUTPUT_DIR);
        if (!testoutput.exists()) {
            testoutput.mkdir();
        }
        listener = EmergencyAbortListener.registerInToolkit();

    }

    @Test(groups = { "gui", "function" })
    @GUITest
    public void testGameStartEndWithWallHit() throws InterruptedException {
        ScreenshotTaker screenshot = new ScreenshotTaker();
        DateFormat df = new SimpleDateFormat("yy_MM_dd_HH_mm_ss");
        ApplicationLauncher.application(GameRunner.class).start();
        FrameFixture mainFrame = WindowFinder.findFrame(MainWindow.GAME_WINDOW_TITLE).withTimeout(5 * 1000)
                .using(robot());
        screenshot.saveImage(screenshot.takeDesktopScreenshot(),
                GUI_SCREENSHOT_OUTPUT_DIR + "testGameStartEndWithWallHit_screen" + df.format(new Date()) + ".png");
        JLabel backGroundPanel = mainFrame.label(MainWindow.BACKGROUND_PANEL_NAME).target();
        mainFrame.button(MainWindow.START_BUTTON_NAME).requireEnabled().requireVisible();
        mainFrame.label(MainWindow.BACKGROUND_PANEL_NAME).requireEnabled().requireVisible();
        mainFrame.button(MainWindow.START_BUTTON_NAME).click(MouseButton.LEFT_BUTTON);
        Util.sleep(1000);
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
        ScreenshotTaker screenshot = new ScreenshotTaker();
        DateFormat df = new SimpleDateFormat("yy_MM_dd_HH_mm_ss");
        screenshot.saveImage(screenshot.takeDesktopScreenshot(),
                GUI_SCREENSHOT_OUTPUT_DIR + "onTearDown_screen" + df.format(new Date()) + ".png");
        listener.unregister();
    }

}

package com.task.tracker;

import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.view.ActionMode;
import android.view.KeyEvent;
import android.view.KeyboardShortcutGroup;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SearchEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityEvent;

import java.util.List;

class TouchEventTracker implements Window.Callback
{
    private final Logger logger;
    private Window.Callback androidCallback;

    TouchEventTracker(Logger logger)
    {
        this.logger = logger;
    }

    void setAndroidCallback(Window.Callback androidCallback)
    {
        this.androidCallback = androidCallback;
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent keyEvent)
    {
        return androidCallback.dispatchKeyEvent(keyEvent);
    }

    @Override
    public boolean dispatchKeyShortcutEvent(KeyEvent keyEvent)
    {
        return androidCallback.dispatchKeyShortcutEvent(keyEvent);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent motionEvent)
    {
        logger.print("dispatchTouchEvent: " + motionEvent);
        return androidCallback.dispatchTouchEvent(motionEvent);
    }

    @Override
    public boolean dispatchTrackballEvent(MotionEvent motionEvent)
    {
        return androidCallback.dispatchTrackballEvent(motionEvent);
    }

    @Override
    public boolean dispatchGenericMotionEvent(MotionEvent motionEvent)
    {
        return androidCallback.dispatchGenericMotionEvent(motionEvent);
    }

    @Override
    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent)
    {
        return androidCallback.dispatchPopulateAccessibilityEvent(accessibilityEvent);
    }

    @Nullable
    @Override
    public View onCreatePanelView(int i)
    {
        return androidCallback.onCreatePanelView(i);
    }

    @Override
    public boolean onCreatePanelMenu(int i, Menu menu)
    {
        return androidCallback.onCreatePanelMenu(i, menu);
    }

    @Override
    public boolean onPreparePanel(int i, View view, Menu menu)
    {
        return androidCallback.onPreparePanel(i, view, menu);
    }

    @Override
    public boolean onMenuOpened(int i, Menu menu)
    {
        return androidCallback.onMenuOpened(i, menu);
    }

    @Override
    public boolean onMenuItemSelected(int i, MenuItem menuItem)
    {
        return androidCallback.onMenuItemSelected(i, menuItem);
    }

    @Override
    public void onWindowAttributesChanged(WindowManager.LayoutParams layoutParams)
    {
        androidCallback.onWindowAttributesChanged(layoutParams);
    }

    @Override
    public void onContentChanged()
    {
        androidCallback.onContentChanged();
    }

    @Override
    public void onWindowFocusChanged(boolean b)
    {
        androidCallback.onWindowFocusChanged(b);
    }

    @Override
    public void onAttachedToWindow()
    {
        androidCallback.onAttachedToWindow();
    }

    @Override
    public void onDetachedFromWindow()
    {
        androidCallback.onDetachedFromWindow();
    }

    @Override
    public void onPanelClosed(int i, Menu menu)
    {
        androidCallback.onPanelClosed(i, menu);
    }

    @Override
    public boolean onSearchRequested()
    {
        return androidCallback.onSearchRequested();
    }

    @Override
    @RequiresApi(api = Build.VERSION_CODES.M)
    public boolean onSearchRequested(SearchEvent searchEvent)
    {
        return androidCallback.onSearchRequested(searchEvent);
    }

    @Nullable
    @Override
    public ActionMode onWindowStartingActionMode(ActionMode.Callback callback)
    {
        return androidCallback.onWindowStartingActionMode(callback);
    }

    @Nullable
    @Override
    @RequiresApi(api = Build.VERSION_CODES.M)
    public ActionMode onWindowStartingActionMode(ActionMode.Callback callback, int i)
    {
        return androidCallback.onWindowStartingActionMode(callback, i);
    }

    @Override
    public void onActionModeStarted(ActionMode actionMode)
    {
        androidCallback.onActionModeStarted(actionMode);
    }

    @Override
    public void onActionModeFinished(ActionMode actionMode)
    {
        androidCallback.onActionModeFinished(actionMode);
    }

    @Override
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void onProvideKeyboardShortcuts(List<KeyboardShortcutGroup> data, @Nullable Menu menu, int deviceId)
    {
        androidCallback.onProvideKeyboardShortcuts(data, menu, deviceId);
    }

    @Override
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void onPointerCaptureChanged(boolean hasCapture)
    {
        androidCallback.onPointerCaptureChanged(hasCapture);
    }
}

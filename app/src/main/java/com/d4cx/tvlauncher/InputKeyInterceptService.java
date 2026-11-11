package com.d4cx.tvlauncher;
import android.accessibilityservice.AccessibilityService;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.accessibility.AccessibilityEvent;
public class InputKeyInterceptService extends AccessibilityService {
 private void open(){startActivity(new Intent(this,InputSelectorActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_SINGLE_TOP));}
 public boolean onKeyEvent(KeyEvent e){if(e.getKeyCode()!=178 && e.getScanCode()!=167)return false;if(e.getAction()==KeyEvent.ACTION_DOWN && e.getRepeatCount()==0)open();return true;}
 public void onAccessibilityEvent(AccessibilityEvent e){if("com.mitv.livetv".contentEquals(e.getPackageName()==null?"":e.getPackageName()) && String.valueOf(e.getClassName()).contains("SelectInputActivity")){performGlobalAction(GLOBAL_ACTION_BACK);open();}}
 public void onInterrupt(){}
}

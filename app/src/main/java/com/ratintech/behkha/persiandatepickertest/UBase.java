package com.ratintech.behkha.persiandatepickertest;

import android.app.Application;
import android.content.Context;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class UBase extends Application {


  private Context context;
  private static Typeface typeface;
  private static AppCompatActivity currentActivity;
  private static LayoutInflater layoutInflater;
  //private static UBase base;
  public static final String SALT = "GDSGdldkglldsjkgklfgHNHGl#%#@lkdmgdskl&^*&^*2626295!@~!ep88118klmdgklfdWE993230177822233";

  @Override
  public void onCreate() {
    super.onCreate();

    //  base = this;
    context = getApplicationContext();
    layoutInflater = LayoutInflater.from(context);


  }



  public static String getCheckCode(AppCompatActivity activity) {
    return PreferenceManager.getDefaultSharedPreferences(activity).getString("error_code", null);
  }

  public static void setCurrentActivity(AppCompatActivity activity) {
    currentActivity = activity;
  }

  public static AppCompatActivity getCurrentActivity() {
    return currentActivity;
  }

  public static void clearCurrentActivity() {
    currentActivity = null;
  }


  public static LayoutInflater getLayoutInflater() {
    return layoutInflater;
  }


  public static View inflateLayout(@LayoutRes int res) {
    return layoutInflater.inflate(res, null);
  }

  public static View inflateLayout(@LayoutRes int res, @Nullable ViewGroup root) {
    return layoutInflater.inflate(res, root);
  }


  public static String md5(final String s) {
    final String MD5 = "MD5";
    try {
      // Create MD5 Hash
      MessageDigest digest = MessageDigest.getInstance(MD5);
      digest.update(s.getBytes());
      byte messageDigest[] = digest.digest();
      // Create Hex String
      StringBuilder hexString = new StringBuilder();
      for (byte aMessageDigest : messageDigest) {
        String h = Integer.toHexString(0xFF & aMessageDigest);
        while (h.length() < 2)
          h = "0" + h;
        hexString.append(h);
      }
      return hexString.toString();

    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }
    return "";
  }

  public static void hash256(String caseString) {
    try {
      MessageDigest digest = MessageDigest.getInstance("SHA-256");

      byte[] u = digest.digest((caseString + UBase.SALT).getBytes("UTF-8"));

      StringBuilder result = new StringBuilder();
      for (byte bb : u) {
        result.append(String.format("%02X", bb));
      }
      Log.e("SHA-256 ", result.toString() + " \n ");

    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
  }

  private static final String HEXES = "0123456789ABCDEF";

  private static String getHex(byte[] raw) {
    final StringBuilder hex = new StringBuilder(2 * raw.length);
    for (final byte b : raw) {
      hex.append(HEXES.charAt((b & 0xF0) >> 4)).append(HEXES.charAt((b & 0x0F)));
    }
    return hex.toString();
  }


  public static Typeface setMyFont() {
    return typeface;
  }


}

package com.android.sjq.wanandroid.tool;

import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import static android.content.ContentValues.TAG;

/**
 * Created by Administrator on 2016/10/14.
 */

public class StrHelper {
    static int i = 0;

    public static void applyFont(final Context context, final View root, final String fontName) {

        try {
            if (root instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) root;
                for (int i = 0; i < viewGroup.getChildCount(); i++) {
                    applyFont(context, viewGroup.getChildAt(i), fontName);
                }
            } else if (root instanceof TextView) {
                i++;
                Log.print("textview", i + "");
                ((TextView) root).setTypeface(Typeface.createFromAsset(context.getAssets(), fontName));
            }
        } catch (Exception e) {
            Log.print(TAG, String.format("Error occured when trying to apply %s font for %s view", fontName, root));
            e.printStackTrace();
        }
    }

}

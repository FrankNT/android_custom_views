package io.eclub.customviews.util;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.TypedValue;

public class UIUtils {
    public static int dpToPx(@NonNull Context context, int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dp, context.getResources().getDisplayMetrics());
    }
}

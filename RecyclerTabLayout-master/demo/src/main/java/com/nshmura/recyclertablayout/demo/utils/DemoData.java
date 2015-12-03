package com.nshmura.recyclertablayout.demo.utils;

import com.nshmura.recyclertablayout.demo.ColorItem;
import com.nshmura.recyclertablayout.demo.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.graphics.Color;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Shinichi Nishimura on 2015/07/22.
 */
public class DemoData {

    private DemoData() {

    }

    public static List<ColorItem> loadDemoColorItems(Context context) {
        List<ColorItem> items = new ArrayList<>();

        try {
            JSONObject obj = new JSONObject(loadJSONFromAsset(context, "colors.json"));
            Iterator<String> keys = obj.keys();
            while (keys.hasNext()) {
                String key = keys.next();
                JSONObject value = obj.getJSONObject(key);
                ColorItem colorItem = new ColorItem();
                colorItem.name = value.getString("name");
                colorItem.hex = value.getString("hex");
                JSONArray rgb = value.getJSONArray("rgb");
                colorItem.color = Color.rgb(rgb.getInt(0), rgb.getInt(1), rgb.getInt(2));
                items.add(colorItem);
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return items;
    }

    public static String loadJSONFromAsset(Context context, String filename) throws IOException {
        InputStream is = context.getAssets().open(filename);
        int size = is.available();
        byte[] buffer = new byte[size];
        is.read(buffer);
        is.close();
        return new String(buffer, "UTF-8");
    }

    public static List<Integer> loadImageResourceList() {
        return new ArrayList<>(Arrays.asList(
                R.drawable.ic_3d_rotation_white_36dp,
                R.drawable.ic_accessibility_white_36dp,
                R.drawable.ic_account_balance_wallet_white_36dp,
                R.drawable.ic_account_balance_white_36dp,
                R.drawable.ic_account_box_white_36dp,
                R.drawable.ic_account_circle_white_36dp
                ));
    }
}

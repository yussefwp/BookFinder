package group151.bookfinder;

/**
 * Created by Anh on 3/24/2017.
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Set;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.SectionIndexer;
import android.widget.TextView;

public class CustomListAdapter extends BaseAdapter implements  SectionIndexer{

    Context ctx;
    HashMap<String,Integer> alphaIndexer;
    String[] sections;
    String[] items = { "Intro to Algo", "Data Structures", "Information Security", "Intro to Diff Eq",
                        "Make America Great Again", "Intro to database", "Intro to machine Learning", "Calculus1", "book1", "book2", "book3", "book4",
    "Book5","Book6","Book7","Book8","Book9","Book10","Book11","Book12","Book13","Book14","Book15","Book16","Book17","Book18","Book19","Book20","Book21",};

    public CustomListAdapter(Context ctx) {
        this.ctx = ctx;
        alphaIndexer = new HashMap<String,Integer>();
        int size = items.length;

        for (int x = 0; x < size; x++) {
            String s = items[x];
            String ch = s.substring(0, 1);
            ch = ch.toUpperCase();
            if (!alphaIndexer.containsKey(ch))
                alphaIndexer.put(ch, x);
        }

        Set<String> sectionLetters = alphaIndexer.keySet();
        ArrayList<String> sectionList = new ArrayList<String>(sectionLetters);
        Collections.sort(sectionList);
        sections = new String[sectionList.size()];
        sectionList.toArray(sections);
    }

    @Override
    public int getCount() {
        return items.length;
    }

    @Override
    public Object getItem(int position) {
        return items[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LinearLayout ll = new LinearLayout(ctx);
        ll.setOrientation(LinearLayout.VERTICAL);

        TextView tv = new TextView(ctx);
        tv.setText(items[position]);

        ll.addView(tv);

        return ll;
    }

    @Override
    public int getPositionForSection(int section) {
        return alphaIndexer.get(sections[section]);
    }

    @Override
    public int getSectionForPosition(int position) {
        return 0;

    }

    @Override
    public Object[] getSections() {
        return sections;
    }

}
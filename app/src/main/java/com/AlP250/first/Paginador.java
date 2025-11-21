package com.AlP250.first;

import android.content.Context;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;


public class Paginador extends FragmentPagerAdapter {
    private final Context mContext;

    public Paginador(Context context, FragmentManager fm){
        super(fm);
        mContext=context;
    }
    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new Cositas();
            case 1:
                return new Cositas2();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }
}

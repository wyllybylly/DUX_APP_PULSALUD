package com.application.pulsalud.onBoarding;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import java.util.ArrayList;

public class ViewPagerAdapter extends FragmentStateAdapter {

    private final ArrayList<Fragment> fragment_list;

    public ViewPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle, ArrayList<Fragment> fragment_list) {
        super(fragmentManager, lifecycle);
        this.fragment_list = fragment_list;
    }

    @Override
    public int getItemCount() {
        return fragment_list.size();
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return fragment_list.get(position);
    }

}

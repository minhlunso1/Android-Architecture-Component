package minhna.android.androidarchitecturecomponent.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import minhna.android.androidarchitecturecomponent.R;
import minhna.android.androidarchitecturecomponent.util.ExtensionUtils;

/**
 * Created by Minh on 2/20/2018.
 */

public class JavaFragment extends BaseFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return ExtensionUtils.inflate(container, R.layout.fragment_first, false);
    }
}

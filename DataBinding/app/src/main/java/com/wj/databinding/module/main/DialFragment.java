package com.wj.databinding.module.main;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.wj.databinding.R;
import com.wj.databinding.databinding.FragmentDialBinding;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Author：王江 on 2016/9/12 20:38
 * Description:
 */
public class DialFragment extends BaseFragment {

    private FragmentDialBinding mFragmentDialBinding;

    @Override
    public final void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ArrayList<String> numbers = new ArrayList<>();
        numbers.addAll(Arrays.asList(getActivity().getResources().getStringArray(R.array.numbers)));
        mFragmentDialBinding = (FragmentDialBinding) getViewDataBinding();
        mFragmentDialBinding.setList(numbers);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_dial;
    }

    @Override
    public void setListener() {
        ((FragmentDialBinding) getViewDataBinding()).setListener(this);
    }

    public final void onClick(View v) {

        if (v instanceof Button) {
            mFragmentDialBinding.dialInput.append(((Button) v).getText());
        } else if (v instanceof ImageButton) {
            if (v.equals(mFragmentDialBinding.dialCall)) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + mFragmentDialBinding.dialInput.getText().toString()));
                startActivity(intent);
            } else if (v.equals(mFragmentDialBinding.dialDelete)) {
                String content = mFragmentDialBinding.dialInput.getText().toString();
                if (content != null && content.length() >= 1) {
                    mFragmentDialBinding.dialInput.setText(content.substring(0, content.length() - 1));
                }
            }
        }


    }
}

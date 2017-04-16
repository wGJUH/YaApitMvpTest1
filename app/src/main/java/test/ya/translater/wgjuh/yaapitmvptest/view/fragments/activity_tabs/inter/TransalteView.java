package test.ya.translater.wgjuh.yaapitmvptest.view.fragments.activity_tabs.inter;

import android.support.v4.app.FragmentManager;

import test.ya.translater.wgjuh.yaapitmvptest.view.fragments.View;

/**
 * Created by wGJUH on 04.04.2017.
 */

public interface TransalteView extends View {

    FragmentManager getTranslateFragmentManager();

    android.view.View getInputFrame();

    android.view.View getTranslateFrame();

    void setFromLanguageTextView(String s);

    void setToLanguageTextView(String s);
}
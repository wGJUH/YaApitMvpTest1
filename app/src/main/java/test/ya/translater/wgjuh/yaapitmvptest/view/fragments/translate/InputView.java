package test.ya.translater.wgjuh.yaapitmvptest.view.fragments.translate;

import test.ya.translater.wgjuh.yaapitmvptest.view.fragments.View;

/**
 * Created by wGJUH on 04.04.2017.
 */

public interface InputView extends View {

    void clearText();

    String getTargetText();

    void setText(String text);
}
